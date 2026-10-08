package net.mcreator.gore.procedures;

import net.mcreator.gore.network.GoreEditionModVariables;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;

public class VitalityStealingsItemInInventoryTickProcedure {
   public static void execute(Entity entity, ItemStack itemstack) {
      if (entity != null && GoreEditionModVariables.getPlayerVariables(entity).vitality_stealings >= 1.0) {
         double _setval = GoreEditionModVariables.getPlayerVariables(entity).deals_fangs_thieves_damage + 1.0;
         GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.deals_fangs_thieves_damage = _setval;
         capability.syncPlayerVariables(entity);
         _setval = 0.0;
         capability = GoreEditionModVariables.getPlayerVariables(entity);
         capability.vitality_stealings = _setval;
         capability.syncPlayerVariables(entity);
         if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
            _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 2));
         }

         if ((new Object() {
                  public boolean checkGamemode(Entity _ent) {
                     if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL;
                     } else {
                        return _ent.level().isClientSide() && _ent instanceof Player _player
                           ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                              && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SURVIVAL
                           : false;
                     }
                  }
               })
               .checkGamemode(entity)
            || (new Object() {
                  public boolean checkGamemode(Entity _ent) {
                     if (_ent instanceof ServerPlayer _serverPlayer) {
                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.ADVENTURE;
                     } else {
                        return _ent.level().isClientSide() && _ent instanceof Player _player
                           ? Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
                              && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.ADVENTURE
                           : false;
                     }
                  }
               })
               .checkGamemode(entity)) {
            if (ItemTagHelper.getEnchantLevel(itemstack, Enchantments.UNBREAKING, entity.level()) == 0 && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }

            if (ItemTagHelper.getEnchantLevel(itemstack, Enchantments.UNBREAKING, entity.level()) != 0
               && Math.random() > (double)(100 / (ItemTagHelper.getEnchantLevel(itemstack, Enchantments.UNBREAKING, entity.level()) + 1) / 100)
               && ItemTagHelper.damageItem(itemstack, entity)) {
               itemstack.shrink(1);
               itemstack.setDamageValue(0);
            }
         }
      }
   }
}
