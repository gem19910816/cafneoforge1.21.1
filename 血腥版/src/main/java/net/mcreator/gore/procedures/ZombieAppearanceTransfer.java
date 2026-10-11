package net.mcreator.gore.procedures;

import net.mcreator.gore.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;

/** Carries the original identity until a client has reported its resource-pack texture. */
public final class ZombieAppearanceTransfer {
   public static final String SOURCE_KEY = "gore_appearance_source";
   public static final String TEXTURE_KEY = "gore_appearance_texture";
   public static final String SOURCE_PREFIX = "@gore-source|";

   private ZombieAppearanceTransfer() {
   }

   public static boolean isZombie(Entity entity) {
      ResourceLocation id = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
      return entity instanceof Zombie || (entity instanceof Mob && id.getNamespace().equals("gore_edition")
         && (id.getPath().contains("zombie") || id.getPath().contains("husk")));
   }

   public static void inherit(Entity corpse, Entity source) {
      if (!isZombie(corpse)) {
         return;
      }
      java.util.UUID original = source.getPersistentData().hasUUID(SOURCE_KEY)
         ? source.getPersistentData().getUUID(SOURCE_KEY) : source.getUUID();
      corpse.getPersistentData().putUUID(SOURCE_KEY, original);
      String texture = source.getPersistentData().getString(TEXTURE_KEY);
      if (!texture.isEmpty()) {
         corpse.getPersistentData().putString(TEXTURE_KEY, texture);
         setTexture(corpse, texture);
      } else {
         String family = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE
            .getKey(source.getType()).getPath().contains("husk") ? "husk" : "zombie";
         setTexture(corpse, SOURCE_PREFIX + original + "|minecraft:textures/entity/zombie/" + family + ".png");
      }
   }

   public static boolean setTexture(Entity entity, String texture) {
      if (entity instanceof ZombieCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof DisarmedZombieCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HeadlessZombieCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredLegsZombieCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof ZombieWithoutLegsAndArmCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HuskCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof DisarmedHuskCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HeadlessHuskCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredLegsHuskCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HuskWithoutLegsAndArmCorpseEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof DisarmedZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HeadlessZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredlegsZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredLegsAndArmZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HorizontallycuttedZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof CrushedZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof VerticalcuttedZombieEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof DisarmedHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HeadlessHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredlegsHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof SeveredLegsAndArmHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof HorizontallyCuttedHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof CrushedHuskEntity corpse) corpse.setTexture(texture);
      else if (entity instanceof VerticalcuttedHuskEntity corpse) corpse.setTexture(texture);
      else return false;
      return true;
   }
}
