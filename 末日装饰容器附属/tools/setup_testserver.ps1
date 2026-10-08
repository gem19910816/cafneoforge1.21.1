# 搭建一个只用于实测的 NeoForge 专用服务器（复用 doomsday1211\server-test 的 libraries，不重复下载）。
param(
    [string]$ProjectRoot = 'C:\Users\79662\Desktop\doomsday-containers',
    [string]$SourceServer = 'C:\Users\79662\Desktop\doomsday1211\server-test',
    [string]$Jar = 'C:\Users\79662\Desktop\doomsday-containers\dist\doomsdaycontainers-1.0.0.jar'
)
$ErrorActionPreference = 'Stop'
$root = Join-Path $ProjectRoot 'testserver'
New-Item -ItemType Directory -Force -Path $root | Out-Null

# libraries：目录联接，省 176MB
$libLink = Join-Path $root 'libraries'
if (-not (Test-Path $libLink)) {
    cmd /c "mklink /J `"$libLink`" `"$SourceServer\libraries`"" | Out-Null
}

Copy-Item (Join-Path $SourceServer 'server.jar') (Join-Path $root 'server.jar') -Force
Copy-Item (Join-Path $SourceServer 'user_jvm_args.txt') (Join-Path $root 'user_jvm_args.txt') -Force
Set-Content (Join-Path $root 'user_jvm_args.txt') "-Xmx2G`r`n-Xms1G" -Encoding ASCII
Set-Content (Join-Path $root 'eula.txt') 'eula=true' -Encoding ASCII

$props = @'
allow-flight=true
difficulty=peaceful
enable-command-block=false
enable-rcon=false
enforce-secure-profile=false
gamemode=creative
level-name=world_ddc
level-type=minecraft\:flat
max-players=2
max-tick-time=-1
motd=DDC test
online-mode=false
op-permission-level=4
spawn-protection=0
spawn-monsters=false
spawn-npcs=false
sync-chunk-writes=false
view-distance=4
'@
Set-Content (Join-Path $root 'server.properties') $props -Encoding ASCII

# mods
$mods = Join-Path $root 'mods'
New-Item -ItemType Directory -Force -Path $mods | Out-Null
Get-ChildItem $mods -Filter *.jar | Remove-Item -Force
Copy-Item (Join-Path $SourceServer 'mods\doomsday_decoration-1.1.3-neoforge-1.21.1.jar') $mods -Force
Copy-Item $Jar $mods -Force

# 测试数据包
$dp = Join-Path $root 'world_ddc\datapacks\ddc_test'
New-Item -ItemType Directory -Force -Path (Join-Path $dp 'data\ddc_test\function'), (Join-Path $dp 'data\minecraft\tags\function') | Out-Null
Set-Content (Join-Path $dp 'pack.mcmeta') '{"pack":{"pack_format":48,"description":"DDC selftest"}}' -Encoding ASCII
Set-Content (Join-Path $dp 'data\minecraft\tags\function\load.json') '{"values":["ddc_test:load"]}' -Encoding ASCII
Set-Content (Join-Path $dp 'data\minecraft\tags\function\tick.json') '{"values":["ddc_test:tick"]}' -Encoding ASCII
$fn = Join-Path $dp 'data\ddc_test\function'
Set-Content (Join-Path $fn 'load.mcfunction') @'
scoreboard objectives add ddc_stage dummy
scoreboard objectives add ddc_timer dummy
scoreboard players set #timer ddc_timer 0
say [DDC] datapack loaded
'@ -Encoding ASCII
Set-Content (Join-Path $fn 'tick.mcfunction') @'
scoreboard players add #timer ddc_timer 1
execute if score #timer ddc_timer matches 200.. run function ddc_test:run
'@ -Encoding ASCII
Set-Content (Join-Path $fn 'run.mcfunction') @'
scoreboard players set #timer ddc_timer 0
execute if score #stage ddc_stage matches 0 run function ddc_test:stage0
execute if score #stage ddc_stage matches 1 run function ddc_test:stage1
'@ -Encoding ASCII
Set-Content (Join-Path $fn 'stage0.mcfunction') @'
scoreboard players set #stage ddc_stage 1
say [DDC] STAGE0 start
gamerule sendCommandFeedback true
fill 96 -62 96 116 -61 116 minecraft:stone
fill 96 -60 96 116 -55 116 minecraft:air
doomsdaycontainers stats
doomsdaycontainers selftest
setblock 100 -60 100 doomsday_decoration:cabinet
data get block 100 -60 100
item replace block 100 -60 100 container.0 with minecraft:diamond 3
data get block 100 -60 100
setblock 100 -59 100 minecraft:hopper[facing=down]
item replace block 100 -59 100 container.0 with minecraft:emerald 4
say [DDC] hopper ready
setblock 104 -60 100 doomsday_decoration:woodencrate
item replace block 104 -60 100 container.0 with minecraft:gold_ingot 2
setblock 104 -60 100 minecraft:air destroy
execute if entity @e[type=item,x=104,y=-60,z=100,distance=..4] run say [DDC] DROP_OK
setblock 108 -60 100 doomsday_decoration:acrate
item replace block 108 -60 100 container.0 with minecraft:diamond 7
data get block 108 -60 100
setblock 110 -60 100 doomsday_decoration:safe
item replace block 110 -60 100 container.0 with minecraft:netherite_ingot 1
data get block 110 -60 100
say [DDC] STAGE0 done
stop
'@ -Encoding ASCII
Set-Content (Join-Path $fn 'stage1.mcfunction') @'
scoreboard players set #stage ddc_stage 2
say [DDC] STAGE1 start
doomsdaycontainers selftest
execute if data block 100 -60 100 Items[{id:"minecraft:emerald"}] run say [DDC] HOPPER_OK
execute if data block 100 -60 100 Items[{id:"minecraft:diamond"}] run say [DDC] ITEM_REPLACE_OK
execute if data block 110 -60 100 Items[{id:"minecraft:netherite_ingot"}] run say [DDC] PERSIST_OK
execute if data block 108 -60 100 Items[{id:"minecraft:diamond"}] run say [DDC] CRATE_KEEP_OK
say [DDC] STAGE1 done
stop
'@ -Encoding ASCII

"测试服务器目录: $root"
Get-ChildItem $mods | Select-Object Name,Length
