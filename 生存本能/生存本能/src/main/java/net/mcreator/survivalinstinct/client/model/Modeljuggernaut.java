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

public class Modeljuggernaut<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modeljuggernaut"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart right_leg;
   public final ModelPart left_leg;

   public Modeljuggernaut(ModelPart root) {
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
            .texOffs(24, 19)
            .addBox(-4.0F, -9.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.556F))
            .texOffs(96, 49)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(0, 31).addBox(-5.0F, -1.0F, -1.75F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, 0.75F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -5.75F, 10.0F, 2.0F, 9.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, -5.0F, 0.75F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
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
         PartPose.offsetAndRotation(0.0F, -3.5F, -3.8F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(93, 67).addBox(-1.4F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-4.0F, -3.0F, 0.0F, 0.1369F, 0.0354F, 0.1957F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(93, 67).mirror().addBox(0.4F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.6F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -3.0F, 0.0F, 0.1369F, -0.0354F, -0.1957F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(32, 31)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(103, 0)
            .mirror()
            .addBox(2.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(103, 0)
            .mirror()
            .addBox(2.0F, 7.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
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
            .addBox(-5.0F, 7.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(103, 0)
            .addBox(-5.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create().texOffs(0, 41).mirror().addBox(-0.5F, -1.5F, -5.5F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-5.5F, -0.5F, 0.5F, 0.1752F, 0.0859F, 0.0152F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 0.5F, -4.5F, 0.0F, 0.2618F, 0.4363F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(26, 31).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.0F, 0.5F, -4.5F, 0.0F, -0.2618F, -0.4363F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(0, 41).addBox(-0.5F, -1.5F, -5.5F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.5F, -0.5F, 0.5F, 0.1752F, -0.0859F, -0.0152F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(30, 79).addBox(-3.0F, -1.5F, -0.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 1.5F, -5.5F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(26, 13).addBox(-5.0F, -1.0F, -2.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, -2.5F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(29, 0).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.0F, 2.5F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(84, 26).addBox(-2.0F, 0.5F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 10.5F, 2.5F, 0.3054F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(52, 82)
            .addBox(1.0F, -1.0F, -1.4F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(10, 82)
            .addBox(-3.0F, -1.0F, -1.4F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(26, 47)
            .addBox(-3.0F, -2.0F, -1.1F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(48, 77)
            .addBox(-3.0F, -2.0F, -1.1F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 4.0F, 4.1F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(27, 83)
            .addBox(-3.0F, -1.0F, -1.4F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(84, 20)
            .addBox(1.0F, -1.0F, -1.4F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(78, 34)
            .addBox(-3.0F, -2.0F, -1.1F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(68, 23)
            .addBox(-3.0F, -2.0F, -1.1F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.0F, 4.1F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(79, 44)
            .addBox(-2.0F, -1.75F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(18, 79)
            .addBox(-2.0F, -1.75F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 6.75F, -3.5F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(0, 82)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(86, 12)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.5F, 7.25F, -3.5F, 0.0F, -0.3491F, 0.0F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create()
            .texOffs(0, 41)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(81, 55)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, 11.25F, -1.5F, 0.1977F, -1.1278F, -0.1564F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(4.5F, 12.25F, -1.5F, 0.1977F, -1.1278F, -0.1564F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(2.5F, 12.25F, -3.5F, 0.0873F, -0.2618F, 0.0F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(-2.5F, 12.25F, -3.5F, 0.0873F, 0.2618F, 0.0F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create().texOffs(94, 80).addBox(-1.5F, -1.25F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(-4.5F, 12.25F, -1.5F, 0.1977F, 1.1278F, 0.1564F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create()
            .texOffs(64, 79)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(85, 49)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5F, 11.25F, -3.5F, 0.0873F, -0.2618F, 0.0F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create()
            .texOffs(13, 44)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(81, 69)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, 11.25F, -1.5F, 0.1977F, 1.1278F, 0.1564F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create()
            .texOffs(74, 80)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(84, 80)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.5F, 11.25F, -3.5F, 0.0873F, 0.2618F, 0.0F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create()
            .texOffs(60, 86)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(42, 82)
            .addBox(-1.5F, -2.25F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-3.5F, 7.25F, -3.5F, 0.0F, 0.3491F, 0.0F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create().texOffs(83, 86).addBox(-2.0F, 0.5F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 10.5F, -2.5F, -0.2618F, 0.0F, 0.0F)
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
            .texOffs(53, 55)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F))
            .texOffs(55, 107)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(44, 117)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.85F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(53, 55)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.65F))
            .mirror(false)
            .texOffs(44, 117)
            .mirror()
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.85F))
            .mirror(false)
            .texOffs(55, 107)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(84, 105)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(108, 89)
            .addBox(-2.2F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(108, 94)
            .addBox(-2.2F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(116, 109)
            .addBox(-2.0F, 4.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(110, 121)
            .addBox(-2.0F, 3.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(110, 121)
            .addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(116, 112)
            .addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(110, 121)
            .addBox(-2.0F, 3.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(110, 121)
            .addBox(-2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(116, 109)
            .addBox(-2.0F, 4.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(116, 112)
            .addBox(-2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(92, 118)
            .addBox(-3.0F, 1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(84, 105)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(108, 89)
            .mirror()
            .addBox(-1.8F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(108, 94)
            .mirror()
            .addBox(-1.8F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(116, 109)
            .mirror()
            .addBox(-2.0F, 4.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(110, 121)
            .mirror()
            .addBox(-2.0F, 3.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(116, 112)
            .mirror()
            .addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(116, 112)
            .mirror()
            .addBox(-2.0F, 4.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(110, 121)
            .mirror()
            .addBox(-2.0F, 3.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(116, 112)
            .mirror()
            .addBox(-2.0F, 5.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(116, 109)
            .mirror()
            .addBox(-2.0F, 4.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(116, 112)
            .mirror()
            .addBox(-2.0F, 4.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(92, 118)
            .mirror()
            .addBox(1.0F, 1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
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
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
