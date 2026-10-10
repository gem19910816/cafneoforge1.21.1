# cafneoforge1.21.1

齿轮与腐肉整合包（CAF）的 **Minecraft 1.21.1 / NeoForge** 模组合集。
一个模组一个文件夹，文件夹内附源码与可直接使用的成品 jar。

| 模组 | 原版本 | 目标平台 | 成品 |
|---|---|---|---|
| [末日装饰 (Doomsday Decoration)](末日装饰/) | 1.20.1 Forge 1.1.3 | 1.21.1 NeoForge | [jar](末日装饰/doomsday_decoration-1.1.3-neoforge-1.21.1.jar) |
| [末日装饰容器附属 (Doomsday Containers)](末日装饰容器附属/) | —（**附属**，需末日装饰） | 1.21.1 NeoForge | [jar](末日装饰容器附属/doomsdaycontainers-1.0.0.jar) |
| [全球市场 (Global Market)](全球市场/) | 1.20.1 Forge（原 `MOD/global-market`） | 1.21.1 NeoForge | [jar](全球市场/gearsandflesh_market-1.0.0.jar) |
| [真实空投 (Realistic Airdrop)](真实空投/) | 1.20.1 Forge 1.1.0-beta | 1.21.1 NeoForge | [jar](真实空投/dyairdrop-1.1.0.jar) |
| [稀薄的空气 (Thin Air)](稀薄的空气/) | 1.20.1 Forge（原 Thin Air / fuzs） | 1.21.1 NeoForge | [jar](稀薄的空气/thinair-1.21.1-neoforge-21.1.1-port.jar) |
| [僵尸游戏 (ZombieGame:Reborn)](僵尸游戏/) | 1.20.1 Forge 2.1.0 | 1.21.1 NeoForge | [jar](僵尸游戏/zombiegamereborn-2.1.0.jar) |
| [急救护理 (SelfAid)](急救护理/) | —（1.21.1 **新实现**，非移植） | 1.21.1 NeoForge | [jar](急救护理/selfaid-1.0.0.jar) |
| [绿葡萄护甲 (LesRaisins Armor)](绿葡萄护甲/) | 1.20.1 Forge 0.1.4.4 | 1.21.1 NeoForge | [jar](绿葡萄护甲/lrarmor-0.1.4.4.jar) |
| [CAF 生存核心 (CAF Survival Core)](CAF生存核心/) | 1.20.1 Forge（原 Tarkov Stamina / ChaosZ Pack） | 1.21.1 NeoForge | [jar](CAF生存核心/tarkov_stamina-1.21.1-neoforge-1.0.0-port.jar) |
| [CAF草药 (Crop Expansion)](CAF草药/) | 1.20.1 Forge（原 `1.20.1forge/药草作物`） | 1.21.1 NeoForge | [jar](CAF草药/crop_expansion-1.21.1-neoforge-1.0.0.jar) |
| [血腥版 (Gore Edition)](血腥版/) | 1.20.1 Forge 0.5 alpha 4d | 1.21.1 NeoForge | [jar](血腥版/gore_edition-0.5-neoforge-1.21.1.jar) |
| [TACZ弹药统一 (Tacz: UniDict)](TACZ弹药统一/) | Tacz-Unidict 2.0.1（非官方移植） | 1.21.1 NeoForge 21.1.252 | [源码与构建说明](TACZ弹药统一/README.md) |

## 说明

- **移植**条目均保持方块/物品注册 ID 与美术资源不变，只做 API 与数据包格式的版本适配。
- 每个模组文件夹里的 `README.md` 写明用法，`移植说明.md` 写明改动清单与验证结果。
- 构建环境：JDK 21 + Gradle 8.8+ + ModDevGradle 2.0.148 + NeoForge 21.1.255。
- **附属**条目（末日装饰容器附属）不改动前置模组，通过 Mixin 在加载期注入能力，可随原模组独立升级。

## 特别标注：附属模组

### [末日装饰容器附属 (Doomsday Containers)](末日装饰容器附属/) — 1.21.1 新实现（附属）

给《末日装饰》用的**容器附属**：把原模组里 **114 种"看起来像容器"的装饰方块**
（柜子、箱子、货架、垃圾桶、行李箱、售货机、冰箱、弹药箱、保险箱、储物柜，以及 30 种遗体、裹尸袋……）
变成真正的容器 —— 右键开原版箱子界面（按方块大小 1~6 行）、内容随存档持久化、
暴露 NeoForge `ItemHandler` 能力（漏斗 / 溜槽 / 物流管道可存取）、比较器按装满程度输出红石信号、
破坏时内容物完整掉落、潜行右键保持方块原有交互。

- **前置**：末日装饰 `doomsday_decoration` 1.1.x（**必需**，即本仓库 `末日装饰/` 目录内的 jar）。
- **不改原模组**：全部通过 Mixin 在加载期注入 `EntityBlock` 与右键入口，原模组 jar 一个字节未改。
  带保险丝：原模组删掉 / 改名某个方块时，只跳过那一个混入（日志提示），其余方块照常，不会崩游戏。
- **开关音效按类型区分（16 种）**：木箱是木盖声、金属柜是铁门声、售货机 / ATM 是卷帘门声、
  行李箱是皮箱声、纸箱是纸板声、塑料箱是塑料声、遗体是黏腻声、垃圾袋 / 裹尸袋是布料声……
- **顺手修掉原模组两处问题**：
  1. 板条箱 `acrate` 的界面是「空的 27 格箱子」——`createMenu` 既没绑定自身的 9 格库存，行数也不对；
  2. 1.21.1 生存模式的右键入口是 `useWithoutItem`，而原模组只实现了 `getMenuProvider`
     （`javap` 核对：该方法在 `ServerPlayerGameMode` 里仅出现在旁观者分支），
     所以板条箱在生存模式下其实打不开界面。
- **验证状态**：`javac 21` 编译 **0 error**（122 个源文件、116 个混入类）；
  NeoForge 21.1.255 专用服务器两轮 RCON 实测：界面 / 菜单自检 **114/114 通过**、
  开关音效 **114/114 逐个确认派发（16 种不同音效）**、方块实体随放置自动建立、
  「漏斗把绿宝石推进柜子」证实 ItemHandler 能力可用、破坏方块内容物完整掉落、
  6 类容器跨重启持久化全部保留、**两轮日志 0 条 ERROR**。
  **客户端图形界面与音效听感未实机目视 / 试听**，详见其 `移植说明.md` 第 8 节。
## 特别标注：非移植条目

### [急救护理 (SelfAid)](急救护理/) — 1.21.1 新实现

也不是移植：它是按 First Aid（ichttt）的玩法概念（分部位生命值 + 急救物品 + 死亡规则）
**从零重写**的原创实现，代码约 8 个类，**没有复制 FirstAid 的任何代码**。

- **玩法**：玩家分 6 个部位（头 20% / 躯干 30% / 双臂各 10% / 双腿各 15%），
  弹射物按命中高度判定爆头/躯干/四肢，摔落集中在腿；头、躯干或全身清空即死亡；
  部分清空给虚弱/缓慢惩罚；共 4 件急救物品（绷带、创可贴、吗啡、急救包）+ 可配置的六格血量 HUD。
- **素材许可**：物品贴图（`bandage` / `plaster` / `morphine`）与 HUD 身体贴图取自
  [ichttt/FirstAid](https://github.com/ichttt/FirstAid)（**GPL-3.0**），按其许可证引用；
  急救包贴图与全部代码为原创。分发时请保留该声明。
- **不要与 FirstAid 同时安装**：两者都会重分配玩家伤害，会互相干扰。
- **验证状态**：编译通过；**尚未进世界实测**（伤害分配、死亡判定、物品回血、HUD 均未实机验证），
  详见其 `移植说明.md` 第四节。

## 特别标注：架构改造条目

### [全球市场 (Global Market)](全球市场/) — 1.21.1 移植 + 数据源改为官网

它是移植，但不是「原样搬过来」：1.20.1 版把挂单存在**当前存档**里，导致单机玩家自成一个市场、
各服务器市场互相隔离。1.21.1 版把数据源整体改为**官网 API**，因此所有联网玩家共享同一个市场。

- **依赖**：需要可用的官网市场 API（`config/gearsandflesh_market-common.toml` 里的 `apiBaseUrl`），
  本模组不包含官网代码。无网络时游戏本体正常，仅市场不可用。
- **新增**：官网账号绑定（`/market bind`）、存档创造检测（开过创造的存档永久禁止上传）、
  市场终端方块（无物品形态，创造与 `/give` 都拿不到）、钱包存取（实体币 ↔ 官网余额）、
  内置货币 `caf:money`（1.21.1 整合包已无 KubeJS 注册的该物品）。
- **规则外移**：物品白名单、NBT 校验、价格中位数区间、新账号审批、限频、手续费全部由官网执行，
  改规则不需要玩家更新模组。
- **验证状态**：编译通过，且已用 NeoForge 21.1.255 开发环境专用服务器实测加载（模组进入模组列表、
  货币注册成功、服务器正常启动）。**与官网 API 的实机联调、多人并发与界面视觉核对尚未进行**，
  详见其 `移植说明.md` 第五节。

## 特别标注：需要前置模组

### [真实空投 (Realistic Airdrop)](真实空投/) — 1.21.1 移植（1:1）

从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar`（作者 Ian，MCreator 制作）**1:1 移植**。
运输机按设定高度与距离飞越玩家上空投下补给箱，箱内按等级抽取战利品并可设密码锁，
空投会吸引敌人前来抢夺；另配 6 把信号枪手动召唤武器 / 医疗 / 小型空投。

- **前置**：GeckoLib for NeoForge 1.21.1 **4.9.3+**（构建期从 GeckoLib 官方 Maven 拉取，
  仓库不附带该 jar）。**不装 GeckoLib 会直接加载失败**。
- **可选**：`zombiekit` 1.21.1 版。原模组自带 20 张引用它的掉落表，未安装时这批表会解析失败
  （仅日志提示，不影响启动与 `dyairdrop` 自身功能）。
- **保留项**：方块 / 物品 / 实体 / 菜单 / 音效的注册 ID 与 1.20.1 版完全一致，
  `assets/` 176 个文件原样保留（贴图、模型、geo、动画、音效、语言逐字节未改），
  `config/dyairdrop.toml` 的分区与键名未变 → 旧存档、投影与旧配置文件可直接沿用。
  `data/` 已按 1.21 规范单数化，配方与战利品表结构同步升级。
- **本次修掉的三个原版 BUG**：
  1. **密码面板**：原版把面板文本框只存在客户端 `guistate` 里，
     `ButtoncheckProcedure` / `SetpwProcedure` / `OpProcedure` 在**专用服务器**上读到空串，
     导致「密码永远验证不了 / 管理员无法设置密码」。已改为按钮包携带文本框内容
     （本地有控件时仍优先用控件，单人行为与原版一致）。
  2. **小地图标记**：原版执行一条**并不存在的指令** `addwaypointxaero`
     （仓库内全部 jar 中除本模组自身外无人提供），因此 Xaero 小地图上永远没有空投标记。
     已改为先检测该指令是否存在（不存在就静默跳过），并按 Xaero 自己的
     `xaero-waypoint:` 分享格式下发系统消息，**只装 Xaero 小地图即可点击添加路点**；
     同时修掉召唤指令里的 `ForgeData` → `NeoForgeData`（不改则地图标记开关永远读不到）。
  3. **与并发区块 / 方块实体模组冲突（如 C2ME）**：MCreator 的界面在发包后还会**在客户端本地
     再执行一遍**服务端过程，于是客户端也在 `world.setBlock` 换箱子（等于在客户端销毁 / 重建带
     方块实体的方块）；而且换箱走的是 `performPrefixedCommand("setblock … replace")`，
     把指令通道上的所有模组都拉进了密码面板流程；定时步骤还不检查区块是否仍加载。
     现在：客户端不再执行这些事务（只发包），换箱改为服务端 + 区块检查的方块 API
     （新增 `compat/CrateCompat`），落地放置箱子也改为仅服务端。
     代价：解锁 / 设置密码 / 设置战利品需等约 1 tick 的往返才看到反馈。
- **验证状态**：`javac 21` 编译 **0 error**（261 个源文件）；NeoForge 21.1.255 官方专用服务器
  实测 `Done (0.8s)!`、**与本模组相关 ERROR 0 条**，并用数据包 load 函数断言
  15 种方块 `setblock`、7 种实体 `summon`、战利品表 `loot spawn`、物品 / 配方 / 进度全部通过；
  第 3 项的换箱路径另用一次性自检版 jar 实机跑过
  （`lockedairdroplarge → lockedairdroplargeopen` + 战利品表写入成功、无 `open` 变体与未加载区块均安全返回）。
  **客户端渲染、Xaero 点击提示，以及「装了 C2ME 的真实多人客户端」下的原始冲突场景未实机复现**，
  详见其 `移植说明.md` 第五节。

### [稀薄的空气 (Thin Air)](稀薄的空气/) — 1.21.1 移植

从 1.20.1 Forge 版 Thin Air（作者 fuzs，含 1.20.4 分支）移植。空气按区域划分品质，
深处/密闭空间空气稀薄会持续掉氧，需靠空气囊、呼吸器、安全灯笼、信号火把等设备维持呼吸；
另含空气品质数据组件、呼吸器盔甲、Curios 头部槽位集成与一组对应进度。

- **前置**：Puzzles Lib for NeoForge 1.21.1 **≥ 21.1.x**（本仓库随附
  `稀薄的空气/libs/puzzleslib-v21.1.62-mc1.21.1+neoforge.jar`）。**不装 Puzzles Lib 会直接加载失败**。
- **可选**：Curios for NeoForge 1.21.1 **9.x**（随附 `libs/curios-neoforge-9.5.1+1.21.1.jar`）。
  未安装时呼吸器的饰品槽位功能不可用，其余功能正常。
- **保留项**：物品 / 方块注册 ID 与原版一致，`assets/` 美术资源原样保留（贴图、模型、音效未改动）。
- **移植版声明**：`neoforge.mods.toml` 中 displayName 为 `Thin Air (移植版)`，
  description 首行标注 `[社区移植版] 由 1.20.1 Forge 版移植至 1.21.1 NeoForge`，版本号带 `-port` 后缀。
- **验证状态**：编译通过（仅过时警告）、`runData` 全部 provider 成功（72+ 资源文件）、
  开发环境服务器启动至 `Done (3.882s)` 且日志无 thinair 相关错误、开发环境客户端进入标题界面零缺失模型。
  **多人并发与长时段玩法流程尚未实机验证**，详见其 `移植说明.md` 第三节。

### [僵尸游戏 (ZombieGame:Reborn)](僵尸游戏/) — 1.21.1 移植

从 1.20.1 Forge 版 `ZombieGame:Reborn` 2.1.0 移植。僵尸会挖穿方块接近玩家、拿方块搭桥建造，
随存活天数推进**阶段**逐步变强；带感染机制（村民/猪灵）、时间播报与铃声，
另有一层与枪械模组联动的「外交层」（Diplomat）。

- **硬前置**：无（只要求 NeoForge 21.1.150+ / MC 1.21.1）。
- **可选联动（全是软依赖，缺了照常启动）**：TaCZ 1.1.8（1.21.1 移植版）、PointBlank 1.11.1、
  MusketMod 1.5.4、Enhanced Celestials 6.x / 2-Core 2.x（血月）、GuardVillagers（纯反射）。
  运行时用 `ModList.get().isLoaded(...)` 守卫。
- **不随附编译期依赖**：`libs/` 下 7 个第三方 jar（TaCZ 57MB、PointBlank 17.5MB 等共约 78MB）
  与 NeoForge 反编译参考 `refsrc/` 都不入仓库；要重编请按 `僵尸游戏/README.md` 的清单自备。
- **本仓库侧的修正**：交付包把 9 个成就放在 `data/zombiegamereborn/advancements/`（复数），
  而 1.21.1 的数据目录是单数（`advancement`），复数会被静默忽略 → 成就一个都拿不到。
  已改名并同步改 jar 内条目，**jar 其余内容逐个 SHA256 比对未变**。
- **验证状态**：编译通过；专用服务器实测 `Done (2.238s)!`、**0 条 ERROR**，
  开发环境客户端 0 条 ERROR（`Sound engine started`）。
  **玩法（挖掘/建造、阶段推进、血月、枪械联动）与多人并发尚未实机验证**，
  详见其 `移植说明.md` 第四节。

### [绿葡萄护甲 (LesRaisins Armor)](绿葡萄护甲/) — 1.21.1 移植

从 1.20.1 Forge 版 `LesRaisinsArmor` 0.1.4.4 移植。数据包驱动的护甲模组：
16 套 / 64 件护甲（名字与贴图沿用原作），属性、套装效果、渲染配置全部由
`data/lrarmor/armor_data/*.json` 定义，配 GeckoLib 基岩模型渲染。

- **前置**：GeckoLib for NeoForge 1.21.1 **4.x**（`neoforge.mods.toml` 里是 required）。
  本仓库不含该 jar，重编前请放进 `绿葡萄护甲/libs/`。
- **未迁移**：EpicFight 兼容（`EpicFightCompat`）与 JEI 兼容均未包含在移植版内。
- **本仓库侧的修正**：交付包把 62 个配方放在 `data/lrarmor/recipes/`（复数），
  1.21.1 的目录是单数（`recipe`），复数会被静默忽略 → 16 套护甲一件都合不出来。
  已改名并同步改 jar 内条目，**jar 其余内容逐个 SHA256 比对未变**。
  （`armor_data/` 是模组自定义扫描目录，不受影响。）
- **验证状态**：仅 `./gradlew build` 通过（868 KB / 332 个条目）；
  **没有跑过客户端或服务端**（工程里连 `run/` 都没有），属性、套装、渲染均未实机验证，
  详见其 `移植说明.md` 的「验证状态」一节。

### [CAF 生存核心 (CAF Survival Core)](CAF生存核心/) — 1.21.1 移植

从 1.20.1 Forge 版 **Tarkov Stamina**（作者 ChaosZ Pack，包名 `com.chaosz.tarkovstamina`）移植。
一套服务端权威的生存状态系统 + 塔科夫风格 HUD：体力、基因强化、锻炼、负重、抑郁、疾病、恐慌、
成瘾、职业、钓鱼、排泄、军用背包与帐篷读条，配置集中在一个 TOML 里。

- **前置**：Curios for NeoForge 1.21.1 **9.x**（**硬前置**，随附
  `CAF生存核心/libs/curios-neoforge-9.5.1+1.21.1.jar`）。上游 `mods.toml` 即标 `mandatory=true`，
  **未安装会 `NoClassDefFoundError`**（背包背部饰品槽与「从饰品栏找回背包」直接调 `CuriosApi`）。
- **软依赖**：`simplytents`（帐篷）、`refurbished_furniture`（马桶）、`survival_instinct`（螺丝刀）、
  `doomsday_decoration`（家具）、`create`（零件）、`harmfulsmoke`（香烟）等，装了对应内容才生效。
- **保留项**：`mod_id` 仍是 `tarkov_stamina`，物品命名空间仍是 `caf`；
  玩家持久化 NBT 键（`stamina` / `cooldown` / `injectionCount` / `exerciseLevel` …）与 1.20.1 版**逐键一致**，
  旧存档的体力与技能进度可直接续用；配置文件分区与键名未变；`assets/` 原样搬运。
  本次是**「加载器换代」而非「功能重写」**，玩法数值一行未改。
- **验证状态**：编译 **0 error**（8 条 `EventBusSubscriber.bus` 过时警告，属有意保留）；
  NeoForge 21.1.227 专用服务器实测加载（`Done (6.151s)!`，与本模组相关 ERROR/WARN **0 条**，
  Curios 正常加载）；产物 jar 内的 `mods.toml`、数据包、25 张贴图与 50 个 class 已逐项核验。
  **客户端（`runClient` 从未启动——HUD 从 `IGuiOverlay` 改为 `LayeredDraw.Layer`，是本次风险最高的一处）、
  背包 GUI、Curios 背部渲染、玩法主流程、旧存档兼容、多人并发均尚未实机验证**，
  详见其 `移植说明.md` 第三、四节。

### [血腥版 (Gore Edition)](血腥版/) — 1.21.1 移植

从 1.20.1 Forge 版 `minecraft_gore_edition-0.5-forge-1.20.1d.jar`（Modrinth 上的 0.5 alpha 4d，
作者 NekroPlaga，MCreator 制作）移植。血腥演出模组：受伤 / 死亡时按材质生成血液、骨屑、肉块粒子，
僵尸、骷髅、蜘蛛等会变成断肢、无头、趴伏、解肢等多种尸体形态，另有尸检、灰烬维度、新武器与附魔。

- **前置**：GeckoLib for NeoForge 1.21.1 **4.9.3+**。上游 `mods.toml` 标为 optional（为了让服务器端
  可以不装），但**客户端不装会看不到任何模型**——97 个渲染器、77 个模型、72 个 `.animation.json`
  全部走 GeckoLib。可选 PlayerAnimator 2.0.4+1.21.1。
- **保留项**：`mod_id` 仍是 `gore_edition`，方块 / 物品 / 实体注册 ID 与 1.20.1 版完全一致，
  `assets/` 逐字节原样搬移，旧存档、投影、蓝图可继续使用。
- **命名空间（容易踩的坑）**：1.20.1 发行版把模组**自己的 26 个标签**放在 `forge:` 命名空间，
  代码里也按 `forge:ge_corpses` 等查询。本次**保持它们在 `forge:`**，只把
  `data/forge/loot_modifiers/global_loot_modifiers.json` 按 NeoForge 要求迁到 `data/neoforge/`。
  若把这些标签一并改名成 `neoforge:`，代码里所有 `forge:ge_*` 查询会**静默失效**（不报错，但尸体判定、
  免疫判定、按材质分类的死亡演出全部失灵）。
- **修掉的三个致命问题**：① `@EventBusSubscriber` 漏 Dist 限定导致专用服务器启动崩；
  ② 3 个 `Tier#getIncorrectBlocksForDrops()` 返回 `null` 导致数据包加载崩（`bindTags` NPE）；
  ③ 玩家变量对空物品栈调 `ItemStack#save` 导致**一进世界就断线**
  （`ClosedChannelException`，1.21 对空栈直接抛 `Cannot encode empty ItemStack`）。
- **验证状态**：`javac 21` 编译 915 个源文件（1231 个类）**0 error**；
  NeoForge 21.1.255 专用服务器实测 `Done (0.587s)!`，**与本模组相关 ERROR 0 条**；
  数据包断言覆盖方块 / 物品 / 实体 / 战利品表 / 状态效果 / 附魔 / 4 个注册表标签，全部通过；
  另用 `ge_regression` 探针模组对「登录即崩」那条链路做了回归测试（空栈抛异常确认、修复后
  `writeNBT` 正常、非空栈往返无损）；资源静态校验 0 缺失模型 / 0 悬空引用 / 0 旧目录名。
  **客户端渲染效果（尸体形态、粒子、GeckoLib 动画）未在带图形界面的客户端里逐项目视**，
  详见其 `移植说明.md`。
- **与作者重写版的区别**：作者另有自己用代码重写的 NeoForge 版本（0.5.1，包名
  `gore_edition.content.*`）。那个不是本移植的产物，**两者互不兼容、不要同时安装**。
