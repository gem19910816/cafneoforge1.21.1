package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class ThreadItem extends Item {
   public ThreadItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.item_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.it_is_used_to_repair_armor"));
   }
}
