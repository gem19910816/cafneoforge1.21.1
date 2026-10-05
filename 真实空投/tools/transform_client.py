# -*- coding: utf-8 -*-
import os, re

SRC = r'C:\Users\79662\AppData\Local\Temp\dyairdrop_moj\net\mcreator\dyairdrop'
DST = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

count = 0
for sub in (r'client\renderer', r'client\model', r'client\particle'):
    sdir = os.path.join(SRC, sub)
    ddir = os.path.join(DST, sub)
    os.makedirs(ddir, exist_ok=True)
    for fn in os.listdir(sdir):
        if not fn.endswith('.java'):
            continue
        t = open(os.path.join(sdir, fn), encoding='utf-8').read()
        body = 'public class' + t.split('public class', 1)[1]
        # 1.21.1: renderToBuffer color int instead of floats
        body = body.replace(
            'public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {',
            'public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {')
        body = re.sub(r'\.render\(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha\);',
                      '.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);', body)
        # generic imports for client classes
        imports = {
            'import javax.annotation.Nullable;',
            'import net.minecraft.client.model.EntityModel;',
            'import net.minecraft.client.model.GeckoLibModel;',
            'import net.minecraft.client.model.HeadedModel;',
            'import net.minecraft.client.model.HierarchicalModel;',
            'import net.minecraft.client.model.Model;',
            'import net.minecraft.client.model.MeshDefinition;',
            'import net.minecraft.client.model.PartDefinition;',
            'import net.minecraft.client.model.ModelPart;',
            'import net.minecraft.client.model.geom.ModelLayerLocation;',
            'import net.minecraft.client.model.geom.ModelPartPose;',
            'import net.minecraft.client.model.geom.PartPose;',
            'import net.minecraft.client.model.geom.builders.CubeDeformation;',
            'import net.minecraft.client.model.geom.builders.CubeListBuilder;',
            'import net.minecraft.client.model.geom.builders.LayerDefinition;',
            'import net.minecraft.client.model.geom.builders.MeshDefinition;',
            'import net.minecraft.client.model.geom.builders.PartDefinition;',
            'import net.minecraft.client.multiplayer.ClientLevel;',
            'import net.minecraft.client.particle.Particle;',
            'import net.minecraft.client.particle.ParticleProvider;',
            'import net.minecraft.client.particle.ParticleRenderType;',
            'import net.minecraft.client.particle.SpriteSet;',
            'import net.minecraft.client.particle.TextureSheetParticle;',
            'import net.minecraft.client.renderer.MultiBufferSource;',
            'import net.minecraft.client.renderer.RenderType;',
            'import net.minecraft.client.renderer.entity.EntityRendererProvider;',
            'import net.minecraft.client.renderer.entity.MobRenderer;',
            'import net.minecraft.resources.ResourceLocation;',
            'import net.minecraft.world.entity.Entity;',
            'import net.minecraft.world.entity.LivingEntity;',
            'import com.mojang.blaze3d.vertex.PoseStack;',
            'import com.mojang.blaze3d.vertex.VertexConsumer;',
            'import net.neoforged.api.distmarker.Dist;',
            'import net.neoforged.api.distmarker.OnlyIn;',
            'import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;',
            'import software.bernie.geckolib.cache.object.BakedGeoModel;',
            'import software.bernie.geckolib.model.GeoModel;',
            'import software.bernie.geckolib.renderer.GeoEntityRenderer;',
            'import software.bernie.geckolib.renderer.GeoRenderer;',
            'import software.bernie.geckolib.renderer.layer.GeoRenderLayer;',
            'import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;',
            'import software.bernie.geckolib.model.DefaultedEntityGeoModel;',
            'import software.bernie.geckolib.constant.DataTickets;',
            'import software.bernie.geckolib.animation.AnimationController;',
            'import software.bernie.geckolib.animation.AnimationState;',
            'import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;',
            'import software.bernie.geckolib.animation.PlayState;',
            'import software.bernie.geckolib.animation.RawAnimation;',
            'import software.bernie.geckolib.util.GeckoLibUtil;',
            'import net.minecraft.core.particles.SimpleParticleType;',
            'import net.minecraft.world.entity.monster.Monster;',
            'import net.minecraft.util.Mth;',
        }
        body = body.replace('software.bernie.geckolib.core.animatable.instance', 'software.bernie.geckolib.animatable.instance')
        body = body.replace('software.bernie.geckolib.core.animation', 'software.bernie.geckolib.animation')
        body = body.replace('software.bernie.geckolib.core.object.PlayState', 'software.bernie.geckolib.animation.PlayState')
        body = body.replace('software.bernie.geckolib.core.object', 'software.bernie.geckolib.animation')
        used = [imp for imp in imports if re.search(re.escape(imp.split('.')[-2] + '.' + imp.split('.')[-1].rstrip(';')) if False else imp.split()[-1][:-1].split('.')[-1] + r'\b', body)]
        # simpler: include every import whose simple name appears in body
        used = []
        for imp in sorted(imports):
            simple = imp.split('.')[-1].rstrip(';')
            if re.search(r'\b' + re.escape(simple) + r'\b', body):
                used.append(imp)
        pkg = 'net.mcreator.dyairdrop.' + sub.replace(os.sep, '.')
        header = 'package %s;\n\n%s\n\n' % (pkg, '\n'.join(used))
        open(os.path.join(ddir, fn), 'w', encoding='utf-8').write(header + body)
        count += 1
print('client classes:', count)
