package net.mcreator.survivalinstinct.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class PoliceBatonMaceItem extends SwordItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 342;
         }

         @Override
         public float getSpeed() {
            return 4.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 2.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 16;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemTags.create(ResourceLocation.parse("minecraft:planks")));
         }
      }; }

   public PoliceBatonMaceItem() { this(tier()); }

   private PoliceBatonMaceItem(Tier tier) { super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 3, -2.0F))); }
}
