# 万象棱镜 (Item-Rarity)

Scarasol 原作的 Minecraft 1.21.1 / NeoForge 授权迁移测试版，原作者署名和许可保留。这不是作者官方发布。

## 安装

环境：Minecraft 1.21.1、Java 21、NeoForge 21.1.252 或更新的 21.1.x。
构建与运行测试使用 NeoForge 21.1.255；21.1.252 已放开依赖检查，尚未在该版本运行测试。

将本目录的 [item_rarity-1.2.0-neoforge.jar](item_rarity-1.2.0-neoforge.jar) 放进客户端和服务端的 `mods` 目录。
该模组是 [翻箱倒柜](../翻箱倒柜/) 的必需前置；使用翻箱倒柜时请同时安装两个 JAR。
稀有度配置仍在 `config/item_rarity/`，客户端与服务端的稀有度定义和贴图需要一致。

## 构建

使用 Java 21，在本目录运行：

```powershell
.\prepare-dependencies.ps1
.\gradlew.bat build
```

依赖准备脚本下载并校验编译依赖，不会将第三方模组打包进成品。
脚本里的 Forge 1.20.1 TaCZ / Tag Editor JAR 仅用于编译，不能装入 NeoForge 1.21.1 游戏。

## 验证与限制

已通过干净构建、与翻箱倒柜共同加载、网络编解码与未授权稀有度修改拦截检查，
以及客户端创作编辑按钮和稀有度背景渲染测试。
TaCZ、Tag Editor 实际运行联动尚未覆盖；详见 [移植说明](移植说明.md) 和 `verification/`。

上游：https://github.com/Scarasol/Item-Rarity
许可与署名见 `LICENSE.txt`、`CREDITS.txt`。
