package com.scarasol.itemrarity.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * @author Scarasol
 */
public class CommonConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue AUTO_DOWNLOAD;

    static {

        AUTO_DOWNLOAD = BUILDER.comment(
                "Whether to automatically download and extract missing config files on startup (non-overwriting)."
        ).define("autoDownload", false);

        SPEC = BUILDER.build();
    }
}