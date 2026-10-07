package net.gem19910816.dyairdrop.core;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.structure.Structure;

/**
 * {@code /locatetag <结构标签>}：查询最近的结构并把坐标播报出来。
 *
 * <p>合并原 {@code T6Procedure} 与 {@code FindNearestStructureProcedure}（两个类，一共 48 行，
 * 一个只负责取值、一个只负责广播）。
 *
 * <p>顺带修掉原实现的一个 NPE：{@code ServerLevel.findNearestMapStructure} 在 100 区块内找不到结构时
 * 返回 {@code null}，原代码直接调 {@code structurePos.getX()} 会崩溃；现在返回「未找到」文本。
 */
public final class StructureLocator {

    private static final int SEARCH_RADIUS_CHUNKS = 100;
    private static final String NOT_FOUND_TEXT = "未找到该结构标签对应的结构";

    private StructureLocator() {
    }

    /** 命令入口：解析标签 → 查最近结构 → 全服播报坐标。 */
    public static void locateAndBroadcast(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
        if (entity == null) {
            return;
        }
        String tag = CommandArgs.message(arguments, "structure");
        Chat.broadcast(world, Component.literal(findNearest(entity, tag)));
    }

    /**
     * 在实体所在维度查找标签对应的最近结构。
     *
     * @return {@code "x, y, z"}；不在服务端或没找到时返回提示文本
     */
    public static String findNearest(Entity entity, String structureTag) {
        if (!(entity.level() instanceof ServerLevel level)) {
            return "";
        }
        TagKey<Structure> tag = TagKey.create(Registries.STRUCTURE, ResourceLocation.parse(structureTag));
        BlockPos origin = entity.blockPosition();
        BlockPos found = level.findNearestMapStructure(tag, origin, SEARCH_RADIUS_CHUNKS, false);
        if (found == null) {
            return NOT_FOUND_TEXT;
        }
        return found.getX() + ", " + found.getY() + ", " + found.getZ();
    }
}
