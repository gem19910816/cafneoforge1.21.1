# 生成"像容器的方块"目标清单。
# 权威映射来自 DoomsdayDecorationModBlocks.java 的 REGISTRY.register("id", () -> new XxxBlock())，
# 中文名来自 assets/doomsday_decoration/lang/en_us.json。
#
# 产物：
#   src/main/resources/doomsdaycontainers/container_targets.json   (附属运行时读取)
#   tools/targets_report.tsv                                        (人工核对)
param(
    [string]$SrcRoot = 'C:\Users\79662\Desktop\doomsday1211\project\src\main',
    [string]$ProjectRoot = 'C:\Users\79662\Desktop\doomsday-containers'
)

$ErrorActionPreference = 'Stop'

$blocksInit = Join-Path $SrcRoot 'java\net\mcreator\doomsdaydecoration\init\DoomsdayDecorationModBlocks.java'
$langPath   = Join-Path $SrcRoot 'resources\assets\doomsday_decoration\lang\en_us.json'
$blockDir   = Join-Path $SrcRoot 'java\net\mcreator\doomsdaydecoration\block'

function Read-Utf8([string]$p) { [System.IO.File]::ReadAllText($p, [System.Text.Encoding]::UTF8) }

# ---- 1. id -> 类名 ----
$initText = Read-Utf8 $blocksInit
$idToClass = @{}
foreach ($m in [regex]::Matches($initText, 'REGISTRY\.register\("([a-z0-9_]+)",\s*\(\)\s*->\s*new\s+(\w+)\(')) {
    $idToClass[$m.Groups[1].Value] = $m.Groups[2].Value
}

# ---- 2. id -> 中文名 ----
$json = (Read-Utf8 $langPath) | ConvertFrom-Json
$names = @{}
foreach ($p in $json.PSObject.Properties) {
    if ($p.Name -like 'block.doomsday_decoration.*') {
        $names[$p.Name.Substring('block.doomsday_decoration.'.Length)] = [string]$p.Value
    }
}

# ---- 3. 筛选规则：关键词 -> 格子数（按顺序先匹配先得） ----
$rules = [ordered]@{
    '储物柜' = 54
    '遗体' = 9; '裹尸袋' = 9
    '保险箱' = 27; '保险柜' = 27
    '冷藏' = 27; '恒温培养箱' = 27; '保温柜' = 27; '培养箱' = 27
    '冰箱' = 27; '冰柜' = 27
    '文件柜' = 27; '抽屉' = 18
    '衣柜' = 54; '柜子' = 27; '木柜' = 27; '金属柜' = 27; '玻璃柜' = 27; '柜' = 27
    '板条箱' = 9
    '木箱' = 27; '纸箱' = 27; '塑料箱' = 27; '武器箱' = 27
    '弹药箱' = 18; '医疗箱' = 18; '配件箱' = 18; '工具箱' = 18; '药箱' = 18; '急救包' = 9
    '行李箱' = 27
    '垃圾桶' = 27; '垃圾袋' = 9
    '邮箱' = 9; '邮筒' = 9
    '货架' = 27; '架子' = 27; '书架' = 27
    '售货机' = 27; '自动取款机' = 9; '收银台' = 9
    '油桶' = 27; '金属桶' = 27
    '购物车' = 27; '小推车' = 27
    '微波炉' = 9; '洗衣机' = 9; '饮水机' = 9; '咖啡机' = 9
    '箱' = 27; '罐' = 9; '桶' = 27
}

# 明显不是容器的排除项（误命中关键词）
$exclude = @(
    'closestool',          # 马桶
    'anticollisionbucket', 'anticollisionbucket_2',   # 防撞桶（路面隔离墩）
    'bucket',              # 水桶（装饰）
    'gastank',             # 煤气罐（压力容器，无盖）
    'jar', 'robloxjar', 'ziptopcan'                    # 易拉罐/饮料罐（单个饮品）
)

# 手工覆盖格子数
$slotOverride = @{ 'acrate' = 9; 'acrate_2' = 9; 'acrate_3' = 9; 'lockers' = 54 }

# 开关音效分类：按注册 id 正则匹配，越具体越靠前（对应附属里 ContainerSounds 的类别表）
$soundRules = [ordered]@{
    '^remains'                             = 'corpse'        # 遗体：黏腻
    '^bodybag'                             = 'body_bag'      # 裹尸袋：拉链 + 布料
    '^trashbag'                            = 'luggage'       # 垃圾袋：布料
    '^(luggage|blackluggage|blueluggage|greenluggage|greyluggage|khakiluggage|redluggage)$' = 'luggage'
    '^mailbox'                             = 'metal_flap'    # 邮箱：翻盖咔哒
    '^(vendingmachine|atm|cashregister)'   = 'vault'         # 售货机 / ATM / 收银台：卷帘
    '^(waterdispenser|coffee_machine)$'    = 'appliance'     # 饮水机 / 咖啡机
    '^(cart|cart_2|shopping_cart)$'        = 'cart'          # 推车：金属碰撞
    '^(shelf|goodsshelves)'                = 'wood_shelf'    # 货架：没有门，木器轻响
    '^woodendrawer'                        = 'wood_drawer'   # 木抽屉
    '^(metaldrawer|officedrawers)'         = 'metal_lid'     # 金属抽屉 / 文件柜
    '^(cabinet_2|cabinet_3)$'              = 'glass_cabinet' # 玻璃柜
    '^(cabinet|woodencabinet)'             = 'wood_cabinet'  # 木柜
    '^(metalcabinet|lockers|safe|fridge|freezer|insulationcabinet|constanttemperatureincubator|washingmachine)' = 'metal_door'
    '^(toolbox|ammunitionbox|medicalbox|accessorybox|firstaidkit|oiltank|oildrum|trashcan|indoorgarbagebin|microwaveoven)' = 'metal_lid'
    '^carton'                              = 'cardboard'     # 纸箱
    '^plasticbox'                          = 'plastic'       # 塑料箱
    '^(acrate|woodencrate|weaponbox)'      = 'wood_crate'    # 木箱 / 板条箱
}

$targets = [ordered]@{}
$rows = New-Object System.Collections.Generic.List[string]
$special = New-Object System.Collections.Generic.List[string]
$unmatchedSound = New-Object System.Collections.Generic.List[string]

foreach ($id in ($idToClass.Keys | Sort-Object)) {
    if ($exclude -contains $id) { continue }
    $cn = $names[$id]
    if (-not $cn) { continue }
    $slots = 0; $hit = $null
    foreach ($k in $rules.Keys) { if ($cn.Contains($k)) { $hit = $k; $slots = $rules[$k]; break } }
    if (-not $hit) { continue }
    if ($slotOverride.ContainsKey($id)) { $slots = $slotOverride[$id] }

    $cls = $idToClass[$id]
    $file = Join-Path $blockDir "$cls.java"
    $text = if (Test-Path $file) { Read-Utf8 $file } else { '' }
    $hasEntityBlock   = $text -match 'implements[^{]*\bEntityBlock\b'
    $declaresNewBE    = $text -match 'BlockEntity\s+newBlockEntity\s*\('
    $declaresMenu     = $text -match 'MenuProvider\s+getMenuProvider\s*\('
    $declaresUse      = $text -match 'useWithoutItem\s*\('
    $declaresOnRemove = $text -match 'void\s+onRemove\s*\('
    $declaresAnalog   = $text -match 'hasAnalogOutputSignal\s*\('

    $conflict = $hasEntityBlock -or $declaresNewBE -or $declaresMenu -or $declaresUse -or $declaresOnRemove -or $declaresAnalog

    # 音效分类
    $soundCat = $null
    foreach ($pat in $soundRules.Keys) { if ($id -match $pat) { $soundCat = $soundRules[$pat]; break } }
    if (-not $soundCat) { $soundCat = 'wood_crate'; $unmatchedSound.Add($id) }

    $entry = [ordered]@{ '@class' = $cls; slots = $slots; name = $cn; sound = $soundCat }
    if ($hasEntityBlock -or $declaresNewBE) { $entry['native'] = $true }   # 原模组自带方块实体，附属只修补交互/界面
    $targets[$id] = $entry
    $rows.Add(($id, $cn, $cls, $slots, $hit, $conflict, $soundCat) -join "`t")
    if ($conflict) { $special.Add("$id ($cls) 已自带: EntityBlock=$hasEntityBlock newBlockEntity=$declaresNewBE menu=$declaresMenu use=$declaresUse onRemove=$declaresOnRemove analog=$declaresAnalog") }
}

# ---- 4. 输出 ----
$outDir = Join-Path $ProjectRoot 'src\main\resources\doomsdaycontainers'
New-Item -ItemType Directory -Force -Path $outDir | Out-Null
$payload = [ordered]@{
    '_comment' = '末日装饰：容器附属 — 目标方块清单。slots 必须为 9 的倍数(9..54)。可自由增删条目：运行时按 id 查找方块，找不到的条目会被跳过。'
    targets    = $targets
}
$jsonOut = $payload | ConvertTo-Json -Depth 5
[System.IO.File]::WriteAllText((Join-Path $outDir 'container_targets.json'), $jsonOut, (New-Object System.Text.UTF8Encoding($false)))

$reportPath = Join-Path $ProjectRoot 'tools\targets_report.tsv'
("id`t中文名`t类名`tslots`t命中`t冲突`t音效类别") + "`r`n" + ($rows -join "`r`n") | Set-Content -Path $reportPath -Encoding UTF8

"目标方块数: $($targets.Count) / 全部方块: $($idToClass.Count)"
"需要特别处理的(自带容器实现): $($special.Count)"
$special | ForEach-Object { "  $_" }
$byCat = $targets.Values | Group-Object { $_.sound } | Sort-Object Name
"音效分类分布:"
$byCat | ForEach-Object { "  {0,-14} {1,3} 个   {2}" -f $_.Name, $_.Count, (($_.Group | ForEach-Object { $_.name } | Select-Object -Unique) -join '/') }
if ($unmatchedSound.Count -gt 0) {
    "没有匹配到音效规则的方块（已按 wood_crate 处理，建议补规则）:"
    $unmatchedSound | ForEach-Object { "  $_" }
}
"JSON: $outDir\container_targets.json"
