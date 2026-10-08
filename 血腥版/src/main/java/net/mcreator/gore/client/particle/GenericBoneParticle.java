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
public class GenericBoneParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static GenericBoneParticle.GenericBoneParticleProvider provider(SpriteSet spriteSet) {
      return new GenericBoneParticle.GenericBoneParticleProvider(spriteSet);
   }

   protected GenericBoneParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 2.0F;
      this.lifetime = 600;
      this.gravity = 0.6F;
      this.hasPhysics = false;
      this.xd = vx * 0.3;
      this.yd = vy * 0.3;
      this.zd = vz * 0.3;
      this.setSpriteFromAge(spriteSet);
   }

   public ParticleRenderType getRenderType() {
      return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   public void tick() {
      super.tick();
      if (!this.removed) {
         this.setSprite(this.spriteSet.get(this.age / 2 % 6 + 1, 6));
      }
   }

   public static class GenericBoneParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public GenericBoneParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new GenericBoneParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
