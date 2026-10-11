package com.scarasol.rummage.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.scarasol.rummage.RummageMod.MODID;

/**
 * @author Scarasol
 */
public class RummageTags {

    public static final TagKey<Item> CHAIN_BLACKLIST = ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, "chain_blacklist"));
    public static final TagKey<Item> CHAIN_WHITELIST = ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, "chain_whitelist"));

}
