package net.gem19910816.dyairdrop.procedures;

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
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * 字母密码面板 RE 的「确认（√）」逻辑：逐位揭晓 + 打字机动画 + 成功后把箱子替换成开启形态。
 *
 * <p>重构点（行为逐条保留，见 {@code 重构说明.md}）：
 * <ul>
 *   <li>原实现把「揭晓第 N 位」的同一段代码**复制了 6 份**（每份约 35 行，只差下标与延迟），
 *       现在收敛成 {@link #revealLetter} 加一个循环；</li>
 *   <li>输入缓存与方块实体的读写统一走 {@link Vars} / {@link Nbt}，音效统一走 {@link Sounds}，
 *       命令统一走 {@link Commands}；</li>
 *   <li>数值与文本逐字保留：提交哨兵 {@code "Z"}、完成标记 {@code "Y"}、延迟 7/14/21/28/35/42、
 *       完成后 7 tick 播 PWCORRECT 并把 {@code animation} 置 1、再 20 tick 执行
 *       {@code setblock ~ ~ ~ <block>open[facing=..]{LootTable:".."} replace} 并把 {@code animation} 置 2、
 *       音效 CHECK 音量 1.0、消息「解锁成功！」、方块实体 {@code valid="shutdown"}。</li>
 * </ul>
 */
public class CheckProcedure {

    /** 密码位数（与原实现一致）。 */
    private static final int PASSWORD_LENGTH = 6;
    /** 动画中哨兵：出现它表示「正在逐位揭晓」，此时不因大写字母清空输入。 */
    private static final String SUBMIT_SENTINEL = "Z";
    /** 解锁成功标记（{@code PannelREticksProcedure} 据此自动关窗）。 */
    private static final String SUCCESS_SUFFIX = "Y";
    private static final int LETTER_STEP_TICKS = 7;
    private static final int COMPLETE_DELAY_TICKS = 7;
    private static final int OPEN_BLOCK_DELAY_TICKS = 20;
    private static final float CHECK_SOUND_VOLUME = 1.0F;
    private static final int ANIMATION_UNLOCK = 1;
    private static final int ANIMATION_OPEN = 2;

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        String input = Vars.of(entity).passwordre;

        if (input.chars().anyMatch(Character::isUpperCase)) {
            // 输错的字母是大写；动画中的哨兵 "Z" 也是大写，所以只有非动画状态才清空
            if (!input.contains(SUBMIT_SENTINEL)) {
                setInput(entity, "");
            }
            return;
        }
        if (input.length() != PASSWORD_LENGTH) {
            setInput(entity, "");
            return;
        }

        setInput(entity, SUBMIT_SENTINEL);
        if (!world.isClientSide()) {
            Nbt.setString(world, BlockPos.containing(x, y, z), "valid", "shutdown");
        }

        for (int index = 0; index < PASSWORD_LENGTH; index++) {
            int letterIndex = index;
            DyairdropMod.queueServerWork(LETTER_STEP_TICKS * (letterIndex + 1), () -> {
                if (revealLetter(world, x, y, z, entity, letterIndex) && letterIndex == PASSWORD_LENGTH - 1) {
                    DyairdropMod.queueServerWork(COMPLETE_DELAY_TICKS, () -> completeUnlock(world, x, y, z, entity));
                }
            });
        }
    }

    /**
     * 揭晓第 {@code index} 位：把密码串里对应的字母接到输入后面并播 CHECK 音。
     *
     * <p>门槛与原实现一致：第 1~5 位要求密码长度 ≥ index+2，第 6 位（index=5）要求 ≥ 6。
     *
     * @return 是否真的揭晓了（密码长度不够时返回 false，后续完成动画也就不会触发）
     */
    private static boolean revealLetter(LevelAccessor world, double x, double y, double z, Entity entity, int index) {
        String password = Nbt.getString(world, BlockPos.containing(x, y, z), "pw");
        int requiredLength = Math.min(index + 2, PASSWORD_LENGTH);
        if (password.length() < requiredLength) {
            return false;
        }
        String letter = password.substring(index, index + 1);
        setInput(entity, Vars.of(entity).passwordre + letter);
        Sounds.play(world, x, y, z, DyairdropModSounds.CHECK.get(), CHECK_SOUND_VOLUME);
        return true;
    }

    /** 解锁成功：播成功音、加 "Y" 标记、提示、置 animation=1，再延迟把方块替换成开启形态。 */
    private static void completeUnlock(LevelAccessor world, double x, double y, double z, Entity entity) {
        Sounds.play(world, x, y, z, DyairdropModSounds.PWCORRECT.get(), CHECK_SOUND_VOLUME);
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

    private static void setAnimation(LevelAccessor world, double x, double y, double z, int value) {
        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = world.getBlockState(pos);
        if (state.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty animation
                && animation.getPossibleValues().contains(value)) {
            world.setBlock(pos, state.setValue(animation, value), 3);
        }
    }

    private static void setInput(Entity entity, String value) {
        Vars.of(entity).passwordre = value;
        Vars.sync(entity);
    }
}
