package net.mcreator.gore.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.HashSet;
import java.util.Set;
import net.mcreator.gore.item.SquitchgunItem;
import net.mcreator.gore.item.model.SquitchgunItemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor.ARGB32;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SquitchgunItemRenderer extends GeoItemRenderer<SquitchgunItem> {
   private static final float SCALE_RECIPROCAL = 0.0625F;
   protected boolean renderArms = false;
   protected MultiBufferSource currentBuffer;
   protected RenderType renderType;
   public ItemDisplayContext transformType;
   protected SquitchgunItem animatable;
   private final Set<String> hiddenBones = new HashSet<>();
   private final Set<String> suppressedBones = new HashSet<>();

   public SquitchgunItemRenderer() {
      super(new SquitchgunItemModel());
   }

   public RenderType getRenderType(SquitchgunItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }

   public void renderByItem(
      ItemStack stack, ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int p_239207_6_
   ) {
      this.transformType = transformType;
      if (this.animatable != null) {
         this.animatable.getTransformType(transformType);
      }

      super.renderByItem(stack, transformType, matrixStack, bufferIn, combinedLightIn, p_239207_6_);
   }

   public void actuallyRender(
      PoseStack matrixStackIn,
      SquitchgunItem animatable,
      BakedGeoModel model,
      RenderType type,
      MultiBufferSource renderTypeBuffer,
      VertexConsumer vertexBuilder,
      boolean isRenderer,
      float partialTicks,
      int packedLightIn,
      int packedOverlayIn,
      float red,
      float green,
      float blue,
      float alpha
   ) {
      this.currentBuffer = renderTypeBuffer;
      this.renderType = type;
      this.animatable = animatable;
      super.actuallyRender(
         matrixStackIn,
         animatable,
         model,
         type,
         renderTypeBuffer,
         vertexBuilder,
         isRenderer,
         partialTicks,
         packedLightIn,
         packedOverlayIn,
         ARGB32.color((int)(alpha * 255.0F), (int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F))
      );
      if (this.renderArms) {
         this.renderArms = false;
      }
   }

   public ResourceLocation getTextureLocation(SquitchgunItem instance) {
      return super.getTextureLocation(instance);
   }
}
