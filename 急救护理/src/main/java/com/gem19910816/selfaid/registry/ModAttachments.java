package com.gem19910816.selfaid.registry;

import com.gem19910816.selfaid.SelfAidMod;
import com.gem19910816.selfaid.body.BodyHealth;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister
            .create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, SelfAidMod.MODID);

    public static final Supplier<AttachmentType<BodyHealth>> BODY_HEALTH = ATTACHMENTS.register("body_health",
            () -> AttachmentType.builder(BodyHealth::new).serialize(BodyHealth.CODEC).build());

    private ModAttachments() {
    }
}
