package net.mcreator.survivalinstinct.procedures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public final class HomemadeBombProyectileProjectileExplosionProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity source) {
        if (!(world instanceof ServerLevel level)) return;
        level.explode(source, x, y, z, 3.0F, Level.ExplosionInteraction.TNT);
        for (int i = 0; i < 4; i++) {
            double angle = i * Math.PI / 2;
            Arrow fragment = new Arrow(EntityType.ARROW, level);
            if (source instanceof Projectile projectile) fragment.setOwner(projectile.getOwner());
            fragment.pickup = AbstractArrow.Pickup.DISALLOWED;
            fragment.setBaseDamage(5.0);
            fragment.setPos(x, y + 0.1, z);
            fragment.shoot(Math.cos(angle), 0.25, Math.sin(angle), 1.0F, 0);
            level.addFreshEntity(fragment);
        }
    }
}
