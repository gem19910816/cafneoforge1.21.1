# -*- coding: utf-8 -*-
"""Generate ORIGINAL GeckoLib geo models (Bedrock 1.12.0) + animations (1.8.0)."""
import os, json

A = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\resources\assets\dyairdrop'
GEO = os.path.join(A, 'geo')
ANIM = os.path.join(A, 'animations')
os.makedirs(GEO, exist_ok=True)
os.makedirs(ANIM, exist_ok=True)

def cube(origin, size, uv, texw=128, texh=128, pivot=None):
    """Box-UV cube: standard minecraft box layout placed at uv slot."""
    x, y, z = origin
    w, h, d = size
    u, v = uv
    c = {
        "origin": [x, y, z],
        "size": [w, h, d],
        "uv": [u, v],
    }
    return c

def bone(name, pivot, cubes, parent=None):
    b = {"name": name, "pivot": pivot, "cubes": cubes}
    if parent:
        b["parent"] = parent
    return b

def geo(identifier, texture_w, texture_h, bones, vbw=4, vbh=4):
    return {
        "format_version": "1.12.0",
        "minecraft:geometry": [{
            "description": {
                "identifier": "geometry." + identifier,
                "texture_width": texture_w,
                "texture_height": texture_h,
                "visible_bounds_width": vbw,
                "visible_bounds_height": vbh,
                "visible_bounds_offset": [0, 0.75, 0]
            },
            "bones": bones
        }]
    }

# Slot allocator on the texture sheet to avoid UV overlap
class Slots:
    def __init__(self, texw=128, texh=128, rowh=34):
        self.u = 0; self.v = 0; self.texw = texw; self.texh = texh; self.rowh = rowh
    def next(self, w, h):
        if self.u + w > self.texw:
            self.u = 0; self.v += self.rowh
        slot = (self.u, self.v)
        self.u += w + 2
        return slot

def crate_geo(identifier, body_size, lid_h, door=False, texw=128, texh=128):
    """Original crate design: slab feet + main body + hinged lid (+ lock stub)."""
    bw, bh, bd = body_size
    bx, bz = -bw/2.0, -bd/2.0
    slots = Slots(texw, texh)
    feet = [
        cube([-bw/2+1, 0, -bd/2+1], [bw-2, 2, 2], slots.next(bw, 6)),
        cube([-bw/2+1, 0, bd/2-3], [bw-2, 2, 2], slots.next(bw, 6)),
    ]
    body = [cube([bx, 2, bz], [bw, bh, bd], slots.next(2*bw, bh+bd))]
    lid_pivot = [0, 2+bh, -bd/2]
    lid = [cube([bx, 2+bh, bz], [bw, lid_h, bd], slots.next(2*bw, lid_h+bd))]
    bones = [
        bone("root", [0, 0, 0], feet + body),
        bone("lid", lid_pivot, lid, parent="root"),
        bone("door", [bx+1, 2+bh/2, bz+bd/2], [cube([bx+1, 2+bh/2, bz+bd/2], [3, 4, 3], slots.next(8, 10))], parent="root"),
    ]
    if door:
        bones.append(bone("lock", [bw/2-2, 2+bh/2, bz+bd/2], [cube([bw/2-2, 2+bh/2-1, bz+bd/2-1], [2, 5, 2], slots.next(8, 10))], parent="root"))
    return geo(identifier, texw, texh, bones, vbw=max(3, bw/16+1), vbh=max(3, (bh+lid_h)/16+1))

models = {
    "airdroplarge": crate_geo("airdroplarge", (26, 11, 18), 4),
    "airdropmedical": crate_geo("airdropmedical", (20, 12, 15), 4),
    "airdropweapon": crate_geo("airdropweapon", (22, 12, 16), 5),
    "airdropweaponblock": crate_geo("airdropweaponblock", (18, 10, 14), 4),
    "airdropweaponblockopen": crate_geo("airdropweaponblockopen", (18, 10, 14), 4),
    "gairdrop": crate_geo("gairdrop", (14, 12, 14), 3),
    "gairdrop2": crate_geo("gairdrop2", (14, 12, 14), 3),
    "grechest": crate_geo("grechest", (16, 11, 13), 3),
    "grechest2": crate_geo("grechest2", (16, 11, 13), 3),
    "lockedairdroplarge": crate_geo("lockedairdroplarge", (26, 11, 18), 4, door=True),
    "lockedairdroplargeopen": crate_geo("lockedairdroplargeopen", (26, 11, 18), 4, door=True),
    "safe": crate_geo("safe", (13, 12, 13), 3, door=True),
    "safeopen": crate_geo("safeopen", (13, 12, 13), 3, door=True),
    "model": crate_geo("model", (14, 12, 14), 3),
}

# plane: original design - segmented fuselage, high wings, twin tail
def plane_geo(identifier):
    texw = texh = 128
    slots = Slots(texw, texh, rowh=46)
    seg = [cube([-20, 16, -10], [40, 20, 20], slots.next(120, 60)),
           cube([20, 16, -10], [40, 20, 20], slots.next(120, 60)),
           cube([-60, 16, -10], [40, 20, 20], slots.next(120, 60)),
           cube([-90, 18, -8], [30, 16, 16], slots.next(94, 50))]
    wing_l = [cube([-18, 30, -95], [70, 3, 70], slots.next(120, 76))]
    wing_r = [cube([-18, 30, 25], [70, 3, 70], slots.next(120, 76))]
    tail = [cube([-88, 34, -2], [24, 22, 4], slots.next(48, 46)),
            cube([-88, 34, -26], [24, 4, 24], slots.next(48, 46)),
            cube([-88, 34, 2], [24, 4, 24], slots.next(48, 46))]
    bones = [
        bone("root", [0, 0, 0], []),
        bone("fuselage", [0, 16, 0], seg, parent="root"),
        bone("wingl", [-18, 30, -25], wing_l, parent="root"),
        bone("wingr", [-18, 30, 25], wing_r, parent="root"),
        bone("tail", [-88, 34, 0], tail, parent="root"),
    ]
    return geo(identifier, texw, texh, bones, vbw=12, vbh=6)

models["plane"] = plane_geo("plane")

for name, g in models.items():
    with open(os.path.join(GEO, name + '.geo.json'), 'w', encoding='utf-8') as f:
        json.dump(g, f, indent=1)
print('geo models:', len(models))

# ---------- animations ----------
def rot(x=0, y=0, z=0):
    return {"rotation": [x, y, z]}

def anim_clips(lid_open_deg=-105, door_open_deg=-95, bob=True):
    clips = {}
    # "0": rest
    clips["0"] = {"loop": True, "animation_length": 1.0,
                  "bones": {"lid": {"rotation": {"0.0": [0,0,0], "1.0": [0,0,0]}},
                            "door": {"rotation": {"0.0": [0,0,0], "1.0": [0,0,0]}}}}
    # "1": open
    clips["1"] = {"loop": "hold_on_last_frame", "animation_length": 1.0,
                  "bones": {"lid": {"rotation": {"0.0": [0,0,0], "0.8": [lid_open_deg,0,0]}},
                            "door": {"rotation": {"0.0": [0,0,0], "0.8": [0,0,door_open_deg]}}}}
    # "2": close
    clips["2"] = {"loop": "hold_on_last_frame", "animation_length": 1.0,
                  "bones": {"lid": {"rotation": {"0.0": [lid_open_deg,0,0], "0.8": [0,0,0]}},
                            "door": {"rotation": {"0.0": [0,0,door_open_deg], "0.8": [0,0,0]}}}}
    # "idle": gentle bob
    if bob:
        clips["idle"] = {"loop": True, "animation_length": 2.0,
                         "bones": {"lid": {"rotation": {"0.0": [0,0,0], "1.0": [1.5,0,0], "2.0": [0,0,0]}}}}
    else:
        clips["idle"] = {"loop": True, "animation_length": 1.0, "bones": {}}
    return clips

crate_names = ["airdroplarge", "airdropmedical", "airdropweapon", "airdropweaponblock",
               "airdropweaponblockopen", "gairdrop", "gairdrop2", "grechest", "grechest2",
               "lockedairdroplarge", "lockedairdroplargeopen", "safe", "safeopen", "model"]
for name in crate_names:
    with open(os.path.join(ANIM, name + '.animation.json'), 'w', encoding='utf-8') as f:
        json.dump({"format_version": "1.8.0", "animations": anim_clips()}, f, indent=1)

# plane animation: idle only
plane_anim = {"format_version": "1.8.0", "animations": {
    "idle": {"loop": True, "animation_length": 2.0,
             "bones": {"fuselage": {"rotation": {"0.0": [0,0,0], "1.0": [0.8,0,0], "2.0": [0,0,0]}}}},
}}
with open(os.path.join(ANIM, 'plane.animation.json'), 'w', encoding='utf-8') as f:
    json.dump(plane_anim, f, indent=1)
print('animations:', len(crate_names) + 1)
