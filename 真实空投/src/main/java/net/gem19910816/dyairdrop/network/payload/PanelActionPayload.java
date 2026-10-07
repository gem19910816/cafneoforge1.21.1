package net.gem19910816.dyairdrop.network.payload;

import net.gem19910816.dyairdrop.panel.PanelService;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 客户端 → 服务端：密码面板的一次操作。
 *
 * <p>这是替代旧实现的关键：旧代码在客户端本地直接调用服务端用的 procedure（
 * {@code PannelScreen} 里 {@code sendToServer(...)} 之后紧跟 {@code handleButtonAction(...)}），
 * 而输入串只存在于客户端的 EditBox 里，服务端拿不到 —— 于是服务端的解锁分支永远走不到。
 * 现在改为「客户端只发动作 + 文本，服务端校验并执行」。
 *
 * @param action 动作：0~9 = 数字键 0~9（字母面板则是 a~f），10 = 删除，11 = 确认（√），
 *               12 = OP 设置战利品表，13 = 设置密码，14 = 测试/保存，15 = 清空输入
 * @param kind   面板类型：{@link PanelService#KIND_DIGIT} / {@link PanelService#KIND_LETTER_RE} /
 *               {@link PanelService#KIND_LETTER_RE2}
 * @param pos    面板对应方块坐标（服务端据此校验玩家真的开着这个面板）
 * @param input  仅动作 12/13 使用：客户端输入框里的文本（设置战利品表 / 设置密码）
 */
public record PanelActionPayload(int action, int kind, BlockPos pos, String input) implements CustomPacketPayload {

    private static final int MAX_INPUT = 64;

    public static final CustomPacketPayload.Type<PanelActionPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("dyairdrop", "panel_action"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PanelActionPayload> STREAM_CODEC = StreamCodec.ofMember(
            (msg, buf) -> {
                buf.writeVarInt(msg.action());
                buf.writeVarInt(msg.kind());
                buf.writeBlockPos(msg.pos());
                buf.writeUtf(msg.input(), MAX_INPUT);
            },
            buf -> new PanelActionPayload(buf.readVarInt(), buf.readVarInt(), buf.readBlockPos(), buf.readUtf(MAX_INPUT)));

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PanelActionPayload message, IPayloadContext context) {
        if (!context.flow().isServerbound()) {
            return;
        }
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                PanelService.handle(player, message.kind(), message.pos(), message.action(), message.input());
            }
        });
    }
}
