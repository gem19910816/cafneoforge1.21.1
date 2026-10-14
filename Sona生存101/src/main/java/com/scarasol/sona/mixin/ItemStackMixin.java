package com.scarasol.sona.mixin;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.manager.ChatManager;
import com.scarasol.sona.manager.RotManager;
import com.scarasol.sona.manager.RustManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Unique
    private boolean init;

    @Inject(method = "isSameItemSameComponents", cancellable = true, at = @At("RETURN"))
    private static void OnIsSameItemSameComponents(ItemStack itemStack1, ItemStack itemStack2, CallbackInfoReturnable<Boolean> cir){
        if (!CommonConfig.ROT_STACKABLE.get() || cir.getReturnValue() || !itemStack1.has(DataComponents.FOOD) || !itemStack2.has(DataComponents.FOOD))
            return;
        if (!itemStack1.is(itemStack2.getItem()))
            return;
        if (tagMatchesIgnoreRot(itemStack1, itemStack2))
            cir.setReturnValue(true);
    }

    @Unique
    private static boolean tagMatchesIgnoreRot(ItemStack itemStack1, ItemStack itemStack2){
        if (itemStack2.has(DataComponents.FOOD) && itemStack1.has(DataComponents.FOOD)){
            CompoundTag tag1 = itemStack1.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            CompoundTag tag2 = itemStack2.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            if (tag1.isEmpty() && !tag2.isEmpty()) {
                return tag2.getAllKeys().size() == tagSize(itemStack2);
            }else if (!tag1.isEmpty() && tag2.isEmpty()){
                return tag1.getAllKeys().size() == tagSize(itemStack1);
            }
        }
        return false;
    }

    @Unique
    private static int tagSize(ItemStack itemStack){
        int size = 0;
        CompoundTag tag = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.isEmpty() || !itemStack.has(DataComponents.FOOD)) return size;
        if (tag.contains("RotValue")) {
            size++;
        }
        if (tag.contains("RotSaveTime")) {
            size++;
        }
        if (tag.contains("RotMultiplier")) {
            size++;
        }
        return size;
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void onInventoryTick(Level level, Entity entity, int slot, boolean selected, CallbackInfo ci){
        if (!init && ChatManager.isChatLimit()){
            ChatManager.setItemRange(this);
            init = true;
        }
        if (level.isClientSide())
            return;
        if (CommonConfig.ROT_OPEN.get() && ((ItemStack) (Object) this).has(DataComponents.FOOD) && RotManager.canBeRotten((ItemStack) (Object) this))
            RotManager.rotTick(this, entity, slot, level.getBiome(entity.getOnPos()).value().getBaseTemperature() / 2 + 0.6);
        if (CommonConfig.RUST_OPEN.get() && RustManager.canBeRust(this))
            RustManager.changeRustModel(this);
    }

    @Inject(method = "useOn", at = @At("RETURN"))
    private void useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir){
        Player player = useOnContext.getPlayer();
        if (player == null || player.isSpectator() || player.isCreative())
            return;
        if (CommonConfig.RUST_OPEN.get() && (cir.getReturnValue() == InteractionResult.CONSUME || cir.getReturnValue() == InteractionResult.SUCCESS))
            RustManager.rustItem(this, player, useOnContext.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    @Inject(method = "use", at = @At("RETURN"))
    private void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        if (player.isSpectator() || player.isCreative())
            return;
        if (CommonConfig.RUST_OPEN.get() && (cir.getReturnValue().getResult() == InteractionResult.CONSUME || cir.getReturnValue().getResult() == InteractionResult.SUCCESS))
            RustManager.rustItem(this, player, interactionHand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    @Inject(method = "interactLivingEntity", at = @At("RETURN"))
    private void onInteractLivingEntity(Player player, LivingEntity livingEntity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir){
        if (player.isSpectator() || player.isCreative())
            return;
        if (CommonConfig.RUST_OPEN.get() && (cir.getReturnValue() == InteractionResult.CONSUME || cir.getReturnValue() == InteractionResult.SUCCESS))
            RustManager.rustItem(this, player, interactionHand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    @Inject(method = "mineBlock", at = @At("RETURN"))
    private void onMineBlock(Level level, BlockState blockState, BlockPos blockPos, Player player, CallbackInfo ci){
        if (player.isSpectator() || player.isCreative())
            return;
        if (CommonConfig.RUST_OPEN.get())
            RustManager.rustItem(this, player, EquipmentSlot.MAINHAND);
    }

    @Inject(method = "hurtEnemy", at = @At("RETURN"))
    private void onHurtEnemy(LivingEntity livingEntity, Player player, CallbackInfoReturnable<Boolean> cir){
        if (player.isSpectator() || player.isCreative())
            return;
        if (CommonConfig.RUST_OPEN.get())
            RustManager.rustItem(this, player, ItemStack.isSameItemSameComponents(player.getMainHandItem(), (ItemStack) (Object) this) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    @Inject(method = "getDestroySpeed", cancellable = true, at = @At("RETURN"))
    private void onGetDestroySpeed(BlockState blockState, CallbackInfoReturnable<Float> cir){
        float speed = cir.getReturnValue();
        if (!CommonConfig.RUST_OPEN.get() || !RustManager.canBeRust(this))
            return;
        if (RustManager.getRust(this) >= 75) {
            cir.setReturnValue(Math.max(1, speed * 0.85f));
        }else if (RustManager.getRust(this) >= 50) {
            cir.setReturnValue(Math.max(1, speed * 0.95f));
        }
    }
}
