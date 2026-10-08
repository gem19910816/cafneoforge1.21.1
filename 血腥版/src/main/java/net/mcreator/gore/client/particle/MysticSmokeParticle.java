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
public class MysticSmokeParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;
   private float angularVelocity;
   private float angularAcceleration;

   public static MysticSmokeParticle.MysticSmokeParticleProvider provider(SpriteSet spriteSet) {
      return new MysticSmokeParticle.MysticSmokeParticleProvider(spriteSet);
   }

   protected MysticSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.2F, 0.2F);
      this.quadSize *= 2.5F;
      this.lifetime = 14;
      this.gravity = -0.8F;
      this.hasPhysics = true;
      this.xd = vx * 0.25;
      this.yd = vy * 0.25;
      this.zd = vz * 0.25;
      this.angularVelocity = 0.0F;
      this.angularAcceleration = 0.1F;
      this.setSpriteFromAge(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
      this.oRoll = this.roll;
      this.roll = this.roll + this.angularVelocity;
      this.angularVelocity = this.angularVelocity + this.angularAcceleration;
      if (!this.removed) {
         this.setSprite(this.spriteSet.get(this.age / 3 % 5 + 1, 5));
      }
   }

   public static class MysticSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public MysticSmokeParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new MysticSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
