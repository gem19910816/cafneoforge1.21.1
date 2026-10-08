package net.mcreator.gore.procedures;

import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class SwordOfAshesLivingEntityIsHitWithToolProcedure {
   public static void execute(Entity entity) {
      if (entity != null
         && entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
         && entity instanceof LivingEntity _entity
         && !_entity.level().isClientSide()) {
         _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.VULNERABILITY, 120, 2));
      }
   }
}
