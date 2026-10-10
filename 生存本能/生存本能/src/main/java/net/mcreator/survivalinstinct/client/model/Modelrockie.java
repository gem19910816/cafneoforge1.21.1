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

public class Modelrockie<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelrockie"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart right_leg;
   public final ModelPart left_leg;

   public Modelrockie(ModelPart root) {
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
            .texOffs(16, 44)
            .mirror()
            .addBox(-3.0F, -7.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 38)
            .addBox(-4.0F, -9.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(-0.1F))
            .texOffs(62, 51)
            .addBox(-5.0F, -7.0F, -3.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(62, 51)
            .mirror()
            .addBox(4.0F, -7.0F, -3.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(0, 38)
            .mirror()
            .addBox(1.0F, -9.0F, -5.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(56, 5)
            .addBox(-5.0F, -9.0F, -1.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(54, 0)
            .addBox(-5.0F, -6.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.7F))
            .texOffs(0, 27)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
            .texOffs(0, 15)
            .addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(-0.4F))
            .texOffs(30, 0)
            .addBox(-5.0F, -4.4F, 1.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F))
            .texOffs(0, 15)
            .addBox(-6.0F, -4.4F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(0, 15)
            .mirror()
            .addBox(5.0F, -4.4F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(25, 57).mirror().addBox(-1.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.8F, -1.9F, 0.0F, 0.0F, 0.0F, 0.9599F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(25, 57).addBox(-0.5F, -1.5F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.8F, -1.9F, 0.0F, 0.0F, 0.0F, -0.9599F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create()
            .texOffs(93, 42)
            .mirror()
            .addBox(1.0F, -0.1109F, -0.2599F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(93, 42)
            .addBox(-2.0F, -0.1109F, -0.2599F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -9.1612F, -5.441F, -1.0472F, 0.0F, 0.0F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(104, 57)
            .mirror()
            .addBox(1.0F, -0.6606F, -0.1716F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.15F))
            .mirror(false)
            .texOffs(106, 66)
            .mirror()
            .addBox(-3.0F, 1.1134F, -2.0951F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(106, 66)
            .addBox(1.0F, 1.1134F, -2.0951F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(106, 66)
            .mirror()
            .addBox(-3.0F, -1.1224F, -2.0587F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(106, 66)
            .addBox(1.0F, -1.1224F, -2.0587F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(107, 58)
            .mirror()
            .addBox(1.0F, -2.1224F, -2.0587F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(107, 58)
            .addBox(-3.0F, -2.1224F, -2.0587F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(104, 57)
            .addBox(-2.0F, -0.6606F, -0.1716F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(104, 54)
            .addBox(-2.0F, -0.6606F, -0.1716F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -9.1612F, -5.441F, -1.7017F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -0.0369F, 1.5803F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -9.1612F, -5.441F, -0.7418F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(26, 43)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(64, 10)
            .addBox(-4.0F, 2.0F, 2.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 75)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
            .texOffs(72, 78)
            .addBox(-5.0F, 3.2F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.4F))
            .texOffs(72, 78)
            .addBox(-5.0F, 7.2F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.4F))
            .texOffs(32, 59)
            .addBox(-4.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(66, 39)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(80, 4)
            .addBox(-3.0F, 3.0F, -3.4F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(80, 4)
            .addBox(-3.0F, 5.0F, -3.4F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(80, 4)
            .addBox(-3.0F, 7.0F, -3.4F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(80, 4)
            .addBox(-3.0F, 9.0F, -3.4F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(47, 44)
            .addBox(-2.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(47, 35)
            .addBox(1.0F, 10.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(1, 28)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(63, 80)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(63, 80)
            .mirror()
            .addBox(0.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(31, 8)
            .addBox(-2.5F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(57, 11)
            .addBox(-2.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(45, 60)
            .addBox(0.5F, 7.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(31, 8)
            .mirror()
            .addBox(0.5F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(30, 27)
            .addBox(0.5F, 7.0F, -3.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(1, 28)
            .mirror()
            .addBox(0.5F, 7.0F, -4.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(12, 75)
            .mirror()
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(32, 59)
            .mirror()
            .addBox(1.0F, -0.8F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .mirror(false)
            .texOffs(72, 78)
            .mirror()
            .addBox(3.0F, 7.2F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(72, 78)
            .mirror()
            .addBox(3.0F, 3.2F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.4F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(47, 29)
            .mirror()
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(31, 34)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
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
            .texOffs(61, 111)
            .mirror()
            .addBox(-3.3F, 0.0F, 0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(61, 111)
            .addBox(3.3F, 0.0F, 0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(58, 109)
            .mirror()
            .addBox(-5.0F, 0.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(58, 109)
            .addBox(2.0F, 0.0F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 2.0F, 5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(17, 54)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(31, 34)
            .mirror()
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(51, 36)
            .addBox(-0.5F, 0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.12F))
            .texOffs(83, 9)
            .mirror()
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(47, 29)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 9.5F, -3.5F, 0.0F, 0.3491F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
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
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(40, 115)
            .addBox(-3.0F, -4.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(40, 108)
            .addBox(-3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F))
            .texOffs(58, 100)
            .mirror()
            .addBox(-5.0F, 0.0F, -3.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(58, 100)
            .addBox(2.0F, 0.0F, -3.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F))
            .texOffs(38, 94)
            .addBox(-3.0F, -5.0F, -3.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 5.0F, 5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(0, 112).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(0, 112)
            .mirror()
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create().texOffs(0, 53).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(83, 105)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(107, 80)
            .addBox(-4.0F, 1.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(101, 90)
            .addBox(-3.0F, 1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(101, 99)
            .addBox(-2.0F, -1.0F, -3.0F, 3.0F, 7.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(83, 96)
            .addBox(-3.0F, 4.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(83, 105)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.3F))
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
