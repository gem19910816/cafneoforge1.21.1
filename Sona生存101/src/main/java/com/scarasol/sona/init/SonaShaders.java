package com.scarasol.sona.init;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

/**
 * @author Scarasol
 */
@EventBusSubscriber(modid = "sona", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SonaShaders {

    public static ShaderInstance entityDitherShader;
    public static ShaderInstance infectionShaderSkyPostShader;

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(event.getResourceProvider(), "sona:rendertype_entity_dither", DefaultVertexFormat.NEW_ENTITY),
                shaderInstance -> entityDitherShader = shaderInstance
        );
        event.registerShader(
                new ShaderInstance(event.getResourceProvider(), "sona:infection_shader_sky_post", DefaultVertexFormat.POSITION_TEX_COLOR),
                shaderInstance -> infectionShaderSkyPostShader = shaderInstance
        );
    }
}
