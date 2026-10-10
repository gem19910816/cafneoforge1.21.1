package net.mcreator.survivalinstinct.item;

import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.mcreator.survivalinstinct.client.model.Modelgas_mask;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.mcreator.survivalinstinct.procedures.GasMaskHelmetTickEventProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

public abstract class GasMaskItem extends ArmorItem {
   public GasMaskItem(Type type, Properties properties) {
      super(net.mcreator.survivalinstinct.init.SurvivalInstinctModArmorMaterials.GAS_MASK, type, properties.durability(type.getDurability(17)));
   }

   public static class Helmet extends GasMaskItem {
      public Helmet() {
         super(Type.HELMET, new Properties());
      }

      @Override
      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @Override
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = net.mcreator.survivalinstinct.client.ArmorModelCache.model("GasMaskItem.java:0", () -> new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "head",
                           (new Modelgas_mask(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelgas_mask.LAYER_LOCATION))).head,
                           "hat",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "body",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_leg",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_leg",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                     )
                  ));
                  armorModel.crouching = living.isShiftKeyDown();
                  armorModel.riding = defaultModel.riding;
                  armorModel.young = living.isBaby();
                  return armorModel;
               }
            }
         );
      }

      @Override
      public void appendHoverText(ItemStack itemstack, net.minecraft.world.item.Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
         super.appendHoverText(itemstack, level, list, flag);
         list.add(Component.translatable("tooltip.survival_instinct.armor_set"));
         list.add(Component.translatable("tooltip.survival_instinct.immunity_to_poison_and_wither"));
      }

      @Override
      public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
         return ResourceLocation.parse("survival_instinct:textures/entities/gas_mask.png");
      }


   }
}
