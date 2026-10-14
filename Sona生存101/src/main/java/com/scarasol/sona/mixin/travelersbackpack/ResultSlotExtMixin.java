package com.scarasol.sona.mixin.travelersbackpack;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.manager.RotManager;
import com.tiviacz.travelersbackpack.inventory.menu.slot.ResultSlotExt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ResultSlotExt.class)
public abstract class ResultSlotExtMixin {
    @WrapOperation(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;grow(I)V"), remap = false)
    private void onOnTake(ItemStack itemstack1, int amount, Operation<Void> original, Player player, ItemStack stack, @Local(ordinal = 0) ItemStack itemstack) {
        if (CommonConfig.ROT_OPEN.get() && stack.has(net.minecraft.core.component.DataComponents.FOOD) && RotManager.canBeRotten(stack))
            RotManager.rotWhenStack(itemstack1, RotManager.getRot(itemstack1), RotManager.getRot(itemstack), itemstack1.getCount(), amount, RotManager.getRotSaveTime(itemstack));
        original.call(itemstack1, amount);
    }
}
