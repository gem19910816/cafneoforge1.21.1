# Ammo Unify · 铳械弹药统一

> 给 **TaCZ（Timeless and Classics Zero）** 用的弹药统一模组。
> 把"一把枪只用它自己那一种专属弹药"改成"按武器类别共用少量通用弹药"。

- 平台：**NeoForge 1.21.1**（`neoforge >= 21.1`）
- 前置：**TaCZ 1.1.8 系列**（`tacz >= 1.1.8`，对应 Modrinth 上的 1.21.1 NeoForge 版）
- 许可：**AGPL-3.0-only**（见 `LICENSE` 与 `NOTICE.md`）
- 产物：`build/libs/ammo_unify-1.0.0.jar`（约 41 KB）

---

## 1. 它解决什么问题

TaCZ 的每把枪，在它自己的 gun index 数据里写死了一种弹药（`GunData.ammoId`）。
装 10 个枪包就有几十种子弹，背包里全是无法通用的弹药，看着头大。

本模组把这件事变成一张**按武器类别查表**：

| 武器类别（gun index 的 `type`） | 统一后的弹药 |
|---|---|
| `pistol`、`smg` | 通用手枪弹 |
| `rifle`、`mg` | 通用步枪弹 |
| `shotgun` | 通用霰弹 |
| `sniper` | 通用狙击弹 |
| `rpg` | 通用炮弹 |
| `fuel` | 通用能量罐 |

这 6 种弹药是**本模组自己注册的 TaCZ 正规弹药**，带完整的物品图标、3D 模型、弹壳特效和枪械工作台配方，可以直接合成。

---

## 2. 实现原理（为什么它只有 17 行核心代码）

TaCZ 1.1.8 里所有与弹药有关的逻辑，最终都汇聚到**同一个数据源**：`CommonGunIndex.getGunData()`。

| 功能 | TaCZ 内部实际走的路 |
|---|---|
| 弹药匹不匹配这把枪 | `AmmoItemDataAccessor.isAmmoOfGun` → `gunIndex.getGunData().getAmmoId().equals(ammoId)` |
| 弹匣盒匹不匹配 | `AmmoBoxItemDataAccessor.isAmmoBoxOfGun` → 同上 |
| 换弹 / 退弹 / 背包扫弹 | `AbstractGunItem.canReload`、`findAndExtractInventoryAmmo`、`hasInventoryAmmo`、`dropAllAmmo` → 全部归结到上面两个判定 |
| 射击时消耗弹药 | `ModernKineticGunScriptAPI.hasAmmoToConsume`、`consumeAmmoFromPlayer`、`reduceAmmoOnce` → 同上 |
| HUD 上的备用弹数 | `GunHudOverlay.handleInventoryAmmo` → 同一个判定 |
| 弹匣容量 | `putAmmoInMagazine` → `AttachmentDataUtils.getAmmoCountWithAttachment(itemStack, gunIndex.getGunData())` |
| 配件数值缓存 | `ChangeGunPropertyEvent` → `cacheProperty.eval(gunItem, gunIndex.getGunData())` |
| 真实弹道（伤害/初速/穿透…） | `shootOnce` 头两行 → `gunIndex.getGunData()` 与 `gunIndex.getBulletData()` |

所以本模组**只覆写 `CommonGunIndex` 的两个方法**（见 `mixin/CommonGunIndexMixin.java`），
为需要统一的枪派生一份把 `ammoId` 换掉的 `GunData` 副本，其余字段原样保留。
一处替换，上表八条同时生效——**没有一行客户端代码，也不需要注入任何 lambda 或合成方法**。

派生结果按 `CommonGunIndex` 实例缓存（`ConcurrentHashMap`），热路径上只有一次哈希查找；
未命中时才走一次反射浅拷贝（TaCZ 的数据类只有 getter、无 setter 也无拷贝构造器，反射是唯一途径）。

---

## 3. 安装

1. 装好 NeoForge 1.21.1 客户端/服务端。
2. `mods/` 里放入 TaCZ 1.1.8（`tacz-neoforge-1.21.1-1.1.8-hotfix-*.jar`）。
3. `mods/` 里放入 `ammo_unify-1.0.0.jar`。
4. **客户端和服务端都要装**；两边都要有同一份配置文件（见下）。

> 注意：**不要**再装 Tacz-Unidict（铳械协议）。二者做的是同一件事，同时装会互相打架。

---

## 4. 配置

启动一次后会生成 `config/ammo_unify/rules.json`：

```json
{
  "enabled": true,
  "by_type": {
    "pistol": "ammo_unify:cartridge_pistol",
    "smg": "ammo_unify:cartridge_pistol",
    "rifle": "ammo_unify:cartridge_rifle",
    "mg": "ammo_unify:cartridge_rifle",
    "shotgun": "ammo_unify:shell_shotgun",
    "sniper": "ammo_unify:cartridge_sniper",
    "rpg": "ammo_unify:shell_barrel",
    "fuel": "ammo_unify:tank_fuel"
  },
  "by_gun": {},
  "ignore_guns": [],
  "ignore_ammo": [],
  "ballistics": {}
}
```

查找顺序（先命中先返回）：

1. `ignore_guns` —— 命中的枪**完全不处理**，保持原来的专属弹药
2. `by_gun` —— 按具体枪 id 指定弹药，优先级高于类别
3. `by_type` —— 按武器类别指定弹药
4. 都没命中 —— 这把枪保持原样

### 4.1 怎么知道一把枪的 id 和类别

开游戏后看日志（本模组会把每一把被改写的枪打到 DEBUG 级），或者直接翻枪包的
`data/<命名空间>/index/guns/*.json`：`type` 字段就是类别。

### 4.2 弹道覆写

`index/ammo` 只描述弹药物品本身，真正的弹道在枪的 `BulletData` 里。
所以想让"通用弹"真的像不同的子弹，就在 `ballistics` 里按**弹药 id** 写：

```json
"ballistics": {
  "ammo_unify:cartridge_sniper": {
    "damage": 14.0,
    "speed": 900.0,
    "pierce": 3,
    "armor_ignore": 0.6,
    "headshot_multiplier": 2.5,
    "damage_falloff": [
      { "distance": 80.0, "damage": 14.0 },
      { "distance": 200.0, "damage": 9.0 },
      { "distance": 1.0e30, "damage": 5.0 }
    ]
  },
  "ammo_unify:shell_barrel": {
    "explosive": true,
    "explosion_radius": 4.0,
    "explosion_damage": 18.0,
    "explosion_destroy_block": false,
    "ignite_entity": true,
    "ignite_seconds": 5
  }
}
```

可写字段：`damage`、`speed`、`gravity`、`friction`、`knockback`、`pierce`、
`bullet_amount`、`life_second`、`armor_ignore`、`headshot_multiplier`、
`damage_falloff`、`tracer`、`explosive`、`explosion_radius`、`explosion_damage`、
`explosion_destroy_block`、`ignite_entity`、`ignite_block`、`ignite_seconds`。

**只有显式写了的字段会被覆盖**，没写的沿用原来那把枪的数值。

---

## 5. 通用弹药的获取

6 种弹药都在**枪械工作台（Gun Smith Table）**里合成，用 NeoForge 通用标签，
兼容各类矿辞模组：

| 弹药 | 配方 | 产出 |
|---|---|---|
| 通用手枪弹 | 5× `c:ingots/copper` + 2× `c:gunpowders` | 40 |
| 通用步枪弹 | 8× `c:ingots/copper` + 3× `c:gunpowders` | 32 |
| 通用霰弹 | 6× `c:ingots/copper` + 4× `c:gunpowders` | 24 |
| 通用狙击弹 | 12× `c:ingots/copper` + 5× `c:gunpowders` | 16 |
| 通用炮弹 | 16× `c:ingots/iron` + 8× `c:gunpowders` | 8 |
| 通用能量罐 | 6× `c:ingots/copper` + 4× `minecraft:coal` | 8 |

配方是普通数据包内容，整合包想改数值直接覆盖
`data/ammo_unify/recipe/ammo/*.json` 即可。

---

## 6. 已知限制（请先读这条再报 bug）

1. **原有的枪包专属弹药会失效**。统一之后，那些枪只认通用弹，
   原来枪包自带的 `tacz:9mm` 之类不能再喂给它们。这是"统一"的必然结果，
   不是 bug。不想被统一的枪请写进 `ignore_guns`。
2. **目前不支持同一把枪在多种弹药间切换**（没有轮盘换弹菜单）。
   弹药由 `by_gun` / `by_type` 静态决定。
3. **客户端与服务端必须使用同一份 `rules.json`**。规则是本地读取的，不做网络同步；
   整合包把配置一起打包即可，服务器自己改过的规则客户端也要跟着改。
4. **只对走 `ModernKineticGunScriptAPI` 的现代枪械生效**，
   走脚本（Lua）自行处理弹药的枪包可能不受影响。
5. 需要 **TaCZ 1.1.8 系列**。1.1.7 及更早的数据类布局不同，
   mixin 会在启动时报注入失败——宁可明确报错，也不静默失效。
6. **不支持"用任意物品当弹药"**（例如让手枪打金粒）。统一的目标弹药必须是
   一个真实的 TaCZ 弹药（本模组自带的 6 种，或任何枪包里的弹药）。
   这是刻意的取舍：TaCZ 的弹药判定被 `instanceof IAmmo` 卡住，要放开它就得在
   五六个背包扫描点各注入一次，会显著牺牲稳定性。

---

## 7. 排错

**症状：进游戏后找不到那 6 种通用弹药（创造模式物品栏里没有）**

说明枪包没有被 TaCZ 扫到。本模组采用 TaCZ 官方注释里写的"直接把 assets 和 data 内置在模组中"
的平铺布局（`data/ammo_unify/index/ammo/*.json`）。如果这个布局在你的 TaCZ 版本上不生效，
用 TaCZ 的**导出式**布局作为兜底：

1. 把 `src/main/resources/assets/ammo_unify/{display,geo_models,textures,lang,gunpack_info.json}`
   和 `src/main/resources/data/ammo_unify/{index,recipe}` 整体移到
   `src/main/resources/assets/ammo_unify/custom/ammo_unify_core/` 下（保持 `assets/ammo_unify/...`
   与 `data/ammo_unify/...` 的相对结构不变）；
2. 在该目录放一个 `gunpack.meta.json`：`{ "namespace": "ammo_unify" }`；
3. 模组主类里调用 TaCZ 的公开扩展点：
   ```java
   com.tacz.guns.api.resource.ResourceManager.registerExportResource(
       AmmoUnify.class, "/assets/ammo_unify/custom/ammo_unify_core");
   ```
   TaCZ 首次加载时会把该文件夹解压到 `.minecraft/tacz/` 下并注册。

**症状：日志里出现 mixin 注入失败 / `CommonGunIndex` 相关报错**

TaCZ 版本不对。本模组针对 **1.1.8 系列**的数据类布局编写；
`AmmoItemDataAccessor.isAmmoOfGun` 与 `CommonGunIndex.getGunData()` 这两个点是整套逻辑的地基，
TaCZ 换了布局就会明确报错而不是静默失效。

**症状：枪装不上弹了**

检查 `config/ammo_unify/rules.json` 里 `by_type` / `by_gun` 指向的弹药 id 是否真的存在
（拼写、命名空间）。若指向了不存在的弹药，那把枪会没有任何可用弹药。
想临时关闭整个模组，把 `"enabled"` 设为 `false`。

**症状：客户端 HUD 备弹数和实际不符**

客户端与服务端的 `rules.json` 不一致。本模组不做配置网络同步，请保证两边一致。

---

## 8. 自己构建

需要 **JDK 21**，并且建议设置 `JAVA_HOME` 指向它（wrapper 脚本优先用 `JAVA_HOME`，
没设时回退到 `PATH` 上的 `java`）。

第一步，准备编译用的 TaCZ jar（本仓库不包含它）：

> Modrinth 项目 **`tacz-1.21.1`**（[UNOFFICIAL] TaCZ 1.21.1 NeoForge Port），
> 下载 **1.1.8-hotfix** 系列的主文件，放到 `libs/` 下，
> 文件名与 `gradle.properties` 里的 `tacz_jar` 一致。

第二步：

```bash
./gradlew build          # 产物在 build/libs/
./gradlew runClient      # 开开发客户端（会把 TaCZ 一起加载，方便联调）
```

Windows 上用 `gradlew.bat`。

`libs/` 里的 TaCZ 二进制 jar **仅用于编译**（`compileOnly` / `runtimeOnly`），
运行时由玩家或整合包提供，不会被打进 `ammo_unify-1.0.0.jar`。

### 重新生成素材

全部贴图、Bedrock 模型与数据 JSON 都由脚本生成，不是手写的：

```bash
python tools/generate_assets.py
```

### 开发笔记

- 本模组**不依赖 MixinExtras**，只用原生 Sponge Mixin。TaCZ 1.21.1 自身也没有 refmap，
  NeoForge 1.21.1 运行期即官方名，所以 `ammo_unify.mixins.json` 里不声明 `refmap`。
- `CommonGunIndex` 不记录自己的 gunId，`GunDataDeriver` 用 `CommonAssetsManager.getAllGuns()`
  建一次反查表（枪包重载时清空）。

---

## 9. 许可与来源

- 本模组代码与素材以 **AGPL-3.0-only** 发布。完整文本见 `LICENSE`。
- **来源与合规说明请务必阅读 [`NOTICE.md`](NOTICE.md)**，其中包含与 TaCZ、
  以及与本模组功能相同的在先实现 Tacz-Unidict 之间关系的完整说明。
- 本模组**不包含** TaCZ 或任何第三方枪包的贴图、模型与数据文件。
