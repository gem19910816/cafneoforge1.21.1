package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionConfigurationFileConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class WhenEntityDeathGoreProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null && !entity.getPersistentData().getBoolean("ge_cancel_die_particles")) {
         if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_withered")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_blaze")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_slime")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_magma_cube")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_iron_golem")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_guardian")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_elder_guardian")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:the_flesh_eaters")))
            && !(entity instanceof ServerPlayer)
            && !(entity instanceof Player)) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               GenericDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  GenericBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  GenericBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  GenericBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  GenericDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               SpiderDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  SpiderBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  SpiderBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  SpiderBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  SpiderDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  EnderBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  EnderBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  EnderBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  EnderDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               SpectralDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  SpectralBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  SpectralBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  SpectralBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  SpectralDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               WardenDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  WardenBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  WardenBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  WardenBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  WardenDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               BeeDeathBloodConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  BeeBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  BeeBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  BeeBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  BeeDeathBloodConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  SkeletonBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  SkeletonBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  SkeletonBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  SkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_withered")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               WitherSkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  WitherSkeletonBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  WitherSkeletonBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  WitherSkeletonBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  WitherSkeletonDeathPiecesConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_blaze")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               BlazeDeathDustConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  BlazeBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  BlazeBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  BlazeBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  BlazeDeathDustConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_slime")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  SlimeBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  SlimeBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  SlimeBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  SlimeDeathSlimeConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_magma_cube")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               MagmaCubeDeathMagmaCubeConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  MagmaCubeBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  MagmaCubeBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  MagmaCubeBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  MagmaCubeDeathMagmaCubeConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_iron_golem")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               IronGolemDeathDustConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  IronGolemBossDeathRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  IronGolemBossDeathFastRepeatProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  IronGolemBossDeathBigExplodeProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  IronGolemDeathDustConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_guardian")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  GuardianDeathDustRepeatConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  GuardianDeathDustFastRepeatConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  GuardianDeathDustBigExplodeConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  GuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_elder_guardian")))) {
            if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1.0F) >= 50.0F)) {
               ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
            } else {
               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("repeat")) {
                  ElderGuardianDeathDustRepeatConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("fast repeat")) {
                  ElderGuardianDeathDustFastRepeatConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("big explode")) {
                  ElderGuardianDeathDustBigExplodeConfigProcedure.execute(world, x, y, z, entity);
               }

               if (((String)GoreEditionConfigurationFileConfiguration.BOSS_DEATH_MODE.get()).equals("none")) {
                  ElderGuardianDeathDustConfigProcedure.execute(world, x, y, z, entity);
               }
            }
         }
      }
   }
}
