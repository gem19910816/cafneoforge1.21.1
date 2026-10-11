package com.scarasol.rummage.mixin.lootr;

import com.scarasol.rummage.api.mixin.ILootrInventoryDelegate;
import com.scarasol.rummage.api.mixin.IRummageable;
import com.scarasol.rummage.api.mixin.IRummageableContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.data.LootrInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mixin(value = LootrInventory.class, remap = false)
public abstract class LootrInventoryMixin implements IRummageableContainer, ILootrInventoryDelegate {
    @Shadow public abstract ILootrInfo getInfo();
    @Unique private IRummageable rummage$delegate;
    @Unique private final Set<UUID> rummage$fallbackCompleted = new HashSet<>();
    @Unique private final Set<UUID> rummage$fallbackOpeners = new HashSet<>();
    @Unique private final Map<UUID, BitSet> rummage$fallbackProgress = new HashMap<>();

    @Inject(method = "createMenu", at = @At("HEAD"))
    private void rummage$bindSource(int id, Inventory inventory, Player player,
                                   CallbackInfoReturnable<AbstractContainerMenu> cir) {
        Container container = getInfo().getInfoContainer();
        if (container instanceof IRummageable source) {
            rummage$delegate = source;
        }
    }

    @Override public void rummage$setBlockEntity(IRummageable entity) { rummage$delegate = entity; }
    @Override public IRummageable rummage$getBlockEntity() { return rummage$delegate; }
    @Override public void setNeedRummage(boolean value) {
        if (rummage$delegate != null) rummage$delegate.setNeedRummage(value);
    }
    @Override public boolean isNeedRummage() {
        return rummage$delegate != null && rummage$delegate.isNeedRummage();
    }
    @Override public boolean isInBlackList() {
        return rummage$delegate != null && rummage$delegate.isInBlackList();
    }
    @Override public Set<UUID> getFullyRummagedPlayer() {
        return rummage$delegate != null ? rummage$delegate.getFullyRummagedPlayer() : rummage$fallbackCompleted;
    }
    @Override public Set<UUID> getRummagingPlayer() {
        return rummage$delegate != null ? rummage$delegate.getRummagingPlayer() : rummage$fallbackOpeners;
    }
    @Override public Map<UUID, BitSet> getRummageProgress() {
        return rummage$delegate != null ? rummage$delegate.getRummageProgress() : rummage$fallbackProgress;
    }
    @Override public UUID getRummageableUUID() {
        return rummage$delegate != null ? rummage$delegate.getRummageableUUID() : getInfo().getInfoUUID();
    }
}
