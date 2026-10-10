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

public class Modeljuggernaut2<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modeljuggernaut_2"), "main");
   public final ModelPart waist;
   public final ModelPart left_leg;
   public final ModelPart right_leg;

   public Modeljuggernaut2(ModelPart root) {
      this.waist = root.getChild("waist");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition waist = partdefinition.addOrReplaceChild(
         "waist",
         CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(16, 16)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(28, 15)
            .addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(24, 10)
            .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(16, 29)
            .addBox(-1.8F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(26, 29)
            .addBox(-1.8F, 4.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(16, 32)
            .addBox(-1.8F, 3.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(16, 29)
            .addBox(-1.8F, 5.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(26, 29)
            .addBox(-1.8F, 4.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(16, 32)
            .addBox(-1.8F, 3.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 29)
            .addBox(1.2F, 1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.1F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(24, 0)
            .addBox(-2.2F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(24, 5)
            .addBox(-2.2F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(32, 20)
            .addBox(-2.0F, 4.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(26, 32)
            .addBox(-2.0F, 3.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(32, 23)
            .addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(26, 32)
            .addBox(-2.0F, 3.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(32, 20)
            .addBox(-2.0F, 4.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(32, 23)
            .addBox(-2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(8, 29)
            .addBox(-3.0F, 1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.1F)),
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
