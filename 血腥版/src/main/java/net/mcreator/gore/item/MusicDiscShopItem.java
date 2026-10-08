package net.mcreator.gore.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class MusicDiscShopItem extends Item {
   public MusicDiscShopItem() {
      super(
         new Properties()
            .stacksTo(1)
            .rarity(Rarity.RARE)
            .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath("gore_edition", "music_disc_shop")))
      );
   }
}
