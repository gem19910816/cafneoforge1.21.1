package net.mcreator.survivalinstinct.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;

public final class RefrigeratorBlockDestroyedByPlayerProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack stack) {
        if (!(world instanceof ServerLevel level) || !(entity instanceof ServerPlayer player)
                || player.getAbilities().instabuild) return;
        var silkTouch = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
        if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, stack) == 0) {
            ExperienceOrb.award(level, new net.minecraft.world.phys.Vec3(x, y, z), 3);
        }
    }
}
