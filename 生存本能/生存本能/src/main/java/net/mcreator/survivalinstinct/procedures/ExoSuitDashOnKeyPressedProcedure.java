package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModMobEffects;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModSounds;
import net.mcreator.survivalinstinct.item.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public final class ExoSuitDashOnKeyPressedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!(entity instanceof ServerPlayer player) || !player.isAlive() || player.isSpectator() || player.isPassenger()
                || player.isSleeping() || player.hasEffect(SurvivalInstinctModMobEffects.DASH_ON_COOLDOWN)) return;
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            Item item = player.getItemBySlot(slot).getItem();
            if (!(item instanceof ExoItem || item instanceof ExoHeavyBlackItem || item instanceof ExoHeavyDesertItem || item instanceof ExoHeavyGreenItem)) return;
        }
        Vec3 previous = player.getDeltaMovement();
        Vec3 look = player.getLookAngle();
        Vec3 impulse = new Vec3(previous.x * 2.5 + look.x * 1.25, previous.y + 0.5 + look.y * 0.15, previous.z * 2.5 + look.z * 1.25);
        if (!Double.isFinite(impulse.lengthSqr())) return;
        if (impulse.lengthSqr() > 12.25) impulse = impulse.normalize().scale(3.5);
        player.addEffect(new MobEffectInstance(SurvivalInstinctModMobEffects.DASH_ON_COOLDOWN, 45, 0, true, false));
        player.setDeltaMovement(impulse);
        player.hurtMarked = true;
        player.connection.send(new ClientboundSetEntityMotionPacket(player));
        player.serverLevel().playSound(null, player.blockPosition(), SurvivalInstinctModSounds.EXO_DASH_02.get(), SoundSource.PLAYERS, 1, 1);
        player.serverLevel().sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 5, 0.2, 0.2, 0.2, 0.5);
    }
}
