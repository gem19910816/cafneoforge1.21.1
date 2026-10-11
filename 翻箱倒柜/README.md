# 翻箱倒柜 (Rummage)

Scarasol 原作的 Minecraft 1.21.1 / NeoForge 授权迁移测试版，原作者署名和许可保留。这不是作者官方发布。

## 安装

环境：Minecraft 1.21.1、Java 21、NeoForge 21.1.252 或更新的 21.1.x。
构建与运行测试使用 NeoForge 21.1.255；21.1.252 已放开依赖检查，尚未在该版本运行测试。

必需前置：[万象棱镜 (Item-Rarity)](../万象棱镜/)。
将本目录的 [rummage-1.1.1-neoforge.jar](rummage-1.1.1-neoforge.jar)
及万象棱镜的 JAR 同时放进客户端和服务端的 `mods` 目录。
配置文件为 `config/rummage-common.toml`。

## 构建

保留本仓库相邻的 `万象棱镜/` 和 `翻箱倒柜/` 两个目录，使用 Java 21，在本目录运行：

```powershell
..\万象棱镜\prepare-dependencies.ps1
.\prepare-dependencies.ps1
.\gradlew.bat build
```

Gradle 会同时构建两个模组，输出分别在各目录的 `build/libs/`。
脚本下载并校验编译依赖，第三方依赖不会打包进成品 JAR。

## 验证与限制

服务器 29 项检查及客户端稀有度渲染、遮罩同步和搜索计时解锁测试已通过。
Corpse、Lootr、Petite Inventory 及 Sophisticated 依赖共同加载成功；
完整多人流程和全部兼容容器尚未覆盖。
Petite Inventory 只接入新版面积 API，旧版位置变化强制重锁行为尚未恢复。
Sona 实际运行联动尚未验证。详见 [移植说明](移植说明.md) 和 `verification/`。

上游：https://github.com/Scarasol/Rummage
许可与署名见 `LICENSE`、`CREDITS.txt`。
