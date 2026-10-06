# 稀薄的空气 (Thin Air) 1.21.1 NeoForge 移植

工程位置：`开发者制作\mod制作\1.21.1neoforge\稀薄的空气`（按文件夹结构规范，mod制作/mod保存 下按游戏版本+加载器分目录）

从 1.20.1 Forge 源码（含 1.20.4 分支）移植到 **Minecraft 1.21.1 + NeoForge 21.1.227**。

## 产物

- `build/libs/thinair-1.21.1-neoforge-21.1.1-port.jar` —— 成品 mod（同时复制到 `mod保存\1.21.1neoforge\稀薄的空气\`）
- **移植版声明**（mod制作约定）：`neoforge.mods.toml` 中 displayName 为 `Thin Air (移植版)`，description 首行标注 `[社区移植版] 由 1.20.1 Forge 版移植至 1.21.1 NeoForge`，版本号带 `-port` 后缀
- 运行必需依赖：**Puzzles Lib ≥ 21.1.x**（随包附了 21.1.62 的 jar 副本），Curios 为可选依赖
- 测试实例：thinair + puzzleslib 已装入 `E:\Minecraft\PCL2\.minecraft\versions\移植mod测试\mods\`（该实例为 NeoForge 21.1.255，与构建目标 21.1.227 同大版本兼容）

## 构建

双击或命令行运行 `build.bat <任务>`（内部使用 `通用\gradle-8.8` + `通用\gradle-home` 共享缓存 + `jave\jdk-21.0.3`）：

- `build.bat build` —— 编译并打 jar
- `build.bat runData` —— datagen（生成模型/配方/进度/战利品表/标签/en_us 语言到 `src/main/generated/`）
- `build.bat runClient` / `runServer` —— 开发环境启动客户端/服务器（游戏目录在 `run\`，与 PCL2 实例无关）

## 工程结构说明

单 Gradle 模块（ModDevGradle 2.0.107）。原模板是 Common/NeoForge 多模块多加载器结构；
本次目标只有 NeoForge，故把 Common 与 NeoForge 源码合并进同一 sourceSet，
`NeoForgeModRegistry` 的 air_bladder 注册并入了 `ModRegistry`（去掉 registerLazily 间接层）。
Puzzles Lib / Curios 以 `libs\` 下的 jar 作为编译依赖（flatDir），Puzzles Lib 运行时单独安装、不打进 jar。

## 主要移植改动（1.20.4 → 1.21.1）

| 位置 | 改动 |
| --- | --- |
| 全局 | `new ResourceLocation(...)` → `ResourceLocation.fromNamespaceAndPath/withDefaultNamespace` |
| ThinAir | `NetworkHandlerV3` → `NetworkHandler`；`LootTableLoadEvents.Modify` 回调去掉 LootDataManager 参数；`ContentRegistrationFlags.COPY_TAG_RECIPES` → `COPY_RECIPES`；战利品表 id 改用 `ResourceKey<LootTable>`（`RegistryManager.registerLootTable`），注入用 `NestedLootTable.lootTableReference` |
| ModRegistry | 1.21 盔甲材料改为注册表项：直接用原版 `ArmorMaterial` record + `registerArmorMaterial(String, Supplier)`，数值沿用 20.4 默认（防御全 1、附魔 0、韧性 0、皮革音效、修复物木炭、层贴图 `thinair:respirator`）；新增数据组件 `air_quality_level`（替代 1.20.4 的 item NBT） |
| SafetyLanternBlock | `use(hand,...)` → `useWithoutItem(...)`（1.20.5 起移除 hand 参数）；`getOrCreateTag` → 数据组件；`hurtAndBreak` 改 EquipmentSlot 参数 |
| AirQualityLevel | `EnchantmentHelper.getRespiration` → `getEnchantmentLevel(holder)`（1.21 附魔数据驱动化，RESPIRATION 是 ResourceKey，经 registryAccess 解析） |
| AirBladderItem | `getUseDuration(stack)` → `(stack, entity)`；`ItemStack.hurt(int, RandomSource, ServerPlayer)` → `hurtAndBreak(int, entity, EquipmentSlot)` |
| 触发器 ×3 | `ExtraCodecs.strictOptionalField` 移除 → `Codec.optionalFieldOf`；`ItemPredicate.matches` → `test` |
| capability | `CapabilityComponent.write/read` 增加 `HolderLookup.Provider` 参数；blockPos 手动按 X/Y/Z 读写（保持旧存档 NBT 兼容） |
| 客户端 | `AddReloadListenersContext` 在 21.1 已移除 → 呼吸器模型改为首次渲染时惰性烘焙；`getArmorFoilBuffer` 改 3 参；`renderToBuffer` 颜色改 int；盔甲贴图路径格式 1.21 未变（`textures/models/armor/respirator_layer_1.png`），无需移动 |
| NeoForge 侧 | `@Mod.EventBusSubscriber` → 顶层 `@EventBusSubscriber`（保留 bus 参数，仅过时警告）；配置注册改为构造注入 `ModContainer.registerConfig` |
| Curios | `getCuriosInventory` 返回值由 LazyOptional 改为直接 Optional；废弃的 `curios/entities` 数据文件已删（1.21.1 Curios 9.x 用 `curios:head` 物品标签注册） |
| datagen | `CopyTagShapelessRecipeBuilder` → `CopyComponentsShapelessRecipeBuilder`；PotionUtils → `PotionContents` 组件；三个 tag provider 改继承泛型 `AbstractTagProvider<T>`（21.1 移除了 Blocks/Items/EntityTypes 内部类）；`META-INF/accesstransformer.cfg` 复制了 Puzzles Lib 对 datagen 私有成员的 AT 条目（MDG 不会自动应用依赖的 AT） |
| 语言文件 | `zh_cn.json` 全部键名更新到 1.21 格式（进度键 `advancements.thinair.<id>.title/description`、效果键 `effect.*` 保留），补齐强化空气囊/信号火把的译名；en_us 由 datagen 生成；`WallSignalTorchBlock` 覆写 `getDescriptionId`（原版 WallTorchBlock 会把翻译键指到原版条目上） |

## 验证记录

- `compileJava` 通过（仅 EventBusSubscriber/LivingBreathEvents 过时警告）
- `runData` 全部 provider 成功，72+ 资源文件生成
- dev 服务器启动至 `Done (3.882s)`，日志无任何 thinair 相关错误（Mixin/能力/数据包全部生效）
- dev 客户端启动至标题界面，资源包重载零错误、零缺失模型

## 已知事项

- Puzzles Lib 21.1 把 `LivingBreathEvents`/`EventBusSubscriber.bus` 标记为过时（仍可用）；后续上游若移除，需要切换到 NeoForge 原生 `LivingBreatheEvent` 等。
- `damage` 物品模型谓词等原版行为未变；空气囊 327/1962 耐久与 1.20.1 数值一致。
