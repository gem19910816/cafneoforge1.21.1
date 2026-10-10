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

public class Modelnight_vision_goggles<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      ResourceLocation.fromNamespaceAndPath("survival_instinct", "modelnight_vision_goggles"), "main"
   );
   public final ModelPart head;

   public Modelnight_vision_goggles(ModelPart root) {
      this.head = root.getChild("head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition head = partdefinition.addOrReplaceChild(
         "head",
         CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.8F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition visor_r1 = head.addOrReplaceChild(
         "visor_r1",
         CubeListBuilder.create()
            .texOffs(0, 16)
            .mirror()
            .addBox(0.7809F, 1.9292F, -7.0335F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F))
            .mirror(false)
            .texOffs(60, 10)
            .mirror()
            .addBox(0.7809F, 1.9292F, -6.616F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .mirror(false)
            .texOffs(36, 62)
            .mirror()
            .addBox(0.9109F, 2.0163F, -4.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(64, 13)
            .mirror()
            .addBox(0.9109F, 2.0163F, -2.6283F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .mirror(false)
            .texOffs(0, 79)
            .mirror()
            .addBox(0.9109F, 2.0163F, -5.6283F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .mirror(false),
         PartPose.offsetAndRotation(-1.0F, -5.4775F, -4.8897F, 0.0457F, -0.3051F, -0.0138F)
      );
      PartDefinition visor_r2 = head.addOrReplaceChild(
         "visor_r2",
         CubeListBuilder.create().texOffs(62, 49).addBox(-2.0F, -0.713F, -2.0102F, 4.0F, 3.0F, 3.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -7.2628F, -4.1938F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition visor_r3 = head.addOrReplaceChild(
         "visor_r3",
         CubeListBuilder.create().texOffs(54, 58).addBox(-1.0F, -1.4305F, -3.0018F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.6F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.8727F, 0.0F, 0.0F)
      );
      PartDefinition visor_r4 = head.addOrReplaceChild(
         "visor_r4",
         CubeListBuilder.create().texOffs(24, 12).addBox(-4.0F, 1.3031F, -3.5191F, 8.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r5 = head.addOrReplaceChild(
         "visor_r5",
         CubeListBuilder.create().texOffs(36, 48).addBox(0.0F, 1.9292F, -5.8744F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r6 = head.addOrReplaceChild(
         "visor_r6",
         CubeListBuilder.create()
            .texOffs(27, 78)
            .addBox(-1.2981F, -0.9564F, -1.5019F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F))
            .texOffs(40, 55)
            .addBox(-1.2981F, -0.9564F, 1.4981F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(58, 42)
            .addBox(-1.2981F, -0.9564F, -0.5019F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.1F))
            .texOffs(22, 59)
            .addBox(-1.2981F, -1.0436F, -2.4981F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12)
            .addBox(-1.2981F, -1.0436F, -2.9156F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.3F)),
         PartPose.offsetAndRotation(0.2981F, -2.3571F, -8.2073F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition visor_r7 = head.addOrReplaceChild(
         "visor_r7",
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
         PartPose.offsetAndRotation(1.0F, -5.4775F, -4.8897F, 0.0457F, 0.3051F, 0.0138F)
      );
      PartDefinition visor_r8 = head.addOrReplaceChild(
         "visor_r8",
         CubeListBuilder.create().texOffs(0, 60).addBox(-2.0F, -0.5344F, -4.0628F, 4.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)),
         PartPose.offsetAndRotation(0.0F, -5.4775F, -4.8897F, 0.2618F, 0.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 128, 128);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
