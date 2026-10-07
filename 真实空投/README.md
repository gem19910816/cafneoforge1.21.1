# 真实空投 (Realistic Airdrop)

Minecraft **1.21.1 / NeoForge** 版。从 1.20.1 Forge 版 `dyairdrop-1.1.0-1.20.1-beta.jar` 移植而来。

一个「真实空投」模组：运输机会按设定高度与距离飞越玩家上空投下补给箱，
箱子里按等级抽取战利品，可设密码锁；空投会吸引敌人前来抢夺。
另配信号枪，可手动召唤武器 / 医疗 / 小型空投。

---

## 安装

1. 把 `dyairdrop-1.1.0.jar` 放进 `mods/`。
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
| 密码锁 | `enablelock` 开启后空投箱需密码解锁，输错按 `attemptpunishment` 扣血 |
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
./gradlew build        # 产物 → build/libs/dyairdrop-1.1.0.jar
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

---

## 许可

**MIT**（全文见 [`LICENSE`](LICENSE)）。

原模组 dyairdrop（1.20.1 Forge 版，作者 **Ian**，MCreator 制作）的著作权人已明确将本项目
**完整开源授权**给 **gem19910816** 接手；1.21.1 / NeoForge 版本的移植、重构与后续维护均由
gem19910816 负责。因此本模组已从原先的「All Rights Reserved（沿用原模组）」改为 MIT 全开源，
任何人都可以自由使用、修改、再分发（保留版权与许可声明即可）。
