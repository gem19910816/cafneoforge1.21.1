# 真实空投 (Realistic Airdrop)

Minecraft **1.21.1 / NeoForge** 版。从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar` 移植而来。

一个「真实空投」模组：运输机会按设定高度与距离飞越玩家上空投下补给箱，
箱子里按等级抽取战利品，可设密码锁；空投会吸引敌人前来抢夺。
另配信号枪，可手动召唤武器 / 医疗 / 小型空投。

---

## 安装

1. 把 `dyairdrop-1.3.0.jar` 放进 `mods/`。
2. **前置**：GeckoLib for NeoForge 1.21.1（**4.x**，本仓库附带 `libs/geckolib-neoforge-1.21.1-4.9.3.jar`）。
   不装 GeckoLib 会直接加载失败。
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
./gradlew build        # 产物 → build/libs/dyairdrop-1.3.0.jar
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
| GeckoLib | 4.9.3（`libs/` 内置） |

---

## 移植说明

改动清单、验证范围与尚未验证的风险项，见 [`移植说明.md`](移植说明.md)。

## 重构（进行中）

本模组正按 Java 最佳实践整体重写：分层架构、服务端权威、去掉 MCreator 的静态 procedure 与历史并列版本，
并专项修复 **密码面板与其它模组的冲突** 与 **Xaero 地图不显示空投** 两个问题。
兼容契约（注册 ID / NBT 键 / 配置键 / 战利品表路径全部不变）、诊断证据与阶段计划见
[`重构说明.md`](重构说明.md)；`dyairdrop-1.1.0-port-1to1` 标签保存着重构前的 1:1 版本。

### 1.3.0 已完成的改动（代码质量专项）

在 1.2.0 的功能修复之上，把整个模块按 Java 最佳实践重写了一遍（**玩法机制、注册 ID、NBT 键、配置键全部未动**）：

- **消灭 MCreator 生成物**：清除全部 70 处 `new Object() { ... }` 匿名类惯用法（每次调用都 new 一个实例，
  在 tick 路径上是纯垃圾对象）、41 处 `CommandSourceStack + performPrefixedCommand` 样板、
  123 处 `((PlayerVariables) x.getData(...))` 强制转换、787 条无用 import。
- **新增 `core` 工具层**：`Nbt`（方块实体读写，写入时值未变则不发包）、`Commands`（虚拟命令源 / 以实体为上下文执行命令）、
  `Vars`（玩家数据）、`Sounds`、`Blocks`、`Numbers`、`CommandArgs`、`GameModes`、`Projectiles`。
- **结构合并（P3）**：
  - 4 个航线过程（`Flycode2neo / 2neomap / 3neo / 3neomap`，349 行）→ `core/FlightService`（两个入口）；
  - 飞机与运输机的每 tick 逻辑（`Planeticks` + `Fancyplaneticks`，256 行）→ `core/PlaneTicker`
    （NBT 由每 tick 反复读 8+ 次改为只读一次、只写回一次）；
  - `CheckProcedure` 375 → 128 行、`ChecknewliteProcedure` 339 → 82 行（各自去掉 6 份复制粘贴的打字机动画）；
  - 整包从 `net.mcreator.dyairdrop` 迁到 `net.gem19910816.dyairdrop`（210 个文件）。
- **顺手修掉的原实现 bug**：`/setairdropcode` 的第二个参数 `blockid` 被丢弃（旧代码把 `loot` 写进了 `airdropblock`，
  而那是信号枪决定召唤哪种空投箱时真正读取的字段）。
- **主动统一的历史分叉**（都写在对应类的 Javadoc 里）：飞机音效统一为距离受限的版本；
  `driftmax < driftmin` 时按 min/max 取界（原来把上下界都写成了 `driftmin`）。
- 源码规模：260 个类 / 18,028 行 → **224 个类**，删除 45 个死代码文件与 4 个重复过程。

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
