package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.procedures.PlayerEatOrangeProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class OrangeItem extends Item {
   public OrangeItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON).food(new Builder().nutrition(3).saturationModifier(0.6F).build()));
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.item_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.removes_the_effect_of_hunger_and_nausea"));
   }

   @Override
   public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
      ItemStack retval = super.finishUsingItem(itemstack, world, entity);
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      PlayerEatOrangeProcedure.execute(entity);
      return retval;
   }
}
