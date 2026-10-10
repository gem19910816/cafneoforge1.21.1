# 生存本能（Survival Instinct）非官方移植版

Minecraft Forge 1.20.1 版 Survival Instinct 1.0.2 的 NeoForge 1.21.1 社区兼容移植。

## 署名与许可

- 原作者：**tohir**
- 原项目：Survival Instinct 1.0.2
- 原平台：Minecraft Forge 1.20.1
- 制作工具来源：MCreator
- 原项目页面：[CurseForge](https://www.curseforge.com/minecraft/mc-mods/survival-instinct)

这是非官方移植版，不代表原作者认可或参与。本仓库保留原注册 ID、原有美术资源、结构和音效；相关权利归原作者及各自权利人所有。
原始 JAR 元数据声明 Academic Free License v3.0，但项目页面同时标注 All Rights Reserved，存在许可冲突。
在获得明确授权前，请按原项目页面的限制使用，不将本移植版描述为官方版本。

## 构建与安装

使用 JDK 17+ 和项目自带 Gradle Wrapper：

```powershell
.\gradlew.bat jar --offline
```

生成的 JAR 位于 `build/libs/`。客户端同时可使用仓库附带的
`Survival-Instinct-Port-zh_CN.zip` Paxi 资源包，以启用完整资源覆盖和简体中文。

## 本次修复

- 缓存并复用外骨骼、重装护甲模型，修复 EMF 的 excessive model creation 警告。
- 冲刺数据包改为服务端校验并限制冲量。
- 便携袋改用 1.21 数据组件，禁止嵌套、复制和错误的手持槽操作。
- 修复容器存档、战利品表、结构、配方、投射物命中、出血计时和套装效果兼容。
- 增加简体中文语言文件，物品提示改为可翻译键。

