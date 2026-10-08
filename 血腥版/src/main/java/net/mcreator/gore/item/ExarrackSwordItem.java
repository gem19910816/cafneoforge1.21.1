package net.mcreator.gore.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ExarrackSwordItem extends SwordItem {
   private static final Tier TIER = new Tier() {
      public int getUses() {
         return 59;
      }

      public float getSpeed() {
         return 4.0F;
      }

      public float getAttackDamageBonus() {
         return 5.0F;
      }

      public int getEnchantmentValue() {
         return 1;
      }

      public Ingredient getRepairIngredient() {
         return Ingredient.of();
      }

      public TagKey<Block> getIncorrectBlocksForDrops() {
         // 1.20.1 Tier#getLevel() == 0 here; 1.21 replaced getLevel() with this tag.
         // Must not be null: DiggerItem/AxeItem call Tier#createToolProperties at construction.
         return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
      }
   };

   public ExarrackSwordItem() {
      super(TIER, new Properties().attributes(SwordItem.createAttributes(TIER, 3, -3.0F)));
   }
}
