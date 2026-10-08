package net.mcreator.gore.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class CordycepsFungusItem extends Item {
   public CordycepsFungusItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }
}
