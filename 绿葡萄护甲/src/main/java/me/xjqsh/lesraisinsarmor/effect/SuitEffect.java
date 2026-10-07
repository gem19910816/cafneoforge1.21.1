package me.xjqsh.lesraisinsarmor.effect;

import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;

public class SuitEffect extends MobEffect {
    private final String suitIdf;
    public SuitEffect(MobEffectCategory effectType, int color, String suitIdf) {
        super(effectType, color);
        this.suitIdf = suitIdf;
    }

    @Override
    public boolean applyEffectTick(@Nonnull LivingEntity entity, int amplifier){
        if(entity instanceof Player player){
            if(LrArmorItem.getSuitCount(player, suitIdf) != 4){
                Holder<MobEffect> self = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(this);
                player.removeEffect(self);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }
}
