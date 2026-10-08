package net.mcreator.gore.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonLegacyHurtDustParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static SkeletonLegacyHurtDustParticle.SkeletonLegacyHurtDustParticleProvider provider(SpriteSet spriteSet) {
      return new SkeletonLegacyHurtDustParticle.SkeletonLegacyHurtDustParticleProvider(spriteSet);
   }

   protected SkeletonLegacyHurtDustParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 2.7F;
      this.lifetime = 9;
      this.gravity = 0.5F;
      this.hasPhysics = true;
      this.xd = vx * 0.1;
      this.yd = vy * 0.1;
      this.zd = vz * 0.1;
      this.setSpriteFromAge(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
      if (!this.removed) {
         this.setSprite(this.spriteSet.get(this.age / 2 % 5 + 1, 5));
      }
   }

   public static class SkeletonLegacyHurtDustParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public SkeletonLegacyHurtDustParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new SkeletonLegacyHurtDustParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
