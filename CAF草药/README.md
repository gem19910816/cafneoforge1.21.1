# CAF草药 (Crop Expansion) — NeoForge 1.21.1

> **工程文件夹名 = `CAF草药`**（英文名 `CAF_Herbs`），但 **mod_id 仍是 `crop_expansion`**、
> 游戏内显示名仍是「齿轮与腐肉作物扩展」—— mod_id 与资源路径必须是纯小写 ASCII，不跟工程名走。

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

### 前置

| 项 | 要求 |
| --- | --- |
| JDK | **21**，并让环境变量 `JAVA_HOME` 指向它 |
| 网络 | **首次构建需要联网**：`gradlew` 要下载 Gradle 8.8 发行版，ModDevGradle 要拉取 Minecraft / NeoForge 依赖；之后可离线构建 |
| 镜像 | 发行版地址已改用**腾讯云公共镜像**（见 `gradle/wrapper/gradle-wrapper.properties`）—— 默认的 `services.gradle.org` 在部分网络**实测连不通**（超时）。要换回官方源，把那一行改回 `https\://services.gradle.org/distributions/gradle-8.8-bin.zip` 即可 |
| 可选 | 若本机已有共享依赖缓存，可设环境变量 `GRADLE_USER_HOME` 指向它，避免重复下载 |

### 命令

```
gradlew.bat build            :: 或在 PowerShell 里： .\build.ps1
```

双击 `build.bat` 等价于 `gradlew.bat build`。
`build.bat` / `build.ps1` 只是 wrapper 的便捷封装，**不含任何本机绝对路径，也不把 jar 复制到工程之外**。

产物：`build\libs\[CAF草药]CAF_Herbs-1.21.1-neoforge-<主.次.修订>.jar`

jar 名 = 中文前缀 `[CAF草药]` + 英文名 `CAF_Herbs` + MC 版本 + 加载器 + 版本号。
前缀里的中文写在 `gradle.properties` 的 `mod_archive_prefix`（`\uXXXX` 转义），
**不写进 `build.gradle`** —— Gradle 脚本按平台默认编码读取，中文在非简体中文环境下会被误解码。
名字里含纯 ASCII 段 `CAF_Herbs-…`，满足规范 3.2.2（纯中文名会让启动器提取出空模块名而崩溃）。

版本号 = `主.次`（`gradle.properties` 的 `mod_version`）+ `修订`（`build_number.properties`），
**每次构建成功后修订位自动 +1**（规范 5.1）。

> 归档到 `mod保存\{版本及加载器}\CAF草药\`、以及安装进测试实例的 `mods\`，
> 按规范由 **AI 手动**完成，不写进任何构建文件（规范 4.3 / 5.2）。

## 工具链

| 项 | 值 |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.227 |
| 构建插件 | ModDevGradle 2.0.107 |
| Gradle | 8.8（工程内 wrapper） |
| Java | 21 |

选 `21.1.227` 是因为它是本机共享依赖缓存里**已有**的版本，可以离线构建。

本机测试实例跑的是 **NeoForge 21.1.255** / Minecraft 1.21.1。编译用 21.1.227、运行用 21.1.255
—— 同一 21.1.x 小版本线内 ABI 兼容，且依赖区间 `versionRange="[21.1.0,)"` 满足；已逐个核对过
编译期用到的 NeoForge / FML 类在 21.1.255（与 loader 4.0.45、bus 8.0.5）里都存在。

## 目录

- `src/main/java/cn/blockforge/generated/cropexpansion/` — 主类、注册、作物方块、种子掉落
- `src/main/resources/assets/crop_expansion/` — 方块状态、模型、纹理、语言
- `src/main/resources/data/crop_expansion/loot_table/` — 战利品表
- `src/main/resources/META-INF/neoforge.mods.toml` — 模组元数据
- `tools/make_models.py` — 专属脚本：生成 8 个生长阶段的方块模型
- `gradlew` / `gradlew.bat` / `gradle/wrapper/` — 构建入口
- `build.bat` / `build.ps1` — wrapper 的便捷封装

## 移植要点（1.20.1 Forge → 1.21.1 NeoForge）

| 改动 | 原因 |
| --- | --- |
| `META-INF/mods.toml` → `META-INF/neoforge.mods.toml` | NeoForge 改了元数据文件名 |
| 依赖项 `mandatory=true` → `type="required"` | 依赖声明字段改名 |
| `FMLJavaModLoadingContext.get().getModEventBus()` → 构造器注入 `IEventBus` | 旧 API 已移除 |
| `DeferredRegister.create(ForgeRegistries.BLOCKS/ITEMS, …)` → `createBlocks()` / `createItems()` | `ForgeRegistries` 已移除，改用专用子类 |
| `RegistryObject<T>` → `DeferredBlock<T>` / `DeferredItem<T>` | 类型改名 |
| `@Mod.EventBusSubscriber(bus = …)` → `@EventBusSubscriber`（**不写 bus**） | NeoForge 21.1 起 `EventBusSubscriber.Bus` 已标记待删除（写了会有 `[removal]` 弃用警告），改为按事件是否实现 `IModBusEvent` 自动分流 |
| `Blocks.GRASS` → `Blocks.SHORT_GRASS` | 1.21 方块改名（注册名 `grass` → `short_grass`） |
| `CreativeModeTabs.NATURAL_BLOCKS` / `INGREDIENTS` → 自建 `ResourceKey` | 1.21 起这两个字段变成 `private` 且没有公开取值方法 |
| `data/…/loot_tables/` → `data/…/loot_table/` | 1.21 数据目录改成单数（**JSON 内容一字未改**，1.21.1 仍支持 `explosion_decay` 与 `binomial_with_bonus_count`） |
| `pack.mcmeta` 的 `pack_format` 15 → 34 | 1.21.1 的资源包格式号 |
| 新增 `HerbCropBlock.codec()` 覆盖 | 1.21 起 `BlockBehaviour.codec()` 是抽象的 |
| `gradle.properties` 里的中文写成 `\uXXXX` 转义 | Java Properties 按 ISO-8859-1 读取，直接写中文会变成乱码（`displayName="é½¿è½®ä¸…"`） |

## 规范合规（《文件夹结构规范以及部分规定指南》v2）

| 条款 | 做法 |
| --- | --- |
| 4.1 三样必备 | 构建入口 = 工程内 `gradlew` + `gradle/wrapper/`；`README.md`；`.gitignore` |
| 4.1 `.gitignore` 最低清单 | `build/` `out/` `bin/` `.gradle/` `run/` `logs/` `*.log` `*.class` `local.properties` `.idea/` `*.iml` `.vscode/` `.settings/` `.project` `.classpath` |
| 4.2 禁止本机绝对路径 | 工程内零绝对路径；Java 版本改用 `java.toolchain` 声明，不再写 `installations.paths` |
| 4.3 构建文件不写工程外路径 | 构建脚本只产出 `build\libs\`；`mod保存` 与实例安装由 AI 手动做 |
| 4.5 联网策略 | 优先离线；README 已写明首次构建需要联网 |
| 4.6 编码 | `.bat` = GBK 兼容（纯 ASCII）+ CRLF；其余 UTF-8 无 BOM |
| 5.1 版本号自增 | `build_number.properties`，构建成功后修订位 +1 |
| 3.1.3 英文名 | `archivesName` 用 `CAF_Herbs`（= 中文工程名「CAF草药」的英文翻译），前缀 `[CAF草药]` 由构建产出，不靠手动改名 |
| 3.2.2 jar 名含 ASCII 段 | `[CAF草药]CAF_Herbs-1.21.1-neoforge-<版本>.jar` |
| 3.2.3 不手动改 jar 名 | 名字完全由构建流程产出 |
| 许可 / 署名 | `license="MIT"`、`authors="YuSheng-hina"`，与工程内 `LICENSE` 文件一致 |

## 已验证

- `gradle build` 干净通过、**零警告**（含 `clean`）
- 字节码 `major version 65`（Java 21）
- 反编译校验：`Blocks.SHORT_GRASS`、`Block.popResource`、`getTabKey()`/`accept()`、
  创造页 key `natural_blocks` / `ingredients` 均正确落在 class 里
- 23 个 `assets/` 文件与 1.20.1 版**逐文件哈希一致**；战利品表内容一致（仅目录名改单数）
- 已（手动）安装进本机 1.21.1 NeoForge 测试实例的 `mods\`，`build\libs` / `mod保存` / 实例 `mods` 三处**哈希一致**
- 依赖约束与实例实际版本逐条对得上：`neoforge [21.1.0,)` ⊂ 21.1.255、
  `minecraft [1.21.1,1.21.2)` ⊂ 1.21.1、`loaderVersion [4,)` ⊂ FML 4.0.45
