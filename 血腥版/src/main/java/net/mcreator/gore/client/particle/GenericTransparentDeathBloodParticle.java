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
public class GenericTransparentDeathBloodParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static GenericTransparentDeathBloodParticle.GenericTransparentDeathBloodParticleProvider provider(SpriteSet spriteSet) {
      return new GenericTransparentDeathBloodParticle.GenericTransparentDeathBloodParticleProvider(spriteSet);
   }

   protected GenericTransparentDeathBloodParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 4.0F;
      this.lifetime = 20;
      this.gravity = 0.06F;
      this.hasPhysics = true;
      this.xd = vx * 0.4;
      this.yd = vy * 0.4;
      this.zd = vz * 0.4;
      this.setSpriteFromAge(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
   }

   public void tick() {
      super.tick();
      if (!this.removed) {
         this.setSprite(this.spriteSet.get(this.age / 3 % 7 + 1, 7));
      }
   }

   public static class GenericTransparentDeathBloodParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public GenericTransparentDeathBloodParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new GenericTransparentDeathBloodParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
