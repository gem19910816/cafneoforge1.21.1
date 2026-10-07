package net.mcreator.dyairdrop.world.inventory;

import java.util.HashMap;

import net.gem19910816.dyairdrop.panel.AbstractPanelMenu;
import net.gem19910816.dyairdrop.panel.PanelService;
import net.mcreator.dyairdrop.init.DyairdropModMenus;
import net.mcreator.dyairdrop.procedures.OpenProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

/**
 * 数字密码面板（0~9）的容器。
 *
 * <p>重构点：不再持有静态 {@code guistate}；不再在玩家 tick 里强制关闭容器；
 * {@code stillValid()} 由 {@link AbstractPanelMenu} 真正实现；打开时仍按原行为占用 {@code valid} 锁。
 *
 * <p>注册 id 保持 {@code dyairdrop:panel} 不变（{@link DyairdropModMenus#PANEL}）。
 */
public class PannelMenu extends AbstractPanelMenu {

    public PannelMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        super(DyairdropModMenus.PANEL.get(), id, inventory, extraData);
        // 原行为：打开时清空玩家输入缓存，并在 valid 为空时写入玩家名（占用锁）
        OpenProcedure.execute(this.world, this.x, this.y, this.z, this.entity, new HashMap<>());
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (player instanceof ServerPlayer serverPlayer) {
            PanelService.onPanelClosed(serverPlayer, panelPos());
        }
    }
}
