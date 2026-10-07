# 绿葡萄护甲 (LesRaisins Armor)

Minecraft **1.21.1 / NeoForge** 版，从 1.20.1 Forge 版
[LesRaisinsArmor](https://github.com/LesRaisins-Studios/LesRaisinsArmor) `0.1.4.4` 移植而来。模组 id `lrarmor`。

**数据包驱动的护甲模组**：护甲属性（防御 / 韧性 / 击退抗性 / 耐久）、套装效果、渲染配置都由
`data/lrarmor/armor_data/*.json` 定义，配 GeckoLib 基岩模型渲染；创造模式标签「绿葡萄护甲」。

## 安装

1. 把 `lrarmor-0.1.4.4.jar` 放进 `mods/`。
2. **前置**：GeckoLib for NeoForge 1.21.1 **4.x**（`neoforge.mods.toml` 里是 required，缺了直接加载失败）。
3. 适用于 Minecraft 1.21.1 / NeoForge 21.1.x。

## 玩法速览

- **16 套护甲、64 件物品**，名字与贴图沿用原作：
  防化服、装甲防化服、突击者、防御者、勘察者、狙击者、创伤小组、
  ATF 执勤队 / ATF 特别组装甲、IRS 执勤队、DEA 执勤队 / DEA 特别组装甲、
  FBI 执勤队 / FBI 特别组装甲、「愚者」组织 / 「愚者」组织作战。
- **套装效果**：`suit.lrarmor.*` 共 16 套，效果包括特制减震夹层、超重型全身装甲、轻量化腿部支撑、便携自愈设备。
- **数据驱动**：16 个 `armor_data/*.json` + 62 个配方，改 JSON 即可改数值，不用重编模组。
- **部件隐藏**：`armor_display/` 下 9 个配置控制第一人称 / 视角下隐藏的身体部件。
- **Mixin**：`EffectInstanceMixin` 把套装效果标记为无限持续，避免显示计时。
- 无命令。

## 配置

`config/lrarmor-common.toml`（COMMON 类型）：

- `enableArmorSetEffect` 是否启用套装效果
- `enableArmorAttribute` 是否启用护甲属性加成

## 从源码构建

```
./gradlew build      # 需要 JDK 21，产物 build/libs/lrarmor-0.1.4.4.jar
```

本仓库**不含** `libs/geckolib-neoforge-1.21.1-4.9.3.jar`（`build.gradle` 用 `flatDir libs/` 引用它做编译）。
重新编译前请把 GeckoLib 4.9.3（NeoForge 1.21.1 版）放进 `libs/`。
本目录的 `gradle.properties` 已去掉原作者机器专用的 `org.gradle.java.home` / `installations.paths` 两行。

## 已知差异

- **EpicFight 兼容未迁移**：原模组的 `EpicFightCompat`（监听 `PrepareModelEvent`）未包含在移植版内。
- **JEI 兼容未迁移**：原项目只在 `build.gradle` 引用，代码里并未使用。
- 客户端只在 `RenderLivingEvent` 中隐藏玩家部件；若数据包改了 `armor_display` JSON，需要重进世界或重启客户端。

## 许可

All Rights Reserved（沿用原作）。原作仓库：https://github.com/LesRaisins-Studios/LesRaisinsArmor

移植改动清单与验证状态见 `移植说明.md`。
