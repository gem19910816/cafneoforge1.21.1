# 真实空投 (Realistic Airdrop)

Minecraft **1.21.1 / NeoForge** 版。从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar` **1:1 移植**而来。

一个「真实空投」模组：运输机会按设定高度与距离飞越玩家上空投下补给箱，
箱子里按等级抽取战利品，可设密码锁；空投会吸引敌人前来抢夺。
另配信号枪，可手动召唤武器 / 医疗 / 小型空投。

---

## 安装

1. 把 `dyairdrop-1.1.0.jar` 放进 `mods/`。
2. **前置**：GeckoLib for NeoForge 1.21.1 **4.9.3 或更高**（构建期从 GeckoLib 官方 Maven 拉取，
   本仓库不再附带 geckolib 的 jar）。**不装 GeckoLib 会直接加载失败。**
3. 可选：`zombiekit`（末日生存工具包）1.21.1 版。本模组自带 20 个引用它的掉落表，
   未安装时这 20 张表会解析失败（仅日志提示，不影响启动与 `dyairdrop` 自身功能）。

适用于 Minecraft 1.21.1 / NeoForge 21.1.150 以上（本仓库按 21.1.255 构建与验证）。

---

## 玩法速览

| 内容 | 说明 |
|---|---|
| 全局空投 | 默认每 10 天一次（`gap`），距玩家 50~100 格，飞机高度 200，默认只在 `minecraft:overworld` 触发 |
| 空投等级 | 默认 5 级（`maxlevel`），等级越高战利品越好 |
| 密码锁 | `enablelock` 开启后空投箱需密码解锁，输错按 `attemptpunishment` 扣血 |
| 吸引敌人 | `enableenemies` 开启后敌人会在 `enemyarrivetime` 分钟内赶来，玩家离得太远空投会被抢走 |
| 信号枪 | 6 把枪（`flaregun0`~`flaregun5`），手持右键蓄力发射信号弹，按权重召唤武器 / 医疗 / 小型空投 |
| 保险箱 | `safe` / `safe_2` / `safeopen`，带密码面板 |
| 小地图标记 | 带地图标记的空投落地时，会向玩家发送一条 Xaero 路径点消息，**只装 Xaero 小地图即可点一下加路点** |

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
**分区与配置项名称与 1.20.1 版完全一致，旧配置文件可以直接沿用。**

| 分区 | 主要项 |
|---|---|
| `worldevents` | `enable` `gap` `maxlevel` `enablelock` `drift` `availableworld` `attemptpunishment` |
| `chestevents` | `enableenemies` `enemyarrivetime` `airdropstolentime` `distance` `enemylist` |
| `Performance` | `forceload` `startposition` `height` `incomplete_block_destruction` |
| `FlaregunEvents` | `WeaponairdropWeight` `MedicalairdropWeight` `SmallairdropWeight` |
| `debug` | `debugmode` |
| `mapcompat` | `enableglobalcoordinates` |

---

## 兼容性

- 方块 / 物品 / 实体 / 菜单 / 音效的**注册 ID 与 1.20.1 版完全一致**，
  `assets/` 全部原样保留（贴图、模型、geo、动画、音效、语言文件逐字节未改），
  旧存档、投影、蓝图可继续使用。
- 数据包目录已按 1.21 规范单数化（`loot_table` / `recipe` / `advancement` / `tags/block`），
  配方 `result.item` 与战利品表条目 `name` 已改成 1.21 的写法。
- 本次移植修掉了原版的两个 BUG（密码面板在专用服务器上不可用、小地图标记永远不出现），
  详见 [`移植说明.md`](移植说明.md)。

---

## 从源码构建

```powershell
cd 真实空投
.\gradlew build          # 产物在 build/libs/dyairdrop-1.1.0.jar
.\gradlew runServer      # 起一个带本模组的开发环境服务端
```

需要 JDK 21、Gradle 8.8+、ModDevGradle 2.0.148、NeoForge 21.1.255（见 `gradle.properties`），
并且首次构建要联网下载 NeoForge 与 GeckoLib。

---

## 许可

原模组 `dyairdrop`（作者 Ian，MCreator 制作）著作权人已将本项目完整开源授权给 gem19910816 接手维护，
1.21.1 / NeoForge 版本的移植与后续开发由 gem19910816 负责。
详见 [`移植说明.md`](移植说明.md) 第一节与仓库根 `README.md`。
