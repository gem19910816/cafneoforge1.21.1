package net.gem19910816.dyairdrop.core;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

/**
 * 聊天消息工具：把模组里重复的「对全服广播 / 对单个玩家说话」写法收敛到一处。
 *
 * <p>与原实现一致：广播走 {@code broadcastSystemMessage(component, false)}（不进日志、发到聊天栏），
 * 只对玩家自己走 {@code displayClientMessage(component, false)}；两端都只在服务端执行。
 */
public final class Chat {

    private Chat() {
    }

    /** 全服广播一句话。 */
    public static void broadcast(LevelAccessor world, Component message) {
        if (world.isClientSide() || world.getServer() == null) {
            return;
        }
        world.getServer().getPlayerList().broadcastSystemMessage(message, false);
    }

    /** 全服广播一段普通文本（语言文件里取）。 */
    public static void broadcast(LevelAccessor world, String translationKey) {
        broadcast(world, Component.literal(Component.translatable(translationKey).getString()));
    }

    /** 只对某个玩家说话（服务端调用）；非玩家实体自动忽略。 */
    public static void tell(Entity entity, String text) {
        if (entity instanceof Player player && !player.level().isClientSide()) {
            player.displayClientMessage(Component.literal(text), false);
        }
    }

    /** 只对某个玩家说一句语言文件里的文本（服务端调用）。 */
    public static void tellKey(Entity entity, String translationKey) {
        tell(entity, Component.translatable(translationKey).getString());
    }
}
