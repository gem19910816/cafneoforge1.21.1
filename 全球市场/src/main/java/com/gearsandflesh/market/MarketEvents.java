package com.gearsandflesh.market;

import com.gearsandflesh.market.config.MarketConfig;
import com.gearsandflesh.market.data.MarketWorldData;
import com.gearsandflesh.market.network.MarketNetwork;
import com.gearsandflesh.market.remote.MarketApiClient;
import com.gearsandflesh.market.service.MarketService;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = MarketConstants.MOD_ID)
public final class MarketEvents {
    private MarketEvents() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("market")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    if (MarketConfig.REQUIRE_TERMINAL.get()
                            && !context.getSource().hasPermission(MarketConstants.ADMIN_PERMISSION_LEVEL)) {
                        context.getSource().sendFailure(
                                Component.literal("请在市场终端方块处交易"));
                        return 0;
                    }
                    MarketNetwork.open(player);
                    return 1;
                })
                .then(Commands.literal("bind")
                        .then(Commands.argument("code", StringArgumentType.word())
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    String code = StringArgumentType.getString(context, "code");
                                    bindPlayer(player, code);
                                    return 1;
                                })))
                .then(Commands.literal("unbind")
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            MarketWorldData.get(player.server).unbind(player.getUUID());
                            context.getSource().sendSuccess(
                                    () -> Component.literal("已解除官网账号绑定"), false);
                            return 1;
                        }))
                .then(Commands.literal("edit")
                        .requires(source -> source.hasPermission(MarketConstants.ADMIN_PERMISSION_LEVEL))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            MarketNetwork.openAdmin(player);
                            return 1;
                        })));
    }

    private static void bindPlayer(ServerPlayer player, String code) {
        MarketWorldData world = MarketWorldData.get(player.server);
        if (world.isBound(player.getUUID())) {
            MarketNetwork.sendNotice(player, false, "本存档已绑定过官网账号，如需更换请先 /market unbind");
            return;
        }
        MarketNetwork.sendNotice(player, true, "正在验证绑定码…");
        MarketApiClient.bind(player.getUUID(), player.getGameProfile().getName(), code)
                .thenAccept(response -> player.server.execute(() -> {
                    if (response.ok() && response.has("token")) {
                        world.bind(player.getUUID(), response.body().get("token").getAsString());
                        MarketNetwork.sendNotice(player, true, "官网账号绑定成功，现在可以使用全球市场了");
                    } else {
                        MarketNetwork.sendNotice(player, false,
                                response.error() != null ? response.error() : "绑定码无效或已使用");
                    }
                }));
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        MarketWorldData.get(event.getServer());
        if (MarketService.isMoneyAvailable()) {
            GlobalMarketMod.LOGGER.info("Global Market currency is {}", MarketConstants.MONEY_ID);
        } else {
            GlobalMarketMod.LOGGER.warn(
                    "Global Market currency {} is missing; wallet deposit/withdraw is disabled",
                    MarketConstants.MONEY_ID);
        }
    }

    /** Worlds that ever enter creative (or spectator) are permanently banned
     *  from uploading listings. */
    @SubscribeEvent
    public static void onGameModeChange(PlayerEvent.PlayerChangeGameModeEvent event) {
        GameType gameMode = event.getNewGameMode();
        if (gameMode == GameType.CREATIVE || gameMode == GameType.SPECTATOR) {
            if (event.getEntity() instanceof ServerPlayer player) {
                MarketWorldData.get(player.server).markCreativeUsed();
            }
        }
    }

    /** Catches creative access that bypassed the change event (e.g. edited
     *  level.dat with cheats enabled at creation). */
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)
                || player.tickCount % 100 != 0) {
            return;
        }
        GameType gameMode = player.gameMode.getGameModeForPlayer();
        if (gameMode == GameType.CREATIVE || gameMode == GameType.SPECTATOR) {
            MarketWorldData.get(player.server).markCreativeUsed();
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        MarketNetwork.forgetPlayer(event.getEntity().getUUID());
    }
}
