package net.mcreator.gore.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class NeedleItem extends Item {
   public NeedleItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON));
   }
}
