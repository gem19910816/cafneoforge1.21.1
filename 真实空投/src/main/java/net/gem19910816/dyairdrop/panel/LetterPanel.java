package net.gem19910816.dyairdrop.panel;

import net.gem19910816.dyairdrop.core.Nbt;
import net.gem19910816.dyairdrop.core.Vars;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

/**
 * 字母密码面板（RE / RE2）的输入与指示逻辑。
 *
 * <p>取代原实现里 6 个 {@code ButtonreNProcedure}（按键）、6 个 {@code LightNProcedure}（正确灯）、
 * 6 个 {@code WrongNProcedure}（错误灯）、{@code PannelREticksProcedure}（成功自动关窗）与
 * {@code PannelREshutProcedure}（关闭时清空）—— 共 21 个类、约 400 行，逻辑本身就是一张字母表。
 *
 * <p>语义逐字保留：
 * <ul>
 *   <li>按键时，若「当前输入 + 该字母」是密码的前缀（{@code pw.contains(input + letter)}）则追加**小写**字母，
 *       否则追加**大写**（大写即"错位"标记，也是后续清空输入的依据）；</li>
 *   <li>输入里已经出现大写字母时，按键不再生效（与旧实现的外层判断一致）；</li>
 *   <li>成功标记 {@code "Y"} 出现后由 {@link #tickAutoClose} 清空并自动关窗。</li>
 * </ul>
 */
public final class LetterPanel {

    /** 面板上的字母表。 */
    private static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f'};
    /** 解锁成功标记（{@code ChecknewliteProcedure} / {@code CheckProcedure} 写入）。 */
    private static final String SUCCESS_MARK = "Y";

    private LetterPanel() {
    }

    public static int letterCount() {
        return LETTERS.length;
    }

    /** 按下第 {@code index} 个字母键（0=a … 5=f）。 */
    public static void press(LevelAccessor world, BlockPos pos, Entity entity, int index) {
        if (entity == null || index < 0 || index >= LETTERS.length) {
            return;
        }
        String input = Vars.of(entity).passwordre;
        if (input.chars().anyMatch(Character::isUpperCase)) {
            return; // 已经错位（或动画中），忽略按键
        }
        char letter = LETTERS[index];
        boolean correct = Nbt.getString(world, pos, "pw").contains(input + letter);
        setInput(entity, input + (correct ? letter : Character.toUpperCase(letter)));
    }

    /** 第 {@code index} 位是否已正确（画绿灯用）。 */
    public static boolean isLit(Entity entity, int index) {
        return entity != null && index >= 0 && index < LETTERS.length
                && Vars.of(entity).passwordre.indexOf(LETTERS[index]) >= 0;
    }

    /** 第 {@code index} 位是否已错误（画红灯用）。 */
    public static boolean isWrong(Entity entity, int index) {
        return entity != null && index >= 0 && index < LETTERS.length
                && Vars.of(entity).passwordre.indexOf(Character.toUpperCase(LETTERS[index])) >= 0;
    }

    /** 解锁成功后自动关窗（原 {@code PannelREticksProcedure}，每 tick 由 {@link PanelService} 调用）。 */
    public static void tickAutoClose(Entity entity) {
        if (entity != null && Vars.of(entity).passwordre.contains(SUCCESS_MARK)) {
            setInput(entity, "");
            if (entity instanceof Player player) {
                player.closeContainer();
            }
        }
    }

    /** 面板关闭时清空输入（原 {@code PannelREshutProcedure}）。 */
    public static void resetInput(Entity entity) {
        if (entity != null) {
            setInput(entity, "");
        }
    }

    private static void setInput(Entity entity, String value) {
        Vars.of(entity).passwordre = value;
        Vars.sync(entity);
    }
}
