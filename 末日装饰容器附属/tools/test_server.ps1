# 实测：启动专用服务器 -> 用 RCON 跑指令 -> 优雅停服 -> 二次启动验证持久化。
param(
    [string]$ProjectRoot = 'C:\Users\79662\Desktop\doomsday-containers',
    [string]$Jdk = 'C:\Users\79662\jdk21\jdk-21.0.12.1+1',
    [string]$RconPassword = 'ddcpass',
    [int]$RconPort = 25575,
    [switch]$KeepWorld
)
$ErrorActionPreference = 'Stop'
$serverDir = Join-Path $ProjectRoot 'testserver'
$reportDir = Join-Path $ProjectRoot 'report'
New-Item -ItemType Directory -Force -Path $reportDir | Out-Null
$transcript = New-Object System.Collections.Generic.List[string]

function Log([string]$s) {
    Write-Output $s
    $transcript.Add($s)
}

# ---------------------------------------------------------------- RCON 客户端
function Send-Packet($stream, [int]$id, [int]$type, [string]$body) {
    $bodyBytes = [System.Text.Encoding]::UTF8.GetBytes($body)
    $len = 4 + 4 + $bodyBytes.Length + 2
    $buf = New-Object byte[] ($len + 4)
    [BitConverter]::GetBytes([int]$len).CopyTo($buf, 0)
    [BitConverter]::GetBytes([int]$id).CopyTo($buf, 4)
    [BitConverter]::GetBytes([int]$type).CopyTo($buf, 8)
    $bodyBytes.CopyTo($buf, 12)
    $stream.Write($buf, 0, $buf.Length)
    $stream.Flush()
}

function Read-Packet($stream) {
    $lenBuf = New-Object byte[] 4
    $read = 0
    while ($read -lt 4) {
        $n = $stream.Read($lenBuf, $read, 4 - $read)
        if ($n -le 0) { throw 'rcon 连接被关闭' }
        $read += $n
    }
    $len = [BitConverter]::ToInt32($lenBuf, 0)
    if ($len -lt 10 -or $len -gt 1MB) { throw "rcon 包长度异常: $len" }
    $buf = New-Object byte[] $len
    $read = 0
    while ($read -lt $len) {
        $n = $stream.Read($buf, $read, $len - $read)
        if ($n -le 0) { throw 'rcon 连接被关闭' }
        $read += $n
    }
    return @{
        id   = [BitConverter]::ToInt32($buf, 0)
        type = [BitConverter]::ToInt32($buf, 4)
        body = [System.Text.Encoding]::UTF8.GetString($buf, 8, $len - 10)
    }
}

function Invoke-Rcon([string]$command) {
    $client = New-Object System.Net.Sockets.TcpClient
    $client.Connect('127.0.0.1', $RconPort)
    $stream = $client.GetStream()
    $stream.ReadTimeout = 8000
    try {
        Send-Packet $stream 1 3 $RconPassword
        $auth = Read-Packet $stream
        if ($auth.id -ne 1) { throw 'rcon 密码错误' }
        Send-Packet $stream 2 2 $command
        $sb = New-Object System.Text.StringBuilder
        Start-Sleep -Milliseconds 120
        while ($stream.DataAvailable) {
            $p = Read-Packet $stream
            [void]$sb.Append($p.body)
            Start-Sleep -Milliseconds 40
        }
        return $sb.ToString()
    } finally {
        $stream.Dispose(); $client.Dispose()
    }
}

# ---------------------------------------------------------------- 服务器控制
$java = Join-Path $Jdk 'bin\java.exe'

# 服务器进程占着日志文件，必须用共享方式读
function Read-Shared([string]$path) {
    if (-not (Test-Path $path)) { return '' }
    $fs = [System.IO.File]::Open($path, [System.IO.FileMode]::Open, [System.IO.FileAccess]::Read, [System.IO.FileShare]::ReadWrite)
    try {
        $sr = New-Object System.IO.StreamReader($fs, [System.Text.Encoding]::UTF8)
        return $sr.ReadToEnd()
    } finally { $fs.Dispose() }
}

function Start-TestServer([string]$tag) {
    $out = Join-Path $reportDir "server_$tag.out.log"
    $err = Join-Path $reportDir "server_$tag.err.log"
    if (Test-Path $out) { Remove-Item $out -Force }
    $args = @('@user_jvm_args.txt', '@libraries/net/neoforged/neoforge/21.1.255/win_args.txt', 'nogui')
    $proc = Start-Process -FilePath $java -ArgumentList $args -WorkingDirectory $serverDir `
        -RedirectStandardOutput $out -RedirectStandardError $err -PassThru -NoNewWindow
    Log "启动测试服务器 (pid $($proc.Id))，输出: $out"
    $deadline = (Get-Date).AddMinutes(3)
    while ((Get-Date) -lt $deadline) {
        Start-Sleep -Seconds 3
        if (Test-Path $out) {
            $txt = Read-Shared $out
            if ($txt -match 'Done \(.+\)! For help') { Log '服务器已就绪 (Done)'; Start-Sleep -Seconds 2; return $proc }
            if ($proc.HasExited) { throw "服务器提前退出，见 $out / $err" }
        }
    }
    throw '服务器 3 分钟内没有就绪'
}

function Stop-TestServer($proc) {
    try { [void](Invoke-Rcon 'stop') } catch { Log "停服指令异常: $($_.Exception.Message)" }
    $deadline = (Get-Date).AddMinutes(2)
    while (-not $proc.HasExited -and (Get-Date) -lt $deadline) { Start-Sleep -Seconds 2 }
    if (-not $proc.HasExited) { Log '警告：服务器没有自行退出，强制结束'; $proc.Kill() } else { Log '服务器已优雅退出' }
}

$results = New-Object System.Collections.Generic.List[string]
function Run([string]$command, [string]$note = '') {
    try {
        $r = (Invoke-Rcon $command).Trim()
    } catch {
        $r = "!! RCON 失败: $($_.Exception.Message)"
    }
    $line = "> $command`n  $($r -replace "`r?`n", "`n  ")"
    if ($note) { $line = "  # $note`n$line" }
    Log $line
    $results.Add("$command`t$r")
    return $r
}

# ---------------------------------------------------------------- 测试流程
if (-not $KeepWorld) {
    $world = Join-Path $serverDir 'world_ddc'
    if (Test-Path $world) { Remove-Item -Recurse -Force $world }
    # 数据包不再需要（权限不足的 stop 会拖垮函数解析），RCON 直接跑指令
    New-Item -ItemType Directory -Force -Path $world | Out-Null
}

Log '======== 第一轮：方块实体 / 界面自检 / 漏斗能力 / 破坏掉落 ========'
$proc = Start-TestServer 'pass1'
Run 'gamerule sendCommandFeedback true'
Run 'forceload add 90 90 120 120' '把测试区区块强加载（默认出生点附近没加载到 x=100）'
Run 'fill 96 -62 96 116 -61 116 minecraft:stone'
Run 'fill 96 -60 96 116 -55 116 minecraft:air'
Run 'doomsdaycontainers stats'
Run 'doomsdaycontainers sounds' '⑨ 每个方块用的是哪一对开关音效'
Run 'doomsdaycontainers selftest'

Run 'setblock 100 -60 100 doomsday_decoration:cabinet'
Run 'data get block 100 -60 100' '① 方块实体是否随放置自动建立'
Run 'item replace block 100 -60 100 container.0 with minecraft:diamond 3' '② 通过 Container 接口放物品'
Run 'data get block 100 -60 100' '③ 物品是否存进去了'
Run 'setblock 100 -59 100 minecraft:hopper[facing=down]'
Run 'item replace block 100 -59 100 container.0 with minecraft:emerald 4' '④ 漏斗里放绿宝石，等它推进容器'
Start-Sleep -Seconds 4
Run 'data get block 100 -60 100' '⑤ 漏斗是否把绿宝石推进去了（走 NeoForge ItemHandler 能力）'

Run 'setblock 104 -60 100 doomsday_decoration:woodencrate'
Run 'item replace block 104 -60 100 container.0 with minecraft:gold_ingot 2'
Run 'setblock 104 -60 100 minecraft:air destroy' '⑥ 破坏方块'
Start-Sleep -Seconds 1
Run 'execute if entity @e[type=item,x=104,y=-60,z=100,distance=..4] run say DDC_DROP_OK' '⑦ 有掉落物'
Run 'data get entity @e[type=item,x=104,y=-60,z=100,distance=..4,limit=1]'
Run 'execute as @e[type=item,x=104,y=-60,z=100,distance=..4] run data get entity @s Item.id' '⑧ 掉落清单：方块本身 + 里面的金锭'
Run 'execute if entity @e[type=item,x=104,y=-60,z=100,distance=..4,nbt={Item:{id:"minecraft:gold_ingot"}}] run say DDC_CONTENT_DROP_OK' '⑨ 内容物确实掉落了'
Run 'kill @e[type=item,x=104,y=-60,z=100,distance=..4]'

Run 'setblock 106 -60 100 minecraft:chest' '对照组：原版箱子，同样破坏一次'
Run 'item replace block 106 -60 100 container.0 with minecraft:gold_ingot 1'
Run 'setblock 106 -60 100 minecraft:air destroy'
Run 'setblock 108 -60 100 doomsday_decoration:acrate'
Run 'item replace block 108 -60 100 container.0 with minecraft:diamond 7' '⑧ 原模组板条箱仍可用'
Run 'data get block 108 -60 100'

Run 'setblock 110 -60 100 doomsday_decoration:safe'
Run 'item replace block 110 -60 100 container.0 with minecraft:netherite_ingot 1' '⑨ 给二次启动留一件下界合金'
Run 'setblock 112 -60 100 doomsday_decoration:lockers' '⑩ 54 格储物柜'
Run 'item replace block 112 -60 100 container.53 with minecraft:stick 1'
Run 'data get block 112 -60 100'
Run 'setblock 114 -60 100 doomsday_decoration:firstaidkit' '⑪ 9 格急救包'
Run 'data get block 114 -60 100'
Run 'setblock 116 -60 100 doomsday_decoration:remains_1'
Run 'item replace block 116 -60 100 container.0 with minecraft:bone 2' '⑫ 遗体也能当容器'
Run 'data get block 116 -60 100'
Run 'setblock 118 -60 100 doomsday_decoration:bodybag'
Run 'item replace block 118 -60 100 container.0 with minecraft:rotten_flesh 3' '⑬ 裹尸袋'
Run 'data get block 118 -60 100'

Stop-TestServer $proc

Log ''
Log '======== 第二轮：重启后持久化验证 ========'
$proc = Start-TestServer 'pass2'
Run 'data get block 110 -60 100' '① 保险箱里的下界合金是否还在'
Run 'data get block 100 -60 100' '② 柜子里的钻石/绿宝石是否还在'
Run 'data get block 112 -60 100' '③ 储物柜第 54 格是否还在'
Run 'data get block 108 -60 100' '④ 板条箱内容是否还在'
Run 'data get block 116 -60 100' '⑤ 遗体里的骨头是否还在'
Run 'data get block 118 -60 100' '⑥ 裹尸袋里的腐肉是否还在'
Run 'doomsdaycontainers stats'
Run 'doomsdaycontainers selftest'
Stop-TestServer $proc

[System.IO.File]::WriteAllLines((Join-Path $reportDir 'test_commands.tsv'), $results, (New-Object System.Text.UTF8Encoding($false)))
[System.IO.File]::WriteAllLines((Join-Path $reportDir 'test_transcript.txt'), $transcript, (New-Object System.Text.UTF8Encoding($false)))

# ---------------------------------------------------------------- 日志体检
Log ''
Log '======== 服务器日志体检 ========'
foreach ($pass in @('pass1', 'pass2')) {
    $log = Join-Path $reportDir "server_$pass.out.log"
    # 服务器 stdout 用系统默认编码（中文 Windows 是 GBK），按 GBK 解一遍才能正确匹配中文
    $raw = [System.IO.File]::ReadAllBytes($log)
    $txt = [System.Text.Encoding]::GetEncoding(936).GetString($raw)
    $lines = $txt -split "`r?`n"
    $containerLine = $lines | Where-Object { $_ -match '容器化目标' } | Select-Object -First 1
    if ($containerLine) { Log "  [$pass] $containerLine" }
    $selftestOk = ($lines | Where-Object { $_ -match 'DDC_SELFTEST .* OK ' }).Count
    $soundOk = ($lines | Where-Object { $_ -match '派发=2' }).Count
    Log "  [$pass] 自检 OK 行 $selftestOk 条，其中音效确认为 2 次派发的 $soundOk 条"
    $soundMap = [regex]::Matches($txt, 'DDC_SOUND (\S+) .*?音效=(\S+) / (\S+)')
    if ($soundMap.Count -gt 0) {
        $opens = $soundMap | ForEach-Object { $_.Groups[2].Value } | Select-Object -Unique
        Log "  [$pass] 音效映射 $($soundMap.Count) 条，不同开关音效 $($opens.Count) 种：$($opens -join ', ')"
    }
    $markers = @('DDC_DROP_OK', 'DDC_CONTENT_DROP_OK')
    foreach ($m in $markers) {
        $hit = $lines | Where-Object { $_ -match $m } | Select-Object -First 1
        if ($hit) { Log "  [$pass] 命中标记 $m : $hit" }
    }
    $bad = $lines | Where-Object { $_ -match 'ERROR|FATAL|Exception' -and $_ -notmatch 'ErrorCallback|LoadingErrorHandler|No key layers' }
    if ($bad) {
        Log "  [$pass] 发现 $($bad.Count) 条 ERROR/异常："
        $bad | Select-Object -First 12 | ForEach-Object { Log "    $_" }
    } else {
        Log "  [$pass] 没有任何 ERROR / 异常"
    }
    $skip = $lines | Where-Object { $_ -match '跳过混入' }
    if ($skip) { Log "  [$pass] 有 $($skip.Count) 个混入被保险丝跳过："; $skip | Select-Object -First 5 | ForEach-Object { Log "    $_" } }
    $mixinWarn = $lines | Where-Object { $_ -match 'Mixin' -and $_ -match 'WARN' }
    if ($mixinWarn) { Log "  [$pass] 混入警告 $($mixinWarn.Count) 条："; $mixinWarn | Select-Object -First 8 | ForEach-Object { Log "    $_" } }
}

Log ''
Log "完整记录: $reportDir\test_transcript.txt"
