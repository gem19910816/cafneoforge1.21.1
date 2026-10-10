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

public class Modelchiken_head<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelchiken_head"), "main");
   public final ModelPart head;

   public Modelchiken_head(ModelPart root) {
      this.head = root.getChild("head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create()
            .texOffs(32, 41)
            .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.7F))
            .texOffs(37, 12)
            .addBox(-5.0F, -6.0F, -3.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F))
            .texOffs(30, 34)
            .addBox(4.0F, -6.0F, -3.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition head_r1 = head.addOrReplaceChild(
         "head_r1",
         CubeListBuilder.create().texOffs(53, 12).mirror().addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false),
         PartPose.offsetAndRotation(4.5F, -5.0F, -1.0F, 0.132F, 0.1298F, 0.0172F)
      );
      PartDefinition head_r2 = head.addOrReplaceChild(
         "head_r2",
         CubeListBuilder.create().texOffs(0, 16).mirror().addBox(2.0F, -1.0F, -0.75F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(-1.0F, -1.0F, -5.25F, -0.0462F, 0.0302F, -0.1719F)
      );
      PartDefinition head_r3 = head.addOrReplaceChild(
         "head_r3",
         CubeListBuilder.create().texOffs(40, 34).mirror().addBox(2.0F, -1.0F, -0.75F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
         PartPose.offsetAndRotation(0.0F, -2.0F, -5.25F, -0.1555F, -0.1642F, -0.3335F)
      );
      PartDefinition head_r4 = head.addOrReplaceChild(
         "head_r4",
         CubeListBuilder.create().texOffs(10, 32).addBox(-1.0F, -5.5F, -0.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, 1.5F, -0.6981F, 0.0F, 0.0F)
      );
      PartDefinition head_r5 = head.addOrReplaceChild(
         "head_r5",
         CubeListBuilder.create().texOffs(39, 20).addBox(-1.0F, -3.5F, -0.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -7.5F, 3.5F, -1.0472F, 0.0F, 0.0F)
      );
      PartDefinition head_r6 = head.addOrReplaceChild(
         "head_r6",
         CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -7.5F, -0.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -1.5F, -0.48F, 0.0F, 0.0F)
      );
      PartDefinition head_r7 = head.addOrReplaceChild(
         "head_r7",
         CubeListBuilder.create().texOffs(20, 32).addBox(-1.0F, -5.5F, -0.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -8.5F, -4.5F, -0.2618F, 0.0F, 0.0F)
      );
      PartDefinition head_r8 = head.addOrReplaceChild(
         "head_r8",
         CubeListBuilder.create()
            .texOffs(29, 28)
            .addBox(-1.5F, 0.0F, -2.75F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(24, 16)
            .addBox(-2.0F, -2.0F, -3.75F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.0F, -5.25F, 0.1745F, 0.0F, 0.0F)
      );
      PartDefinition head_r9 = head.addOrReplaceChild(
         "head_r9",
         CubeListBuilder.create().texOffs(40, 34).addBox(-4.0F, -1.0F, -0.75F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.0F, -2.0F, -5.25F, -0.1555F, 0.1642F, 0.3335F)
      );
      PartDefinition head_r10 = head.addOrReplaceChild(
         "head_r10",
         CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -1.0F, -0.75F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(1.0F, -1.0F, -5.25F, -0.0462F, -0.0302F, 0.1719F)
      );
      PartDefinition head_r11 = head.addOrReplaceChild(
         "head_r11",
         CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -1.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -3.7716F, -5.9335F, 0.48F, 0.0F, 0.0F)
      );
      PartDefinition head_r12 = head.addOrReplaceChild(
         "head_r12",
         CubeListBuilder.create().texOffs(53, 12).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.4F)),
         PartPose.offsetAndRotation(-4.5F, -5.0F, -1.0F, 0.132F, -0.1298F, -0.0172F)
      );
      return LayerDefinition.create(meshdefinition, 64, 64);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
