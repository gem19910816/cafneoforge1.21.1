package net.mcreator.gore.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;

public class AshesTestAttackAllWithoutAshesProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : !entity.isShiftKeyDown() || !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")));
   }
}
