这个目录用于放置**仅编译期使用**的 TaCZ 二进制 jar。
它不会被打进 Ammo Unify 的发布产物，也不会随源码分发。

请把 TaCZ 的 1.21.1 NeoForge 版本放到这里，文件名需要与 build.gradle 里的
`tacz_jar` 属性一致（默认见 gradle.properties）：

    libs/tacz-neoforge-1.21.1-1.1.8-hotfix-r7.jar

获取方式：Modrinth 项目 [UNOFFICIAL] TaCZ 1.21.1 NeoForge Port
（slug: tacz-1.21.1），下载 1.1.8-hotfix 系列的主文件即可。

TaCZ 的许可为 GPL-3.0-only（其资源部分为 CC BY-NC-ND 4.0），
版权归 TaCZ 开发组所有。本仓库不包含、也不再分发它的任何内容。
