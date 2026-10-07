# CAF草药 (Crop Expansion) — NeoForge 1.21.1

> **工程文件夹名 = `CAF草药`**，但 **mod_id 仍是 `crop_expansion`**、游戏内显示名仍是
> 「齿轮与腐肉作物扩展」—— mod_id 与资源路径必须是纯小写 ASCII，不跟着文件夹名改。

由 **1.20.1 Forge** 版移植而来（源工程：`mod制作\1.20.1forge\药草作物`）。
添加可种植的药草作物：像原版小麦一样种在耕地上、8 个生长阶段（age 0–7）、
可用骨粉催熟，成熟后收割得到草药；破坏草 / 高草丛有 5% 概率掉出种子。

## 内容

| 注册名 | 类型 | 说明 |
| --- | --- | --- |
| `crop_expansion:herb_crop` | 方块（8 个生长阶段） | 药草作物 |
| `crop_expansion:herb_seeds` | 物品（`ItemNameBlockItem`） | 草药种子，种在耕地上 |
| `crop_expansion:herbs` | 物品 | 草药，成熟收割的产物 |

## 构建

双击 `build.bat`（或在 PowerShell 里跑 `.\build.ps1`）。脚本会：

1. 读 `local.properties` 拿本机路径（JDK 21 / 共用 Gradle / 依赖缓存）
2. 用 `mod制作\通用\gradle-8.8` 构建
3. 把产出的 jar 复制到 `mod保存\1.21.1neoforge\CAF草药\`
4. 部署到测试实例的 `mods\`（`local.properties` 的 `deploy_mods`，留空则不部署）——
   会先删掉该目录里本 mod 的旧构建；若 jar 被运行中的游戏占用，只警告、不会让构建失败

命令行等价写法：

```
set JAVA_HOME=E:\Minecraft\jave\jdk-21_windows-x64_bin\jdk-21.0.3
<E:\Minecraft\开发者制作\mod制作\通用\gradle-8.8\bin\gradle.bat> ^
  -p <本工程目录> --no-daemon ^
  -g "E:\Minecraft\开发者制作\mod制作\通用\gradle-home" build
```

产物：`build\libs\crop_expansion-1.21.1-neoforge-<版本>.jar`

## 工具链

| 项 | 值 |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.227 |
| 构建插件 | ModDevGradle 2.0.107 |
| Java | 21 |

`21.1.227` 是 `mod制作\通用\gradle-home` 本地依赖缓存里**已有**的版本，所以能离线构建。

**测试实例**：`E:\Minecraft\PCL2\.minecraft\versions\移植mod测试`（MC 1.21.1 / **NeoForge 21.1.255**）。
编译用 21.1.227、运行用 21.1.255 —— 同一 21.1.x 小版本线内 ABI 兼容，且依赖区间
`versionRange="[21.1.0,)"` 满足；已逐个核对过编译期用到的 NeoForge / FML 类在 21.1.255
（与 loader 4.0.45、bus 8.0.5）里都存在。

## 目录

- `src/main/java/cn/blockforge/generated/cropexpansion/` — 主类、注册、作物方块、种子掉落
- `src/main/resources/assets/crop_expansion/` — 方块状态、模型、纹理、语言
- `src/main/resources/data/crop_expansion/loot_table/` — 战利品表
- `src/main/resources/META-INF/neoforge.mods.toml` — 模组元数据
- `local.properties` — 本机路径（换电脑 / 换实例只改这个文件）

## 移植要点（1.20.1 Forge → 1.21.1 NeoForge）

| 改动 | 原因 |
| --- | --- |
| `META-INF/mods.toml` → `META-INF/neoforge.mods.toml` | NeoForge 改了元数据文件名 |
| 依赖项 `mandatory=true` → `type="required"` | 依赖声明字段改名 |
| `FMLJavaModLoadingContext.get().getModEventBus()` → 构造器注入 `IEventBus` | 旧 API 已移除 |
| `DeferredRegister.create(ForgeRegistries.BLOCKS/ITEMS, …)` → `createBlocks()` / `createItems()` | `ForgeRegistries` 已移除，改用专用子类 |
| `RegistryObject<T>` → `DeferredBlock<T>` / `DeferredItem<T>` | 类型改名 |
| `@Mod.EventBusSubscriber(bus = …)` → `@EventBusSubscriber`（**不写 bus**） | NeoForge 21.1 起 `EventBusSubscriber.Bus` 已标记待删除（写了会有 [removal] 弃用警告），改为按事件是否实现 `IModBusEvent` 自动分流 |
| `Blocks.GRASS` → `Blocks.SHORT_GRASS` | 1.21 方块改名（注册名 `grass` → `short_grass`） |
| `CreativeModeTabs.NATURAL_BLOCKS` / `INGREDIENTS` → 自建 `ResourceKey` | 1.21 起这两个字段变成 `private` 且没有公开取值方法 |
| `data/…/loot_tables/` → `data/…/loot_table/` | 1.21 数据目录改成单数（**JSON 内容一字未改**，1.21.1 仍支持 `explosion_decay` 与 `binomial_with_bonus_count`） |
| `pack.mcmeta` 的 `pack_format` 15 → 34 | 1.21.1 的资源包格式号 |
| 新增 `HerbCropBlock.codec()` 覆盖 | 1.21 起 `BlockBehaviour.codec()` 是抽象的 |
| `gradle.properties` 里的中文写成 `\uXXXX` 转义 | Java Properties 按 ISO-8859-1 读取，直接写中文会变成乱码（`displayName="é½¿è½®ä¸…"`） |

## 已验证

- `gradle build` 干净通过、**零警告**（含 `clean`）
- 字节码 `major version 65`（Java 21）
- 反编译校验：`Blocks.SHORT_GRASS`、`Block.popResource`、`getTabKey()`/`accept()`、
  创造页 key `natural_blocks` / `ingredients` 均正确落在 class 里
- 23 个 `assets/` 文件与 1.20.1 版**逐文件哈希一致**；战利品表内容一致（仅目录名改单数）
- 已部署进测试实例 `移植mod测试\mods\`，三处（`build\libs` / `mod保存` / 实例 `mods`）**哈希一致**
- 依赖约束与实例实际版本逐条对得上：`neoforge [21.1.0,)` ⊂ 21.1.255、
  `minecraft [1.21.1,1.21.2)` ⊂ 1.21.1、`loaderVersion [4,)` ⊂ FML 4.0.45
