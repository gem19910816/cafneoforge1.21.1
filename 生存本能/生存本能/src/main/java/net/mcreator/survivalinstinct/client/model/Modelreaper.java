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

public class Modelreaper<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelreaper"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart right_shoe;
   public final ModelPart left_shoe;

   public Modelreaper(ModelPart root) {
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
            .texOffs(0, 82)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(7, 37)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(74, 98)
            .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
            .texOffs(66, 83)
            .addBox(-4.0F, -8.0F, 4.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(96, 80)
            .addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(46, 87)
            .mirror()
            .addBox(-0.4163F, 0.7392F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .mirror(false)
            .texOffs(80, 118)
            .addBox(-0.4163F, 0.7392F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(48, 76)
            .addBox(-0.4163F, -1.2608F, -6.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.4163F, -6.7392F, 2.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(67, 9).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(3.0F, 0.0591F, -4.3377F, -0.9092F, -0.6922F, -0.2395F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(84, 5).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false),
         PartPose.offsetAndRotation(3.1378F, -0.1838F, -4.9465F, 2.3693F, -0.1425F, -0.9337F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create()
            .texOffs(38, 67)
            .mirror()
            .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(38, 67)
            .mirror()
            .addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.6349F, 0.5364F, -3.9451F, -1.8662F, 0.8828F, -1.0199F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(67, 9).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.6F)).mirror(false),
         PartPose.offsetAndRotation(-3.0F, 0.0591F, -3.3377F, -0.9092F, 0.6922F, 0.2395F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create()
            .texOffs(54, 97)
            .mirror()
            .addBox(-0.3742F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(-4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create()
            .texOffs(48, 76)
            .mirror()
            .addBox(-0.5837F, -1.2608F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(46, 87)
            .addBox(-0.5837F, 0.7392F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
            .texOffs(80, 118)
            .mirror()
            .addBox(-0.5837F, 0.7392F, 1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.01F))
            .mirror(false),
         PartPose.offsetAndRotation(-4.4163F, -6.7392F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(86, 77).addBox(-0.2982F, -1.1178F, -2.9116F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-5.0F, -3.0F, 1.0F, -0.0451F, 0.0434F, 0.0219F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(30, 15).addBox(-5.0F, -0.5F, -4.75F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -5.0F, -0.25F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create()
            .texOffs(86, 77)
            .mirror()
            .addBox(-0.7018F, -1.1178F, -2.9116F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false),
         PartPose.offsetAndRotation(5.0F, -3.0F, 1.0F, -0.0451F, -0.0434F, -0.0219F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(82, 52).addBox(-4.0F, -0.5872F, -1.4962F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, -3.5F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create()
            .texOffs(44, 59)
            .mirror()
            .addBox(-2.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, -0.7383F, 0.0797F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create().texOffs(28, 112).addBox(-4.0F, -0.5F, -0.8172F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.18F)),
         PartPose.offsetAndRotation(0.0F, -8.2172F, 4.2172F, 0.7418F, 0.0F, 0.0F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(54, 97).addBox(-0.6258F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(4.1258F, -8.0425F, 0.0F, 0.0F, 0.0F, 0.8727F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create()
            .texOffs(29, 122)
            .mirror()
            .addBox(-5.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, 0.0076F, 0.0869F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create().texOffs(79, 72).addBox(-4.0F, -0.5F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -4.5F, -0.5672F, 0.0F, 0.0F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create()
            .texOffs(56, 91)
            .mirror()
            .addBox(-4.3006F, -0.5574F, 0.7878F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -4.5F, -3.5F, -0.0492F, -0.4795F, 0.0227F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create().texOffs(93, 58).mirror().addBox(-1.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(4.5F, -5.0F, 3.5F, 0.0F, 0.7418F, 0.0F)
      );
      PartDefinition head_r19 = head.addOrReplaceChild(
         "head_r19",
         CubeListBuilder.create()
            .texOffs(49, 96)
            .mirror()
            .addBox(-1.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F))
            .mirror(false),
         PartPose.offsetAndRotation(4.1972F, -6.5038F, -4.87F, 0.0F, 0.6545F, 0.0F)
      );
      PartDefinition head_r20 = head.addOrReplaceChild(
         "head_r20",
         CubeListBuilder.create().texOffs(78, 50).addBox(-4.0F, -0.494F, 0.75F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition head_r21 = head.addOrReplaceChild(
         "head_r21",
         CubeListBuilder.create().texOffs(44, 59).addBox(1.9489F, -0.494F, 3.1245F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.1181F, 0.7383F, -0.0797F)
      );
      PartDefinition head_r22 = head.addOrReplaceChild(
         "head_r22",
         CubeListBuilder.create().texOffs(93, 58).addBox(0.2F, -3.0F, -0.1F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, 3.5F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition head_r23 = head.addOrReplaceChild(
         "head_r23",
         CubeListBuilder.create().texOffs(56, 91).addBox(3.3006F, -0.5574F, 0.7878F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.0F, -4.5F, -3.5F, -0.0492F, 0.4795F, -0.0227F)
      );
      PartDefinition head_r24 = head.addOrReplaceChild(
         "head_r24",
         CubeListBuilder.create().texOffs(49, 96).addBox(0.1972F, -1.5F, -0.13F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.13F)),
         PartPose.offsetAndRotation(-4.1972F, -6.5038F, -4.87F, 0.0F, -0.6545F, 0.0F)
      );
      PartDefinition head_r25 = head.addOrReplaceChild(
         "head_r25",
         CubeListBuilder.create().texOffs(29, 122).addBox(4.0907F, -0.1044F, -2.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, -2.506F, 3.25F, -0.2615F, -0.0076F, -0.0869F)
      );
      PartDefinition head_r26 = head.addOrReplaceChild(
         "head_r26",
         CubeListBuilder.create().texOffs(67, 9).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.6F)).mirror(false),
         PartPose.offsetAndRotation(-3.0F, 0.0591F, -4.3377F, -0.9092F, 0.6922F, 0.2395F)
      );
      PartDefinition head_r27 = head.addOrReplaceChild(
         "head_r27",
         CubeListBuilder.create()
            .texOffs(68, 33)
            .mirror()
            .addBox(-1.5F, 0.2975F, -1.4575F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(68, 33)
            .mirror()
            .addBox(-1.5F, 0.0975F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F))
            .mirror(false)
            .texOffs(85, 9)
            .mirror()
            .addBox(-1.5F, -1.25F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F))
            .mirror(false)
            .texOffs(47, 70)
            .mirror()
            .addBox(-1.5F, -2.25F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 0.2025F, -5.5425F, -1.1111F, -0.4176F, 0.6863F)
      );
      PartDefinition head_r28 = head.addOrReplaceChild(
         "head_r28",
         CubeListBuilder.create().texOffs(84, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-3.1378F, -0.1838F, -4.9465F, 2.3693F, 0.1425F, 0.9337F)
      );
      PartDefinition head_r29 = head.addOrReplaceChild(
         "head_r29",
         CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, 0.5F, 3.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition head_r30 = head.addOrReplaceChild(
         "head_r30",
         CubeListBuilder.create().texOffs(40, 21).addBox(-5.0F, -2.5F, -1.0F, 10.0F, 5.0F, 2.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -1.5F, -1.0F, -0.3927F, 0.0F, 0.0F)
      );
      PartDefinition head_r31 = head.addOrReplaceChild(
         "head_r31",
         CubeListBuilder.create()
            .texOffs(38, 67)
            .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(38, 67)
            .addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(3.6349F, 0.5364F, -3.9451F, -1.8662F, -0.8828F, 1.0199F)
      );
      PartDefinition head_r32 = head.addOrReplaceChild(
         "head_r32",
         CubeListBuilder.create().texOffs(67, 9).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(3.0F, 0.0591F, -3.3377F, -0.9092F, -0.6922F, -0.2395F)
      );
      PartDefinition head_r33 = head.addOrReplaceChild(
         "head_r33",
         CubeListBuilder.create().texOffs(68, 38).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, 0.0591F, -4.3377F, -0.9599F, 0.0F, 0.0F)
      );
      PartDefinition head_r34 = head.addOrReplaceChild(
         "head_r34",
         CubeListBuilder.create()
            .texOffs(68, 33)
            .addBox(-1.5F, 0.2975F, -1.4575F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(68, 33)
            .addBox(-1.5F, 0.0975F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.4F))
            .texOffs(85, 9)
            .addBox(-1.5F, -1.25F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(47, 70)
            .addBox(-1.5F, -2.25F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 0.2025F, -5.5425F, -1.1111F, 0.4176F, -0.6863F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create()
            .texOffs(83, 16)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(40, 28)
            .addBox(-3.0F, 3.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(67, 14)
            .addBox(-3.0F, 5.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 0)
            .addBox(-3.0F, 7.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(70, 2)
            .addBox(-3.0F, 9.0F, 1.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(55, 10)
            .addBox(-5.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(54, 52)
            .addBox(-5.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .texOffs(55, 10)
            .mirror()
            .addBox(2.0F, 3.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(72, 24)
            .addBox(-3.0F, 9.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(72, 26)
            .addBox(-3.0F, 7.0F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(72, 48)
            .addBox(-3.0F, 4.0F, -2.8F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(8, 66)
            .addBox(-3.0F, 4.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(73, 16)
            .addBox(-4.0F, 3.0F, -2.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 15)
            .addBox(-4.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(0, 15)
            .mirror()
            .addBox(1.0F, 0.0F, 1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(67, 73)
            .addBox(-4.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .texOffs(67, 73)
            .mirror()
            .addBox(1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(0, 46)
            .addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 57)
            .addBox(1.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .texOffs(20, 46)
            .addBox(-4.0F, 2.0F, -3.0F, 8.0F, 9.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(0, 57)
            .mirror()
            .addBox(-4.0F, -1.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F))
            .mirror(false)
            .texOffs(8, 66)
            .mirror()
            .addBox(1.0F, 4.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(73, 16)
            .mirror()
            .addBox(1.0F, 3.0F, -2.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(54, 52)
            .mirror()
            .addBox(2.0F, 6.0F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.7F))
            .mirror(false)
            .texOffs(86, 35)
            .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(12, 57)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 66)
            .mirror()
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .mirror(false)
            .texOffs(48, 58)
            .mirror()
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(62, 19)
            .mirror()
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(55, 71)
            .mirror()
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(-5.0F, 9.0F, -0.05F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(60, 46)
            .mirror()
            .addBox(-0.5F, -0.75F, -0.9F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(30, 15)
            .mirror()
            .addBox(-0.5F, -0.75F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(-2.5302F, 9.7668F, -2.9139F, 0.0873F, 0.0435F, 0.0038F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(40, 10)
            .mirror()
            .addBox(-0.5F, -0.75F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(0, 66)
            .mirror()
            .addBox(-0.5F, -0.75F, -0.9F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(-4.5302F, 9.7668F, -2.9139F, 0.0928F, 0.3477F, 0.0317F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(58, 76)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(76, 28)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(56, 4)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(12, 57)
            .mirror()
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(2.0F, 7.25F, -3.7F, 0.0876F, 0.0869F, 0.0076F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(20, 57)
            .mirror()
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(52, 76)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(62, 61)
            .mirror()
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(76, 61)
            .mirror()
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(4.0F, 7.25F, -3.7F, 0.0226F, -0.5286F, 0.0487F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(12, 57)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 66)
            .addBox(-1.0F, -2.0F, -1.95F, 2.0F, 4.0F, 4.0F, new CubeDeformation(-0.1F))
            .texOffs(48, 58)
            .addBox(-1.0F, -2.0F, -2.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(62, 19)
            .addBox(-1.0F, -2.0F, 1.45F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(55, 71)
            .addBox(-1.0F, 0.5F, -1.95F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(5.0F, 9.0F, -0.05F, 0.0F, 0.0F, 0.1309F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create().texOffs(33, 84).addBox(-2.0F, -3.5F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 3.5F, 4.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create().texOffs(0, 21).addBox(-2.5F, 0.5F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(1.0F, 6.5F, 4.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create()
            .texOffs(0, 21)
            .addBox(-2.5F, 0.5F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(30, 21)
            .addBox(-2.5F, -1.5F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(1.0F, 4.5F, 4.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-1.85F, -0.5F, -1.15F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.4F))
            .texOffs(40, 46)
            .addBox(-0.15F, -4.5F, -0.35F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(3.15F, 2.4036F, 4.0585F, 0.0699F, 0.2129F, 0.0606F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(52, 61).addBox(-2.0F, -3.5F, -1.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 4.5F, 3.0F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create()
            .texOffs(12, 57)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(56, 4)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(76, 28)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(58, 76)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.0F, 7.25F, -3.7F, 0.0876F, -0.0869F, -0.0076F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create()
            .texOffs(76, 61)
            .addBox(-1.0F, -2.25F, -0.9F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(62, 61)
            .addBox(-1.0F, -1.25F, -0.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(52, 76)
            .addBox(-1.0F, -2.25F, -0.1F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(20, 57)
            .addBox(-0.5F, -0.25F, -0.7F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-4.0F, 7.25F, -3.7F, 0.0226F, 0.5286F, -0.0487F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create()
            .texOffs(30, 15)
            .addBox(-0.5F, -0.75F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(60, 46)
            .addBox(-0.5F, -0.75F, -0.9F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5302F, 9.7668F, -2.9139F, 0.0873F, -0.0435F, -0.0038F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create()
            .texOffs(40, 10)
            .addBox(-0.5F, -0.75F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 66)
            .addBox(-0.5F, -0.75F, -0.9F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.5302F, 9.7668F, -2.9139F, 0.0928F, -0.3477F, -0.0317F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create()
            .texOffs(12, 66)
            .addBox(-2.0F, -0.75F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
            .texOffs(66, 51)
            .mirror()
            .addBox(-4.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(35, 72)
            .mirror()
            .addBox(1.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(48, 37)
            .addBox(-2.0F, -1.75F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.3F))
            .texOffs(24, 30)
            .mirror()
            .addBox(1.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(24, 30)
            .addBox(-2.5F, -1.75F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
            .texOffs(66, 51)
            .addBox(1.0F, -1.75F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(-0.3F))
            .texOffs(35, 72)
            .addBox(-4.0F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.75F, 3.5F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create()
            .texOffs(103, 0)
            .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(102, 17)
            .addBox(-0.5F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create()
            .texOffs(100, 53)
            .mirror()
            .addBox(2.3F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(113, 53)
            .mirror()
            .addBox(2.3F, -2.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.5F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create()
            .texOffs(101, 65)
            .mirror()
            .addBox(-2.7F, -0.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create().texOffs(102, 17).addBox(-1.7F, -5.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(1.0F, 9.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create()
            .texOffs(103, 0)
            .mirror()
            .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(102, 17)
            .mirror()
            .addBox(-3.5F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F))
            .mirror(false),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create()
            .texOffs(102, 17)
            .mirror()
            .addBox(-2.3F, -5.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(-1.0F, 9.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(101, 65).addBox(-3.3F, -0.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create()
            .texOffs(113, 53)
            .addBox(-4.3F, -2.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.5F))
            .texOffs(100, 53)
            .addBox(-4.3F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(64, 19)
            .mirror()
            .addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(44, 42)
            .mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(64, 61)
            .mirror()
            .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(52, 10)
            .mirror()
            .addBox(-1.47F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 5.5F, -2.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create()
            .texOffs(26, 66)
            .addBox(-1.0F, -2.5F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
            .texOffs(62, 0)
            .addBox(-1.0F, -2.5F, -2.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 3.5F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(44, 42)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(64, 61)
            .addBox(-2.0F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F))
            .texOffs(64, 19)
            .addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-3.0F, 3.5F, 0.0F, 0.0F, 0.0F, -0.1309F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create().texOffs(52, 10).addBox(-1.53F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)),
         PartPose.offsetAndRotation(0.0F, 5.5F, -2.5F, 0.0873F, 0.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(60, 24)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F))
            .texOffs(82, 25)
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
            .texOffs(90, 19)
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offset(-2.0F, 12.0F, 0.0F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(90, 19)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(82, 25)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
            .mirror(false)
            .texOffs(60, 24)
            .mirror()
            .addBox(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.4F))
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
