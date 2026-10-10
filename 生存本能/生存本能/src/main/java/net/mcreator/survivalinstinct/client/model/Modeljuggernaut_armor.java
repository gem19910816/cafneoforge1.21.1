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

public class Modeljuggernaut_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modeljuggernaut_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart right_leg;
   public final ModelPart left_leg;

   public Modeljuggernaut_armor(ModelPart root) {
      this.head = root.getChild("head");
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
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(138, 2)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(138, 18)
            .addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(200, 54)
            .addBox(-4.0F, -8.0F, 4.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(210, 50)
            .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.05F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(160, 49)
            .mirror()
            .addBox(-0.6258F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(194, 34)
            .addBox(-0.6258F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, 0.8727F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(150, 46)
            .mirror()
            .addBox(-0.4163F, -1.2608F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(138, 18)
            .addBox(-0.4163F, 0.7392F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(4.4163F, -6.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(156, 82).addBox(-5.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6504F, -0.0795F, -0.1041F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(160, 82).addBox(4.2222F, -0.6075F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6504F, 0.0795F, 0.1041F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(182, 58).addBox(4.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, -0.0076F, -0.0869F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(138, 18)
            .mirror()
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .mirror(false)
            .texOffs(150, 46)
            .addBox(-0.5837F, -1.2608F, -6.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.4163F, -6.7392F, 2.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(180, 68).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(138, 27).addBox(0.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(-4.1972F, -6.5038F, -4.87F, 0.0F, -0.6545F, 0.0F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(162, 18).addBox(0.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, 3.5F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(171, 22).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(3.9658F, -4.5038F, -4.1154F, 0.0983F, 0.478F, 0.0453F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(170, 2).addBox(-1.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(4.1972F, -6.5038F, -4.87F, 0.0F, 0.6545F, 0.0F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create().texOffs(138, 8).addBox(1.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, 0.7383F, -0.0797F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create().texOffs(170, 49).addBox(-1.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, -5.0F, 3.5F, 0.0F, 0.7418F, 0.0F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(160, 49).addBox(-0.3742F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create().texOffs(162, 68).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create()
            .texOffs(182, 58)
            .mirror()
            .addBox(-5.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, 0.0076F, 0.0869F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create().texOffs(213, 69).addBox(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -4.5F, -0.5672F, 0.0F, 0.0F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create().texOffs(216, 2).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, -4.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create().texOffs(213, 67).addBox(-4.0F, -0.494F, 0.75F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(214, 32).addBox(-4.0F, -0.5F, -0.8172F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.18F)),
         PartPose.offsetAndRotation(0.0F, -8.2172F, 4.2172F, 0.7418F, 0.0F, 0.0F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create().texOffs(192, 46).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-3.9658F, -4.5038F, -4.1154F, 0.0983F, -0.478F, -0.0453F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create().texOffs(142, 8).addBox(-2.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, -0.7383F, 0.0797F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create().texOffs(156, 43).addBox(-4.0F, 4.9969F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)),
         PartPose.offsetAndRotation(0.0F, -4.412F, 0.912F, -0.6545F, 0.0F, 0.0F)
      );
      PartDefinition visor_r1 = head.addOrReplaceChild(
         "visor_r1",
         CubeListBuilder.create().texOffs(21, 114).addBox(-5.0F, -1.0F, -5.75F, 10.0F, 2.0F, 9.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, -5.0F, -0.25F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r2 = head.addOrReplaceChild(
         "visor_r2",
         CubeListBuilder.create()
            .texOffs(0, 111)
            .addBox(-5.0F, -1.5F, -1.8F, 10.0F, 1.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 111)
            .addBox(-5.0F, 0.5F, -1.8F, 10.0F, 1.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(9, 111)
            .mirror()
            .addBox(4.0F, -1.5F, -1.8F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(9, 111)
            .addBox(-5.0F, -1.5F, -1.8F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(9, 103)
            .addBox(-5.0F, -1.5F, -1.2F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -3.5F, -4.8F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(32, 31)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(103, 0)
            .mirror()
            .addBox(2.0F, 5.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(103, 0)
            .mirror()
            .addBox(2.0F, 8.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(32, 69)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(79, 3)
            .addBox(-4.0F, 2.0F, -3.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(13, 41)
            .addBox(-4.0F, 4.0F, -3.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(79, 0)
            .addBox(-4.0F, 2.0F, 2.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 79)
            .addBox(-4.0F, 4.0F, 2.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(73, 9)
            .addBox(-4.0F, 6.0F, 2.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(54, 0)
            .addBox(-4.0F, 8.0F, 2.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(68, 13)
            .addBox(-4.0F, 2.0F, 2.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(69, 55)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.55F))
            .texOffs(67, 0)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.55F))
            .texOffs(63, 65)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.8F))
            .texOffs(66, 35)
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.8F))
            .texOffs(0, 27)
            .addBox(-5.0F, -3.0F, 5.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(103, 0)
            .addBox(-5.0F, 8.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(103, 0)
            .addBox(-5.0F, 5.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create().texOffs(53, 82).mirror().addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false),
         PartPose.offsetAndRotation(-2.5F, 4.5068F, 4.156F, -0.0865F, -0.0114F, -0.1304F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create().texOffs(53, 82).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(2.5F, 4.5068F, 4.156F, -0.0865F, 0.0114F, 0.1304F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-0.5F, -1.5F, -5.5F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-5.5F, -0.5F, 0.5F, 0.1752F, 0.0859F, 0.0152F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 0.5F, -4.5F, 0.0F, 0.2618F, 0.4363F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(26, 31).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.0F, 0.5F, -4.5F, 0.0F, -0.2618F, -0.4363F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(0, 41).addBox(-0.5F, -1.5F, -5.5F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.5F, -0.5F, 0.5F, 0.1752F, -0.0859F, -0.0152F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(30, 79).addBox(-3.0F, -1.5F, -0.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 1.5F, -5.5F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(26, 13).addBox(-5.0F, -1.0F, -2.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, -2.5F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(29, 0).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.0F, 2.5F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(48, 77).addBox(-3.0F, -2.0F, -1.1F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(0.0F, 9.0F, 4.1F, -0.2182F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(26, 47).addBox(-3.0F, -2.0F, -1.1F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 10.0F, 4.1F, -0.2182F, 0.0F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(53, 82).mirror().addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false),
         PartPose.offsetAndRotation(-2.5F, 9.5068F, 4.156F, -0.2164F, -0.0283F, -0.1278F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(53, 82).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(2.5F, 9.5068F, 4.156F, -0.2164F, 0.0283F, 0.1278F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(26, 47).addBox(-3.0F, -2.0F, -1.1F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 5.0F, 4.1F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create().texOffs(48, 77).addBox(-3.0F, -2.0F, -1.1F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(0.0F, 4.0F, 4.1F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create()
            .texOffs(79, 44)
            .addBox(-2.0F, -1.75F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(18, 79)
            .addBox(-2.0F, -1.75F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 6.75F, -3.5F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create()
            .texOffs(0, 82)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(86, 12)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.5F, 7.25F, -3.5F, 0.0F, -0.3491F, 0.0F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create()
            .texOffs(0, 41)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(81, 55)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, 11.25F, -1.5F, 0.1977F, -1.1278F, -0.1564F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(4.5F, 12.25F, -1.5F, 0.1977F, -1.1278F, -0.1564F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(2.5F, 12.25F, -3.5F, 0.0873F, -0.2618F, 0.0F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(-2.5F, 12.25F, -3.5F, 0.0873F, 0.2618F, 0.0F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(-4.5F, 12.25F, -1.5F, 0.1977F, 1.1278F, 0.1564F)
      );
      PartDefinition body_r23 = body.addOrReplaceChild(
         "body_r23",
         CubeListBuilder.create()
            .texOffs(64, 79)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(85, 49)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5F, 11.25F, -3.5F, 0.0873F, -0.2618F, 0.0F)
      );
      PartDefinition body_r24 = body.addOrReplaceChild(
         "body_r24",
         CubeListBuilder.create()
            .texOffs(13, 44)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(81, 69)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, 11.25F, -1.5F, 0.1977F, 1.1278F, 0.1564F)
      );
      PartDefinition body_r25 = body.addOrReplaceChild(
         "body_r25",
         CubeListBuilder.create()
            .texOffs(74, 80)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(84, 80)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.5F, 11.25F, -3.5F, 0.0873F, 0.2618F, 0.0F)
      );
      PartDefinition body_r26 = body.addOrReplaceChild(
         "body_r26",
         CubeListBuilder.create()
            .texOffs(60, 86)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 82)
            .mirror()
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.5F, 7.25F, -3.5F, 0.0F, 0.3491F, 0.0F)
      );
      PartDefinition body_r27 = body.addOrReplaceChild(
         "body_r27",
         CubeListBuilder.create().texOffs(109, 68).addBox(-3.0F, 0.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.5F, 1.5F, 2.7918F, -0.0119F, 3.1347F)
      );
      PartDefinition body_r28 = body.addOrReplaceChild(
         "body_r28",
         CubeListBuilder.create().texOffs(109, 68).addBox(-3.0F, 0.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.5F, -2.5F, -0.2618F, 0.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(75, 64)
            .mirror()
            .addBox(-1.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(62, 74)
            .mirror()
            .addBox(-1.0F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(12, 74)
            .mirror()
            .addBox(-1.0F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create()
            .texOffs(51, 41)
            .mirror()
            .addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create().texOffs(53, 3).mirror().addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.3F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create()
            .texOffs(37, 47)
            .mirror()
            .addBox(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(-0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(2.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(75, 64)
            .addBox(-3.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(62, 74)
            .addBox(-3.0F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(12, 74)
            .addBox(-3.0F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(51, 41).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(53, 3).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create().texOffs(37, 47).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-2.0F, 6.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(115, 24)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(103, 27)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(113, 13)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(115, 24)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(103, 27)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(113, 13)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(84, 105)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(100, 58)
            .addBox(-2.1F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(100, 58)
            .addBox(-2.1F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(123, 37)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(109, 218)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(132, 40)
            .mirror()
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.1F, 5.9253F, -0.2885F, -0.0863F, -1.5272F, 0.001F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create()
            .texOffs(136, 37)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(109, 218)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.6F))
            .mirror(false)
            .texOffs(132, 40)
            .mirror()
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.5F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.1F, 0.9253F, -0.2885F, -0.0863F, -1.5272F, 0.001F)
      );
      PartDefinition right_leg_r3 = right_leg.addOrReplaceChild(
         "right_leg_r3",
         CubeListBuilder.create()
            .texOffs(119, 47)
            .addBox(-1.5F, -3.5F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.45F))
            .texOffs(109, 48)
            .addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.1F, 4.5F, -2.1F, 0.0869F, 3.0E-4F, -0.0076F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(84, 105)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(100, 58)
            .mirror()
            .addBox(-1.9F, 5.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(100, 58)
            .mirror()
            .addBox(-1.9F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(136, 37)
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(109, 218)
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.6F))
            .texOffs(132, 40)
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(3.1F, 0.9253F, -0.2885F, -0.0863F, 1.5272F, -0.001F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create()
            .texOffs(119, 47)
            .mirror()
            .addBox(-1.5F, -3.5F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.45F))
            .mirror(false)
            .texOffs(109, 48)
            .mirror()
            .addBox(-1.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(0.1F, 4.5F, -2.1F, 0.0869F, -3.0E-4F, 0.0076F)
      );
      return LayerDefinition.create(meshdefinition, 256, 256);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
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
