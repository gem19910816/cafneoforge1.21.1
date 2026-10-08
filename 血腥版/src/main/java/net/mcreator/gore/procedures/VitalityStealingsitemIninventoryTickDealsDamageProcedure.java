package net.mcreator.gore.procedures;

import net.mcreator.gore.GoreEditionMod;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class VitalityStealingsitemIninventoryTickDealsDamageProcedure {
   public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
      if (entity != null && GoreEditionModVariables.getPlayerVariables(entity).deals_fangs_thieves_damage >= 1.0) {
         GoreEditionMod.queueServerWork(1, () -> {
            if (ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }

            double _setval = GoreEditionModVariables.getPlayerVariables(entity).deals_fangs_thieves_damage - 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.deals_fangs_thieves_damage = _setval;
            capability.syncPlayerVariables(entity);
         });
      }
   }
}
