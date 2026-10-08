package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class WhenEntityIsDeathLegacyExplodeProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, DamageSource damagesource, Entity entity) {
      if (damagesource != null && entity != null) {
         if (!damagesource.is(DamageTypes.IN_FIRE)
            && !damagesource.is(DamageTypes.ON_FIRE)
            && !damagesource.is(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
            && !damagesource.is(DamageTypes.LAVA)
            && !entity.isOnFire()) {
            if (!entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_blaze")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_guardian")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_elder_guardian")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_iron_golem")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_magma_cube")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_slime")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))
               && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_withered")))
               && !(entity instanceof ServerPlayer)) {
               GenericDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_arthropods")))) {
               SpiderDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_enderean")))) {
               EnderDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_spectrum")))) {
               SpectralDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_wardens")))) {
               WardenDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_melliferous")))) {
               BeeDeathLegacyprProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
               LegacyDeathBloodSoundsProcedure.execute(world, entity);
            }

            if (entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_skeletal")))) {
               SkeletonDeathLegacyPrProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
            }
         }

         if (entity instanceof ServerPlayer) {
            WhenPlayerDiesLegacyProcedure.execute(world, x, y + (double)(entity.getBbHeight() / 2.0F), z, entity);
         }
      }
   }
}
