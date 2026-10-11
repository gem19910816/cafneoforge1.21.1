package net.mcreator.gore.client;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.mcreator.gore.network.ZombieShovePoseMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public final class ZombieShovePoseClient {
   public record Pose(float bodyYaw, float direction, int duration, long endTick) {
      public float elapsed(long tick, float partialTick) {
         return Math.max(0, Math.min(duration, duration - (endTick - tick) + partialTick));
      }
   }
   private static final Map<UUID, Pose> POSES = new LinkedHashMap<>();
   private static Object currentLevel;

   private ZombieShovePoseClient() {}

   private static void checkLevel() {
      Object level = Minecraft.getInstance().level;
      if (level != currentLevel) {
         currentLevel = level;
         POSES.clear();
      }
   }

   public static void accept(ZombieShovePoseMessage message) {
      checkLevel();
      if (Minecraft.getInstance().level == null || !Float.isFinite(message.bodyYaw())
         || !Float.isFinite(message.direction()) || message.duration() < 10
         || message.duration() > 84 || message.remaining() < 0
         || message.remaining() > message.duration()) return;
      POSES.put(message.entityId(), new Pose(message.bodyYaw(), message.direction(),
         message.duration(), Minecraft.getInstance().level.getGameTime() + message.remaining()));
      while (POSES.size() > 2048) POSES.remove(POSES.keySet().iterator().next());
   }

   public static Pose get(LivingEntity entity) {
      checkLevel();
      Pose pose = POSES.get(entity.getUUID());
      if (pose != null && entity.level().getGameTime() > pose.endTick() + 2) {
         POSES.remove(entity.getUUID());
         return null;
      }
      return pose;
   }

}
