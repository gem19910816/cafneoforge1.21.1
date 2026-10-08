package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class DeformityForAshesOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         double rreapeeater = 0.0;
         if ((entity instanceof Mob _mobEnt ? _mobEnt.getTarget() : null) == null) {
            entity.getPersistentData().putBoolean("messingaround", true);
            entity.getPersistentData().putDouble("messingaroundlimit", 0.0);
         } else if (!entity.getPersistentData().getBoolean("messingaround")) {
            if (entity.getPersistentData().getDouble("messingaroundlimit") == 0.0) {
               entity.getPersistentData().putBoolean("messingaround", true);
            }
         } else {
            if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) instanceof LivingEntity) {
               if (!entity.getPersistentData().getBoolean("spyonplayer")) {
                  entity.setSilent(false);
               } else {
                  entity.setSilent(true);
               }

               if (!entity.getPersistentData().getBoolean("spyonplayer") && Math.random() < 0.01) {
                  entity.getPersistentData().putBoolean("spyonplayer", true);
               }

               if (entity.getPersistentData().getBoolean("spyonplayer")) {
                  entity.lookAt(
                     Anchor.EYES,
                     new Vec3(
                        (entity instanceof Mob _mobEntxxxx ? _mobEntxxxx.getTarget() : null).getX(),
                        (entity instanceof Mob _mobEntxxx ? _mobEntxxx.getTarget() : null).getY(),
                        (entity instanceof Mob _mobEntxx ? _mobEntxx.getTarget() : null).getZ()
                     )
                  );
                  if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 254, false, false));
                  }

                  entity.getPersistentData().putDouble("spyonplayerEndTimer", entity.getPersistentData().getDouble("spyonplayerEndTimer") + 1.0);
               }

               if (entity.getPersistentData().getDouble("spyonplayerEndTimer") == 200.0) {
                  entity.getPersistentData().putDouble("spyonplayerEndTimer", 0.0);
                  entity.getPersistentData().putBoolean("spyonplayer", false);
               }
            } else {
               entity.getPersistentData().putBoolean("spyonplayer", false);
               entity.getPersistentData().putDouble("spyonplayerEndTimer", 0.0);
            }

            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(9.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator == (entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null)) {
                  entity.getPersistentData().putBoolean("messingaround", false);
               }
            }

            if (entity.getPersistentData().getDouble("messingaroundlimit") == 0.0) {
               if (Math.random() < 0.2) {
                  entity.getPersistentData().putBoolean("messingaround", false);
               }

               for (int index0 = 0; index0 < 2; index0++) {
                  if (++rreapeeater == 1.0) {
                     entity.getPersistentData().putDouble("MessingAroundLimit_Posibilities", (double)Mth.nextInt(RandomSource.create(), 1, 5));
                  }

                  if (rreapeeater == 2.0) {
                     if (entity.getPersistentData().getDouble("MessingAroundLimit_Posibilities") == 1.0) {
                        entity.getPersistentData().putDouble("MessingAroundLimitNumber", 460.0);
                     }

                     if (entity.getPersistentData().getDouble("MessingAroundLimit_Posibilities") == 2.0) {
                        entity.getPersistentData().putDouble("MessingAroundLimitNumber", 900.0);
                     }

                     if (entity.getPersistentData().getDouble("MessingAroundLimit_Posibilities") == 3.0) {
                        entity.getPersistentData().putDouble("MessingAroundLimitNumber", 1660.0);
                     }

                     if (entity.getPersistentData().getDouble("MessingAroundLimit_Posibilities") == 4.0) {
                        entity.getPersistentData().putDouble("MessingAroundLimitNumber", 2400.0);
                     }

                     if (entity.getPersistentData().getDouble("MessingAroundLimit_Posibilities") == 5.0) {
                        entity.getPersistentData().putDouble("MessingAroundLimitNumber", 3000.0);
                     }
                  }
               }
            }

            entity.getPersistentData().putDouble("messingaroundlimit", entity.getPersistentData().getDouble("messingaroundlimit") + 1.0);
            if (entity.getPersistentData().getDouble("messingaroundlimit") == entity.getPersistentData().getDouble("MessingAroundLimitNumber")) {
               entity.getPersistentData().putBoolean("messingaround", false);
            }
         }

         if ((entity instanceof Mob _mobEntx ? _mobEntx.getTarget() : null) != null) {
            if (!entity.getPersistentData().getBoolean("messingaround")) {
               if (entity.getPersistentData().getDouble("scream tick") == 0.0 && world instanceof Level _level) {
                  if (!_level.isClientSide()) {
                     _level.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.deformity_for_ashes.scream")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.0F
                     );
                  } else {
                     _level.playLocalSound(
                        x,
                        y,
                        z,
                        (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:entity.deformity_for_ashes.scream")),
                        SoundSource.HOSTILE,
                        4.0F,
                        1.0F,
                        false
                     );
                  }
               }

               entity.getPersistentData().putDouble("scream tick", entity.getPersistentData().getDouble("scream tick") + 1.0);
               if (entity.getPersistentData().getDouble("scream tick") == 240.0) {
                  entity.getPersistentData().putDouble("scream tick", 0.0);
               }
            }
         } else {
            entity.getPersistentData().putDouble("scream tick", 0.0);
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (world instanceof ServerLevel _levelx) {
                  _levelx.getServer()
                     .getCommands()
                     .performPrefixedCommand(
                        new CommandSourceStack(
                              CommandSource.NULL,
                              new Vec3(entityiteratorx.getX(), entityiteratorx.getY(), entityiteratorx.getZ()),
                              Vec2.ZERO,
                              _levelx,
                              4,
                              "",
                              Component.literal(""),
                              _levelx.getServer(),
                              null
                           )
                           .withSuppressedOutput(),
                        "/stopsound @a[distance...1] hostile gore_edition:entity.deformity_for_ashes.scream"
                     );
               }
            }
         }

         if (entity.isInWaterOrBubble()) {
            entity.setNoGravity(true);
         } else {
            entity.setNoGravity(false);
         }
      }
   }
}
