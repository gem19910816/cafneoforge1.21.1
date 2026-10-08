package net.mcreator.gore.item;

import net.mcreator.gore.procedures.ExecutionersAxLivingEntityIsHitWithToolProcedure;
import net.mcreator.gore.procedures.ExecutionersAxRightclickedProcedure;
import net.mcreator.gore.procedures.ExecutionersAxToolInHandTickProcedure;
import net.mcreator.gore.procedures.ExecutionersAxToolInInventoryTickProcedure;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ExecutionersAxItem extends AxeItem {
   private static final Tier TIER_AXE = new Tier() {
      public int getUses() {
         return 9;
      }

      public float getSpeed() {
         return 0.0F;
      }

      public float getAttackDamageBonus() {
         return 18.0F;
      }

      public int getEnchantmentValue() {
         return 30;
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

   public ExecutionersAxItem() {
      super(TIER_AXE, new Properties().attributes(AxeItem.createAttributes(TIER_AXE, 1.0F, -3.4F)));
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      ExecutionersAxLivingEntityIsHitWithToolProcedure.execute(entity.level(), entity, sourceentity, itemstack);
      return retval;
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      ExecutionersAxRightclickedProcedure.execute(entity, (ItemStack)ar.getObject());
      return ar;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      if (selected) {
         ExecutionersAxToolInHandTickProcedure.execute(entity, itemstack);
      }

      ExecutionersAxToolInInventoryTickProcedure.execute(entity, itemstack);
   }
}
