package com.scarasol.sona.manager;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.item.IRustItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

import java.util.List;
import java.util.Random;

import static com.scarasol.sona.SonaMod.MODID;

public class RustManager {

    private static final ResourceLocation MAINHAND = ResourceLocation.fromNamespaceAndPath(MODID, "rust_mainhand");
    private static final ResourceLocation HELMET = ResourceLocation.fromNamespaceAndPath(MODID, "rust_helmet");
    private static final ResourceLocation CHESTPLATE = ResourceLocation.fromNamespaceAndPath(MODID, "rust_chestplate");
    private static final ResourceLocation LEGGINGS = ResourceLocation.fromNamespaceAndPath(MODID, "rust_leggings");
    private static final ResourceLocation BOOTS = ResourceLocation.fromNamespaceAndPath(MODID, "rust_boots");

    private static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD};

    public static void putRust(ItemStack itemStack, double rustValue){
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putDouble("RustValue", rustValue));
    }

    public static double getRust(ItemStack itemStack){
         return getTag(itemStack).getDouble("RustValue");
    }

    public static double getRust(Object object){
        if (object instanceof ItemStack itemStack)
            return getRust(itemStack);
        return -1;
    }

    public static void addRust(ItemStack itemStack, double addition) {
        if (addition > 0)
            addition = addition * CommonConfig.RUST_WEIGHT.get().floatValue();
        addActualRust(itemStack, addition);
    }

    public static void addActualRust(ItemStack itemStack, double addition){
        double rust = addition > 0 ? Math.min(100, addition + getRust(itemStack)) : Math.max(0, addition + getRust(itemStack));
        putRust(itemStack, rust);
    }

    public static void putWaxed(ItemStack itemStack, int times){
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putInt("Waxed", times));
    }

    public static int getWaxed(ItemStack itemStack){
        return getTag(itemStack).getInt("Waxed");
    }

    public static void addWaxed(ItemStack itemStack, int addition) {
        int waxed = addition > 0 ? Math.min(CommonConfig.WAX_TIMES.get(), addition + getWaxed(itemStack)) : Math.max(0, addition + getWaxed(itemStack));
        putWaxed(itemStack, waxed);
    }

    public static boolean isWaxed(ItemStack itemStack){
        return getTag(itemStack).getInt("Waxed") > 0;
    }

    private static CompoundTag getTag(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
    }

    public static void rustItem(ItemStack itemStack, LivingEntity livingEntity, EquipmentSlot equipmentSlot){
        if (!canBeRust(itemStack))
            return;
        double rustValue = getRust(itemStack);
        if (rustValue >= 75 && new Random().nextDouble() < (rustValue - 75) / 800) {
            itemStack.hurtAndBreak(9999999, livingEntity, equipmentSlot);
        }
        if (isWaxed(itemStack)){
            if (!CommonConfig.WAX_PERMANENT.get())
                addWaxed(itemStack, -1);
            return;
        }
        rustValue = new Random().nextDouble(0.2, 0.6) + rustValue / 100;
        addRust(itemStack, rustValue);
    }

    public static void rustItem(Object object, LivingEntity livingEntity, EquipmentSlot equipmentSlot){
        if (object instanceof ItemStack itemStack)
            rustItem(itemStack, livingEntity, equipmentSlot);
    }

    public static void addRustAttributeModifier(ItemAttributeModifierEvent event){
        ItemStack itemStack = event.getItemStack();
        if (!canBeRust(itemStack))
            return;
        double rustValue = getRust(itemStack);
        if (rustValue < 50)
            return;
        ResourceLocation modifierId;
        double multiplier;
        if (itemStack.getItem() instanceof TieredItem){
            double value = 1;
            modifierId = MAINHAND;
            if (rustValue >= 75){
                multiplier = -0.5;
            }else {
                multiplier = -0.25;
            }
            for (ItemAttributeModifiers.Entry entry : event.getModifiers()) {
                if (entry.attribute().is(Attributes.ATTACK_DAMAGE) && entry.modifier().operation() == AttributeModifier.Operation.ADD_VALUE) {
                    value += entry.modifier().amount();
                }
            }
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(modifierId, multiplier * value, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        }else if (itemStack.getItem() instanceof ArmorItem armorItem){
            if (rustValue >= 75){
                multiplier = -0.1;
            }else {
                multiplier = -0.05;
            }
            modifierId = switch (armorItem.getEquipmentSlot()){
                case CHEST -> CHESTPLATE;
                case LEGS -> LEGGINGS;
                case FEET -> BOOTS;
                default -> HELMET;
            };
            event.addModifier(Attributes.ARMOR, new AttributeModifier(modifierId, multiplier, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.bySlot(armorItem.getEquipmentSlot()));
        }

    }

    public static boolean canBeRust(Object obj){
        if (obj instanceof ItemStack itemStack)
            return canBeRust(itemStack.getItem());
        return false;
    }

    public static boolean canBeRust(ItemStack itemStack){
        return canBeRust(itemStack.getItem());
    }

    public static boolean canBeRust(Item item){
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        if (key == null)
            return false;
        if (CommonConfig.findIndex(key.toString(), CommonConfig.RUST_WHITELIST.get()) != -1)
            return false;
        if (item instanceof TieredItem tieredItem && tieredItem.getTier() == Tiers.IRON){
            return true;
        }
        if (item instanceof ArmorItem armorItem && (armorItem.getMaterial() == ArmorMaterials.IRON || armorItem.getMaterial() == ArmorMaterials.CHAIN)){
            return true;
        }
        return CommonConfig.findIndex(key.toString(), CommonConfig.RUST_BLACKLIST.get()) != -1;
    }

    public static void tooltipInsert(List<Component> toolTip, ItemStack itemStack) {
        double rustValue = getRust(itemStack);
        if (rustValue < 50){
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rust.brand_new").withStyle(ChatFormatting.DARK_GREEN));
        }else if (rustValue < 75){
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rust.slightly_rusted").withStyle(ChatFormatting.YELLOW));
            if (itemStack.getItem() instanceof TieredItem)
                toolTip.add(Math.min(7, toolTip.size()), Component.literal("-5% " + Component.translatable("tooltip.sona.rust.tool_rust").getString()).withStyle(ChatFormatting.RED));
        }else {
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rust.heavily_rusted").withStyle(ChatFormatting.RED));
            if (itemStack.getItem() instanceof TieredItem)
                toolTip.add(Math.min(7, toolTip.size()), Component.literal("-15% " + Component.translatable("tooltip.sona.rust.tool_rust").getString()).withStyle(ChatFormatting.RED));
        }
        if (isWaxed(itemStack)){
            if (CommonConfig.WAX_PERMANENT.get()){
                toolTip.add(Math.min(2, toolTip.size()), Component.translatable("tooltip.sona.rust.waxed").withStyle(ChatFormatting.DARK_GREEN));
            }else {
                toolTip.add(Math.min(2, toolTip.size()), Component.literal(Component.translatable("tooltip.sona.rust.waxed_remaining").getString() + getWaxed(itemStack)).withStyle(ChatFormatting.DARK_GREEN));
            }
        }
    }

    public static void onAttacked(LivingEntity livingEntity) {
        EquipmentSlot equipmentSlot = ARMOR_SLOTS[livingEntity.getRandom().nextInt(4)];
        ItemStack itemStack = livingEntity.getItemBySlot(equipmentSlot);
        if (canBeRust(itemStack)) {
            rustItem(itemStack, livingEntity, equipmentSlot);
        }
    }

    public static boolean wax(ItemStack itemStack, ItemStack waxItem, LivingEntity livingEntity){
        if (!canBeRust(itemStack))
            return false;
        ResourceLocation waxKey = BuiltInRegistries.ITEM.getKey(waxItem.getItem());
        int index = waxKey == null ? -1 : CommonConfig.findIndex(waxKey.toString(), CommonConfig.WAX_ITEM.get());
        if (index == -1)
            return false;
        String[] waxInfo = CommonConfig.WAX_ITEM.get().get(index).split(",");
        if (waxInfo.length < 2)
            return false;
        int damage = Integer.parseInt(waxInfo[1].trim());
        if (consume(waxItem, damage, livingEntity)){
            putWaxed(itemStack, CommonConfig.WAX_TIMES.get());
            rustParticle(livingEntity.level(), new Random(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), true);
            return true;
        }
        return false;
    }

    public static boolean removalRust(ItemStack itemStack, ItemStack removalItem, LivingEntity livingEntity){
        if (!canBeRust(itemStack))
            return false;
        ResourceLocation removalKey = BuiltInRegistries.ITEM.getKey(removalItem.getItem());
        int index = removalKey == null ? -1 : CommonConfig.findIndex(removalKey.toString(), CommonConfig.RUST_REMOVE_ITEM.get());
        if (index == -1)
            return false;
        String[] removalInfo = CommonConfig.RUST_REMOVE_ITEM.get().get(index).split(",");
        if (removalInfo.length < 3)
            return false;
        int damage = Integer.parseInt(removalInfo[2].trim());
        double removal = -Double.parseDouble(removalInfo[1].trim());
        if (consume(removalItem, damage, livingEntity)) {
            addRust(itemStack, removal);
            rustParticle(livingEntity.level(), new Random(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), false);
            return true;
        }
        return false;
    }

    public static boolean consume(ItemStack itemStack, int number, LivingEntity livingEntity){
        if (livingEntity instanceof Player player && player.isCreative())
            return true;
        if (itemStack.isDamageableItem()){
            if (number > itemStack.getMaxDamage() - itemStack.getDamageValue())
                return false;
            itemStack.hurtAndBreak(number, livingEntity, EquipmentSlot.OFFHAND);
        }else {
            if (number > itemStack.getCount())
                return false;
            itemStack.shrink(number);
        }
        return true;
    }

    public static void rustParticle(Level level, Random random, double x, double y, double z, boolean wax){
        SimpleParticleType particleType;
        SoundEvent soundEvent;
        if (wax){
            particleType = ParticleTypes.WAX_ON;
            soundEvent = SoundEvents.HONEYCOMB_WAX_ON;
        }else {
            particleType = ParticleTypes.SCRAPE;
            soundEvent = SoundEvents.AXE_SCRAPE;
        }
        for (int i = 0; i < 10; ++i) {
            double d4 = random.nextGaussian() * 0.02;
            double d5 = random.nextGaussian() * 0.02;
            double d6 = random.nextGaussian() * 0.02;
            double d = 0.95;
            level.addParticle(particleType, x + 0.13124999403953552 + 0.737500011920929 * (double)random.nextFloat(), y + d + (double)random.nextFloat() * (1.0 - d), z + 0.13124999403953552 + 0.737500011920929 * (double)random.nextFloat(), d4, d5, d6);
            level.playSound(null, x, y, z, soundEvent, SoundSource.PLAYERS, 1, 1);
        }
    }

    public static void changeRustModel(ItemStack itemStack){
        if (itemStack.getItem() instanceof IRustItem && canBeRust(itemStack)){
            if (getRust(itemStack) > 70){
                itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(1));
            }else {
                itemStack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(0));
            }
        }
    }

    public static void changeRustModel(Object object){
        if (object instanceof ItemStack itemStack)
            changeRustModel(itemStack);
    }
}
