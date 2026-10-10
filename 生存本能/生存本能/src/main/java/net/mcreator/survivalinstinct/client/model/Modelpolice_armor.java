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

public class Modelpolice_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelpolice_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelpolice_armor(ModelPart root) {
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
            .texOffs(0, 12)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(11, 64)
            .addBox(-2.0F, -7.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(44, 45)
            .addBox(-1.0F, -8.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.4687F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.0313F, -0.0661F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(24, 20)
            .addBox(-5.0F, 0.5F, -6.25F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 28)
            .addBox(-4.0F, -1.5F, -3.25F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.551F)),
         PartPose.offsetAndRotation(0.0F, -6.5F, -0.75F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create().texOffs(32, 29).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(48, 45)
            .addBox(-4.0F, -4.5015F, -1.4827F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F))
            .texOffs(38, 64)
            .addBox(-3.0F, -2.0015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 16)
            .addBox(-3.0F, -3.5015F, -0.4827F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(56, 10)
            .addBox(-3.5F, -1.1167F, -0.2176F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(66, 45)
            .addBox(-3.0F, -0.5167F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(30, 2)
            .addBox(-3.0F, 0.9833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
            .texOffs(30, 0)
            .addBox(-3.0F, 2.4833F, -0.0176F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
         PartPose.offsetAndRotation(0.0F, 6.5015F, 2.4173F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(0, 64)
            .addBox(-1.5F, -5.6F, -0.6F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 12)
            .addBox(-1.5F, -5.6F, -0.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(2.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(0, 4)
            .addBox(-2.0F, -1.75F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(24, 32)
            .addBox(-2.0F, -1.75F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(2.5F, 8.1493F, -2.968F, 3.0986F, 0.0423F, 3.1246F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(24, 12)
            .addBox(-1.0F, -1.75F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 54)
            .addBox(-1.0F, -1.75F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.5F, 8.1493F, -2.968F, 3.0103F, -0.1308F, -3.1359F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(56, 37)
            .addBox(-1.0F, -1.75F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(54, 56)
            .addBox(-1.0F, -1.75F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-3.5F, 8.1493F, -2.968F, 3.0533F, -0.218F, -3.1319F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(0, 38)
            .addBox(-1.5F, -5.6F, -0.6F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(68, 38)
            .addBox(-1.5F, -5.6F, -0.6F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(43, 66)
            .addBox(-3.5F, -3.6F, -0.8F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 8.0F, -2.9F, 3.098F, 0.0F, -3.1416F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(0, 12)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.15F))
            .texOffs(0, 28)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(16, 64)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 0.95F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(24, 12).addBox(-4.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(56, 37).addBox(-2.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(-3.0F, 7.95F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create()
            .texOffs(30, 4)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(62, 50)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(68, 0)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-3.0F, 0.95F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(42, 56).addBox(-1.0F, 0.05F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.0F, 7.95F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(54, 58).addBox(1.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 7.0208F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(24, 29).addBox(-2.0F, -0.75F, -0.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0F, 3.75F, -3.1F, -3.0543F, -0.0019F, 3.098F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create()
            .texOffs(28, 38)
            .addBox(-0.5F, 0.25F, -0.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(32, 45)
            .addBox(1.5F, 0.25F, -0.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 3.75F, -3.1F, 3.098F, -0.0019F, 3.098F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.75F, -0.4F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-2.0F, 3.75F, -3.1F, 3.098F, -0.0019F, 3.098F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create().texOffs(52, 16).addBox(-4.0F, -4.5F, -1.45F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, -2.55F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(16, 38).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(32, 45).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(0, 38).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(40, 0)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(68, 33)
            .addBox(-2.1F, 1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(68, 23)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(93, 0)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(89, 3)
            .mirror()
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.5F))
            .mirror(false)
            .texOffs(93, 6)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.6F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.1F, 2.9253F, -0.2885F, -0.0863F, -1.5272F, 0.001F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(56, 27)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(11, 67)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(56, 27)
            .mirror()
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(11, 67)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
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
