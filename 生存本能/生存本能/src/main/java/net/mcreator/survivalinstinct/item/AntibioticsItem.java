package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.procedures.AntibioticsPlayerFinishesUsingItemProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class AntibioticsItem extends Item {
   public AntibioticsItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON).food(new Builder().nutrition(1).saturationModifier(0.3F).alwaysEdible().build()));
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 28;
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.item_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.removes_all_diseases_affecting_the_player"));
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      AntibioticsPlayerFinishesUsingItemProcedure.execute(entity);
      return retval;
   }
}
