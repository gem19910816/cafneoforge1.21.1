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

public class Modelghillie<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelghillie"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;

   public Modelghillie(ModelPart root) {
      this.head = root.getChild("head");
      this.body = root.getChild("body");
      this.left_shoe = root.getChild("left_shoe");
      this.right_shoe = root.getChild("right_shoe");
      this.left_arm = root.getChild("left_arm");
      this.right_arm = root.getChild("right_arm");
      this.left_leg = root.getChild("left_leg");
      this.right_leg = root.getChild("right_leg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(0, 112)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 16)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
            .texOffs(0, 0)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, -4.0F, 5.0F, 0.2717F, 0.6271F, 0.3576F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(60, 14).addBox(-0.007F, -2.5942F, -1.165F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -6.1902F, 4.1313F, 0.7405F, 0.6121F, 0.9345F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create()
            .texOffs(0, 12)
            .mirror()
            .addBox(1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -6.1902F, 4.1313F, 1.0805F, -1.2378F, -1.0494F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(0, 12).addBox(-1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -6.1902F, 4.1313F, 1.0805F, 1.2378F, 1.0494F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .mirror()
            .addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(24, 0)
            .mirror()
            .addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(6.0F, -6.0F, -4.0F, -0.4367F, 0.6516F, -0.6952F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(38, 13).mirror().addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, -6.0F, -5.0F, -0.3651F, 0.9021F, -0.7305F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -6.0F, -1.0F, 0.0789F, 0.0109F, 0.578F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(60, 14).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -5.0F, 3.0F, 0.1631F, 0.0952F, 0.7225F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(48, 24).addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, -8.0F, 2.0F, 0.008F, -0.1154F, 0.7346F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, -1.0F, 5.0F, 0.2717F, 0.6271F, 0.3576F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(60, 7).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -5.0F, 0.0F, 0.1359F, -0.0223F, -0.3145F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create()
            .texOffs(0, 47)
            .mirror()
            .addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 47)
            .mirror()
            .addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(4.0F, 0.0F, -5.0F, -0.3214F, 1.4353F, -0.6587F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create().texOffs(0, 30).mirror().addBox(0.0F, -2.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, -0.6509F, 0.9027F, -0.7344F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(38, 13).mirror().addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, -4.0F, -5.0F, -0.8887F, 0.9021F, -0.7305F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create().texOffs(24, 52).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -1.0F, -4.0F, -0.2837F, 0.6315F, -0.453F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.0F, -7.0F, -5.0F, -1.2834F, 1.1958F, -1.5981F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create()
            .texOffs(0, 12)
            .mirror()
            .addBox(1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 12)
            .mirror()
            .addBox(1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -3.1902F, 4.1313F, 1.0805F, -1.2378F, -1.0494F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create()
            .texOffs(0, 12)
            .addBox(-1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12)
            .addBox(-1.1282F, -2.2209F, 0.6154F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -3.1902F, 4.1313F, 1.0805F, 1.2378F, 1.0494F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create()
            .texOffs(60, 14)
            .mirror()
            .addBox(0.007F, -2.5942F, -1.165F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -3.1902F, 4.1313F, 0.7405F, -0.6121F, -0.9345F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -1.0F, 5.0F, 0.2717F, -0.6271F, -0.3576F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create()
            .texOffs(48, 24)
            .mirror()
            .addBox(0.9887F, -2.7882F, -1.9885F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -6.1902F, 4.1313F, 0.5284F, -0.4259F, -0.82F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create()
            .texOffs(60, 14)
            .mirror()
            .addBox(0.007F, -2.5942F, -1.165F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -6.1902F, 4.1313F, 0.7405F, -0.6121F, -0.9345F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create()
            .texOffs(0, 55)
            .mirror()
            .addBox(0.3683F, -3.8775F, -5.0727F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(3.0608F, -6.1902F, 4.1313F, 0.5464F, -0.6112F, -0.7303F)
      );
      PartDefinition head_r24 = head.addOrReplaceChild(
         "head_r24",
         CubeListBuilder.create().texOffs(48, 24).mirror().addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, -8.0F, 2.0F, 0.008F, 0.1154F, -0.7346F)
      );
      PartDefinition head_r25 = head.addOrReplaceChild(
         "head_r25",
         CubeListBuilder.create().texOffs(58, 42).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -3.0F, -2.0F, -0.1204F, 0.2803F, -0.2721F)
      );
      PartDefinition head_r26 = head.addOrReplaceChild(
         "head_r26",
         CubeListBuilder.create().texOffs(58, 54).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -3.0F, 2.0F, 0.1376F, -0.0044F, -0.4442F)
      );
      PartDefinition head_r27 = head.addOrReplaceChild(
         "head_r27",
         CubeListBuilder.create().texOffs(60, 14).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -5.0F, 3.0F, 0.1631F, -0.0952F, -0.7225F)
      );
      PartDefinition head_r28 = head.addOrReplaceChild(
         "head_r28",
         CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, -4.0F, -3.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, -4.0F, 5.0F, 0.2717F, -0.6271F, -0.3576F)
      );
      PartDefinition head_r29 = head.addOrReplaceChild(
         "head_r29",
         CubeListBuilder.create().texOffs(48, 24).addBox(-0.9887F, -2.7882F, -1.9885F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -6.1902F, 4.1313F, 0.5284F, 0.4259F, 0.82F)
      );
      PartDefinition head_r30 = head.addOrReplaceChild(
         "head_r30",
         CubeListBuilder.create().texOffs(24, 52).addBox(0.0F, -3.0F, -3.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -1.0F, -4.0F, -0.2837F, -0.6315F, 0.453F)
      );
      PartDefinition head_r31 = head.addOrReplaceChild(
         "head_r31",
         CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -4.0F, -4.0F, -0.2968F, -0.5508F, 0.4223F)
      );
      PartDefinition head_r32 = head.addOrReplaceChild(
         "head_r32",
         CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 0.0F, -5.0F, -0.3214F, -1.4353F, 0.6587F)
      );
      PartDefinition head_r33 = head.addOrReplaceChild(
         "head_r33",
         CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, -2.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, -0.6509F, -0.9027F, 0.7344F)
      );
      PartDefinition head_r34 = head.addOrReplaceChild(
         "head_r34",
         CubeListBuilder.create().texOffs(38, 13).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, -4.0F, -5.0F, -0.8887F, -0.9021F, 0.7305F)
      );
      PartDefinition head_r35 = head.addOrReplaceChild(
         "head_r35",
         CubeListBuilder.create().texOffs(38, 13).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, -6.0F, -5.0F, -0.3651F, -0.9021F, 0.7305F)
      );
      PartDefinition head_r36 = head.addOrReplaceChild(
         "head_r36",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0F, -7.0F, -5.0F, -1.2834F, -1.1958F, 1.5981F)
      );
      PartDefinition head_r37 = head.addOrReplaceChild(
         "head_r37",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -6.0F, -4.0F, -0.4367F, -0.6516F, 0.6952F)
      );
      PartDefinition head_r38 = head.addOrReplaceChild(
         "head_r38",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -5.0F, -2.0F, -0.1896F, -0.2396F, 0.5398F)
      );
      PartDefinition head_r39 = head.addOrReplaceChild(
         "head_r39",
         CubeListBuilder.create().texOffs(0, 55).addBox(-0.3683F, -3.8775F, -5.0727F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -6.1902F, 4.1313F, 0.5464F, 0.6112F, 0.7303F)
      );
      PartDefinition head_r40 = head.addOrReplaceChild(
         "head_r40",
         CubeListBuilder.create().texOffs(58, 42).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -3.0F, -2.0F, -0.1204F, -0.2803F, 0.2721F)
      );
      PartDefinition head_r41 = head.addOrReplaceChild(
         "head_r41",
         CubeListBuilder.create().texOffs(58, 54).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -3.0F, 2.0F, 0.1376F, 0.0044F, 0.4442F)
      );
      PartDefinition head_r42 = head.addOrReplaceChild(
         "head_r42",
         CubeListBuilder.create().texOffs(60, 7).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -5.0F, 0.0F, 0.1359F, 0.0223F, 0.3145F)
      );
      PartDefinition head_r43 = head.addOrReplaceChild(
         "head_r43",
         CubeListBuilder.create().texOffs(60, 14).addBox(-0.007F, -2.5942F, -1.165F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0608F, -3.1902F, 4.1313F, 0.7405F, 0.6121F, 0.9345F)
      );
      PartDefinition head_r44 = head.addOrReplaceChild(
         "head_r44",
         CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -4.0F, -4.0F, -0.2968F, 0.5508F, -0.4223F)
      );
      PartDefinition head_r45 = head.addOrReplaceChild(
         "head_r45",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -5.0F, -2.0F, -0.1896F, 0.2396F, -0.5398F)
      );
      PartDefinition head_r46 = head.addOrReplaceChild(
         "head_r46",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(6.0F, -6.0F, -1.0F, 0.0789F, -0.0109F, -0.578F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(104, 0)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(72, 62)
            .addBox(-3.0F, 3.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 34)
            .addBox(-3.0F, 5.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(68, 22)
            .addBox(-3.0F, 7.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(68, 20)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(12, 58)
            .addBox(-5.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(26, 55)
            .addBox(-5.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(26, 55)
            .mirror()
            .addBox(2.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(12, 58)
            .mirror()
            .addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(66, 60)
            .addBox(-3.0F, 9.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(66, 48)
            .addBox(-3.0F, 7.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(64, 32)
            .addBox(-3.0F, 5.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 48)
            .addBox(-3.0F, 3.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(8, 72)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(8, 72)
            .mirror()
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(22, 71)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(22, 71)
            .mirror()
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(48, 0)
            .addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 50)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(32, 44)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 50)
            .mirror()
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .mirror(false),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 4.0F, 6.0F, 0.4469F, 0.5153F, 0.5823F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 11.0F, 5.0F, -0.3512F, 0.8739F, -0.0457F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0F, 5.0F, 4.0F, 0.3088F, 0.8859F, 0.7907F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 7.0F, 3.0F, 0.3088F, 0.8859F, 0.7907F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 8.0F, 6.0F, 0.2782F, 0.4603F, 0.8723F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, 4.0F, 6.0F, 0.4469F, -0.5153F, -0.5823F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, 11.0F, 5.0F, -0.3512F, -0.8739F, 0.0457F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.0F, 5.0F, 4.0F, 0.3088F, -0.8859F, -0.7907F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, 7.0F, 3.0F, 0.3088F, -0.8859F, -0.7907F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, 8.0F, 6.0F, 0.2782F, -0.4603F, -0.8723F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 7.0F, 4.0F, 0.846F, -1.0758F, -1.2424F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 8.0F, 7.0F, 0.6167F, -0.6617F, -1.0545F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 3.0F, 7.0F, 0.8135F, -0.6502F, -1.4064F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 2.0F, 3.0F, 0.3364F, -0.3284F, -0.5905F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 5.0F, 5.0F, 0.846F, 1.0758F, 1.2424F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 4.0F, 7.0F, 0.718F, -0.7918F, -0.7493F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, 5.0F, 5.0F, 0.846F, -1.0758F, -1.2424F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, 3.0F, 1.0F, 0.0747F, -0.0277F, -0.3604F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, 3.0F, -2.0F, -0.1896F, 0.2396F, -0.5398F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 5.0F, 3.0F, 0.3146F, -0.3452F, -0.6179F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 0.3364F, -0.3284F, -0.5905F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, 4.0F, -2.0F, -0.4367F, 0.6516F, -0.6952F)
      );
      PartDefinition body_r23 = body.addOrReplaceChild(
         "body_r23",
         CubeListBuilder.create().texOffs(0, 47).mirror().addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 10.0F, -2.0F, -0.0923F, 0.6963F, -0.4328F)
      );
      PartDefinition body_r24 = body.addOrReplaceChild(
         "body_r24",
         CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, 6.0F, -1.0F, -0.2968F, 0.5508F, -0.4223F)
      );
      PartDefinition body_r25 = body.addOrReplaceChild(
         "body_r25",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.0F, 9.0F, 1.0F, 0.0249F, 0.1976F, -0.2381F)
      );
      PartDefinition body_r26 = body.addOrReplaceChild(
         "body_r26",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 4.0F, 3.0F, 0.3178F, 0.0025F, -0.4773F)
      );
      PartDefinition body_r27 = body.addOrReplaceChild(
         "body_r27",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 11.0F, 6.0F, -0.3969F, -1.2223F, 0.0859F)
      );
      PartDefinition body_r28 = body.addOrReplaceChild(
         "body_r28",
         CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 10.0F, -2.0F, -0.0923F, -0.6963F, 0.4328F)
      );
      PartDefinition body_r29 = body.addOrReplaceChild(
         "body_r29",
         CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, 6.0F, -1.0F, -0.2968F, -0.5508F, 0.4223F)
      );
      PartDefinition body_r30 = body.addOrReplaceChild(
         "body_r30",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, 9.0F, 1.0F, 0.0249F, -0.1976F, 0.2381F)
      );
      PartDefinition body_r31 = body.addOrReplaceChild(
         "body_r31",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, 3.0F, -2.0F, -0.1896F, -0.2396F, 0.5398F)
      );
      PartDefinition body_r32 = body.addOrReplaceChild(
         "body_r32",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 11.0F, 6.0F, -0.3969F, 1.2223F, -0.0859F)
      );
      PartDefinition body_r33 = body.addOrReplaceChild(
         "body_r33",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 7.0F, 4.0F, 0.846F, 1.0758F, 1.2424F)
      );
      PartDefinition body_r34 = body.addOrReplaceChild(
         "body_r34",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, 3.0F, 1.0F, 0.0747F, 0.0277F, 0.3604F)
      );
      PartDefinition body_r35 = body.addOrReplaceChild(
         "body_r35",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 4.0F, 3.0F, 0.3178F, -0.0025F, 0.4773F)
      );
      PartDefinition body_r36 = body.addOrReplaceChild(
         "body_r36",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 5.0F, 3.0F, 0.3146F, 0.3452F, 0.6179F)
      );
      PartDefinition body_r37 = body.addOrReplaceChild(
         "body_r37",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 0.3364F, 0.3284F, 0.5905F)
      );
      PartDefinition body_r38 = body.addOrReplaceChild(
         "body_r38",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 4.0F, 7.0F, 0.718F, 0.7918F, 0.7493F)
      );
      PartDefinition body_r39 = body.addOrReplaceChild(
         "body_r39",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 8.0F, 7.0F, 0.6167F, 0.6617F, 1.0545F)
      );
      PartDefinition body_r40 = body.addOrReplaceChild(
         "body_r40",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 3.0F, 7.0F, 0.8135F, 0.6502F, 1.4064F)
      );
      PartDefinition body_r41 = body.addOrReplaceChild(
         "body_r41",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 2.0F, 3.0F, 0.3364F, 0.3284F, 0.5905F)
      );
      PartDefinition body_r42 = body.addOrReplaceChild(
         "body_r42",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.0F, 4.0F, -2.0F, -0.4367F, -0.6516F, 0.6952F)
      );
      PartDefinition body_r43 = body.addOrReplaceChild(
         "body_r43",
         CubeListBuilder.create()
            .texOffs(16, 32)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(32, 22)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(24, 0)
            .mirror()
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(30, 0)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(68, 5)
            .mirror()
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(5.0F, 9.0F, -0.05F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition body_r44 = body.addOrReplaceChild(
         "body_r44",
         CubeListBuilder.create()
            .texOffs(75, 64)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(44, 0)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(64, 75)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(40, 21)
            .mirror()
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.0F, 8.25F, -3.7F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r45 = body.addOrReplaceChild(
         "body_r45",
         CubeListBuilder.create()
            .texOffs(16, 40)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(50, 75)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(30, 22)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(75, 36)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 8.25F, -3.7F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r46 = body.addOrReplaceChild(
         "body_r46",
         CubeListBuilder.create()
            .texOffs(40, 21)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(64, 75)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(44, 0)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(75, 64)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 8.25F, -3.7F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition body_r47 = body.addOrReplaceChild(
         "body_r47",
         CubeListBuilder.create()
            .texOffs(16, 32)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(32, 22)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(24, 0)
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(30, 0)
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(68, 5)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(-5.0F, 9.0F, -0.05F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition body_r48 = body.addOrReplaceChild(
         "body_r48",
         CubeListBuilder.create()
            .texOffs(63, 62)
            .mirror()
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(67, 43)
            .mirror()
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(24, 16)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(42, 71)
            .mirror()
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(63, 50)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(42, 71)
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(67, 43)
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(63, 62)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F)),
         PartPose.offsetAndRotation(0.0F, 8.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r49 = body.addOrReplaceChild(
         "body_r49",
         CubeListBuilder.create()
            .texOffs(44, 58)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(39, 64)
            .mirror()
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(68, 15)
            .mirror()
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(64, 37)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(74, 72)
            .mirror()
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(74, 72)
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(39, 64)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(68, 15)
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(0.0F, 3.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create().texOffs(82, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create().texOffs(82, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(16, 40)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -1.0F, 1.0F, 0.0783F, -0.0143F, -0.5345F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(2.775F, 2.6734F, -2.4457F, -0.7524F, 0.8694F, -0.7057F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 1.0F, 1.0F, 0.0776F, -0.0177F, -0.491F)
      );
      PartDefinition left_arm_r4 = left_arm.addOrReplaceChild(
         "left_arm_r4",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(2.775F, 3.6734F, -2.4457F, -0.75F, 0.6847F, -0.7145F)
      );
      PartDefinition left_arm_r5 = left_arm.addOrReplaceChild(
         "left_arm_r5",
         CubeListBuilder.create().texOffs(59, 65).mirror().addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.0763F, 0.3715F, 3.74F, 1.0659F, -0.8139F, -1.0426F)
      );
      PartDefinition left_arm_r6 = left_arm.addOrReplaceChild(
         "left_arm_r6",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 4.0F, -2.0F, -0.0506F, 0.9633F, -0.4667F)
      );
      PartDefinition left_arm_r7 = left_arm.addOrReplaceChild(
         "left_arm_r7",
         CubeListBuilder.create().texOffs(59, 65).mirror().addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.6746F, 0.6631F, 2.472F, 0.3946F, -0.6562F, -0.7848F)
      );
      PartDefinition left_arm_r8 = left_arm.addOrReplaceChild(
         "left_arm_r8",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.0783F, -0.0143F, -0.5345F)
      );
      PartDefinition left_arm_r9 = left_arm.addOrReplaceChild(
         "left_arm_r9",
         CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, 7.0F, -1.0F, -0.3369F, 0.7167F, -0.4897F)
      );
      PartDefinition left_arm_r10 = left_arm.addOrReplaceChild(
         "left_arm_r10",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.0F, 3.0F, 3.0F, 0.961F, -0.9468F, -1.1196F)
      );
      PartDefinition left_arm_r11 = left_arm.addOrReplaceChild(
         "left_arm_r11",
         CubeListBuilder.create().texOffs(60, 7).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.0F, 5.0F, 3.0F, 0.1483F, -0.4111F, -0.3711F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(16, 40).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(59, 65).addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0763F, 0.3715F, 3.74F, 1.0659F, 0.8139F, 1.0426F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(59, 65).addBox(-1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.775F, 2.6734F, -2.4457F, -0.7524F, -0.8694F, 0.7057F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create().texOffs(59, 65).addBox(-1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.775F, 3.6734F, -2.4457F, -0.75F, -0.6847F, 0.7145F)
      );
      PartDefinition right_arm_r4 = right_arm.addOrReplaceChild(
         "right_arm_r4",
         CubeListBuilder.create().texOffs(59, 65).addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.6746F, 0.6631F, 2.472F, 0.3946F, 0.6562F, 0.7848F)
      );
      PartDefinition right_arm_r5 = right_arm.addOrReplaceChild(
         "right_arm_r5",
         CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 7.0F, -1.0F, -0.3369F, -0.7167F, 0.4897F)
      );
      PartDefinition right_arm_r6 = right_arm.addOrReplaceChild(
         "right_arm_r6",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, -1.0F, 1.0F, 0.0783F, 0.0143F, 0.5345F)
      );
      PartDefinition right_arm_r7 = right_arm.addOrReplaceChild(
         "right_arm_r7",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 0.0F, 1.0F, 0.0783F, 0.0143F, 0.5345F)
      );
      PartDefinition right_arm_r8 = right_arm.addOrReplaceChild(
         "right_arm_r8",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 1.0F, 1.0F, 0.0776F, 0.0177F, 0.491F)
      );
      PartDefinition right_arm_r9 = right_arm.addOrReplaceChild(
         "right_arm_r9",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 3.0F, 3.0F, 0.961F, 0.9468F, 1.1196F)
      );
      PartDefinition right_arm_r10 = right_arm.addOrReplaceChild(
         "right_arm_r10",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 4.0F, -2.0F, -0.0506F, -0.9633F, 0.4667F)
      );
      PartDefinition right_arm_r11 = right_arm.addOrReplaceChild(
         "right_arm_r11",
         CubeListBuilder.create().texOffs(60, 7).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.0F, 5.0F, 3.0F, 0.1483F, 0.4111F, 0.3711F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.1F, 3.0F, -2.0F, -0.7496F, 0.8451F, -0.8336F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.1F, 2.0F, -2.0F, -0.3038F, 0.7151F, -0.4853F)
      );
      PartDefinition left_leg_r3 = left_leg.addOrReplaceChild(
         "left_leg_r3",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(2.875F, 7.6734F, -2.4457F, -0.75F, 0.6847F, -0.7145F)
      );
      PartDefinition left_leg_r4 = left_leg.addOrReplaceChild(
         "left_leg_r4",
         CubeListBuilder.create().texOffs(59, 65).mirror().addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.8937F, 7.7319F, -2.0982F, -0.6944F, 0.8331F, -0.5652F)
      );
      PartDefinition left_leg_r5 = left_leg.addOrReplaceChild(
         "left_leg_r5",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(1.1F, 2.0F, -2.0F, -0.9729F, 0.9513F, -1.0088F)
      );
      PartDefinition left_leg_r6 = left_leg.addOrReplaceChild(
         "left_leg_r6",
         CubeListBuilder.create().texOffs(59, 65).mirror().addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(0.8937F, 6.7319F, -2.0982F, -0.8607F, 0.9745F, -0.6964F)
      );
      PartDefinition left_leg_r7 = left_leg.addOrReplaceChild(
         "left_leg_r7",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.875F, 6.6734F, -2.4457F, -0.9159F, 0.8102F, -0.8276F)
      );
      PartDefinition left_leg_r8 = left_leg.addOrReplaceChild(
         "left_leg_r8",
         CubeListBuilder.create().texOffs(24, 0).mirror().addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.1F, 1.0F, -2.0F, -0.4292F, 0.8659F, -0.5747F)
      );
      PartDefinition left_leg_r9 = left_leg.addOrReplaceChild(
         "left_leg_r9",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .mirror()
            .addBox(1.4797F, -4.4879F, -1.8652F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0011F, 4.8795F, 2.5907F, 0.503F, -0.8813F, -0.5867F)
      );
      PartDefinition left_leg_r10 = left_leg.addOrReplaceChild(
         "left_leg_r10",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(0.6376F, -2.4991F, -1.4972F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0011F, 4.8795F, 2.5907F, 0.3407F, -0.7561F, -0.7721F)
      );
      PartDefinition left_leg_r11 = left_leg.addOrReplaceChild(
         "left_leg_r11",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .mirror()
            .addBox(0.6614F, -3.7994F, -2.8138F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(2.0011F, 4.8795F, 1.5907F, 0.3408F, -0.6056F, -0.6405F)
      );
      PartDefinition left_leg_r12 = left_leg.addOrReplaceChild(
         "left_leg_r12",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.1F, 7.0F, 1.0F, -0.0096F, -0.0177F, -0.491F)
      );
      PartDefinition left_leg_r13 = left_leg.addOrReplaceChild(
         "left_leg_r13",
         CubeListBuilder.create()
            .texOffs(59, 65)
            .mirror()
            .addBox(-1.3187F, -0.0607F, -1.255F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0011F, 4.8795F, 2.5907F, 0.1191F, -0.7585F, -0.5193F)
      );
      PartDefinition left_leg_r14 = left_leg.addOrReplaceChild(
         "left_leg_r14",
         CubeListBuilder.create().texOffs(0, 55).mirror().addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(3.1F, 3.0F, 1.0F, 0.0776F, -0.0177F, -0.491F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.1F, 3.0F, 1.0F, 0.0776F, 0.0177F, 0.491F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create().texOffs(24, 0).addBox(-1.4797F, -4.4879F, -1.8652F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0011F, 4.8795F, 2.5907F, 0.503F, 0.8813F, 0.5867F)
      );
      PartDefinition right_leg_r3 = right_leg.addOrReplaceChild(
         "right_leg_r3",
         CubeListBuilder.create().texOffs(59, 65).addBox(-0.6376F, -2.4991F, -1.4972F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0011F, 4.8795F, 2.5907F, 0.3407F, 0.7561F, 0.7721F)
      );
      PartDefinition right_leg_r4 = right_leg.addOrReplaceChild(
         "right_leg_r4",
         CubeListBuilder.create().texOffs(59, 65).addBox(1.3187F, -0.0607F, -1.255F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0011F, 4.8795F, 2.5907F, 0.1191F, 0.7585F, 0.5193F)
      );
      PartDefinition right_leg_r5 = right_leg.addOrReplaceChild(
         "right_leg_r5",
         CubeListBuilder.create().texOffs(24, 0).addBox(-0.6614F, -3.7994F, -2.8138F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0011F, 4.8795F, 1.5907F, 0.3408F, 0.6056F, 0.6405F)
      );
      PartDefinition right_leg_r6 = right_leg.addOrReplaceChild(
         "right_leg_r6",
         CubeListBuilder.create().texOffs(59, 65).addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-0.8937F, 6.7319F, -2.0982F, -0.8607F, -0.9745F, 0.6964F)
      );
      PartDefinition right_leg_r7 = right_leg.addOrReplaceChild(
         "right_leg_r7",
         CubeListBuilder.create().texOffs(59, 65).addBox(-1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.875F, 6.6734F, -2.4457F, -0.9159F, -0.8102F, 0.8276F)
      );
      PartDefinition right_leg_r8 = right_leg.addOrReplaceChild(
         "right_leg_r8",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.1F, 1.0F, -2.0F, -0.4292F, -0.8659F, 0.5747F)
      );
      PartDefinition right_leg_r9 = right_leg.addOrReplaceChild(
         "right_leg_r9",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.1F, 2.0F, -2.0F, -0.9729F, -0.9513F, 1.0088F)
      );
      PartDefinition right_leg_r10 = right_leg.addOrReplaceChild(
         "right_leg_r10",
         CubeListBuilder.create().texOffs(59, 65).addBox(0.0F, -2.5F, -2.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.8937F, 7.7319F, -2.0982F, -0.6944F, -0.8331F, 0.5652F)
      );
      PartDefinition right_leg_r11 = right_leg.addOrReplaceChild(
         "right_leg_r11",
         CubeListBuilder.create().texOffs(59, 65).addBox(-1.5213F, -4.9861F, -2.764F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.875F, 7.6734F, -2.4457F, -0.75F, -0.6847F, 0.7145F)
      );
      PartDefinition right_leg_r12 = right_leg.addOrReplaceChild(
         "right_leg_r12",
         CubeListBuilder.create().texOffs(0, 55).addBox(0.0F, -3.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.1F, 7.0F, 1.0F, -0.0096F, 0.0177F, 0.491F)
      );
      PartDefinition right_leg_r13 = right_leg.addOrReplaceChild(
         "right_leg_r13",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.1F, 3.0F, -2.0F, -0.7496F, -0.8451F, 0.8336F)
      );
      PartDefinition right_leg_r14 = right_leg.addOrReplaceChild(
         "right_leg_r14",
         CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -2.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-3.1F, 2.0F, -2.0F, -0.3038F, -0.7151F, 0.4853F)
      );
      return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
      this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
