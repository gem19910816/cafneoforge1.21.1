package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class SmithingTemplatePrototypeItem extends Item {
   public SmithingTemplatePrototypeItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.juggernaut_upgrade"));
      list.add(Component.literal(""));
      list.add(Component.translatable("tooltip.survival_instinct.applies_to"));
      list.add(Component.translatable("tooltip.survival_instinct.juggernaut_armor"));
      list.add(Component.translatable("tooltip.survival_instinct.ingredients"));
      list.add(Component.translatable("tooltip.survival_instinct.exoskeleton_armor"));
   }
}
