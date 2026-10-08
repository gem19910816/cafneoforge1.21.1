package net.mcreator.gore.item;

import java.util.function.Consumer;
import net.mcreator.gore.item.renderer.SquitchgunItemRenderer;
import net.mcreator.gore.procedures.SquitchgunItemInInventoryTickProcedure;
import net.mcreator.gore.procedures.SquitchgunRightclickedProcedure;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController.State;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SquitchgunItem extends Item implements GeoItem {
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
   public String animationprocedure = "empty";
   public static ItemDisplayContext transformType;

   public SquitchgunItem() {
      super(new Properties().durability(250).rarity(Rarity.EPIC));
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      super.initializeClient(consumer);
      consumer.accept(new IClientItemExtensions() {
         private final BlockEntityWithoutLevelRenderer renderer = new SquitchgunItemRenderer();

         public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return this.renderer;
         }
      });
   }

   public void getTransformType(ItemDisplayContext type) {
      transformType = type;
   }

   private PlayState idlePredicate(AnimationState event) {
      if (transformType != null && transformType.firstPerson() && this.animationprocedure.equals("empty")) {
         event.getController().setAnimation(RawAnimation.begin().thenLoop("squitchgun.idle"));
         return PlayState.CONTINUE;
      } else {
         return PlayState.STOP;
      }
   }

   private PlayState procedurePredicate(AnimationState event) {
      if (transformType != null && transformType.firstPerson()) {
         if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == State.STOPPED) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == State.STOPPED) {
               this.animationprocedure = "empty";
               event.getController().forceAnimationReset();
            }
         } else if (this.animationprocedure.equals("empty")) {
            return PlayState.STOP;
         }
      }

      return PlayState.CONTINUE;
   }

   public void registerControllers(ControllerRegistrar data) {
      AnimationController procedureController = new AnimationController(this, "procedureController", 0, this::procedurePredicate);
      data.add(new AnimationController[]{procedureController});
      AnimationController idleController = new AnimationController(this, "idleController", 0, this::idlePredicate);
      data.add(new AnimationController[]{idleController});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
      ItemStack itemstack = (ItemStack)ar.getObject();
      double x = entity.getX();
      double y = entity.getY();
      double z = entity.getZ();
      SquitchgunRightclickedProcedure.execute(entity, itemstack);
      return ar;
   }

   public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
      super.inventoryTick(itemstack, world, entity, slot, selected);
      SquitchgunItemInInventoryTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
   }
}
