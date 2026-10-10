package net.mcreator.survivalinstinct.client;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

@EventBusSubscriber(modid = "survival_instinct", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ArmorModelCache {
    private static final Map<ModelLayerLocation, ModelPart> ROOTS = new HashMap<>();
    private static final Map<String, HumanoidModel<?>> MODELS = new HashMap<>();

    private ArmorModelCache() {}

    public static ModelPart root(ModelLayerLocation layer) {
        return ROOTS.computeIfAbsent(layer, key -> Minecraft.getInstance().getEntityModels().bakeLayer(key));
    }

    public static HumanoidModel<?> model(String key, Supplier<HumanoidModel<?>> factory) {
        return MODELS.computeIfAbsent(key, ignored -> factory.get());
    }

    @SubscribeEvent
    public static void registerReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener((ResourceManagerReloadListener) manager -> clear());
    }

    public static void clear() {
        MODELS.clear();
        ROOTS.clear();
    }
}
