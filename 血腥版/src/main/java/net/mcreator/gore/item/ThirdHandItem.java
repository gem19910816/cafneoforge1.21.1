package net.mcreator.gore.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class ThirdHandItem extends Item {
   public ThirdHandItem() {
      super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
   }
}
