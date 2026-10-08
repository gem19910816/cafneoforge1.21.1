package net.mcreator.dyairdrop.block.display;

import java.util.function.Consumer;
import net.mcreator.dyairdrop.block.renderer.SafeDisplayItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

public class SafeDisplayItem extends BlockItem implements GeoItem {
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

   public SafeDisplayItem(Block block, Properties settings) {
      super(block, settings);
   }

   private PlayState predicate(AnimationState event) {
      return PlayState.CONTINUE;
   }

   public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
      consumer.accept(new GeoRenderProvider() {
         private final BlockEntityWithoutLevelRenderer renderer = new SafeDisplayItemRenderer();

         public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
            return this.renderer;
         }
      });
   }

   public void registerControllers(ControllerRegistrar data) {
      data.add(new AnimationController[]{new AnimationController(this, "controller", 0, this::predicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }
}
