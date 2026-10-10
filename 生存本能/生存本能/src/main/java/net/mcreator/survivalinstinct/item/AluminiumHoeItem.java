package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class AluminiumHoeItem extends HoeItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 194;
         }

         @Override
         public float getSpeed() {
            return 11.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 0.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_STONE_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 18;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SurvivalInstinctModItems.ALUMINIUM.get()));
         }
      }; }

   public AluminiumHoeItem() { this(tier()); }

   private AluminiumHoeItem(Tier tier) { super(tier, new Properties().attributes(HoeItem.createAttributes(tier, 0, 0.5F))); }
}
