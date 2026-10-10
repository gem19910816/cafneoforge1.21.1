package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.mcreator.survivalinstinct.procedures.WhiskeyPlayerFinishesUsingItemProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class WhiskeyItem extends Item {
   public WhiskeyItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON).food(new Builder().nutrition(2).saturationModifier(0.4F).alwaysEdible().build()));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.DRINK;
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.item_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.absorption_ii_for_60_seconds_and_nausea_i_for_8_seconds"));
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = new ItemStack(SurvivalInstinctModItems.EMPTY_BOTTLE.get());
      super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      WhiskeyPlayerFinishesUsingItemProcedure.execute(entity);
      if (itemstack.isEmpty()) {
         return retval;
      } else {
         if (entity instanceof Player player && !player.getAbilities().instabuild && !player.getInventory().add(retval)) {
            player.drop(retval, false);
         }

         return itemstack;
      }
   }
}
