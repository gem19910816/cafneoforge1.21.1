package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class GreataxeStopSignItem extends SwordItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 210;
         }

         @Override
         public float getSpeed() {
            return 4.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 3.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 6;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SurvivalInstinctModItems.ALUMINIUM.get()));
         }
      }; }

   public GreataxeStopSignItem() { this(tier()); }

   private GreataxeStopSignItem(Tier tier) { super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 3, -3.0F))); }
}
