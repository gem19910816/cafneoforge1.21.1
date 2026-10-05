# -*- coding: utf-8 -*-
import os, re
BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

# 1. menus: capability blocks -> null-checked assignments; add FriendlyByteBuf import
n = 0
for fn in os.listdir(os.path.join(BASE, 'world', 'inventory')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'world', 'inventory', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = re.sub(
        r'(\S+?)\.getCapability\(Capabilities\.ItemHandler\.(ITEM|ENTITY)\)\.ifPresent\(capability -> \{\n(\s*)this\.internal = capability;\n\s*this\.bound = true;\n\s*\}\);',
        r'{\n\3IItemHandler capability = \1.getCapability(Capabilities.ItemHandler.\2, null);\n\3if (capability != null) {\n\3   this.internal = capability;\n\3   this.bound = true;\n\3}\n\3}',
        t)
    t = re.sub(
        r'(\S+?)\.getCapability\(Capabilities\.ItemHandler\.BLOCK\)\.ifPresent\(capability -> \{\n(\s*)this\.internal = capability;\n\s*this\.bound = true;\n\s*\}\);',
        r'{\n\2IItemHandler capability = \1.getCapability(Capabilities.ItemHandler.BLOCK, null);\n\2if (capability != null) {\n\2   this.internal = capability;\n\2   this.bound = true;\n\2}\n\2}',
        t)
    if 'import net.minecraft.network.FriendlyByteBuf;' not in t and 'FriendlyByteBuf' in t:
        t = t.replace('import net.minecraft.network.RegistryFriendlyByteBuf;\n', '')
        t = t.replace('import net.minecraft.network.chat.Component;', 'import net.minecraft.network.FriendlyByteBuf;\nimport net.minecraft.network.chat.Component;') if 'import net.minecraft.network.chat.Component;' in t else t
        if 'import net.minecraft.network.FriendlyByteBuf;' not in t:
            lines = t.split('\n'); idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
            lines.insert(idx + 2, 'import net.minecraft.network.FriendlyByteBuf;'); t = '\n'.join(lines)
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('menus round20:', n)

# 2. dimensionTypeId leftovers
n = 0
for root, dirs, files in os.walk(os.path.join(BASE, 'procedures')):
    for fn in files:
        if not fn.endswith('.java'): continue
        p = os.path.join(root, fn)
        t = open(p, encoding='utf-8').read()
        o = t
        t = t.replace('.dimensionTypeId().location().toString();', '.dimension().location().toString();')
        if t != o:
            open(p, 'w', encoding='utf-8').write(t); n += 1
print('dimensionTypeId fixed:', n)

# 3. ShutdownProcedure showlight var
p = os.path.join(BASE, 'procedures', 'ShutdownProcedure.java')
t = open(p, encoding='utf-8').read()
t = t.replace('capability.showlight = _setval;', 'capability.showlight = _setvalx;')
open(p, 'w', encoding='utf-8').write(t)

# 4. FastairdropProcedure BuiltInRegistries import
p = os.path.join(BASE, 'procedures', 'FastairdropProcedure.java')
t = open(p, encoding='utf-8').read()
if 'import net.minecraft.core.registries.BuiltInRegistries;' not in t:
    lines = t.split('\n'); idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
    lines.insert(idx + 2, 'import net.minecraft.core.registries.BuiltInRegistries;')
    t = '\n'.join(lines)
open(p, 'w', encoding='utf-8').write(t)

# 5. isSameItemSameTags -> isSameItemSameComponents (1.21.1)
n = 0
for root, dirs, files in os.walk(BASE):
    for fn in files:
        if not fn.endswith('.java'): continue
        p = os.path.join(root, fn)
        t = open(p, encoding='utf-8').read()
        if 'isSameItemSameTags' in t:
            t = t.replace('ItemStack.isSameItemSameTags(', 'ItemStack.isSameItemSameComponents(')
            open(p, 'w', encoding='utf-8').write(t); n += 1
print('isSameItem renamed:', n)
