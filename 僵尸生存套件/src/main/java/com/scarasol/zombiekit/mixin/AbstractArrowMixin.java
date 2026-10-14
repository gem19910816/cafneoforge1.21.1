package com.scarasol.zombiekit.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.scarasol.zombiekit.api.NoAttenuationProjectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// 1.21 中 Projectile 构造器为包私有，Mixin 类不再继承 Projectile；局部表捕获改为 WrapOperation（1.21 局部表不兼容）
@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {

    // 1.21.1 重力改为 setDeltaMovement 之后的 applyGravity() 单独调用；对无下坠弹射物直接跳过
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;applyGravity()V"))
    private void zombiekit$noAttenuation(AbstractArrow self, Operation<Void> original) {
        if (!(self instanceof NoAttenuationProjectile)) {
            original.call(self);
        }
    }
}
