package net.mcreator.gore.item;

import net.mcreator.gore.procedures.CordycepsScytheCannotBeEnchantedProcedure;
import net.mcreator.gore.procedures.CordycepsScytheLivingEntityIsHitWithToolProcedure;
import net.mcreator.gore.procedures.NeedlecordycepsscytherightclickedonairProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CordycepsScytheItem extends Item {
   public CordycepsScytheItem() {
      super(
         new Properties()
            .attributes(
               ItemAttributeModifiers.builder()
                  .add(
                     Attributes.ATTACK_DAMAGE,
                     new AttributeModifier(ResourceLocation.fromNamespaceAndPath("gore_edition", "item_attack_damage"), 1.0, Operation.ADD_VALUE),
                     EquipmentSlotGroup.MAINHAND
                  )
                  .add(
                     Attributes.ATTACK_SPEED,
                     new AttributeModifier(ResourceLocation.fromNamespaceAndPath("gore_edition", "item_attack_speed"), -2.0, Operation.ADD_VALUE),
                     EquipmentSlotGroup.MAINHAND
                  )
                  .build()
            )
            .durability(101)
      );
   }

   public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
      return 1.0F;
   }

   public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
      itemstack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
      return true;
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      itemstack.hurtAndBreak(2, entity, EquipmentSlot.MAINHAND);
      CordycepsScytheLivingEntityIsHitWithToolProcedure.execute(entity, sourceentity, itemstack);
      return true;
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      NeedlecordycepsscytherightclickedonairProcedure.execute(entity);
      return ar;
   }

   public int getEnchantmentValue() {
      return 0;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      CordycepsScytheCannotBeEnchantedProcedure.execute(entity, itemstack);
   }
}
