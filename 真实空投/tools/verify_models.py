# -*- coding: utf-8 -*-
"""Detect models whose elements reference texture variables not declared in textures{}."""
import os, json

A = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\resources\assets\dyairdrop'
problems = []
for root, dirs, files in os.walk(os.path.join(A, 'models')):
    for fn in files:
        if not fn.endswith('.json'):
            continue
        p = os.path.join(root, fn)
        rel = os.path.relpath(p, A).replace(os.sep, '/')
        try:
            d = json.load(open(p, encoding='utf-8'))
        except Exception as e:
            problems.append((rel, ['JSON ERROR: %s' % e]))
            continue
        keys = set()

        def collect(node):
            if isinstance(node, dict):
                for k, v in node.items():
                    if k == 'texture' and isinstance(v, str) and v.startswith('#'):
                        keys.add(v)
                    collect(v)
            elif isinstance(node, list):
                for it in node:
                    collect(it)
        collect(d.get('elements', []))
        declared = set('#' + k for k in d.get('textures', {}).keys())
        unresolved = keys - declared
        if unresolved:
            problems.append((rel, sorted(unresolved)))

print('models with unresolved texture vars:', len(problems))
for rel, u in problems:
    print(' ', rel, u)

# also check JSON validity across assets
bad = []
for root, dirs, files in os.walk(A):
    for fn in files:
        if fn.endswith('.json'):
            p = os.path.join(root, fn)
            try:
                json.load(open(p, encoding='utf-8'))
            except Exception as e:
                bad.append((os.path.relpath(p, A), str(e)[:80]))
print('\ninvalid JSON files:', len(bad))
for b in bad:
    print(' ', b)
