package com.scarasol.zombiekit.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.scarasol.zombiekit.ZombieKitMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ZombieKitAttachments {

    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> CANCEL_KNOCKBACK =
            REGISTRY.register("cancel_knockback", () -> AttachmentType.builder(() -> false).build());

    @SubscribeEvent
    public static void register(FMLConstructModEvent event) {
        REGISTRY.register(ModList.get().getModContainerById(MODID).orElseThrow().getEventBus());
    }
}
