package com.scarasol.sona.effect;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaDamageTypes;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.init.SonaTags;
import com.scarasol.sona.manager.RustManager;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Corrosion extends PhysicalEffect{

    public Corrosion() {
        super(MobEffectCategory.HARMFUL, -10066432);
    }



    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        float damage = (float) (equipmentCorroded(entity, amplifier) * (amplifier + 1) / (0.2 * (amplifier + 1) + 1));
        if (damage > 0 && CommonConfig.OVER_DOT.get())
            entity.invulnerableTime = 0;
        entity.hurt(SonaDamageTypes.damageSource(entity.level().registryAccess(), SonaDamageTypes.CORROSION), damage);
        if (entity.hasEffect(SonaMobEffects.SLIMINESS)){
            int level = entity.getEffect(SonaMobEffects.SLIMINESS).getAmplifier() + 1;
            entity.removeEffect(SonaMobEffects.SLIMINESS);
            entity.removeEffect(SonaMobEffects.CORROSION);
            if (!entity.level().isClientSide())
                entity.level().explode(null, entity.getX(), entity.getY(), entity.getZ(), (amplifier + 1 + level) / 2.0F, Level.ExplosionInteraction.NONE);
        }
        return true;
    }

    public double equipmentCorroded(LivingEntity entity, int amplifier){
        double exposed = 0;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
            if (!equipmentSlot.isArmor())
                continue;
            ItemStack armor = entity.getItemBySlot(equipmentSlot);
            if (armor.isEmpty()){
                exposed += 0.25;
            }else {
                if(!armor.is(SonaTags.CORRODED_IMMUNE))
                    armor.hurtAndBreak((int)Math.pow(amplifier + 1, 2), entity, equipmentSlot);
                if (CommonConfig.RUST_OPEN.get() && RustManager.canBeRust(armor))
                    RustManager.addRust(armor, -(amplifier + 1));
            }
        }
        return exposed;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return (duration % 20 == 0);
    }

}
