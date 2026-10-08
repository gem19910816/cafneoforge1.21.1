package net.mcreator.gore.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.FastColor.ARGB32;
import software.bernie.geckolib.cache.object.GeoBone;

public class AnimUtils {
   public static void renderPartOverBone(
      ModelPart model, GeoBone bone, PoseStack stack, VertexConsumer buffer, int packedLightIn, int packedOverlayIn, float alpha
   ) {
      renderPartOverBone(model, bone, stack, buffer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, alpha);
   }

   public static void renderPartOverBone(
      ModelPart model, GeoBone bone, PoseStack stack, VertexConsumer buffer, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a
   ) {
      setupModelFromBone(model, bone);
      model.render(stack, buffer, packedLightIn, packedOverlayIn, ARGB32.color((int)(a * 255.0F), (int)(r * 255.0F), (int)(g * 255.0F), (int)(b * 255.0F)));
   }

   public static void setupModelFromBone(ModelPart model, GeoBone bone) {
      model.setPos(bone.getPivotX(), bone.getPivotY(), bone.getPivotZ());
      model.xRot = 0.0F;
      model.yRot = 0.0F;
      model.zRot = 0.0F;
   }
}
