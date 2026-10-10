package net.mcreator.survivalinstinct.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class BaseballBatSwordItem extends SwordItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 80;
         }

         @Override
         public float getSpeed() {
            return 4.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 1.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_WOODEN_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 15;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemTags.create(ResourceLocation.parse("minecraft:planks")));
         }
      }; }

   public BaseballBatSwordItem() { this(tier()); }

   private BaseballBatSwordItem(Tier tier) { super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 3, -2.2F))); }
}
