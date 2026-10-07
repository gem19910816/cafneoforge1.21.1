package net.gem19910816.dyairdrop.core;

import java.util.Locale;

import net.gem19910816.dyairdrop.compat.map.MapMarkerService;
import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

/**
 * 空投木箱（下落中的实体）的每 tick 逻辑：落地后变成真正的箱子，可选打地图标记，然后销毁自己。
 *
 * <p>取代原 {@code MobairdropticksProcedure}（113 行）。行为逐条保留：
 * <ul>
 *   <li>{@code timer == 1} 时记下自己的名字（名字格式 {@code blockid,loot,level}）、改名「空投」、加缓降效果；</li>
 *   <li>{@code timer >= 1} 且落地/入水时放置箱子：落地高度按「原位置是否为空 / 是否开启未完成方块破坏 /
 *       是否在水中」决定（与原实现同序判断）；</li>
 *   <li>名字里带 {@code locked} 的用 {@code setBlock} 放方块并把战利品表写进方块实体，
 *       其余用 {@code setblock … {LootTable:…} destroy} 命令放置；</li>
 *   <li>实体带 {@code dymap} 时，按箱子类型登记地图标记（{@link MapMarkerService}），
 *       显示名取对应物品名的做法与原实现一致；</li>
 *   <li>最后销毁实体，并把 {@code timer} +1。</li>
 * </ul>
 */
public final class CrateTicker {

    private static final String TAG_TIMER = "timer";
    private static final String TAG_NAME = "cuname";
    private static final String TAG_MAP = "dymap";
    private static final String CRATE_DISPLAY_NAME = "空投";
    private static final int SLOW_FALLING_DURATION = 100000;
    private static final int SLOW_FALLING_AMPLIFIER = 1;
    private static final String LOCKED_MARKER = "locked";
    private static final String AIRDROP_PREFIX = "dyairdrop:";
    private static final String FALLBACK_BLOCK = "dyairdrop:airdroplarge";
    private static final String FALLBACK_LOOT = "dyairdrop:largeairdrop1";

    private CrateTicker() {
    }

    /** 每 tick 调用（由 4 种空投实体驱动）。 */
    public static void tick(LevelAccessor world, double x, double y, double z, Entity crate) {
        if (crate == null) {
            return;
        }
        CompoundTag data = crate.getPersistentData();
        double timer = data.getDouble(TAG_TIMER);

        if (timer == 1.0) {
            beginFall(crate, data);
        } else if (timer >= 1.0 && (crate.onGround() || crate.isInWater())) {
            land(world, x, y, z, crate, data);
        }
        data.putDouble(TAG_TIMER, timer + 1.0);
    }

    /** 起始：记录自己的名字、改名、加缓降效果。 */
    private static void beginFall(Entity crate, CompoundTag data) {
        data.putString(TAG_NAME, crate.getDisplayName().getString());
        crate.setCustomName(Component.literal(CRATE_DISPLAY_NAME));
        if (crate instanceof LivingEntity living && !living.level().isClientSide()) {
            living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, SLOW_FALLING_DURATION, SLOW_FALLING_AMPLIFIER, false, false));
        }
    }

    /** 落地：放箱子 → 打地图标记 → 销毁实体。 */
    private static void land(LevelAccessor world, double x, double y, double z, Entity crate, CompoundTag data) {
        String[] parts = data.getString(TAG_NAME).split(",", 3);
        boolean named = parts.length > 2;
        String blockId = named ? parts[0] : FALLBACK_BLOCK;
        String lootTable = named ? parts[1] : FALLBACK_LOOT;

        double landingY = landingHeight(world, x, y, z, crate);
        BlockPos pos = BlockPos.containing(x, landingY, z);

        if (blockId.contains(LOCKED_MARKER)) {
            placeLockedChest(world, pos, blockId, lootTable);
        } else {
            Commands.run(world, x, landingY, z, "setblock ~ ~ ~ " + blockId + "{LootTable:\"" + lootTable + "\"} destroy");
        }

        if (data.getBoolean(TAG_MAP)) {
            addMapMarker(world, pos, blockId);
        }
        if (!crate.level().isClientSide()) {
            crate.discard();
        }
    }

    /** 落地高度：原实现的三段判断（空位 / 允许破坏未完成方块 / 水中）。 */
    private static double landingHeight(LevelAccessor world, double x, double y, double z, Entity crate) {
        if (crate.onGround()) {
            if (world.isEmptyBlock(BlockPos.containing(x, y, z))) {
                return y;
            }
            return Boolean.TRUE.equals(AirdropconfigConfiguration.INCOMPLETE_BLOCK_DESTRUCTION.get()) ? y : y + 1.0;
        }
        if (crate.isInWater()) {
            return y + 1.0;
        }
        return y;
    }

    /** 带锁的箱子：放上方块，并把战利品表写进方块实体（供开锁后使用）。 */
    private static void placeLockedChest(LevelAccessor world, BlockPos pos, String blockId, String lootTable) {
        Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockId.toLowerCase(Locale.ENGLISH)));
        world.setBlock(pos, block.defaultBlockState(), 3);
        if (world instanceof Level level) {
            level.updateNeighborsAt(pos, level.getBlockState(pos).getBlock());
        }
        Nbt.setString(world, pos, "loot", lootTable);
    }

    /** 按箱子类型登记地图标记，显示名沿用「对应物品的显示名」这一原行为。 */
    private static void addMapMarker(LevelAccessor world, BlockPos pos, String blockId) {
        String itemId = blockId.replace(LOCKED_MARKER, "").toLowerCase(Locale.ENGLISH);
        if (!itemId.contains(AIRDROP_PREFIX)) {
            itemId = AIRDROP_PREFIX + itemId;
        }
        String displayName = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId))).getDisplayName().getString();
        if (world instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            MapMarkerService.addMarker(serverLevel, pos, displayName, MapMarkerService.colorForBlockId(blockId));
        }
    }
}
