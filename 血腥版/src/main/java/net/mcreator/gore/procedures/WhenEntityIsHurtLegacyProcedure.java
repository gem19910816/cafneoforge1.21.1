package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class WhenEntityIsHurtLegacyProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double amount) {
      if (entity != null) {
         if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:legacy_solid_entities")))) {
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
               GenericHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))) {
               SpiderHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))) {
               EnderHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))) {
               SpectralHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))) {
               WardenHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))) {
               BeeHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               LegacyHurtBloodSoundsProcedure.execute(world, entity, amount);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))) {
               SkeletonHurtLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity, amount);
               SkeletonHurtDustSoundsProcedure.execute(world, entity, amount);
            }
         }

         if (entity instanceof ServerPlayer) {
            WhenPlayerIsHurtLegacyProcedure.execute(world, x, y, z, entity, amount);
         }
      }
   }
}
