package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.mcreator.survivalinstinct.procedures.BleedingHitWeaponProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class GlaiveCircularSawItem extends AxeItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 190;
         }

         @Override
         public float getSpeed() {
            return 10.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 8.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 2;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SurvivalInstinctModItems.BATTERIES.get()));
         }
      }; }

   public GlaiveCircularSawItem() { this(tier()); }

   private GlaiveCircularSawItem(Tier tier) { super(tier, new Properties().attributes(AxeItem.createAttributes(tier, 1.0F, -3.1F))); }

   @Override
   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      BleedingHitWeaponProcedure.execute(entity);
      return retval;
   }
}
