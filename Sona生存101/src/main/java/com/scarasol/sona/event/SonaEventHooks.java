package com.scarasol.sona.event;

import com.google.common.collect.Lists;
import com.scarasol.sona.event.common.ChunkInfectionGetEvent;
import com.scarasol.sona.event.server.ChunkInfectionTreeDecoratorEvent;
import com.scarasol.sona.event.server.NeutralityTargetBlockEvent;
import com.scarasol.sona.event.server.SonaSoundEvent;
import com.scarasol.sona.init.SonaMobEffects;
import com.scarasol.sona.init.SonaTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Scarasol
 */
public class SonaEventHooks {

    /**
     * NeoForge 8.x 的事件总线没有公开的 hasListeners 查询。
     * 热路径（如目标选取、渲染链路）每帧都会 post 自定义事件，
     * 这里通过反射缓存「该事件类型是否有监听者」，无监听者时直接跳过 post；
     * 反射失败时退化为总是 post，保证语义不变。
     */
    private static final Map<Class<? extends Event>, Boolean> LISTENER_CACHE = new ConcurrentHashMap<>();
    private static final Object LISTENER_LISTS; // EventBus#listenerLists (LockHelper)
    private static final Method LOCK_HELPER_GET;
    private static final Method LISTENER_LIST_GET_LISTENERS;

    static {
        Object listenerLists = null;
        Method lockHelperGet = null;
        Method listenerListGetListeners = null;
        try {
            Field field = NeoForge.EVENT_BUS.getClass().getDeclaredField("listenerLists");
            field.setAccessible(true);
            listenerLists = field.get(NeoForge.EVENT_BUS);
            lockHelperGet = listenerLists.getClass().getDeclaredMethod("get", Object.class);
            lockHelperGet.setAccessible(true);
            Class<?> listenerListClass = Class.forName("net.neoforged.bus.ListenerList");
            listenerListGetListeners = listenerListClass.getMethod("getListeners");
        } catch (Throwable ignored) {
        }
        LISTENER_LISTS = listenerLists;
        LOCK_HELPER_GET = lockHelperGet;
        LISTENER_LIST_GET_LISTENERS = listenerListGetListeners;
    }

    private static boolean hasListeners(Class<? extends Event> eventClass) {
        return LISTENER_CACHE.computeIfAbsent(eventClass, type -> {
            try {
                if (LISTENER_LISTS == null) {
                    return true;
                }
                Object listenerList = LOCK_HELPER_GET.invoke(LISTENER_LISTS, type);
                return listenerList != null && ((Object[]) LISTENER_LIST_GET_LISTENERS.invoke(listenerList)).length > 0;
            } catch (Throwable throwable) {
                return true;
            }
        });
    }

    public static int getFullChunkInfection(BlockPos blockPos, Level level, int chunkInfection) {
        ChunkInfectionGetEvent event = new ChunkInfectionGetEvent(chunkInfection, level, blockPos, ChunkInfectionGetEvent.State.FULL);
        if (hasListeners(ChunkInfectionGetEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return event.getChunkInfection();
    }

    public static int getCalculateChunkInfection(BlockPos blockPos, Level level, int chunkInfection) {
        ChunkInfectionGetEvent event = new ChunkInfectionGetEvent(chunkInfection, level, blockPos, ChunkInfectionGetEvent.State.CALCULATE);
        if (hasListeners(ChunkInfectionGetEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return event.getChunkInfection();
    }

    public static int getInitChunkInfection(BlockPos blockPos, Level level, int chunkInfection) {
        ChunkInfectionGetEvent event = new ChunkInfectionGetEvent(chunkInfection, level, blockPos, ChunkInfectionGetEvent.State.INIT);
        if (hasListeners(ChunkInfectionGetEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return event.getChunkInfection();
    }


    public static List<TreeDecorator> addTreeDecorator(ServerLevel serverLevel, BlockPos blockPos, int chunkInfection) {
        List<TreeDecorator> treeDecorators = Lists.newArrayList();
        ChunkInfectionTreeDecoratorEvent event = new ChunkInfectionTreeDecoratorEvent(serverLevel, blockPos, chunkInfection, treeDecorators);
        if (hasListeners(ChunkInfectionTreeDecoratorEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return event.getTreeDecorators();
    }

    public static boolean spawnSoundDecoy(ServerLevel serverLevel, BlockPos blockPos, int amplifier, SonaSoundEvent.State state) {
        SonaSoundEvent event = new SonaSoundEvent(serverLevel, blockPos, amplifier, state);
        if (hasListeners(SonaSoundEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return !event.isCanceled();
    }

    public static boolean shouldBlockNeutralityTargetGoal(Mob mob, Level level, @javax.annotation.Nullable LivingEntity target) {
        NeutralityTargetBlockEvent event = new NeutralityTargetBlockEvent(mob, level, target);
        if (hasListeners(NeutralityTargetBlockEvent.class)) {
            NeoForge.EVENT_BUS.post(event);
        }
        return !event.isCanceled();
    }

    public static boolean shouldCheckNeutrality(Mob mob, LivingEntity target) {
        if (mob.hasEffect(SonaMobEffects.INSANE)) {
            return false;
        }
        boolean mobNeutrality = mob.hasEffect(SonaMobEffects.NEUTRALITY);
        boolean mobTagged = mob.getType().is(SonaTags.NEUTRALITY_TARGETS);
        boolean targetNeutrality = target.hasEffect(SonaMobEffects.NEUTRALITY);
        boolean targetTagged = target.getType().is(SonaTags.NEUTRALITY_TARGETS);
        return mobNeutrality && (targetNeutrality || targetTagged) || (mobTagged && targetNeutrality);
    }
}
