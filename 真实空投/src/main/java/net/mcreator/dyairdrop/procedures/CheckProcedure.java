package net.mcreator.dyairdrop.procedures;

import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.compat.CrateCompat;
import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

/** Checks the six-key English lock used by locked airdrop crates. */
public class CheckProcedure {
   private CheckProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (world == null || world.isClientSide() || !(entity instanceof Player player)) {
         return;
      }
      BlockPos pos = BlockPos.containing(x, y, z);
      String entered = DyairdropModVariables.get(entity).passwordre;
      String key = PasswordUtil.ensureLetterKey(world, pos);

      if (!entered.equals(key)) {
         DyairdropModVariables.with(entity, variables -> {
            variables.passwordre = "";
            variables.syncPlayerVariables(entity);
         });
         player.displayClientMessage(Component.translatable("message.passwordwrong"), false);
         try {
            player.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                  .getHolderOrThrow(DamageTypes.MAGIC)),
               ((Double)AirdropconfigConfiguration.ATTEMPTPUNISHMENT.get()).floatValue());
         } catch (RuntimeException ignored) {
         }
         return;
      }

      PasswordUtil.setBlockString(world, pos, "valid", "shutdown");
      DyairdropModVariables.with(entity, variables -> {
         variables.passwordre = "Y";
         variables.syncPlayerVariables(entity);
      });
      player.displayClientMessage(Component.translatable("message.passwordcorrect"), false);
      CrateCompat.setBlockAnimation(world, pos, 1);
      player.closeContainer();
      DyairdropMod.queueServerWork(20, () -> {
         CrateCompat.openCrate(world, pos);
         CrateCompat.setBlockAnimation(world, pos, 2);
      });
   }
}
