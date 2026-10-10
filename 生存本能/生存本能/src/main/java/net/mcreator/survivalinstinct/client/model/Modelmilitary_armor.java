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

public class Modelmilitary_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelmilitary_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelmilitary_armor(ModelPart root) {
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
            .texOffs(62, 52)
            .addBox(-4.0F, -8.0F, 4.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(72, 48)
            .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.05F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(1, 79)
            .mirror()
            .addBox(-0.4163F, 0.7392F, 1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .mirror(false)
            .texOffs(0, 16)
            .addBox(-0.4163F, 0.7392F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(44, 45)
            .addBox(-0.4163F, -1.2608F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.4163F, -6.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(18, 80).addBox(-5.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6504F, -0.0795F, -0.1041F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(22, 80).addBox(4.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6504F, 0.0795F, 0.1041F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(44, 56).addBox(4.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, -0.0076F, -0.0869F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(66, 62).addBox(-0.3353F, 0.4848F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(67, 25).addBox(-0.4042F, -1.4939F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .mirror()
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .mirror(false)
            .texOffs(0, 0)
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(12, 44)
            .addBox(-0.5837F, -1.2608F, -6.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.4163F, -6.7392F, 2.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(57, 59).addBox(0.2837F, -2.2608F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(4.4163F, -4.7392F, -5.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(42, 66).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(24, 16).addBox(-0.5958F, -1.4939F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(58, 21).addBox(-1.2837F, -2.2608F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-4.4163F, -4.7392F, -5.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create().texOffs(48, 66).addBox(-0.6647F, 0.4848F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create().texOffs(0, 25).addBox(0.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(-4.1972F, -6.5038F, -4.87F, 0.0F, -0.6545F, 0.0F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(24, 16).addBox(0.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, 3.5F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create().texOffs(33, 20).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(3.9658F, -4.5038F, -4.1154F, 0.0983F, 0.478F, 0.0453F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create().texOffs(32, 0).addBox(-1.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(4.1972F, -6.5038F, -4.87F, 0.0F, 0.6545F, 0.0F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create().texOffs(0, 6).addBox(1.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, 0.7383F, -0.0797F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create().texOffs(32, 47).addBox(-1.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, -5.0F, 3.5F, 0.0F, 0.7418F, 0.0F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create().texOffs(22, 47).addBox(-0.3742F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(24, 66).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create()
            .texOffs(44, 56)
            .mirror()
            .addBox(-5.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, 0.0076F, 0.0869F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create().texOffs(56, 21).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -7.5F, -4.5F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create().texOffs(75, 67).addBox(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -4.5F, -0.5672F, 0.0F, 0.0F)
      );
      PartDefinition head_r24 = head.addOrReplaceChild(
         "head_r24",
         CubeListBuilder.create().texOffs(78, 0).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, -4.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r25 = head.addOrReplaceChild(
         "head_r25",
         CubeListBuilder.create().texOffs(75, 65).addBox(-4.0F, -0.494F, 0.75F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r26 = head.addOrReplaceChild(
         "head_r26",
         CubeListBuilder.create().texOffs(76, 30).addBox(-4.0F, -0.5F, -0.8172F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.18F)),
         PartPose.offsetAndRotation(0.0F, -8.2172F, 4.2172F, 0.7418F, 0.0F, 0.0F)
      );
      PartDefinition head_r27 = head.addOrReplaceChild(
         "head_r27",
         CubeListBuilder.create().texOffs(48, 24).addBox(-0.6258F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, 0.8727F)
      );
      PartDefinition head_r28 = head.addOrReplaceChild(
         "head_r28",
         CubeListBuilder.create().texOffs(54, 44).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-3.9658F, -4.5038F, -4.1154F, 0.0983F, -0.478F, -0.0453F)
      );
      PartDefinition head_r29 = head.addOrReplaceChild(
         "head_r29",
         CubeListBuilder.create().texOffs(4, 6).addBox(-2.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, -0.7383F, 0.0797F)
      );
      PartDefinition head_r30 = head.addOrReplaceChild(
         "head_r30",
         CubeListBuilder.create().texOffs(18, 41).addBox(-4.0F, 4.9969F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6545F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(0, 41)
            .addBox(-4.0F, -4.5015F, -1.4827F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F))
            .texOffs(78, 2)
            .addBox(-3.0F, -2.0015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(22, 45)
            .addBox(-3.0F, -3.5015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(68, 33)
            .addBox(-3.5F, -1.1167F, -0.2176F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(40, 33)
            .addBox(-3.0F, -0.5167F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(40, 35)
            .addBox(-3.0F, 0.9833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(22, 43)
            .addBox(-3.0F, 2.4833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(68, 33)
            .addBox(-3.5F, -2.6F, -0.6F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(22, 43)
            .addBox(-3.0F, 1.0F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(40, 33)
            .addBox(-3.0F, -2.0F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(40, 35)
            .addBox(-3.0F, -0.5F, -0.4F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(0.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(22, 45)
            .addBox(-3.0F, -3.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(78, 2)
            .addBox(-3.0F, -2.0F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 41)
            .addBox(-4.0F, -4.5F, -1.45F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, -2.55F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(68, 0)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F))
            .texOffs(22, 47)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(10, 80)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(0, 71)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 0.95F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(66, 39).addBox(-2.0F, 0.05F, -3.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 5.95F, 1.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(54, 44).addBox(-4.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(0, 63).addBox(-2.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 7.95F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(48, 11)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(68, 8)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(58, 70)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-3.0F, 0.95F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(12, 66).addBox(-1.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 7.95F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(30, 66).addBox(1.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(67, 16).addBox(-1.0F, 0.05F, -3.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 5.95F, 1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(40, 16)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(68, 70)
            .mirror()
            .addBox(-1.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(40, 16)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(68, 70)
            .addBox(-3.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(0, 52).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(36, 37)
            .mirror()
            .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(76, 25)
            .mirror()
            .addBox(-1.9F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(76, 25)
            .mirror()
            .addBox(-1.9F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(28, 56)
            .mirror()
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(24, 5)
            .mirror()
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(0.1F, 4.5F, -2.1F, 0.0873F, -0.0873F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(36, 37)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(76, 25)
            .addBox(-2.1F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(76, 25)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(24, 5)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(28, 56)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.1F, 4.5F, -2.1F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(48, 56)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(33, 16)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(32, 56)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(48, 56)
            .mirror()
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(33, 16)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(32, 56)
            .mirror()
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.4F))
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
