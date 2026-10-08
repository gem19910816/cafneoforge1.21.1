package net.mcreator.gore.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class AmethystWaveItem extends Item {
   public AmethystWaveItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.EPIC));
   }
}
