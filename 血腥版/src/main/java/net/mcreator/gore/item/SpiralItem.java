package net.mcreator.gore.item;

import net.mcreator.gore.procedures.SpiralRightclickedProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class SpiralItem extends Item {
   public SpiralItem() {
      super(new Properties().durability(396).rarity(Rarity.EPIC));
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      SpiralRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, (ItemStack)ar.getObject());
      return ar;
   }
}
