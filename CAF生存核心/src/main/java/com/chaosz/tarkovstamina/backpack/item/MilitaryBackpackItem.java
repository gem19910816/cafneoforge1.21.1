package com.chaosz.tarkovstamina.backpack.item;

import com.chaosz.tarkovstamina.backpack.menu.BackpackMenu;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

/**
 * CAF 军用背包。
 *
 * <p>1.21.1 的两处结构性变化：</p>
 * <ul>
 *   <li><b>物品 NBT → 数据组件。</b>{@code getOrCreateTag()} 已移除。这里刻意选
 *       {@code minecraft:custom_data}：1.20.5+ 的 DFU 会把老物品上的未知 NBT
 *       自动搬进这个组件，所以 1.20.1 存档里的背包内容（{@code Inventory} /
 *       {@code BackpackId} 两个键）原样就能读出来。</li>
 *   <li><b>Curios 能力 → {@link ICurioItem}。</b>1.20.1 那套
 *       {@code ICapabilityProvider} + {@code Capability} + {@code LazyOptional}
 *       全没了，Curios 9.x 直接让物品实现接口。</li>
 * </ul>
 */
public class MilitaryBackpackItem extends Item implements ICurioItem {
    /** Full-size CAF storage: twelve columns by nine rows. */
    public static final int SLOT_COUNT = 108;
    private static final String BACKPACK_ID = "BackpackId";

    private final int columns;
    private final int rows;

    public MilitaryBackpackItem(Properties properties) {
        this(properties, BackpackMenu.MILITARY_COLUMNS, BackpackMenu.MILITARY_ROWS);
    }

    public MilitaryBackpackItem(Properties properties, int columns, int rows) {
        super(properties);
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getSlotCount() {
        return columns * rows;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            ensureId(stack);
            // Right-click is an open action only. Curios can still equip the item manually.
            openMenu(serverPlayer, stack);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void openMenu(ServerPlayer player, ItemStack stack) {
        ensureId(stack);
        if (!(stack.getItem() instanceof MilitaryBackpackItem backpack)) return;
        // 1.21：NetworkHooks.openScreen 没了，直接用 ServerPlayer#openMenu。
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return stack.getHoverName();
            }

            @Nullable
            @Override
            public net.minecraft.world.inventory.AbstractContainerMenu createMenu(
                    int id, Inventory inventory, Player p) {
                HolderLookup.Provider registries = player.registryAccess();
                ItemStackHandler handler = createHandler(stack, backpack.getSlotCount(), registries);
                // Normalize legacy NBT to the current fixed format
                // as soon as the menu opens, before any client interaction.
                saveHandler(stack, handler, registries);
                return new BackpackMenu(id, inventory, handler, stack,
                        backpack.getColumns(), backpack.getRows());
            }
        }, buf -> {
            buf.writeVarInt(backpack.getColumns());
            buf.writeVarInt(backpack.getRows());
        });
    }

    // ═══════════════════════════════════════════════════════════════
    //  物品数据（走 minecraft:custom_data，兼容 1.20.1 存档）
    // ═══════════════════════════════════════════════════════════════

    /** 读出背包自己的那棵 NBT；没有就返回空 CompoundTag。 */
    private static CompoundTag getTag(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        return data == null ? new CompoundTag() : data.copyTag();
    }

    private static void setTag(ItemStack stack, CompoundTag tag) {
        if (tag.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }
    }

    public static ItemStackHandler createHandler(ItemStack stack, int slotCount,
                                                 HolderLookup.Provider registries) {
        var handler = new ItemStackHandler(slotCount);
        CompoundTag tag = getTag(stack);
        if (tag.contains("Inventory", Tag.TAG_COMPOUND)) {
            // Never call ItemStackHandler.deserializeNBT here: older backpacks
            // stored a 45-slot Size tag, and that method resizes the handler.
            CompoundTag inventory = tag.getCompound("Inventory");
            if (inventory.contains("Items", Tag.TAG_LIST)) {
                ListTag items = inventory.getList("Items", Tag.TAG_COMPOUND);
                for (int i = 0; i < items.size(); i++) {
                    CompoundTag entry = items.getCompound(i);
                    int slot = entry.getInt("Slot");
                    if (slot >= 0 && slot < slotCount) {
                        // 1.21：物品栈的反序列化要注册表访问（数据组件要查注册表）。
                        handler.setStackInSlot(slot,
                                ItemStack.parse(registries, entry).orElse(ItemStack.EMPTY));
                    }
                }
            }
        }
        return handler;
    }

    public static void saveHandler(ItemStack stack, ItemStackHandler handler,
                                   HolderLookup.Provider registries) {
        CompoundTag tag = getTag(stack);
        tag.put("Inventory", handler.serializeNBT(registries));
        setTag(stack, tag);
    }

    public static void ensureId(ItemStack stack) {
        if (stack.isEmpty()) return;
        CompoundTag tag = getTag(stack);
        if (!tag.hasUUID(BACKPACK_ID)) {
            tag.putUUID(BACKPACK_ID, UUID.randomUUID());
            setTag(stack, tag);
        }
    }

    public static ItemStack resolveCarried(ServerPlayer player, ItemStack target) {
        ensureId(target);
        if (sameId(player.getMainHandItem(), target)) return player.getMainHandItem();
        if (sameId(player.getOffhandItem(), target)) return player.getOffhandItem();
        // Curios 9.x：Optional 直达，不再有 LazyOptional.resolve()。
        return CuriosApi.getCuriosInventory(player)
                .flatMap(inv -> inv.findFirstCurio(stack -> sameId(stack, target)))
                .map(SlotResult::stack)
                .orElse(ItemStack.EMPTY);
    }

    private static boolean sameId(ItemStack a, ItemStack b) {
        if (a.isEmpty() || b.isEmpty()) return false;
        CompoundTag ta = getTag(a);
        CompoundTag tb = getTag(b);
        return ta.hasUUID(BACKPACK_ID) && tb.hasUUID(BACKPACK_ID)
                && ta.getUUID(BACKPACK_ID).equals(tb.getUUID(BACKPACK_ID));
    }

    /** The server must close the menu once the backing stack leaves its slot. */
    public static boolean isCarriedBy(ServerPlayer player, ItemStack target) {
        return !resolveCarried(player, target).isEmpty();
    }

    // ═══════════════════════════════════════════════════════════════
    //  Curios 集成（9.x 走接口，不再走 capability）
    // ═══════════════════════════════════════════════════════════════

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return "back".equals(slotContext.identifier());
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
        tooltipComponents.add(Component.translatable("tooltip.caf.backpack.slots", getSlotCount()));
        tooltipComponents.add(Component.translatable("tooltip.caf.backpack.curios"));
    }
}
