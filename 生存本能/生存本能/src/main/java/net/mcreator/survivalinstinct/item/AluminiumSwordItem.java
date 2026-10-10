package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class AluminiumSwordItem extends SwordItem {
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
            return 2.0F;
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

   public AluminiumSwordItem() { this(tier()); }

   private AluminiumSwordItem(Tier tier) { super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 3, -2.35F))); }
}
