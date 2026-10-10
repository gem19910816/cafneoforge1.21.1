package com.scarasol.tud.compat;

import com.scarasol.tud.TudMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import java.util.Set;

/** Optional bridge: do not require a Forge-only Tag Editor binary at compile time. */
public final class TagEditorCompat {
    private static boolean warned;
    private TagEditorCompat() {}

    public static Set<ResourceLocation> getAllTags(ItemStack stack) { return tags(stack, ItemStack.class); }
    public static Set<ResourceLocation> getAllTags(ResourceLocation id) { return tags(id, ResourceLocation.class); }

    @SuppressWarnings("unchecked")
    private static Set<ResourceLocation> tags(Object value, Class<?> type) {
        try {
            Class<?> helper = Class.forName("com.scarasol.tageditor.compat.tacz.TaczTagHelper");
            return (Set<ResourceLocation>) helper.getMethod("getAllItemTags", type).invoke(null, value);
        } catch (ReflectiveOperationException | LinkageError exception) {
            if (!warned) {
                warned = true;
                TudMod.LOGGER.warn("Tag Editor bridge unavailable for this version", exception);
            }
            return Set.of();
        }
    }
}
