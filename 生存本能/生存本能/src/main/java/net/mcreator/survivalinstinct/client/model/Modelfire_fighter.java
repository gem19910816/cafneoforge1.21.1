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

public class Modelfire_fighter<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelfire_fighter"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart right_shoe;
   public final ModelPart left_shoe;

   public Modelfire_fighter(ModelPart root) {
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
            .texOffs(0, 112)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(24, 22)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.6F))
            .texOffs(24, 14)
            .addBox(-5.0F, -9.0F, -1.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
            .texOffs(24, 33)
            .addBox(-1.0F, -9.0F, -5.0F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -0.5F, -6.5F, 10.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, -4.5F, 0.5F, -0.0433F, -0.0018F, 0.0052F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(39, 38).mirror().addBox(-0.5F, -2.0F, -4.5F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0529F, -2.2044F, -0.5F, -0.0873F, 0.0F, -0.2618F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(39, 38).addBox(-0.5F, -2.0F, -4.5F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0529F, -2.2044F, -0.5F, -0.0873F, 0.0F, 0.2618F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(60, 50).addBox(-4.0F, 4.0F, -0.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.0F, 3.5F, 0.1745F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -0.5F, -6.5F, 10.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, 0.5F, -0.0433F, 0.0018F, -0.0052F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(66, 30).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, -6.8F, -5.0F, 0.1745F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(0, 30)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(56, 3)
            .addBox(-5.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(44, 0)
            .addBox(-5.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(44, 0)
            .mirror()
            .addBox(2.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.6F))
            .mirror(false)
            .texOffs(56, 3)
            .mirror()
            .addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.6F))
            .mirror(false)
            .texOffs(78, 45)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(80, 26)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(80, 26)
            .mirror()
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(12, 63)
            .addBox(-3.0F, 3.0F, -3.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 62)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(70, 21)
            .addBox(-3.0F, 3.0F, 1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 62)
            .mirror()
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .mirror(false)
            .texOffs(78, 45)
            .mirror()
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(72, 38)
            .mirror()
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(-2.0F, 8.75F, -2.5F, 0.134F, 0.2608F, 0.0233F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(38, 33)
            .mirror()
            .addBox(-1.2F, -4.5F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(0, 0)
            .mirror()
            .addBox(-1.2F, -4.5F, -5.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(-0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(2.5F, 5.5F, 2.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(42, 62).mirror().addBox(-0.5F, -0.5F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
         PartPose.offsetAndRotation(1.5F, 11.5F, 0.0F, 0.0F, 0.0F, -0.3491F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(0, 10)
            .addBox(-0.5F, -1.25F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F))
            .texOffs(80, 16)
            .addBox(-1.5F, -2.25F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(10, 75)
            .addBox(-1.5F, -2.25F, -0.5F, 3.0F, 6.0F, 2.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(3.7553F, 3.1755F, -3.2673F, -0.1479F, -0.4025F, -0.0311F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-1.8F, -4.5F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(38, 33)
            .addBox(-1.8F, -4.5F, 3.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(-2.5F, 5.5F, -2.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(27, 73)
            .addBox(1.2443F, -1.7486F, -1.5323F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(57, 82)
            .addBox(1.2443F, -0.7486F, -1.5323F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(70, 55)
            .addBox(-1.7557F, -0.7486F, -1.5323F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(40, 80)
            .addBox(1.7443F, -1.7486F, -1.5323F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(72, 65)
            .addBox(-1.7557F, -1.7486F, -1.5323F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(33, 0)
            .addBox(-2.2557F, -1.7486F, -1.5323F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(72, 38)
            .addBox(-3.7557F, -1.7486F, -1.5323F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(61, 82)
            .addBox(-3.7557F, -0.7486F, -1.5323F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-0.2443F, 8.75F, 2.4678F, -3.0334F, -3.0E-4F, 3.1399F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(27, 73)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(40, 80)
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(2.0F, 8.75F, -2.5F, 0.134F, -0.2608F, -0.0233F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(61, 82)
            .addBox(-4.0F, -0.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(57, 82)
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(72, 65)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(33, 0)
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(70, 55)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(72, 38)
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(2.0F, 8.75F, -2.5F, 0.134F, -0.2608F, -0.0233F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(74, 6)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(0, 14)
            .mirror()
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(51, 74)
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(69, 71)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, -2.5F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(0, 14)
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(75, 77)
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, -2.5F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(12, 46)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(4, 10)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(44, 0)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0983F, 0.478F, 0.0453F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(0, 30).addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 9.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(42, 62).addBox(-2.5F, -0.5F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offsetAndRotation(-1.5F, 11.5F, 0.0F, 0.0F, 0.0F, 0.3491F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(14, 69)
            .mirror()
            .addBox(-1.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(50, 33)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(93, 0)
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(56, 65).mirror().addBox(-1.7F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(50, 33)
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(93, 0)
            .mirror()
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(14, 69)
            .addBox(-3.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(56, 65).addBox(-2.3F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-1.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(32, 51)
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(90, 57)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(86, 34)
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.49F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(86, 34)
            .mirror()
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(90, 57)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(32, 51)
            .mirror()
            .addBox(-2.0F, 5.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false),
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
