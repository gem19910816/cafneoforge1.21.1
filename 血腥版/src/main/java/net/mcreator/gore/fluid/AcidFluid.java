package net.mcreator.gore.fluid;

import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.init.GoreEditionModFluidTypes;
import net.mcreator.gore.init.GoreEditionModFluids;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.BaseFlowingFluid.Properties;

public abstract class AcidFluid extends BaseFlowingFluid {
   public static final Properties PROPERTIES = new Properties(
         () -> (FluidType)GoreEditionModFluidTypes.ACID_TYPE.get(),
         () -> (Fluid)GoreEditionModFluids.ACID.get(),
         () -> (Fluid)GoreEditionModFluids.FLOWING_ACID.get()
      )
      .explosionResistance(100.0F)
      .slopeFindDistance(1)
      .block(() -> (LiquidBlock)GoreEditionModBlocks.ACID.get());

   private AcidFluid() {
      super(PROPERTIES);
   }

   public ParticleOptions getDripParticle() {
      return ParticleTypes.DRIPPING_WATER;
   }

   public static class Flowing extends AcidFluid {
      protected void createFluidStateDefinition(Builder<Fluid, FluidState> builder) {
         super.createFluidStateDefinition(builder);
         builder.add(new Property[]{LEVEL});
      }

      public int getAmount(FluidState state) {
         return (Integer)state.getValue(LEVEL);
      }

      public boolean isSource(FluidState state) {
         return false;
      }
   }

   public static class Source extends AcidFluid {
      public int getAmount(FluidState state) {
         return 8;
      }

      public boolean isSource(FluidState state) {
         return true;
      }
   }
}
