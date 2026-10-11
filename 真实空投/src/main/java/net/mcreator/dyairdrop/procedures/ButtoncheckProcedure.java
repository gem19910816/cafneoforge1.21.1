package net.mcreator.dyairdrop.procedures;

import java.util.HashMap;
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

/**
 * Server-authoritative numeric keypad check.
 *
 * <p>The generated implementation performed six delayed substring operations
 * and could read an empty key, which made malformed crates crash. A check is
 * now one bounded transaction; the visual bit string is still populated for
 * the existing panel textures.</p>
 */
public class ButtoncheckProcedure {
   private ButtoncheckProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap<?, ?> ignoredGuiState) {
      if (world == null || world.isClientSide() || !(entity instanceof Player player)) {
         return;
      }

      BlockPos pos = BlockPos.containing(x, y, z);
      String input = PasswordUtil.normalizeDigits(DyairdropModVariables.get(entity).password);
      DyairdropModVariables.with(entity, variables -> {
         variables.password = input;
         variables.showlight = 0.0;
         variables.pw = "";
         variables.keyticking = world.dayTime();
         variables.syncPlayerVariables(entity);
      });

      if (!PasswordUtil.isSixDigit(input)) {
         player.displayClientMessage(Component.translatable("message.lengtherror"), false);
         return;
      }

      String key = PasswordUtil.ensureNumericKey(world, pos);
      StringBuilder result = new StringBuilder(6);
      for (int i = 0; i < 6; i++) {
         result.append(input.charAt(i) == key.charAt(i) ? '1' : '0');
      }
      String resultBits = result.toString();
      boolean correct = input.equals(key);
      DyairdropModVariables.with(entity, variables -> {
         variables.showlight = 1.0;
         variables.keyticking = world.dayTime();
         variables.pw = resultBits;
         variables.syncPlayerVariables(entity);
      });

      if (!correct) {
         player.displayClientMessage(Component.translatable("message.passwordwrong"), false);
         try {
            player.hurt(
               new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                  .getHolderOrThrow(DamageTypes.MAGIC)),
               ((Double)AirdropconfigConfiguration.ATTEMPTPUNISHMENT.get()).floatValue());
         } catch (RuntimeException ignored) {
            // A custom damage registry must not make the password panel fatal.
         }
         return;
      }

      PasswordUtil.setBlockString(world, pos, "open", "1");
      PasswordUtil.setBlockString(world, pos, "valid", "shutdown");
      player.displayClientMessage(Component.translatable("message.passwordcorrect"), false);
      CrateCompat.setBlockAnimation(world, pos, 1);
      // The old generated flow left the panel attached to the pre-open block.
      // Close it before the delayed block swap so the client cannot interact
      // with a stale menu while the crate is being replaced.
      player.closeContainer();

      DyairdropMod.queueServerWork(40, () -> {
         CrateCompat.openCrate(world, pos);
         CrateCompat.setBlockAnimation(world, pos, 2);
      });
   }
}
