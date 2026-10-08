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

public class Modelflare_gun<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("dyairdrop", "modelflare_gun"), "main");
   public final ModelPart flare;

   public Modelflare_gun(ModelPart root) {
      this.flare = root.getChild("flare");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition flare = partdefinition.addOrReplaceChild(
         "flare",
         CubeListBuilder.create()
            .texOffs(4, 6)
            .mirror()
            .addBox(-0.815F, 0.32F, -1.185F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .mirror(false),
         PartPose.offset(0.0F, 17.68F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 16, 16);
   }

   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
      this.flare.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   }
}
