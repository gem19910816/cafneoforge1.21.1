package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class StelliumHoeItem extends HoeItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 821;
         }

         @Override
         public float getSpeed() {
            return 7.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 0.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 12;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SurvivalInstinctModItems.STEELLIUM.get()));
         }
      }; }

   public StelliumHoeItem() { this(tier()); }

   private StelliumHoeItem(Tier tier) { super(tier, new Properties().attributes(HoeItem.createAttributes(tier, 0, 0.0F))); }
}
