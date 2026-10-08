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
public class SpectralBloodDropsParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static SpectralBloodDropsParticle.SpectralBloodDropsParticleProvider provider(SpriteSet spriteSet) {
      return new SpectralBloodDropsParticle.SpectralBloodDropsParticleProvider(spriteSet);
   }

   protected SpectralBloodDropsParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 2.0F;
      this.lifetime = 600;
      this.gravity = 0.6F;
      this.hasPhysics = true;
      this.xd = vx * 0.38;
      this.yd = vy * 0.38;
      this.zd = vz * 0.38;
      this.pickSprite(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
   }

   public static class SpectralBloodDropsParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public SpectralBloodDropsParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new SpectralBloodDropsParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
