package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.mcreator.survivalinstinct.procedures.BleedingHitWeaponProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class GlaiveChainsawItem extends AxeItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 230;
         }

         @Override
         public float getSpeed() {
            return 14.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 12.0F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_IRON_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 2;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SurvivalInstinctModItems.GASOLINE_CAN.get()));
         }
      }; }

   public GlaiveChainsawItem() { this(tier()); }

   private GlaiveChainsawItem(Tier tier) { super(tier, new Properties().attributes(AxeItem.createAttributes(tier, 1.0F, -3.3F))); }

   @Override
   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      BleedingHitWeaponProcedure.execute(entity);
      return retval;
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.hit_effect"));
      list.add(Component.translatable("tooltip.survival_instinct.bleeding"));
   }
}
