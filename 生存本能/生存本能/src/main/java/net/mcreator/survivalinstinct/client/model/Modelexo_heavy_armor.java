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

public class Modelexo_heavy_armor<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelexo_heavy_armor"), "main");
   public final ModelPart head;
   public final ModelPart body;
   public final ModelPart left_arm;
   public final ModelPart right_arm;
   public final ModelPart left_leg;
   public final ModelPart right_leg;
   public final ModelPart left_shoe;
   public final ModelPart right_shoe;

   public Modelexo_heavy_armor(ModelPart root) {
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
            .texOffs(0, 107)
            .addBox(-4.0F, -8.0F, -1.0F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.58F))
            .texOffs(4, 4)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create()
            .texOffs(92, 31)
            .mirror()
            .addBox(-0.9163F, -2.2608F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(5.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, -0.5672F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create()
            .texOffs(94, 41)
            .mirror()
            .addBox(-0.4163F, -2.2608F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(5.4163F, -3.7392F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(71, 95).mirror().addBox(0.0F, -2.0F, -2.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false),
         PartPose.offsetAndRotation(4.0F, -3.0F, -2.5F, 0.2564F, 0.1714F, 0.2207F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(93, 81).mirror().addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(2.4485F, -0.4461F, -5.7807F, 0.6608F, -0.9278F, -0.2529F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(0, 32).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.4485F, -0.4461F, -5.7807F, 0.3037F, 1.0517F, -0.1717F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(93, 81).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.4485F, -0.4461F, -5.7807F, 0.6608F, 0.9278F, 0.2529F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(36, 42).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.4485F, -0.4461F, -5.7807F, 0.3037F, -1.0517F, 0.1717F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create().texOffs(92, 31).addBox(-2.0837F, -2.2608F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-5.4163F, -2.7392F, 0.0F, 0.0F, 0.0F, 0.5672F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(36, 42).addBox(-0.2447F, -1.9161F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.2553F, -4.5839F, -0.5F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(94, 41).addBox(-0.5837F, -2.2608F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-5.4163F, -3.7392F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(71, 95).addBox(-1.0F, -2.0F, -2.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-4.0F, -3.0F, -2.5F, 0.2564F, -0.1714F, -0.2207F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create().texOffs(50, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0649F, -0.3193F, -7.3675F, 0.2182F, 0.0F, 0.0F)
      );
      PartDefinition head_r13 = head.addOrReplaceChild(
         "head_r13",
         CubeListBuilder.create().texOffs(42, 42).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(0.0156F, -5.8108F, -4.6494F, 0.0862F, 0.7732F, 0.0594F)
      );
      PartDefinition head_r14 = head.addOrReplaceChild(
         "head_r14",
         CubeListBuilder.create().texOffs(18, 42).addBox(-2.0F, -4.0F, -4.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.1717F, 0.7666F, 0.1194F)
      );
      PartDefinition head_r15 = head.addOrReplaceChild(
         "head_r15",
         CubeListBuilder.create().texOffs(13, 63).addBox(0.3916F, -5.9743F, -0.5F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-6.0F, -6.0F, 0.5F, 0.0161F, -0.5664F, -0.0511F)
      );
      PartDefinition head_r16 = head.addOrReplaceChild(
         "head_r16",
         CubeListBuilder.create().texOffs(48, 22).addBox(-5.0F, -3.5F, -1.0F, 10.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition head_r17 = head.addOrReplaceChild(
         "head_r17",
         CubeListBuilder.create().texOffs(25, 74).addBox(-0.5F, -4.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(2.5F, -4.5F, -2.5F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition head_r18 = head.addOrReplaceChild(
         "head_r18",
         CubeListBuilder.create().texOffs(59, 74).addBox(-0.5F, -4.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-2.5F, -4.5F, -2.5F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition body = partdefinition.addOrReplaceChild(
         "body",
         CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r1 = body.addOrReplaceChild(
         "body_r1",
         CubeListBuilder.create()
            .texOffs(50, 0)
            .mirror()
            .addBox(-3.0F, -2.0F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(59, 10)
            .mirror()
            .addBox(-4.0F, -4.5F, -1.45F, 8.0F, 7.0F, 2.0F, new CubeDeformation(-0.15F))
            .mirror(false)
            .texOffs(93, 68)
            .addBox(-3.0F, -3.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(69, 35)
            .addBox(-3.0F, -0.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, -2.55F, 0.0F, 3.1416F, 0.0F)
      );
      PartDefinition body_r2 = body.addOrReplaceChild(
         "body_r2",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(43, 42)
            .mirror()
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 3.9253F, -3.2885F, 2.9671F, 0.0F, -3.1416F)
      );
      PartDefinition body_r3 = body.addOrReplaceChild(
         "body_r3",
         CubeListBuilder.create()
            .texOffs(93, 92)
            .mirror()
            .addBox(-2.5F, -2.6019F, -0.5128F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 11.0F, -2.9F, -2.8798F, 0.0F, 3.1416F)
      );
      PartDefinition body_r4 = body.addOrReplaceChild(
         "body_r4",
         CubeListBuilder.create()
            .texOffs(93, 68)
            .mirror()
            .addBox(-3.0F, -3.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(69, 35)
            .mirror()
            .addBox(-3.0F, -0.5F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(59, 19)
            .mirror()
            .addBox(-3.0F, 1.0F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(50, 0)
            .addBox(-3.0F, -2.0F, -0.45F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 6.5F, -2.55F, 0.0F, -3.1416F, 0.0F)
      );
      PartDefinition body_r5 = body.addOrReplaceChild(
         "body_r5",
         CubeListBuilder.create()
            .texOffs(24, 22)
            .mirror()
            .addBox(-2.0F, 3.5F, -0.4F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(28, 55)
            .mirror()
            .addBox(-2.0F, 1.5F, -0.4F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(28, 57)
            .mirror()
            .addBox(-2.0F, -0.5F, -0.4F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 10.0F, -2.6F, -2.8798F, 0.0F, -3.1416F)
      );
      PartDefinition body_r6 = body.addOrReplaceChild(
         "body_r6",
         CubeListBuilder.create()
            .texOffs(32, 59)
            .mirror()
            .addBox(-6.0F, -2.0F, -2.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 64)
            .addBox(-6.0F, -2.0F, 0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 64)
            .addBox(2.0F, -3.0F, -3.5F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.2F))
            .texOffs(25, 9)
            .addBox(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, 4.4347F, 0.2182F, 0.0F, 0.0F)
      );
      PartDefinition body_r7 = body.addOrReplaceChild(
         "body_r7",
         CubeListBuilder.create()
            .texOffs(10, 96)
            .mirror()
            .addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(12, 79)
            .mirror()
            .addBox(-1.0F, -1.5F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(-4.0F, 5.5F, 4.4347F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition body_r8 = body.addOrReplaceChild(
         "body_r8",
         CubeListBuilder.create()
            .texOffs(0, 64)
            .mirror()
            .addBox(-3.0F, -3.0F, -3.5F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(0, 64)
            .mirror()
            .addBox(5.0F, -2.0F, 0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(32, 59)
            .addBox(5.0F, -2.0F, -2.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 0.0F, 4.4347F, 0.2182F, 0.0F, 0.0F)
      );
      PartDefinition body_r9 = body.addOrReplaceChild(
         "body_r9",
         CubeListBuilder.create().texOffs(16, 51).mirror().addBox(0.0F, -3.0F, -0.5F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(5.3F, -3.6887F, 2.5926F, 0.2317F, -0.3405F, -0.0786F)
      );
      PartDefinition body_r10 = body.addOrReplaceChild(
         "body_r10",
         CubeListBuilder.create().texOffs(12, 48).mirror().addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)).mirror(false),
         PartPose.offsetAndRotation(2.5F, 10.3549F, 3.179F, -0.2986F, -0.0651F, -0.2084F)
      );
      PartDefinition body_r11 = body.addOrReplaceChild(
         "body_r11",
         CubeListBuilder.create().texOffs(81, 27).addBox(-4.0F, -1.8698F, -0.23F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 2.5632F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r12 = body.addOrReplaceChild(
         "body_r12",
         CubeListBuilder.create().texOffs(12, 48).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(-2.5F, 10.3549F, 3.179F, -0.2986F, 0.0651F, 0.2084F)
      );
      PartDefinition body_r13 = body.addOrReplaceChild(
         "body_r13",
         CubeListBuilder.create().texOffs(72, 19).addBox(-4.0F, -1.7393F, -1.2215F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 9.1338F, 3.5632F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition body_r14 = body.addOrReplaceChild(
         "body_r14",
         CubeListBuilder.create()
            .texOffs(86, 19)
            .mirror()
            .addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.15F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.4537F, 7.0208F, 0.0F, 0.0F, 0.0F, -0.3927F)
      );
      PartDefinition body_r15 = body.addOrReplaceChild(
         "body_r15",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(43, 42)
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(50, 52)
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(0.0F, 3.9253F, -3.2885F, 2.9671F, 0.0F, 3.1416F)
      );
      PartDefinition body_r16 = body.addOrReplaceChild(
         "body_r16",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .mirror(false)
            .texOffs(44, 22)
            .mirror()
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false)
            .texOffs(66, 74)
            .mirror()
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offsetAndRotation(-3.0F, 3.9253F, -3.2885F, 3.054F, -0.1308F, -3.1359F)
      );
      PartDefinition body_r17 = body.addOrReplaceChild(
         "body_r17",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .addBox(-1.0F, -1.5075F, -1.3285F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
            .texOffs(44, 22)
            .addBox(-0.5F, -0.5075F, -0.1285F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.3F))
            .texOffs(66, 74)
            .addBox(-1.0F, -1.5075F, -1.0285F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(3.0F, 3.9253F, -3.2885F, 3.054F, 0.1308F, 3.1359F)
      );
      PartDefinition body_r18 = body.addOrReplaceChild(
         "body_r18",
         CubeListBuilder.create().texOffs(16, 51).addBox(0.0F, -3.0F, -0.5F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-5.3F, -3.6887F, 2.5926F, 0.2317F, 0.3405F, 0.0786F)
      );
      PartDefinition body_r19 = body.addOrReplaceChild(
         "body_r19",
         CubeListBuilder.create()
            .texOffs(24, 0)
            .addBox(-5.0F, -3.5015F, -5.4827F, 10.0F, 2.0F, 6.0F, new CubeDeformation(-0.1F))
            .texOffs(59, 69)
            .addBox(2.0F, -3.5015F, -0.4827F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(0.0F, 5.5015F, 7.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r20 = body.addOrReplaceChild(
         "body_r20",
         CubeListBuilder.create()
            .texOffs(12, 79)
            .addBox(-1.0F, -1.5F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.3F))
            .texOffs(10, 96)
            .addBox(-1.0F, -1.5F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(4.0F, 5.5F, 4.4347F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition body_r21 = body.addOrReplaceChild(
         "body_r21",
         CubeListBuilder.create().texOffs(27, 27).addBox(-4.0F, -7.5015F, -4.4827F, 8.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, 5.5015F, 6.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r22 = body.addOrReplaceChild(
         "body_r22",
         CubeListBuilder.create()
            .texOffs(59, 69)
            .mirror()
            .addBox(-3.0F, -3.5015F, -0.4827F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 5.5015F, 7.4173F, 0.0F, 0.0F, 0.0F)
      );
      PartDefinition body_r23 = body.addOrReplaceChild(
         "body_r23",
         CubeListBuilder.create()
            .texOffs(31, 87)
            .mirror()
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F))
            .mirror(false)
            .texOffs(97, 85)
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(98, 19)
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .texOffs(91, 7)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(3.0F, 0.95F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition body_r24 = body.addOrReplaceChild(
         "body_r24",
         CubeListBuilder.create()
            .texOffs(65, 86)
            .mirror()
            .addBox(-4.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, 5.0208F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition body_r25 = body.addOrReplaceChild(
         "body_r25",
         CubeListBuilder.create()
            .texOffs(98, 19)
            .mirror()
            .addBox(-1.0F, -0.95F, 1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(97, 85)
            .mirror()
            .addBox(-1.0F, -0.95F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(91, 7)
            .mirror()
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(31, 87)
            .addBox(-1.0F, -1.55F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(-0.15F)),
         PartPose.offsetAndRotation(-3.0F, 0.95F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition body_r26 = body.addOrReplaceChild(
         "body_r26",
         CubeListBuilder.create().texOffs(86, 19).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.15F)),
         PartPose.offsetAndRotation(3.4537F, 7.0208F, 0.0F, 0.0F, 0.0F, 0.3927F)
      );
      PartDefinition body_r27 = body.addOrReplaceChild(
         "body_r27",
         CubeListBuilder.create().texOffs(65, 86).addBox(1.9504F, -1.1506F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, 5.0208F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition left_arm = partdefinition.addOrReplaceChild(
         "left_arm",
         CubeListBuilder.create().texOffs(38, 52).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(5.0F, 2.0F, 0.0F)
      );
      PartDefinition left_arm_r1 = left_arm.addOrReplaceChild(
         "left_arm_r1",
         CubeListBuilder.create()
            .texOffs(46, 71)
            .mirror()
            .addBox(-0.9265F, -1.5F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(1.9265F, 6.3752F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition left_arm_r2 = left_arm.addOrReplaceChild(
         "left_arm_r2",
         CubeListBuilder.create()
            .texOffs(52, 2)
            .mirror()
            .addBox(-2.0F, 4.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(70, 48)
            .mirror()
            .addBox(0.0F, -1.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 87)
            .addBox(-1.0F, 0.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition left_arm_r3 = left_arm.addOrReplaceChild(
         "left_arm_r3",
         CubeListBuilder.create().texOffs(24, 92).addBox(0.0735F, -0.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(1.9265F, 5.3752F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition right_arm = partdefinition.addOrReplaceChild(
         "right_arm",
         CubeListBuilder.create().texOffs(16, 55).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition right_arm_r1 = right_arm.addOrReplaceChild(
         "right_arm_r1",
         CubeListBuilder.create().texOffs(46, 71).addBox(-2.0735F, -1.5F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(-1.9265F, 6.3752F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm_r2 = right_arm.addOrReplaceChild(
         "right_arm_r2",
         CubeListBuilder.create().texOffs(34, 95).addBox(-1.0735F, -0.5F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-1.9265F, 5.3752F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition right_arm_r3 = right_arm.addOrReplaceChild(
         "right_arm_r3",
         CubeListBuilder.create()
            .texOffs(52, 2)
            .addBox(-3.0F, 4.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(70, 48)
            .addBox(-3.0F, -1.0F, -3.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(47, 90)
            .addBox(-2.0F, 0.0F, -2.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.6F)),
         PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition left_leg = partdefinition.addOrReplaceChild(
         "left_leg",
         CubeListBuilder.create()
            .texOffs(54, 52)
            .mirror()
            .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(85, 76)
            .mirror()
            .addBox(-1.9F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.25F))
            .mirror(false),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition left_leg_r1 = left_leg.addOrReplaceChild(
         "left_leg_r1",
         CubeListBuilder.create()
            .texOffs(28, 88)
            .mirror()
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false)
            .texOffs(97, 70)
            .mirror()
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(0.1F, 4.5F, -2.1F, 0.0873F, -0.0873F, 0.0F)
      );
      PartDefinition left_leg_r2 = left_leg.addOrReplaceChild(
         "left_leg_r2",
         CubeListBuilder.create()
            .texOffs(72, 58)
            .mirror()
            .addBox(-0.4F, 2.5F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(10, 71)
            .mirror()
            .addBox(-1.4F, -0.5F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(14, 84)
            .mirror()
            .addBox(-1.4F, -1.5F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.3F))
            .mirror(false),
         PartPose.offsetAndRotation(1.1F, -0.5F, 0.0F, 0.0F, 0.0F, -0.2182F)
      );
      PartDefinition right_leg = partdefinition.addOrReplaceChild(
         "right_leg",
         CubeListBuilder.create()
            .texOffs(54, 52)
            .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(85, 76)
            .addBox(-2.1F, 4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition right_leg_r1 = right_leg.addOrReplaceChild(
         "right_leg_r1",
         CubeListBuilder.create()
            .texOffs(28, 88)
            .addBox(-1.5F, -1.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.4F))
            .texOffs(97, 70)
            .addBox(-1.5F, -1.5F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.2F)),
         PartPose.offsetAndRotation(-0.1F, 4.5F, -2.1F, 0.0873F, 0.0873F, 0.0F)
      );
      PartDefinition right_leg_r2 = right_leg.addOrReplaceChild(
         "right_leg_r2",
         CubeListBuilder.create()
            .texOffs(72, 58)
            .addBox(-2.6F, 2.5F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(10, 71)
            .addBox(-2.6F, -0.5F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(14, 84)
            .addBox(-1.6F, -1.5F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.3F)),
         PartPose.offsetAndRotation(-1.1F, -0.5F, 0.0F, 0.0F, 0.0F, 0.2182F)
      );
      PartDefinition left_shoe = partdefinition.addOrReplaceChild(
         "left_shoe",
         CubeListBuilder.create()
            .texOffs(81, 66)
            .mirror()
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .mirror(false)
            .texOffs(64, 0)
            .mirror()
            .addBox(-2.0F, 11.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
            .mirror(false),
         PartPose.offset(2.0F, 12.0F, 0.0F)
      );
      PartDefinition right_shoe = partdefinition.addOrReplaceChild(
         "right_shoe",
         CubeListBuilder.create()
            .texOffs(81, 66)
            .addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.49F))
            .texOffs(64, 0)
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
