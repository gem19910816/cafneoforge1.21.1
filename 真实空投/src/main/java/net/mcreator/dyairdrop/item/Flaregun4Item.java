package net.mcreator.dyairdrop.item;


import net.mcreator.dyairdrop.procedures.FlaregunlootsetProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class Flaregun4Item extends Item {
   public Flaregun4Item() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
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
