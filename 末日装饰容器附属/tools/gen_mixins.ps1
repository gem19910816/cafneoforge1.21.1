# 根据 container_targets.json 生成 Mixin 源码 + doomsdaycontainers.mixins.json。
# 生成规则：只注入目标方块类里"还没有"的方法，避免与原模组自带的实现冲突。
param(
    [string]$SrcRoot = 'C:\Users\79662\Desktop\doomsday1211\project\src\main',
    [string]$ProjectRoot = 'C:\Users\79662\Desktop\doomsday-containers'
)

$ErrorActionPreference = 'Stop'
function Read-Utf8([string]$p) { [System.IO.File]::ReadAllText($p, [System.Text.Encoding]::UTF8) }
function Write-Utf8([string]$p, [string]$t) {
    [System.IO.File]::WriteAllText($p, $t, (New-Object System.Text.UTF8Encoding($false)))
}

$targetsJson = Join-Path $ProjectRoot 'src\main\resources\doomsdaycontainers\container_targets.json'
$outDir = Join-Path $ProjectRoot 'src\main\java\com\carrion\doomsdaycontainers\mixin'
$blockDir = Join-Path $SrcRoot 'java\net\mcreator\doomsdaydecoration\block'
New-Item -ItemType Directory -Force -Path $outDir | Out-Null

$tmplTop = @'
package com.carrion.doomsdaycontainers.mixin;

import com.carrion.doomsdaycontainers.ContainerHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 自动生成，请勿手改 —— 改 tools/gen_mixins.ps1 后重跑。
 * 目标：doomsday_decoration:@ID@（@CN@），@SLOTS@ 格
 *
 * 让这个原本只是装饰的方块变成真正的容器：方块实体 + 原版箱子界面 + 物品管道能力。
 */
@Mixin(targets = "net.mcreator.doomsdaydecoration.block.@CLASS@", remap = false)
public abstract class @MIXIN@@IMPLEMENTS@ {
'@

$mNewBe = @'

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ContainerHooks.newBlockEntity((Block) (Object) this, pos, state);
    }
'@

$mMenu = @'

    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return ContainerHooks.getMenuProvider((Block) (Object) this, state, level, pos);
    }
'@

$mUse = @'

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        return ContainerHooks.useWithoutItem((Block) (Object) this, state, level, pos, player, hit);
    }
'@

$mRemove = @'

    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        ContainerHooks.onRemove((Block) (Object) this, state, level, pos, newState, movedByPiston);
    }
'@

$mAnalog = @'

    protected boolean hasAnalogOutputSignal(BlockState state) {
        return ContainerHooks.hasAnalogOutputSignal((Block) (Object) this, state);
    }

    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return ContainerHooks.getAnalogOutputSignal((Block) (Object) this, state, level, pos);
    }
'@

$json = (Read-Utf8 $targetsJson) | ConvertFrom-Json
$mixinNames = New-Object System.Collections.Generic.List[string]
$skipped = New-Object System.Collections.Generic.List[string]
$report = New-Object System.Collections.Generic.List[string]

foreach ($prop in $json.targets.PSObject.Properties) {
    $id = $prop.Name
    $cls = $prop.Value.'@class'
    $slots = [int]$prop.Value.slots
    $cn = [string]$prop.Value.name

    $file = Join-Path $blockDir "$cls.java"
    $text = if (Test-Path $file) { Read-Utf8 $file } else { '' }
    $alreadyEntityBlock = $text -match 'implements[^{]*\bEntityBlock\b'

    $body = ''
    $methods = @()
    if (-not ($text -match 'BlockEntity\s+newBlockEntity\s*\('))            { $body += $mNewBe;  $methods += 'newBlockEntity' }
    if (-not ($text -match 'MenuProvider\s+getMenuProvider\s*\('))          { $body += $mMenu;   $methods += 'getMenuProvider' }
    if (-not ($text -match 'useWithoutItem\s*\('))                          { $body += $mUse;    $methods += 'useWithoutItem' }
    if (-not ($text -match 'void\s+onRemove\s*\('))                         { $body += $mRemove; $methods += 'onRemove' }
    if (-not ($text -match 'hasAnalogOutputSignal\s*\('))                   { $body += $mAnalog; $methods += 'hasAnalogOutputSignal/getAnalogOutputSignal' }

    if ($methods.Count -eq 0) { $skipped.Add("$id ($cls)：无需注入"); continue }

    $mixin = "$($cls)Mixin"
    $top = $tmplTop.Replace('@ID@', $id).Replace('@CN@', $cn).Replace('@SLOTS@', $slots).Replace('@CLASS@', $cls)
    if ($alreadyEntityBlock) {
        $top = $top.Replace('@IMPLEMENTS@', '')
    } else {
        $top = $top.Replace('@IMPLEMENTS@', ' implements EntityBlock')
    }
    $top = $top.Replace('@MIXIN@', $mixin)
    Write-Utf8 (Join-Path $outDir "$mixin.java") ($top + $body + "`n}`n")
    $mixinNames.Add($mixin)
    $report.Add("$id`t$mixin`t$($methods -join ', ')")
}

# 手写的板条箱界面修补混入
$mixinNames.Add('AcrateBlockEntityMixin')

$cfg = [ordered]@{
    required           = $true
    minVersion         = '0.8.5'
    package            = 'com.carrion.doomsdaycontainers.mixin'
    compatibilityLevel = 'JAVA_21'
    plugin             = 'com.carrion.doomsdaycontainers.mixin.DoomsdayContainersMixinPlugin'
    mixins             = @($mixinNames | Sort-Object)
    injectors          = @{ defaultRequire = 1 }
}
$cfgJson = $cfg | ConvertTo-Json -Depth 5
Write-Utf8 (Join-Path $ProjectRoot 'src\main\resources\doomsdaycontainers.mixins.json') $cfgJson
Write-Utf8 (Join-Path $ProjectRoot 'tools\mixins_report.tsv') (("id`tmixin`t注入的方法`r`n") + ($report -join "`r`n"))

"生成混入类: $($mixinNames.Count) (含 1 个手写) / 目标: $($json.targets.PSObject.Properties.Count)"
$skipped | ForEach-Object { "跳过: $_" }
