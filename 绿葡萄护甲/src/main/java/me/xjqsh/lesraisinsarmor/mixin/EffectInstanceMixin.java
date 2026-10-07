package me.xjqsh.lesraisinsarmor.mixin;

import me.xjqsh.lesraisinsarmor.effect.SuitEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public abstract class EffectInstanceMixin {

    @Shadow public abstract MobEffect getEffect();

    @Inject(method = "isInfiniteDuration", at = @At("HEAD"), cancellable = true)
    public void isNoCounter(CallbackInfoReturnable<Boolean> cir) {
        if(this.getEffect() instanceof SuitEffect) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
