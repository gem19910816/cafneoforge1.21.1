package com.scarasol.zombiekit.item.weapon;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitKeyMappings;
import com.scarasol.zombiekit.item.api.DoubleHandWeapon;
import com.scarasol.zombiekit.item.weapon.parts.GripParts;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseballBat extends SwordItem implements SweepWeapon, DoubleHandWeapon {

    private final boolean studded;
    private ItemAttributeModifiers weaponModifiers;

    public BaseballBat(Tier tier, int damage, float speed, Properties properties, boolean studded) {
        super(tier, properties);
        this.studded = studded;
    }

    public boolean isStudded() {
        return studded;
    }

    public float getDamage() {
        double damage;
        if (isStudded()){
            damage = CommonConfig.STUDDED_BASEBALL_BAT_DAMAGE.get();
        }else {
            damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_BASEBALL_BAT_DAMAGE.get() : CommonConfig.BASEBALL_BAT_DAMAGE.get();
        }
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
        double damage;
        if (isStudded()){
            damage = CommonConfig.STUDDED_BASEBALL_BAT_DAMAGE.get();
        }else {
            damage = getTier() == Tiers.NETHERITE ? CommonConfig.NETHERITE_BASEBALL_BAT_DAMAGE.get() : CommonConfig.BASEBALL_BAT_DAMAGE.get();
        }
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage - 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, CommonConfig.BASEBALL_BAT_SPEED.get() - 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        return builder.build();
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        weaponEffect(entity, sourceEntity);
        return super.hurtEnemy(itemstack, entity, sourceEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, @NotNull TooltipContext context, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.zombiekit.baseball_bat.description"));
        list.add(Component.translatable("item.zombiekit.modification", ZombieKitKeyMappings.MODIFICATION_GUI.getKey().getDisplayName()));
        if (getTier() != Tiers.NETHERITE)
            list.add(Component.translatable("item.zombiekit.level_limit"));
    }

    @Override
    public void sweepEffect(LivingEntity target, LivingEntity attacker, float damage) {
        if (attacker instanceof Player player && CommonConfig.SWEEP.get()){
            target.hurt(attacker.level().damageSources().playerAttack(player), (float) (damage * CommonConfig.SWEEP_MULTIPLIER.get()));
        }
        weaponEffect(target, attacker);
    }

    public void weaponEffect(LivingEntity target, LivingEntity attacker) {
        double multiplier = getTier() == Tiers.NETHERITE ? 1.8 : 1.2;
        target.setDeltaMovement(new Vec3((multiplier * attacker.getLookAngle().x), (multiplier * attacker.getLookAngle().y), (multiplier * attacker.getLookAngle().z)));
        if (studded)
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1, false, true));
    }

    @Override
    public int getMaxDamage(ItemStack itemStack) {
        float modifier = 1;
        Item item = getGripParts(itemStack);
        if (item instanceof GripParts gripParts)
            modifier = modifier * gripParts.getDurabilityModifier();
        return Math.round(super.getMaxDamage(itemStack) * modifier);
    }

    @Override
    public AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        if (getGripParts(stack) instanceof GripParts gripParts) {
            double range = gripParts.getSweepRange();
            if (range != 0) {
                Vec3 lookAngle = new Vec3(player.getViewVector(1).x, 0, player.getViewVector(1).z).normalize().scale(range + 0.5);
                return player.getBoundingBox().inflate(range, 0.25D, range).move(lookAngle).move(0, player.getViewVector(1).y, 0);
            }
        }
        return super.getSweepHitBox(stack, player, target);
    }
}
