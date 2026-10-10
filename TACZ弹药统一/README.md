# TACZ 弹药统一 / Tacz: UniDict

Tacz-Unidict 2.0.1 的非官方移植版，版本号 `2.0.1-port.1`。

- Minecraft：1.21.1
- NeoForge：21.1.252
- Java：21
- TaCZ：`tacz-neoforge-1.21.1-1.1.8-hotfix-r7.jar`
- Mod ID：`tacz_unidict`

## 功能与配置

按武器类别统一弹药，也支持为指定 GunId 配置多种可切换弹药。
普通物品可以作为弹药；多弹药配置不代表一个弹匣中混装多种子弹。
默认按 `C` 打开弹药选择轮盘，实际按键以游戏控制设置为准。

配置生成在游戏实例的 `config` 目录：

- `tacz-unidict-common.toml`：武器分类映射、白名单与兼容选项。
- `tacz_unidict/gun_data/*.json`：指定枪械的可选弹药列表。
- `tacz_unidict/ammo_data/*.json`：弹药或普通物品弹药的定义。
- `tacz_unidict/modifier_data/*.json`：伤害修正定义。

本源码不包含此前临时测试的 M1911 / 铁锭配置。
保留原模组模型、贴图与六种弹药配方，没有新增配方或美术资源。
不要与 `ammo_unify` 同时安装。

## 构建

此目录是独立 Gradle 工程，不需要在仓库根目录构建。
TaCZ 本体不随源码分发，请自行取得上述版本的依赖。
将它放在本目录的 `libs` 文件夹，或通过 `-Ptacz_jar` 指定路径。

Windows（JDK 21）：

```powershell
.\gradlew.bat build
# 或指定外部依赖：
.\gradlew.bat build "-Ptacz_jar=C:/path/to/tacz-neoforge-1.21.1-1.1.8-hotfix-r7.jar"
```

输出在 `build/libs`，包括成品 JAR 和 sources JAR。
开发启动命令为 `runClient` / `runServer`；需要相同的 TaCZ 依赖。

## 验证范围

此前已对打包后的 JAR 完成专用服务器和客户端验证：
默认 TaCZ 枪包 54 把枪、服务器 459 项断言，以及弹药选择、普通物品消耗、
旧弹退回和模型贴图检查。客户端兼容检查包含 TaCZ Tweaks 3.0.0-alpha.10。
没有验证整个 CAF 整合包或所有第三方枪包。

## 来源与许可

原项目：[Scarasol/Tacz-Unidict](https://github.com/Scarasol/Tacz-Unidict)。
来源提交：`6c74ffd181d079d89b453bca5f458b900ab4cd29`。
原作者：Scarasol、IronCurtain。

保留原项目的 `LICENSE`、`CREDITS.txt` 和资源署名；详见这些文件与 `PORTING.md`。
用户声明已获得原项目许可。本移植不是原作者的官方发布，也未重新许可原资源。
