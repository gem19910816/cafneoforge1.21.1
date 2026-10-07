package net.gem19910816.dyairdrop.world.inventory;

import net.gem19910816.dyairdrop.panel.AbstractPanelMenu;
import net.gem19910816.dyairdrop.init.DyairdropModMenus;
import net.gem19910816.dyairdrop.procedures.PannelREshutProcedure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

/**
 * 字母密码面板 RE2（a~f，带打字机动画）的容器。
 *
 * <p>注册 id 保持 {@code dyairdrop:pannel_re_2} 不变（{@link DyairdropModMenus#PANNEL_RE_2}）。
 */
public class PannelRE2Menu extends AbstractPanelMenu {

    public PannelRE2Menu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        super(DyairdropModMenus.PANNEL_RE_2.get(), id, inventory, extraData);
        PannelREshutProcedure.execute(this.entity);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        PannelREshutProcedure.execute(this.entity);
    }
}
