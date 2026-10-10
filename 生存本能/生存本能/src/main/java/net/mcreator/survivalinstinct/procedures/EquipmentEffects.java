package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.item.*;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = "survival_instinct")
public final class EquipmentEffects {
    private enum Family { NONE, CAMO, EXO, HEAVY, JUGGERNAUT, FIRE, HAZMAT }

    private static Family family(Item item) {
        if (item instanceof GuillieItem || item instanceof SpruceGuillieItem) return Family.CAMO;
        if (item instanceof ExoItem) return Family.EXO;
        if (item instanceof ExoHeavyBlackItem || item instanceof ExoHeavyGreenItem || item instanceof ExoHeavyDesertItem) return Family.HEAVY;
        if (item instanceof BlackJuggernautItem || item instanceof GreenJuggernautItem || item instanceof DesertJuggernautItem) return Family.JUGGERNAUT;
        if (item instanceof FireFighterItem) return Family.FIRE;
        if (item instanceof HazmatItem) return Family.HAZMAT;
        return Family.NONE;
    }

    @SubscribeEvent
    public static void tick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living) || living.level().isClientSide()) return;
        Item head = living.getItemBySlot(EquipmentSlot.HEAD).getItem();
        if (head instanceof NightVisionGogglesItem) refresh(living, MobEffects.NIGHT_VISION, 80, 0, false);
        else if (head instanceof GreenHunterItem || head instanceof BlackHunterItem || head instanceof DesertHunterItem) {
            refresh(living, MobEffects.NIGHT_VISION, 60, 0, false);
        } else if (head instanceof GasMaskItem) {
            living.removeEffect(MobEffects.POISON);
            living.removeEffect(MobEffects.WITHER);
        }

        Family set = family(head);
        if (set == Family.NONE || family(living.getItemBySlot(EquipmentSlot.CHEST).getItem()) != set
                || family(living.getItemBySlot(EquipmentSlot.LEGS).getItem()) != set
                || family(living.getItemBySlot(EquipmentSlot.FEET).getItem()) != set) return;
        switch (set) {
            case CAMO -> {
                if (living.isShiftKeyDown()) refresh(living, MobEffects.INVISIBILITY, 25, 0, false);
            }
            case EXO -> {
                refresh(living, MobEffects.DIG_SPEED, 70, 0, true);
                refresh(living, MobEffects.DAMAGE_BOOST, 70, 0, true);
                refresh(living, MobEffects.MOVEMENT_SPEED, 70, 0, true);
                jump(living);
            }
            case HEAVY -> {
                refresh(living, MobEffects.NIGHT_VISION, 70, 0, true);
                refresh(living, MobEffects.DAMAGE_BOOST, 70, 1, true);
                refresh(living, MobEffects.DAMAGE_RESISTANCE, 70, 1, true);
                living.removeEffect(MobEffects.BLINDNESS);
                living.removeEffect(MobEffects.DARKNESS);
                jump(living);
            }
            case JUGGERNAUT -> {
                refresh(living, MobEffects.DAMAGE_RESISTANCE, 25, 0, false);
                refresh(living, MobEffects.MOVEMENT_SLOWDOWN, 25, 0, false);
            }
            case FIRE -> living.clearFire();
            case HAZMAT -> {
                living.removeEffect(MobEffects.POISON);
                living.removeEffect(MobEffects.WITHER);
                living.removeEffect(MobEffects.DARKNESS);
                living.removeEffect(MobEffects.BLINDNESS);
                living.removeEffect(MobEffects.HUNGER);
                living.removeEffect(MobEffects.WEAKNESS);
                living.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                living.removeEffect(MobEffects.CONFUSION);
                living.removeEffect(MobEffects.DIG_SLOWDOWN);
                living.removeEffect(MobEffects.LEVITATION);
            }
            default -> { }
        }
    }

    private static void jump(LivingEntity entity) {
        if (entity.onGround() && entity.isShiftKeyDown()) refresh(entity, MobEffects.JUMP, 10, 3, true);
    }

    private static void refresh(LivingEntity entity, Holder<MobEffect> effect, int duration, int amplifier, boolean ambient) {
        MobEffectInstance current = entity.getEffect(effect);
        if (current == null || current.getAmplifier() < amplifier
                || (current.getAmplifier() == amplifier && current.getDuration() <= duration - Math.min(10, duration / 2))) {
            entity.addEffect(new MobEffectInstance(effect, duration, amplifier, ambient, false));
        }
    }
}
