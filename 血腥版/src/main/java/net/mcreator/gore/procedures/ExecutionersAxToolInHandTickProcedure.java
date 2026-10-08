package net.mcreator.gore.procedures;

import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ExecutionersAxToolInHandTickProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null
         && !ItemTagHelper.getBoolean(itemstack, "accustom_the_axe")
         && entity instanceof LivingEntity _entity
         && !_entity.level().isClientSide()) {
         _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 0));
      }
   }
}
