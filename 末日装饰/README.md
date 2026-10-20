# 末日装饰 NeoForge 1.21.1

本工程现在将原“末日装饰容器附属”集成在 `doomsday_decoration` 模组中。游戏只安装构建出的 `doomsday_decoration-1.2.0.jar` 即可同时获得装饰方块和可配置容器功能，不再需要单独安装容器附属。

容器容量、音效类别和方块目标清单位于 `src/main/resources/doomsdaycontainers/container_targets.json`。服务器可逐个控制哪些装饰方块具有容器功能，详见[容器配置说明](容器配置说明.md)。

构建命令：`gradle build`。
