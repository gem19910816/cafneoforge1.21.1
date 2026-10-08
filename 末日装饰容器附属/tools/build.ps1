# 离线编译 + 打包（复用 doomsday1211 工程已经拉好的 NeoForge 依赖，无需联网、无需 Gradle）。
param(
    [string]$ProjectRoot = 'C:\Users\79662\Desktop\doomsday-containers',
    [string]$ClasspathFile = 'C:\Users\79662\Desktop\doomsday1211\build-manual\classpath.txt',
    [string]$Jdk = 'C:\Users\79662\jdk21\jdk-21.0.12.1+1'
)

$ErrorActionPreference = 'Stop'
$out = Join-Path $ProjectRoot 'build\classes'
$dist = Join-Path $ProjectRoot 'dist'
$srcJava = Join-Path $ProjectRoot 'src\main\java'
$srcRes = Join-Path $ProjectRoot 'src\main\resources'

if (Test-Path $out) { Remove-Item -Recurse -Force $out }
New-Item -ItemType Directory -Force -Path $out, $dist | Out-Null

$cp = ([System.IO.File]::ReadAllText($ClasspathFile, [System.Text.Encoding]::UTF8)).Trim()
$files = Get-ChildItem $srcJava -Recurse -File -Filter *.java | ForEach-Object { $_.FullName }

$args = New-Object System.Collections.Generic.List[string]
$args.AddRange([string[]]@('-encoding', 'UTF-8', '-proc:none', '-Xlint:none', '-Xmaxerrs', '200', '-d', $out, '-classpath', $cp))
$args.AddRange([string[]]$files)
$argsFile = Join-Path $ProjectRoot 'build\javac.args'
[System.IO.File]::WriteAllLines($argsFile, $args, (New-Object System.Text.UTF8Encoding($false)))

"编译 $($files.Count) 个源文件 …"
& "$Jdk\bin\javac.exe" "@$argsFile"
if ($LASTEXITCODE -ne 0) { throw "javac 失败，退出码 $LASTEXITCODE" }

# 资源
Copy-Item -Path (Join-Path $srcRes '*') -Destination $out -Recurse -Force

$ver = '1.0.0'
$jarPath = Join-Path $dist "doomsdaycontainers-$ver.jar"
if (Test-Path $jarPath) { Remove-Item -Force $jarPath }
& "$Jdk\bin\jar.exe" --create --file $jarPath -C $out .
if ($LASTEXITCODE -ne 0) { throw "jar 打包失败" }

"产物: $jarPath  ($([math]::Round((Get-Item $jarPath).Length / 1KB, 1)) KB)"
