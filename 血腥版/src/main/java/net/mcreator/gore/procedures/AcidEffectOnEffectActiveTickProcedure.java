package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class AcidEffectOnEffectActiveTickProcedure {
   public static void execute(LevelAccessor world, Entity entity, double amplifier) {
      if (entity != null) {
         entity.hurt(
            new DamageSource(
               world.registryAccess()
                  .registryOrThrow(Registries.DAMAGE_TYPE)
                  .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("gore_edition:acid_damage")))
            ),
            (float)(amplifier + 1.0)
         );
      }
   }
}
