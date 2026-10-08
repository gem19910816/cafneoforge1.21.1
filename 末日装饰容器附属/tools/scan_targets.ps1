# 扫描 末日装饰 的方块：找出"看起来像容器"的方块，并报告它们的 Java 实现特征。
# 输出 TSV: id <TAB> 中文名 <TAB> 类名 <TAB> EntityBlock <TAB> useItemOn <TAB> useWithoutItem <TAB> onRemove <TAB> getMenuProvider <TAB> 命中关键词
param(
    [string]$SrcRoot = 'C:\Users\79662\Desktop\doomsday1211\project\src\main',
    [string]$OutFile = 'C:\Users\79662\Desktop\doomsday-containers\tools\targets_report.tsv'
)

$ErrorActionPreference = 'Stop'
$langPath = Join-Path $SrcRoot 'resources\assets\doomsday_decoration\lang\en_us.json'
$blockDir = Join-Path $SrcRoot 'java\net\mcreator\doomsdaydecoration\block'

$json = [System.IO.File]::ReadAllText($langPath, [System.Text.Encoding]::UTF8) | ConvertFrom-Json
$names = @{}
foreach ($p in $json.PSObject.Properties) {
    if ($p.Name -like 'block.doomsday_decoration.*') {
        $names[$p.Name.Substring('block.doomsday_decoration.'.Length)] = [string]$p.Value
    }
}

# 中文关键词 -> 容器类型/默认格子数
$rules = [ordered]@{
    '保险箱' = 27; '保险柜' = 27
    '冰箱' = 27; '冰柜' = 27; '冷藏' = 27; '恒温培养箱' = 27; '培养箱' = 27
    '储物柜' = 54; '置物柜' = 54; '更衣柜' = 54; '衣柜' = 54
    '柜子' = 27; '柜' = 27
    '文件柜' = 27; '抽屉' = 18
    '板条箱' = 9; '木箱' = 27; '纸箱' = 27; '塑料箱' = 27; '弹药箱' = 18; '医疗箱' = 18
    '配件箱' = 18; '工具箱' = 18; '药箱' = 18; '箱子' = 27; '箱' = 27
    '行李箱' = 27; '行李' = 27
    '垃圾桶' = 27; '垃圾' = 27
    '邮筒' = 9; '邮箱' = 9; '信箱' = 9
    '货架' = 27; '架子' = 27; '置物架' = 27; '书架' = 27
    '售货机' = 27; '自动取款机' = 9; '取款机' = 9; '收银台' = 9
    '油桶' = 27; '汽油桶' = 27; '桶' = 27
    '罐' = 9
    '篮子' = 9; '筐' = 9; '购物车' = 27; '小推车' = 27
    '微波炉' = 9; '洗衣机' = 9; '饮水机' = 9; '咖啡机' = 9
}

$rows = New-Object System.Collections.Generic.List[string]
$all = Get-ChildItem $blockDir -Filter *.java -File
foreach ($f in $all) {
    $id = $f.BaseName
    if ($id -notlike '*Block') { continue }
    $reg = $id.Substring(0, $id.Length - 5)                     # XxxBlock -> Xxx
    $snake = ($reg -creplace '(?<=[a-z0-9])(?=[A-Z])', '_').ToLower()
    if (-not $names.ContainsKey($snake)) { continue }
    $cn = $names[$snake]
    $hit = $null; $slots = 0
    foreach ($k in $rules.Keys) {
        if ($cn.Contains($k)) { $hit = $k; $slots = $rules[$k]; break }
    }
    if (-not $hit) { continue }
    $text = [System.IO.File]::ReadAllText($f.FullName, [System.Text.Encoding]::UTF8)
    $rows.Add(($snake, $cn, $id, ($text -match 'implements[^{]*EntityBlock'), ($text -match 'useItemOn\s*\('), ($text -match 'useWithoutItem\s*\('), ($text -match 'onRemove\s*\('), ($text -match 'getMenuProvider\s*\('), $hit, $slots) -join "`t")
}
$rows | Sort-Object | Set-Content -Path $OutFile -Encoding UTF8
"matched: $($rows.Count) / total blocks: $($all.Count)"
"report: $OutFile"
