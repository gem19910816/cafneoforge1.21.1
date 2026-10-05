# -*- coding: utf-8 -*-
import os, re
BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

def add_imports(t, imports):
    lines = t.split('\n')
    idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
    for imp in imports:
        if imp not in t:
            lines.insert(idx + 2, imp)
    return '\n'.join(lines)

# 1. menus: BLOCK capability via level; ServerPlayer import
n = 0
for fn in os.listdir(os.path.join(BASE, 'world', 'inventory')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'world', 'inventory', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = t.replace('this.boundBlockEntity.getCapability(Capabilities.ItemHandler.BLOCK, null);',
                  'this.world.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);')
    if 'ServerPlayer' in t and 'import net.minecraft.server.level.ServerPlayer;' not in t:
        t = add_imports(t, ['import net.minecraft.server.level.ServerPlayer;'])
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('menus round21:', n)

# 2. dimensionTypeId -> dimension (global)
n = 0
for root, dirs, files in os.walk(os.path.join(BASE, 'procedures')):
    for fn in files:
        if not fn.endswith('.java'): continue
        p = os.path.join(root, fn)
        t = open(p, encoding='utf-8').read()
        if '.dimensionTypeId()' in t:
            t = t.replace('.dimensionTypeId()', '.dimension()')
            open(p, 'w', encoding='utf-8').write(t); n += 1
print('dimensionTypeId renamed:', n)

# 3. SafeopenBlock: unreachable break after yield
p = os.path.join(BASE, 'block', 'SafeopenBlock.java')
t = open(p, encoding='utf-8').read()
t = re.sub(r'(yield box\([^\n]*\));\n(\s*)break;', r'\1;', t)
open(p, 'w', encoding='utf-8').write(t)
print('SafeopenBlock switch fixed')
