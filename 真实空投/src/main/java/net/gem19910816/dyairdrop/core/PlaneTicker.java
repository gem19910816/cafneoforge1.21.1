package net.gem19910816.dyairdrop.core;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

/**
 * 空投飞机的每 tick 逻辑（普通机 {@code dyairdrop:plane} 与运输机 {@code dyairdrop:transportplane}）。
 *
 * <p>取代原 {@code PlaneticksProcedure} 与 {@code FancyplaneticksProcedure}（两者 90% 相同）：
 * <ul>
 *   <li>NBT 只读一次、只在最后写回一次（原实现每个判断都重新读，是每 tick、每架飞机都跑的热路径）；</li>
 *   <li>NBT 键名与数值常量逐字保留（{@code timer/starter/d/dpassed/flytime/hasdone/name/dymap}、
 *       205 / 3.0 / 5.0 / 108 / 爆炸 4.0F 等），存档与手感不变；</li>
 *   <li>运输机独有的两处差异（起飞时朝向前方看、每 tick 两股翼尖云迹）由 {@code transport} 分支表达，
 *       不再各自复制一份完整逻辑。</li>
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
    private static final double DISTANCE_TRACKING_FROM = 2.0;
    private static final double MOVEMENT_REFRESH_TICKS = 5.0;
    private static final double MAX_LIFETIME_TICKS = 205.0;
    private static final double ARRIVAL_TOLERANCE = 1.0;
    private static final double DEFAULT_DISTANCE = 108.0;
    private static final float CRASH_EXPLOSION_POWER = 4.0F;
    private static final String STOP_SOUND_COMMAND = "/stopsound @a[distance=..200] ambient dyairdrop:planesound";
    private static final String PLANE_DISPLAY_NAME = "运输机";
    /** 运输机翼尖云迹：偏航角与云迹长度（数值与原实现完全一致）。 */
    private static final double WING_ANGLE = Math.atan(0.33333333) + 1.570796327;
    private static final double TRAIL_LENGTH = 1.4;
    private static final double TRAIL_OFFSET_SCALE = 0.5;
    private static final String TRAIL_PARTICLE_TEMPLATE = "particle cloud ";
    /** 名字里没有第三段时的兜底落点。 */
    private static final String FALLBACK_BLOCK = "dyairdrop:airdroplarge";
    private static final String FALLBACK_LOOT = "dyairdrop:largeairdrop1";

    private PlaneTicker() {
    }

    /** 普通飞机（{@code PlaneEntity}）。 */
    public static void tick(LevelAccessor world, double x, double y, double z, Entity plane) {
        tick(world, x, y, z, plane, false);
    }

    /** 运输机（{@code TransportplaneEntity}）：额外朝向前方看 + 两股翼尖云迹。 */
    public static void tickTransport(LevelAccessor world, double x, double y, double z, Entity plane) {
        tick(world, x, y, z, plane, true);
    }

    private static void tick(LevelAccessor world, double x, double y, double z, Entity plane, boolean transport) {
        if (plane == null) {
            return;
        }
        CompoundTag data = plane.getPersistentData();
        double timer = data.getDouble(TAG_TIMER);

        if (timer <= 0.0) {
            startFlight(plane, data, transport ? new Vec3(x + 1.0, y, z) : null);
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

        if (transport) {
            emitWingTrails(world, x, y, z, plane);
        }
    }

    /** 起飞：记录起点、解除重力、按名字第三段设定飞行距离；运输机额外朝向前方看。 */
    private static void startFlight(Entity plane, CompoundTag data, Vec3 lookTarget) {
        data.putDouble(TAG_START_X, plane.getX());
        plane.setNoGravity(true);
        data.putDouble(TAG_TIMER, 0.0);
        data.putString(TAG_NAME, plane.getDisplayName().getString());

        String[] parts = data.getString(TAG_NAME).split(",", 3);
        data.putDouble(TAG_DISTANCE, Numbers.parseDouble(parts.length > 2 ? parts[2] : Double.toString(DEFAULT_DISTANCE)));

        plane.setCustomName(Component.literal(PLANE_DISPLAY_NAME));
        if (lookTarget != null) {
            plane.lookAt(Anchor.EYES, lookTarget);
        }
        plane.setDeltaMovement(new Vec3(SPEED, 0.0, 0.0));
    }

    /** 到达落点：按名字里的第一段（blockid）召唤对应类型的空投箱。 */
    private static void dropCrate(LevelAccessor world, double x, double y, double z, CompoundTag data) {
        String name = data.getString(TAG_NAME);
        String[] parts = name.split(",", 3);
        String blockId = parts.length > 2 ? parts[0] : FALLBACK_BLOCK;
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

    /** 运输机的两股翼尖云迹（左右各一条，数值与原实现一致）。 */
    private static void emitWingTrails(LevelAccessor world, double x, double y, double z, Entity plane) {
        if (!(world instanceof ServerLevel)) {
            return;
        }
        Vec3 look = plane.getLookAngle();
        double cos = Math.cos(WING_ANGLE);
        double sin = Math.sin(WING_ANGLE);

        double rightX = look.x * cos - look.z * sin;
        double rightZ = look.x * sin + look.z * cos;
        double leftX = look.x * cos + look.z * sin;
        double leftZ = -look.x * sin + look.z * cos;

        double scale = (Math.pow(rightX, 2.0) + Math.pow(rightZ, 2.0)) * TRAIL_OFFSET_SCALE;
        rightX = rightX / scale * TRAIL_LENGTH;
        rightZ = rightZ / scale * TRAIL_LENGTH;
        leftX = leftX / scale * TRAIL_LENGTH;
        leftZ = leftZ / scale * TRAIL_LENGTH;

        Commands.run(world, x, y, z, TRAIL_PARTICLE_TEMPLATE + (x + rightX) + " " + (y + 1.0) + " " + (z + rightZ) + " 0.2 0.2 0.2 0 5 force");
        Commands.run(world, x, y, z, TRAIL_PARTICLE_TEMPLATE + (x + leftX) + " " + (y + 1.0) + " " + (z + leftZ) + " 0.2 0.2 0.2 0 5 force");
    }
}
