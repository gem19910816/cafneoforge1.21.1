package net.mcreator.survivalinstinct.procedures;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class GreenNightVisionGUIDisplayOverlayIngameProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : (entity instanceof LivingEntity _entGetArmorxxx ? _entGetArmorxxx.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
               == SurvivalInstinctModItems.NIGHT_VISION_GOGGLES_HELMET.get()
            || (entity instanceof LivingEntity _entGetArmorxx ? _entGetArmorxx.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
               == SurvivalInstinctModItems.GREEN_HUNTER_HELMET.get()
            || (entity instanceof LivingEntity _entGetArmorx ? _entGetArmorx.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
               == SurvivalInstinctModItems.DESERT_HUNTER_HELMET.get()
            || (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
               == SurvivalInstinctModItems.BLACK_HUNTER_HELMET.get();
   }
}
