# -*- coding: utf-8 -*-
"""Generate ORIGINAL model JSONs (custom/item/block/displaysettings). Blockstates kept (functional)."""
import os, json

A = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\resources\assets\dyairdrop'
M = os.path.join(A, 'models')
for sub in ('custom', 'item', 'block', 'displaysettings'):
    os.makedirs(os.path.join(M, sub), exist_ok=True)
    for f in os.listdir(os.path.join(M, sub)):
        os.remove(os.path.join(M, sub, f))

def w(path, obj):
    with open(path, 'w', encoding='utf-8') as f:
        json.dump(obj, f, indent=2)

def particle_model(texname):
    return {"parent": "block/block", "textures": {"particle": "dyairdrop:block/" + texname}}

# ---- custom: particle stubs for geo blocks (blockstate references) ----
custom = {
    'airdroplarge_particle': 'largeairdrop',
    'airdropmedical_particle': 'gre',
    'airdropweapon_particle': 'millitaryloot',
    'lockedairdroplarge_particle': 'millitaryloot',
    'lockedairdroplargeopen_particle': 'millitaryloot',
    'lockedairdropmedical_particle': 'gre',
    'lockedairdropmedicalopen_particle': 'gre',
    'lockedairdropweapon_particle': 'millitaryloot',
    'lockedairdropweaponopen_particle': 'millitaryloot',
    'safe_particle': 'safe',
    'safe_2_particle': 'safe',
    'safeopen_particle': 'safe',
}
for name, tex in custom.items():
    w(os.path.join(M, 'custom', name + '.json'), particle_model(tex))

# ---- custom: flare gun element models (original shape: top-break pistol) ----
def gun_model(accent_idx):
    """Item-space model; texture #4 is the 64x64 gun skin."""
    return {
        "credit": "original design",
        "texture_size": [64, 64],
        "textures": {"4": "dyairdrop:block/flare_gunneo", "particle": "dyairdrop:block/flare_gunneo"},
        "elements": [
            {"from": [2.0, 6.0, 6.0], "to": [5.0, 11.0, 10.0],
             "faces": {"north": {"uv": [4, 4, 12, 12], "texture": "#4"},
                       "south": {"uv": [4, 4, 12, 12], "texture": "#4"},
                       "east": {"uv": [4, 4, 12, 12], "texture": "#4"},
                       "west": {"uv": [4, 4, 12, 12], "texture": "#4"},
                       "up": {"uv": [4, 4, 12, 12], "texture": "#4"},
                       "down": {"uv": [4, 4, 12, 12], "texture": "#4"}}},
            {"from": [5.0, 8.5, 6.5], "to": [14.0, 11.5, 9.5],
             "faces": {"north": {"uv": [16, 4, 40, 10], "texture": "#4"},
                       "south": {"uv": [16, 4, 40, 10], "texture": "#4"},
                       "east": {"uv": [4, 4, 10, 10], "texture": "#4"},
                       "west": {"uv": [4, 4, 10, 10], "texture": "#4"},
                       "up": {"uv": [16, 4, 40, 10], "texture": "#4"},
                       "down": {"uv": [16, 4, 40, 10], "texture": "#4"}}},
            {"from": [12.0, 6.0, 7.0], "to": [14.0, 8.5, 9.0],
             "faces": {"north": {"uv": [40, 4, 44, 8], "texture": "#4"},
                       "south": {"uv": [40, 4, 44, 8], "texture": "#4"},
                       "east": {"uv": [4, 4, 8, 8], "texture": "#4"},
                       "west": {"uv": [4, 4, 8, 8], "texture": "#4"},
                       "up": {"uv": [40, 4, 44, 8], "texture": "#4"},
                       "down": {"uv": [40, 4, 44, 8], "texture": "#4"}}},
        ],
        "display": {
            "thirdperson_righthand": {"rotation": [0, 90, -20], "translation": [0, 1, 0], "scale": [0.55, 0.55, 0.55]},
            "thirdperson_lefthand": {"rotation": [0, 90, -20], "translation": [0, 1, 0], "scale": [0.55, 0.55, 0.55]},
            "firstperson_righthand": {"rotation": [0, 90, -10], "scale": [0.68, 0.68, 0.68]},
            "firstperson_lefthand": {"rotation": [0, 90, -10], "scale": [0.68, 0.68, 0.68]},
            "ground": {"scale": [0.5, 0.5, 0.5]},
            "gui": {"rotation": [0, -135, 25], "scale": [0.9, 0.9, 0.9]},
            "fixed": {"rotation": [0, 90, 0], "scale": [0.8, 0.8, 0.8]}
        }
    }
for i in range(5):
    g = gun_model(i)
    if i == 0:
        w(os.path.join(M, 'custom', 'flare_gun.json'), g)
    else:
        w(os.path.join(M, 'custom', 'flare_gun%d.json' % i), g)

# ---- custom: airdrop/airdrop2/greairdrop (simple element crates used by item models) ----
def mini_crate():
    return {
        "credit": "original design",
        "texture_size": [64, 64],
        "textures": {"0": "dyairdrop:block/largeairdrop", "particle": "dyairdrop:block/largeairdrop"},
        "elements": [
            {"from": [1.0, 0.0, 3.0], "to": [15.0, 12.0, 13.0],
             "faces": {f: {"uv": [0, 0, 16, 16], "texture": "#0"} for f in ("north","south","east","west","up","down")}},
            {"from": [1.0, 12.0, 3.0], "to": [15.0, 15.0, 13.0],
             "faces": {f: {"uv": [16, 0, 32, 16], "texture": "#0"} for f in ("north","south","east","west","up","down")}},
        ]
    }
w(os.path.join(M, 'custom', 'airdrop.json'), mini_crate())
w(os.path.join(M, 'custom', 'airdrop2.json'), mini_crate())
w(os.path.join(M, 'custom', 'greairdrop.json'), mini_crate())

# ---- block: plain blocks (airdropsmall, lockedairdropsmall/open + weapon open stubs) ----
w(os.path.join(M, 'block', 'airdropsmall.json'), {
    "parent": "block/cube_all", "textures": {"all": "dyairdrop:block/gre", "particle": "dyairdrop:block/gre"}})
w(os.path.join(M, 'block', 'lockedairdropsmall.json'), {
    "parent": "block/cube_all", "textures": {"all": "dyairdrop:block/millitaryloot", "particle": "dyairdrop:block/millitaryloot"}})
w(os.path.join(M, 'block', 'lockedairdropsmallopen.json'), {
    "parent": "block/cube_all", "textures": {"all": "dyairdrop:block/millitaryloot", "particle": "dyairdrop:block/millitaryloot"}})
w(os.path.join(M, 'block', 'airdropweapon_blockstate_0.json'),
  {"parent": "dyairdrop:custom/airdropweapon_particle"})
w(os.path.join(M, 'block', 'lockedairdropweaponopen_blockstate_0.json'),
  {"parent": "dyairdrop:custom/lockedairdropweaponopen_particle"})

# ---- item models ----
items = {
    'airdroplarge': {"parent": "dyairdrop:displaysettings/airdroplarge"},
    'airdropmedical': {"parent": "dyairdrop:displaysettings/grechest3"},
    'airdropweapon': {"parent": "dyairdrop:displaysettings/airdropweaponblock.item"},
    'airdropsmall': {"parent": "dyairdrop:block/airdropsmall"},
    'safe': {"parent": "dyairdrop:displaysettings/safe.item"},
    'safe_2': {"parent": "dyairdrop:displaysettings/safe.item"},
    'safeopen': {"parent": "dyairdrop:displaysettings/safeopen.item"},
    'lockedairdroplarge': {"parent": "dyairdrop:displaysettings/lockedairdroplarge.item"},
    'lockedairdroplargeopen': {"parent": "dyairdrop:displaysettings/lockedairdroplargeopen.item"},
    'lockedairdropmedical': {"parent": "dyairdrop:custom/airdrop2"},
    'lockedairdropmedicalopen': {"parent": "dyairdrop:custom/airdrop2"},
    'lockedairdropweapon': {"parent": "dyairdrop:custom/airdrop2"},
    'lockedairdropweaponopen': {"parent": "dyairdrop:custom/lockedairdropweaponopen_particle"},
    'lockedairdropsmall': {"parent": "dyairdrop:block/lockedairdropsmall"},
    'lockedairdropsmallopen': {"parent": "dyairdrop:block/lockedairdropsmallopen"},
}
for name, obj in items.items():
    w(os.path.join(M, 'item', name + '.json'), obj)
for i in range(5):
    parent = 'dyairdrop:custom/flare_gun' if i == 0 else 'dyairdrop:custom/flare_gun%d' % i
    w(os.path.join(M, 'item', 'flaregun%d.json' % i), {
        "parent": parent,
        "textures": {"4": "dyairdrop:block/flare_gunneo%d" % i, "particle": "dyairdrop:item/flare_gunneo%d" % i}})
w(os.path.join(M, 'item', 'flaregun0.json'), {
    "parent": "dyairdrop:custom/flare_gun",
    "textures": {"4": "dyairdrop:block/flare_gunneo", "particle": "dyairdrop:item/flare_gunneo"}})

# ---- displaysettings ----
def display_settings(particle_tex):
    return {
        "credit": "original design",
        "parent": "builtin/entity",
        "texture_size": [128, 128],
        "display": {
            "thirdperson_righthand": {"rotation": [0, 0, 0], "translation": [0, 1, 0], "scale": [0.55, 0.55, 0.55]},
            "thirdperson_lefthand": {"rotation": [0, 0, 0], "translation": [0, 1, 0], "scale": [0.55, 0.55, 0.55]},
            "firstperson_righthand": {"rotation": [0, -15, 0], "translation": [0, 2, 0], "scale": [0.6, 0.6, 0.6]},
            "firstperson_lefthand": {"rotation": [0, 15, 0], "translation": [0, 2, 0], "scale": [0.6, 0.6, 0.6]},
            "ground": {"translation": [0, 2, 0], "scale": [0.4, 0.4, 0.4]},
            "gui": {"rotation": [25, 215, 0], "translation": [0, 0, 0], "scale": [0.7, 0.7, 0.7]},
            "fixed": {"rotation": [0, 180, 0], "scale": [0.65, 0.65, 0.65]}
        },
        "textures": {"particle": particle_tex}
    }
ds = {
    'airdroplarge': 'largeairdrop',
    'grechest3': 'gre',
    'airdropweaponblock.item': 'millitaryloot',
    'safe.item': 'safe',
    'safeopen.item': 'safe',
    'lockedairdroplarge.item': 'millitaryloot',
    'lockedairdroplargeopen.item': 'millitaryloot',
}
for name, tex in ds.items():
    w(os.path.join(M, 'displaysettings', name + '.json'), display_settings(tex))

print('model JSONs generated')
