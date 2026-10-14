package com.scarasol.sona.entity;

import com.scarasol.sona.accessor.mixin.ILivingEntityAccessor;
import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.util.ServerRenderEmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SoundDecoy extends Mob {

    private static final EntityDataAccessor<Integer> DATA_AMPLIFIER = SynchedEntityData.defineId(SoundDecoy.class, EntityDataSerializers.INT);

    private int life;

    private String soundEvent;

    public SoundDecoy(EntityType<? extends Mob> entityType, Level level, int amplifier) {
        super(entityType, level);
        life = 20 * CommonConfig.DECOY_LIFE.get() * (amplifier + 1);
        this.entityData.set(DATA_AMPLIFIER, amplifier);
        this.setNoGravity(true);
        this.setNoAi(true);
    }

    public SoundDecoy(EntityType<SoundDecoy> entityType, Level level) {
        this(entityType, level, 0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_AMPLIFIER, 0);
    }

    public String getSoundEvent() {
        return soundEvent;
    }

    public int getAmplifier() {
        return this.entityData.get(DATA_AMPLIFIER);
    }

    public void setSoundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return null;
    }

    @Override
    public SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean causeFallDamage(float l, float d, @NotNull DamageSource source) {
        return false;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata) {
        this.addEffect(new MobEffectInstance(SonaMobEffects.EXPOSURE, life, getAmplifier(), false, false));
        if ((Object) this instanceof ILivingEntityAccessor accessor) {
            accessor.setExposureAmplifier(getAmplifier());
        }
        return super.finalizeSpawn(world, difficulty, reason, livingdata);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("Life"))
            this.life = compoundTag.getInt("Life");
        if (compoundTag.contains("Amplifier"))
            this.entityData.set(DATA_AMPLIFIER, compoundTag.getInt("Amplifier"));
        if ((Object) this instanceof ILivingEntityAccessor accessor) {
            accessor.setExposureAmplifier(getAmplifier());
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Life", this.life);
        compoundTag.putInt("Amplifier", getAmplifier());
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if ((Object) this instanceof ILivingEntityAccessor accessor) {
            accessor.setExposureAmplifier(getAmplifier());
        }
        if (!level().isClientSide() && CommonConfig.EXPOSURE_INDICATOR.get() && tickCount % 10 == 0) {
            ServerRenderEmitter.emitPositionIndicator(level(), position(), (getAmplifier() + 1) * 16.0D, 14);
        }
        if (!level().isClientSide() && life % 10 == 0 && !level().getEntitiesOfClass(SoundDecoy.class, getBoundingBox().inflate(8), soundDecoy -> soundDecoy.getAmplifier() >= getAmplifier() && soundDecoy != this).isEmpty()) {
            this.discard();
        }
        if (life-- <= 0) {
            this.discard();
        }
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(@NotNull Entity entityIn) {
    }

    @Override
    public void push(Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, @NotNull BlockState state, @NotNull BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.0);
        builder = builder.add(Attributes.MAX_HEALTH, 4);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 0);
        return builder;
    }


}
