# -*- coding: utf-8 -*-
"""Verify asset references with correct key awareness (parent vs textures)."""
import os, re, json

BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1'
RES = os.path.join(BASE, 'src', 'main', 'resources')
JAVA = os.path.join(BASE, 'src', 'main', 'java')
ASSETS = os.path.join(RES, 'assets', 'dyairdrop')

def exists(rel):
    return os.path.exists(os.path.join(ASSETS, rel))

missing = []

java = ''
for root, dirs, files in os.walk(JAVA):
    for fn in files:
        if fn.endswith('.java'):
            java += open(os.path.join(root, fn), encoding='utf-8').read()

# java -> textures / geo / animations
for m in re.finditer(r'fromNamespaceAndPath\("dyairdrop",\s*"([^"]+\.png)"\)', java):
    if not exists(m.group(1)):
        missing.append(('java-texture', m.group(1)))
for m in re.finditer(r'TEXTURE,\s*"([a-z0-9_]+)"', java):
    if not exists('textures/entities/%s.png' % m.group(1)):
        missing.append(('entity-texture', m.group(1)))
for m in re.finditer(r'fromNamespaceAndPath\("dyairdrop",\s*"((?:geo|animations)/[^"]+\.json)"\)', java):
    if not exists(m.group(1)):
        missing.append(('java-geo-anim', m.group(1)))

# json files: parent refs and texture refs
for root, dirs, files in os.walk(ASSETS):
    for fn in files:
        if not fn.endswith('.json'):
            continue
        p = os.path.join(root, fn)
        rel = os.path.relpath(p, ASSETS).replace('\\', '/')
        data = json.load(open(p, encoding='utf-8'))

        def check_parent(val, ctx):
            if isinstance(val, str) and val.startswith('dyairdrop:'):
                if not exists('models/%s.json' % val.split(':', 1)[1]):
                    missing.append(('parent', '%s -> %s' % (ctx, val)))

        def check_tex(val, ctx):
            if isinstance(val, str) and val.startswith('dyairdrop:'):
                tp = val.split(':', 1)[1]
                if not exists('textures/%s.png' % tp):
                    missing.append(('texture', '%s -> %s' % (ctx, tp)))

        def walk(node, ctx):
            if isinstance(node, dict):
                for k, v in node.items():
                    if k == 'parent' or k == 'model':
                        check_parent(v, ctx)
                    elif k in ('textures',):
                        if isinstance(v, dict):
                            for tv in v.values():
                                check_tex(tv, ctx)
                    else:
                        walk(v, ctx)
            elif isinstance(node, list):
                for it in node:
                    walk(it, ctx)

        walk(data, rel)

# particles
pd = os.path.join(ASSETS, 'particles')
if os.path.isdir(pd):
    for fn in os.listdir(pd):
        d = json.load(open(os.path.join(pd, fn), encoding='utf-8'))
        for tex in d.get('textures', []):
            check_tex(tex, 'particles/' + fn) if tex.startswith('dyairdrop:') else None

# sounds
snd = json.load(open(os.path.join(ASSETS, 'sounds.json'), encoding='utf-8'))
for key, val in snd.items():
    for s in val.get('sounds', []):
        nm = s['name'].split(':')[-1]
        if not os.path.exists(os.path.join(ASSETS, 'sounds', nm + '.ogg')):
            missing.append(('sound', nm))

# geo / animations consistency: every animations/*.animation.json and geo/*.geo.json referenced?
print('=== missing: %d ===' % len(missing))
for kind, item in missing:
    print(' [%s] %s' % (kind, item))
if not missing:
    print('全部资源引用一致')
