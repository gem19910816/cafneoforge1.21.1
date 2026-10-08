package net.mcreator.gore.item;

import net.mcreator.gore.procedures.SpectralExecutionerAxeGiftItemInInventoryTickProcedure;
import net.mcreator.gore.procedures.SpectralExecutionerAxeGiftRightclickedProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class SpectralExecutionerAxeGiftItem extends Item {
   public SpectralExecutionerAxeGiftItem() {
      super(new Properties().durability(5).rarity(Rarity.COMMON));
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      SpectralExecutionerAxeGiftRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, (ItemStack)ar.getObject());
      return ar;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      SpectralExecutionerAxeGiftItemInInventoryTickProcedure.execute(entity, itemstack);
   }
}
