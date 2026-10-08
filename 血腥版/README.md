# 血腥版 (Gore Edition) — 1.21.1 NeoForge

由 1.20.1 Forge 版 `minecraft_gore_edition-0.5-forge-1.20.1d.jar` 移植而来
（即 Modrinth / CurseForge 上 Gore Edition `0.5 alpha 4d` 那一版，作者 NekroPlaga，MCreator 制作）。

**注意**：本模组是 MCreator 生成的 1.20.1 版本。作者另有自己用代码重写的 NeoForge 版本
（0.5.1，包名 `gore_edition.content.*`），那个不是本移植的产物，两者互不兼容，**不要同时安装**。

| 项目 | 说明 |
|---|---|
| 支持版本 | Minecraft **1.21.1** / NeoForge **21.1.255**（21.1.150+ 均可） |
| Java | 21 |
| 模组 ID | `gore_edition`（与 1.20.1 版一致） |
| 内容 | 86 个物品、15 个方块、102 个实体、102 种粒子、5 个状态效果、24 个音效、1 个附魔 |
| 数据 | 22 张战利品表、22 个配方、6 个进度、9 个结构、44 个标签 |
| 前置 | [GeckoLib for NeoForge 1.21.1](https://modrinth.com/mod/geckolib) **4.9.3+** |
| 可选 | [PlayerAnimator](https://modrinth.com/mod/playeranimator) 2.0.4+1.21.1（第三人称动作） |

## 直接使用

下载本文件夹里的 `gore_edition-0.5-neoforge-1.21.1.jar`，丢进 `.minecraft/mods/` 即可。

**必须同时安装 GeckoLib 4.9.3（neoforge-1.21.1）**：本模组的 97 个渲染器、77 个模型与 72 个
`.animation.json` 全部走 GeckoLib。`neoforge.mods.toml` 里把它标为 optional，是为了让服务器端
可以不装；客户端不装会看不到任何模型。

方块 / 物品 / 实体的注册 ID 与 1.20.1 版完全相同，贴图、模型、动画、语言文件均为原样搬移，
因此旧存档、投影、蓝图可以继续使用。

## 自己编译

```bash
# 需要 JDK 21、Gradle 8.8+
./gradlew build      # 产物在 build/libs/
```

用 ModDevGradle 2.0.78 拉取 NeoForge 21.1.255，GeckoLib 与 PlayerAnimator 声明为 `compileOnly`
（见 `build.gradle`）。

## 移植要点

- 起始基线是本仓库所在整合包内一份**已完成大半的 NeoForge 1.21.1 移植**（`gore_edition-0.5.jar`），
  但它可反编译不可编译、且专用服务器启动即崩。本次把它做成可编译、可启动、可通过验证的成品。
- **GeckoLib 4.9 包名整包改名**：`software.bernie.geckolib.core.*` →
  `animatable.*` / `animation.*` / `constant.dataticket.*`（已在上游移植中完成）。
- **网络层重写**：`SimpleChannel` / `NetworkRegistry` → `CustomPacketPayload` + `StreamCodec` +
  `PayloadRegistrar`；`NetworkHooks.openScreen` → `Player.openMenu`。
- **注册表 API**：`RegistryObject` → `DeferredHolder`；`ForgeRegistries` → `BuiltInRegistries`；
  `ForgeConfigSpec` → `ModConfigSpec`；玩家变量由 capabilitiy 改为 **NeoForge Data Attachment**。
- 数据包目录按 1.21 规范单数化：`loot_tables/` → `loot_table/`、`recipes/` → `recipe/`、
  `advancements/` → `advancement/`、`structures/` → `structure/`、`tags/blocks/` → `tags/block/` 等共 103 处。
- 本次修掉的关键问题见下方「验证情况」与 [移植说明.md](移植说明.md)。

### 关于命名空间（重要）

1.20.1 发行版把模组**自己的 26 个标签**放在 `forge:` 命名空间
（`data/forge/tags/entity_types/ge_corpses.json` → `forge:ge_corpses`），代码里也按 `forge:ge_*` 查询。
这些标签**保持原样留在 `forge:`**，只把 `data/forge/loot_modifiers/global_loot_modifiers.json`
按 NeoForge 要求迁到 `data/neoforge/`。

> 如果把它们一起改名成 `neoforge:`，代码里所有 `forge:ge_corpses` / `forge:inmune_entity` /
> `forge:corpse_included` / `forge:legacy_solid_entities` / `forge:the_flesh_eaters` 查询会**静默失效**
> （不报错，但尸体判定、免疫判定、按材质分类的死亡演出全部失灵）。

## 验证情况

- `javac 21` 编译 915 个源文件（1231 个类）**0 error**。
- NeoForge 21.1.255 官方专用服务器实机加载：`Done (0.587s)!`，
  **与本模组相关的 ERROR 0 条**（只剩下述原模组自带的两个孤立战利品表）。
- 数据包断言（`minecraft:load` 函数，抽样 `setblock` / `summon` / `loot spawn` / `effect give` / `enchant`）：
  方块、物品、实体、战利品表、状态效果、附魔 `explosive_contact` 全部通过；
  4 个注册表标签（`#forge:ge_corpses`、`#forge:solid_entities`、`#gore_edition:ashes_natives`、
  `#gore_edition:ashes_blocks`）全部解析成功。
- 玩家登录路径回归测试（另写 `ge_regression` 探针模组，直接调用崩服那条链路）：
  空栈 `ItemStack.save` 确会抛 `Cannot encode empty ItemStack`；修复后全新 `PlayerVariables`
  的 `writeNBT` 正常返回 37 个键，非空栈往返 `minecraft:stone x3` 无损、空栈往返为 EMPTY。
- 资源静态校验：555 个 JSON 全部解析通过；17 个 blockstate、146 个模型 → **0 缺失模型、
  0 悬空父模型、0 缺失贴图**；119 个物品模型覆盖全部 86 个物品；102 个粒子定义文件齐全；
  `data/` 与 `assets/` 下不存在任何 1.20.1 旧目录名。

### 本次修掉的三个致命问题

| 问题 | 现象 | 根因 |
|---|---|---|
| `HellAshesDimension$HellAshesSpecialEffectsHandler` 缺 `value = Dist.CLIENT` | 专用服务器启动崩：`has no @SubscribeEvent methods, but register was called anyway` | NeoForge 的 `@EventBusSubscriber` 默认 `{CLIENT, DEDICATED_SERVER}`，而该处理器参数是仅客户端的 `RegisterDimensionSpecialEffectsEvent`。全量扫描 915 个源文件后确认**只有这 1 个**漏了 Dist 限定 |
| 3 个 `Tier#getIncorrectBlocksForDrops()` 返回 `null` | 数据包加载崩：`MappedRegistry.bindTags` NPE | 1.21 用该方法取代被删除的 `getLevel()`（原值均为 0）。`DiggerItem`/`AxeItem` 构造时调用 `Tier#createToolProperties`，null 标签会往方块注册表的标签表写入 **null 键**。改为 `BlockTags.INCORRECT_FOR_WOODEN_TOOL` |
| 玩家变量里对空物品栈调 `ItemStack#save` | **一进世界就断线**：`连接已丢失 / Internal Exception: java.nio.channels.ClosedChannelException` | 1.21 的 `ItemStack#save` 对空栈直接抛 `Cannot encode empty ItemStack`（1.20.1 只是写空 tag）。`third_hand_itemstack_store` 初值就是 `ItemStack.EMPTY`，于是每个玩家第一次进世界都崩在 `PlayerLoggedInEvent` 里。已改为空栈不写、读取端缺键取 EMPTY |

另外修掉两个 1.21 已失效的 1.20.1 资源写法：
`models/item/acid_bucket.json` 的 `forge:fluid_container` / `forge:item/bucket_drip` →
`neoforge:fluid_container` / `neoforge:item/bucket_drip`（否则酸液桶模型加载失败）；
`models/item/third_hand.json` 引用的贴图名含空格（`coffin_orb (copia 1)`）在 1.21 不是合法资源位置，
已指向 `coffin_orb`。

## 原模组自带的问题（未改动，保持原样，1.20.1 版即如此）

1. **两个孤立战利品表**：`data/gore_edition/loot_table/blocks/voidstone.json` 与
   `hell_portal_frame.json` 引用 `goreedition:voidstone` / `goreedition:hell_portal_frame`。
   命名空间少写了下划线，而且这两个方块**在本模组里根本不存在**（只注册了 15 个方块）。
   1.21 会把它们记为两条 ERROR 并跳过；功能上无影响。想彻底安静就删掉这两个 JSON。
2. `sounds.json` 的 `extra_smashed` 引用的 `.ogg` 在原 jar 中就不存在。
3. 9 个实体 ID 没有语言条目（`acid_ashes`、`ashes_black_hole`、`grenade_of_acid`、
   `grenade_of_greek_fire_projectile`、`magma_grenade_projectile`、`projectile_squished`、
   `projectile_wither_skull`、`skeleton_without_arm_arm_projectile`、
   `skeleton_without_arm_head_projectile`）——1.20.1 的 `en_us.json` 也没有。
4. 部分标签引用了不存在的条目（例如 `gore_edition:exarrack_art_down_center`），
   `height_ge_normal_entities` 与 `hell_orb` 两个标签的 `values` 是空数组。
5. 若干无人引用的遗留文件（`models/block/HellPortalFrame.json` 大写、`* (copia 1).png`、
   `acid_effect (old).png`、`sounds/flesh eater.txt`、`blockbench/`、`geo/*.bbmodel` 等），
   因为没有任何 blockstate / 模型引用，Minecraft 不会加载，只是占体积。

## 许可

与原模组保持一致（All Rights Reserved）。移植仅做版本与 API 适配，未修改任何美术资源，
也未包含原作者的代码重写版（0.5.1）中的任何内容。
