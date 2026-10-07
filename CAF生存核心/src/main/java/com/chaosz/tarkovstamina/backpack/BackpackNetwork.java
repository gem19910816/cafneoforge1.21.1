package com.chaosz.tarkovstamina.backpack;

import com.chaosz.tarkovstamina.backpack.item.MilitaryBackpackItem;
import com.chaosz.tarkovstamina.backpack.menu.BackpackMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

/**
 * 背包的两个客户端→服务端包：打开背包、滚动（保留兼容）。
 *
 * <p>包类型在 {@code com.chaosz.tarkovstamina.network.StaminaNetwork#register} 里统一登记，
 * 本类只留包的定义、处理器与发送方法 —— 对外签名与 1.20.1 版一致。</p>
 */
public final class BackpackNetwork {

    private BackpackNetwork() {
    }

    // ═══════════════════════════════════════════════════════════════
    //  打开背包
    // ═══════════════════════════════════════════════════════════════

    public record OpenBackpackPacket() implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<OpenBackpackPacket> TYPE =
                new CustomPacketPayload.Type<>(CafBackpack.id("open_backpack"));

        public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPacket> STREAM_CODEC =
                StreamCodec.unit(new OpenBackpackPacket());

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  滚动（网格不再滚动，保留是为了不破坏老客户端）
    // ═══════════════════════════════════════════════════════════════

    public record ScrollBackpackPacket(int offset) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<ScrollBackpackPacket> TYPE =
                new CustomPacketPayload.Type<>(CafBackpack.id("scroll_backpack"));

        public static final StreamCodec<RegistryFriendlyByteBuf, ScrollBackpackPacket> STREAM_CODEC =
                StreamCodec.of(ScrollBackpackPacket::encode, ScrollBackpackPacket::decode);

        public static void encode(RegistryFriendlyByteBuf buf, ScrollBackpackPacket packet) {
            buf.writeVarInt(packet.offset);
        }

        public static ScrollBackpackPacket decode(RegistryFriendlyByteBuf buf) {
            return new ScrollBackpackPacket(buf.readVarInt());
        }

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  服务端处理
    // ═══════════════════════════════════════════════════════════════

    // public：StaminaNetwork（在 network 包里）要引用这两个方法，包私有跨不过去。
    public static void onOpenBackpack(OpenBackpackPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;
            ItemStack backpackStack = findBackpack(player);
            if (!backpackStack.isEmpty()) {
                MilitaryBackpackItem.openMenu(player, backpackStack);
            }
        });
    }

    public static void onScrollBackpack(ScrollBackpackPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player
                    && player.containerMenu instanceof BackpackMenu menu) {
                menu.setBackpackScroll(packet.offset());
            }
        });
    }

    private static ItemStack findBackpack(ServerPlayer player) {
        if (player.getMainHandItem().getItem() instanceof MilitaryBackpackItem) {
            return player.getMainHandItem();
        }
        if (player.getOffhandItem().getItem() instanceof MilitaryBackpackItem) {
            return player.getOffhandItem();
        }
        // Curios 9.x：getCuriosInventory 直接返回 Optional，不再有 LazyOptional.resolve()。
        return CuriosApi.getCuriosInventory(player)
                .flatMap(inv -> inv.findFirstCurio(stack -> stack.getItem() instanceof MilitaryBackpackItem))
                .map(SlotResult::stack)
                .orElse(ItemStack.EMPTY);
    }

    // ═══════════════════════════════════════════════════════════════
    //  发送
    // ═══════════════════════════════════════════════════════════════

    public static void sendOpenBackpackPacket() {
        PacketDistributor.sendToServer(new OpenBackpackPacket());
    }

    public static void sendScrollPacket(int offset) {
        PacketDistributor.sendToServer(new ScrollBackpackPacket(offset));
    }
}
