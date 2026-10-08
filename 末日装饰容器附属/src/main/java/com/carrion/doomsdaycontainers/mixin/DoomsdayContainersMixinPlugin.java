package com.carrion.doomsdaycontainers.mixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/**
 * 混入保险丝：原模组换版本、删方块或改名时，只是对应的混入不生效，而不是整个游戏起不来。
 *
 * - 目标类不在 classpath 上 → 跳过该混入（并打印一条提示）
 * - 覆盖式混入（AcrateBlockEntityMixin）额外确认目标类里确实有要覆盖的方法
 */
public class DoomsdayContainersMixinPlugin implements IMixinConfigPlugin {
    private static final String TARGET_PREFIX = "net.mcreator.doomsdaydecoration.";

    @Override
    public void onLoad(String mixinPackage) {
        // nothing to do
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!targetClassName.startsWith(TARGET_PREFIX)) {
            return true;
        }
        String resource = targetClassName.replace('.', '/') + ".class";
        byte[] bytes = readResource(resource);
        if (bytes == null) {
            System.out.println("[DoomsdayContainers] 目标方块不存在，跳过混入：" + targetClassName);
            return false;
        }
        if (mixinClassName.endsWith("AcrateBlockEntityMixin")) {
            // 覆盖 createMenu 之前，确认目标类里确实有这个方法的实现
            return containsAscii(bytes, "createMenu");
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // nothing to do
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // nothing to do
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // nothing to do
    }

    private static byte[] readResource(String resource) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) loader = DoomsdayContainersMixinPlugin.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream(resource)) {
            return in == null ? null : in.readAllBytes();
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean containsAscii(byte[] haystack, String needle) {
        byte[] pattern = needle.getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        outer:
        for (int i = 0; i <= haystack.length - pattern.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (haystack[i + j] != pattern[j]) continue outer;
            }
            return true;
        }
        return false;
    }
}
