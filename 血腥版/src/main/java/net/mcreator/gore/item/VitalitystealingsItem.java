package net.mcreator.gore.item;

import net.mcreator.gore.procedures.VitalityStealingsItemInInventoryTickProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class VitalitystealingsItem extends Item {
   public VitalitystealingsItem() {
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
                     new AttributeModifier(ResourceLocation.fromNamespaceAndPath("gore_edition", "item_attack_speed"), -2.4, Operation.ADD_VALUE),
                     EquipmentSlotGroup.MAINHAND
                  )
                  .build()
            )
            .durability(131)
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
      return true;
   }

   public int getEnchantmentValue() {
      return 2;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      VitalityStealingsItemInInventoryTickProcedure.execute(entity, itemstack);
   }
}
