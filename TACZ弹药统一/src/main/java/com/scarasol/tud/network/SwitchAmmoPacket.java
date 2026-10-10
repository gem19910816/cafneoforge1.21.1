package com.scarasol.tud.network;

import com.scarasol.tud.TudMod;
import com.scarasol.tud.client.network.ClientNetworkHandler;
import com.scarasol.tud.manager.AmmoManager;
import com.scarasol.tud.util.GunAmmoSelection;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.entity.IGunOperator;
import com.tacz.guns.api.item.IGun;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SwitchAmmoPacket(int id) implements CustomPacketPayload {
    public static final Type<SwitchAmmoPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(TudMod.MODID, "switch_ammo"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchAmmoPacket> STREAM_CODEC = StreamCodec.of(
            (buffer, packet) -> buffer.writeInt(packet.id()), buffer -> new SwitchAmmoPacket(buffer.readInt()));

    @Override
    public Type<SwitchAmmoPacket> type() { return TYPE; }

    public static void handle(SwitchAmmoPacket packet, IPayloadContext context) {
        if (context.flow() == PacketFlow.CLIENTBOUND) {
            ClientNetworkHandler.acceptSelection(packet.id());
            return;
        }
        if (!(context.player() instanceof ServerPlayer player) || player.isSpectator()) return;
        ItemStack stack = player.getMainHandItem();
        IGun gun = IGun.getIGunOrNull(stack);
        var data = AmmoManager.getGunData(stack);
        if (gun == null || data == null || data.availableAmmo() == null
                || packet.id() < 0 || packet.id() >= data.availableAmmo().size()) return;
        var index = TimelessAPI.getCommonGunIndex(gun.getGunId(stack));
        if (index.isEmpty() || !AmmoManager.canUseGeneralAmmo(gun.getGunId(stack).toString(),
                index.get().getGunData().getAmmoId().toString())) return;
        if (AmmoManager.getAmmoData(data.availableAmmo().get(packet.id()).ammoId()) == null) return;
        if (GunAmmoSelection.get(stack) == packet.id()) return;

        // Return the old ammunition before updating the selected type.
        gun.dropAllAmmo(player, stack);
        if (gun.hasBulletInBarrel(stack)) {
            gun.setBulletInBarrel(stack, false);
            if (!player.isCreative()) ItemHandlerHelper.giveItemToPlayer(player, AmmoManager.getGunAmmo(stack));
        }
        GunAmmoSelection.set(stack, packet.id());
        IGunOperator.fromLivingEntity(player).initialData();
        player.inventoryMenu.broadcastChanges();
        context.reply(packet);
    }
}
