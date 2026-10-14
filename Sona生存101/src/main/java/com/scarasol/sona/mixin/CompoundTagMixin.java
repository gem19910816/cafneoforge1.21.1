package com.scarasol.sona.mixin;

import com.scarasol.sona.configuration.CommonConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(CompoundTag.class)
public abstract class CompoundTagMixin implements Tag {

    @Shadow public abstract boolean contains(String p_128442_);

    @Shadow public abstract Set<String> getAllKeys();

    @Shadow public abstract Tag get(String p_128423_);

    @Shadow public abstract Tag put(String p_128366_, Tag p_128367_);

    @Unique
    private static boolean sona$rotStackable() {
        try {
            return CommonConfig.ROT_STACKABLE.get();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    // 浅拷贝并剔除腐坏键，避免 equals/hashCode 各做一次深拷贝
    @Unique
    private static CompoundTag sona$stripRot(CompoundTag tag) {
        CompoundTag copy = new CompoundTag();
        for (String key : tag.getAllKeys()) {
            if (!key.equals("RotValue") && !key.equals("RotSaveTime") && !key.equals("RotMultiplier")) {
                copy.put(key, tag.get(key));
            }
        }
        return copy;
    }

    @Unique
    private boolean sona$hasRotKeys() {
        return this.contains("RotValue") || this.contains("RotSaveTime");
    }

    @Inject(method = "equals", cancellable = true, at = @At("RETURN"))
    private void onEquals(Object object, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValue() && sona$rotStackable() && object instanceof CompoundTag compoundTag && (compoundTag.contains("RotValue") || compoundTag.contains("RotSaveTime") || this.sona$hasRotKeys())){
            if (sona$stripRot((CompoundTag) (Object) this).equals(sona$stripRot(compoundTag))){
                cir.setReturnValue(true);
            }
        }
    }

    // equals 忽略腐坏键后，hashCode 必须同步忽略，保证相等对象哈希一致
    @Inject(method = "hashCode", cancellable = true, at = @At("RETURN"))
    private void onHashCode(CallbackInfoReturnable<Integer> cir){
        if (sona$rotStackable() && this.sona$hasRotKeys()){
            cir.setReturnValue(sona$stripRot((CompoundTag) (Object) this).hashCode());
        }
    }
}
