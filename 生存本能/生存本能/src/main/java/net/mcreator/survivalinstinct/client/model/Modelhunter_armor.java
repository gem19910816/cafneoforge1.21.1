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

public class Modelhunter_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelhunter_armor"), "main");
   public final ModelPart head;

   public Modelhunter_armor(ModelPart root) {
      this.head = root.getChild("head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(0, 102)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F))
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
      PartDefinition visor_r1 = head.addOrReplaceChild(
         "visor_r1",
         CubeListBuilder.create().texOffs(85, 27).addBox(-2.0F, -0.5344F, -4.0628F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.2182F, 0.0F, 0.0F)
      );
      PartDefinition visor_r2 = head.addOrReplaceChild(
         "visor_r2",
         CubeListBuilder.create()
            .texOffs(94, 45)
            .addBox(-2.9109F, 2.0163F, -5.6283F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(118, 14)
            .addBox(-2.9109F, 2.0163F, -2.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(107, 36)
            .addBox(-2.9109F, 2.0163F, -4.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(114, 11)
            .addBox(-2.7809F, 1.9292F, -6.616F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(101, 16)
            .addBox(-2.7809F, 1.9292F, -7.0335F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0F, 0.3054F, 0.0F)
      );
      PartDefinition visor_r3 = head.addOrReplaceChild(
         "visor_r3",
         CubeListBuilder.create()
            .texOffs(101, 12)
            .addBox(-1.7019F, 1.9292F, -6.3662F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(88, 34)
            .addBox(-1.7019F, 1.9292F, -5.9487F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(121, 27)
            .addBox(-1.7019F, 2.0163F, -3.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(111, 29)
            .addBox(-1.7019F, 2.0163F, -1.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(95, 56)
            .addBox(-1.7019F, 2.0163F, -4.9525F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0F, 0.0873F, 0.0F)
      );
      PartDefinition visor_r4 = head.addOrReplaceChild(
         "visor_r4",
         CubeListBuilder.create()
            .texOffs(101, 4)
            .addBox(-0.2981F, 1.9292F, -6.3662F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(114, 32)
            .addBox(-0.2981F, 2.0163F, -1.9525F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(96, 66)
            .addBox(-0.2981F, 2.0163F, -4.9525F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(88, 34)
            .addBox(-0.2981F, 1.9292F, -5.9487F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0F, -0.0873F, 0.0F)
      );
      PartDefinition visor_r5 = head.addOrReplaceChild(
         "visor_r5",
         CubeListBuilder.create()
            .texOffs(107, 22)
            .addBox(0.0F, 1.9292F, -5.8744F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F))
            .texOffs(116, 21)
            .addBox(0.0F, 2.0163F, -3.8782F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition visor_r6 = head.addOrReplaceChild(
         "visor_r6",
         CubeListBuilder.create()
            .texOffs(101, 0)
            .addBox(0.7809F, 1.9292F, -7.0335F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(106, 29)
            .addBox(0.7809F, 1.9292F, -6.616F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(100, 29)
            .addBox(0.9109F, 2.0163F, -4.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(122, 5)
            .addBox(0.9109F, 2.0163F, -2.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(110, 48)
            .addBox(0.9109F, 2.0163F, -5.6283F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0F, -0.3054F, 0.0F)
      );
      PartDefinition visor_r7 = head.addOrReplaceChild(
         "visor_r7",
         CubeListBuilder.create().texOffs(108, 0).addBox(-4.0F, 1.3031F, -3.5191F, 8.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition visor_r8 = head.addOrReplaceChild(
         "visor_r8",
         CubeListBuilder.create().texOffs(127, 30).addBox(-1.0F, -1.4305F, -3.0018F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.829F, 0.0F, 0.0F)
      );
      PartDefinition visor_r9 = head.addOrReplaceChild(
         "visor_r9",
         CubeListBuilder.create()
            .texOffs(114, 38)
            .addBox(-2.0F, -0.713F, -2.0102F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F))
            .texOffs(107, 50)
            .addBox(-2.0F, -0.713F, -2.0102F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -7.2628F, -4.1938F, 0.2618F, 0.0F, 0.0F)
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
         CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(5.7595F, -4.1147F, 0.0F, 0.0F, -0.6109F, -0.0873F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(82, 70).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(4.5907F, -4.5F, -1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create()
            .texOffs(16, 41)
            .mirror()
            .addBox(-0.4163F, -1.2608F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(4.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create().texOffs(28, 52).addBox(-0.5958F, -1.4939F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.7595F, -4.1147F, 0.0F, 0.0F, 0.6109F, 0.0873F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create()
            .texOffs(58, 35)
            .addBox(-1.2837F, -1.2608F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(47, 55)
            .addBox(-1.2837F, -2.2608F, 2.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-4.4163F, -4.7392F, -5.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create().texOffs(9, 70).addBox(-0.6647F, 0.4848F, -1.5F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.2091F, -5.5708F, -1.5F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(24, 36).addBox(0.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(-4.1972F, -6.5038F, -4.87F, 0.0F, -0.6545F, 0.0F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create().texOffs(56, 11).addBox(0.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, 3.5F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create().texOffs(56, 59).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(3.9658F, -4.5038F, -4.1154F, 0.0983F, 0.478F, 0.0453F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create().texOffs(0, 41).addBox(-1.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(4.1972F, -6.5038F, -4.87F, 0.0F, 0.6545F, 0.0F)
      );
      PartDefinition head_r24 = head.addOrReplaceChild(
         "head_r24",
         CubeListBuilder.create().texOffs(44, 27).addBox(1.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, 0.7383F, -0.0797F)
      );
      PartDefinition head_r25 = head.addOrReplaceChild(
         "head_r25",
         CubeListBuilder.create().texOffs(68, 89).addBox(-1.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5F, -5.0F, 3.5F, 0.0F, 0.7418F, 0.0F)
      );
      PartDefinition head_r26 = head.addOrReplaceChild(
         "head_r26",
         CubeListBuilder.create().texOffs(18, 52).addBox(-0.3742F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition head_r27 = head.addOrReplaceChild(
         "head_r27",
         CubeListBuilder.create()
            .texOffs(35, 87)
            .addBox(-0.5837F, 0.7392F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(8, 49)
            .addBox(-0.5837F, -1.2608F, -6.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.4163F, -6.7392F, 2.0F, 0.0F, 0.0F, 0.0873F)
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
      return LayerDefinition.create(meshdefinition, 156, 156);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
