package net.mcreator.gore.item;

import java.util.List;
import net.mcreator.gore.procedures.SwordOfAshesLivingEntityIsHitWithToolProcedure;
import net.mcreator.gore.procedures.SwordOfAshesToolInInventoryTickProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class SwordOfAshesItem extends SwordItem {
   private static final Tier TIER = new Tier() {
      public int getUses() {
         return 221;
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

   public SwordOfAshesItem() {
      super(TIER, new Properties().attributes(SwordItem.createAttributes(TIER, 3, -3.0F)));
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      SwordOfAshesLivingEntityIsHitWithToolProcedure.execute(entity);
      return retval;
   }

   public void appendHoverText(ItemStack itemstack, TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.literal("§2Gives vulnerability III to ashes creatures"));
      list.add(Component.literal("§2You can across Ashes dimension and Overworld easily"));
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      if (selected) {
         SwordOfAshesToolInInventoryTickProcedure.execute(world, entity);
      }
   }
}
