package com.carrion.doomsdaycontainers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

/** Per-block switches for the containers added by this mod. */
public final class ContainerConfig {
    private static final Map<ResourceLocation, ModConfigSpec.BooleanValue> ENABLED = new LinkedHashMap<>();
    private static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Enable container behavior for each Doomsday Decoration block. Changes require a restart.")
                .push("containers");
        builder.push(DoomsdayContainers.TARGET_MODID);

        try (InputStream stream = ContainerConfig.class.getResourceAsStream(ContainerTargets.RESOURCE)) {
            if (stream != null) {
                JsonObject root = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                        .getAsJsonObject();
                JsonObject targets = root.has("targets") ? root.getAsJsonObject("targets") : root;
                for (Map.Entry<String, com.google.gson.JsonElement> entry : targets.entrySet()) {
                    if (entry.getValue().isJsonObject()
                            && entry.getValue().getAsJsonObject().has("native")
                            && entry.getValue().getAsJsonObject().get("native").getAsBoolean()) {
                        continue;
                    }
                    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                            DoomsdayContainers.TARGET_MODID, entry.getKey());
                    builder.comment(id.toString());
                    ENABLED.put(id, builder.worldRestart().define(id.getPath(), true));
                }
            } else {
                DoomsdayContainers.LOGGER.error("Missing container target list {}; per-block config is empty",
                        ContainerTargets.RESOURCE);
            }
        } catch (Exception exception) {
            DoomsdayContainers.LOGGER.error("Failed to create per-block container config", exception);
        }

        builder.pop();
        builder.pop();
        SPEC = builder.build();
    }

    private ContainerConfig() {}

    public static void register() {
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.SERVER, SPEC);
    }

    public static boolean isEnabled(ResourceLocation id) {
        ModConfigSpec.BooleanValue value = ENABLED.get(id);
        return value == null || value.get();
    }
}
