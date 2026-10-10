package net.mcreator.survivalinstinct.item;

import net.mcreator.survivalinstinct.procedures.MREOpenProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class MREItem extends Item {
   public MREItem() {
      super(new Properties().stacksTo(16).rarity(Rarity.COMMON));
   }

   @Override
   public UseAnim getUseAnimation(ItemStack itemstack) {
      return UseAnim.EAT;
   }

   @Override
   public int getUseDuration(ItemStack itemstack, net.minecraft.world.entity.LivingEntity entity) {
      return 42;
   }

   @Override
   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      MREOpenProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
      return ar;
   }
}
