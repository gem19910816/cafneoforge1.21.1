package net.gem19910816.dyairdrop.panel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * 三个空投密码面板菜单（数字 / 字母 RE / 字母 RE2）的共同基类。
 *
 * <p>取代旧的 MCreator 产物：删掉恒为 false 的 {@code bound} 判定（原 {@code stillValid()} 因此**恒返回 true**，
 * 面板不会因为走远或方块被破坏而关闭）、删掉静态 {@code guistate}、删掉「每 tick 检查 containerMenu instanceof」
 * 的强制关闭写法（那会在别的模组替换/包装 containerMenu 时误伤）。
 *
 * <p>现在 {@link #stillValid(Player)} 是真正的校验：同一玩家、同一维度、8 格内、方块实体仍是容器。
 */
public abstract class AbstractPanelMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    private static final double MAX_DISTANCE_SQR = 64.0;

    public final Level world;
    public final Player entity;
    public int x;
    public int y;
    public int z;

    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final BlockPos pos;

    protected AbstractPanelMenu(MenuType<?> type, int id, Inventory inventory, FriendlyByteBuf extraData) {
        super(type, id);
        this.entity = inventory.player;
        this.world = inventory.player.level();
        BlockPos parsed = extraData == null ? BlockPos.ZERO : extraData.readBlockPos();
        this.pos = parsed.immutable();
        this.x = parsed.getX();
        this.y = parsed.getY();
        this.z = parsed.getZ();
    }

    public BlockPos panelPos() {
        return pos;
    }

    @Override
    public boolean stillValid(Player player) {
        if (!player.getUUID().equals(entity.getUUID())) {
            return false;
        }
        if (player.level() != world) {
            return false;
        }
        if (player.distanceToSqr(Vec3.atCenterOf(pos)) > MAX_DISTANCE_SQR) {
            return false;
        }
        return world.getBlockEntity(pos) instanceof Container;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }
}
