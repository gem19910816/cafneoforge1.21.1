# NOTICE / 来源与许可说明

本文件是 `Ammo Unify`（模组 id `ammo_unify`）的来源、许可与合规说明。
**分发本模组时，本文件必须与本模组的 `LICENSE` 一并保留。**

---

## 1. 本项目的许可

本项目（`Ammo Unify`）以 **GNU Affero General Public License v3.0 only（AGPL-3.0-only）** 授权发布。
完整许可文本见同目录下的 `LICENSE`。

## 2. 与 TaCZ 的关系

本模组是 **TaCZ（Timeless and Classics Zero）** 的附属模组，必须在安装了 TaCZ 的环境下运行，
运行期依赖由玩家或整合包提供，**不打包进本模组的 jar**。

- TaCZ 项目：<https://github.com/MCModderAnchor/TACZ>
- TaCZ 在 Modrinth 上的 1.21.1 NeoForge 由社区移植维护，本项目针对其 `1.1.8-hotfix` 系列构建
- TaCZ 的许可为 **GPL-3.0-only**（其资源部分为 CC BY-NC-ND 4.0）

本模组以 AGPL-3.0-only 发布，与 TaCZ 的 GPL-3.0-only 相容（AGPL-3.0 第 13 条允许与 GPLv3 作品结合）。

## 3. 与 Tacz-Unidict（铳械协议）的关系 —— 请阅读

本模组解决的问题与 **Scarasol 的 Tacz-Unidict** 相同：把 TaCZ "一把枪绑定一种专属弹药" 的机制，
改造成按武器类别共用少数几种"通用弹药"。

- Tacz-Unidict 项目：<https://github.com/Scarasol/Tacz-Unidict>
- 其许可为 **AGPL-3.0**（代码）与 **CC BY-NC-ND 4.0**（内置枪包素材）

**本模组是独立实现（independent implementation）。** 具体而言：

- 本项目**不包含** Tacz-Unidict 的任何源代码、Java 类名、方法名、包名、注释、配置文件格式、JSON 数据文件或美术素材；
- 本项目**不包含** Tacz-Unidict 内置枪包的贴图、`geo_models`、`display` 或 `index/ammo` 数据；
- 本项目自己的枪包素材（贴图、模型、数据）由本仓库内的脚本生成，见 `tools/`；
- 二者的相似之处仅来自**同一个上游依赖 TaCZ 的公开接口**：任何要改写"枪认哪种弹药"的实现，都必须在 TaCZ 的同一批判定点上介入。这属于功能性要求，不构成对在先实现的复制。

Tacz-Unidict 是本领域的在先实现，特此致谢。

## 4. 第三方素材

本项目**不重新分发**任何第三方美术资源。

- TaCZ 的贴图/模型：不包含
- Tacz-Unidict 的贴图/模型：不包含
- 本模组自带的通用弹药物品贴图与模型：由本仓库 `tools/generate_assets.py` 生成，随本项目按 AGPL-3.0-only 授权

## 5. 合规自查清单（分发前请逐项确认）

- [ ] 本模组的完整源码已公开，且可被接收者获取（AGPL-3.0 第 6 条）
- [ ] `LICENSE`（AGPL-3.0 全文）已随 jar / 源码仓库一并提供
- [ ] 本 `NOTICE.md` 已随 jar / 源码仓库一并提供
- [ ] 若你修改过本模组，已在显著位置标明"已修改"及修改日期（AGPL-3.0 第 5(a) 条）
- [ ] 你的整合包**没有**把 TaCZ 或 Tacz-Unidict 的素材重新打包分发
- [ ] 你的整合包**没有**设置付费门槛来解锁本模组（若含 NC 素材则此项为硬性要求）
- [ ] 若在公开服务器上运行修改版，玩家可获取该版本的对应源码（AGPL-3.0 第 13 条）

## 6. 免责

本模组按"现状"提供，不附带任何明示或默示的担保。详见 `LICENSE` 第 15、16 条。
