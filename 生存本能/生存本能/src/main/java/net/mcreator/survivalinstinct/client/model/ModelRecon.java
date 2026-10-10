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

public class ModelRecon<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "model_recon"), "main");
   public final ModelPart helmet;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart right_leg;
   public final ModelPart left_leg;

   public ModelRecon(ModelPart root) {
      this.helmet = root.getChild("helmet");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_shoe = root.getChild("left_shoe");
      this.right_shoe = root.getChild("right_shoe");
      this.right_leg = root.getChild("right_leg");
      this.left_leg = root.getChild("left_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition helmet = partdefinition.addOrReplaceChild(
         "helmet",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-5.0F, -9.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 30)
            .addBox(-4.0F, -9.0F, -5.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
            .texOffs(16, 20)
            .addBox(1.0F, -9.0F, -5.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
            .texOffs(53, 57)
            .addBox(-6.0F, -5.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.4F))
            .texOffs(21, 15)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
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
            .texOffs(45, 104)
            .mirror()
            .addBox(1.0F, -1.75F, -3.25F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(46, 109)
            .addBox(-1.0F, -1.75F, -3.25F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(45, 104)
            .addBox(-5.0F, -1.75F, -3.25F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(44, 113)
            .addBox(-5.0F, -0.75F, -2.25F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -8.25F, -2.75F, -0.2182F, 0.0F, 0.0F)
      );
      PartDefinition head_r2 = helmet.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(87, 0)
            .addBox(-1.0F, -1.0F, -3.65F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.5F))
            .texOffs(16, 35)
            .addBox(-1.0F, -1.0F, -3.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.32F)),
         PartPose.offsetAndRotation(-5.0F, -6.0F, -2.75F, 0.0737F, 0.0468F, -0.5655F)
      );
      PartDefinition head_r3 = helmet.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create()
            .texOffs(31, 47)
            .addBox(-1.0F, -1.0F, 0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(38, 71)
            .addBox(-1.0F, -1.0F, -2.25F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-5.0F, -6.0F, -1.75F, 0.0737F, 0.0468F, -0.5655F)
      );
      PartDefinition head_r4 = helmet.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(81, 79)
            .addBox(1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(9, 82)
            .addBox(5.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(64, 34)
            .addBox(1.0F, -2.0F, -2.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(-4.0F, -5.0F, 5.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = helmet.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 56).addBox(-1.0F, -2.75F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(5.0F, -2.25F, -0.5F, -3.1416F, 0.0F, 3.1416F)
      );
      PartDefinition head_r6 = helmet.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(82, 16)
            .addBox(-1.7874F, -0.5205F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(64, 25)
            .addBox(-1.7874F, -1.5205F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(5.0F, -2.25F, -0.5F, -3.1416F, 0.0F, 2.6616F)
      );
      PartDefinition head_r7 = helmet.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create()
            .texOffs(19, 82)
            .addBox(-1.5F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(64, 53)
            .addBox(-1.5F, -2.0F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(-5.5F, -2.0F, -0.5F, 0.0F, 0.0F, 0.48F)
      );
      PartDefinition head_r8 = helmet.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create()
            .texOffs(100, 9)
            .addBox(2.2923F, -1.0F, 1.248F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(97, 1)
            .addBox(2.2923F, -1.0F, -0.752F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-4.0F, -1.0F, -2.5F, 1.5862F, 1.2231F, 1.5554F)
      );
      PartDefinition head_r9 = helmet.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(97, 1).addBox(-1.7654F, -1.0F, -3.3478F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-4.0F, -1.0F, -2.5F, 0.3626F, -0.2814F, -0.1367F)
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
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(63, 44)
            .addBox(3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(63, 0)
            .addBox(3.0F, 5.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(63, 0)
            .addBox(3.0F, 2.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(26, 62)
            .addBox(-5.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(80, 74)
            .addBox(-3.0F, 3.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 60)
            .addBox(-3.0F, 5.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 60)
            .addBox(-3.0F, 9.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 74)
            .addBox(-3.0F, 7.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(30, 8)
            .addBox(-3.0F, 3.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(52, 44)
            .addBox(-3.0F, 5.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(79, 49)
            .addBox(-3.0F, 7.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(79, 51)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 45)
            .addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(63, 0)
            .mirror()
            .addBox(-5.0F, 5.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(63, 0)
            .mirror()
            .addBox(-5.0F, 2.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false),
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
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(73, 14)
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(75, 53)
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F))
            .texOffs(75, 22)
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(73, 0)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(76, 83)
            .addBox(-1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 84)
            .addBox(0.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 73)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 9.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(84, 5)
            .addBox(-1.0215F, -2.2198F, -0.9592F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 8)
            .addBox(-1.0215F, -1.2198F, -0.1592F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(52, 19)
            .addBox(-1.0215F, -2.2198F, -0.1592F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(30, 0)
            .addBox(-0.5215F, -0.2198F, -0.7592F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(3.0F, 3.2329F, -3.6368F, -0.0492F, -0.3458F, 0.0316F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(30, 0)
            .mirror()
            .addBox(-0.4785F, -0.2198F, -0.7592F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(52, 19)
            .mirror()
            .addBox(-0.9785F, -2.2198F, -0.1592F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(0, 8)
            .mirror()
            .addBox(-0.9785F, -1.2198F, -0.1592F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(84, 5)
            .mirror()
            .addBox(-0.9785F, -2.2198F, -0.9592F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.0F, 3.2329F, -3.6368F, -0.0492F, 0.3458F, -0.0316F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(30, 0)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
            .texOffs(52, 19)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(0, 8)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(84, 5)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(3.0F, 9.25F, -3.7F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(32, 20)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
            .texOffs(40, 53)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(32, 28)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(85, 43)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, 9.25F, -3.7F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(40, 10)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F))
            .texOffs(86, 12)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 38)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(11, 56)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(75, 62)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(68, 74)
            .addBox(-1.0F, -2.0F, -1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(56, 74)
            .addBox(-1.0F, -2.0F, 0.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(46, 35)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(60, 74)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(5.0F, 9.0F, -0.05F, 3.1416F, 0.0F, -3.0107F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(36, 62)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(39, 83)
            .addBox(-1.0F, -2.0F, -1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(83, 62)
            .addBox(-1.0F, -2.0F, 0.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 78)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(72, 74)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-5.0F, 9.0F, -0.05F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(112, 10)
            .mirror()
            .addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(112, 10).addBox(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create().texOffs(16, 58).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create().texOffs(16, 58).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(85, 107)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(109, 82)
            .addBox(-4.0F, 1.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(103, 92)
            .addBox(-3.0F, 1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(103, 101)
            .addBox(-2.0F, -1.0F, -3.0F, 3.0F, 7.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(85, 98)
            .addBox(-3.0F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(85, 107).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.helmet.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
