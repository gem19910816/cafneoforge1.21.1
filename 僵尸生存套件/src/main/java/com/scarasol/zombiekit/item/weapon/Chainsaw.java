package com.scarasol.zombiekit.item.weapon;

import com.scarasol.zombiekit.config.CommonConfig;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Chainsaw extends Item {
    public Chainsaw(Properties properties) {
        super(properties);
    }

    private static CompoundTag getTag(ItemStack itemStack) {
        return itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.zombiekit.chainsaw.description_1"));
        list.add(Component.translatable("item.zombiekit.chainsaw.description_2"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            if (!getTag(itemStack).getBoolean("Power")){
                if (player.isShiftKeyDown()){
                    ItemStack itemOther;
                    if (hand == InteractionHand.MAIN_HAND){
                        itemOther = player.getItemInHand(InteractionHand.OFF_HAND);
                    }else {
                        itemOther = player.getItemInHand(InteractionHand.MAIN_HAND);
                    }
                    if (itemOther.is(ZombieKitItems.BATTERY.get())){
                        int damage = itemOther.getDamageValue();
                        itemOther.setDamageValue(itemStack.getDamageValue());
                        itemStack.setDamageValue(damage);
                        player.getCooldowns().addCooldown(itemStack.getItem(), 140);
                        return new InteractionResultHolder(InteractionResult.PASS, itemStack);
                    }
                }else if (itemStack.getDamageValue() >= 100){
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("zombiekit:chainsaw_start_failed")), SoundSource.PLAYERS, 1, 1);
                    return new InteractionResultHolder(InteractionResult.PASS, itemStack);
                }else {
                    CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> {
                        tag.putBoolean("Power", true);
                        tag.putInt("CustomModelData", 1);
                    });
                }
            }else if (player.isShiftKeyDown()){
                CustomData.update(DataComponents.CUSTOM_DATA, itemStack, tag -> {
                    tag.putBoolean("Power", false);
                    tag.putInt("CustomModelData", 0);
                });
            }else {
                player.startUsingItem(hand);
            }
        }
        return new InteractionResultHolder(InteractionResult.SUCCESS, itemStack);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (entity instanceof LivingEntity livingEntity){
            ItemStack offHandItem = livingEntity.getOffhandItem();
            if ((selected || offHandItem == itemstack) && getTag(itemstack).getBoolean("Power")){
                if (world.getGameTime() % 10 == 0){
                    if (livingEntity.isUsingItem() && livingEntity.getUseItem().is(ZombieKitItems.CHAINSAW.get())){
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("zombiekit:chainsaw_attack")), SoundSource.PLAYERS, 0.6f, 1);
                    }else {
                        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("zombiekit:chainsaw_idle")), SoundSource.PLAYERS, 0.3f, 1);
                    }
                }
                if (!world.isClientSide() && livingEntity instanceof Player player && !player.getAbilities().instabuild){
                    if (world.getGameTime() % CommonConfig.CHAINSAW_POWER.get() == 0) {
                        itemstack.hurtAndBreak(4, livingEntity, selected ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

                        if (itemstack.getDamageValue() >= 100){
                            CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> {
                                tag.putBoolean("Power", false);
                                tag.putInt("CustomModelData", 0);
                            });
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int duration) {
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        Vec3 vec = livingEntity.getViewVector(1);
        Vec3 _center;
        if (vec.y < 0){
            _center = new Vec3(x + vec.x * 1.5, y + vec.y * 0.5, z + vec.z * 1.5);
        }else {
            _center = new Vec3(x + vec.x * 1.5, y + vec.y + 1, z + vec.z * 1.5);
        }
        List<Entity> _entfound = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.9d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
        for (Entity entityiterator : _entfound) {
            if (!livingEntity.equals(entityiterator)){
                entityiterator.invulnerableTime = 0;
                entityiterator.getPersistentData().putBoolean("CancelKnockback", true);
                entityiterator.hurt(livingEntity.level().damageSources().mobAttack(livingEntity), CommonConfig.CHAINSAW_DAMAGE.get().floatValue());
            }
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.is(ZombieKitItems.BATTERY.get());
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack, LivingEntity entity) {
        return 72000;
    }
}
