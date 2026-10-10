package net.mcreator.survivalinstinct.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class ExoComponentItem extends Item {
   public ExoComponentItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
   }
}
