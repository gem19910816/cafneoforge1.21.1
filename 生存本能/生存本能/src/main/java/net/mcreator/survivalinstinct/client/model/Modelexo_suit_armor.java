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

public class Modelexo_suit_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelexo_suit_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart right_shoe;
   public final ModelPart left_shoe;

   public Modelexo_suit_armor(ModelPart root) {
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
         CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(24, 4)
            .mirror()
            .addBox(-2.827F, -0.2834F, -2.7217F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.4088F, -0.8426F, -1.65F, 0.0999F, -0.5148F, 0.1918F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(23, 7)
            .mirror()
            .addBox(-1.0976F, -1.381F, -2.3512F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.4088F, -0.8426F, -1.65F, -0.0382F, -0.1434F, 1.8215F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(46, 52).mirror().addBox(-1.0F, -0.5F, 1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -3.5F, 0.0F, -0.0382F, -0.1434F, 1.1233F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(24, 4).addBox(-0.173F, -0.2834F, -2.7217F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.4088F, -0.8426F, -1.65F, 0.0999F, 0.5148F, -0.1918F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(23, 7).addBox(-0.9024F, -1.381F, -2.3512F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.4088F, -0.8426F, -1.65F, -0.0382F, 0.1434F, -1.8215F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(46, 52).addBox(-1.0F, -0.5F, 1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, -3.5F, 0.0F, -0.0382F, 0.1434F, -1.1233F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, 0.5F, -1.5F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.7559F, 5.2365F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(16, 62).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -5.5593F, 4.4589F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(8, 61).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, 5.0F, -0.2618F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create().texOffs(50, 47).addBox(-2.5F, -0.5F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 8.5F, 0.0F, 0.0F, 0.1309F, 0.1309F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create().texOffs(67, 18).mirror().addBox(-0.5F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-2.5F, 2.9802F, 2.8021F, 0.1476F, -0.3562F, -0.2346F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(50, 47).mirror().addBox(-1.5F, -0.5F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-3.0F, 8.5F, 0.0F, 0.0F, -0.1309F, -0.1309F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(52, 0).mirror().addBox(-1.5F, -0.5F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-3.0F, 2.5F, 0.0F, 0.0F, -0.2182F, 0.2618F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(17, 58).mirror().addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-3.0F, 5.5F, 0.0F, 0.0F, -0.2182F, 0.2618F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(61, 32)
            .mirror()
            .addBox(-2.0F, -0.5F, 0.6041F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(3.1746F, 10.523F, -1.1041F, -2.9001F, 0.1434F, 2.2786F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(52, 0).addBox(-2.5F, -0.5F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 2.5F, 0.0F, 0.0F, 0.2182F, -0.2618F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(17, 58).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 5.5F, 0.0F, 0.0F, 0.2182F, -0.2618F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.5709F, -0.1553F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 4.7114F, 2.9438F, 0.3491F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(43, 58).addBox(-1.0F, -0.8706F, -0.316F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, 4.7114F, 2.9438F, -0.3491F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(67, 18).addBox(-1.5F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5F, 2.9802F, 2.8021F, 0.1476F, 0.3562F, 0.2346F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(24, 18).addBox(-1.0F, -4.5F, -2.75F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.5F, 2.75F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(0, 53).addBox(-2.0F, -4.5F, -0.75F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 4.5F, 2.75F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(66, 46).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.2261F, 11.6787F, -0.5F, 0.0F, 0.3927F, -0.1309F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create().texOffs(61, 32).addBox(-2.0F, -0.5F, 0.6041F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.1746F, 10.523F, -1.1041F, -2.9001F, -0.1434F, -2.2786F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(33, 30)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(43, 40).mirror().addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 7.5F, 0.0F, 0.0F, 0.0F, -0.48F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create().texOffs(27, 60).mirror().addBox(-1.0F, -3.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.8622F, 5.3929F, 0.0143F, -0.2736F, -0.1602F, 0.1295F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create()
            .texOffs(44, 28)
            .mirror()
            .addBox(-0.75F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(3.5518F, -1.756F, 0.3796F, 1.192F, 0.0519F, -0.4877F)
      );
      PartDefinition left_arm_r4 = left_arm.addOrReplaceChild(
         "left_arm_r4",
         CubeListBuilder.create()
            .texOffs(59, 61)
            .mirror()
            .addBox(-0.75F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(3.5518F, -1.756F, 0.3796F, 0.5375F, 0.0519F, -0.4877F)
      );
      PartDefinition left_arm_r5 = left_arm.addOrReplaceChild(
         "left_arm_r5",
         CubeListBuilder.create().texOffs(45, 25).mirror().addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 2.5F, 0.0F, 0.0F, 0.0F, 0.3054F)
      );
      PartDefinition left_arm_r6 = left_arm.addOrReplaceChild(
         "left_arm_r6",
         CubeListBuilder.create().texOffs(58, 17).mirror().addBox(-1.5F, 0.0F, 0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.65F)).mirror(false),
         PartPose.offsetAndRotation(0.5F, -2.5F, -0.5F, -0.7418F, 0.0F, 0.0F)
      );
      PartDefinition left_arm_r7 = left_arm.addOrReplaceChild(
         "left_arm_r7",
         CubeListBuilder.create().texOffs(61, 37).mirror().addBox(-0.5F, -3.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.5F, 1.0F, 0.0F, 0.1946F, -0.2191F, -0.266F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(33, 30).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(27, 60).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.8622F, 5.3929F, 0.0143F, -0.2736F, 0.1602F, -0.1295F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(59, 61).addBox(-0.25F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-3.5518F, -1.756F, 0.3796F, 0.5375F, -0.0519F, 0.4877F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create().texOffs(44, 28).addBox(-1.25F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.5518F, -1.756F, 0.3796F, 1.192F, -0.0519F, 0.4877F)
      );
      PartDefinition right_arm_r4 = right_arm.addOrReplaceChild(
         "right_arm_r4",
         CubeListBuilder.create().texOffs(43, 40).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 7.5F, 0.0F, 0.0F, 0.0F, 0.48F)
      );
      PartDefinition right_arm_r5 = right_arm.addOrReplaceChild(
         "right_arm_r5",
         CubeListBuilder.create().texOffs(45, 25).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 2.5F, 0.0F, 0.0F, 0.0F, -0.3054F)
      );
      PartDefinition right_arm_r6 = right_arm.addOrReplaceChild(
         "right_arm_r6",
         CubeListBuilder.create().texOffs(58, 17).addBox(-2.5F, 0.0F, 0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.65F)),
         PartPose.offsetAndRotation(-0.5F, -2.5F, -0.5F, -0.7418F, 0.0F, 0.0F)
      );
      PartDefinition right_arm_r7 = right_arm.addOrReplaceChild(
         "right_arm_r7",
         CubeListBuilder.create().texOffs(61, 37).addBox(-1.5F, -3.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.5F, 1.0F, 0.0F, 0.1946F, 0.2191F, 0.266F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create().texOffs(28, 30).mirror().addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.7993F, 4.2227F, 0.4075F, 0.883F, -0.1116F, -0.0857F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create()
            .texOffs(55, 55)
            .mirror()
            .addBox(0.5F, -3.2638F, -0.9255F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.5F, 6.2753F, -0.0298F, 0.2285F, -0.1116F, -0.0857F)
      );
      PartDefinition left_leg_r3 = left_leg.addOrReplaceChild(
         "left_leg_r3",
         CubeListBuilder.create().texOffs(49, 32).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, 9.5F, 0.0F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition left_leg_r4 = left_leg.addOrReplaceChild(
         "left_leg_r4",
         CubeListBuilder.create().texOffs(12, 50).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition left_leg_r5 = left_leg.addOrReplaceChild(
         "left_leg_r5",
         CubeListBuilder.create()
            .texOffs(62, 52)
            .mirror()
            .addBox(0.5F, -4.7264F, -1.7436F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(65, 58)
            .mirror()
            .addBox(0.5F, -3.7264F, -0.7436F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.5F, 5.2753F, -0.0298F, 2.9234F, -0.1309F, 0.0F)
      );
      PartDefinition left_leg_r6 = left_leg.addOrReplaceChild(
         "left_leg_r6",
         CubeListBuilder.create()
            .texOffs(42, 66)
            .mirror()
            .addBox(0.5F, -4.2638F, 0.0745F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(0.5F, 5.2753F, -0.0298F, 0.2182F, -0.1309F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create().texOffs(28, 30).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.7993F, 4.2227F, 0.4075F, 0.883F, 0.1116F, 0.0857F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create().texOffs(49, 32).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.0F, 9.5F, 0.0F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition right_leg_r3 = right_leg.addOrReplaceChild(
         "right_leg_r3",
         CubeListBuilder.create()
            .texOffs(62, 52)
            .addBox(-2.5F, -4.7264F, -1.7436F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(65, 58)
            .addBox(-2.5F, -3.7264F, -0.7436F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-0.5F, 5.2753F, -0.0298F, 2.9234F, 0.1309F, 0.0F)
      );
      PartDefinition right_leg_r4 = right_leg.addOrReplaceChild(
         "right_leg_r4",
         CubeListBuilder.create().texOffs(55, 55).addBox(-2.5F, -3.2638F, -0.9255F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.5F, 6.2753F, -0.0298F, 0.2285F, 0.1116F, 0.0857F)
      );
      PartDefinition right_leg_r5 = right_leg.addOrReplaceChild(
         "right_leg_r5",
         CubeListBuilder.create().texOffs(42, 66).addBox(-2.5F, -4.2638F, 0.0745F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.5F, 5.2753F, -0.0298F, 0.2182F, 0.1309F, 0.0F)
      );
      PartDefinition right_leg_r6 = right_leg.addOrReplaceChild(
         "right_leg_r6",
         CubeListBuilder.create().texOffs(12, 50).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild("right_shoe", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));
      PartDefinition left_shoe_r1 = right_shoe.addOrReplaceChild(
         "left_shoe_r1",
         CubeListBuilder.create().texOffs(0, 69).mirror().addBox(0.3F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.3927F)
      );
      PartDefinition left_shoe_r2 = right_shoe.addOrReplaceChild(
         "left_shoe_r2",
         CubeListBuilder.create().texOffs(46, 22).mirror().addBox(-4.0F, 0.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.3927F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild("left_shoe", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));
      PartDefinition right_shoe_r1 = left_shoe.addOrReplaceChild(
         "right_shoe_r1",
         CubeListBuilder.create().texOffs(46, 22).addBox(0.0F, 0.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.3927F)
      );
      PartDefinition right_shoe_r2 = left_shoe.addOrReplaceChild(
         "right_shoe_r2",
         CubeListBuilder.create().texOffs(0, 69).addBox(-1.3F, -1.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.3927F)
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
