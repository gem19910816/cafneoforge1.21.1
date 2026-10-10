package net.mcreator.survivalinstinct.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class ClothItem extends Item {
   public ClothItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }
}
