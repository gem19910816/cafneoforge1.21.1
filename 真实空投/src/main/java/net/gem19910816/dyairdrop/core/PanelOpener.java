package net.gem19910816.dyairdrop.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.netty.buffer.Unpooled;

import net.gem19910816.dyairdrop.world.inventory.AirdropGUIMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelMenu;
import net.gem19910816.dyairdrop.world.inventory.PannelRE2Menu;
import net.gem19910816.dyairdrop.world.inventory.PannelREMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;

/**
 * 「打开某个空投箱子界面」的统一入口。
 *
 * <p>取代原 {@code SafetestProcedure}、{@code Safeopen2Procedure}、{@code RandomstringProcedure}、
 * {@code AirdropGUIopenProcedure}、{@code FancyairdropguiopenProcedure}、
 * {@code LockedairdroplargedebugProcedure} 六个类（约 420 行，其中打开菜单的匿名 {@link MenuProvider}
 * 被复制了 10 遍）。
 *
 * <p>各分支行为逐条保留：
 * <ul>
 *   <li>数字面板（{@code Lockedairdroplarge} / {@code Safe}）：先查 {@code valid} 占用锁，被占用/已 shutdown
 *       就提示 {@code message.currentlybeingused }（键名带尾空格，原样保留）并打开 {@code Pannel};</li>
 *   <li>字母面板 RE（医疗 / 小型 / 武器箱）：同样先查占用锁，再开 {@code PannelRE} 并生成 6 位字母密码；</li>
 *   <li>字母面板 RE2（{@code Safe2}）：**原实现不做占用检查**（保持一致），先生成密码，
 *       已解锁（{@code isopen}）则直接开 {@code AirdropGUI}，否则开 {@code PannelRE2}；</li>
 *   <li>战利品界面（4 种普通箱 + 开启态箱子）：清掉 {@code s} 标记后开 {@code AirdropGUI}，
 *       其中 {@code Fancy…} 版本额外播开箱音并把 {@code animation} 置 1（原实现顺序：清 s → 置动画 → 开界面 → 播音）；</li>
 *   <li>调试入口（开启态箱子）：第一次右键把 {@code canopen} 置 true，之后才开界面。</li>
 * </ul>
 *
 * <p><b>保留的历史差异</b>：RE 面板生成密码后只在创造模式广播 {@code message.creativepassword}，
 * 而 RE2 面板无条件广播（原实现如此，未改；若确认是漏洞可改为与 RE 一致）。
 */
public final class PanelOpener {

    private static final String TAG_VALID = "valid";
    private static final String TAG_LETTER_PASSWORD = "pw";
    private static final String TAG_OPENED = "isopen";
    private static final String TAG_IN_USE = "s";
    private static final String TAG_CAN_OPEN = "canopen";
    private static final String BUSY_MESSAGE_KEY = "message.currentlybeingused ";
    private static final String CREATIVE_PASSWORD_KEY = "message.creativepassword";
    private static final String LETTER_ALPHABET = "abcdef";
    private static final String STATE_SHUTDOWN = "shutdown";
    private static final int PASSWORD_LENGTH = 6;
    private static final int ANIMATION_UNLOCK = 1;

    private PanelOpener() {
    }

    /** 数字密码面板（{@code Pannel}）。 */
    public static void openDigitPanel(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        if (!isPanelFree(world, pos, entity)) {
            return;
        }
        openMenu(entity, pos, "Pannel", PannelMenu::new);
    }

    /**
     * 字母密码面板（RE / RE2）。
     *
     * @param re2 {@code true} 表示 RE2（{@code Safe2}，无占用检查、解锁后直接给战利品界面）
     */
    public static void openLetterPanel(LevelAccessor world, double x, double y, double z, Entity entity, boolean re2) {
        if (entity == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        if (re2) {
            ensureLetterPassword(world, pos, entity, false);
            if (Nbt.getBoolean(world, pos, TAG_OPENED)) {
                openMenu(entity, pos, "AirdropGUI", AirdropGUIMenu::new);
            } else {
                openMenu(entity, pos, "PannelRE2", PannelRE2Menu::new);
            }
            return;
        }
        if (!isPanelFree(world, pos, entity)) {
            return;
        }
        openMenu(entity, pos, "PannelRE", PannelREMenu::new);
        ensureLetterPassword(world, pos, entity, true);
    }

    /** 普通战利品界面：清掉「正在使用」标记后开界面。 */
    public static void openLootGui(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        Nbt.setBoolean(world, pos, TAG_IN_USE, false);
        openMenu(entity, pos, "AirdropGUI", AirdropGUIMenu::new);
    }

    /** 开启态箱子的战利品界面：清标记 → 置开箱动画 → 开界面 → 播开箱音。 */
    public static void openLootGuiWithAnimation(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        Nbt.setBoolean(world, pos, TAG_IN_USE, false);
        Blocks.setAnimation(world, pos, ANIMATION_UNLOCK);
        openMenu(entity, pos, "AirdropGUI", AirdropGUIMenu::new);
        Sounds.play(world, x, y, z, SoundEvents.CHEST_OPEN, 1.0F);
    }

    /** 调试入口：第一次右键置 {@code canopen}，之后才开界面。 */
    public static void openDebugGui(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) {
            return;
        }
        BlockPos pos = BlockPos.containing(x, y, z);
        if (!Nbt.getBoolean(world, pos, TAG_CAN_OPEN)) {
            Nbt.setBoolean(world, pos, TAG_CAN_OPEN, true);
            return;
        }
        openMenu(entity, pos, "AirdropGUI", AirdropGUIMenu::new);
    }

    // ------------------------------------------------------------------ 内部

    /** 面板占用锁：{@code valid} 为空可用；被别人占着或处于 shutdown 状态则提示后拒绝。 */
    private static boolean isPanelFree(LevelAccessor world, BlockPos pos, Entity entity) {
        String valid = Nbt.getString(world, pos, TAG_VALID);
        if (valid.length() < 1) {
            return true;
        }
        if (!entity.getDisplayName().getString().equals(valid) || STATE_SHUTDOWN.equals(valid)) {
            if (entity instanceof Player player && !player.level().isClientSide()) {
                player.displayClientMessage(Component.literal(Component.translatable(BUSY_MESSAGE_KEY).getString()), true);
            }
            return false;
        }
        return true;
    }

    /** 首次打开字母面板时生成 6 位字母密码（{@code abcdef} 洗牌）。 */
    private static void ensureLetterPassword(LevelAccessor world, BlockPos pos, Entity entity, boolean broadcastOnlyInCreative) {
        if (!Nbt.getString(world, pos, TAG_LETTER_PASSWORD).isEmpty()) {
            return;
        }
        List<Character> letters = new ArrayList<>(PASSWORD_LENGTH);
        for (char c : LETTER_ALPHABET.toCharArray()) {
            letters.add(c);
        }
        Collections.shuffle(letters);
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (char c : letters) {
            password.append(c);
        }
        if (!world.isClientSide()) {
            Nbt.setString(world, pos, TAG_LETTER_PASSWORD, password.toString());
        }
        boolean shouldBroadcast = !broadcastOnlyInCreative || GameModes.isCreative(entity);
        if (shouldBroadcast) {
            Chat.broadcast(world, Component.literal(Component.translatable(CREATIVE_PASSWORD_KEY).getString() + password));
        }
    }

    /** 打开菜单：标题文本与菜单里携带的方块坐标都与原实现一致。 */
    private static void openMenu(Entity entity, BlockPos pos, String title, MenuFactory factory) {
        if (!(entity instanceof ServerPlayer player)) {
            return;
        }
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal(title);
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player owner) {
                return factory.create(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
            }
        }, buffer -> buffer.writeBlockPos(pos));
    }

    /** 菜单构造器签名（三个面板菜单 + 战利品菜单都是这个形状）。 */
    @FunctionalInterface
    private interface MenuFactory {
        AbstractContainerMenu create(int id, Inventory inventory, FriendlyByteBuf data);
    }
}
