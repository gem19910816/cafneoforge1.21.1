package com.scarasol.sona.mixin;

import com.scarasol.sona.accessor.mixin.IBaseContainerBlockEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.manager.RotManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.Hopper;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.VanillaInventoryCodeHooks;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(VanillaInventoryCodeHooks.class)
public abstract class VanillaInventoryCodeHooksMixin {
    @Inject(method = "lambda$extractHook$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/Hopper;setItem(ILnet/minecraft/world/item/ItemStack;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
        private static void onExtractHook(Hopper dest, Pair itemHandlerResult, CallbackInfoReturnable<Boolean> cir, IItemHandler handler, int i, ItemStack extractItem, int j, ItemStack destStack){
            if (dest instanceof BlockEntity blockEntity && extractItem.has(net.minecraft.core.component.DataComponents.FOOD) && CommonConfig.ROT_OPEN.get() && RotManager.canBeRotten(extractItem)){
                if (destStack.isEmpty()){
                    RotManager.putRotSaveTime(extractItem, blockEntity.getLevel().getGameTime());
                }else {
                RotManager.rotWhenStack(destStack, RotManager.getRot(destStack), RotManager.getRot(extractItem), destStack.getCount(), extractItem.getCount(), blockEntity.getLevel().getGameTime());
            }
        }
    }

    @Inject(method = "getItemHandlerAt", remap = false, cancellable = true, at = @At("RETURN"))
    private static void onGetItemHandler(Level level, double x, double y, double z, Direction direction, CallbackInfoReturnable<Optional<Pair<IItemHandler, Object>>> cir){
        if (cir.getReturnValue().isPresent()){
            if (level.getBlockEntity(new BlockPos(Mth.floor(x), Mth.floor(y), Mth.floor(z))) instanceof IBaseContainerBlockEntityAccessor baseContainerBlockEntityAccessor && baseContainerBlockEntityAccessor.isLocked())
                cir.setReturnValue(Optional.empty());
        }
    }
}
