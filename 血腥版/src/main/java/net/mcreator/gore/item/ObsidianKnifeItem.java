package net.mcreator.gore.item;

import net.mcreator.gore.procedures.ObsidianKnifeLivingEntityIsHitWithItemProcedure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ObsidianKnifeItem extends Item {
   public ObsidianKnifeItem() {
      super(
         new Properties()
            .attributes(
               ItemAttributeModifiers.builder()
                  .add(
                     Attributes.ATTACK_DAMAGE,
                     new AttributeModifier(ResourceLocation.fromNamespaceAndPath("gore_edition", "item_attack_damage"), 4.0, Operation.ADD_VALUE),
                     EquipmentSlotGroup.MAINHAND
                  )
                  .add(
                     Attributes.ATTACK_SPEED,
                     new AttributeModifier(ResourceLocation.fromNamespaceAndPath("gore_edition", "item_attack_speed"), -2.4, Operation.ADD_VALUE),
                     EquipmentSlotGroup.MAINHAND
                  )
                  .build()
            )
            .stacksTo(16)
            .rarity(Rarity.COMMON)
      );
   }

   public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
      ObsidianKnifeLivingEntityIsHitWithItemProcedure.execute(entity.level(), entity, sourceentity, itemstack);
      return retval;
   }
}
