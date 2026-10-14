package com.scarasol.sona.mixin;

import com.scarasol.sona.accessor.mixin.IBaseContainerBlockEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.LockCode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.UUID;

@Mixin(BaseContainerBlockEntity.class)
public abstract class BaseContainerBlockEntityMixin extends BlockEntity implements IBaseContainerBlockEntityAccessor {

    @Shadow
    private LockCode lockKey;

    @Shadow
    public abstract Component getName();

    // NeoForge 已移除 BlockEntity#getPersistentData，锁数据改为 mixin 字段并经 saveAdditional/loadAdditional 持久化
    @Unique
    private String sona$lockUUID;

    @Unique
    private boolean sona$lockFlag;

    public BaseContainerBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    @Unique
    public void breakLockKey() {
        if (BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(this.getType()).toString().contains("lootr"))
            return;
        this.lockKey = LockCode.NO_LOCK;
        this.sona$lockUUID = null;
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }

    }

    @Override
    @Unique
    public boolean isLocked() {
        return this.lockKey != LockCode.NO_LOCK || this.sona$lockUUID != null;
    }

    @Override
    @Unique
    public boolean isLocked(Player player) {
        if (BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(this.getType()).toString().contains("lootr")) {
            return isLocked() && this.getLevel() != null && !containsPlayer(player, saveWithoutMetadata(this.getLevel().registryAccess()).getList("LootrOpeners", Tag.TAG_INT_ARRAY));
        } else {
            return isLocked();
        }
    }

    @Unique
    public boolean containsPlayer(Player player, ListTag list) {
        UUID uuid = player.getUUID();
        for (Tag tag : list) {
            if (uuid.equals(NbtUtils.loadUUID(tag)))
                return true;
        }
        return false;
    }

    @Override
    @Unique
    public void lockContainer(CompoundTag compoundTag) {
        if (BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(getType()).getPath().contains("airdrop"))
            return;
        if (CommonConfig.BLOCK_LOCK_BLACKLIST.get().contains(BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(getType()).toString()))
            return;
        if (compoundTag.contains("LootTable", 8) && !this.sona$lockFlag) {
            if (CommonConfig.LOCK_WHITELIST_OPEN.get() && CommonConfig.findIndex(compoundTag.getString("LootTable"), CommonConfig.LOCK_WHITELIST.get()) == -1)
                return;
            // 客户端跳过随机判定，由服务端决定，锁状态通过方块实体更新标签同步给客户端
            if (getLevel() == null || !getLevel().isClientSide()) {
                if (new Random().nextDouble() * 100 < CommonConfig.LOCK_PERCENT.get()) {
                    lockContainer(compoundTag, UUID.randomUUID());
                }
            }
        }
    }

    @Override
    @Unique
    public void lockContainer(CompoundTag compoundTag, UUID uuid) {
        compoundTag.putString("Lock", uuid.toString());
        this.lockKey = LockCode.fromTag(compoundTag);
        this.sona$lockUUID = uuid.toString();
        this.setChanged();
    }

    @Inject(method = "canOpen", cancellable = true, at = @At("RETURN"))
    private void onCanUnlock(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() && BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(this.getType()).toString().contains("lootr"))
            cir.setReturnValue(true);
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    private void sona$saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider, CallbackInfo ci) {
        if (this.sona$lockUUID != null)
            compoundTag.putString("SonaLock", this.sona$lockUUID);
        if (this.sona$lockFlag)
            compoundTag.putBoolean("SonaLockFlag", true);
    }

    @Inject(method = "loadAdditional", at = @At("TAIL"))
    private void onLoad(CompoundTag compoundTag, HolderLookup.Provider provider, CallbackInfo ci) {
        if (compoundTag.contains("SonaLock"))
            this.sona$lockUUID = compoundTag.getString("SonaLock");
        this.sona$lockFlag = compoundTag.getBoolean("SonaLockFlag");
        if (CommonConfig.LOCK_PERCENT.get() == 0) {
            this.sona$lockFlag = false;
            this.lockKey = LockCode.NO_LOCK;
        } else {
            lockContainer(compoundTag);
            this.sona$lockFlag = true;
        }
    }


}
