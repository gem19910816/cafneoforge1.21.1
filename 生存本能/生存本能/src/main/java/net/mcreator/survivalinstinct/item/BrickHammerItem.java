package net.mcreator.survivalinstinct.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class BrickHammerItem extends PickaxeItem {
   private static Tier tier() { return new Tier() {
         @Override
         public int getUses() {
            return 61;
         }

         @Override
         public float getSpeed() {
            return 5.0F;
         }

         @Override
         public float getAttackDamageBonus() {
            return 4.3F;
         }

         @Override
         public net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> getIncorrectBlocksForDrops() { return net.minecraft.tags.BlockTags.INCORRECT_FOR_STONE_TOOL; }

         @Override
         public int getEnchantmentValue() {
            return 18;
         }

         @Override
         public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(Items.BRICK));
         }
      }; }

   public BrickHammerItem() { this(tier()); }

   private BrickHammerItem(Tier tier) { super(tier, new Properties().attributes(PickaxeItem.createAttributes(tier, 1, -2.7F))); }

   @Override
   public boolean hasCraftingRemainingItem(ItemStack stack) {
      return true;
   }

   @Override
   public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
      ItemStack retval = new ItemStack(this);
      retval.setDamageValue(itemstack.getDamageValue() + 1);
      return retval.getDamageValue() >= retval.getMaxDamage() ? ItemStack.EMPTY : retval;
   }

   @Override
   public boolean isRepairable(ItemStack itemstack) {
      return false;
   }

   @Override
   public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, level, list, flag);
      list.add(Component.translatable("tooltip.survival_instinct.tool_utility"));
      list.add(Component.translatable("tooltip.survival_instinct.you_can_break_tools_and_items_to_obtain_useful_scrap"));
   }
}
