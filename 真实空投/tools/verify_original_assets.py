# -*- coding: utf-8 -*-
"""Verify original assets after swap: all code/JSON references resolve."""
import os, re, json

B = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1'
A = os.path.join(B, 'src', 'main', 'resources', 'assets', 'dyairdrop')
J = os.path.join(B, 'src', 'main', 'java')

def ex(rel):
    return os.path.exists(os.path.join(A, rel))

java = ''
for root, dirs, files in os.walk(J):
    for fn in files:
        if fn.endswith('.java'):
            java += open(os.path.join(root, fn), encoding='utf-8').read()

missing = []

# 1. java -> textures
for m in re.finditer(r'fromNamespaceAndPath\("dyairdrop",\s*"([^"]+\.png)"\)', java):
    if not ex(m.group(1)):
        missing.append(('java-tex', m.group(1)))
# 2. java -> geo / animations
for m in re.finditer(r'fromNamespaceAndPath\("dyairdrop",\s*"((?:geo|animations)/[^"]+\.json)"\)', java):
    if not ex(m.group(1)):
        missing.append(('java-geo-anim', m.group(1)))
# 3. java -> dynamic entity textures
for m in re.finditer(r'TEXTURE,\s*"([a-z0-9_]+)"', java):
    if not ex('textures/entities/%s.png' % m.group(1)):
        missing.append(('entity-tex', m.group(1)))
# 4. java -> screen textures
for m in re.finditer(r'dyairdrop:(textures/screens/[a-z0-9_/]+\.png)', java):
    if not ex(m.group(1)):
        missing.append(('screen-tex', m.group(1)))
for m in re.finditer(r'dyairdrop:([a-z0-9_/]+\.png)', java):
    r = m.group(1)
    if not r.startswith('textures/'):
        if not ex('textures/' + r):
            missing.append(('java-tex-bare', r))

# 5. json internal refs
def resolve_tex(val, ctx):
    if not isinstance(val, str):
        return
    if val.startswith('#'):
        return
    if val.startswith('dyairdrop:'):
        p = val.split(':', 1)[1]
    else:
        p = val  # relative like "safe" inside displaysettings
    cands = ['textures/%s.png' % p, 'textures/block/%s.png' % p, 'textures/item/%s.png' % p,
             'textures/entities/%s.png' % p, 'textures/particle/%s.png' % p]
    if not any(ex(c) for c in cands):
        missing.append(('json-tex', '%s -> %s' % (ctx, val)))

def resolve_parent(val, ctx):
    if not isinstance(val, str):
        return
    if not val.startswith('dyairdrop:'):
        return
    p = val.split(':', 1)[1]
    if not ex('models/%s.json' % p):
        missing.append(('parent', '%s -> %s' % (ctx, val)))

for root, dirs, files in os.walk(A):
    for fn in files:
        if not fn.endswith('.json'):
            continue
        p = os.path.join(root, fn)
        rel = os.path.relpath(p, A).replace(os.sep, '/')
        try:
            d = json.load(open(p, encoding='utf-8'))
        except Exception as e:
            missing.append(('bad-json', '%s: %s' % (rel, e)))
            continue

        def walk(node, ctx, in_tex=False):
            if isinstance(node, dict):
                for k, v in node.items():
                    if k in ('parent', 'model'):
                        resolve_parent(v, ctx)
                    elif k == 'textures' and isinstance(v, dict):
                        for tv in v.values():
                            resolve_tex(tv, ctx)
                    else:
                        walk(v, ctx, in_tex)
            elif isinstance(node, list):
                for it in node:
                    walk(it, ctx, in_tex)
        walk(d, rel)

# 6. particles
pd = os.path.join(A, 'particles')
for fn in os.listdir(pd):
    d = json.load(open(os.path.join(pd, fn), encoding='utf-8'))
    for t in d.get('textures', []):
        resolve_tex(t, 'particles/' + fn)

# 7. sounds
snd = json.load(open(os.path.join(A, 'sounds.json'), encoding='utf-8'))
for key, val in snd.items():
    for s in val.get('sounds', []):
        nm = s['name'].split(':')[-1]
        if not os.path.exists(os.path.join(A, 'sounds', nm + '.ogg')):
            missing.append(('sound', '%s -> %s' % (key, nm)))

print('=== missing: %d ===' % len(missing))
for k, i in missing:
    print(' [%s] %s' % (k, i))
if not missing:
    print('原作素材引用全部一致')
