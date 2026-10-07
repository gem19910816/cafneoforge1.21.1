"""药草作物：8 个生长阶段的独立方块模型生成器。

设计（对照用户要求：像原版小麦、从一点芽慢慢长高、阶段5起出现蓝色）：
- 每一阶段一个 bbmodel（java_block 格式），16x16 贴图，1 像素 = 1 单位。
- 贴图 = 一簇真正的草药叶：底部两行密实扎根，多条 1px 叶尖自下而上生长、
  微微弯折，中间高两边低（拱形轮廓），下暗上亮。阶段 >=5 在叶尖挂蓝色小花。
- 几何 = 十字交叉的两张主卡（±45°），阶段 >=2 再加 1~2 张错位的侧卡
  （取同一贴图的底部切片），整体像小麦但更立体。
- 高度逐级递增：3 → 15 单位（一格 = 16）。

    PYTHONPATH=/opt/model-skill/lib python3 make_models.py
"""
import random

# ---------------- 调色板 ----------------
DARK   = (34, 64, 22)
MID    = (58, 100, 32)
BRIGHT = (88, 138, 46)
TIP    = (124, 174, 66)
BLU    = (64, 108, 212)
BLU_L  = (138, 174, 244)
BLU_D  = (36, 60, 150)


def lerp(a, b, t):
    t = max(0.0, min(1.0, t))
    return tuple(round(a[i] + (b[i] - a[i]) * t) for i in range(3))


from bbmodel import Model


def px(m, reg, u, v, rgb):
    m.px(reg, u, v, (rgb[0], rgb[1], rgb[2], 255))


def draw_blade(m, reg, W, H, flower, rnd):
    """画一簇 W×H 的草药叶：v=0 是顶部（卡片上沿），v=H-1 是根部。

    flower: 0 无花；1 少量蓝点；2 开花；3 盛放（蓝花最多最亮）。
    """
    def at(u, v, rgb):
        if 0 <= u < W and 0 <= v < H:
            px(m, reg, u, v, rgb)

    # ---- 叶尖：自下而上生长 ----
    # 起点铺满底部每一格（偶尔两格宽），保证根部密实
    tips = []
    for u0 in range(W):
        if u0 % 2 == 1 and rnd.random() < 0.35:
            continue  # 稍作稀疏，避免完全等距的栅栏感
        # 拱形：中间高、两边低；t 是该叶尖能到的最高行（越小越高）
        edge = abs(u0 - (W - 1) / 2.0) / ((W - 1) / 2.0 + 1e-6)  # 0 中 1 边
        reach = H - 1 - max(1, int(round((H - 1) * (1.0 - 0.75 * edge) * rnd.uniform(0.72, 1.0))))
        reach = max(0, min(reach, H - 2))
        tips.append((u0, reach))

    for (u0, top) in tips:
        u = u0
        bend = rnd.choice([0, 0, 1, -1])
        for v in range(H - 1, top - 1, -1):
            t = (H - 1 - v) / max(1, H - 1 - top)          # 0 根 1 尖
            col = lerp(DARK, TIP, 0.15 + 0.85 * t)
            at(u, v, col)
            if v >= H - 3 and rnd.random() < 0.5:           # 根部偶尔加粗一格
                du = 1 if rnd.random() < 0.5 else -1
                at(u + du, v, lerp(col, DARK, 0.4))
            # 中段偶尔伸出一片短侧叶（向斜上 1~2 格）
            if top < H - 4 and v == (H - 1 + top) // 2 and rnd.random() < 0.5:
                side = 1 if rnd.random() < 0.5 else -1
                at(u + side, v - 1, lerp(col, BRIGHT, 0.5))
            if v > top and bend and (H - 1 - v) % 3 == 2:   # 叶尖微弯
                u = max(0, min(W - 1, u + bend))
        # 最顶一格最亮（嫩尖）
        at(u, top, TIP)
        if top > 0 and rnd.random() < 0.6:
            at(u, top - 1 if top - 1 >= 0 else 0, lerp(TIP, BRIGHT, 0.6)) if top >= 1 else None

    # 保证顶部中心有尖（轮廓收在中间，像长高了的草）
    mid = (W - 1) // 2
    hi = min(t for (_, t) in tips)
    at(mid, hi, TIP)

    # ---- 蓝色花点：贴在较高叶尖的上段两侧 ----
    if flower >= 1:
        tall = sorted(tips, key=lambda x: x[1])[: 2 + flower]
        for (u0, top) in tall:
            v2 = top + 1 + rnd.randint(0, 1)
            du = rnd.choice([-1, 1])
            at(u0 + du, v2, BLU)
            at(u0 + du, v2 + 1, BLU_D)
    if flower >= 2:
        tall = sorted(tips, key=lambda x: x[1])
        for (u0, top) in tall[:4]:
            for v2 in (top + 1, top + 3):
                du = -1 if u0 % 2 else 1
                at(u0 + du, v2, BLU_L if v2 == top + 1 else BLU)
                at(u0 + du, v2 + 1, BLU_D)
    if flower >= 3:
        # 盛放：中心一簇最亮的花 + 更多蓝点
        for (u, v2) in [(mid, 1), (mid - 1, 2), (mid + 1, 2), (mid, 3)]:
            at(u, v2, BLU_L)
        for (u0, top) in tips:
            if top <= 2 and rnd.random() < 0.8:
                at(u0, top + 1, BLU)
                at(u0, top + 2, BLU_D)

    # ---- 做旧：只给已有像素加深浅，透明处保持透明 ----
    rx0, ry0, rx1, ry1 = m.regions[reg]
    for yy in range(ry0, ry1):
        for xx in range(rx0, rx1):
            r0, g0, b0, a0 = m.tex[yy][xx]
            if a0 and (r0, g0, b0) not in (BLU, BLU_L, BLU_D) and rnd.random() < 0.12:
                tgt = DARK if rnd.random() < 0.5 else BRIGHT
                m.tex[yy][xx] = (*lerp((r0, g0, b0), tgt, 0.3), 255)


def card(m, name, cx, cz, w, h, ang, rect, inflate=0.0):
    """一张叶片卡片：零厚度四边形，绕 (cx,8,cz) 转 ang 度。
    rect = 贴图上 (u1,v1,u2,v2)，v 从上到下；卡片底边沉到 y=-0.5 避免与耕地接缝。"""
    uv = {f: tuple(float(x) for x in rect) for f in
          ("north", "south", "east", "west", "up", "down")}
    m.cube(name, (cx - w / 2.0, -0.5, cz), (cx + w / 2.0, h - 0.5, cz),
           uv=uv, rotation=(0, ang, 0), origin=(cx, 8.0, cz), inflate=inflate)


def subrect(W, H, w, h):
    """在 16x16 贴图的 (0,0) 处 W×H 精灵里，取底部对齐、水平居中的 w×h 切片。"""
    sx = (W - w) // 2
    return (sx, H - h, sx + w, H)


# ---------------- 阶段参数 ----------------
# 每阶段: 主精灵宽 W、高 H（=卡片高）、花级 0-3、侧卡 [(w,h,沿垂直方向偏移量)...]
STAGES = {
    0: dict(W=8,  H=4,  flower=0, sides=[]),
    1: dict(W=9,  H=6,  flower=0, sides=[]),
    2: dict(W=10, H=8,  flower=0, sides=[(0.55, 0.55, 3.0)]),
    3: dict(W=11, H=10, flower=0, sides=[(0.55, 0.6, 3.0), (0.5, 0.45, 3.5)]),
    4: dict(W=12, H=11, flower=0, sides=[(0.6, 0.65, 3.0), (0.5, 0.5, 3.5)]),
    5: dict(W=12, H=13, flower=1, sides=[(0.6, 0.7, 3.0), (0.55, 0.55, 3.5)]),
    6: dict(W=13, H=14, flower=2, sides=[(0.6, 0.75, 3.0), (0.55, 0.6, 3.5)]),
    7: dict(W=14, H=15, flower=3, sides=[(0.6, 0.8, 3.0), (0.6, 0.65, 3.5)]),
}


def build(stage):
    p = STAGES[stage]
    W, H = p["W"], p["H"]
    rnd = random.Random(2024 + stage)
    m = Model(f"herb_crop_stage{stage}", tex_size=16)

    m.region("blade", 0, 0, W, H)
    draw_blade(m, "blade", W, H, p["flower"], rnd)

    # 主十字：两张 ±45° 的完整叶簇卡
    card(m, "main_a", 8, 8, W, H, 45, (0, 0, W, H))
    card(m, "main_b", 8, 8, W, H, -45, (0, 0, W, H))

    # 侧卡：沿主卡垂直方向错位一小步，用同一贴图的底部切片 → 同一丛的矮叶
    import math
    for i, (fw, fh, off) in enumerate(p["sides"]):
        w = max(4, int(round(W * fw)))
        h = max(3, int(round(H * fh)))
        ang = 45 if i % 2 == 0 else -45
        # 卡片绕 Y 转 ang 后，其法线方向 = (cos, 0, -sin) 旋转；沿法线平移 off
        a = math.radians(ang)
        nx, nz = math.sin(a), math.cos(a)   # 法线（垂直于卡面）
        cx = 8 + nx * off * (1 if i % 2 == 0 else -1)
        cz = 8 + nz * off * (1 if i % 2 == 0 else -1)
        card(m, f"side_{i}", cx, cz, w, h, ang, subrect(W, H, w, h), inflate=0.01)

    m.save(f"out/herb_crop_stage{stage}.bbmodel", model_format="java_block")
    return m


if __name__ == "__main__":
    import os
    os.makedirs("out", exist_ok=True)
    for s in range(8):
        m = build(s)
        lo, hi = m.bounds()
        print(f"stage {s}: ok  bbox y {lo[1]:.1f}..{hi[1]:.1f}")
