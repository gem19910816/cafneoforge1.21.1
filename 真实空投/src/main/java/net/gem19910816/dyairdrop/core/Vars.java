package net.gem19910816.dyairdrop.core;

import net.mcreator.dyairdrop.network.DyairdropModVariables;
import net.minecraft.world.entity.Entity;

/**
 * 玩家数据（Data Attachment）访问工具。
 *
 * <p>取代到处出现的
 * {@code ((DyairdropModVariables.PlayerVariables) entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()))}，
 * 顺便避免漏写 {@code ifPresentData(...)} 时的重复取值。
 *
 * <p>字段名与语义保持不变（{@code password} / {@code pw} / {@code showlight} / {@code keyticking} /
 * {@code passwordre} / {@code keyre} / {@code airdroploot} / {@code airdropblock}），存档兼容。
 */
public final class Vars {

    private Vars() {
    }

    public static DyairdropModVariables.PlayerVariables of(Entity entity) {
        return entity.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get());
    }

    /** 把该玩家的变量同步给他本人（原 {@code syncPlayerVariables}）。 */
    public static void sync(Entity entity) {
        of(entity).syncPlayerVariables(entity);
    }
}
