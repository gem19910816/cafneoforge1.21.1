package net.gem19910816.dyairdrop.panel;

import net.gem19910816.dyairdrop.core.Commands;

import net.gem19910816.dyairdrop.panel.LetterPanel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.gem19910816.dyairdrop.configuration.AirdropconfigConfiguration;
import net.gem19910816.dyairdrop.core.Blocks;
import net.gem19910816.dyairdrop.init.DyairdropModSounds;
import net.gem19910816.dyairdrop.network.DyairdropModVariables;



import net.gem19910816.dyairdrop.world.inventory.PannelMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelRE2Menu;
import net.gem19910816.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * 密码面板的**唯一权威实现**（服务端）。
 *
 * <p>替代旧实现里散落在客户端屏幕 + 静态 {@code guistate} + 4 个按钮 payload 里的逻辑。
 * 语义按 {@code 重构说明.md} 与原实现的静态行为规格 1:1 保留：
 * 密码首次按 √ 时随机生成 6 位数字、逐位比对结果串 {@code pw} 驱动亮灯、
 * 成功置 {@code open="1"} / {@code valid="shutdown"}、+45 tick 放大成功音与 animation=1、
 * +84 tick 用命令把方块替换成开启形态、失败扣 MAGIC 伤害 attemptpunishment（默认 2.0）并提示「密码错误」、
 * 位数不符提示「长度错误」并回显输入、55 tick 后自动关窗。
 *
 * <p>服务端权威带来三个直接收益：
 * <ol>
 *   <li>解锁真的会发生（旧实现服务端永远走不到解锁分支）；</li>
 *   <li>输入串不再只存在于客户端 EditBox，杜绝「客户端本地跑一遍服务端逻辑」造成的双重执行与回弹；</li>
 *   <li>面板与其它模组的容器/按键冲突消失（不再抢占键位、不再每 tick 强关别人的容器）。</li>
 * </ol>
 */
@EventBusSubscriber
public final class PanelService {

    /** 数字面板（PannelMenu，0~9 + √）。 */
    public static final int KIND_DIGIT = 0;
    /** 字母面板 RE（PannelREMenu，a~f）。 */
    public static final int KIND_LETTER_RE = 1;
    /** 字母面板 RE2（PannelRE2Menu，a~f）。 */
    public static final int KIND_LETTER_RE2 = 2;

    /** 动作编号（与网络包一致）。 */
    public static final int ACTION_DELETE = 10;
    public static final int ACTION_CONFIRM = 11;
    public static final int ACTION_SET_LOOT = 12;
    public static final int ACTION_SET_PASSWORD = 13;
    public static final int ACTION_TEST = 14;
    public static final int ACTION_CLEAR = 15;

    private static final int MAX_INPUT = 16;
    private static final int PASSWORD_LENGTH = 6;
    private static final double MAX_PANEL_DISTANCE_SQR = 64.0;
    private static final long AUTO_CLOSE_TICKS = 55;
    private static final long CONFIRM_ANIMATION_DELAY = 45;
    private static final long CONFIRM_SETBLOCK_DELAY = 84;

    private static final Map<UUID, Integer> LAST_ACTION_TICK = new HashMap<>();

    private PanelService() {
    }

    // ------------------------------------------------------------------ 入口

    /**
     * 处理一次面板操作。所有校验都在这里完成；校验不过就静默丢弃（客户端可能被改，不能信任）。
     */
    public static void handle(ServerPlayer player, int kind, BlockPos pos, int action, String rawInput) {
        if (player == null || pos == null || !(player.level() instanceof ServerLevel level)) {
            return;
        }
        if (player.distanceToSqr(Vec3.atCenterOf(pos)) > MAX_PANEL_DISTANCE_SQR) {
            return;
        }
        if (!isRateLimitOk(player, level)) {
            return;
        }
        if (!isPanelOpenFor(player, kind, pos)) {
            return;
        }
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) {
            return;
        }

        switch (kind) {
            case KIND_DIGIT -> handleDigitPanel(player, level, pos, be, action, rawInput);
            case KIND_LETTER_RE -> handleLetterPanel(player, level, pos, action, false);
            case KIND_LETTER_RE2 -> handleLetterPanel(player, level, pos, action, true);
            default -> {
                // 未知面板类型：忽略
            }
        }
    }

    private static boolean isRateLimitOk(ServerPlayer player, ServerLevel level) {
        int now = level.getServer().getTickCount();
        Integer last = LAST_ACTION_TICK.get(player.getUUID());
        if (last != null && now - last < 1) {
            return false;
        }
        LAST_ACTION_TICK.put(player.getUUID(), now);
        return true;
    }

    private static boolean isPanelOpenFor(ServerPlayer player, int kind, BlockPos pos) {
        return switch (kind) {
            case KIND_DIGIT -> player.containerMenu instanceof PannelMenu menu && matches(menu.x, menu.y, menu.z, pos);
            case KIND_LETTER_RE -> player.containerMenu instanceof PannelREMenu menu && matches(menu.x, menu.y, menu.z, pos);
            case KIND_LETTER_RE2 -> player.containerMenu instanceof PannelRE2Menu menu && matches(menu.x, menu.y, menu.z, pos);
            default -> false;
        };
    }

    private static boolean matches(int x, int y, int z, BlockPos pos) {
        return x == pos.getX() && y == pos.getY() && z == pos.getZ();
    }

    // ------------------------------------------------------------------ 数字面板

    private static void handleDigitPanel(ServerPlayer player, ServerLevel level, BlockPos pos, BlockEntity be, int action, String rawInput) {
        DyairdropModVariables.PlayerVariables vars = vars(player);

        if (action >= 0 && action <= 9) {
            if (vars.showlight == 0.0 && vars.password.length() < MAX_INPUT) {
                vars.password = vars.password + action;
                sync(player, vars);
            }
            return;
        }

        switch (action) {
            case ACTION_DELETE -> {
                // 原 ButtondelateProcedure 的行为：删除最后一位，并把提交状态复位（玩家借此重试）
                if (!vars.password.isEmpty()) {
                    vars.password = vars.password.substring(0, vars.password.length() - 1);
                }
                vars.showlight = 0.0;
                vars.pw = "";
                sync(player, vars);
            }
            case ACTION_CLEAR -> {
                vars.password = "";
                sync(player, vars);
            }
            case ACTION_CONFIRM -> confirmDigitPassword(player, level, pos, be, vars);
            case ACTION_SET_LOOT -> {
                String loot = rawInput == null ? "" : rawInput.strip();
                writeTag(level, pos, be, "loot", loot);
                player.displayClientMessage(Component.literal(Component.translatable("message.setloot").getString() + loot), false);
                player.closeContainer();
            }
            case ACTION_SET_PASSWORD -> {
                String candidate = rawInput == null ? "" : rawInput.replace(" ", "");
                if (candidate.length() == PASSWORD_LENGTH) {
                    writeTag(level, pos, be, "key", candidate);
                    player.displayClientMessage(Component.literal(Component.translatable("message.passwordset").getString() + candidate), false);
                    player.closeContainer();
                } else {
                    player.displayClientMessage(Component.translatable("message.lengtherror"), false);
                }
            }
            case ACTION_TEST -> {
                player.displayClientMessage(Component.literal(be.getPersistentData().getString("key") + be.getPersistentData().getString("loot")), false);
                player.displayClientMessage(Component.literal("保存成功！"), false);
            }
            default -> {
                // 忽略未知动作
            }
        }
    }

    private static void confirmDigitPassword(ServerPlayer player, ServerLevel level, BlockPos pos, BlockEntity be, DyairdropModVariables.PlayerVariables vars) {
        if (vars.showlight != 0.0) {
            return; // 已提交过，等自动关窗
        }
        String input = vars.password.replace(" ", "");
        vars.password = input;

        String key = be.getPersistentData().getString("key");
        if (key.length() <= 1) {
            key = generatePassword(player);
            writeTag(level, pos, be, "key", key);
        }

        if (vars.showlight != 1.0) {
            vars.pw = "";
        }

        if (input.length() != key.length()) {
            // 原实现：闸门失败 → 「长度错误」+ 回显输入
            player.displayClientMessage(Component.literal("长度错误"), false);
            player.displayClientMessage(Component.literal(input), false);
            sync(player, vars);
            return;
        }

        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            result.append(input.charAt(i) == key.charAt(i) ? '1' : '0');
        }
        vars.pw = result.toString();
        vars.showlight = 1.0;
        vars.keyticking = level.getDayTime();
        sync(player, vars);

        // CHECK 音效：tick 1/8/15/22/29/36/43（音量 5.0，音高 1.0）
        for (int delay = 1; delay <= 43; delay += 7) {
            schedule(delay, () -> playAt(level, pos, DyairdropModSounds.CHECK.get(), 5.0F));
        }

        boolean matched = input.equals(key);
        if (matched) {
            writeTag(level, pos, be, "open", "1");
            writeTag(level, pos, be, "valid", "shutdown");
        }

        schedule((int) CONFIRM_ANIMATION_DELAY, () -> {
            if (matched) {
                playAt(level, pos, DyairdropModSounds.PWCORRECT.get(), 5.0F);
                Blocks.setAnimation(level, pos, 1);
            } else {
                player.displayClientMessage(Component.literal("密码错误"), false);
                playAt(level, pos, DyairdropModSounds.PWWRONG.get(), 1.0F);
                double punishment = AirdropconfigConfiguration.ATTEMPTPUNISHMENT.get();
                if (punishment > 0) {
                    player.hurt(level.damageSources().magic(), (float) punishment);
                }
            }
        });

        if (matched) {
            schedule((int) CONFIRM_SETBLOCK_DELAY, () -> replaceWithOpenVariant(level, pos));
        }
    }

    private static String generatePassword(ServerPlayer player) {
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(player.getRandom().nextInt(10));
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------ 字母面板（RE / RE2）

    private static void handleLetterPanel(ServerPlayer player, ServerLevel level, BlockPos pos, int action, boolean re2) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (action >= 0 && action < LetterPanel.letterCount()) {
            LetterPanel.press(level, pos, player, action);
            return;
        }
        if (action == 6 || action == ACTION_CONFIRM) {
            if (re2) {
                LetterPanelConfirm.confirmRe2(level, x, y, z, player);
            } else {
                LetterPanelConfirm.confirmRe(level, x, y, z, player);
            }
        }
    }

    // ------------------------------------------------------------------ 生命周期

    /**
     * 面板打开时（服务端）的初始化：清空玩家输入缓存，并在 {@code valid} 为空时占用该面板。
     *
     * <p>对应原 {@code OpenProcedure}，但去掉了「顺手清空客户端 EditBox」这种跨端副作用。
     */
    public static void onPanelOpened(Player player, BlockPos pos) {
        if (!(player instanceof ServerPlayer serverPlayer) || !(player.level() instanceof ServerLevel level)) {
            return;
        }
        DyairdropModVariables.PlayerVariables vars = vars(serverPlayer);
        vars.password = "";
        if (vars.showlight != 0.0) {
            vars.showlight = 0.0;
            vars.pw = "";
        }
        sync(serverPlayer, vars);

        BlockEntity be = level.getBlockEntity(pos);
        if (be != null && be.getPersistentData().getString("valid").isEmpty()) {
            writeTag(level, pos, be, "valid", player.getDisplayName().getString());
        }
    }

    /**
     * 面板关闭（容器 removed）时清理：清空输入缓存、方块实体 {@code open="0"}、
     * 若 {@code valid} 正好是本玩家则释放占用锁。对应原 {@code ShutdownProcedure}，但去掉了两段无副作用的自我赋值。
     */
    public static void onPanelClosed(ServerPlayer player, BlockPos pos) {
        DyairdropModVariables.PlayerVariables vars = vars(player);
        vars.password = "";
        vars.showlight = 0.0;
        sync(player, vars);

        if (player.level() instanceof ServerLevel level) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be != null) {
                writeTag(level, pos, be, "open", "0");
                String valid = be.getPersistentData().getString("valid");
                if (valid.equals(player.getDisplayName().getString())) {
                    writeTag(level, pos, be, "valid", "");
                }
            }
        }
        LAST_ACTION_TICK.remove(player.getUUID());
    }

    /** 字母面板 RE 的收尾（原 {@code PannelREticksProcedure}）：解锁成功后自动关窗。 */
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.containerMenu instanceof PannelREMenu || player.containerMenu instanceof PannelRE2Menu) {
            LetterPanel.tickAutoClose(player);
            return;
        }
        if (player.containerMenu instanceof PannelMenu) {
            // 原 ShutdownprocessProcedure：提交后 55 tick 自动关窗（现在只在服务端、且只针对本玩家自己的菜单）
            DyairdropModVariables.PlayerVariables vars = vars(player);
            if (vars.pw.length() == PASSWORD_LENGTH
                    && vars.showlight == 1.0
                    && player.level().getDayTime() - vars.keyticking >= AUTO_CLOSE_TICKS) {
                player.closeContainer();
            }
        }
    }

    // ------------------------------------------------------------------ 工具

    private static DyairdropModVariables.PlayerVariables vars(ServerPlayer player) {
        return player.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get());
    }

    private static void sync(ServerPlayer player, DyairdropModVariables.PlayerVariables vars) {
        vars.syncPlayerVariables(player);
    }

    private static void writeTag(ServerLevel level, BlockPos pos, BlockEntity be, String key, String value) {
        if (be.getPersistentData().getString(key).equals(value)) {
            return;
        }
        be.getPersistentData().putString(key, value);
        BlockState state = level.getBlockState(pos);
        level.sendBlockUpdated(pos, state, state, 3);
        be.setChanged();
    }

    private static void playAt(ServerLevel level, BlockPos pos, net.minecraft.sounds.SoundEvent sound, float volume) {
        net.gem19910816.dyairdrop.core.Sounds.play(level, pos.getX(), pos.getY(), pos.getZ(), sound, volume);
    }

    /** 原实现的成功收尾：把方块整块替换成开启形态，并把战利品表塞进去。 */
    private static void replaceWithOpenVariant(ServerLevel level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) {
            return;
        }
        String loot = be.getPersistentData().getString("loot");
        BlockState state = level.getBlockState(pos);
        String blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
        String facing = "";
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            facing = "[facing=" + state.getValue(BlockStateProperties.HORIZONTAL_FACING).getName() + "]";
        }
        String command = "setblock ~ ~ ~ " + blockId + "open" + facing + "{LootTable:\"" + loot + "\"} replace";
        Commands.run(level, pos, command);
    }

    private static void schedule(int delayTicks, Runnable action) {
        DyairdropMod.queueServerWork(Math.max(1, delayTicks), action);
    }

    /** 供旧命令/调试入口使用：为某个面板随机生成并写入 6 位密码（原行为：首次确认时生成）。 */
    public static String ensurePassword(ServerLevel level, BlockPos pos, ServerPlayer player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be == null) {
            return "";
        }
        String key = be.getPersistentData().getString("key");
        if (key.length() <= 1) {
            key = generatePassword(player);
            writeTag(level, pos, be, "key", key);
        }
        return key;
    }
}
