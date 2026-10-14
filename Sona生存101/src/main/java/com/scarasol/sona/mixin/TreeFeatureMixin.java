package com.scarasol.sona.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import com.scarasol.sona.manager.InfectionManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Scarasol
 */
@Mixin(TreeFeature.class)
public abstract class TreeFeatureMixin extends Feature<TreeConfiguration> {
    public TreeFeatureMixin(Codec<TreeConfiguration> codec) {
        super(codec);
    }

    // 1.21.1 的 place() 结构重写，装饰器列表直接在 treeconfiguration.decorators 上遍历；
    // 感染区需要追加装饰器时，包装读取 decorators 字段的表达式即可
    @ModifyExpressionValue(method = "place", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/levelgen/feature/configurations/TreeConfiguration;decorators:Ljava/util/List;"))
    private List<TreeDecorator> sona$addInfectionDecorators(List<TreeDecorator> original, FeaturePlaceContext<TreeConfiguration> context) {
        ServerLevel serverLevel = context.level().getLevel();
        if (serverLevel != null && InfectionManager.canChunkInfection(serverLevel)) {
            List<TreeDecorator> extra = InfectionManager.addTreeDecorator(serverLevel, context.origin());
            if (!extra.isEmpty()) {
                List<TreeDecorator> merged = new ArrayList<>(original);
                merged.addAll(extra);
                return merged;
            }
        }
        return original;
    }
}
