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
public class GuardianSkeletonParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static GuardianSkeletonParticle.GuardianSkeletonParticleProvider provider(SpriteSet spriteSet) {
      return new GuardianSkeletonParticle.GuardianSkeletonParticleProvider(spriteSet);
   }

   protected GuardianSkeletonParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 2.0F;
      this.lifetime = 600;
      this.gravity = 1.3F;
      this.hasPhysics = true;
      this.xd = vx * 0.5;
      this.yd = vy * 0.5;
      this.zd = vz * 0.5;
      this.pickSprite(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
   }

   public static class GuardianSkeletonParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public GuardianSkeletonParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new GuardianSkeletonParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
