package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.mcreator.survivalinstinct.procedures.BleedingHitWeaponProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class LanceWithBrokenBottleItem extends SwordItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 42;
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
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_WOODEN_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 2;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemTags.create(ResourceLocation.parse("c:glass_blocks")));
         }
      }; }

   public LanceWithBrokenBottleItem() { this(tier()); }

   private LanceWithBrokenBottleItem(Tier tier) { super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 3, -2.2F))); }

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
