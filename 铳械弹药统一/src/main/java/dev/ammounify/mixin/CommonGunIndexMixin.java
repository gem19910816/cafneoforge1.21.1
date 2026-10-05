package dev.ammounify.mixin;

import com.tacz.guns.resource.index.CommonGunIndex;
import com.tacz.guns.resource.pojo.data.gun.BulletData;
import com.tacz.guns.resource.pojo.data.gun.GunData;
import dev.ammounify.data.GunDataDeriver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 本模组唯一的 mixin。
 *
 * <p>{@code CommonGunIndex} 是 TaCZ 里"一把枪的静态数据"的汇聚点，而
 * {@code getGunData()} 又是所有弹药相关逻辑的共同上游：</p>
 *
 * <ul>
 *   <li>弹药匹配 —— {@code AmmoItemDataAccessor.isAmmoOfGun} 内部就是
 *       {@code gunIndex.getGunData().getAmmoId().equals(ammoId)}；
 *       {@code AmmoBoxItemDataAccessor.isAmmoBoxOfGun} 同理</li>
 *   <li>装填/退弹/背包扫描 —— {@code AbstractGunItem.canReload}、
 *       {@code findAndExtractInventoryAmmo}、{@code hasInventoryAmmo}、
 *       {@code ModernKineticGunScriptAPI.hasAmmoToConsume}、
 *       {@code consumeAmmoFromPlayer}、{@code reduceAmmoOnce} 都归结到上面那两个判定</li>
 *   <li>HUD 备弹统计 —— {@code GunHudOverlay.handleInventoryAmmo} 用的也是同一判定</li>
 *   <li>弹匣容量 —— {@code ModernKineticGunScriptAPI.putAmmoInMagazine} 里
 *       {@code AttachmentDataUtils.getAmmoCountWithAttachment(itemStack, gunIndex.getGunData())}</li>
 *   <li>配件数值缓存 —— {@code ChangeGunPropertyEvent} 里
 *       {@code cacheProperty.eval(gunItem, gunIndex.getGunData())}</li>
 *   <li>真实弹道 —— {@code ModernKineticGunScriptAPI.shootOnce} 的第一二行
 *       就是 {@code gunIndex.getGunData()} 与 {@code gunIndex.getBulletData()}</li>
 * </ul>
 *
 * <p>换掉这两个方法，上面六条一次性全部生效，无需任何客户端代码。
 * 返回的数据由 {@link GunDataDeriver} 按 index 实例缓存，未命中缓存时只做一次哈希查找。</p>
 */
@Mixin(value = CommonGunIndex.class, remap = false)
public abstract class CommonGunIndexMixin {

    @Inject(method = "getGunData", at = @At("HEAD"), cancellable = true, remap = false)
    private void ammoUnify$overrideGunData(CallbackInfoReturnable<GunData> cir) {
        GunData derived = GunDataDeriver.derived((CommonGunIndex) (Object) this);
        if (derived != null) {
            cir.setReturnValue(derived);
        }
    }

    @Inject(method = "getBulletData", at = @At("HEAD"), cancellable = true, remap = false)
    private void ammoUnify$overrideBulletData(CallbackInfoReturnable<BulletData> cir) {
        GunData derived = GunDataDeriver.derived((CommonGunIndex) (Object) this);
        if (derived != null) {
            cir.setReturnValue(derived.getBulletData());
        }
    }
}
