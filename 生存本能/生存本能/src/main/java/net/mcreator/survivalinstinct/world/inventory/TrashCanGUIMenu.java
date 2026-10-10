package net.mcreator.survivalinstinct.world.inventory;
import java.util.HashMap;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
public final class TrashCanGUIMenu extends PortContainerMenu {
    public static final HashMap<String,Object> guistate = new HashMap<>();
    public TrashCanGUIMenu(int id, Inventory inv, FriendlyByteBuf data) { this(id, inv, data == null ? inv.player.blockPosition() : data.readBlockPos(), null); }
    public TrashCanGUIMenu(int id, Inventory inv, BlockPos pos, InteractionHand hand) { super(SurvivalInstinctModMenus.TRASH_CAN_GUI.get(), id, inv, pos, hand, 27, 7, 18); }
}
