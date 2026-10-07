package net.gem19910816.dyairdrop.panel;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.core.Blocks;
import net.gem19910816.dyairdrop.core.Commands;
import net.gem19910816.dyairdrop.core.Nbt;
import net.gem19910816.dyairdrop.core.Sounds;
import net.gem19910816.dyairdrop.core.Vars;
import net.gem19910816.dyairdrop.init.DyairdropModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 字母面板的「确认（√）」逻辑：逐位打字机揭晓 → 解锁成功。
 *
 * <p>合并原 {@code CheckProcedure}（RE 面板，375 行 → 收敛后 128 行）与
 * {@code ChecknewliteProcedure}（RE2 面板，339 行 → 82 行）—— 两者的差别只有三点，
 * 这里用两个公开方法表达，其余共享：
 * <ol>
 *   <li>RE 逐位读方块实体里的密码 {@code pw}（每位的长度门槛不同），RE2 读玩家侧的 {@code keyre} 快照；</li>
 *   <li>RE 的成功收尾是「播成功音 + 加 Y + 提示 + animation=1，再 20 tick 用 setblock 替换成开启形态并 animation=2」，
 *       RE2 只把方块实体标记为 {@code isopen}；</li>
 *   <li>RE 的完成动作嵌在第 6 位揭晓回调里（第 6 位没揭晓就不完成），RE2 是独立的一次 49 tick 调度。</li>
 * </ol>
 *
 * <p>逐字保留：哨兵 {@code "Z"}、完成标记 {@code "Y"}、延迟 7/14/21/28/35/42（RE 再加 7、再 20）、
 * CHECK 与 PWCORRECT 音量 1.0、消息「解锁成功！」、方块实体 {@code valid="shutdown"}、
 * 以及成功时执行的 {@code setblock ~ ~ ~ <block>open[facing=..]{LootTable:".."} replace}。
 */
public final class LetterPanelConfirm {

    private static final int PASSWORD_LENGTH = 6;
    /** 动画哨兵：出现它表示「正在逐位揭晓」，此时不因大写字母清空输入。 */
    private static final String SUBMIT_SENTINEL = "Z";
    /** 解锁成功标记（{@link LetterPanel#tickAutoClose} 据此自动关窗）。 */
    private static final String SUCCESS_SUFFIX = "Y";
    private static final int LETTER_STEP_TICKS = 7;
    /** RE：第 6 位揭晓后再等 7 tick 才做成功收尾。 */
    private static final int COMPLETE_EXTRA_TICKS = 7;
    /** RE：成功收尾后 20 tick 把方块替换成开启形态。 */
    private static final int OPEN_BLOCK_DELAY_TICKS = 20;
    private static final float SOUND_VOLUME = 1.0F;
    private static final int ANIMATION_UNLOCK = 1;
    private static final int ANIMATION_OPEN = 2;
    private static final String LOCKED_STATE = "shutdown";

    private LetterPanelConfirm() {
    }

    // ------------------------------------------------------------------ RE 面板

    /** RE 面板确认：逐位读方块实体密码，全部揭晓后替换成开启形态。 */
    public static void confirmRe(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!prepare(world, x, y, z, entity)) {
            return;
        }
        if (!world.isClientSide()) {
            Nbt.setString(world, BlockPos.containing(x, y, z), "valid", LOCKED_STATE);
        }
        for (int index = 0; index < PASSWORD_LENGTH; index++) {
            int letterIndex = index;
            DyairdropMod.queueServerWork(LETTER_STEP_TICKS * (letterIndex + 1), () -> {
                boolean revealed = revealFromBlockPassword(world, x, y, z, entity, letterIndex);
                if (revealed && letterIndex == PASSWORD_LENGTH - 1) {
                    DyairdropMod.queueServerWork(COMPLETE_EXTRA_TICKS, () -> completeRe(world, x, y, z, entity));
                }
            });
        }
    }

    /**
     * 揭晓第 {@code index} 位（读方块实体里的 {@code pw}）。
     *
     * <p>门槛与原实现一致：前 5 位要求密码长度 ≥ index+2，第 6 位要求 ≥ 6。
     *
     * @return 是否真的揭晓（长度不够时 false，后续完成动画也就不会触发）
     */
    private static boolean revealFromBlockPassword(LevelAccessor world, double x, double y, double z, Entity entity, int index) {
        String password = Nbt.getString(world, BlockPos.containing(x, y, z), "pw");
        if (password.length() < Math.min(index + 2, PASSWORD_LENGTH)) {
            return false;
        }
        setInput(entity, Vars.of(entity).passwordre + password.substring(index, index + 1));
        Sounds.play(world, x, y, z, DyairdropModSounds.CHECK.get(), SOUND_VOLUME);
        return true;
    }

    /** RE 成功收尾：成功音、加 Y、提示、animation=1，再 20 tick 替换方块并把 animation 置 2。 */
    private static void completeRe(LevelAccessor world, double x, double y, double z, Entity entity) {
        Sounds.play(world, x, y, z, DyairdropModSounds.PWCORRECT.get(), SOUND_VOLUME);
        setInput(entity, Vars.of(entity).passwordre + SUCCESS_SUFFIX);
        if (entity instanceof Player player && !player.level().isClientSide()) {
            player.displayClientMessage(Component.literal("解锁成功！"), false);
        }
        Blocks.setAnimation(world, BlockPos.containing(x, y, z), ANIMATION_UNLOCK);

        DyairdropMod.queueServerWork(OPEN_BLOCK_DELAY_TICKS, () -> {
            if (world instanceof ServerLevel) {
                BlockPos pos = BlockPos.containing(x, y, z);
                BlockState state = world.getBlockState(pos);
                Commands.run(world, x, y, z, "setblock ~ ~ ~ " + Blocks.idOf(state)
                        + "open[facing=" + Blocks.facingOf(state)
                        + "]{LootTable:\"" + Nbt.getString(world, pos, "loot") + "\"} replace");
            }
            Blocks.setAnimation(world, BlockPos.containing(x, y, z), ANIMATION_OPEN);
        });
    }

    // ------------------------------------------------------------------ RE2 面板

    /** RE2 面板确认：快照到 {@code keyre} 后逐位揭晓，49 tick 后标记 {@code isopen}。 */
    public static void confirmRe2(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!prepare(world, x, y, z, entity)) {
            return;
        }
        Vars.of(entity).keyre = Vars.of(entity).passwordre;
        setInput(entity, SUBMIT_SENTINEL);

        for (int index = 0; index < PASSWORD_LENGTH; index++) {
            int letterIndex = index;
            DyairdropMod.queueServerWork(LETTER_STEP_TICKS * (letterIndex + 1),
                    () -> revealFromSnapshot(world, x, y, z, entity, letterIndex));
        }
        DyairdropMod.queueServerWork(LETTER_STEP_TICKS * PASSWORD_LENGTH,
                () -> completeRe2(world, x, y, z, entity));
    }

    /** 揭晓第 {@code index} 位（读 {@code keyre} 快照，无长度门槛）。 */
    private static void revealFromSnapshot(LevelAccessor world, double x, double y, double z, Entity entity, int index) {
        setInput(entity, Vars.of(entity).passwordre + Vars.of(entity).keyre.substring(index, index + 1));
        Sounds.play(world, x, y, z, DyairdropModSounds.CHECK.get(), SOUND_VOLUME);
    }

    /** RE2 成功收尾：成功音、加 Y、提示、标记 {@code isopen}。 */
    private static void completeRe2(LevelAccessor world, double x, double y, double z, Entity entity) {
        Sounds.play(world, x, y, z, DyairdropModSounds.PWCORRECT.get(), SOUND_VOLUME);
        setInput(entity, Vars.of(entity).passwordre + SUCCESS_SUFFIX);
        if (entity instanceof Player player && !player.level().isClientSide()) {
            player.displayClientMessage(Component.literal("解锁成功！"), false);
        }
        if (!world.isClientSide()) {
            Nbt.setBoolean(world, BlockPos.containing(x, y, z), "isopen", true);
        }
    }

    // ------------------------------------------------------------------ 共享

    /**
     * 共同前置：含大写字母（错位或动画中）时不处理；长度不为 6 时清空输入；否则置哨兵并返回 true。
     */
    private static boolean prepare(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return false;
        }
        String input = Vars.of(entity).passwordre;
        if (input.chars().anyMatch(Character::isUpperCase)) {
            if (!input.contains(SUBMIT_SENTINEL)) {
                setInput(entity, "");
            }
            return false;
        }
        if (input.length() != PASSWORD_LENGTH) {
            setInput(entity, "");
            return false;
        }
        setInput(entity, SUBMIT_SENTINEL);
        return true;
    }

    private static void setInput(Entity entity, String value) {
        Vars.of(entity).passwordre = value;
        Vars.sync(entity);
    }
}
