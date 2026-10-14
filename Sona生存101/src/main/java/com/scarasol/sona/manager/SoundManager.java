package com.scarasol.sona.manager;

import com.scarasol.sona.configuration.CommonConfig;
import com.scarasol.sona.entity.SoundDecoy;
import com.scarasol.sona.entity.ai.goal.SoundAttractionGoal;
import com.scarasol.sona.event.SonaEventHooks;
import com.scarasol.sona.event.server.SonaSoundEvent;
import com.scarasol.sona.init.SonaEntities;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.network.SoundDecoyPacket;
import com.scarasol.sona.network.SyncSoundPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class SoundManager {

    private static final List<String> soundWhiteList = new ArrayList<>();
    private static boolean soundOpen;

    // Mob#goalSelector is protected at compile time (public at runtime via NeoForge AT), so access it reflectively
    private static final MethodHandle GOAL_SELECTOR_GETTER;

    static {
        MethodHandle handle = null;
        try {
            handle = MethodHandles.privateLookupIn(Mob.class, MethodHandles.lookup())
                    .findGetter(Mob.class, "goalSelector", GoalSelector.class);
        } catch (ReflectiveOperationException ignored) {
        }
        GOAL_SELECTOR_GETTER = handle;
    }

    public static boolean isSoundOpen() {
        return soundOpen;
    }

    public static void setSoundOpen(boolean soundOpen) {
        SoundManager.soundOpen = soundOpen;
    }

    public static void addSoundWhiteList(String sound){
        soundWhiteList.add(sound);
    }

    public static void clearSoundWhiteList(){
        soundWhiteList.clear();
    }

    public static void syncSoundWhiteList(ServerPlayer player){
        for (String sound : soundWhiteList){
            PacketDistributor.sendToPlayer(player, new SyncSoundPacket(sound, soundOpen));
        }
    }

    public static boolean isSoundAttractedMob(Mob mob){
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
        if (key != null) {
            if (CommonConfig.findIndex(key.toString(), CommonConfig.SOUND_ATTRACTED_MOB_WHITELIST.get()) != -1)
                return true;
            if (CommonConfig.findIndex(key.toString(), CommonConfig.SOUND_ATTRACTED_MOB_BLACKLIST.get()) != -1)
                return false;
        }
        return mob.getType().is(EntityTypeTags.UNDEAD);
    }

    public static void insertAi(Mob mob){
        if (isSoundAttractedMob(mob) && GOAL_SELECTOR_GETTER != null) {
            try {
                ((GoalSelector) GOAL_SELECTOR_GETTER.invokeExact(mob)).addGoal(3, new SoundAttractionGoal(mob, 1.0D, 2.5D));
            } catch (Throwable ignored) {
            }
        }
    }

    public static int getIndex(String soundName){
        int index = CommonConfig.findIndex(soundName, soundWhiteList);
        if (index == -1)
            index = CommonConfig.containSearch(soundName, soundWhiteList);
        return index;
    }

    public static int getAmplifier(int index){
        String[] str = soundWhiteList.get(index).split(",");
        if (str.length == 2)
            return Math.max(Integer.parseInt(str[1].trim()), 0);
        return 0;
    }

    public static void spawnSoundDecoy(Level level, double x, double y, double z, int amplifier){
        if (level instanceof ServerLevel serverLevel && SonaEventHooks.spawnSoundDecoy(serverLevel, BlockPos.containing(x, y, z), amplifier, SonaSoundEvent.State.DECOY)){
            SoundDecoy soundDecoy = new SoundDecoy(SonaEntities.SOUND_DECOY.get(), level, amplifier);
            soundDecoy.setPos(x, y, z);
            soundDecoy.finalizeSpawn(serverLevel, level.getCurrentDifficultyAt(BlockPos.containing(x, y, z)), MobSpawnType.MOB_SUMMONED, null);
            serverLevel.addFreshEntity(soundDecoy);
        }else if (level.isClientSide()) {
            PacketDistributor.sendToServer(new SoundDecoyPacket(x, y, z, amplifier));
        }
    }

    public static void addSoundEffect(LivingEntity livingEntity, int time, int amplifier) {
        if (livingEntity.level() instanceof ServerLevel serverLevel && SonaEventHooks.spawnSoundDecoy(serverLevel, livingEntity.blockPosition(), amplifier, SonaSoundEvent.State.LIVING)) {
            livingEntity.addEffect(new MobEffectInstance(SonaMobEffects.EXPOSURE, time, amplifier, false, false));
        }
    }
}
