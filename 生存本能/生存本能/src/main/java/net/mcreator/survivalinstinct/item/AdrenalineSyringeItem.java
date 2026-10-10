package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.procedures.AdrenalineSyringePlayerFinishesUsingItemProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class AdrenalineSyringeItem extends Item {
   public AdrenalineSyringeItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON).food(new Builder().nutrition(0).saturationModifier(0.0F).alwaysEdible().build()));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.BOW;
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 42;
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.item_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.haste_ii_resistance_i_speed_ii_hunger_ii_for_45_seconds"));
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      AdrenalineSyringePlayerFinishesUsingItemProcedure.execute(entity);
      return retval;
   }
}
