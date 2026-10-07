package net.gem19910816.dyairdrop.compat.map;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

/**
 * 一个「地图标记」的服务端记录。
 *
 * <p>空投落地时创建，空投箱被搜空或被移除时销毁；客户端把它翻译成 Xaero（或未来的其它地图模组）
 * 的临时路点。所有字段都是不可变的，坐标用 {@link BlockPos}。
 *
 * @param id    标记唯一 id（服务端递增分配，用于 ADD/REMOVE 对应）
 * @param level 所在维度
 * @param pos   方块坐标（空投箱位置）
 * @param label 显示名（沿用箱子显示名这一原有行为）
 * @param color Xaero 颜色序号 0~15（按空投等级区分）
 */
public record MapMarker(int id, ResourceKey<Level> level, BlockPos pos, String label, int color) {
}
