package net.mcreator.dyairdrop.world.inventory;

import net.gem19910816.dyairdrop.panel.AbstractPanelMenu;
import net.mcreator.dyairdrop.init.DyairdropModMenus;
import net.mcreator.dyairdrop.procedures.PannelREshutProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

/**
 * 字母密码面板 RE（a~f）的容器。
 *
 * <p>注册 id 保持 {@code dyairdrop:pannel_re} 不变（{@link DyairdropModMenus#PANNEL_RE}）。
 * 自动关窗逻辑（解锁成功后 {@code passwordre} 含 {@code Y}）已移到服务端的
 * {@link net.gem19910816.dyairdrop.panel.PanelService}，不再依赖「每 tick 检查容器类型」。
 */
public class PannelREMenu extends AbstractPanelMenu {

    public PannelREMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        super(DyairdropModMenus.PANNEL_RE.get(), id, inventory, extraData);
        PannelREshutProcedure.execute(this.entity);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        PannelREshutProcedure.execute(this.entity);
    }
}
