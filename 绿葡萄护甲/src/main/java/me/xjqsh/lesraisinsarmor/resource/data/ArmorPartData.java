package me.xjqsh.lesraisinsarmor.resource.data;


import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorPartData {
    private ItemAttributeModifiers attributes;
    private Ingredient repairIngredient;
    private Holder<SoundEvent> equipSound;
    private int enchantmentValue;
    private int maxDurability;
    private int defense = 0;
    private float toughness = 0;
    private float knockbackResistance = 0;

    public ItemAttributeModifiers getAttributes() {
        return attributes;
    }

    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public Holder<SoundEvent> getEquipSound() {
        return equipSound;
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public int getDefense() {
        return defense;
    }

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public static ArmorPartData fromJson(ResourceLocation modifierId, ArmorPartData.Struct rawData, EquipmentSlot slot) {
        ArmorPartData data = new ArmorPartData();
        EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(slot);
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ARMOR,
                new AttributeModifier(modifierId, rawData.defense, AttributeModifier.Operation.ADD_VALUE),
                group);
        builder.add(Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(modifierId, rawData.toughness, AttributeModifier.Operation.ADD_VALUE),
                group);
        if (rawData.knockbackResistance > 0) {
            builder.add(Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(modifierId, rawData.knockbackResistance, AttributeModifier.Operation.ADD_VALUE),
                    group);
        }
        data.attributes = builder.build();
        try {
            if (rawData.repairIngredient != null && !rawData.repairIngredient.isJsonNull()) {
                data.repairIngredient = Ingredient.CODEC.decode(
                        com.mojang.serialization.JsonOps.INSTANCE, rawData.repairIngredient
                ).result().map(p -> p.getFirst()).orElse(null);
            }
        } catch (JsonParseException ignore) {}
        data.equipSound = Holder.direct(SoundEvent.createVariableRangeEvent(rawData.sound));
        data.enchantmentValue = rawData.enchantmentValue;
        data.maxDurability = rawData.maxDurability;
        data.defense = rawData.defense;
        data.toughness = rawData.toughness;
        data.knockbackResistance = rawData.knockbackResistance;

        return data;
    }

    public static class Struct {
        private int defense = 0;
        private int toughness = 0;
        private int knockbackResistance = 0;
        private int enchantmentValue = 5;
        private int maxDurability = 128;
        private ResourceLocation sound = ResourceLocation.withDefaultNamespace("item.armor.equip_leather");
        private JsonElement repairIngredient = null;
    }
}
