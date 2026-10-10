package net.mcreator.survivalinstinct.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class SteelliumNuggetItem extends Item {
   public SteelliumNuggetItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }
}
