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
public class GenericLegacyGoreParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static GenericLegacyGoreParticle.GenericLegacyGoreParticleProvider provider(SpriteSet spriteSet) {
      return new GenericLegacyGoreParticle.GenericLegacyGoreParticleProvider(spriteSet);
   }

   protected GenericLegacyGoreParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.15F);
      this.quadSize *= 2.5F;
      this.lifetime = 600;
      this.gravity = 1.0F;
      this.hasPhysics = true;
      this.xd = vx * -1.0;
      this.yd = vy * -1.0;
      this.zd = vz * -1.0;
      this.pickSprite(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
   }

   public static class GenericLegacyGoreParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public GenericLegacyGoreParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new GenericLegacyGoreParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
