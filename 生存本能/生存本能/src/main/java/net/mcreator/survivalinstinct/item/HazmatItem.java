package net.mcreator.survivalinstinct.item;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.mcreator.survivalinstinct.client.model.Modelhazmat;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

public abstract class HazmatItem extends ArmorItem {
   public HazmatItem(Type type, Properties properties) {
      super(net.mcreator.survivalinstinct.init.SurvivalInstinctModArmorMaterials.HAZMAT, type, properties.durability(type.getDurability(26)));
   }

   public static class Boots extends HazmatItem {
      public Boots() {
         super(Type.BOOTS, new Properties());
      }

      @Override
      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @OnlyIn(Dist.CLIENT)
               @Override
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = net.mcreator.survivalinstinct.client.ArmorModelCache.model("HazmatItem.java:0", () -> new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "left_leg",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).left_shoe,
                           "right_leg",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).right_shoe,
                           "head",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "hat",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "body",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_arm",
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
         list.add(Component.translatable("tooltip.survival_instinct.bonus_armor_set"));
         list.add(Component.translatable("tooltip.survival_instinct.immunity_to_all_negative_effects_vanilla"));
      }

      @Override
      public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
         return ResourceLocation.parse("survival_instinct:textures/entities/hazmat.png");
      }
   }

   public static class Chestplate extends HazmatItem {
      public Chestplate() {
         super(Type.CHESTPLATE, new Properties());
      }

      @Override
      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @OnlyIn(Dist.CLIENT)
               @Override
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = net.mcreator.survivalinstinct.client.ArmorModelCache.model("HazmatItem.java:1", () -> new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "body",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).body,
                           "left_arm",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).left_arm,
                           "right_arm",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).right_arm,
                           "head",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "hat",
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
         list.add(Component.translatable("tooltip.survival_instinct.bonus_armor_set"));
         list.add(Component.translatable("tooltip.survival_instinct.immunity_to_all_negative_effects_vanilla"));
      }

      @Override
      public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
         return ResourceLocation.parse("survival_instinct:textures/entities/hazmat.png");
      }
   }

   public static class Helmet extends HazmatItem {
      public Helmet() {
         super(Type.HELMET, new Properties());
      }

      @Override
      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @Override
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = net.mcreator.survivalinstinct.client.ArmorModelCache.model("HazmatItem.java:2", () -> new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "head",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).head,
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
         list.add(Component.translatable("tooltip.survival_instinct.bonus_armor_set"));
         list.add(Component.translatable("tooltip.survival_instinct.immunity_to_all_negative_effects_vanilla"));
      }

      @Override
      public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
         return ResourceLocation.parse("survival_instinct:textures/entities/hazmat.png");
      }
   }

   public static class Leggings extends HazmatItem {
      public Leggings() {
         super(Type.LEGGINGS, new Properties());
      }

      @Override
      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @OnlyIn(Dist.CLIENT)
               @Override
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = net.mcreator.survivalinstinct.client.ArmorModelCache.model("HazmatItem.java:3", () -> new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "left_leg",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).left_leg,
                           "right_leg",
                           (new Modelhazmat(net.mcreator.survivalinstinct.client.ArmorModelCache.root(Modelhazmat.LAYER_LOCATION))).right_leg,
                           "head",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "hat",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "body",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_arm",
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
         list.add(Component.translatable("tooltip.survival_instinct.bonus_armor_set"));
         list.add(Component.translatable("tooltip.survival_instinct.immunity_to_all_negative_effects_vanilla"));
      }

      @Override
      public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
         return ResourceLocation.parse("survival_instinct:textures/entities/hazmat.png");
      }
   }
}
