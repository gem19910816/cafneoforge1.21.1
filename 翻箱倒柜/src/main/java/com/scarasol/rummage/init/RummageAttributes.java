package com.scarasol.rummage.init;

import com.scarasol.rummage.RummageMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * @author Scarasol
 */
@EventBusSubscriber(modid = RummageMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RummageAttributes {
    public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(Registries.ATTRIBUTE, RummageMod.MODID);

    public static final DeferredHolder<Attribute, Attribute> RUMMAGE_MODIFIER = REGISTRY.register("rummage_modifier",
            () -> new RangedAttribute("attribute.rummage.rummage_modifier", 1.0D, 0.0D, 1024.0D));

    public static final DeferredHolder<Attribute, Attribute> CAN_CHAIN_RUMMAGE = REGISTRY.register("can_chain_rummage",
            () -> new RangedAttribute("attribute.rummage.can_chain_rummage", 0.0D, 0.0D, 1.0D));

    public static final DeferredHolder<Attribute, Attribute> SILENT_RUMMAGE = REGISTRY.register("silent_rummage",
            () -> new RangedAttribute("attribute.rummage.silent_rummage", 0.0D, 0.0D, 1.0D));

    public static final DeferredHolder<Attribute, Attribute> MIN_RUMMAGE_RARITY = REGISTRY.register("min_rummage_rarity",
            () -> new RangedAttribute("attribute.rummage.min_rummage_rarity", 0.0D, 0.0D, 1024.0D));

    public static final DeferredHolder<Attribute, Attribute> DESTROY_CHANCE = REGISTRY.register("destroy_chance",
            () -> new RangedAttribute("attribute.rummage.destroy_chance", 1.0D, 0.0D, 1024.0D));

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, RummageAttributes.RUMMAGE_MODIFIER);
        event.add(EntityType.PLAYER, RummageAttributes.CAN_CHAIN_RUMMAGE);
        event.add(EntityType.PLAYER, RummageAttributes.SILENT_RUMMAGE);
        event.add(EntityType.PLAYER, RummageAttributes.MIN_RUMMAGE_RARITY);
        event.add(EntityType.PLAYER, RummageAttributes.DESTROY_CHANCE);
    }

    public static double getAttributeValue(Player player, Holder<Attribute> attribute) {
        AttributeInstance attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            return attributeInstance.getValue();
        }
        return attribute.value().getDefaultValue();
    }
}

