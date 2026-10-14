package com.scarasol.sona.manager;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaSounds;
import com.scarasol.sona.network.RotPacket;
import com.scarasol.sona.util.SonaBlockUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RotManager {

    // config-list caches keyed by list identity so a config reload (new list instance) invalidates them
    private static List<? extends String> rotWhitelistSource;
    private static Set<String> rotWhitelistCache;
    private static List<? extends String> rotDetailSource;
    private static Map<String, Integer> rotDetailCache;

    private static Set<String> rotWhitelist() {
        List<? extends String> source = CommonConfig.ROT_WHITELIST.get();
        if (source != rotWhitelistSource) {
            rotWhitelistSource = source;
            rotWhitelistCache = new HashSet<>(source);
        }
        return rotWhitelistCache;
    }

    private static Map<String, Integer> rotDetailIndex() {
        List<? extends String> source = CommonConfig.ROT_DETAIL.get();
        if (source != rotDetailSource) {
            rotDetailSource = source;
            Map<String, Integer> cache = new HashMap<>();
            for (int i = 0; i < source.size(); i++) {
                String[] str = source.get(i).split(",");
                if (!str[0].startsWith("#"))
                    cache.putIfAbsent(str[0], i);
            }
            rotDetailCache = cache;
        }
        return rotDetailCache;
    }

    private static CompoundTag getTag(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
    }


    public static void rotWhenStack(ItemStack itemStack, double rotValue1, double rotValue2, int count1, int count2, long saveTime) {
        if (itemStack.has(DataComponents.FOOD) && count2 > 0) {
            double rotValue = (rotValue1 * count1 + rotValue2 * count2) / (count1 + count2);
            putRot(itemStack, rotValue);
            putRotSaveTime(itemStack, Math.max(Math.min(getRotSaveTime(itemStack), saveTime), 0));
        }
    }

    public static void putRot(ItemStack itemStack, double rotValue) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putDouble("RotValue", rotValue));
    }

    public static double getRot(ItemStack itemStack) {
        return getTag(itemStack).getDouble("RotValue");
    }

    public static void addRot(ItemStack itemStack, double addition) {
        if (addition > 0)
            addition = addition * CommonConfig.ROT_WEIGHT.get().floatValue();
        addActualRot(itemStack, addition);
    }

    public static void addActualRot(ItemStack itemStack, double addition) {
        double rot = addition > 0 ? Math.min(100, addition + getRot(itemStack)) : Math.max(0, addition + getRot(itemStack));
        putRot(itemStack, rot);
    }

    public static void putMultiplier(ItemStack itemStack, double rotMultiplier) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putDouble("RotMultiplier", rotMultiplier));
    }

    public static double getMultiplier(ItemStack itemStack) {
        double multiplier = getTag(itemStack).getDouble("RotMultiplier");
        if (multiplier == 0) {
            multiplier = initMultiplier(itemStack);
            putMultiplier(itemStack, multiplier);
        }
        if (isWarped(itemStack)) {
            multiplier = multiplier * CommonConfig.WARPED_WEIGHT.get();
        }
        return multiplier;
    }

    public static void putRotSaveTime(ItemStack itemStack, long gameTime) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putLong("RotSaveTime", gameTime));
    }

    public static long getRotSaveTime(ItemStack itemStack) {
        return getTag(itemStack).getLong("RotSaveTime");
    }

    public static void putWarp(ItemStack itemStack, boolean warped) {
        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> tag.putBoolean("Warped", warped));
    }

    public static boolean isWarped(ItemStack itemStack) {
        return getTag(itemStack).getBoolean("Warped");
    }

    public static double initMultiplier(ItemStack itemStack) {
        ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        String name = resourceLocation != null ? resourceLocation.toString() : "";
        Integer cachedIndex = rotDetailIndex().get(name);
        int index = Math.max(cachedIndex != null ? cachedIndex : -1, CommonConfig.tagSearch(itemStack, CommonConfig.ROT_DETAIL.get()));
        double rate = 1;
        if (index == -1) {
            if (resourceLocation != null && "zombiekit".equals(resourceLocation.getNamespace())) {
                if ("chocolate".equals(resourceLocation.getPath())) {
                    rate = 0.02;
                } else if ("compressed_biscuit".equals(resourceLocation.getPath())) {
                    rate = 0.01;
                }
            }
            return rate;
        }
        String[] str = CommonConfig.ROT_DETAIL.get().get(index).split(",");
        if (str.length < 3) {
            return rate;
        }
        if (Double.parseDouble(str[1].trim()) <= 0) {
            return rate;
        }
        rate = Double.parseDouble(str[1].trim());
        return rate;
    }

    public static void eatRotFood(LivingEntity livingEntity, ItemStack itemStack) {
        double rotValue = getRot(itemStack);
        if (rotValue >= 90) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0, false, false));
        } else if (rotValue >= 70 && livingEntity.level().getRandom().nextDouble() > 0.75) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0, false, false));
        } else if (rotValue >= 40 && livingEntity.level().getRandom().nextDouble() > 0.9) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 0, false, false));
        }
    }

    public static boolean warpFood(ItemStack food, ItemStack warpItem, LivingEntity livingEntity) {
        if (!canBeRotten(food) || !food.has(DataComponents.FOOD))
            return false;
        ResourceLocation warpKey = BuiltInRegistries.ITEM.getKey(warpItem.getItem());
        if (warpKey == null || !CommonConfig.WARPED_ITEMS.get().contains(warpKey.toString()))
            return false;
        int warpCount = Math.min(warpItem.getCount(), food.getCount());
        ItemStack warpedFood = food.copy();
        putWarp(warpedFood, true);
        warpedFood.setCount(warpCount);
        if (livingEntity instanceof Player player)
            ItemHandlerHelper.giveItemToPlayer(player, warpedFood);
        warpItem.shrink(warpCount);
        food.shrink(warpCount);
        rotParticle(livingEntity.level(), new Random(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        return true;
    }

    public static void rotParticle(Level level, Random random, double x, double y, double z) {
        SimpleParticleType particleType = ParticleTypes.COMPOSTER;
        SoundEvent soundEvent = SonaSounds.SLIDER_ZIPPER_BAG.get();
        for (int i = 0; i < 10; ++i) {
            double d4 = random.nextGaussian() * 0.02;
            double d5 = random.nextGaussian() * 0.02;
            double d6 = random.nextGaussian() * 0.02;
            double d = 0.95;
            level.addParticle(particleType, x + 0.13124999403953552 + 0.737500011920929 * (double) random.nextFloat(), y + d + (double) random.nextFloat() * (1.0 - d), z + 0.13124999403953552 + 0.737500011920929 * (double) random.nextFloat(), d4, d5, d6);
        }
        level.playSound(null, x, y, z, soundEvent, SoundSource.PLAYERS, 1, 1);
    }

    public static double getContainerMultiplier(Level level, @Nullable BlockPos blockPos) {
        int index = -1;
        if (blockPos == null)
            index = CommonConfig.findIndex("minecraft:ender_chest", CommonConfig.ROT_CONTAINER.get());
        else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity != null) {
                ResourceLocation typeKey = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntity.getType());
                if (typeKey != null)
                    index = CommonConfig.findIndex(typeKey.toString(), CommonConfig.ROT_CONTAINER.get());
            }
            if (index == -1) {
                ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(level.getBlockState(blockPos).getBlock());
                if (blockKey != null)
                    index = CommonConfig.findIndex(blockKey.toString(), CommonConfig.ROT_CONTAINER.get());
            }
        }
        if (index != -1) {
            String[] str = CommonConfig.ROT_CONTAINER.get().get(index).split(",");
            if (str.length >= 2) {
                return Double.parseDouble(str[1].trim());
            }
        }
        return 1;
    }

    public static void syncRotValue(double rotValue, int slot, boolean flag, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new RotPacket(rotValue, slot, flag));
    }

    public static void containerOpen(Player player, AbstractContainerMenu abstractContainerMenu) {
        double containerMultiplier = 1;
        double temperature = 1;
        BlockPos blockPos = SonaBlockUtil.tryFindContainerBlockPos(abstractContainerMenu);
        if (blockPos == null) {
            if (abstractContainerMenu.slots.get(0).container instanceof PlayerEnderChestContainer) {
                containerMultiplier = RotManager.getContainerMultiplier(player.level(), null);
            }
        }else {

            double height = blockPos.getY();
            containerMultiplier = RotManager.getContainerMultiplier(player.level(), blockPos);
            Level level = player.level();
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            ResourceLocation typeKey = blockEntity != null ? BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntity.getType()) : null;
            if (typeKey != null && CommonConfig.ROT_TEMPERATURE.get() && CommonConfig.findIndex(typeKey.toString(), CommonConfig.ROT_TEMPERATURE_WHITELIST.get()) == -1) {
                if (height < 0) {
                    temperature = 0.5;
                } else if (height < 30) {
                    temperature = 0.7;
                } else if (height < 60) {
                    temperature = 0.9;
                }
                temperature = temperature * (level.getBiome(blockPos).value().getBaseTemperature() / 2 + 0.6);
            }
        }
        if (containerMultiplier != 0 && player instanceof ServerPlayer serverPlayer) {
            RotManager.rotInContainer(serverPlayer, abstractContainerMenu.slots, abstractContainerMenu.slots.size() - serverPlayer.getInventory().items.size(), containerMultiplier, serverPlayer.level().getGameTime(), temperature);
        }
    }

    public static void rotInContainer(ServerPlayer serverPlayer, List<Slot> slots, int size, double containerMultiplier, long gameTime, double temperature) {
        for (int i = 0; i < size; i++) {
            Slot slot = slots.get(i);
            ItemStack itemStack = slot.getItem().copy();
            if (itemStack.has(DataComponents.FOOD) && canBeRotten(itemStack)) {
                long saveTime = getRotSaveTime(itemStack);
                if (saveTime == 0) continue;
                double rotValue = getRot(itemStack);
                int cycle = 1680;
                cycle = Math.max((int) (cycle / temperature / getMultiplier(itemStack) / containerMultiplier), 1);
                double rotAddition = (gameTime - saveTime) / (double) cycle;
                rotAddition = rotAddition * CommonConfig.ROT_WEIGHT.get();
                if (rotValue + rotAddition >= 100) {
                    rotten(itemStack, null, slot);
                } else {
                    addActualRot(itemStack, rotAddition);
                    slot.set(itemStack);
                    syncRotValue(getRot(itemStack), i, false, serverPlayer);
                    slot.setChanged();
                }
            }
        }
    }

    public static void rotTimeUpdate(List<Slot> slots, int size, long gameTime) {
        for (int i = 0; i < size; i++) {
            Slot slot = slots.get(i);
            ItemStack itemStack = slot.getItem();
            if (itemStack.has(DataComponents.FOOD) && canBeRotten(itemStack)) {
                putRotSaveTime(itemStack, gameTime);
                slot.set(itemStack);
                slot.setChanged();
            }
        }
    }

    public static void rotten(ItemStack itemStack, SlotAccess slotAccess, Slot slot) {
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        Integer cachedIndex = itemKey != null ? rotDetailIndex().get(itemKey.toString()) : null;
        int index = Math.max(cachedIndex != null ? cachedIndex : -1, CommonConfig.tagSearch(itemStack, CommonConfig.ROT_DETAIL.get()));
        if (index == -1) {
            int amount = itemStack.getCount();
            itemStack.setCount(0);
            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(CommonConfig.ROT_RESULT.get()));
            if (item != null) {
                ItemStack output = new ItemStack(item);
                output.setCount(amount);
                if (slot == null && slotAccess != null) {
                    slotAccess.set(output);
                } else if (slot != null && slotAccess == null) {
                    slot.set(output);
                    slot.setChanged();
                }
            }
            return;
        }
        String[] str = CommonConfig.ROT_DETAIL.get().get(index).split(",");
        if (str.length < 3) {
            int amount = itemStack.getCount();
            itemStack.setCount(0);
            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(CommonConfig.ROT_RESULT.get()));
            if (item != null) {
                ItemStack output = new ItemStack(item);
                output.setCount(amount);
                if (slot == null && slotAccess != null) {
                    slotAccess.set(output);
                } else if (slot != null && slotAccess == null) {
                    slot.set(output);
                    slot.setChanged();
                }
            }
            return;
        }
        int count = itemStack.getCount();
        itemStack.setCount(0);
        Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(str[2].trim()));
        if (item == null)
            item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(CommonConfig.ROT_RESULT.get()));
        if (item != null) {
            ItemStack itemStackNew = new ItemStack(item);
            itemStackNew.setCount(count);
            if (slot == null && slotAccess != null) {
                slotAccess.set(itemStackNew);
            } else if (slot != null && slotAccess == null) {
                slot.set(itemStackNew);
                slot.setChanged();
            }
        }

    }

    public static boolean canBeRotten(ItemStack itemStack) {
        ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        if (resourceLocation == null) {
            return true;
        }
        if ("zombiekit".equals(resourceLocation.getNamespace())) {
            if (resourceLocation.getPath().contains("canned")) {
                return false;
            }
        }
        return !rotWhitelist().contains(resourceLocation.toString());
    }

    public static void rotTick(Object object, Entity entity, int slot, double temperature) {
        if (object instanceof ItemStack itemStack) {
            if (itemStack.has(DataComponents.FOOD) && canBeRotten(itemStack)) {
                int cycle = 1680;
                if (CommonConfig.ROT_TEMPERATURE.get()) {
                    cycle = Math.max((int) (cycle / temperature / getMultiplier(itemStack)), 1);
                } else {
                    cycle = Math.max((int) (cycle / getMultiplier(itemStack)), 1);
                }
                if ((entity.level().getGameTime() + entity.getId()) % cycle == 0) {
                    addRot(itemStack, 1);
                    if (entity instanceof ServerPlayer serverPlayer)
                        syncRotValue(getRot(itemStack), slot, true, serverPlayer);
                }
                if (getRot(itemStack) >= 100) {
                    rotten(itemStack, entity.getSlot(slot), null);
                }
            }
        }
    }

    public static void tooltipInsert(List<Component> toolTip, ItemStack itemStack) {
        double rotValue = getRot(itemStack);
        if (rotValue < 40) {
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rot.fresh").withStyle(ChatFormatting.DARK_GREEN));
        } else if (rotValue < 70) {
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rot.slightly_spoiled").withStyle(ChatFormatting.DARK_AQUA));
        } else if (rotValue < 90) {
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rot.spoiled").withStyle(ChatFormatting.YELLOW));
        } else {
            toolTip.add(Math.min(1, toolTip.size()), Component.translatable("tooltip.sona.rot.heavily_spoiled").withStyle(ChatFormatting.RED));
        }
        if (isWarped(itemStack))
            toolTip.add(Math.min(2, toolTip.size()), Component.translatable("tooltip.sona.rot.warped").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
    }


}
