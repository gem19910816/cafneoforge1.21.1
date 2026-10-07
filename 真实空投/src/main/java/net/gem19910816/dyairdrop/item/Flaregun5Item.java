package net.gem19910816.dyairdrop.item;


import net.gem19910816.dyairdrop.procedures.FlaregunlootsetProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class Flaregun5Item extends Item {
   public Flaregun5Item() {
      super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
   }

   public int getUseDuration(ItemStack itemstack, LivingEntity entity) {
      return 72000;
   }

   

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      FlaregunlootsetProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, (ItemStack)ar.getObject());
      return ar;
   }
}
