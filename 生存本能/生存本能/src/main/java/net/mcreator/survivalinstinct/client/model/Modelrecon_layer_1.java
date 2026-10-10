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

public class Modelrecon_layer_1<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrecon_layer_1"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelrecon_layer_1(ModelPart root) {
      this.head = root.getChild("head");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_shoe = root.getChild("left_shoe");
      this.right_shoe = root.getChild("right_shoe");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(0, 92)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01F))
            .texOffs(0, 0)
            .addBox(-5.0F, -9.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(40, 23)
            .addBox(-3.0F, -7.0F, 4.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(16, 41)
            .addBox(-2.0F, -6.0F, 4.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.1F))
            .texOffs(16, 44)
            .addBox(2.0F, -7.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 44)
            .addBox(-3.0F, -7.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 38)
            .addBox(-4.0F, -9.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(-0.1F))
            .texOffs(62, 51)
            .addBox(-5.0F, -7.0F, -3.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(60, 61)
            .addBox(4.0F, -7.0F, -3.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(30, 28)
            .addBox(1.0F, -9.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(-0.1F))
            .texOffs(56, 5)
            .addBox(-5.0F, -9.0F, -1.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(54, 0)
            .addBox(-5.0F, -6.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.45F))
            .texOffs(0, 27)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 15)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.4F))
            .texOffs(30, 0)
            .addBox(-5.0F, -4.4F, 1.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 15)
            .addBox(-5.0F, -4.4F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(28, 59)
            .addBox(-5.3F, -3.4F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.5F))
            .texOffs(56, 39)
            .addBox(4.3F, -3.4F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.5F))
            .texOffs(0, 0)
            .addBox(4.0F, -4.4F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(93, 42)
            .addBox(1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(93, 42)
            .addBox(-2.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -7.4957F, -5.9347F, -0.4363F, 0.0F, 0.0F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(102, 60)
            .addBox(-3.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(104, 57)
            .addBox(-6.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(104, 54)
            .addBox(-6.0F, -1.0F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.0F, -7.7132F, -6.5904F, -1.0908F, 0.0F, 0.0F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create()
            .texOffs(106, 66)
            .addBox(-4.0F, -1.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(106, 66)
            .addBox(0.0F, -1.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(1.0F, -7.7132F, -8.5904F, -1.0908F, 0.0F, 0.0F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(106, 66)
            .addBox(-4.0F, -1.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(106, 66)
            .addBox(0.0F, -1.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(107, 58)
            .addBox(0.0F, -2.0F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(107, 58)
            .addBox(-4.0F, -2.0F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(1.0F, -8.7132F, -6.5904F, -1.0908F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(26, 43)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(64, 10)
            .addBox(-4.0F, 2.0F, 2.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 75)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(72, 69)
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(72, 49)
            .addBox(2.0F, 7.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(72, 28)
            .addBox(2.0F, 9.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(56, 71)
            .addBox(-5.0F, 9.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(72, 20)
            .addBox(-5.0F, 7.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(44, 69)
            .addBox(-5.0F, 4.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(70, 61)
            .addBox(-5.0F, 2.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(0, 69)
            .addBox(2.0F, 2.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(26, 69)
            .addBox(2.0F, 4.2F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(32, 59)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(50, 59)
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(66, 39)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 4)
            .addBox(-3.0F, 3.0F, -3.4F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(78, 0)
            .addBox(-3.0F, 6.0F, -3.4F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(47, 44)
            .addBox(-2.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(47, 35)
            .addBox(1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(1, 28)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(63, 80)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(39, 70)
            .addBox(0.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(31, 8)
            .addBox(-2.5F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(57, 11)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(45, 60)
            .addBox(0.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(1, 34)
            .addBox(0.5F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(30, 27)
            .addBox(0.5F, 7.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(31, 34)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(69, 21)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(23, 45)
            .addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(83, 9)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.0F, 9.5F, -3.5F, 0.0F, -0.3491F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(17, 54)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(1, 23)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(51, 36)
            .addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(83, 14)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(47, 29)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 9.5F, -3.5F, 0.0F, 0.3491F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(96, 13)
            .addBox(1.0F, -1.5F, -0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(96, 13)
            .addBox(4.0F, -1.5F, -0.5F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(97, 6)
            .addBox(0.0F, -2.5F, -0.5F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(101, 16)
            .addBox(0.0F, -2.5F, -0.5F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 12.5F, 2.5F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(65, 112)
            .addBox(-3.0F, -4.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(65, 105)
            .addBox(-3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(83, 89)
            .addBox(-5.0F, 0.0F, -3.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F))
            .texOffs(83, 97)
            .addBox(2.0F, 0.0F, -3.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F))
            .texOffs(63, 91)
            .addBox(-3.0F, -5.0F, -3.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 5.0F, 5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(86, 108).addBox(3.3F, 0.0F, 0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(83, 106).addBox(2.0F, 0.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 2.0F, 5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(16, 59)
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(46, 79)
            .addBox(-1.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.35F))
            .texOffs(70, 77)
            .addBox(-1.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.35F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(56, 23)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(0, 79)
            .addBox(-3.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.35F))
            .texOffs(30, 77)
            .addBox(-3.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.35F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create().texOffs(0, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create().texOffs(50, 43).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)),
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
      this.left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
