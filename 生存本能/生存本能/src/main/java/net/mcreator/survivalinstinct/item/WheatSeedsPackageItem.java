package net.mcreator.survivalinstinct.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class WheatSeedsPackageItem extends Item {
   public WheatSeedsPackageItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }
}
