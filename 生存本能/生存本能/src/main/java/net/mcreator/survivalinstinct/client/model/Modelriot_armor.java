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

public class Modelriot_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelriot_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelriot_armor(ModelPart root) {
      this.head = root.getChild("head");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
      this.left_shoe = root.getChild("left_shoe");
      this.right_shoe = root.getChild("right_shoe");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(26, 25)
            .addBox(-5.0F, -5.0F, -1.0F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.6F))
            .texOffs(24, 15)
            .addBox(-5.0F, -5.5F, -5.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.3F))
            .texOffs(47, 37)
            .addBox(-5.0F, -4.5F, -5.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(-5.0F, -9.5F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(0, 31)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(64, 23)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 63)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(0, 63)
            .mirror()
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .mirror(false)
            .texOffs(64, 23)
            .mirror()
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(62, 0)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 17)
            .addBox(-4.0F, 3.0F, -3.4F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(68, 67)
            .addBox(-4.0F, 5.0F, -3.4F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(68, 34)
            .addBox(-4.0F, 7.0F, -3.4F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(66, 14)
            .addBox(-4.0F, 9.0F, -3.4F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(28, 50)
            .addBox(-2.0F, 4.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(40, 13)
            .addBox(-3.0F, 2.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(28, 48)
            .addBox(-3.0F, 0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(0, 7)
            .addBox(-2.0F, 4.5F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 64)
            .addBox(-3.0F, 0.5F, -0.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 10.5F, 2.5F, 0.3054F, 0.0F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(0, 7)
            .addBox(-2.0F, 4.5F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(28, 50)
            .addBox(-2.0F, 4.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(40, 13)
            .addBox(-3.0F, 2.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(28, 48)
            .addBox(-3.0F, 0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(12, 64)
            .addBox(-3.0F, 0.5F, -0.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 10.5F, -2.5F, -0.2618F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(66, 14)
            .addBox(-4.0F, 2.5F, -0.95F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(68, 34)
            .addBox(-4.0F, 0.5F, -0.95F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(68, 67)
            .addBox(-4.0F, -1.5F, -0.95F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(70, 17)
            .addBox(-4.0F, -3.5F, -0.95F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
            .texOffs(62, 0)
            .addBox(-4.0F, -4.5F, -0.55F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, 2.55F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(24, 15)
            .addBox(0.5F, -6.5F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.5F, 2.5F, -3.0F, -0.0886F, -0.1739F, 0.1899F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(54, 76)
            .mirror()
            .addBox(-1.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(76, 47)
            .mirror()
            .addBox(-1.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(16, 48)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(52, 54).mirror().addBox(-0.5F, -2.0F, -3.0F, 3.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.5F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-2.5F, -4.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(-0.3F)).mirror(false),
         PartPose.offsetAndRotation(2.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(16, 48)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(76, 57)
            .addBox(-3.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(54, 76)
            .addBox(-3.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(76, 47)
            .addBox(-3.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(52, 54).addBox(-2.5F, -2.0F, -3.0F, 3.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.5F, 6.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(40, 0).addBox(-2.5F, -4.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-2.5F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(70, 37)
            .addBox(-1.9F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(30, 0)
            .addBox(-1.9F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(42, 44)
            .mirror()
            .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.4F)).mirror(false),
         PartPose.offsetAndRotation(0.1F, 5.0F, -1.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(42, 44)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(42, 73)
            .addBox(-2.1F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(68, 72)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(-0.1F, 5.0F, -1.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create()
            .texOffs(66, 77)
            .addBox(-1.0F, -2.25F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.45F))
            .texOffs(30, 69)
            .addBox(-1.0F, -2.25F, -2.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.1F, 2.25F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(14, 69)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
            .texOffs(24, 21)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(56, 67)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(24, 21)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(14, 69)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false)
            .texOffs(56, 67)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
