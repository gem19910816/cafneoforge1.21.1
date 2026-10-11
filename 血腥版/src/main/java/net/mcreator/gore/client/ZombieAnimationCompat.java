package net.mcreator.gore.client;

import java.util.function.Function;
import java.lang.reflect.Method;
import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.EntityModel;

/** Optional bridge for EMF/Fresh Animations; no hard dependency on EMF. */
public final class ZombieAnimationCompat {
   private static boolean registered;
   private static Method emfEntityOf;
   private static Method animateModel;

   private ZombieAnimationCompat() {
   }

   public static void registerEmfPauseCondition() {
      if (registered) {
         return;
      }
      registered = true;
      try {
         Class<?> api = Class.forName("traben.entity_model_features.EMFAnimationApi");
         Class<?> emfEntity = Class.forName("traben.entity_model_features.utils.EMFEntity");
         emfEntityOf = api.getMethod("emfEntityOf", Entity.class);
         animateModel = api.getMethod("animateModelForEntity", emfEntity, EntityModel.class, boolean.class);
         Object accepted = api.getMethod("registerPauseCondition", Function.class).invoke(null,
            (Function<Object, Boolean>) value -> value instanceof LivingEntity living
               && (living.hasEffect(GoreEditionModMobEffects.ZOMBIE_DOWNED)
                  || living.hasEffect(GoreEditionModMobEffects.ZOMBIE_STAGGERED)));
         GoreEditionMod.LOGGER.info("[Gore shove recovery-v11] EMF live-pose bridge registered: {}", accepted);
      } catch (ClassNotFoundException ignored) {
         GoreEditionMod.LOGGER.info("[Gore shove recovery-v10] EMF absent; using original model rendering");
      } catch (ReflectiveOperationException | LinkageError failure) {
         GoreEditionMod.LOGGER.warn("[Gore shove recovery-v10] Cannot register EMF pause condition", failure);
      }
   }

   public static void evaluateBasePose(EntityModel<?> model, LivingEntity entity) {
      if (animateModel == null) return;
      try {
         // Evaluate this frame before Gore blends it; suppress only the later
         // draw-time evaluation so it cannot overwrite the blended result.
         animateModel.invoke(null, emfEntityOf.invoke(null, entity), model, true);
      } catch (ReflectiveOperationException | LinkageError failure) {
         animateModel = null;
         GoreEditionMod.LOGGER.warn("[Gore shove recovery-v11] Cannot evaluate EMF base pose", failure);
      }
   }
}
