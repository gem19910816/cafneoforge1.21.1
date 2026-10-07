package net.gem19910816.dyairdrop.core;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;

/**
 * 游戏模式判定。
 *
 * <p>取代 MCreator 在 3 个过程里各写一遍的 {@code checkGamemode} 匿名类。
 * 判定规则保持原样：服务端看 {@link ServerPlayer#gameMode}，客户端查连接里的玩家信息；
 * 另外补了 {@code getConnection() != null} 的空值保护（原写法在极端时序下会 NPE）。
 */
public final class GameModes {

    private GameModes() {
    }

    public static boolean isCreative(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof ServerPlayer serverPlayer) {
            return serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
        }
        if (!entity.level().isClientSide() || !(entity instanceof Player player)) {
            return false;
        }
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return false; // 专用服务端不存在本地连接
        }
        var connection = Minecraft.getInstance().getConnection();
        if (connection == null) {
            return false;
        }
        var info = connection.getPlayerInfo(player.getGameProfile().getId());
        return info != null && info.getGameMode() == GameType.CREATIVE;
    }
}
