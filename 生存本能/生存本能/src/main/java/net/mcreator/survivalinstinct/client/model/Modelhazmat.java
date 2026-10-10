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

public class Modelhazmat<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelhazmat"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart right_shoe;
   public final ModelPart left_shoe;

   public Modelhazmat(ModelPart root) {
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
            .texOffs(0, 0)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F))
            .texOffs(0, 24)
            .addBox(-5.0F, -3.0F, -1.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(29, 21)
            .addBox(-4.0F, -5.0F, -5.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.45F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(99, 49)
            .addBox(-1.6736F, -0.8573F, -1.3264F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(116, 62)
            .addBox(-1.6736F, 0.1427F, -1.3264F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(108, 68)
            .addBox(-1.6736F, 1.6903F, -1.2839F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(108, 74)
            .addBox(-1.6736F, 1.4903F, -1.3264F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 0.2025F, -4.5425F, -1.1111F, 0.4176F, -0.6863F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(0, 32)
            .mirror()
            .addBox(3.0F, 0.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.65F))
            .mirror(false)
            .texOffs(0, 32)
            .addBox(-4.0F, 0.5F, 1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.65F)),
         PartPose.offsetAndRotation(0.0F, -5.5F, -2.5F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(24, 0).addBox(-5.0F, -1.0F, -3.5F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -1.0F, -2.5F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(0, 95).addBox(-4.0F, -1.0F, -7.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-2.0F, -5.0F, 2.0F, 0.0F, 0.0F, 1.5708F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create()
            .texOffs(0, 95)
            .mirror()
            .addBox(-4.0F, -1.0F, -7.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(-0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(2.0F, -5.0F, 2.0F, 0.0F, 0.0F, -1.5708F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(27, 11).addBox(-5.0F, -1.5F, -3.5F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -5.5F, -2.5F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(28, 28)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.15F))
            .texOffs(26, 18)
            .addBox(-3.0F, 3.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(32, 7)
            .addBox(-3.0F, 5.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(32, 9)
            .addBox(-3.0F, 7.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(39, 19)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(62, 61)
            .addBox(-5.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(60, 34)
            .addBox(-5.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(60, 34)
            .mirror()
            .addBox(2.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(62, 61)
            .mirror()
            .addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(0, 73)
            .addBox(-3.0F, 9.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(78, 13)
            .addBox(-3.0F, 7.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 66)
            .addBox(-3.0F, 5.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 68)
            .addBox(-3.0F, 3.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(47, 85)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(47, 85)
            .mirror()
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(10, 86)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(10, 86)
            .mirror()
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(52, 5)
            .addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(66, 10)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(48, 56)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(66, 10)
            .mirror()
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create().texOffs(64, 80).mirror().addBox(-1.0F, 3.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)).mirror(false),
         PartPose.offsetAndRotation(-2.0F, 4.5F, 5.0F, 0.0F, 0.7854F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(74, 70)
            .mirror()
            .addBox(-2.0F, -1.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(0, 75)
            .mirror()
            .addBox(-2.0F, -4.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.5F))
            .mirror(false),
         PartPose.offsetAndRotation(-2.0F, 8.5F, 4.0F, 0.0F, -0.7854F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(64, 80).addBox(-2.0F, 3.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(2.0F, 4.5F, 5.0F, 0.0F, -0.7854F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(16, 56)
            .mirror()
            .addBox(-2.0F, -4.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(72, 34)
            .mirror()
            .addBox(-2.0F, -3.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(-2.0F, 4.5F, 4.0F, 0.0F, 0.7854F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(72, 34)
            .addBox(-2.0F, -1.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(16, 56)
            .addBox(-2.0F, -2.5F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.0F, 2.5F, 4.0F, 0.0F, -0.7854F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(74, 70)
            .addBox(-2.0F, -1.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(0, 75)
            .addBox(-2.0F, -4.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(2.0F, 8.5F, 4.0F, 0.0F, 0.7854F, 0.0F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(84, 54)
            .mirror()
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(80, 49)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(85, 83)
            .mirror()
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(0, 80)
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(52, 34)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, -2.5F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(0, 80)
            .mirror()
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(85, 83)
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(84, 54)
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, -2.5F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(46, 7)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 6)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(0, 16)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(24, 32)
            .mirror()
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(16, 40)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 24)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(26, 20)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(64, 43)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.25F, -3.7F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(24, 32)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 16)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 6)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(46, 7)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 9.25F, -3.7F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(68, 28)
            .mirror()
            .addBox(-1.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(52, 18)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create()
            .texOffs(94, 5)
            .mirror()
            .addBox(2.3F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(68, 52)
            .mirror()
            .addBox(-1.7F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false)
            .texOffs(107, 5)
            .mirror()
            .addBox(2.3F, -2.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.5F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create().texOffs(95, 17).mirror().addBox(-2.7F, -0.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(52, 18)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(68, 28)
            .addBox(-3.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create()
            .texOffs(107, 5)
            .addBox(-4.3F, -2.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.5F))
            .texOffs(94, 5)
            .addBox(-4.3F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(68, 52)
            .addBox(-2.3F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-1.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(95, 17).addBox(-3.3F, -0.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(0, 48)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(40, 76)
            .mirror()
            .addBox(-2.0F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(60, 70)
            .mirror()
            .addBox(-1.75F, -2.5F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(52, 77)
            .mirror()
            .addBox(-0.75F, -2.5F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(2.75F, 2.5F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(40, 76)
            .addBox(-2.0F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(0, 48)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(52, 77)
            .addBox(-1.25F, -2.5F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(60, 70)
            .addBox(-1.25F, -2.5F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-2.75F, 2.5F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create().texOffs(97, 28).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create().texOffs(97, 28).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false),
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
