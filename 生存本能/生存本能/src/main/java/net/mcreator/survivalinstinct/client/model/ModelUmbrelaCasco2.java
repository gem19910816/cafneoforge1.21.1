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

public class ModelUmbrelaCasco2<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("survival_instinct", "model_umbrela_casco_2"), "main");
   public final ModelPart Casco;

   public ModelUmbrelaCasco2(ModelPart root) {
      this.Casco = root.getChild("Casco");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition Casco = partdefinition.addOrReplaceChild(
         "Casco",
         CubeListBuilder.create()
            .texOffs(0, 0)
            .addBox(-4.0F, -6.9F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.1F))
            .texOffs(59, 0)
            .addBox(-3.3F, -4.8F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(59, 12)
            .addBox(1.3F, -4.8F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.2F))
            .texOffs(0, 16)
            .addBox(-4.0F, -7.4F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.6F))
            .texOffs(26, 10)
            .addBox(-4.0F, -4.0F, -2.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(1.0F))
            .texOffs(24, 0)
            .addBox(-4.0F, -4.0F, -4.0F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.8F))
            .texOffs(24, 18)
            .addBox(-4.0F, -4.0F, 0.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.3F)),
         PartPose.offset(0.0F, 0.0F, 0.0F)
      );
      PartDefinition group = Casco.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(8.0F, 0.0F, -12.0F));
      PartDefinition Casco_r1 = group.addOrReplaceChild(
         "Casco_r1",
         CubeListBuilder.create()
            .texOffs(0, 53)
            .addBox(2.5F, 2.0F, -0.7F, 3.0F, 1.0F, 3.0F, new CubeDeformation(-0.1F))
            .texOffs(0, 51)
            .addBox(2.5F, 0.0F, -0.7F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.2F))
            .texOffs(12, 48)
            .addBox(2.9F, 2.7F, 0.0F, 2.3F, 1.0F, 2.3F, new CubeDeformation(-0.2F)),
         PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, -0.8021F, -0.504F, -0.437F)
      );
      PartDefinition Casco_r2 = group.addOrReplaceChild(
         "Casco_r2",
         CubeListBuilder.create()
            .texOffs(12, 36)
            .addBox(-1.1F, 1.7F, 0.0F, 2.3F, 1.0F, 2.3F, new CubeDeformation(0.1F))
            .texOffs(12, 41)
            .addBox(-1.5F, 1.0F, -0.7F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.2F))
            .texOffs(0, 39)
            .addBox(-1.5F, -3.0F, -0.7F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F))
            .texOffs(73, 0)
            .addBox(-1.5F, 0.0F, -0.7F, 3.0F, 1.0F, 3.0F, new CubeDeformation(-0.1F)),
         PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, -0.9163F, 0.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 200, 200);
   }

   @Override
   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.Casco.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }

   @Override
   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
