package net.gem19910816.dyairdrop.core;

import net.gem19910816.dyairdrop.compat.zombiekit.ZombieKitCompat;

import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.gem19910816.dyairdrop.init.DyairdropModItems;
import net.gem19910816.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

/**
 * 信号枪的全部逻辑：发射（消耗 + 冷却 + 按权重抽空投类型与战利品表）、信号弹每 tick、信号弹爆开。
 *
 * <p>取代原 {@code FlaregunlootsetProcedure}（131 行）、{@code FlareticksProcedure}（162 行）、
 * {@code FlareburstProcedure}（59 行）—— 三者共用同一张「等级 → 烟花 NBT」表，
 * 原来这张表被写了两遍（各 6 个分支）。
 *
 * <p>行为逐条保留：
 * <ul>
 *   <li>发射时播 {@code FIREWORK_ROCKET_LAUNCH}（NEUTRAL 音源、音量 1.0），服务端生成信号弹并按视线方向射出（速度 3.0）；</li>
 *   <li>权重换算、{@code random < s} → 小型 / {@code < w+s} → 武器 / 否则医疗 的抽取顺序、以及
 *       战利品表 id 的拼装（{@code <mod>:chests/<类型>airdrop<枪号>}）全部照旧；</li>
 *   <li>发射消耗 1 个物品并把 6 把信号枪都置于 300 tick 冷却（原实现逐个 addCooldown）；</li>
 *   <li>信号弹第 35 tick 爆开：先按等级放对应烟花，再判断「成功召唤」或「失败提示」，然后销毁信号弹。</li>
 * </ul>
 *
 * <p><b>保留的历史写法（未改，属玩法概率）</b>：权重归一化时原实现复用被改写过的 {@code w} 作为分母
 * （{@code w = 10w/(w+m+s); m = 10m/(w+m+s); s = 10s/(w+m+s)}），这里保持原样，
 * 以免改变玩家的实际抽中概率。
 */
public final class FlareService {

    private static final String TAG_COUNTER = "counter1";
    private static final double POP_TICK = 35.0;
    private static final int GUN_COOLDOWN_TICKS = 300;
    private static final float FLARE_DAMAGE = 5.0F;
    private static final float FLARE_SPEED = 3.0F;
    private static final double SHOOT_EYE_OFFSET = 0.1;
    private static final double WEIGHT_TOTAL = 10.0;
    private static final String AIRDROP_BLOCK_PREFIX = "dyairdrop:airdrop";
    private static final String FLAREGUN_ID_PREFIX = "dyairdrop:flaregun";
    private static final String SMALL_BLOCK = "dyairdrop:airdropsmall";
    private static final String WEAPON_BLOCK = "dyairdrop:airdropweapon";
    private static final String MEDICAL_BLOCK = "dyairdrop:airdropmedical";
    private static final String FIREWORK_SUMMON = "/summon minecraft:firework_rocket ";

    /** 按战利品表尾号的烟花 NBT（与原实现的两份表逐字一致）。 */
    private static final String[] LEVEL_FIREWORKS = {
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;64375],FadeColors:[I;3798784]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;3668224],FadeColors:[I;64667]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}",
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;58619],FadeColors:[I;13303]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;12793],FadeColors:[I;55548]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}",
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;11796731],FadeColors:[I;16187633]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16318671],FadeColors:[I;10551548]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}",
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;14786560],FadeColors:[I;16758272]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16361728],FadeColors:[I;14519814]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}",
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:0,Colors:[I;16333056],FadeColors:[I;13503243]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;13501444],FadeColors:[I;16202496]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}"};
    /** 等级识别不出来时的烟花（位置用 {@code ~ ~ ~}，与原实现一致）。 */
    private static final String DEFAULT_FIREWORK =
            "{FireworksItem:{tag:{Fireworks:{Flight:2,Explosions:[{Trail:1b,Flicker:1b,Type:1,Colors:[I;15952396],FadeColors:[I;16582625]},{Trail:1b,Flicker:1b,Type:1,Colors:[I;16059086],FadeColors:[I;16221952]}]}},id:\"minecraft:firework_rocket\",Count:1},LifeTime:1}";

    private FlareService() {
    }

    // ------------------------------------------------------------------ 发射

    /** 手持信号枪右键：抽类型与战利品表、射出信号弹、消耗物品、进冷却。 */
    public static void useFlareGun(LevelAccessor world, double x, double y, double z, Entity shooter, ItemStack gun) {
        if (shooter == null || gun == null) {
            return;
        }
        Sounds.play(world, x, y, z, SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 1.0F);

        if (!shooter.level().isClientSide()) {
            Projectile flare = Projectiles.flare(shooter.level(), shooter, FLARE_DAMAGE);
            flare.setPos(shooter.getX(), shooter.getEyeY() - SHOOT_EYE_OFFSET, shooter.getZ());
            flare.shoot(shooter.getLookAngle().x, shooter.getLookAngle().y, shooter.getLookAngle().z, FLARE_SPEED, 0.0F);
            shooter.level().addFreshEntity(flare);
        }

        DyairdropModVariables.PlayerVariables vars = Vars.of(shooter);
        vars.airdropblock = rollAirdropType();
        vars.airdroploot = lootTableFor(vars.airdropblock, gun);
        Vars.sync(shooter);

        gun.shrink(1);
        if (shooter instanceof Player player) {
            for (Item flaregun : new Item[]{DyairdropModItems.FLAREGUN1.get(), DyairdropModItems.FLAREGUN2.get(),
                    DyairdropModItems.FLAREGUN3.get(), DyairdropModItems.FLAREGUN4.get(), DyairdropModItems.FLAREGUN5.get()}) {
                player.getCooldowns().addCooldown(flaregun, GUN_COOLDOWN_TICKS);
            }
        }
    }

    /** 按配置权重的顺序抽空投类型（与原实现的比较顺序、分母写法一致）。 */
    private static String rollAirdropType() {
        double weapon = AirdropconfigConfiguration.WEAPONAIRDROPWEIGHT.get();
        double medical = AirdropconfigConfiguration.MEDICALAIRDROPWEIGHT.get();
        double small = AirdropconfigConfiguration.SMALLAIRDROPWEIGHT.get();
        double total = weapon + medical + small;
        weapon = WEIGHT_TOTAL * weapon / total;
        medical = WEIGHT_TOTAL * medical / (weapon + medical + small);
        small = WEIGHT_TOTAL * small / (weapon + medical + small);

        double random = Mth.nextDouble(RandomSource.create(), 0.0, WEIGHT_TOTAL);
        if (random < small) {
            return SMALL_BLOCK;
        }
        if (random < weapon + small) {
            return WEAPON_BLOCK;
        }
        return MEDICAL_BLOCK;
    }

    /** 战利品表 id：{@code <mod>:chests/<类型>airdrop<枪号>}。 */
    private static String lootTableFor(String airdropBlock, ItemStack gun) {
        String modName = ZombieKitCompat.lootNamespace();
        String gunIndex = BuiltInRegistries.ITEM.getKey(gun.getItem()).toString().replace(FLAREGUN_ID_PREFIX, "");
        return modName + ":chests/" + airdropBlock.replace(AIRDROP_BLOCK_PREFIX, "") + "airdrop" + gunIndex;
    }

    // ------------------------------------------------------------------ 信号弹

    /** 信号弹每 tick：35 tick 时爆开——先放烟花，再决定召唤空投还是提示失败。 */
    public static void tick(LevelAccessor world, double x, double y, double z, Entity shooter, Entity flare) {
        if (shooter == null || flare == null) {
            return;
        }
        CompoundTag data = flare.getPersistentData();
        double counter = data.getDouble(TAG_COUNTER) + 1.0;
        data.putDouble(TAG_COUNTER, counter);
        if (counter != POP_TICK) {
            return;
        }

        double startPosition = clampedStartPosition();
        double height = clampedHeight();

        if (world instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            serverLevel.sendParticles(net.minecraft.core.particles.ParticleTypes.FIREWORK, x, y, z, 4, 0.0, 0.0, 0.0, 0.1);
        }
        summonLevelFirework(world, x, y, z, Vars.of(shooter).airdroploot);

        boolean noLootConfigured = Vars.of(shooter).airdroploot.length() * Vars.of(shooter).airdropblock.length() <= 0;
        if (noLootConfigured && !GameModes.isCreative(shooter)) {
            Chat.tellKey(shooter, "message.airdropeventsfailure");
        } else if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
            boolean lock = Boolean.TRUE.equals(AirdropconfigConfiguration.ENABLELOCK.get());
            callAirdrop(world, flare, height, startPosition, shooter, lock);
            Chat.tellKey(shooter, "message.callingairdropsuccess");
            Vars.of(shooter).airdropblock = "";
            Vars.of(shooter).airdroploot = "";
            Vars.sync(shooter);
        } else {
            Chat.tellKey(shooter, "message.airdropeventsfailure");
        }

        if (!flare.level().isClientSide()) {
            flare.discard();
        }
    }

    /** 信号弹爆开（另一条调用路径）：只放烟花 + 销毁，不召唤空投。 */
    public static void burst(LevelAccessor world, double x, double y, double z, Entity shooter, Entity flare) {
        if (shooter == null || flare == null) {
            return;
        }
        summonLevelFirework(world, x, y, z, Vars.of(shooter).airdroploot);
        if (!flare.level().isClientSide()) {
            flare.discard();
        }
    }

    // ------------------------------------------------------------------ 工具

    /** 按信号弹所在位置调用 {@code /setairdrop free …}。 */
    private static void callAirdrop(LevelAccessor world, Entity flare, double height, double startPosition, Entity shooter, boolean lock) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("##");
        Commands.run(world, flare.getX(), flare.getY(), flare.getZ(), "/setairdrop free "
                + format.format(flare.getX()) + " "
                + format.format(flare.getZ()) + " "
                + format.format(height) + " "
                + format.format(startPosition) + " \""
                + Vars.of(shooter).airdropblock + "\" \""
                + Vars.of(shooter).airdroploot + "\" "
                + (lock ? "true true" : "false true"));
    }

    /** 按 {@code airdroploot} 尾号放对应烟花（尾号取不到时用默认烟花）。 */
    private static void summonLevelFirework(LevelAccessor world, double x, double y, double z, String lootTable) {
        String nbt = DEFAULT_FIREWORK;
        if (lootTable != null) {
            for (int level = 1; level <= LEVEL_FIREWORKS.length; level++) {
                if (lootTable.endsWith(Integer.toString(level))) {
                    nbt = LEVEL_FIREWORKS[level - 1];
                    break;
                }
            }
        }
        String offset = nbt == DEFAULT_FIREWORK ? "~ ~ ~ " : "~ ~1 ~ ";
        Commands.run(world, x, y, z, FIREWORK_SUMMON + offset + nbt);
    }

    private static double clampedStartPosition() {
        double configured = AirdropconfigConfiguration.STARTPOSITION.get();
        if (configured <= 0.0) {
            return 0.0;
        }
        return Math.min(configured, 512.0);
    }

    private static double clampedHeight() {
        double configured = AirdropconfigConfiguration.HEIGHT.get();
        if (configured <= 100.0) {
            return 100.0;
        }
        if (configured >= 320.0) {
            return 320.0;
        }
        return Math.round(configured);
    }
}
