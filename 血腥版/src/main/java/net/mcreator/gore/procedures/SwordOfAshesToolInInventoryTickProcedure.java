package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;

public class SwordOfAshesToolInInventoryTickProcedure {
   public static void execute(LevelAccessor world, Entity entity) {
      if (entity != null
         && (world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))
         && (!(entity instanceof LivingEntity _livEnt3) || !_livEnt3.hasEffect(MobEffects.NIGHT_VISION))
         && entity instanceof LivingEntity _entity
         && !_entity.level().isClientSide()) {
         _entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 40, 0));
      }
   }
}
