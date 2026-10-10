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

public class Modelrecluit_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrecluit_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelrecluit_armor(ModelPart root) {
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
            .texOffs(0, 11)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(24, 11)
            .addBox(-5.0F, -9.0F, -1.5F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(88, 0)
            .addBox(-5.0F, -6.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(88, 0)
            .mirror()
            .addBox(3.0F, -6.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.7595F, -4.1147F, 0.0F, 0.0F, -0.6109F, -0.0873F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(73, 60).addBox(-0.4163F, -1.2608F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(20, 27).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.7595F, -4.1147F, 0.0F, 0.0F, 0.6109F, 0.0873F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(69, 0).addBox(-0.4163F, -1.2608F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.2182F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(60, 57).addBox(2.5592F, -0.9076F, -4.2164F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-0.8087F, -6.2093F, -4.0F, 0.121F, -0.05F, 0.3897F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(20, 62).addBox(-4.065F, -0.2887F, -4.2164F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-0.8087F, -6.2093F, -4.0F, 0.121F, 0.05F, -0.3897F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(54, 9).addBox(-1.1913F, 0.1856F, -4.2164F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-0.8087F, -6.2093F, -4.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-5.0F, -0.25F, -4.25F, 10.0F, 2.0F, 9.0F, new CubeDeformation(-0.2F))
            .texOffs(24, 19)
            .addBox(-4.0F, -2.25F, -4.25F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.75F, 0.25F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(58, 69).addBox(-1.5837F, -1.2608F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, 0.2182F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(73, 73).addBox(-0.5837F, -1.2608F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(0, 27)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(0, 95)
            .addBox(-4.0F, 10.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r11 = body.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(3, 88).addBox(-4.0F, 2.0F, -1.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.35F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(0, 75)
            .mirror()
            .addBox(-0.9944F, -1.5065F, -1.3599F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(0, 59)
            .mirror()
            .addBox(-0.4944F, -0.5065F, -0.0985F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(28, 46)
            .mirror()
            .addBox(-0.9944F, -1.5065F, -1.0985F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.7328F, 4.9271F, 4.5762F, 0.0418F, -1.0827F, -0.1807F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(36, 30)
            .addBox(-2.1788F, 1.145F, -0.1795F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(32, 57)
            .addBox(-1.6788F, 2.145F, 0.8205F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(74, 36)
            .addBox(-2.1788F, 1.145F, -0.4409F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-2.351F, 6.2529F, 3.1113F, -0.0452F, -0.2615F, 0.0117F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(71, 53)
            .addBox(3.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(30, 71)
            .addBox(3.0F, 1.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 4.5F, 3.9347F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(32, 46).addBox(-3.0F, -3.5F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(0.0F, 4.5F, 3.9347F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(48, 18).addBox(-3.0F, -3.4981F, -1.9128F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, 4.5F, 3.9347F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(54, 9).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(1.4469F, 4.4742F, 6.0369F, 0.1306F, 0.0076F, 7.0E-4F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(40, 71)
            .addBox(-4.0F, 1.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(66, 33)
            .addBox(-4.0F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(1.0F, 4.5F, 3.9347F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(16, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-1.4469F, 4.4742F, 6.0369F, 0.1306F, -0.0076F, -7.0E-4F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(0, 0).addBox(-1.8964F, -1.2475F, -1.7887F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-2.351F, 6.2529F, 3.1113F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(0, 68).addBox(-2.651F, -5.2529F, -1.7887F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-2.351F, 6.2529F, 3.1113F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(56, 75).addBox(-2.7153F, -5.2529F, -1.6854F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.351F, 6.2529F, 3.1113F, 0.0F, -0.0873F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(78, 72)
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(16, 76)
            .addBox(-1.0F, -2.0F, -1.2614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(24, 30)
            .addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(3.0F, 9.4161F, -3.2386F, 3.0964F, 0.2615F, 3.1299F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(32, 76).addBox(-1.5F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.8F, 3.5F, -2.1F, 3.1416F, 0.0873F, -3.1416F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(10, 69).addBox(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(2.8F, 3.5F, -2.1F, 3.1416F, 0.0F, -3.1416F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create().texOffs(42, 10).addBox(1.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 5.0208F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create().texOffs(70, 45).addBox(-1.8F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(1.8F, 7.5F, -2.1F, 3.1416F, 0.0F, 3.098F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create()
            .texOffs(76, 66)
            .addBox(-0.9944F, -1.5065F, -1.3599F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(48, 18)
            .addBox(-0.4944F, -0.5065F, -0.0985F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(80, 31)
            .addBox(-0.9944F, -1.5065F, -1.0985F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(5.0309F, 8.9271F, -1.3537F, 2.8535F, 1.1758F, 3.0367F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create().texOffs(24, 16).addBox(-5.149F, 2.7542F, -0.3739F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create()
            .texOffs(0, 75)
            .addBox(-1.0056F, -1.5065F, -1.3599F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(0, 59)
            .addBox(-0.5056F, -0.5065F, -0.0985F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(28, 46)
            .addBox(-1.0056F, -1.5065F, -1.0985F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(3.7328F, 4.9271F, 4.5762F, 0.0418F, 1.0827F, 0.1807F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create()
            .texOffs(76, 12)
            .addBox(0.1788F, 1.145F, -0.4409F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(48, 59)
            .addBox(0.6788F, 2.145F, 0.8205F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(78, 58)
            .addBox(0.1788F, 1.145F, -0.1795F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, -0.0452F, 0.2615F, -0.0117F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create().texOffs(0, 11).addBox(-0.1036F, -1.2475F, -1.7887F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create().texOffs(48, 48).addBox(-5.149F, -2.2002F, -1.0151F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r23 = body.addOrReplaceChild(
         "body_r23",
         CubeListBuilder.create().texOffs(48, 68).addBox(-0.349F, -5.2529F, -1.7887F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r24 = body.addOrReplaceChild(
         "body_r24",
         CubeListBuilder.create().texOffs(8, 76).addBox(0.7153F, -5.2529F, -1.6854F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.351F, 6.2529F, 3.1113F, 0.0F, 0.0873F, 0.0F)
      );
      PartDefinition body_r25 = body.addOrReplaceChild(
         "body_r25",
         CubeListBuilder.create()
            .texOffs(0, 43)
            .addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(24, 76)
            .addBox(-1.0F, -2.0F, -1.2614F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(79, 0)
            .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(-3.0F, 9.4161F, -3.2386F, 3.0964F, -0.2615F, -3.1299F)
      );
      PartDefinition body_r26 = body.addOrReplaceChild(
         "body_r26",
         CubeListBuilder.create()
            .texOffs(0, 81)
            .addBox(-1.0056F, -1.5065F, -1.0985F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .texOffs(53, 0)
            .addBox(-0.5056F, -0.5065F, -0.0985F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(77, 4)
            .addBox(-1.0056F, -1.5065F, -1.3599F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-5.0309F, 8.9271F, -1.3537F, 2.8535F, -1.1758F, -3.0367F)
      );
      PartDefinition body_r27 = body.addOrReplaceChild(
         "body_r27",
         CubeListBuilder.create().texOffs(64, 26).addBox(-3.5F, -2.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 11.0F, -2.6F, 3.0543F, 0.0F, 3.1416F)
      );
      PartDefinition body_r28 = body.addOrReplaceChild(
         "body_r28",
         CubeListBuilder.create().texOffs(12, 43).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 4.5F, -2.6F, 3.0107F, 0.0F, 3.1416F)
      );
      PartDefinition body_r29 = body.addOrReplaceChild(
         "body_r29",
         CubeListBuilder.create().texOffs(50, 40).addBox(-4.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 5.0208F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r30 = body.addOrReplaceChild(
         "body_r30",
         CubeListBuilder.create().texOffs(46, 51).addBox(-2.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 7.95F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r31 = body.addOrReplaceChild(
         "body_r31",
         CubeListBuilder.create()
            .texOffs(77, 50)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(78, 26)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(56, 32)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 0.95F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r32 = body.addOrReplaceChild(
         "body_r32",
         CubeListBuilder.create().texOffs(52, 24).addBox(-1.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 7.95F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r33 = body.addOrReplaceChild(
         "body_r33",
         CubeListBuilder.create()
            .texOffs(78, 19)
            .addBox(-1.0F, -0.95F, 2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(78, 42)
            .addBox(-1.0F, -0.95F, 6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(10, 62)
            .addBox(-1.0F, -1.55F, 2.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 0.95F, -5.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r34 = body.addOrReplaceChild(
         "body_r34",
         CubeListBuilder.create().texOffs(68, 69).addBox(-0.2F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-1.8F, 7.5F, -2.1F, 3.1416F, 0.0F, -3.098F)
      );
      PartDefinition body_r35 = body.addOrReplaceChild(
         "body_r35",
         CubeListBuilder.create().texOffs(40, 76).addBox(-0.5F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.8F, 3.5F, -2.1F, 3.1416F, -0.0873F, 3.1416F)
      );
      PartDefinition body_r36 = body.addOrReplaceChild(
         "body_r36",
         CubeListBuilder.create().texOffs(20, 69).addBox(-1.5F, -2.5F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-2.8F, 3.5F, -2.1F, 3.1416F, 0.0F, 3.1416F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(16, 46)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(64, 15)
            .mirror()
            .addBox(-1.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(58, 48).mirror().addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(16, 46)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(64, 15)
            .addBox(-3.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(58, 48).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(66, 28)
            .mirror()
            .addBox(-1.9F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(0, 43)
            .mirror()
            .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(70, 79)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(60, 15)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.1F, 4.5F, -2.1F, 0.0873F, -0.0873F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(66, 28)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(0, 43)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(72, 33)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(47, 81)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.1F, 4.5F, -2.1F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create()
            .texOffs(56, 32)
            .addBox(-0.7412F, 0.0F, -1.4659F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(65, 0)
            .addBox(-0.7412F, 0.0F, -1.4659F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
         PartPose.offsetAndRotation(-3.1F, 2.0F, -0.5F, 0.0925F, 0.348F, 0.024F)
      );
      PartDefinition right_leg_r3 = right_leg.addOrReplaceChild(
         "right_leg_r3",
         CubeListBuilder.create()
            .texOffs(64, 76)
            .addBox(-1.0F, -3.0F, 0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.25F))
            .texOffs(79, 77)
            .addBox(-1.0F, -3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(67, 6)
            .addBox(-1.0F, -1.0F, -0.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.1F, 2.0F, -0.5F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(48, 59)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(48, 24)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(48, 59)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(48, 24)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F)),
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
