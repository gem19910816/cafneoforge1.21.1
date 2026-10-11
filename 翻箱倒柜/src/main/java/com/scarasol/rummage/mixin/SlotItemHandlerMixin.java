package com.scarasol.rummage.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.scarasol.rummage.data.RummageTarget;
import com.scarasol.rummage.manager.ClientRummageManager;
import com.scarasol.rummage.util.CommonContainerUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Scarasol
 */
@Mixin(SlotItemHandler.class)
public abstract class SlotItemHandlerMixin {

    // 1. 补全注入：专门拦截 SlotItemHandler 的 mayPickup，防止其绕过 SlotMixin
    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void rummage$mayPickup(Player player, CallbackInfoReturnable<Boolean> cir) {
        Slot currentSlot = (Slot) (Object) this;

        if (player.level().isClientSide()) {
            if (ClientRummageManager.shouldMask(currentSlot.index)) {
                cir.setReturnValue(false);
            }
            return;
        }
        RummageTarget target = CommonContainerUtil.getTarget(currentSlot, player.containerMenu);
        if (target != null && !target.entity().isSlotRummaged(player, target.localSlotIndex())) {
            cir.setReturnValue(false);
        }
    }

    @WrapOperation(
            method = {
                    "mayPickup(Lnet/minecraft/world/entity/player/Player;)Z",
                    "remove(I)Lnet/minecraft/world/item/ItemStack;"
            },
            at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/items/IItemHandler;extractItem(IIZ)Lnet/minecraft/world/item/ItemStack;", remap = false)
    )
    private ItemStack rummage$bypassExtract(IItemHandler handler, int slot, int amount, boolean simulate, Operation<ItemStack> original) {
        // 2. 在放行 bypass 之前，如果是纯客户端环境且格子被遮罩，直接返回空物品，瘫痪整理模组的提取逻辑
        if (EffectiveSide.get().isClient()) {
            Slot currentSlot = (Slot) (Object) this;
            if (ClientRummageManager.shouldMask(currentSlot.index)) {
                return ItemStack.EMPTY;
            }
        }

        CommonContainerUtil.UI_ACTION_BYPASS.set(true);
        try {
            return original.call(handler, slot, amount, simulate);
        } finally {
            CommonContainerUtil.UI_ACTION_BYPASS.remove();
        }
    }

}
