package net.gem19910816.dyairdrop.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

/**
 * 空投飞机（{@code dyairdrop:plane}）的每 tick 逻辑。
 *
 * <p>取代原 {@code PlaneticksProcedure}：行为逐条保持一致，但
 * <ul>
 *   <li>NBT 只读一次、只在最后写回一次（原实现每个判断都重新 {@code getPersistentData().getDouble(...)}，
 *       这是每 tick、每架飞机都要跑的热路径）；</li>
 *   <li>魔法字符串与魔法数字提为常量（{@code timer} / {@code starter} / {@code d} / {@code dpassed} /
 *       {@code flytime} / {@code hasdone} / {@code name} / {@code dymap} 等键名与 205/3.0/5.0 等数值不变，
 *       存档兼容）；</li>
 *   <li>「按高度判定落点 → 召唤空投箱/医疗箱/武器箱 → 销毁飞机」的分支收敛到 {@link #dropCrate}。</li>
 * </ul>
 */
public final class PlaneTicker {

    // ---- NBT 键（与旧实现逐字一致，不能改） ----
    private static final String TAG_TIMER = "timer";
    private static final String TAG_START_X = "starter";
    private static final String TAG_DISTANCE = "d";
    private static final String TAG_DISTANCE_PASSED = "dpassed";
    private static final String TAG_FLY_TIME = "flytime";
    private static final String TAG_DONE = "hasdone";
    private static final String TAG_NAME = "name";
    private static final String TAG_MAP = "dymap";

    // ---- 行为常量（原实现里的字面量） ----
    private static final double SPEED = 3.0;
    private static final double INIT_TICKS = 0.0;
    private static final double DISTANCE_TRACKING_FROM = 2.0;
    private static final double MOVEMENT_REFRESH_TICKS = 5.0;
    private static final double MAX_LIFETIME_TICKS = 205.0;
    private static final double ARRIVAL_TOLERANCE = 1.0;
    private static final double DEFAULT_DISTANCE = 108.0;
    private static final float CRASH_EXPLOSION_POWER = 4.0F;
    private static final String STOP_SOUND_COMMAND = "/stopsound @a[distance=..200] ambient dyairdrop:planesound";
    private static final String PLANE_DISPLAY_NAME = "运输机";
    /** 飞机名字里的三段格式：{@code <blockid>,<loot_table>,<distance>}。 */
    private static final String[] FALLBACK_TARGET = {"dyairdrop:airdroplarge", "dyairdrop:largeairdrop1"};

    private PlaneTicker() {
    }

    /** 每 tick 调用（由 {@code PlaneEntity} 驱动）。 */
    public static void tick(LevelAccessor world, double x, double y, double z, Entity plane) {
        if (plane == null) {
            return;
        }
        CompoundTag data = plane.getPersistentData();
        double timer = data.getDouble(TAG_TIMER);

        if (timer <= INIT_TICKS) {
            startFlight(plane, data);
        }
        if (timer >= DISTANCE_TRACKING_FROM) {
            data.putDouble(TAG_DISTANCE_PASSED, Math.abs(data.getDouble(TAG_START_X) - plane.getX()));
        }

        double flyTime = data.getDouble(TAG_FLY_TIME);
        if (flyTime > 0.0) {
            if (timer >= flyTime * 2.0 && !plane.level().isClientSide()) {
                plane.discard();
            }
        } else if (timer >= MAX_LIFETIME_TICKS && !plane.level().isClientSide()) {
            plane.discard();
        }

        if (timer % MOVEMENT_REFRESH_TICKS == 0.0) {
            plane.setDeltaMovement(new Vec3(SPEED, 0.0, 0.0));
        }

        boolean arrived = Math.round(Math.abs(data.getDouble(TAG_DISTANCE_PASSED) - data.getDouble(TAG_DISTANCE))) <= (long) ARRIVAL_TOLERANCE;
        if (arrived && !data.getBoolean(TAG_DONE)) {
            data.putBoolean(TAG_DONE, true);
            data.putDouble(TAG_FLY_TIME, timer);
            dropCrate(world, x, y, z, data);
        }

        data.putDouble(TAG_TIMER, timer + 1.0);

        if (plane.getDeltaMovement().x() == 0.0 || plane.isInWall()) {
            crash(world, x, y, z, plane);
        }
    }

    /** 起飞：记录起点、解除重力、按名字里的第三段设定飞行距离。 */
    private static void startFlight(Entity plane, CompoundTag data) {
        data.putDouble(TAG_START_X, plane.getX());
        plane.setNoGravity(true);
        data.putDouble(TAG_TIMER, 0.0);
        data.putString(TAG_NAME, plane.getDisplayName().getString());

        String[] parts = data.getString(TAG_NAME).split(",", 3);
        String distance = parts.length > 2 ? parts[2] : Double.toString(DEFAULT_DISTANCE);
        data.putDouble(TAG_DISTANCE, Numbers.parseDouble(distance));

        plane.setCustomName(Component.literal(PLANE_DISPLAY_NAME));
        plane.setDeltaMovement(new Vec3(SPEED, 0.0, 0.0));
    }

    /** 到达落点：按名字里的 blockid 召唤对应类型的空投箱（携带 dymap 时用于地图打点）。 */
    private static void dropCrate(LevelAccessor world, double x, double y, double z, CompoundTag data) {
        String name = data.getString(TAG_NAME);
        String[] parts = name.split(",", 3);
        String blockId = parts.length > 2 ? parts[0] : FALLBACK_TARGET[0];
        String lootTable = parts.length > 2 ? parts[1] : FALLBACK_TARGET[1];

        String entityId = airdropEntityFor(blockId.replace("locked", ""));
        String extraNbt = data.getBoolean(TAG_MAP) ? ",ForgeData:{dymap:1b}" : "";
        Commands.run(world, Math.round(x), Math.round(y), Math.round(z),
                "summon " + entityId + " ~ ~ ~ {CustomName:'{\"text\":\"" + name + "\"}'" + extraNbt + "}");
    }

    /** 方块 id → 空投实体 id（与原实现的 if/else 链一致）。 */
    private static String airdropEntityFor(String blockId) {
        return switch (blockId) {
            case "dyairdrop:airdropsmall" -> "dyairdrop:smallairdrop";
            case "dyairdrop:airdropweapon" -> "dyairdrop:weaponairdrop";
            case "dyairdrop:airdropmedical" -> "dyairdrop:medicalairdrop";
            default -> "dyairdrop:airdrop";
        };
    }

    /** 撞墙 / 失去速度：爆炸、停音效、移除飞机。 */
    private static void crash(LevelAccessor world, double x, double y, double z, Entity plane) {
        if (!plane.level().isClientSide()) {
            plane.discard();
        }
        if (world instanceof Level level && !level.isClientSide()) {
            level.explode(null, x, y, z, CRASH_EXPLOSION_POWER, Level.ExplosionInteraction.MOB);
        }
        if (world instanceof ServerLevel) {
            Commands.run(world, Math.round(x), Math.round(y), Math.round(z), STOP_SOUND_COMMAND);
        }
    }
}
