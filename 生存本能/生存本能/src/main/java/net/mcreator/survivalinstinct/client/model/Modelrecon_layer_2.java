package net.mcreator.survivalinstinct.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Modelrecon_layer_2<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrecon_layer_2"), "main");
   public final ModelPart waist;
   public final ModelPart left_leg;
   public final ModelPart right_leg;

   public Modelrecon_layer_2(ModelPart root) {
      this.waist = root.getChild("waist");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition waist = partdefinition.addOrReplaceChild(
         "waist",
         CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(0, 25)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(24, 0)
            .addBox(-4.0F, 1.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(18, 10)
            .addBox(-3.0F, 1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(18, 19)
            .addBox(-2.0F, -1.0F, -3.0F, 3.0F, 7.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 16)
            .addBox(-3.0F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 64, 64);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.waist.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
