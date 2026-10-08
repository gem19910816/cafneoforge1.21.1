# 末日装饰：真实容器 (Doomsday Containers)

给《末日装饰》里**所有看起来像容器的装饰方块**装上真正的容器功能。

| 项目 | 值 |
|---|---|
| 支持版本 | Minecraft **1.21.1** / NeoForge **21.1.255** |
| Java | 21 |
| 模组 ID | `doomsdaycontainers` |
| 前置 | `doomsday_decoration`（末日装饰）1.1.x |
| 端 | 客户端 + 服务端都要装 |
| 修改原模组文件 | **否**（全部通过 Mixin 注入，原模组 jar 原封不动） |
| 容器化方块数 | **114 个**（含 30 种遗体、裹尸袋） |

产物：`doomsdaycontainers-1.0.0.jar`（约 142 KB；本地构建输出在 `dist/`，仓库内放在本文件夹根目录）

---

## 1. 效果

被容器化的方块现在是货真价实的容器：

1. **右键打开原版箱子界面**，按方块大小自动 1~6 行（9 / 18 / 27 / 36 / 45 / 54 格）
2. **开关音效按类型区分**（共 16 种，不是所有东西都用箱子音效）：木箱是木盖声、金属柜是铁门声、冰箱是冰箱门、
   售货机/ATM 是卷帘门声、行李箱是皮箱声、纸箱是纸板声、塑料箱是塑料声、遗体是黏腻声、垃圾袋/裹尸袋是布料声……见第 4 节。
3. **内容随存档持久保存**，重启服务器后原样还在
4. **暴露 NeoForge 物品处理器能力**：漏斗、投掷器、溜槽、以及各类物流/自动化模组都能往里塞、往外拿
5. **比较器按装满程度输出红石信号**
6. **破坏方块时完整掉落里面的东西**，不会吞物品
7. **潜行右键不拦截**，让方块的原有装饰交互（音效等）照常工作
8. **老存档里已经放下的装饰方块**，第一次右键时会自动补建方块实体，不用拆了重放

另外顺手修了原模组自带的一个问题（见第 5 节）。

---

## 2. 安装

1. 装好《末日装饰》本体（`doomsday_decoration-1.1.3-neoforge-1.21.1.jar`）
2. 把 `doomsdaycontainers-1.0.0.jar` 丢进 `mods/`
3. 启动即可。附属在启动日志里会打印一行：

```
[DoomsdayContainers]: 容器化目标：注册 113 个新容器，1 个原模组自带方块实体，0 个未找到（共 114 条清单）
```

如果看到 `0 个未找到` 说明全部命中；如果原模组换了版本、删了某些方块，只会有对应几条 `WARN` 并被跳过，**不会崩游戏**。

想核对生效情况，游戏里（需要 OP/权限等级 2）执行：

```
/doomsdaycontainers stats       # 生效了多少个
/doomsdaycontainers selftest    # 逐个自检界面行数、格子数、库存绑定
```

---

## 3. 哪些方块被容器化了（114 个）

| 类别 | 方块 | 格子数 |
|---|---|---|
| **遗体 / 裹尸袋** | `remains_1`…`remains_10`（10 个）、`remains_11/22/…/99`（9 个）、`remains_111/222/…/999`（9 个）、`remains_1010` `remains_101010`、`bodybag` | 9 |
| 板条箱/木箱/纸箱/塑料箱/武器箱 | `acrate` `acrate_2` `acrate_3` `woodencrate` `carton` `carton_2` `carton_3` `plasticbox` `plasticbox_2/3/4` `weaponbox` | 9 / 27 |
| 柜类 | `cabinet` `cabinet_2` `cabinet_3` `metalcabinet` `metalcabinet_2` `woodencabinet` `woodencabinet_2` `insulationcabinet` `constanttemperatureincubator` | 27 |
| 储物柜 | `lockers` `lockers_2` | 54 |
| 抽屉/文件柜 | `metaldrawer` `metaldrawer_2` `woodendrawer` `woodendrawer_2` `officedrawers_1` `officedrawers_2` | 18 / 27 |
| 箱类 | `ammunitionbox` `medicalbox` `toolbox` `accessorybox_1` `accessorybox_2` `firstaidkit` `safe` | 9 / 18 / 27 |
| 行李箱 | `luggage` `blackluggage` `blueluggage` `greenluggage` `greyluggage` `khakiluggage` `redluggage` | 27 |
| 货架 | `shelf` `shelf_2/3/4` `goodsshelves` `goodsshelves_2/3/4/5/6` | 27 |
| 垃圾类 | `trashcan` `trashcan_2/3/4` `indoorgarbagebin` `trashbag` `trashbag_2/3` | 9 / 27 |
| 桶罐类 | `oildrum` `oiltank` `oiltank_2` | 27 |
| 家电类 | `fridge` `fridge_2` `freezer` `washingmachine` `microwaveoven` `waterdispenser` `coffee_machine` | 9 / 27 |
| 商用设备 | `vendingmachine` `vendingmachine_2/3/4` `atm` `atm_2` `cashregister` | 9 / 27 |
| 邮箱 | `mailbox_1` `mailbox_2` | 9 |
| 推车 | `cart` `cart_2` `shopping_cart` | 27 |

（板条箱 `acrate` 原模组自带方块实体，附属不抢它的方块实体，只在需要时补交互、补音效、修界面。）

**想增删或改大小**：编辑
`src/main/resources/doomsdaycontainers/container_targets.json`
（`slots` 必须是 9 的倍数、9~54；不在清单里的方块就是普通装饰），
然后重新构建；如果新增的方块是**原模组里全新的方块类**，还要重跑生成脚本：

```powershell
# 重新根据原模组源码生成目标清单与混入类
powershell -File tools/gen_targets.ps1
powershell -File tools/gen_mixins.ps1
```

---

## 4. 开关音效（按类型区分，共 16 种）

音效在配置里按方块分类（`container_targets.json` 的 `sound` 字段），游戏内可用
`/doomsdaycontainers sounds` 逐条列出每个方块实际用的是哪一对声音。

| 类别 | 开 / 关音效 | 用在哪些方块 |
|---|---|---|
| `wood_crate` | 木桶盖 `barrel.open` / `.close` | 板条箱、木箱、武器箱 |
| `wood_cabinet` | 木门 `wooden_door.open` / `.close` | 柜子、木柜 |
| `glass_cabinet` | 玻璃 `glass.place` / 木活板门关 | 玻璃柜（2 个） |
| `wood_drawer` | 木活板门 `wooden_trapdoor.open` / `.close` | 木抽屉（2 个） |
| `wood_shelf` | 木头 `wood.place` / `wood.hit` | 货架、架子（没有门，木器轻响，10 个） |
| `metal_door` | 铁门 `iron_door.open` / `.close` | 金属柜、储物柜、保险箱、冰箱、冰柜、洗衣机、保温柜、恒温培养箱（11 个） |
| `metal_lid` | 铁活板门 `iron_trapdoor.open` / `.close` | 工具箱、弹药箱、医疗箱、配件箱、急救包、金属抽屉、文件柜、油桶、垃圾桶、微波炉（19 个） |
| `metal_flap` | 木按钮 `click_on` / `click_off` | 邮箱（2 个） |
| `vault` | 宝库卷帘 `vault.open_shutter` / `.close_shutter` | 售货机、ATM、收银台（7 个） |
| `appliance` | 铜活板门 `copper_trapdoor.open` / `.close` | 饮水机、咖啡机 |
| `cardboard` | 展示框 `item_frame.add_item` / `.remove_item` | 纸箱（3 个） |
| `plastic` | 黏液块 `slime_block.place` / `.break` | 塑料箱（4 个） |
| `luggage` | 皮革 `armor.equip_leather` / 收纳袋 `bundle.insert` | 7 种行李箱 + 3 种垃圾袋（10 个） |
| `corpse` | 挤压 `slime.squish` / 蜜块滑 `honey_block.slide` | 30 种遗体 |
| `body_bag` | 收纳袋 `bundle.remove_one` / `bundle.drop_contents` | 裹尸袋 |
| `cart` | 锁链 `chain.place` / `chain.hit` | 小推车、购物车（3 个） |

**想给单个方块换音效**：直接在 `container_targets.json` 里加字段覆盖，例如

```json
"acrate": { "@class": "AcrateBlock", "slots": 9, "sound": "wood_crate",
            "sound_open": "minecraft:block.chest.open", "sound_close": "minecraft:block.chest.close" }
```

`sound_open` / `sound_close` 可以填任意音效 id（原版或模组的都行，比如 `doomsday_decoration:huzai`），
填了就以它为准，没填就用 `sound` 类别的默认值。改完重新构建即可，不用改代码。

同一容器多人同时开只响一次（`0→1` / `1→0` 计数），和原版箱子一致。

---

## 5. 实测结果（真跑的，不是"应该可以"）

用本地 NeoForge 21.1.255 专用服务器 + 原模组 + 本附属，RCON 驱动两轮实测：

| 项目 | 结果 | 证据 |
|---|---|---|
| 目标识别 | ✅ 114/114 | `stats` → 注册 113 个新容器，1 个原模组自带 |
| 界面/菜单自检 | ✅ 114/114 通过 | 逐个构造界面，核对菜单类型、行数、容器格数、库存是否绑定到该方块实体 |
| **开关音效** | ✅ 114/114 通过，**16 种不同音效** | 每个方块都在世界里真放一次、真跑 `startOpen`/`stopOpen`，确认各派发一次声音；`/doomsdaycontainers sounds` 输出 114 条映射（日志 `音效确认为 2 次派发的 114 条`、`音效映射 114 条，不同开关音效 16 种`） |
| 方块实体随放置自动建立 | ✅ | `data get block` → `{... Items: [], id: "doomsday_decoration:cabinet"}` |
| 物品存取 | ✅ | `item replace block ... container.0 with minecraft:diamond 3` → `Items: [{count:3, id:"minecraft:diamond"}]` |
| **遗体 / 裹尸袋** | ✅ | `remains_1` 里放骨头、`bodybag` 里放腐肉，读取正确且重启后仍在 |
| **漏斗能力（ItemHandler）** | ✅ | 漏斗在上方朝下塞绿宝石，4 秒后柜子里出现 `{count:4, id:"minecraft:emerald"}`，落在第 2 格 |
| 54 格容器 | ✅ | 储物柜第 53 格写入木棍成功 |
| **破坏掉落内容** | ✅ | `setblock ... air destroy` 后同时命中 `DDC_DROP_OK` 与 `DDC_CONTENT_DROP_OK`（方块本身 + 里面的金锭） |
| **跨重启持久化** | ✅ | 第二轮启动后：保险箱里的下界合金、柜子里的钻石+绿宝石、储物柜第 53 格的木棍、板条箱里的钻石、遗体里的骨头、裹尸袋里的腐肉全部还在 |
| 日志洁净度 | ✅ | 附属自身 **0 条 WARN / ERROR**；服务器日志里唯一一条 ERROR 是原版平坦世界配置提示 `No key layers in MapLike[{}]`（服务器配置本身产生，与本附属无关） |

复现方式：

```powershell
powershell -File tools/build.ps1         # 编译打包
powershell -File tools/setup_testserver.ps1   # 搭测试服（复用已有 NeoForge 依赖，不重新下载）
powershell -File tools/test_server.ps1   # 两轮实测 + 日志体检
```

原始记录见 `report/test_transcript.txt` 与 `report/server_pass1.out.log` / `server_pass2.out.log`。

> 客户端界面：专用服务器测不了图形界面。附属用的是**原版箱子菜单类型**（`GENERIC_9x1`…`GENERIC_9x6`）与原版 `ChestScreen`，
> 而菜单类型/行数/库存绑定已在服务端逐个验证通过，所以客户端会按同样的行数渲染原版箱子界面。

---

## 6. 原理

一个方块要想变成"真容器"，**必须满足两个硬条件**：方块类实现 `EntityBlock` + 注册 `BlockEntityType`。
原模组的 1143 个装饰方块都是纯 `Block`，所以无论如何都得"动到方块类"——区别只在于动在哪里：
改原模组源码，还是像本附属这样用 Mixin 在加载期注入。本附属选择后者，原模组 jar 一个字节都不改。

具体做法：

1. **Mixin 注入**（`mixin/*BlockMixin.java`，由 `tools/gen_mixins.ps1` 生成，共 83 个）
   给每个目标方块类加上 `EntityBlock` 接口，并在**原类里没有**这些方法时补上：
   `newBlockEntity` / `getMenuProvider` / `useWithoutItem` / `onRemove` / `hasAnalogOutputSignal` / `getAnalogOutputSignal`。
   逻辑统一在 `ContainerHooks` 里，生成出来的混入类只有转发。
2. **注册方块实体类型**（`ContainerTargets`）
   在 `RegisterEvent`（方块实体类型注册阶段，此时方块注册表已经填好）读取
   `container_targets.json`，为每个目标方块注册一个 `BlockEntityType`。
3. **通用方块实体**（`GenericContainerBlockEntity`）
   继承 `RandomizableContainerBlockEntity`，实现 `WorldlyContainer`：存档、懒加载战利品表、六面可访问。
   名字直接取方块自己的翻译键，所以界面标题就是"柜子""保险箱""金属桶"。
4. **界面**：`createMenu` 用 `new ChestMenu(MenuType.GENERIC_9xN, id, inv, this, rows)`，
   `N` 由格子数算出，客户端交给原版 `ChestScreen` 渲染，不需要任何客户端代码。
5. **能力**：`RegisterCapabilitiesEvent` 里对每个类型注册 `Capabilities.ItemHandler.BLOCK`，
   指定面用 `SidedInvWrapper`、无方向用 `InvWrapper`。
6. **开关音效**：容器方块实体的 `startOpen` / `stopOpen` 里按方块类别播不同音效（`ContainerSounds`，16 个类别），
   带 `0→1` / `1→0` 计数，所以多人同开只响一次；服务端 `playSound(null, ...)` 会广播给附近客户端。
   原模组的板条箱用的是 `Container` 接口的默认空实现，它的 `startOpen`/`stopOpen` 由混入补上，用的是木盖声（`wood_crate`）。

几个踩过的坑（都已修好，写在这里省后人再踩）：

- **不能在模组构造阶段查方块注册表**，那时所有注册表还是空的；必须在 `RegisterEvent` 里做。
- **不能靠 `instanceof EntityBlock` 判断"原模组是否自带方块实体"**——本附属正是给所有目标补上了这个接口，
  所以"是否原生"由生成清单时对原模组源码的分析结果（`native` 字段）决定。
- **方块实体类型不能用传入的 `BlockState` 反查**：区块序列化重建方块实体时，如果那个位置已经变成空气
  （例如破坏方块后残留的方块实体 NBT），传进来的 state 是空气，反查得到 `null`，直接在 `BlockEntity` 构造里 NPE。
  现在类型由注册时捕获，state 不可用时回退到方块默认状态。
- **1.21.1 的生存模式右键入口是 `useWithoutItem`**（`useItemOn` 返回 `PASS_TO_DEFAULT_BLOCK_INTERACTION` 之后调用），
  `getMenuProvider` 在 `ServerPlayerGameMode` 里只出现在**旁观者**分支。这带来两个结论：
  附属必须自己注入 `useWithoutItem`（只写 `getMenuProvider` 是打不开界面的）；
  同时说明**原模组板条箱在 1.21.1 生存模式下其实根本打不开界面**——附属的 `AcrateBlockMixin` 顺手补上了这个入口。
- **板条箱界面错位**：原模组 `AcrateBlockEntity.createMenu` 用的是 `ChestMenu.threeRows(id, inventory)`，
  既没绑定自己的库存（界面永远是空的），又用了 3 行（27 格）而它只有 9 格。
  附属用 `@Overwrite` 改成 1 行 + 绑定自身库存（`AcrateBlockEntityMixin`）。
  这个覆盖式混入带保险丝：目标类里没有 `createMenu` 就自动跳过，不会崩游戏。

---

## 7. 构建

**方式一：Gradle（有网）**

```bash
gradle build          # 需要 JDK 21、Gradle 8.8+
# 产物 build/libs/doomsdaycontainers-1.0.0.jar
```

用的是 ModDevGradle 2.0.148 + NeoForge 21.1.255。
编译**不需要**原模组 jar：混入目标是以字符串形式写的类名，运行时才解析，所以本附属可以独立编译、独立发版。
想在开发环境里连着原模组一起跑，把原模组 jar 放进 `libs/` 并取消 `build.gradle` 里那行 `runtimeOnly` 注释。

**方式二：离线 javac（无网环境，本次交付就是这么编的）**

```powershell
powershell -File tools/build.ps1
```

它复用本机 `doomsday1211` 工程已经拉好的 NeoForge 依赖（`build-manual/classpath.txt`）直接 javac + jar，不联网、不跑 Gradle。

---

## 8. 目录结构

```
doomsday-containers/
├─ dist/doomsdaycontainers-1.0.0.jar      # 成品
├─ src/main/java/com/carrion/doomsdaycontainers/
│   ├─ DoomsdayContainers.java            # 模组入口、能力注册
│   ├─ ContainerTargets.java              # 目标清单 -> 方块实体类型
│   ├─ GenericContainerBlockEntity.java   # 通用容器方块实体（含开关音效）
│   ├─ ContainerSounds.java               # 16 类开关音效（想换音效改这里或改 JSON）
│   ├─ ContainerHooks.java                # 混入逻辑实现
│   ├─ DoomsdayContainersCommand.java     # /doomsdaycontainers stats|selftest
│   └─ mixin/
│       ├─ DoomsdayContainersMixinPlugin.java   # 混入保险丝（目标不存在就跳过）
│       ├─ AcrateBlockEntityMixin.java          # 手写：修板条箱界面
│       └─ *BlockMixin.java                     # 生成：83 个目标方块
├─ src/main/resources/
│   ├─ META-INF/neoforge.mods.toml
│   ├─ doomsdaycontainers.mixins.json
│   └─ doomsdaycontainers/container_targets.json  # 想改就改这里
├─ tools/                                 # 清单生成、混入生成、编译、实测脚本
├─ report/                                # 实测记录与日志
└─ testserver/                            # 本地实测用专用服务器（libraries 是目录联接，可删）
```

---

## 9. 注意事项

- **客户端也要装**：方块实体类型与界面是双端注册的，只装服务端会出现丢包/界面打不开。
- **移除附属后**：已经放进这些容器里的物品仍留在存档的方块实体数据里，但方块不再是容器，会造成这些数据变成
  "孤儿方块实体 NBT"（和原版拆掉箱子时的表现一样），物品拿不出来。移除前请先清空容器。
- **潜行右键**不打开容器，保持方块原有交互；正常右键打开。
- **开关音效**按类型分 16 类（见第 4 节）。要调单个方块的音效，改 `container_targets.json` 的
  `sound` / `sound_open` / `sound_close` 字段即可（不用改代码），游戏内用 `/doomsdaycontainers sounds` 查看实际映射。
- 界面用的是原版箱子贴图，不是每种方块单独画一套 GUI——原模组的板条箱本来也是用箱子界面。
- 附属锁定的是"方块**类名**"。原模组大改类名时，对应混入会被保险丝跳过（日志会打印"目标方块不存在，跳过混入"），
  此时该方块退回普通装饰，其余方块不受影响。

## 10. 许可

本附属只做行为注入，不包含原模组的任何美术/数据资源。与原模组保持一致（未标注）。
