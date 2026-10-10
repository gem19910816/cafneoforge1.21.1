package net.mcreator.survivalinstinct.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class CircularSawItem extends Item {
   public CircularSawItem() {
      super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
   }

   @Override
   public net.minecraft.world.item.component.ItemAttributeModifiers getDefaultAttributeModifiers() {
      return net.minecraft.world.item.component.ItemAttributeModifiers.builder()
        .add(Attributes.ATTACK_DAMAGE,new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID,4.0,Operation.ADD_VALUE),net.minecraft.world.entity.EquipmentSlotGroup.MAINHAND)
        .add(Attributes.ATTACK_SPEED,new AttributeModifier(Item.BASE_ATTACK_SPEED_ID,-2.4,Operation.ADD_VALUE),net.minecraft.world.entity.EquipmentSlotGroup.MAINHAND).build();
   }
}
