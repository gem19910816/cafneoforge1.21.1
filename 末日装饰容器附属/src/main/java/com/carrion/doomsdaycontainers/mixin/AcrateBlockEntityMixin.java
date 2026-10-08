package com.carrion.doomsdaycontainers.mixin;

import com.carrion.doomsdaycontainers.ContainerSounds;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * 顺手修掉原模组已知的小毛病：板条箱 acrate 的界面。
 *
 * 原版（含 1.21.1 移植版）的 AcrateBlockEntity 用的是 ChestMenu.threeRows(id, inventory)，
 * 既没带自己的库存（界面永远是空的），又用了 3 行（27 格）而它实际只有 9 格。
 * 这里改成和它自身库存绑定的 1 行界面。
 *
 * 该混合是覆盖式（@Overwrite）：如果目标类里没有 createMenu，插件会跳过它，不会崩游戏。
 */
@Mixin(targets = "net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity", remap = false)
public abstract class AcrateBlockEntityMixin {
    @Overwrite
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new ChestMenu(MenuType.GENERIC_9x1, id, inventory, (Container) (Object) this, 1);
    }

    /**
     * 板条箱也要有开关音效。
     * 原模组没有实现 Container#startOpen / #stopOpen（用的是接口默认空实现），所以这里是补上，不是覆盖。
     */
    public void startOpen(Player player) {
        if (player.isSpectator()) return;
        ContainerSounds.playOpen((BlockEntity) (Object) this);
    }

    public void stopOpen(Player player) {
        if (player.isSpectator()) return;
        ContainerSounds.playClose((BlockEntity) (Object) this);
    }
}
