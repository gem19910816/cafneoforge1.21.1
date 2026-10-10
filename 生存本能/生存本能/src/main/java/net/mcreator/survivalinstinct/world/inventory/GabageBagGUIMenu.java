package net.mcreator.survivalinstinct.world.inventory;
import java.util.HashMap;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
public final class GabageBagGUIMenu extends PortContainerMenu {
    public static final HashMap<String,Object> guistate = new HashMap<>();
    public GabageBagGUIMenu(int id, Inventory inv, FriendlyByteBuf data) { this(id, inv, data == null ? inv.player.blockPosition() : data.readBlockPos(), data != null && data.readByte() == 1 ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND); }
    public GabageBagGUIMenu(int id, Inventory inv, BlockPos pos, InteractionHand hand) { super(SurvivalInstinctModMenus.GABAGE_BAG_GUI.get(), id, inv, pos, hand, 9, 7, 36); }
}
