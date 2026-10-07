package net.gem19910816.dyairdrop.procedures;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.core.Nbt;
import net.gem19910816.dyairdrop.core.Sounds;
import net.gem19910816.dyairdrop.core.Vars;
import net.gem19910816.dyairdrop.init.DyairdropModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

/**
 * 字母密码面板 RE2 的「确认（√）」逻辑：快照输入 → 逐位打字机揭晓 → 解锁。
 *
 * <p>与 {@link CheckProcedure} 的差别（保持原样）：
 * <ul>
 *   <li>RE2 在开始时把输入快照到 {@code keyre}，逐位揭晓时读的是这份快照（RE 读的是方块实体里的密码）；</li>
 *   <li>解锁成功只置方块实体 {@code isopen=true}（不再替换方块、不做 animation 动画）；</li>
 *   <li>完成动画是独立的一次 49 tick 调度，而不是嵌在第 6 位揭晓回调里。</li>
 * </ul>
 * 原实现把「揭晓第 N 位」复制了 6 份（每份约 35 行），这里收敛成循环 + {@link #revealLetter}。
 *
 * <p>逐字保留：哨兵 {@code "Z"}、完成标记 {@code "Y"}、延迟 7/14/21/28/35/42/49、CHECK 与 PWCORRECT 音量 1.0、
 * 消息「解锁成功！」、{@code keyre} 快照语义。
 */
public class ChecknewliteProcedure {

    private static final int PASSWORD_LENGTH = 6;
    private static final String SUBMIT_SENTINEL = "Z";
    private static final String SUCCESS_SUFFIX = "Y";
    private static final int LETTER_STEP_TICKS = 7;
    private static final int COMPLETE_DELAY_TICKS = LETTER_STEP_TICKS * PASSWORD_LENGTH;
    private static final float SOUND_VOLUME = 1.0F;

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        String input = Vars.of(entity).passwordre;

        if (input.chars().anyMatch(Character::isUpperCase)) {
            if (!input.contains(SUBMIT_SENTINEL)) {
                setInput(entity, "");
            }
            return;
        }
        if (input.length() != PASSWORD_LENGTH) {
            setInput(entity, "");
            return;
        }

        Vars.of(entity).keyre = input;
        setInput(entity, SUBMIT_SENTINEL);

        for (int index = 0; index < PASSWORD_LENGTH; index++) {
            int letterIndex = index;
            DyairdropMod.queueServerWork(LETTER_STEP_TICKS * (letterIndex + 1), () -> revealLetter(world, x, y, z, entity, letterIndex));
        }
        DyairdropMod.queueServerWork(COMPLETE_DELAY_TICKS, () -> completeUnlock(world, x, y, z, entity));
    }

    /** 揭晓第 {@code index} 位：从 {@code keyre} 快照里取字母接到输入后面，并播 CHECK 音。 */
    private static void revealLetter(LevelAccessor world, double x, double y, double z, Entity entity, int index) {
        setInput(entity, Vars.of(entity).passwordre + Vars.of(entity).keyre.substring(index, index + 1));
        Sounds.play(world, x, y, z, DyairdropModSounds.CHECK.get(), SOUND_VOLUME);
    }

    /** 解锁成功：播成功音、加 "Y" 标记、提示玩家，并把方块实体标记为已解锁。 */
    private static void completeUnlock(LevelAccessor world, double x, double y, double z, Entity entity) {
        Sounds.play(world, x, y, z, DyairdropModSounds.PWCORRECT.get(), SOUND_VOLUME);
        setInput(entity, Vars.of(entity).passwordre + SUCCESS_SUFFIX);
        if (entity instanceof Player player && !player.level().isClientSide()) {
            player.displayClientMessage(Component.literal("解锁成功！"), false);
        }
        if (!world.isClientSide()) {
            Nbt.setBoolean(world, BlockPos.containing(x, y, z), "isopen", true);
        }
    }

    private static void setInput(Entity entity, String value) {
        Vars.of(entity).passwordre = value;
        Vars.sync(entity);
    }
}
