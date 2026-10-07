# cafneoforge1.21.1

齿轮与腐肉整合包（CAF）的 **Minecraft 1.21.1 / NeoForge** 模组合集。
一个模组一个文件夹，文件夹内附源码与可直接使用的成品 jar。

| 模组 | 原版本 | 目标平台 | 成品 |
|---|---|---|---|
| [末日装饰 (Doomsday Decoration)](末日装饰/) | 1.20.1 Forge 1.1.3 | 1.21.1 NeoForge | [jar](末日装饰/doomsday_decoration-1.1.3-neoforge-1.21.1.jar) |
| [铳械弹药统一 (Ammo Unify)](铳械弹药统一/) | —（1.21.1 **新实现**，非移植） | 1.21.1 NeoForge | [jar](铳械弹药统一/ammo_unify-1.0.0.jar) |
| [全球市场 (Global Market)](全球市场/) | 1.20.1 Forge（原 `MOD/global-market`） | 1.21.1 NeoForge | [jar](全球市场/gearsandflesh_market-1.0.0.jar) |
| [真实空投 (Realistic Airdrop)](真实空投/) | 1.20.1 Forge 1.1.0-beta | 1.21.1 NeoForge | [jar](真实空投/dyairdrop-1.1.0.jar) |
| [稀薄的空气 (Thin Air)](稀薄的空气/) | 1.20.1 Forge（原 Thin Air / fuzs） | 1.21.1 NeoForge | [jar](稀薄的空气/thinair-1.21.1-neoforge-21.1.1-port.jar) |
| [僵尸游戏 (ZombieGame:Reborn)](僵尸游戏/) | 1.20.1 Forge 2.1.0 | 1.21.1 NeoForge | [jar](僵尸游戏/zombiegamereborn-2.1.0.jar) |
| [急救护理 (SelfAid)](急救护理/) | —（1.21.1 **新实现**，非移植） | 1.21.1 NeoForge | [jar](急救护理/selfaid-1.0.0.jar) |
| [绿葡萄护甲 (LesRaisins Armor)](绿葡萄护甲/) | 1.20.1 Forge 0.1.4.4 | 1.21.1 NeoForge | [jar](绿葡萄护甲/lrarmor-0.1.4.4.jar) |
| [CAF 生存核心 (CAF Survival Core)](CAF生存核心/) | 1.20.1 Forge（原 Tarkov Stamina / ChaosZ Pack） | 1.21.1 NeoForge | [jar](CAF生存核心/tarkov_stamina-1.21.1-neoforge-1.0.0-port.jar) |

## 说明

- **移植**条目均保持方块/物品注册 ID 与美术资源不变，只做 API 与数据包格式的版本适配。
- 每个模组文件夹里的 `README.md` 写明用法，`移植说明.md` 写明改动清单与验证结果。
- 构建环境：JDK 21 + Gradle 8.8+ + ModDevGradle 2.0.148 + NeoForge 21.1.255。

## 特别标注：非移植条目

### [铳械弹药统一 (Ammo Unify)](铳械弹药统一/) — 1.21.1 新实现

它不是移植，**没有对应的 1.20.1 成品 jar**。它是为 1.21.1 **从零实现**的
TaCZ（Timeless and Classics Zero）通用弹药模组，功能对标 1.20.1 的
Tacz-Unidict（TACZ：铳械协议）。后者依赖的 TaCZ 弹药机制在 1.1.8 被上游改掉，
无法直接移植，故改为新写。

- **前置**：TaCZ **1.1.8 系列**（Modrinth 项目 `tacz-1.21.1`）。**不要与 Tacz-Unidict 同时安装**。
- **许可**：AGPL-3.0-only。源码与许可随附在该文件夹内（`LICENSE`、`NOTICE.md`），
  分发时这两个文件必须保留。
- **验证状态**：已完成编译与静态校验（注入点经 `javap` 逐个核对），
  **尚未做运行时实机测试**。验证范围与首要风险项见其 `移植说明.md` 第四节。
- **素材**：该模组的贴图、Bedrock 模型与数据 JSON 全部由 `tools/generate_assets.py` 生成，
  不含 TaCZ 或 Tacz-Unidict 的任何美术资源。

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

### [真实空投 (Realistic Airdrop)](真实空投/) — 1.21.1 移植

从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar` 移植。运输机按设定高度与距离飞越玩家上空投下
补给箱，箱内按等级抽取战利品并可设密码锁，空投会吸引敌人前来抢夺；另配 6 把信号枪手动召唤空投。

- **前置**：GeckoLib for NeoForge 1.21.1 **4.x**（本仓库随附 `真实空投/libs/geckolib-neoforge-1.21.1-4.9.3.jar`）。
  **不装 GeckoLib 会直接加载失败**。
- **可选**：`zombiekit`（末日生存工具包）1.21.1 版。原模组自带 20 个引用它的掉落表，
  未安装时 `data/zombiekit/` 下这批表会解析失败（仅日志提示，不影响启动与 `dyairdrop` 自身功能）。
- **保留项**：方块 / 物品 / 实体 / 菜单的注册 ID 与 1.20.1 版完全一致，`assets/` 与原 jar 逐文件一致，
  `config/dyairdrop.toml` 的分区与配置项名也未变，旧配置文件可直接沿用。
- **服务器建议**：`Performance.forceload` 默认 `true`（会为飞机飞行路径强制加载区块），
  原作注释亦标「服务器慎用」，服务器上建议按需关闭。
- **验证状态**：编译通过（0 error），已用 NeoForge 21.1.255 开发环境专用服务器实测加载
  （`Done (3.219s)!`、0 条 ERROR）、开发环境客户端实测进入主菜单，模型与贴图 0 缺失。
  **玩法主流程（空投触发、密码面板、敌人抢夺、飞机飞行路径）与多人并发尚未实机验证**，
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
