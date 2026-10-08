package net.mcreator.gore.fluid.types;

import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidType.Properties;

public class AcidFluidType extends FluidType {
   public AcidFluidType() {
      super(
         Properties.create()
            .fallDistanceModifier(0.0F)
            .canExtinguish(true)
            .supportsBoating(true)
            .canHydrate(true)
            .motionScale(3.5E-4)
            .rarity(Rarity.EPIC)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
      );
   }

   public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
      consumer.accept(new IClientFluidTypeExtensions() {
         private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("gore_edition:block/acid_still");
         private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("gore_edition:block/acid_flow");

         public ResourceLocation getStillTexture() {
            return STILL_TEXTURE;
         }

         public ResourceLocation getFlowingTexture() {
            return FLOWING_TEXTURE;
         }
      });
   }
}
