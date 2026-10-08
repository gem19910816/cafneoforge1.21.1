package net.mcreator.gore.item;

import net.mcreator.gore.procedures.VoodooRabbitItemInHandTickProcedure;
import net.mcreator.gore.procedures.VoodooRabbitLivingEntityIsHitWithItemProcedure;
import net.mcreator.gore.procedures.VoodooRabbitRightclickedProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class VoodooRabbitItem extends Item {
   public VoodooRabbitItem() {
      super(new Properties().durability(9).rarity(Rarity.COMMON));
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      VoodooRabbitRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, (ItemStack)ar.getObject());
      return ar;
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      VoodooRabbitLivingEntityIsHitWithItemProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity, sourceentity, itemstack);
      return retval;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      VoodooRabbitItemInHandTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
   }
}
