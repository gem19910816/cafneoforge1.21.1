package dev.ammounify;

import com.mojang.logging.LogUtils;
import dev.ammounify.rule.AmmoRules;
import dev.ammounify.rule.RuleReloader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Ammo Unify —— TaCZ 弹药统一。
 *
 * <p>TaCZ 的每把枪在它自己的 gun index 数据里写死了一种专属弹药（{@code GunData.ammoId}）。
 * 枪包一多，弹药种类就爆炸。本模组的思路是：把"这把枪用哪种弹药"这个问题，
 * 从"每把枪一个专属答案"改成"按武器类别查表得到少数几种通用弹药"。</p>
 *
 * <h2>实现原理</h2>
 * <p>TaCZ 里所有与弹药有关的判定，最终都收敛到同一个数据源：
 * {@code CommonGunIndex.getGunData()}。例如弹药匹配
 * （{@code AmmoItemDataAccessor.isAmmoOfGun} 就是拿 {@code gunIndex.getGunData().getAmmoId()}
 * 去比弹药物品的 id）、装填与退弹、背包弹药扫描、HUD 备弹统计、弹匣容量
 * （{@code AttachmentDataUtils.getAmmoCountWithAttachment(itemStack, gunIndex.getGunData())}）、
 * 配件数值缓存（{@code ChangeGunPropertyEvent} 里传入的也是 {@code gunIndex.getGunData()}）、
 * 以及开枪时取出的 {@code GunData}/{@code BulletData}（{@code ModernKineticGunScriptAPI.shootOnce}
 * 的头两行）——它们无一例外都经过 {@code CommonGunIndex}。</p>
 *
 * <p>因此本模组只在 {@code CommonGunIndex} 这一个点上做替换：为"需要统一的枪"
 * 派生一份把 {@code ammoId} 换成通用弹药的副本，其余字段原样保留。
 * 替换一处，全链路生效。不写一行客户端代码。</p>
 *
 * <p>许可：AGPL-3.0-only。来源与合规说明见仓库根目录的 {@code NOTICE.md}。</p>
 */
@Mod(AmmoUnify.MOD_ID)
public final class AmmoUnify {

    public static final String MOD_ID = "ammo_unify";
    public static final Logger LOG = LogUtils.getLogger();

    public AmmoUnify(IEventBus modBus, ModContainer container) {
        // 双端都需要规则：客户端要用它算 HUD 备弹与工具提示，服务端要用它算真实弹道。
        AmmoRules.reload();
        RuleReloader.attach();
        LOG.info("[Ammo Unify] 已加载，规则条目 {} 条（{}）",
                AmmoRules.get().totalRules(),
                AmmoRules.get().isActive() ? "启用" : "未启用");
    }
}
