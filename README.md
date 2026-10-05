# cafneoforge1.21.1

齿轮与腐肉整合包（CAF）的 **Minecraft 1.21.1 / NeoForge** 模组合集。
一个模组一个文件夹，文件夹内附源码与可直接使用的成品 jar。

| 模组 | 原版本 | 目标平台 | 成品 |
|---|---|---|---|
| [末日装饰 (Doomsday Decoration)](末日装饰/) | 1.20.1 Forge 1.1.3 | 1.21.1 NeoForge | [jar](末日装饰/doomsday_decoration-1.1.3-neoforge-1.21.1.jar) |
| [铳械弹药统一 (Ammo Unify)](铳械弹药统一/) | —（1.21.1 **新实现**，非移植） | 1.21.1 NeoForge | [jar](铳械弹药统一/ammo_unify-1.0.0.jar) |

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
