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

public class Modelrockie3<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrockie_3"), "main");
   public final ModelPart helmet;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;

   public Modelrockie3(ModelPart root) {
      this.helmet = root.getChild("helmet");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition helmet = partdefinition.addOrReplaceChild(
         "helmet",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-5.0F, -9.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 30)
            .addBox(-4.0F, -9.0F, -5.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(16, 20)
            .addBox(1.0F, -9.0F, -5.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(53, 57)
            .addBox(-6.0F, -5.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.4F))
            .texOffs(21, 15)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(40, 53)
            .addBox(-5.0F, -6.0F, -2.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(29, 51)
            .addBox(3.0F, -6.0F, -2.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(36, 60)
            .addBox(4.0F, -6.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(44, 62)
            .addBox(-5.0F, -6.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(0, 56)
            .addBox(4.0F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(58, 4)
            .addBox(-5.0F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(20, 48)
            .addBox(4.0F, -6.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(54, 35)
            .addBox(-5.0F, -6.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(20, 45)
            .addBox(-5.0F, -6.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(46, 35)
            .addBox(4.0F, -6.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(30, 0)
            .addBox(-5.0F, -5.0F, 1.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(-0.4F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = helmet.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(16, 35)
            .addBox(-1.0F, -1.0F, -3.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.35F))
            .texOffs(31, 47)
            .addBox(-1.0F, -1.0F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.45F))
            .texOffs(38, 71)
            .addBox(-1.0F, -1.0F, -2.25F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(-5.0F, -6.0F, -0.75F, 0.0852F, 0.0189F, -0.2174F)
      );
      PartDefinition head_r2 = helmet.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(81, 79)
            .addBox(1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(9, 82)
            .addBox(5.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(64, 34)
            .addBox(1.0F, -2.0F, -2.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(-4.0F, -5.0F, 6.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r3 = helmet.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(0, 56).addBox(-1.0F, -2.75F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(5.0F, -2.25F, -0.5F, -3.1416F, 0.0F, 3.1416F)
      );
      PartDefinition head_r4 = helmet.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(82, 16)
            .addBox(-1.7874F, -0.5205F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(64, 25)
            .addBox(-1.7874F, -1.5205F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(5.0F, -2.25F, -0.5F, -3.1416F, 0.0F, 2.6616F)
      );
      PartDefinition head_r5 = helmet.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create()
            .texOffs(19, 82)
            .addBox(-1.5F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(64, 53)
            .addBox(-1.5F, -2.0F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(-5.5F, -2.0F, -0.5F, 0.0F, 0.0F, 0.48F)
      );
      PartDefinition head_r6 = helmet.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(79, 31)
            .addBox(0.5F, 0.1678F, -2.7992F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.7F))
            .texOffs(0, 79)
            .addBox(-3.5F, 0.1678F, -2.7992F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.7F))
            .texOffs(81, 67)
            .addBox(0.5F, -1.8322F, -2.7992F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.7F))
            .texOffs(81, 38)
            .addBox(-3.5F, -1.8322F, -2.7992F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.7F))
            .texOffs(65, 3)
            .addBox(-2.5F, 0.0983F, -1.8407F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 63)
            .addBox(1.5F, 0.0983F, -1.8407F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 15)
            .addBox(1.5F, -1.3322F, -1.7992F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(21, 15)
            .addBox(-2.5F, -1.3322F, -1.7992F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(56, 25)
            .addBox(-2.5F, -0.8322F, -0.7992F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5188F, -6.0761F, -1.4399F, 0.0F, 0.0F)
      );
      PartDefinition head_r7 = helmet.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(22, 69).addBox(-0.5F, -0.3793F, 0.1235F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5188F, -6.0761F, -0.9163F, 0.0F, 0.0F)
      );
      PartDefinition head_r8 = helmet.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(64, 69).addBox(-1.5F, 0.222F, 1.3159F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5188F, -6.0761F, -0.4363F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(52, 35)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(51, 47)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(66, 82)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(29, 83)
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(46, 82)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(56, 82)
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(45, 8)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(63, 44)
            .addBox(3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(63, 0)
            .addBox(3.0F, 5.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(26, 62)
            .addBox(-5.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(42, 62)
            .addBox(-5.0F, 5.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(80, 74)
            .addBox(-3.0F, 3.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(80, 60)
            .addBox(-3.0F, 5.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(80, 58)
            .addBox(-3.0F, 7.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 29)
            .addBox(-3.0F, 9.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(30, 8)
            .addBox(-3.0F, 3.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(52, 44)
            .addBox(-3.0F, 5.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(79, 49)
            .addBox(-3.0F, 7.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(79, 51)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 45)
            .addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(41, 42)
            .addBox(-5.0F, -1.75F, -3.25F, 3.0F, 6.0F, 5.0F, new CubeDeformation(-0.8F))
            .texOffs(0, 0)
            .addBox(-5.0F, -1.75F, -2.25F, 3.0F, 6.0F, 2.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 30)
            .addBox(2.0F, -1.75F, -2.25F, 3.0F, 6.0F, 2.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 66)
            .addBox(-5.0F, -0.75F, -3.25F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.7F))
            .texOffs(64, 62)
            .addBox(-5.0F, 1.25F, -3.25F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.7F))
            .texOffs(53, 67)
            .addBox(2.0F, 1.25F, -3.25F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.7F))
            .texOffs(11, 68)
            .addBox(2.0F, -0.75F, -3.25F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.7F))
            .texOffs(20, 47)
            .addBox(2.0F, -1.75F, -3.25F, 3.0F, 6.0F, 5.0F, new CubeDeformation(-0.8F))
            .texOffs(26, 35)
            .addBox(-4.0F, -5.75F, -2.25F, 8.0F, 10.0F, 2.0F, new CubeDeformation(-0.5F))
            .texOffs(0, 15)
            .addBox(-4.0F, -5.75F, -3.25F, 8.0F, 10.0F, 5.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, 5.25F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(73, 41)
            .addBox(-3.0F, -1.5287F, -1.029F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.6F))
            .texOffs(48, 74)
            .addBox(1.0F, -1.5287F, -1.029F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.6F))
            .texOffs(32, 20)
            .addBox(-4.0F, -1.5287F, -1.029F, 8.0F, 4.0F, 4.0F, new CubeDeformation(-0.8F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, 5.25F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(26, 76)
            .addBox(-3.0F, -4.5633F, 0.0983F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(77, 6)
            .addBox(1.0F, -4.5633F, 0.0983F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(42, 28)
            .addBox(-4.0F, -4.5633F, 0.0983F, 8.0F, 4.0F, 3.0F, new CubeDeformation(-0.8F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, 5.25F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(54, 0)
            .addBox(-2.0F, 1.1286F, 0.1025F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(27, 71)
            .addBox(-3.0F, 1.1286F, 0.1025F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, 5.25F, -0.2182F, 0.0F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(14, 75)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(73, 14)
            .addBox(-6.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(75, 53)
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(75, 22)
            .addBox(-6.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(73, 0)
            .addBox(-3.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(76, 83)
            .addBox(-3.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F))
            .texOffs(0, 84)
            .addBox(0.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F))
            .texOffs(0, 73)
            .addBox(-3.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(1.0F, 9.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(30, 0)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(52, 19)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 8)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(84, 5)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 9.25F, -3.7F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(32, 20)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(40, 53)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(32, 28)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(85, 43)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.25F, -3.7F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(40, 10)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(86, 12)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 38)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(11, 56)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(75, 62)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(46, 35)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(60, 74)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(5.0F, 9.0F, -0.05F, 3.1416F, 0.0F, -3.0107F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(68, 74).addBox(-1.0F, -2.0F, -1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(5.0F, 9.0F, 0.95F, 3.1416F, 0.0F, -3.0107F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(56, 74).addBox(-1.0F, -2.0F, 0.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(5.0F, 9.0F, -1.05F, 3.1416F, 0.0F, -3.0107F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(36, 62)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(39, 83)
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(83, 62)
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(36, 78)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(72, 74)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-5.0F, 9.0F, -0.05F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(100, 106).addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(108, 74).addBox(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(61, 15)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(69, 69)
            .addBox(-2.0F, 11.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(16, 58)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(65, 9)
            .addBox(-2.0F, 11.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.helmet.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
