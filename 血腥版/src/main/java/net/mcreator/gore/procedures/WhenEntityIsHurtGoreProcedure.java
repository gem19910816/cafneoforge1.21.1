package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class WhenEntityIsHurtGoreProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_withered")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_blaze")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_slime")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_magma_cube")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_iron_golem")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_guardian")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_elder_guardian")))
            && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:the_flesh_eaters")))
            && !(entity instanceof Player)
            && !(entity instanceof ServerPlayer)) {
            GenericHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))) {
            SpiderHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))) {
            EnderHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))) {
            SpectralHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))) {
            WardenHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))) {
            BeeHurtBloodConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))) {
            SkeletonHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_withered")))) {
            WitherSkeletonHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_blaze")))) {
            BlazeHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_slime")))) {
            SlimeHurtSlimeConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_magma_cube")))) {
            MagmaCubeHurtMagmaCubeConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_iron_golem")))) {
            IronGolemHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_guardian")))) {
            GuardianHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }

         if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_elder_guardian")))) {
            ElderGuardianHurtDustConfigProcedure.execute(world, x, y, z, entity, amount);
         }
      }
   }
}
