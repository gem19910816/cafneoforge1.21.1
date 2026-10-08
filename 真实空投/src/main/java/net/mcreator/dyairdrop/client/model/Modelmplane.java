package net.mcreator.dyairdrop.client.model;

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

public class Modelmplane<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("dyairdrop", "modelmplane"), "main");
   public final ModelPart bb_main;

   public Modelmplane(ModelPart root) {
      this.bb_main = root.getChild("bb_main");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
      PartDefinition cube_r1 = bb_main.addOrReplaceChild(
         "cube_r1",
         CubeListBuilder.create()
            .texOffs(360, 112)
            .mirror()
            .addBox(-63.6976F, 52.0911F, -75.6495F, 20.0F, 20.0F, 36.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(365, 114)
            .mirror()
            .addBox(-103.6976F, 88.0911F, -75.6495F, 16.0F, 16.0F, 36.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(84, 0)
            .addBox(-63.6976F, 52.0911F, -39.6495F, 16.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -1.3688F, -0.8625F, 1.4165F)
      );
      PartDefinition cube_r2 = bb_main.addOrReplaceChild(
         "cube_r2",
         CubeListBuilder.create()
            .texOffs(364, 114)
            .addBox(87.6976F, 88.0911F, -75.6495F, 16.0F, 16.0F, 36.0F, new CubeDeformation(0.0F))
            .texOffs(84, 0)
            .mirror()
            .addBox(47.6976F, 52.0911F, -39.6495F, 16.0F, 20.0F, 20.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(360, 112)
            .addBox(43.6976F, 52.0911F, -75.6495F, 20.0F, 20.0F, 36.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 1.7728F, -0.8625F, -1.7251F)
      );
      PartDefinition cube_r3 = bb_main.addOrReplaceChild(
         "cube_r3",
         CubeListBuilder.create()
            .texOffs(360, 168)
            .mirror()
            .addBox(-42.1657F, -45.7294F, 128.6062F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.8091F, -1.4502F, -0.7617F)
      );
      PartDefinition cube_r4 = bb_main.addOrReplaceChild(
         "cube_r4",
         CubeListBuilder.create()
            .texOffs(292, 0)
            .mirror()
            .addBox(47.4098F, -45.7294F, 133.0955F, 24.0F, 4.0F, 28.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -2.5379F, -1.4168F, 2.5872F)
      );
      PartDefinition cube_r5 = bb_main.addOrReplaceChild(
         "cube_r5",
         CubeListBuilder.create().texOffs(292, 0).addBox(-71.4098F, -45.7294F, 133.0955F, 24.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.6037F, -1.4168F, -0.5544F)
      );
      PartDefinition cube_r6 = bb_main.addOrReplaceChild(
         "cube_r6",
         CubeListBuilder.create().texOffs(360, 168).addBox(10.1657F, -45.7294F, 128.6062F, 32.0F, 4.0F, 32.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -2.3325F, -1.4502F, 2.3799F)
      );
      PartDefinition cube_r7 = bb_main.addOrReplaceChild(
         "cube_r7",
         CubeListBuilder.create()
            .texOffs(180, 0)
            .addBox(-4.0F, -25.6852F, 147.7586F, 8.0F, 32.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .mirror()
            .addBox(-4.0F, -49.6852F, 127.7586F, 4.0F, 28.0F, 28.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(100, 80)
            .mirror()
            .addBox(0.0F, -69.6852F, 131.7586F, 4.0F, 20.0F, 24.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(100, 80)
            .addBox(-4.0F, -69.6852F, 131.7586F, 4.0F, 20.0F, 24.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(0.0F, -49.6852F, 127.7586F, 4.0F, 28.0F, 28.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.5708F, -0.0873F)
      );
      PartDefinition cube_r8 = bb_main.addOrReplaceChild(
         "cube_r8",
         CubeListBuilder.create()
            .texOffs(0, 80)
            .mirror()
            .addBox(-17.1997F, -20.4922F, 139.7798F, 8.0F, 28.0F, 16.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -0.0436F, -1.5272F, 0.0F)
      );
      PartDefinition cube_r9 = bb_main.addOrReplaceChild(
         "cube_r9",
         CubeListBuilder.create().texOffs(0, 80).addBox(9.1997F, -20.4922F, 139.7798F, 8.0F, 28.0F, 16.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 3.098F, -1.5272F, -3.1416F)
      );
      PartDefinition cube_r10 = bb_main.addOrReplaceChild(
         "cube_r10",
         CubeListBuilder.create()
            .texOffs(0, 80)
            .mirror()
            .addBox(-26.2921F, 38.0987F, 52.4785F, 24.0F, 8.0F, 52.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.4835F, 0.1309F)
      );
      PartDefinition cube_r11 = bb_main.addOrReplaceChild(
         "cube_r11",
         CubeListBuilder.create().texOffs(148, 300).addBox(-3.8535F, 1.5962F, 55.9641F, 40.0F, 8.0F, 44.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -1.5708F, 0.3927F, 1.6144F)
      );
      PartDefinition cube_r12 = bb_main.addOrReplaceChild(
         "cube_r12",
         CubeListBuilder.create()
            .texOffs(180, 48)
            .mirror()
            .addBox(6.0927F, 32.8901F, 95.9201F, 24.0F, 16.0F, 44.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 3.1416F, -1.4835F, -2.9671F)
      );
      PartDefinition cube_r13 = bb_main.addOrReplaceChild(
         "cube_r13",
         CubeListBuilder.create()
            .texOffs(180, 0)
            .mirror()
            .addBox(-34.8857F, 0.4615F, 99.9977F, 36.0F, 8.0F, 40.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 1.5708F, 0.3927F, -1.4835F)
      );
      PartDefinition cube_r14 = bb_main.addOrReplaceChild(
         "cube_r14",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .mirror()
            .addBox(0.0F, -13.5315F, 27.9553F, 8.0F, 12.0F, 68.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 0)
            .addBox(-8.0F, -13.5315F, 27.9553F, 8.0F, 12.0F, 68.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.5708F, 0.0349F)
      );
      PartDefinition cube_r15 = bb_main.addOrReplaceChild(
         "cube_r15",
         CubeListBuilder.create()
            .texOffs(360, 52)
            .mirror()
            .addBox(-29.2229F, 35.1242F, 8.3932F, 28.0F, 8.0F, 52.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.5272F, 0.0873F)
      );
      PartDefinition cube_r16 = bb_main.addOrReplaceChild(
         "cube_r16",
         CubeListBuilder.create().texOffs(360, 0).addBox(-3.0424F, 5.2602F, 11.8013F, 48.0F, 8.0F, 44.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -1.5708F, 0.3927F, 1.5708F)
      );
      PartDefinition cube_r17 = bb_main.addOrReplaceChild(
         "cube_r17",
         CubeListBuilder.create().texOffs(0, 80).addBox(2.2921F, 38.0987F, 52.4785F, 24.0F, 8.0F, 52.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -3.1416F, -1.4835F, -3.0107F)
      );
      PartDefinition cube_r18 = bb_main.addOrReplaceChild(
         "cube_r18",
         CubeListBuilder.create()
            .texOffs(148, 300)
            .mirror()
            .addBox(-36.1465F, 1.5962F, 55.9641F, 40.0F, 8.0F, 44.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 1.5708F, 0.3927F, -1.5272F)
      );
      PartDefinition cube_r19 = bb_main.addOrReplaceChild(
         "cube_r19",
         CubeListBuilder.create()
            .texOffs(168, 44)
            .mirror()
            .addBox(-12.0F, -15.293F, -140.1987F, 12.0F, 12.0F, 168.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(168, 44)
            .addBox(0.0F, -15.293F, -140.1987F, 12.0F, 12.0F, 168.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      PartDefinition cube_r20 = bb_main.addOrReplaceChild(
         "cube_r20",
         CubeListBuilder.create()
            .texOffs(0, 300)
            .mirror()
            .addBox(-36.0F, 36.3555F, -63.2197F, 36.0F, 8.0F, 76.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(0, 300)
            .addBox(0.0F, 36.3555F, -63.2197F, 36.0F, 8.0F, 76.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.5708F, 0.1309F)
      );
      PartDefinition cube_r21 = bb_main.addOrReplaceChild(
         "cube_r21",
         CubeListBuilder.create().texOffs(360, 52).addBox(1.2229F, 35.1242F, 8.3932F, 28.0F, 8.0F, 52.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 3.1416F, -1.5272F, -3.0543F)
      );
      PartDefinition cube_r22 = bb_main.addOrReplaceChild(
         "cube_r22",
         CubeListBuilder.create()
            .texOffs(360, 0)
            .mirror()
            .addBox(-44.9576F, 5.2602F, 11.8013F, 48.0F, 8.0F, 44.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 1.5708F, 0.3927F, -1.5708F)
      );
      PartDefinition cube_r23 = bb_main.addOrReplaceChild(
         "cube_r23",
         CubeListBuilder.create().texOffs(180, 0).addBox(-1.1143F, 0.4615F, 99.9977F, 36.0F, 8.0F, 40.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -1.5708F, 0.3927F, 1.6581F)
      );
      PartDefinition cube_r24 = bb_main.addOrReplaceChild(
         "cube_r24",
         CubeListBuilder.create().texOffs(180, 48).addBox(-30.0927F, 32.8901F, 95.9201F, 24.0F, 16.0F, 44.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.4835F, 0.1745F)
      );
      PartDefinition cube_r25 = bb_main.addOrReplaceChild(
         "cube_r25",
         CubeListBuilder.create()
            .texOffs(180, 108)
            .mirror()
            .addBox(85.3954F, 46.6902F, -129.4958F, 32.0F, 8.0F, 36.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -0.0436F, -0.7854F, 0.0F)
      );
      PartDefinition cube_r26 = bb_main.addOrReplaceChild(
         "cube_r26",
         CubeListBuilder.create().texOffs(180, 108).addBox(-117.3954F, 46.6902F, -129.4958F, 32.0F, 8.0F, 36.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 3.098F, -0.7854F, -3.1416F)
      );
      PartDefinition cube_r27 = bb_main.addOrReplaceChild(
         "cube_r27",
         CubeListBuilder.create()
            .texOffs(232, 363)
            .mirror()
            .addBox(-87.5372F, 67.7166F, -95.5372F, 32.0F, 60.0F, 8.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -3.1416F, -0.7854F, 2.5744F)
      );
      PartDefinition cube_r28 = bb_main.addOrReplaceChild(
         "cube_r28",
         CubeListBuilder.create().texOffs(232, 363).addBox(55.5372F, 67.7166F, -95.5372F, 32.0F, 60.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -0.7854F, -0.5672F)
      );
      PartDefinition cube_r29 = bb_main.addOrReplaceChild(
         "cube_r29",
         CubeListBuilder.create()
            .texOffs(244, 284)
            .mirror()
            .addBox(5.5953F, 44.707F, -135.9544F, 44.0F, 8.0F, 76.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.0F, -1.4835F, 0.0F)
      );
      PartDefinition cube_r30 = bb_main.addOrReplaceChild(
         "cube_r30",
         CubeListBuilder.create().texOffs(244, 284).addBox(-49.5953F, 44.707F, -135.9544F, 44.0F, 8.0F, 76.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 3.1416F, -1.4835F, 3.1416F)
      );
      PartDefinition cube_r31 = bb_main.addOrReplaceChild(
         "cube_r31",
         CubeListBuilder.create()
            .texOffs(256, 224)
            .mirror()
            .addBox(-172.6074F, -10.3089F, -87.2234F, 88.0F, 8.0F, 52.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.4591F, -1.3759F, -0.4667F)
      );
      PartDefinition cube_r32 = bb_main.addOrReplaceChild(
         "cube_r32",
         CubeListBuilder.create().texOffs(256, 224).addBox(84.6074F, -10.3089F, -87.2234F, 88.0F, 8.0F, 52.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -2.6825F, -1.3759F, 2.6749F)
      );
      PartDefinition cube_r33 = bb_main.addOrReplaceChild(
         "cube_r33",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .mirror()
            .addBox(-17.3917F, -2.9845F, -144.1987F, 12.0F, 56.0F, 156.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -1.5708F, -1.1345F, 1.5708F)
      );
      PartDefinition cube_r34 = bb_main.addOrReplaceChild(
         "cube_r34",
         CubeListBuilder.create().texOffs(0, 0).addBox(5.3917F, -2.9845F, -144.1987F, 12.0F, 56.0F, 156.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 1.5708F, -1.1345F, -1.5708F)
      );
      PartDefinition cube_r35 = bb_main.addOrReplaceChild(
         "cube_r35",
         CubeListBuilder.create()
            .texOffs(0, 224)
            .mirror()
            .addBox(-87.4847F, -10.9244F, -95.6495F, 96.0F, 12.0F, 64.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, 0.3199F, -1.4329F, -0.3228F)
      );
      PartDefinition cube_r36 = bb_main.addOrReplaceChild(
         "cube_r36",
         CubeListBuilder.create().texOffs(0, 224).addBox(-8.5153F, -10.9244F, -95.6495F, 96.0F, 12.0F, 64.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -57.707F, 0.0F, -2.8217F, -1.4329F, 2.8188F)
      );
      return LayerDefinition.create(meshdefinition, 1024, 1024);
   }

   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }
}
