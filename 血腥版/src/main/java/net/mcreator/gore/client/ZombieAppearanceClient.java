package net.mcreator.gore.client;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.mcreator.gore.network.ZombieAppearanceMessage;
import net.mcreator.gore.procedures.ZombieAppearanceTransfer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

public final class ZombieAppearanceClient {
   private static final Map<UUID, ResourceLocation> TEXTURES = new LinkedHashMap<>();
   private static final Map<UUID, ResourceLocation> REPORTED = new LinkedHashMap<>();
   private static Object currentLevel;
   private static Method etfVariant;

   static {
      try {
         etfVariant = Class.forName("traben.entity_texture_features.ETFApi")
            .getMethod("getCurrentETFVariantTextureOfEntity", Entity.class, ResourceLocation.class);
      } catch (ReflectiveOperationException | LinkageError ignored) {
         // Resource packs without ETF still use the renderer's selected texture.
      }
   }

   private ZombieAppearanceClient() {
   }

   private static void checkLevel() {
      Object level = Minecraft.getInstance().level;
      if (currentLevel != level) {
         currentLevel = level;
         TEXTURES.clear();
         REPORTED.clear();
      }
   }

   public static void capture(Entity entity, ResourceLocation base) {
      checkLevel();
      if (!ZombieAppearanceTransfer.isZombie(entity)
         || net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath().contains("corpse")
         || isInheritedVariant(entity)) return;
      ResourceLocation selected = base;
      if (etfVariant != null) {
         try {
            Object result = etfVariant.invoke(null, entity, base);
            if (result instanceof ResourceLocation location) selected = location;
         } catch (ReflectiveOperationException | LinkageError ignored) {
            etfVariant = null;
         }
      }
      TEXTURES.put(entity.getUUID(), selected);
      if (!selected.equals(REPORTED.get(entity.getUUID())) && selected.toString().length() <= 256
         && Minecraft.getInstance().getConnection() != null) {
         PacketDistributor.sendToServer(new ZombieAppearanceMessage(entity.getId(), entity.getUUID(), selected.toString()));
         REPORTED.put(entity.getUUID(), selected);
      }
      trim(TEXTURES);
      trim(REPORTED);
   }

   private static boolean isInheritedVariant(Entity entity) {
      if (!entity.getPersistentData().hasUUID(ZombieAppearanceTransfer.SOURCE_KEY)) {
         return false;
      }
      String stored = entity.getPersistentData().getString(ZombieAppearanceTransfer.TEXTURE_KEY);
      return !stored.isEmpty();
   }

   public static ResourceLocation corpseTexture(Entity entity, String texture) {
      checkLevel();
      if (texture.startsWith(ZombieAppearanceTransfer.SOURCE_PREFIX)) {
         String[] parts = texture.split("\\|", 3);
         if (parts.length == 3) {
            try {
               UUID source = UUID.fromString(parts[1]);
               ResourceLocation selected = TEXTURES.get(source);
               if (selected != null) {
                  if (!selected.equals(REPORTED.get(entity.getUUID()))
                     && Minecraft.getInstance().getConnection() != null) {
                     PacketDistributor.sendToServer(new ZombieAppearanceMessage(entity.getId(), source, selected.toString()));
                     REPORTED.put(entity.getUUID(), selected);
                     trim(REPORTED);
                  }
                  return selected;
               }
               ResourceLocation fallback = ResourceLocation.tryParse(parts[2]);
               if (fallback != null) return fallback;
            } catch (IllegalArgumentException ignored) {
            }
         }
      }
      if (texture.contains(":")) {
         ResourceLocation qualified = ResourceLocation.tryParse(texture.endsWith(".png") ? texture : texture + ".png");
         if (qualified != null) return qualified;
      }
      return ResourceLocation.fromNamespaceAndPath("gore_edition", "textures/entities/" + texture + ".png");
   }

   private static void trim(Map<UUID, ResourceLocation> cache) {
      while (cache.size() > 2048) {
         cache.remove(cache.keySet().iterator().next());
      }
   }
}
