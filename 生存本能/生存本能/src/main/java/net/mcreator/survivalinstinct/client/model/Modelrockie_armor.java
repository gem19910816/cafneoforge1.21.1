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

public class Modelrockie_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrockie_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelrockie_armor(ModelPart root) {
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
            .texOffs(0, 0)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 16)
            .addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(66, 51)
            .addBox(-4.0F, -8.0F, 4.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(-1.5F, -8.0F, 4.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(74, 66)
            .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
            .texOffs(30, 61)
            .addBox(-1.5F, -8.0F, -5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(40, 15)
            .addBox(-1.5F, -9.0F, -4.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.3F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(0, 60)
            .addBox(-2.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(68, 0)
            .addBox(1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(69, 58).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5F, -4.0F, 5.0F, -0.0452F, -0.2615F, 0.0117F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(56, 90).addBox(4.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6903F, 0.0831F, 0.1183F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(52, 37)
            .addBox(0.2837F, -1.2608F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(54, 11)
            .addBox(0.2837F, -2.2608F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(4.4163F, -4.7392F, -5.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create()
            .texOffs(0, 84)
            .mirror()
            .addBox(-0.4163F, -1.2608F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.2182F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(48, 44)
            .addBox(-0.4163F, -1.2608F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(80, 86)
            .addBox(-0.4163F, 0.7392F, 1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(4.4163F, -6.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(8, 91).addBox(-5.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.412F, 2.912F, -1.0435F, -0.1133F, -0.0657F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(22, 91).addBox(-5.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6903F, -0.0831F, -0.1183F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(72, 92).addBox(4.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.412F, 2.912F, -1.0435F, 0.1133F, 0.0657F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(29, 90).addBox(4.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, -0.0076F, -0.0869F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(25, 70).addBox(-0.3353F, 0.4848F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create().texOffs(70, 36).addBox(-0.4042F, -1.4939F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create()
            .texOffs(46, 55)
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(35, 87)
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(8, 49)
            .addBox(-0.5837F, -1.2608F, -6.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.4163F, -6.7392F, 2.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.7595F, -4.1147F, 0.0F, 0.0F, -0.6109F, -0.0873F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create().texOffs(82, 70).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create()
            .texOffs(16, 41)
            .mirror()
            .addBox(-0.4163F, -1.2608F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create().texOffs(28, 52).addBox(-0.5958F, -1.4939F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.7595F, -4.1147F, 0.0F, 0.0F, 0.6109F, 0.0873F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create()
            .texOffs(58, 35)
            .addBox(-1.2837F, -1.2608F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(47, 55)
            .addBox(-1.2837F, -2.2608F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-4.4163F, -4.7392F, -5.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(9, 70).addBox(-0.6647F, 0.4848F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create().texOffs(24, 36).addBox(0.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(-4.1972F, -6.5038F, -4.87F, 0.0F, -0.6545F, 0.0F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create().texOffs(56, 11).addBox(0.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, 3.5F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create().texOffs(56, 59).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(3.9658F, -4.5038F, -4.1154F, 0.0983F, 0.478F, 0.0453F)
      );
      PartDefinition head_r24 = head.addOrReplaceChild(
         "head_r24",
         CubeListBuilder.create().texOffs(0, 41).addBox(-1.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(4.1972F, -6.5038F, -4.87F, 0.0F, 0.6545F, 0.0F)
      );
      PartDefinition head_r25 = head.addOrReplaceChild(
         "head_r25",
         CubeListBuilder.create().texOffs(44, 27).addBox(1.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, 0.7383F, -0.0797F)
      );
      PartDefinition head_r26 = head.addOrReplaceChild(
         "head_r26",
         CubeListBuilder.create().texOffs(68, 89).addBox(-1.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, -5.0F, 3.5F, 0.0F, 0.7418F, 0.0F)
      );
      PartDefinition head_r27 = head.addOrReplaceChild(
         "head_r27",
         CubeListBuilder.create().texOffs(18, 52).addBox(-0.3742F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition head_r28 = head.addOrReplaceChild(
         "head_r28",
         CubeListBuilder.create().texOffs(16, 41).addBox(-0.5837F, -1.2608F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r29 = head.addOrReplaceChild(
         "head_r29",
         CubeListBuilder.create().texOffs(0, 84).addBox(-1.5837F, -1.2608F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, 0.2182F)
      );
      PartDefinition head_r30 = head.addOrReplaceChild(
         "head_r30",
         CubeListBuilder.create().texOffs(18, 70).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition head_r31 = head.addOrReplaceChild(
         "head_r31",
         CubeListBuilder.create().texOffs(48, 90).addBox(-5.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, 0.0076F, 0.0869F)
      );
      PartDefinition head_r32 = head.addOrReplaceChild(
         "head_r32",
         CubeListBuilder.create().texOffs(87, 91).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -7.5F, -5.5F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition head_r33 = head.addOrReplaceChild(
         "head_r33",
         CubeListBuilder.create()
            .texOffs(32, 23)
            .addBox(-0.5F, -0.5F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(79, 40)
            .addBox(-3.0F, -0.5F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-1.0F, -8.5F, -4.5F, -0.5672F, 0.0F, 0.0F)
      );
      PartDefinition head_r34 = head.addOrReplaceChild(
         "head_r34",
         CubeListBuilder.create().texOffs(82, 20).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, -4.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r35 = head.addOrReplaceChild(
         "head_r35",
         CubeListBuilder.create().texOffs(78, 18).addBox(-4.0F, -0.494F, 0.75F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r36 = head.addOrReplaceChild(
         "head_r36",
         CubeListBuilder.create().texOffs(25, 70).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.5F, -4.0F, 5.0F, -0.0452F, 0.2615F, -0.0117F)
      );
      PartDefinition head_r37 = head.addOrReplaceChild(
         "head_r37",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .addBox(-2.0F, -2.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(14, 83)
            .addBox(-2.0F, -2.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r38 = head.addOrReplaceChild(
         "head_r38",
         CubeListBuilder.create()
            .texOffs(16, 47)
            .addBox(-0.5F, -0.5F, -0.8172F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(28, 80)
            .addBox(-3.0F, -0.5F, -0.8172F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.18F)),
         PartPose.offsetAndRotation(-1.0F, -8.2172F, 4.2172F, 0.7418F, 0.0F, 0.0F)
      );
      PartDefinition head_r39 = head.addOrReplaceChild(
         "head_r39",
         CubeListBuilder.create().texOffs(36, 52).addBox(-0.6258F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, 0.8727F)
      );
      PartDefinition head_r40 = head.addOrReplaceChild(
         "head_r40",
         CubeListBuilder.create().texOffs(59, 82).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-3.9658F, -4.5038F, -4.1154F, 0.0983F, -0.478F, -0.0453F)
      );
      PartDefinition head_r41 = head.addOrReplaceChild(
         "head_r41",
         CubeListBuilder.create().texOffs(52, 35).addBox(-2.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, -0.7383F, 0.0797F)
      );
      PartDefinition head_r42 = head.addOrReplaceChild(
         "head_r42",
         CubeListBuilder.create().texOffs(76, 9).addBox(-4.0F, 4.9969F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6545F, 0.0F, 0.0F)
      );
      PartDefinition visor_r1 = head.addOrReplaceChild(
         "visor_r1",
         CubeListBuilder.create().texOffs(114, 102).addBox(-2.0F, -0.5049F, -2.1728F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -7.2031F, -5.9513F, -1.1829F, -0.0968F, -0.0964F)
      );
      PartDefinition visor_r2 = head.addOrReplaceChild(
         "visor_r2",
         CubeListBuilder.create()
            .texOffs(112, 120)
            .addBox(-2.514F, 1.636F, -4.5617F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F))
            .texOffs(116, 55)
            .addBox(-2.514F, 1.636F, -0.5617F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(88, 104)
            .addBox(-2.514F, 1.636F, -2.5617F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(112, 52)
            .addBox(-2.3839F, 1.5488F, -4.5494F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(106, 110)
            .addBox(-2.3839F, 1.5488F, -4.9668F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, -7.2031F, -5.9513F, -2.289F, -0.2046F, -0.2284F)
      );
      PartDefinition visor_r3 = head.addOrReplaceChild(
         "visor_r3",
         CubeListBuilder.create()
            .texOffs(106, 110)
            .addBox(-1.0F, 1.5488F, -4.5146F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.3F))
            .texOffs(74, 101)
            .addBox(-1.0F, 1.5488F, -4.0971F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(110, 84)
            .addBox(-1.0F, 1.636F, -2.1009F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(92, 97)
            .addBox(-1.0F, 1.636F, -0.1009F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(77, 119)
            .addBox(-1.0F, 1.636F, -4.1009F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -7.2031F, -5.9513F, -2.3126F, 0.0F, 0.0F)
      );
      PartDefinition visor_r4 = head.addOrReplaceChild(
         "visor_r4",
         CubeListBuilder.create().texOffs(51, 108).addBox(-4.0F, 0.7628F, -1.7077F, 8.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -7.2031F, -5.9513F, -2.3998F, 0.0F, 0.0F)
      );
      PartDefinition visor_r5 = head.addOrReplaceChild(
         "visor_r5",
         CubeListBuilder.create().texOffs(98, 99).addBox(-1.0F, -1.2087F, -3.041F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -5.6502F, -5.0344F, -0.5236F, 0.0F, 0.0F)
      );
      PartDefinition visor_r6 = head.addOrReplaceChild(
         "visor_r6",
         CubeListBuilder.create().texOffs(114, 91).addBox(-2.0F, -0.713F, -2.0102F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -7.2628F, -4.1938F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition visor_r7 = head.addOrReplaceChild(
         "visor_r7",
         CubeListBuilder.create()
            .texOffs(50, 120)
            .mirror()
            .addBox(0.514F, 1.636F, -4.5617F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(116, 55)
            .mirror()
            .addBox(0.514F, 1.636F, -0.5617F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(88, 104)
            .mirror()
            .addBox(0.514F, 1.636F, -2.5617F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(112, 52)
            .mirror()
            .addBox(0.3839F, 1.5488F, -4.5494F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(106, 110)
            .mirror()
            .addBox(0.3839F, 1.5488F, -4.9668F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -7.2031F, -5.9513F, -2.289F, 0.2046F, 0.2284F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(42, 80)
            .addBox(-3.0F, -0.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(52, 35)
            .addBox(-4.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(6, 86)
            .addBox(-4.0F, 1.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.5F, 3.9347F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create().texOffs(28, 52).addBox(-2.0F, -1.7393F, -1.2215F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 3.5632F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(70, 44)
            .addBox(-4.9848F, -1.2657F, -0.3434F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F))
            .texOffs(89, 70)
            .addBox(-4.9848F, -1.2657F, -0.3434F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 3.5632F, -0.1334F, -0.2333F, -0.101F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(44, 0).addBox(3.5F, 0.4F, 0.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(18, 50)
            .addBox(2.0F, -0.6F, -0.9F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(34, 82)
            .addBox(2.0F, -0.6F, -0.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(3.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(68, 35)
            .addBox(-1.0F, -2.0F, -1.2614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(44, 16)
            .addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(58, 67)
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(3.0F, 9.4161F, -3.2386F, 3.0964F, 0.2615F, 3.1299F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(0, 78).addBox(-3.5F, -2.6F, -0.6F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 8.0F, -2.9F, 3.098F, 0.0F, 3.1416F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(28, 82)
            .addBox(2.0F, -0.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(24, 25)
            .addBox(-4.0F, -3.5F, -2.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(-0.15F))
            .texOffs(12, 60)
            .addBox(3.0F, 1.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(22, 86)
            .addBox(3.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.5F, 3.9347F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, -3.4981F, -1.9128F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 3.5F, 3.9347F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(48, 0)
            .addBox(-4.0F, -4.5015F, -1.4827F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F))
            .texOffs(84, 52)
            .addBox(-3.0F, -2.0015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(84, 33)
            .addBox(-3.0F, -3.5015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(79, 27)
            .addBox(-3.0F, -1.7393F, -1.2215F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(51, 76)
            .addBox(-3.0F, -1.7393F, -1.2215F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(37, 52)
            .addBox(1.0F, -1.7393F, -1.2215F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 3.5632F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(34, 72)
            .addBox(3.9848F, -1.2657F, -0.3434F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F))
            .texOffs(0, 90)
            .addBox(2.9848F, -1.2657F, -0.3434F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 3.5632F, -0.1334F, 0.2333F, 0.101F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create()
            .texOffs(76, 44)
            .addBox(-3.5F, -1.1167F, -0.2176F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(84, 54)
            .addBox(-3.0F, -0.5167F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(84, 31)
            .addBox(-3.0F, 0.9833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(83, 50)
            .addBox(-3.0F, 2.4833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create()
            .texOffs(90, 0)
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(44, 24)
            .addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(72, 86)
            .addBox(-1.0F, -2.0F, -1.2614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.0F, 9.4161F, -3.2386F, 3.0964F, -0.2615F, -3.1299F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create()
            .texOffs(86, 42)
            .addBox(-3.0F, 1.0F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(84, 56)
            .addBox(-3.0F, -2.0F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(83, 84)
            .addBox(-3.0F, -0.5F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(0.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create()
            .texOffs(69, 84)
            .addBox(-3.0F, -3.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(84, 64)
            .addBox(-3.0F, -2.0F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(48, 24)
            .addBox(-4.0F, -4.5F, -1.45F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, -2.55F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create()
            .texOffs(35, 72)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F))
            .texOffs(88, 86)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(60, 89)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(69, 58)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 0.95F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create().texOffs(0, 68).addBox(-2.0F, 0.05F, -3.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 5.95F, 1.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create().texOffs(56, 35).addBox(-4.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create().texOffs(58, 43).addBox(-2.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 7.95F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create()
            .texOffs(43, 88)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(14, 89)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(68, 0)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(72, 70)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-3.0F, 0.95F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create().texOffs(57, 59).addBox(-1.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 7.95F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r23 = body.addOrReplaceChild(
         "body_r23",
         CubeListBuilder.create().texOffs(0, 60).addBox(1.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r24 = body.addOrReplaceChild(
         "body_r24",
         CubeListBuilder.create().texOffs(68, 26).addBox(-1.0F, 0.05F, -3.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 5.95F, 1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(0, 41)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(78, 12)
            .mirror()
            .addBox(-1.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create()
            .texOffs(79, 22)
            .mirror()
            .addBox(-2.0F, -0.5F, -0.95F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0F, 4.5F, 0.95F, -3.1416F, 0.0F, -3.1416F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create().texOffs(34, 61).mirror().addBox(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create()
            .texOffs(0, 57)
            .addBox(-1.5915F, -1.0912F, -1.542F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(20, 25)
            .addBox(-1.5915F, -1.5912F, -1.542F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(1.0F, 4.5F, 0.95F, -3.0543F, 0.0873F, 3.1416F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(0, 41)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(78, 12)
            .addBox(-3.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create()
            .texOffs(48, 11)
            .addBox(-1.4085F, -1.5912F, -1.542F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(91, 35)
            .addBox(-1.4085F, -1.0912F, -1.542F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(-1.0F, 4.5F, 0.95F, -3.0543F, -0.0873F, -3.1416F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(79, 22).addBox(-2.0F, -0.5F, -0.95F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-1.0F, 4.5F, 0.95F, -3.1416F, 0.0F, 3.1416F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create().texOffs(34, 61).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(32, 0)
            .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(79, 35)
            .addBox(-1.9F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(63, 78)
            .addBox(-1.9F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(36, 36)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(91, 22)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.1F, 4.5F, -2.1F, 0.0873F, -0.0873F, 0.0F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create()
            .texOffs(44, 0)
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(34, 82)
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(18, 50)
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(3.1F, 4.9253F, -0.2885F, -0.0863F, 1.5272F, -0.001F)
      );
      PartDefinition left_leg_r3 = left_leg.addOrReplaceChild(
         "left_leg_r3",
         CubeListBuilder.create()
            .texOffs(44, 0)
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(34, 82)
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(18, 50)
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(3.1F, 0.9253F, -0.2885F, -0.0863F, 1.5272F, -0.001F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(75, 79)
            .addBox(-2.1F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(79, 58)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(24, 36)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(91, 58)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(90, 11)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.1F, 4.5F, -2.1F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create()
            .texOffs(58, 43)
            .addBox(-0.7412F, 0.0F, -1.4659F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 41)
            .addBox(-0.7412F, 0.0F, -1.4659F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
         PartPose.offsetAndRotation(-3.1F, 2.0F, -0.5F, 0.0925F, 0.348F, 0.024F)
      );
      PartDefinition right_leg_r3 = right_leg.addOrReplaceChild(
         "right_leg_r3",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .addBox(-1.0F, -3.0F, 0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.25F))
            .texOffs(56, 55)
            .addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(52, 82)
            .addBox(-1.0F, -1.0F, -0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.1F, 2.0F, -0.5F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(66, 17)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(54, 21)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(66, 17)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(54, 21)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(64, 7)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F)),
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
