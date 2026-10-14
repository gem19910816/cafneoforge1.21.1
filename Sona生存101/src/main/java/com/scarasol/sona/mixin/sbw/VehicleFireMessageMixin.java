package com.scarasol.sona.mixin.sbw;


import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.network.message.send.VehicleFireMessage;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.manager.SoundManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.UUID;

/**
 * @author Scarasol
 */
@Mixin(value = VehicleFireMessage.class)
public abstract class VehicleFireMessageMixin {

    @WrapOperation(method = "handler", at = @At(value = "INVOKE", target = "Lcom/atsuishio/superbwarfare/entity/vehicle/base/VehicleEntity;vehicleShoot(Lnet/minecraft/world/entity/LivingEntity;Ljava/util/UUID;Lnet/minecraft/world/phys/Vec3;)V"), remap = false)
    private void onHandler(VehicleEntity vehicle, LivingEntity shooter, UUID uuid, Vec3 pos, Operation<Void> original) {
        if (CommonConfig.GUN_SOUND_ATTRACT.get() && shooter instanceof ServerPlayer player) {
            SoundManager.addSoundEffect(player, 40, CommonConfig.FIRE_EXPOSURE.get() - 1);
        }
        original.call(vehicle, shooter, uuid, pos);
    }
}
