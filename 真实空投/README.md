# 真实空投 (Realistic Airdrop)

Minecraft **1.21.1 / NeoForge** 版。从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar` 移植而来。

一个「真实空投」模组：运输机会按设定高度与距离飞越玩家上空投下补给箱，
箱子里按等级抽取战利品，可设密码锁；空投会吸引敌人前来抢夺。
另配信号枪，可手动召唤武器 / 医疗 / 小型空投。

---

## 安装

1. 把 `dyairdrop-1.4.1.jar` 放进 `mods/`。
2. **前置**：GeckoLib for NeoForge 1.21.1（**4.x**）。本模组在 `neoforge.mods.toml` 里声明了对它的依赖，
   不装会直接加载失败；构建时从 GeckoLib 官方 Maven 拉取，仓库里不再附带 jar。
3. 可选：`zombiekit`（末日生存工具包）1.21.1 版。装了之后 `data/zombiekit/` 下的 20 个专属掉落表才会生效。

适用于 Minecraft 1.21.1 / NeoForge 21.1.150 以上。

---

## 玩法速览

| 内容 | 说明 |
|---|---|
| 全局空投 | 默认每 3 天一次（`gap`），距玩家 50~100 格，飞机高度 200，自动空投在 `minecraft:overworld` |
| 空投等级 | 默认 5 级（`maxlevel`），等级越高战利品越好 |
| 密码锁 | `enablelock` 开启后空投箱需密码解锁，输错按 `attemptpunishment` 扣血（1.2.0 起改为服务端权威校验） |
| 吸引敌人 | `enableenemies` 开启后敌人会在 `enemyarrivetime` 分钟内赶来，玩家离得太远空投会被抢走 |
| 信号枪 | 6 把枪（`flaregun0`~`flaregun5`），手持右键蓄力发射信号弹，按权重召唤武器 / 医疗 / 小型空投 |
| 保险箱 | `safe` / `safe_2` / `safeopen`，带密码面板 |

创造模式物品栏有独立页签 `dyairdrop`。

---

## 命令

| 命令 | 权限 | 说明 |
|---|---|---|
| `/setairdrop` | OP | 在指定坐标/存档召唤一次空投 |
| `/reairdrop` | OP | 重放上一次空投 |
| `/setairdroploot <等级> <战利品表>` | OP | 指定某个等级空投用的战利品表 |
| `/setairdropcode <密码>` | OP | 设置空投密码 |
| `/locatetag <结构标签>` | OP 4 | 定位指定结构标签的最近结构 |
| `/testcode` | OP | 调试用 |

---

## 配置

配置文件：`config/dyairdrop.toml`（COMMON 类型，服务端生效）。
**配置项名称与分区与 1.20.1 版完全一致，旧配置文件可以直接沿用。**

| 分区 | 主要项 |
|---|---|
| `worldevents` | `enable` `gap` `maxlevel` `enablelock` `drift` `availableworld` `attemptpunishment` |
| `chestevents` | `enableenemies` `enemyarrivetime` `airdropstolentime` `distance` `enemylist` |
| `Performance` | `forceload` `startposition` `height` `incomplete_block_destruction` |
| `FlaregunEvents` | `WeaponairdropWeight` `MedicalairdropWeight` `SmallairdropWeight` |
| `debug` | `debugmode` |
| `mapcompat` | `enableglobalcoordinates` |

> `Performance.forceload` 默认 `true`：会为飞机飞行路径强制加载区块，方便在未加载区域召唤空投，
> 但在服务器上可能与既有常加载区域冲突（原作注释亦提示「服务器慎用」）。服务器建议按需关闭。

---

## 从源码构建

需要 **JDK 21**。

```bash
./gradlew build        # 产物 → build/libs/dyairdrop-1.4.1.jar
./gradlew runServer    # 开发环境专用服务器
./gradlew runClient    # 开发环境客户端
```

| 组件 | 版本 |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.255（最低 21.1.150） |
| ModDevGradle | 2.0.148 |
| Gradle | 9.5.0 |
| Parchment | 2024.11.17 |
| GeckoLib | 4.9.3（编译期从官方 Maven 拉取） |

---

## 重构记录

改动清单、逐条对照、验证范围与尚未验证的风险项，见 [`重构说明.md`](重构说明.md)。

## 重构（已完成）

本模组正按 Java 最佳实践整体重写：分层架构、服务端权威、去掉 MCreator 的静态 procedure 与历史并列版本，
并专项修复 **密码面板与其它模组的冲突** 与 **Xaero 地图不显示空投** 两个问题。
兼容契约（注册 ID / NBT 键 / 配置键 / 战利品表路径全部不变）、诊断证据与阶段计划见
[`重构说明.md`](重构说明.md)；`dyairdrop-1.1.0-port-1to1` 标签保存着重构前的 1:1 版本。

### 1.4.1 修复：地图标记开关失效（实机复现后定位）

NeoForge 1.21.1 把实体持久化数据的 NBT 键从 `ForgeData` 改成了 `NeoForgeData`，且**只读新键**。
飞机/木箱原先用 `summon … {ForgeData:{dymap:1b}}` 传「要不要打地图标记」，于是这个开关永远写不进去，
表现为：信号枪提示「空投召唤成功」，但小地图/世界地图上永远不出现空投路点。

现改为**代码直接生成实体并直接写数据**（飞机、木箱、落地成箱都不再依赖命令），并在关键步骤打日志
（`[dyairdrop] 空投飞机已生成` / `空投木箱已投下` / `空投已落地成箱`），任何一步失败都能在日志里看到。
### 1.4.0：MCreator 过程层彻底消失

**原本 53 个 `*Procedure` 静态类、18,028 行、260 个类，现在 `procedures` 包已被整个删除**——所有逻辑按职责
收敛成一批服务类（`core/` 14 个 + `panel/` 4 个 + `compat/` 1 个），188 个类、13,000 余行：

| 原先的过程类 | 现在 | 说明 |
|---|---|---|
| `Flycode2neo` / `2neomap` / `3neo` / `3neomap`（349 行） | `core/FlightService` | 4 个历史版本航线合并，两个入口 |
| `Planeticks` + `Fancyplaneticks`（256 行） | `core/PlaneTicker` | 普通飞机与运输机共用一条主流程 |
| `Airdroplargeticks`（94 行，名字叫 large 却是 9 种箱子共用） | `core/ChestTicker` | 计时 / 信号烟 / 放敌人 / 判被抢走拆成 4 个方法 |
| `Mobairdropticks`（113 行） | `core/CrateTicker` | 落地成箱、写战利品表、登记地图标记 |
| `Flaregunlootset` + `Flareticks` + `Flareburst`（352 行） | `core/FlareService` | 烟花表原本写了两遍，现只有一份 |
| `Worldairdropevents` + `Randomworldairdrop` + `Fastairdrop` + `Getrandomplayer`（约 360 行） | `core/AirdropScheduler` | 定时空投 / `/airdrop` / `/airdrop world` |
| `Selectsummonposition`（77 行） | `core/EnemySpawner` | 候选点不再构造 4851 个 `double[]`，改 BlockPos + Fisher–Yates |
| `Buttonre1..6` + `Light1..6` + `Wrong1..6` + `PannelREticks` + `PannelREshut`（约 400 行） | `panel/LetterPanel` | 21 个类其实只是一张字母表 |
| `CheckProcedure`（375 行）+ `ChecknewliteProcedure`（339 行） | `panel/LetterPanelConfirm` | 各自去掉 6 份复制粘贴，合并为一个类两个方法 |
| `Safetest` + `Safeopen2` + `Randomstring` + `AirdropGUIopen` + `Fancyairdropguiopen` + `Lockedairdroplargedebug`（约 420 行） | `core/PanelOpener` | 匿名 MenuProvider 原来复制了 10 遍 |
| `T6` + `FindNearestStructure`（48 行） | `core/StructureLocator` | 顺带修掉「找不到结构时 NPE」 |
| `Unlockflaregun`（41 行） | `compat/zombiekit/ZombieKitCompat` | zombiekit 判断集中一处 |
| `Opshow` / `Tests` / `Setairdropcode` 等 | `core/GameModes` / 命令类内联 | 纯转调的壳类直接删除 |

新增的工具层：`Nbt`（方块实体读写，值未变则不发包）、`Commands`（命令执行，全模块只剩 2 处
`performPrefixedCommand`）、`Vars`、`Sounds`（含音源重载）、`Chat`（广播 / 私聊）、`Blocks`（朝向 / id / 动画）、
`Numbers`、`CommandArgs`、`GameModes`、`Projectiles`。

**顺带修掉的原实现 bug**（都写进了对应类的 Javadoc）：`/setairdropcode` 的 `blockid` 参数被丢弃；
`/locatetag` 找不到结构时 NPE；信号弹失败提示把语言键当文本显示；地图标记落地约 1 秒后被误删。

**保留未改的历史写法**（属玩法数值，已在 Javadoc 注明）：信号枪权重归一化复用被改写过的分母；
RE2 面板生成密码后无条件广播（RE 只在创造模式广播）。

### 1.3.0 已完成的改动（代码质量专项）

- **消灭 MCreator 生成物**：清除全部 70 处 `new Object() { ... }` 匿名类惯用法（每次调用都 new 一个实例，
  在 tick 路径上是纯垃圾对象）、41 处 `CommandSourceStack + performPrefixedCommand` 样板、
  123 处 `((PlayerVariables) x.getData(...))` 强制转换、787 条无用 import。
- **整包迁移**：`net.mcreator.dyairdrop` → `net.gem19910816.dyairdrop`（210 个文件），
  MCreator 的 `*Procedure` 静态工具类不再散落在 `net.mcreator` 命名空间下。
- **清理**：删除 45 个死代码 / 旧实现文件（含确认无调用点的 `ChecknewProcedure`、不可达的 TestGUI2 面板链路、
  6 个无引用的航线历史版本、15 个已并入面板服务的展示判定类），随后又删掉约 30 个零调用方的过程类。

### 1.2.0 已完成的改动

- **密码面板改为服务端权威**：客户端只发「动作 + 文本」（`panel_action` 包），服务端校验
  （面板类型与坐标一致、8 格距离、动作频率）后执行；删除「客户端本地再跑一遍服务端逻辑」、
  静态 `guistate`、每 tick 强制关容器、ESC 硬编码。**解锁逻辑此前在服务端恒不可达（服务端拿不到输入串），
  现已真正生效** —— 这是与其它模组冲突、以及多人下开不了箱的共同根因。
- **Xaero 地图标记真正可用**：原实现执行的服务端命令 `addwaypointxaero` 并不存在（全实例无人提供，
  Xaero 只有聊天前缀 `xaero-waypoint:`），且被 `withSuppressedOutput` 吞掉报错，属永久静默失败的死代码。
  现改为服务端维护标记 + `map_marker` 包下发给装了 Xaero 的玩家，客户端**反射调用 Xaero 26.5.0 / 1.46.0
  实测签名**加入**临时路点**（小地图与世界地图共用），空投箱被搜空或被移除时自动回收；
  颜色按空投类型区分（大型金 / 医疗红 / 武器暗红 / 小型绿）。
- **性能**：面板渲染每帧只读一次玩家数据与方块实体 NBT（原为 15 次 procedure 调用、每次各读一遍）；
  「空投被抢走」判定中半径可达 200 格的每 tick AABB 实体查询限流到每秒一次。
- **清理**：删除 45 个死代码 / 旧实现文件（含确认无调用点的 `ChecknewProcedure`、不可达的 TestGUI2 面板链路、
  6 个无引用的航线历史版本、15 个已并入面板服务的展示判定类）。

---

## 许可

**MIT**（全文见 [`LICENSE`](LICENSE)）。

原模组 dyairdrop（1.20.1 Forge 版，作者 **Ian**，MCreator 制作）的著作权人已明确将本项目
**完整开源授权**给 **gem19910816** 接手；1.21.1 / NeoForge 版本的移植、重构与后续维护均由
gem19910816 负责。因此本模组已从原先的「All Rights Reserved（沿用原模组）」改为 MIT 全开源，
任何人都可以自由使用、修改、再分发（保留版权与许可声明即可）。
