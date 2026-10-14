package com.scarasol.zombiekit.item.weapon;

import com.scarasol.sona.item.IRustItem;
import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitKeyMappings;
import com.scarasol.zombiekit.item.api.DoubleHandWeapon;
import com.scarasol.zombiekit.item.weapon.parts.GripParts;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Rake extends HoeItem implements IRustItem, DoubleHandWeapon {

    private static final ResourceLocation BLOCK_RANGE_ID = ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "rake_block_range");
    private static final ResourceLocation ENTITY_RANGE_ID = ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "rake_entity_range");

    private ItemAttributeModifiers weaponModifiers;

    public Rake(Tier tier, int attackDamage, float speed, Properties properties) {
        super(tier, properties);
    }

    public float getAttackDamage() {
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_RAKE_DAMAGE.get() : CommonConfig.RAKE_DAMAGE.get();
        return (float) (damage * 0.5);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        if (this.weaponModifiers == null)
            this.weaponModifiers = initModifier();
        ItemAttributeModifiers modifiers = this.weaponModifiers;
        Item grip = getGripParts(stack);
        if (grip instanceof GripParts gripParts) {
            modifiers = gripParts.withWeaponModifiers(this, modifiers);
        }
        return modifiers;
    }

    public ItemAttributeModifiers initModifier() {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        double damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_RAKE_DAMAGE.get() : CommonConfig.RAKE_DAMAGE.get();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage - 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, CommonConfig.RAKE_SPEED.get() - 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        double amplifier = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_RAKE_RANGE_INCREASE.get() : CommonConfig.RAKE_RANGE_INCREASE.get();
        builder.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(BLOCK_RANGE_ID, amplifier, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ENTITY_RANGE_ID, amplifier, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND);
        return builder.build();
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, @NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.zombiekit.rake.description"));
        list.add(Component.translatable("item.zombiekit.modification", ZombieKitKeyMappings.MODIFICATION_GUI.getKey().getDisplayName()));
        if (getTier() != Tiers.NETHERITE)
            list.add(Component.translatable("item.zombiekit.level_limit"));
    }

    @Override
    public int getMaxDamage(ItemStack itemStack) {
        float modifier = 1;
        Item item = getGripParts(itemStack);
        if (item instanceof GripParts gripParts)
            modifier = modifier * gripParts.getDurabilityModifier();
        return Math.round(super.getMaxDamage(itemStack) * modifier);
    }
}
