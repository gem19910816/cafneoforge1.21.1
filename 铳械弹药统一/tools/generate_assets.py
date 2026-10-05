#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Ammo Unify —— 枪包素材生成器。

这个脚本是 Ammo Unify 全部美术与数据资源的唯一来源。仓库里看到的
贴图、Bedrock 几何模型、display / index / recipe JSON 与语言文件，
全部由本脚本从下面的 AMMO 表推导出来，没有任何一个字节来自第三方资源包。

用法：
    python tools/generate_assets.py

输出根目录：src/main/resources/
"""

from __future__ import annotations

import json
import math
import os
from pathlib import Path

from PIL import Image, ImageDraw

# ---------------------------------------------------------------- 路径

ROOT = Path(__file__).resolve().parent.parent
RES = ROOT / "src" / "main" / "resources"
NS = "ammo_unify"

# ---------------------------------------------------------------- 弹药表

# id, 中文名, 英文名, 弹体色, 弹头色, 底火/底带色, 尺寸(w,h,d), 堆叠, 排序, 工具提示
AMMO = [
    dict(
        id="cartridge_pistol", zh="通用手枪弹", en="Universal Pistol Cartridge",
        body=(196, 150, 62), tip=(206, 118, 58), base=(150, 112, 44),
        size=(2, 4, 2), stack=64, sort=1, tag="handgun",
    ),
    dict(
        id="cartridge_rifle", zh="通用步枪弹", en="Universal Rifle Cartridge",
        body=(202, 162, 72), tip=(168, 170, 182), base=(152, 116, 48),
        size=(2, 7, 2), stack=64, sort=2, tag="rifle",
    ),
    dict(
        id="shell_shotgun", zh="通用霰弹", en="Universal Shotgun Shell",
        body=(168, 52, 46), tip=(168, 52, 46), base=(190, 150, 70),
        size=(3, 5, 3), stack=32, sort=3, tag="shotgun",
    ),
    dict(
        id="cartridge_sniper", zh="通用狙击弹", en="Universal Sniper Cartridge",
        body=(172, 132, 52), tip=(92, 142, 82), base=(132, 100, 40),
        size=(2, 9, 2), stack=32, sort=4, tag="sniper",
    ),
    dict(
        id="shell_barrel", zh="通用炮弹", en="Universal Barreled Round",
        body=(96, 110, 70), tip=(58, 62, 46), base=(70, 80, 52),
        size=(3, 8, 3), stack=16, sort=5, tag="launcher",
    ),
    dict(
        id="tank_fuel", zh="通用能量罐", en="Universal Fuel Tank",
        body=(72, 168, 178), tip=(196, 200, 204), base=(48, 118, 128),
        size=(4, 6, 4), stack=16, sort=6, tag="energy",
    ),
]

# 配方：材料列表 + 产出数量
RECIPES = {
    "cartridge_pistol": ([("tag", "c:ingots/copper", 5), ("tag", "c:gunpowders", 2)], 40),
    "cartridge_rifle": ([("tag", "c:ingots/copper", 8), ("tag", "c:gunpowders", 3)], 32),
    "shell_shotgun": ([("tag", "c:ingots/copper", 6), ("tag", "c:gunpowders", 4)], 24),
    "cartridge_sniper": ([("tag", "c:ingots/copper", 12), ("tag", "c:gunpowders", 5)], 16),
    "shell_barrel": ([("tag", "c:ingots/iron", 16), ("tag", "c:gunpowders", 8)], 8),
    "tank_fuel": ([("tag", "c:ingots/copper", 6), ("item", "minecraft:coal", 4)], 8),
}

TEX_W = TEX_H = 64
SLOT_W = SLOT_H = 32


# ---------------------------------------------------------------- 工具

def write_json(path: Path, payload) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8", newline="\n") as fh:
        json.dump(payload, fh, ensure_ascii=False, indent=2)
        fh.write("\n")


def shade(color, factor: float):
    return tuple(max(0, min(255, int(c * factor))) for c in color)


# ---------------------------------------------------------------- Bedrock 几何

def cube(origin, size, uv_map):
    return {
        "origin": list(origin),
        "size": list(size),
        "uv": uv_map,
    }


def face_uv(u, v, w, h):
    return {"uv": [u, v], "uv_size": [w, h]}


def box_uv(w, h, d, base_u=0, base_v=0):
    """
    自建的 UV 布局：把六个面平铺到贴图上的固定位置。
    生成贴图时用的是同一套坐标，因此映射天然对齐。

        north/south  -> (base_u + 0, base_v) / (base_u + 8, base_v)   尺寸 w x h
        east/west    -> (base_u + 0, base_v + 8) / (base_u + 8, ...)  尺寸 d x h
        up/down      -> (base_u + 0, base_v + 16) / (base_u + 8, ...) 尺寸 w x d
    """
    return {
        "north": face_uv(base_u + 0, base_v + 0, w, h),
        "south": face_uv(base_u + 8, base_v + 0, w, h),
        "east": face_uv(base_u + 0, base_v + 8, d, h),
        "west": face_uv(base_u + 8, base_v + 8, d, h),
        "up": face_uv(base_u + 0, base_v + 16, w, d),
        "down": face_uv(base_u + 8, base_v + 16, w, d),
    }


def ammo_geometry(ammo):
    w, h, d = ammo["size"]
    return {
        "format_version": "1.12.0",
        "minecraft:geometry": [
            {
                "description": {
                    "identifier": f"geometry.{NS}_{ammo['id']}",
                    "texture_width": TEX_W,
                    "texture_height": TEX_H,
                    "visible_bounds_width": 2,
                    "visible_bounds_height": 1.5,
                    "visible_bounds_offset": [0, 0.5, 0],
                },
                "bones": [
                    {"name": "root", "pivot": [0, 0, 0]},
                    {"name": "bullets", "parent": "root", "pivot": [0, 0, 0]},
                    {
                        "name": ammo["id"],
                        "parent": "bullets",
                        "pivot": [0, h / 2.0, 0],
                        "cubes": [
                            cube(
                                (-w / 2.0, 0, -d / 2.0),
                                (w, h, d),
                                box_uv(w, h, d, 0, 0),
                            )
                        ],
                    },
                    # TaCZ 的 display transform 会按这些骨骼名施加缩放
                    {"name": "positioning", "pivot": [0, 0, 0]},
                    {"name": "ground", "parent": "positioning", "pivot": [0, 0, 0]},
                    {"name": "fixed", "parent": "positioning", "pivot": [0, 0, 0]},
                    {"name": "thirdperson_hand", "parent": "positioning", "pivot": [0, 0, 0]},
                ],
            }
        ],
    }


def shell_geometry(ammo):
    w, h, d = ammo["size"]
    sw, sh, sd = max(1, w - 1), 3, max(1, d - 1)
    return {
        "format_version": "1.12.0",
        "minecraft:geometry": [
            {
                "description": {
                    "identifier": f"geometry.{NS}_{ammo['id']}_shell",
                    "texture_width": TEX_W,
                    "texture_height": TEX_H,
                    "visible_bounds_width": 1,
                    "visible_bounds_height": 1,
                    "visible_bounds_offset": [0, 0.25, 0],
                },
                "bones": [
                    {"name": "root", "pivot": [0, 0, 0]},
                    {
                        "name": f"{ammo['id']}_shell",
                        "parent": "root",
                        "pivot": [0, sh / 2.0, 0],
                        "cubes": [
                            cube(
                                (-sw / 2.0, 0, -sd / 2.0),
                                (sw, sh, sd),
                                box_uv(sw, sh, sd, 0, 32),
                            )
                        ],
                    },
                ],
            }
        ],
    }


def display_json(ammo):
    return {
        "model": f"{NS}:ammo/{ammo['id']}",
        "texture": f"{NS}:ammo/uv/{ammo['id']}",
        "slot": f"{NS}:ammo/slot/{ammo['id']}",
        "shell": {
            "model": f"{NS}:shell/{ammo['id']}_shell",
            "texture": f"{NS}:shell/{ammo['id']}_shell",
        },
        "tracer_color": ammo["tracer"],
        "transform": {
            "scale": {
                "thirdperson": [0.6, 0.6, 0.6],
                "ground": [0.6, 0.6, 0.6],
                "fixed": [1.2, 1.2, 1.2],
            }
        },
    }


def index_json(ammo):
    return {
        "name": f"{NS}.ammo.{ammo['id']}.name",
        "display": f"{NS}:{ammo['id']}_display",
        "tooltip": f"{NS}.ammo.{ammo['id']}.tooltip",
        "stack_size": ammo["stack"],
        "sort": ammo["sort"],
    }


def recipe_json(ammo):
    materials, count = RECIPES[ammo["id"]]
    mats = []
    for kind, value, n in materials:
        mats.append({"item": {kind: value}, "count": n})
    return {
        "materials": mats,
        "result": {
            "type": "ammo",
            "group": f"{NS}_unified",
            "id": f"{NS}:{ammo['id']}",
            "count": count,
        },
        "type": "tacz:gun_smith_table_crafting",
    }


# ---------------------------------------------------------------- 贴图生成

def new_tex():
    img = Image.new("RGBA", (TEX_W, TEX_H), (0, 0, 0, 0))
    return img, ImageDraw.Draw(img)


def fill(img, u, v, w, h, color):
    """在 (u,v) 处填一个 w x h 的矩形，坐标取整以便和 UV 精确对齐。"""
    for y in range(int(v), int(v + h)):
        for x in range(int(u), int(u + w)):
            if 0 <= x < TEX_W and 0 <= y < TEX_H:
                img.putpixel((x, y), color)


def paint_box(img, w, h, d, base_u, base_v, body, tip, base):
    """按 box_uv() 的布局给六个面涂色。"""
    fill(img, base_u + 0, base_v + 0, w, h, body)          # north
    fill(img, base_u + 8, base_v + 0, w, h, shade(body, 0.88))  # south
    fill(img, base_u + 0, base_v + 8, d, h, shade(body, 0.94))  # east
    fill(img, base_u + 8, base_v + 8, d, h, shade(body, 0.82))  # west
    fill(img, base_u + 0, base_v + 16, w, d, tip)          # up
    fill(img, base_u + 8, base_v + 16, w, d, base)         # down

    # 在底面加一圈底带，视觉上像弹壳底缘
    band = max(1, int(h * 0.22))
    for name_u in (base_u + 0, base_u + 8):
        pass
    fill(img, base_u + 0, base_v + h - band, w, band, base)
    fill(img, base_u + 8, base_v + h - band, w, band, shade(base, 0.85))
    fill(img, base_u + 0, base_v + 8 + h - band, d, band, shade(base, 0.92))
    fill(img, base_u + 8, base_v + 8 + h - band, d, band, shade(base, 0.8))


def make_ammo_uv(ammo) -> Image.Image:
    img, _ = new_tex()
    w, h, d = ammo["size"]
    paint_box(img, w, h, d, 0, 0, ammo["body"], ammo["tip"], ammo["base"])
    return img


def make_shell_uv(ammo) -> Image.Image:
    img, _ = new_tex()
    w, h, d = ammo["size"]
    sw, sh, sd = max(1, w - 1), 3, max(1, d - 1)
    # 弹壳：黄铜色，顶部开口暗色
    paint_box(img, sw, sh, sd, 0, 32, ammo["base"], shade(ammo["base"], 0.6), shade(ammo["base"], 0.7))
    return img


def make_slot_icon(ammo) -> Image.Image:
    """32x32 物品栏图标：一枚立着的弹药，纯原创绘制。"""
    img = Image.new("RGBA", (SLOT_W, SLOT_H), (0, 0, 0, 0))
    dr = ImageDraw.Draw(img)

    body = ammo["body"]
    tip = ammo["tip"]
    base = ammo["base"]

    w, h, d = ammo["size"]
    # 竖着画，按弹药长度决定高度
    total_h = 8 + h * 2
    top = (SLOT_H - total_h) // 2 + 1
    cx = SLOT_W // 2
    half = max(2, int(round(w * 0.9)))

    tip_h = max(3, total_h // 4)
    body_h = total_h - tip_h

    # 弹头（上窄下宽的多边形）
    dr.polygon(
        [(cx, top), (cx + half, top + tip_h), (cx + half, top + tip_h + 1),
         (cx - half, top + tip_h + 1), (cx - half, top + tip_h)],
        fill=tip + (255,),
    )
    dr.rectangle([cx - half, top + tip_h, cx + half - 1, top + tip_h + body_h],
                 fill=body + (255,))
    # 底缘
    dr.rectangle([cx - half - 1, top + tip_h + body_h - 3,
                  cx + half, top + tip_h + body_h + 1], fill=base + (255,))
    # 高光与描边，避免看起来是纯色块
    dr.line([(cx - half + 1, top + tip_h + 1), (cx - half + 1, top + tip_h + body_h - 4)],
            fill=shade(body, 1.25) + (255,))
    dr.line([(cx + half - 1, top + tip_h + 1), (cx + half - 1, top + tip_h + body_h - 4)],
            fill=shade(body, 0.6) + (255,))
    return img


# ---------------------------------------------------------------- 主流程

def main() -> None:
    lang_zh = {}
    lang_en = {}
    pack_name_key = f"pack.{NS}.ammo_unify_core.name"
    pack_desc_key = f"pack.{NS}.ammo_unify_core.desc"
    lang_zh[pack_name_key] = "铳械弹药统一 · 核心包"
    lang_zh[pack_desc_key] = "通用弹药与配方，素材由本模组脚本生成"
    lang_en[pack_name_key] = "Ammo Unify · Core Pack"
    lang_en[pack_desc_key] = "Universal ammunition and recipes, assets generated by this mod's script"

    for ammo in AMMO:
        ammo.setdefault("tracer", None)

    # 曳光色：按弹种给个区分度
    tracer = {
        "cartridge_pistol": "#FFCC66",
        "cartridge_rifle": "#FFD27F",
        "shell_shotgun": "#FF9A5A",
        "cartridge_sniper": "#9CE07A",
        "shell_barrel": "#FF7A3C",
        "tank_fuel": "#66E0FF",
    }

    for ammo in AMMO:
        aid = ammo["id"]
        ammo["tracer"] = tracer[aid]

        # ---- 数据：弹药索引 ----
        write_json(RES / "data" / NS / "index" / "ammo" / f"{aid}.json", index_json(ammo))
        # ---- 数据：枪械工作台配方 ----
        write_json(RES / "data" / NS / "recipe" / "ammo" / f"{aid}.json", recipe_json(ammo))

        # ---- 资源：display ----
        write_json(RES / "assets" / NS / "display" / "ammo" / f"{aid}_display.json",
                   display_json(ammo))
        # ---- 资源：几何模型 ----
        write_json(RES / "assets" / NS / "geo_models" / "ammo" / f"{aid}.json",
                   ammo_geometry(ammo))
        write_json(RES / "assets" / NS / "geo_models" / "shell" / f"{aid}_shell.json",
                   shell_geometry(ammo))

        # ---- 资源：贴图 ----
        uv_dir = RES / "assets" / NS / "textures" / "ammo" / "uv"
        slot_dir = RES / "assets" / NS / "textures" / "ammo" / "slot"
        shell_dir = RES / "assets" / NS / "textures" / "shell"
        for d in (uv_dir, slot_dir, shell_dir):
            d.mkdir(parents=True, exist_ok=True)

        make_ammo_uv(ammo).save(uv_dir / f"{aid}.png")
        make_slot_icon(ammo).save(slot_dir / f"{aid}.png")
        make_shell_uv(ammo).save(shell_dir / f"{aid}_shell.png")

        # ---- 语言 ----
        key = f"{NS}.ammo.{aid}"
        lang_zh[f"{key}.name"] = ammo["zh"]
        lang_zh[f"{key}.tooltip"] = "通用弹药 · 按武器类别统一"
        lang_en[f"{key}.name"] = ammo["en"]
        lang_en[f"{key}.tooltip"] = "Universal ammunition · unified by weapon class"

    # ---- 语言文件 ----
    write_json(RES / "assets" / NS / "lang" / "zh_cn.json", lang_zh)
    write_json(RES / "assets" / NS / "lang" / "en_us.json", lang_en)

    # ---- 枪包信息（本包素材由本脚本生成，因此按本模组的许可授权）----
    write_json(RES / "assets" / NS / "gunpack_info.json", {
        "version": "1.0.0",
        "name": pack_name_key,
        "desc": pack_desc_key,
        "license": "AGPL-3.0-only",
        "authors": ["Ammo Unify"],
        "date": "2025-01-01",
    })

    # ---- pack.mcmeta（1.21.1 与 TaCZ 自身保持一致使用 48）----
    write_json(RES / "pack.mcmeta", {
        "pack": {
            "description": f"{NS} resources",
            "pack_format": 48,
        }
    })

    print(f"已生成 {len(AMMO)} 种弹药的资源，输出目录：{RES}")


if __name__ == "__main__":
    main()
