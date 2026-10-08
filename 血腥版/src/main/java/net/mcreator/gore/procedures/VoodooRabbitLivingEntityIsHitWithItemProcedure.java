package net.mcreator.gore.procedures;

import java.util.Comparator;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoodooRabbitLivingEntityIsHitWithItemProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
      if (entity != null && sourceentity != null) {
         entity.getPersistentData().putBoolean(sourceentity.getStringUUID() + "voodo", true);
         if (ItemTagHelper.getDouble(itemstack, "click") == 5.0) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(7.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator.getPersistentData().getBoolean(sourceentity.getStringUUID() + "voodo")) {
                  entityiterator.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), sourceentity), 21.0F
                  );
               }
            }

            if (ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }
      }
   }
}
