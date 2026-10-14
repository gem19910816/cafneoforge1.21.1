package com.scarasol.zombiekit.network;


import com.scarasol.zombiekit.inventory.WeaponModificationMenu;
import com.scarasol.zombiekit.item.armor.ExoArmor;
import com.scarasol.zombiekit.item.weapon.Flamethrower;
import com.scarasol.zombiekit.item.weapon.parts.ChargingParts;
import com.scarasol.zombiekit.item.api.ModifiableWeapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IPlayerExtension;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.scarasol.zombiekit.ZombieKitMod.MODID;

public record KeyBindPacket(int mode) implements CustomPacketPayload {

    public static final Type<KeyBindPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "key_bind"));

    public static final StreamCodec<FriendlyByteBuf, KeyBindPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, KeyBindPacket::mode,
            KeyBindPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public int getMode() {
        return mode;
    }

    public static void handler(KeyBindPacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                if (msg.getMode() <= 5)
                    ExoArmor.switchMode(player, msg.getMode());
                else if (msg.getMode() == 6) {
                    if (player.getMainHandItem().getItem() instanceof ModifiableWeapon)
                        // 编译期 classpath 是未打补丁的 vanilla jar，openMenu(MenuProvider, Consumer) 由 NeoForge 的 IPlayerExtension 提供，故强转调用
                        ((IPlayerExtension) player).openMenu(new MenuProvider() {
                            @Override
                            public Component getDisplayName() {
                                return Component.literal("weapon_modification_gui");
                            }

                            @Override
                            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                                return new WeaponModificationMenu(id, inventory, null);
                            }
                        }, friendlyByteBuf -> ItemStack.STREAM_CODEC.encode(friendlyByteBuf, player.getMainHandItem()));
                } else if (msg.getMode() == 7) {
                    ItemStack itemStack = player.getMainHandItem();
                    if (itemStack.getItem() instanceof ModifiableWeapon modifiableWeapon) {
                        Item parts = modifiableWeapon.getChargingParts(itemStack);
                        if (parts instanceof ChargingParts chargingParts) {
                            chargingParts.attack((ServerLevel) player.level(), player, (float) player.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                        }
                    }
                } else if (msg.getMode() == 8) {
                    ItemStack itemStack = player.getMainHandItem();
                    if (itemStack.getItem() instanceof Flamethrower flamethrower) {
                        flamethrower.reload(player, player.level(), InteractionHand.MAIN_HAND);
                    }
                }
            }
        });
    }
}
