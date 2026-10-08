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
public class SlimeHurtSlimeParticle extends TextureSheetParticle {
   private final SpriteSet spriteSet;

   public static SlimeHurtSlimeParticle.SlimeHurtSlimeParticleProvider provider(SpriteSet spriteSet) {
      return new SlimeHurtSlimeParticle.SlimeHurtSlimeParticleProvider(spriteSet);
   }

   protected SlimeHurtSlimeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
      super(world, x, y, z);
      this.spriteSet = spriteSet;
      this.setSize(0.0F, 0.0F);
      this.quadSize *= 2.3F;
      this.lifetime = 19;
      this.gravity = 0.7F;
      this.hasPhysics = true;
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
         this.setSprite(this.spriteSet.get(this.age / 4 % 5 + 1, 5));
      }
   }

   public static class SlimeHurtSlimeParticleProvider implements ParticleProvider<SimpleParticleType> {
      private final SpriteSet spriteSet;

      public SlimeHurtSlimeParticleProvider(SpriteSet spriteSet) {
         this.spriteSet = spriteSet;
      }

      public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         return new SlimeHurtSlimeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
      }
   }
}
