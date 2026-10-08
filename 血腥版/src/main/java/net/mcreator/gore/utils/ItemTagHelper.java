package net.mcreator.gore.utils;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;

public class ItemTagHelper {
   public static CompoundTag getOrCreateTag(ItemStack stack) {
      CustomData data = (CustomData)stack.get(DataComponents.CUSTOM_DATA);
      return data != null ? data.copyTag() : new CompoundTag();
   }

   public static void saveTag(ItemStack stack, CompoundTag tag) {
      stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
   }

   public static String getString(ItemStack stack, String key) {
      return getOrCreateTag(stack).getString(key);
   }

   public static void putString(ItemStack stack, String key, String value) {
      CompoundTag tag = getOrCreateTag(stack);
      tag.putString(key, value);
      saveTag(stack, tag);
   }

   public static double getDouble(ItemStack stack, String key) {
      return getOrCreateTag(stack).getDouble(key);
   }

   public static void putDouble(ItemStack stack, String key, double value) {
      CompoundTag tag = getOrCreateTag(stack);
      tag.putDouble(key, value);
      saveTag(stack, tag);
   }

   public static boolean getBoolean(ItemStack stack, String key) {
      return getOrCreateTag(stack).getBoolean(key);
   }

   public static void putBoolean(ItemStack stack, String key, boolean value) {
      CompoundTag tag = getOrCreateTag(stack);
      tag.putBoolean(key, value);
      saveTag(stack, tag);
   }

   public static int getInt(ItemStack stack, String key) {
      return getOrCreateTag(stack).getInt(key);
   }

   public static void putInt(ItemStack stack, String key, int value) {
      CompoundTag tag = getOrCreateTag(stack);
      tag.putInt(key, value);
      saveTag(stack, tag);
   }

   public static boolean damageItem(ItemStack stack, Entity entity) {
      stack.hurtAndBreak(1, entity instanceof LivingEntity le ? le : null, EquipmentSlot.MAINHAND);
      return stack.isEmpty();
   }

   public static int getEnchantLevel(ItemStack stack, ResourceKey<Enchantment> key, LevelAccessor world) {
      Registry<Enchantment> registry = world.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
      Optional<Reference<Enchantment>> holder = registry.getHolder(key);
      return holder.isEmpty() ? 0 : EnchantmentHelper.getItemEnchantmentLevel((Holder)holder.get(), stack);
   }

   public static boolean damageItemAmount(ItemStack stack, int amount, Entity entity) {
      stack.hurtAndBreak(amount, entity instanceof LivingEntity le ? le : null, EquipmentSlot.MAINHAND);
      return stack.isEmpty();
   }
}
