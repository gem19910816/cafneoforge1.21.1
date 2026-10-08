package net.mcreator.dyairdrop.item;

import java.util.List;
import net.mcreator.dyairdrop.procedures.FlaregunlootsetProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class Flaregun5Item extends Item {
   public Flaregun5Item() {
      super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
   }

   public int getUseDuration(ItemStack itemstack) {
      return 72000;
   }

   public void appendHoverText(ItemStack itemstack, Item.TooltipContext world, List<Component> list, TooltipFlag flag) {
      super.appendHoverText(itemstack, world, list, flag);
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      FlaregunlootsetProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, (ItemStack)ar.getObject());
      return ar;
   }
}
