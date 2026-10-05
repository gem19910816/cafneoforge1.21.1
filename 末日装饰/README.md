# 末日装饰 (Doomsday Decoration) — 1.21.1 NeoForge

由 1.20.1 Forge 版 `doomsday_decoration-1.1.3-forge-1.20.1.jar` 移植而来，**内容与原版一致**。

| 项目 | 说明 |
|---|---|
| 支持版本 | Minecraft **1.21.1** / NeoForge **21.1.255** |
| Java | 21 |
| 模组 ID | `doomsday_decoration` |
| 内容 | 1143 个方块、1144 个物品、3 个创造标签页、1 个方块实体（板条箱）、5 个音效 |
| 数据 | 1143 张战利品表、8 个方块标签 |
| 前置 | 无 |

## 直接使用

下载本文件夹里的 `doomsday_decoration-1.1.3-neoforge-1.21.1.jar`，丢进 `.minecraft/mods/` 即可。

方块/物品的注册 ID 与 1.20.1 版完全相同（例如 `doomsday_decoration:blackandwhiteceramictiles`），
贴图、模型、blockstate、语言文件、音效文件均为原样搬移，旧存档与建造蓝图可直接继续使用。

## 自己编译

```bash
# 需要 JDK 21、Gradle 8.8+
gradle build        # 产物在 build/libs/
```

使用 ModDevGradle 2.0.148 拉取 NeoForge 21.1.255。

## 移植要点

- 1.20.1 的 `official` 命名空间是「Mojang 类名 + SRG 成员名」，已全部还原为 1.21.1 的 Mojang 名字（153 个成员）。
- 阶梯 / 门 / 活板门 / 栅栏门 / 按钮 / 压力板 的原版构造函数在 1.21.1 有签名变化，已逐个适配。
- 1.21.1 把 `BlockBehaviour#use` 拆成了 `useItemOn` / `useWithoutItem`，右击音效方块已改用 `useItemOn`。
- `RegistryObject` → `DeferredHolder`；Forge 的 `LazyOptional` 能力系统 → `RegisterCapabilitiesEvent` 注册 `Capabilities.ItemHandler.BLOCK`。
- 数据包目录按 1.21 规范单数化：`loot_tables/` → `loot_table/`，`tags/blocks/` → `tags/block/`。

详细清单见 [移植说明.md](移植说明.md)。

## 验证情况

- javac 21 编译 1154 个源文件 **0 错误**。
- 用官方 NeoForge 21.1.255 专用服务器实机加载：模组正常加载，日志中与本模组相关的 ERROR/WARN 为 **0**。
- 数据包断言测试（`DD_OK` 为通过）：阶梯/台阶/墙/门/活板门/栅栏/栏杆/栅栏门/按钮/压力板/普通方块全部放置成功，
  方块实体创建与 ID 正确，战利品表可掉落，方块标签生效。
- 资源静态校验：1143 个 blockstate ↔ 1143 个注册方块一一对应，模型/贴图 **0 缺失、0 悬空引用**。

## 原模组自带的问题（未改动，保持原样）

1. `blood` / `blood_2` … `blood_6` 六个模型的贴图路径为空（原 jar 即如此），游戏会记一条无效贴图警告。
2. 板条箱 `acrate` 的 GUI 是空的 27 格箱子而非它自己的 9 格库存（原模组行为相同）。
   修复：`ChestMenu.threeRows(id, inventory, this)`。

## 许可

与原模组保持一致（未标注）。移植仅做版本适配，未修改任何美术资源。
