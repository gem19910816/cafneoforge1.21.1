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

public class Modelhunter<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelhunter"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart left_leg;
   public final ModelPart right_leg;

   public Modelhunter(ModelPart root) {
      this.head = root.getChild("head");
      this.body = root.getChild("body");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_shoe = root.getChild("left_shoe");
      this.right_shoe = root.getChild("right_shoe");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(0, 12)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(17, 91)
            .addBox(-5.0F, -9.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(-0.2F))
            .texOffs(24, 106)
            .addBox(-5.0F, -9.0F, -2.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(74, 26)
            .addBox(-3.0F, -8.0F, -5.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(28, 51)
            .addBox(-6.0F, -7.0F, -2.5F, 2.0F, 3.0F, 8.0F, new CubeDeformation(-0.4F))
            .texOffs(50, 47)
            .addBox(4.0F, -7.0F, -2.5F, 2.0F, 3.0F, 8.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 69)
            .addBox(3.5F, -5.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F))
            .texOffs(55, 67)
            .addBox(-6.5F, -5.0F, -3.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.6F))
            .texOffs(0, 0)
            .addBox(-5.0F, -5.8F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
            .texOffs(30, 0)
            .addBox(-5.0F, -5.2F, 0.0F, 10.0F, 4.0F, 5.0F, new CubeDeformation(-0.4F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition visor_r1 = head.addOrReplaceChild(
         "visor_r1",
         CubeListBuilder.create().texOffs(62, 49).addBox(-2.0F, -0.713F, -2.0102F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -7.2628F, -4.1938F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition visor_r2 = head.addOrReplaceChild(
         "visor_r2",
         CubeListBuilder.create().texOffs(54, 58).addBox(-1.0F, -1.4305F, -3.0018F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.8727F, 0.0F, 0.0F)
      );
      PartDefinition visor_r3 = head.addOrReplaceChild(
         "visor_r3",
         CubeListBuilder.create().texOffs(24, 12).addBox(-4.0F, 1.3031F, -3.5191F, 8.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r4 = head.addOrReplaceChild(
         "visor_r4",
         CubeListBuilder.create()
            .texOffs(76, 75)
            .addBox(0.9109F, 2.0163F, -5.6283F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(38, 17)
            .addBox(0.9109F, 2.0163F, -2.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(12, 44)
            .addBox(0.9109F, 2.0163F, -4.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(18, 44)
            .addBox(0.7809F, 1.9292F, -6.616F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(0.7809F, 1.9292F, -7.0335F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0457F, -0.3051F, -0.0138F)
      );
      PartDefinition visor_r5 = head.addOrReplaceChild(
         "visor_r5",
         CubeListBuilder.create()
            .texOffs(55, 77)
            .addBox(-0.2981F, 2.0163F, -4.9525F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(46, 36)
            .addBox(-0.2981F, 2.0163F, -1.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(0, 4)
            .addBox(-0.2981F, 1.9292F, -6.3662F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(22, 59)
            .addBox(-0.2981F, 1.9292F, -5.9487F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0438F, -0.0872F, -0.0038F)
      );
      PartDefinition visor_r6 = head.addOrReplaceChild(
         "visor_r6",
         CubeListBuilder.create()
            .texOffs(48, 25)
            .addBox(0.0F, 2.0163F, -3.8782F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(36, 48)
            .addBox(0.0F, 1.9292F, -5.8744F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r7 = head.addOrReplaceChild(
         "visor_r7",
         CubeListBuilder.create()
            .texOffs(27, 78)
            .addBox(-1.7019F, 2.0163F, -4.9525F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(40, 55)
            .addBox(-1.7019F, 2.0163F, -1.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(58, 42)
            .addBox(-1.7019F, 2.0163F, -3.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(22, 59)
            .addBox(-1.7019F, 1.9292F, -5.9487F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12)
            .addBox(-1.7019F, 1.9292F, -6.3662F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0438F, 0.0872F, 0.0038F)
      );
      PartDefinition visor_r8 = head.addOrReplaceChild(
         "visor_r8",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .addBox(-2.7809F, 1.9292F, -7.0335F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(60, 10)
            .addBox(-2.7809F, 1.9292F, -6.616F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 62)
            .addBox(-2.9109F, 2.0163F, -4.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(64, 13)
            .addBox(-2.9109F, 2.0163F, -2.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(0, 79)
            .addBox(-2.9109F, 2.0163F, -5.6283F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0457F, 0.3051F, 0.0138F)
      );
      PartDefinition visor_r9 = head.addOrReplaceChild(
         "visor_r9",
         CubeListBuilder.create().texOffs(0, 60).addBox(-2.0F, -0.5344F, -4.0628F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(44, 73).addBox(-1.0F, -0.5F, -2.5F, 3.0F, 3.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(-5.5F, -2.5F, -0.5F, 0.0F, 0.0F, 0.5236F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(23, 64).addBox(-2.0F, -2.5F, -2.5F, 3.0F, 5.0F, 5.0F, new CubeDeformation(-0.7F)),
         PartPose.offsetAndRotation(5.5F, -2.5F, -0.5F, -0.001F, 0.0089F, -0.5236F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(70, 19)
            .addBox(-3.0F, 3.0F, -3.3F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(55, 30)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(72, 4)
            .addBox(-3.0F, 5.0F, -3.3F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 19)
            .addBox(-3.0F, 3.0F, -3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(72, 4)
            .addBox(-3.0F, 5.0F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(65, 71)
            .addBox(3.0F, 3.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(70, 49)
            .addBox(3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(64, 10)
            .addBox(-5.0F, 3.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(33, 69)
            .addBox(-5.0F, 7.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(72, 0)
            .addBox(-3.0F, 7.0F, -3.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(62, 40)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(60, 0)
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(10, 59)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.65F))
            .texOffs(60, 58)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(55, 0)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(42, 62)
            .addBox(-1.0F, 0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(0, 65)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(3.0F, 4.5F, -3.1F, 0.0481F, -0.4359F, -0.0203F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-0.75F, -2.2704F, 0.1159F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
            .texOffs(43, 9)
            .addBox(-0.75F, -0.2296F, -0.6159F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-3.25F, 2.2267F, -3.0832F, 0.0426F, 0.0094F, -0.218F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(55, 0)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(60, 4)
            .addBox(-1.0F, 0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(34, 65)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(4.0F, 9.5F, -3.1F, 0.0517F, -0.5666F, -0.0278F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(0, 8)
            .addBox(-1.0F, 0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(0, 67)
            .addBox(-1.0F, -1.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.15F))
            .texOffs(55, 0)
            .addBox(-1.0F, -2.5F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-4.0F, 9.5F, -3.1F, 0.0569F, 0.6973F, 0.0366F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(42, 58)
            .addBox(-1.5F, -2.25F, -3.0F, 3.0F, 5.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(58, 19)
            .addBox(-1.5F, -2.25F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(-4.9924F, 8.1632F, 0.0F, -3.1416F, 0.0F, 2.9671F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(58, 19)
            .addBox(-2.0F, -2.25F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(42, 58)
            .addBox(-2.0F, -2.25F, -3.0F, 3.0F, 5.0F, 6.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(5.5F, 8.25F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(42, 58)
            .addBox(-2.0F, -2.25F, -3.5F, 3.0F, 5.0F, 6.0F, new CubeDeformation(-0.6F))
            .texOffs(97, 4)
            .addBox(-2.0F, -2.25F, -3.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(58, 19)
            .addBox(-2.0F, -2.25F, -3.5F, 3.0F, 4.0F, 6.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.5F, 9.25F, -4.0F, 1.5765F, 1.3092F, 1.5651F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(97, 4).addBox(-2.0F, -2.25F, -3.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(4.5F, 9.25F, -4.0F, 1.5765F, 1.3092F, 1.5651F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(72, 4)
            .addBox(-3.0F, 0.0F, -1.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 19)
            .addBox(-3.0F, -2.0F, -1.25F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(72, 4)
            .addBox(-3.0F, -4.0F, -1.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(55, 30)
            .addBox(-4.0F, -7.0F, -1.05F, 8.0F, 9.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(70, 19)
            .addBox(-3.0F, -6.0F, -1.25F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(72, 4)
            .addBox(-3.0F, -4.0F, -1.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 19)
            .addBox(-3.0F, -6.0F, -1.25F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.0F, 2.25F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(48, 9).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(0, 44).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(20, 43)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(0, 110)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(0, 112)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(20, 43)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(0, 112)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false)
            .texOffs(0, 110)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(93, 101).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(93, 101)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(87, 117)
            .addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.25F))
            .texOffs(101, 90)
            .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(101, 85)
            .addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(106, 114)
            .addBox(-4.0F, 1.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
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
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
