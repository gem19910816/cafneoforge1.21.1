package net.mcreator.dyairdrop.compat;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Small, server-safe helpers shared by both password panels.
 *
 * <p>The original MCreator procedures duplicated this logic in every button and
 * relied on delayed string concatenation. Keeping the data access and input
 * normalization here makes malformed or half-initialized crates harmless.</p>
 */
public final class PasswordUtil {
   private PasswordUtil() {
   }

   public static String blockString(LevelAccessor world, BlockPos pos, String key) {
      if (world == null || pos == null || key == null) {
         return "";
      }
      BlockEntity blockEntity = world.getBlockEntity(pos);
      return blockEntity == null ? "" : blockEntity.getPersistentData().getString(key);
   }

   public static boolean setBlockString(LevelAccessor world, BlockPos pos, String key, String value) {
      if (world == null || world.isClientSide() || pos == null || key == null) {
         return false;
      }
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity == null) {
         return false;
      }
      blockEntity.getPersistentData().putString(key, value == null ? "" : value);
      if (world instanceof Level level) {
         level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
      }
      return true;
   }

   public static boolean setBlockBoolean(LevelAccessor world, BlockPos pos, String key, boolean value) {
      if (world == null || world.isClientSide() || pos == null || key == null) {
         return false;
      }
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity == null) {
         return false;
      }
      blockEntity.getPersistentData().putBoolean(key, value);
      if (world instanceof Level level) {
         level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
      }
      return true;
   }

   public static String normalizeDigits(String input) {
      if (input == null) {
         return "";
      }
      StringBuilder result = new StringBuilder(6);
      for (int i = 0; i < input.length() && result.length() < 6; i++) {
         char c = input.charAt(i);
         if (c >= '0' && c <= '9') {
            result.append(c);
         }
      }
      return result.toString();
   }

   public static boolean isSixDigit(String input) {
      return input != null && input.length() == 6 && input.chars().allMatch(c -> c >= '0' && c <= '9');
   }

   public static String ensureNumericKey(LevelAccessor world, BlockPos pos) {
      String key = blockString(world, pos, "key");
      if (isSixDigit(key)) {
         return key;
      }
      StringBuilder generated = new StringBuilder(6);
      RandomSource random = RandomSource.create();
      for (int i = 0; i < 6; i++) {
         generated.append((char)('0' + random.nextInt(10)));
      }
      key = generated.toString();
      setBlockString(world, pos, "key", key);
      return key;
   }

   public static String ensureLetterKey(LevelAccessor world, BlockPos pos) {
      String key = blockString(world, pos, "pw");
      if (key.length() == 6 && key.chars().allMatch(c -> c >= 'a' && c <= 'f')
         && key.chars().distinct().count() == 6) {
         return key;
      }
      List<Character> letters = new ArrayList<>(List.of('a', 'b', 'c', 'd', 'e', 'f'));
      Collections.shuffle(letters);
      StringBuilder generated = new StringBuilder(6);
      letters.forEach(generated::append);
      key = generated.toString();
      setBlockString(world, pos, "pw", key);
      return key;
   }
}
