# CAF 生存核心（CAF Survival Core）

> 上游原名 **Tarkov Stamina**，包名 `com.chaosz.tarkovstamina`，mod_id `tarkov_stamina`。
> 本工程是它的 **Minecraft 1.21.1 / NeoForge** 版，由 1.20.1 Forge 版移植而来。

一套服务端权威的生存状态系统 + 塔科夫风格 HUD。体力、伤病、抑郁、恐慌、成瘾、
负重、职业、钓鱼、排泄、军用背包，全部在一个模组里，配置项集中在一个 TOML。

## 装了什么

| 系统 | 内容 |
|---|---|
| 体力核心 | 疾跑耗体力、跳跃扣体力、延迟恢复、耗尽锁疾跑、睡觉回满、死亡重生回满 |
| 基因强化 | 右键注射针剂，每次 +10% 上限，最多 3 次 |
| 锻炼 | 疾跑积累进度，每级 +1% 上限（最高 70 级） |
| 负重 | 背包/帐篷超过阈值 → 缓慢 II，并拖累职业加成 |
| 抑郁 | 熬夜 + 室内幽闭 → 抑郁 50/100；洗澡（水中+营火）或睡觉恢复 |
| 疾病 | 淋雨无伞会感冒；室内静养或靠近火源加速康复，睡觉痊愈 |
| 恐慌 | 周围怪物 ≥10 只 → 挖掘疲劳；累计击杀 100 只后麻木免疫 |
| 成瘾 | 烟/酒摄入 3 次上瘾；30 分钟犯瘾，72 分钟戒断；摄入后 5 分钟冷静 |
| 职业 | 木工（斧）/石工（镐）/技工（螺丝刀）分级给急迫，受上面几种负面状态扣减 |
| 钓鱼 | 抛竿计经验，2 级/3 级缩短咬钩等待，手持鱼竿给幸运 |
| 排泄 | 食物种类过杂会窜稀；憋太久腹胀；蹲下或坐马桶解决；屎可投掷、可蹲下吃掉 |
| 军用背包 | 挎包 9×3 / 书包 9×4 / 登山包 9×5 / 军用背包 12×9，可手持也可挂 Curios 背部槽 |
| 帐篷读条 | 潜行空手右键收帐篷、手持帐篷右键放帐篷，BossBar 显示读条 |
| 生存档案 | `/caf` 打开四页面板；`/caf hud` 拖动调整体力条位置；`/caf hud reset` 复位 |

## 前置

- **Minecraft 1.21.1**
- **NeoForge 21.1.0+**（编译对着 21.1.227）
- **Curios 9.x**（**硬前置**）—— 随附 `libs\curios-neoforge-9.5.1+1.21.1.jar`

Curios 是必须的：背包的背部饰品槽、以及「从饰品栏找回背包」那条路径都直接调
`CuriosApi`，没装会 `NoClassDefFoundError`。这是沿用上游 `mods.toml` 里的
`mandatory=true`，没有放宽。

其余联动（`simplytents` 帐篷、`refurbished_furniture` 马桶、
`survival_instinct` 螺丝刀、`doomsday_decoration` 家具、`create` 零件、
`harmfulsmoke` 香烟等）全是**软**依赖：装了对应内容才生效，不装一切照常。

## 构建

```
build.bat build        # 编译并打 jar
build.bat runClient    # 开发环境客户端（游戏目录 run\）
build.bat runServer    # 开发环境服务端
build.bat runData      # datagen
```

`build.bat` 使用 `通用\gradle-8.8` + `通用\gradle-home` 共享缓存 + `jdk-21.0.3`。
构建环境是 **Gradle 8.8 + ModDevGradle 2.0.107 + NeoForge 21.1.227 + Java 21**。

产物：`build\libs\tarkov_stamina-1.21.1-neoforge-<版本>.jar`

> 开发环境跑 `runServer` 时，把 `libs\curios-neoforge-9.5.1+1.21.1.jar` 复制到
> `run\mods\`，否则因为 Curios 是硬前置，服务端会拒绝加载本模组。

## 移植版声明

`neoforge.mods.toml` 里 displayName 为 `CAF 生存核心`，description 首行标注
`[社区移植版] 由 1.20.1 Forge 版移植至 1.21.1 NeoForge`，版本号带 `-port` 后缀。

改动清单与验证结果见 [移植说明.md](移植说明.md)。
