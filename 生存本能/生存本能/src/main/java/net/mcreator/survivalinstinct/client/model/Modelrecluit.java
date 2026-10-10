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

public class Modelrecluit<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrecluit"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart right_shoe;
   public final ModelPart left_shoe;

   public Modelrecluit(ModelPart root) {
      this.head = root.getChild("head");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
      this.right_shoe = root.getChild("right_shoe");
      this.left_shoe = root.getChild("left_shoe");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(0, 27)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 82)
            .addBox(-5.0F, -9.0F, -2.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(0, 27)
            .addBox(-5.5F, -4.5F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F))
            .texOffs(76, 2)
            .addBox(4.5F, -4.5F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(76, 8).mirror().addBox(-1.0F, -0.5F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false),
         PartPose.offsetAndRotation(5.5F, -3.0F, -0.5F, 0.0F, 0.0F, -0.5236F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(18, 66).addBox(-4.0676F, -1.3313F, -5.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.1913F, -5.2093F, -4.0F, 0.0807F, 0.0334F, -0.3914F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(0, 66).addBox(1.0968F, -1.261F, -5.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.1913F, -5.2093F, -4.0F, 0.0807F, -0.0334F, 0.3914F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(24, 59).addBox(-2.1913F, -0.7907F, -5.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.1913F, -5.2093F, -4.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, 0.5F, 12.4F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -17.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(0, 21)
            .addBox(-2.0F, 0.5F, 3.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 15)
            .addBox(-5.0F, 0.5F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 0)
            .addBox(-5.0F, -2.5F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(76, 8).addBox(0.0F, -0.5F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-5.5F, -3.0F, -0.5F, 0.0F, 0.0F, 0.5236F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(32, 27)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(56, 41)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(86, 0)
            .addBox(-5.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(86, 0)
            .mirror()
            .addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(0, 97)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(42, 98)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(27, 98)
            .addBox(-3.0F, 1.0F, -3.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.8F))
            .texOffs(27, 98)
            .addBox(-3.0F, 6.0F, -3.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.8F))
            .texOffs(27, 98)
            .addBox(-3.0F, 1.0F, 1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.8F))
            .texOffs(92, 45)
            .mirror()
            .addBox(1.0F, 1.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(58, 53)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(92, 45)
            .addBox(-4.0F, 1.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(92, 45)
            .addBox(-4.0F, 1.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 97)
            .mirror()
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(58, 53)
            .mirror()
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .mirror(false)
            .texOffs(92, 45)
            .mirror()
            .addBox(1.0F, 1.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(42, 98)
            .mirror()
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 9.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(12, 66)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(40, 68)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(44, 43)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(68, 24)
            .mirror()
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(74, 24)
            .mirror()
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(5.0F, 9.0F, -0.05F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(76, 34)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(30, 27)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(0, 66)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(52, 0)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(30, 27)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(0, 66)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0F, 2.25F, -3.7F, 0.0873F, -0.0435F, -0.0038F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(52, 0)
            .mirror()
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 66)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(30, 27)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(76, 34)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0F, 9.25F, -3.7F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(40, 68)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(12, 66)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(68, 24)
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(44, 43)
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(74, 24)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-5.0F, 9.0F, -0.05F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(30, 66)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(52, 71)
            .mirror()
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(24, 73)
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(70, 53)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(33, 75)
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(33, 75)
            .mirror()
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(52, 71)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(24, 73)
            .mirror()
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 9.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(52, 12).addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(52, 12)
            .mirror()
            .addBox(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(40, 0)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(74, 65)
            .mirror()
            .addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.25F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(40, 0)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(74, 65)
            .addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.25F))
            .texOffs(64, 48)
            .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(64, 11)
            .addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(0, 0)
            .addBox(-4.0F, 1.0F, -1.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(24, 27)
            .addBox(-4.0F, 2.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(0, 43)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(90, 19)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(107, 27)
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(0, 43)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(90, 19)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(107, 27)
            .mirror()
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
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
      this.right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
