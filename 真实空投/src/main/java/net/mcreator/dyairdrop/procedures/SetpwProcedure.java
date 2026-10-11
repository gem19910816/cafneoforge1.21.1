package net.mcreator.dyairdrop.procedures;

import java.util.HashMap;
import net.mcreator.dyairdrop.compat.PasswordUtil;
import net.mcreator.dyairdrop.network.PanelText;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class SetpwProcedure {
   private SetpwProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap<?, ?> guiState) {
      if (world == null || world.isClientSide() || !(entity instanceof Player player)) {
         return;
      }

      String input = PasswordUtil.normalizeDigits(PanelText.from(guiState, entity));
      if (!PasswordUtil.isSixDigit(input)) {
         player.displayClientMessage(Component.translatable("message.lengtherror"), false);
         return;
      }

      PasswordUtil.setBlockString(world, BlockPos.containing(x, y, z), "key", input);
      player.displayClientMessage(Component.literal(Component.translatable("message.passwordset").getString() + input), false);
      player.closeContainer();
   }
}
