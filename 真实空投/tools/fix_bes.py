# -*- coding: utf-8 -*-
import os, re

BE_DIR = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop\block\entity'

GECKO_IMPORTS = {
    'GeoBlockEntity': 'import software.bernie.geckolib.animatable.GeoBlockEntity;',
    'AnimatableInstanceCache': 'import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;',
    'AnimationController': 'import software.bernie.geckolib.animation.AnimationController;',
    'AnimationState': 'import software.bernie.geckolib.animation.AnimationState;',
    'ControllerRegistrar': 'import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;',
    'RawAnimation': 'import software.bernie.geckolib.animation.RawAnimation;',
    'PlayState': 'import software.bernie.geckolib.animation.PlayState;',
    'GeckoLibUtil': 'import software.bernie.geckolib.util.GeckoLibUtil;',
}

n = 0
for fn in os.listdir(BE_DIR):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(BE_DIR, fn)
    t = open(p, encoding='utf-8').read()
    adds = set()
    for simple, imp in GECKO_IMPORTS.items():
        if re.search(r'\b' + simple + r'\b', t) and imp not in t:
            adds.add(imp)
    if re.search(r'AnimationController\.State\b', t):
        adds.add('import software.bernie.geckolib.animation.AnimationController.State;')
    # block references (AirdroplargeBlock.ANIMATION etc.)
    for m in re.finditer(r'\b(\w+Block)\.([A-Z_]+)\b', t):
        blockname = m.group(1)
        imp = 'import net.mcreator.dyairdrop.block.%s;' % blockname
        if blockname != fn[:-5] and imp not in t and os.path.exists(os.path.join(os.path.dirname(BE_DIR), 'block', blockname + '.java')):
            adds.add(imp)
    # state reference for State.STOPPED (inner import already covers)
    if adds:
        lines = t.split('\n')
        idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
        for imp in sorted(adds):
            if imp not in t:
                lines.insert(idx + 2, imp)
        t = '\n'.join(lines)
        open(p, 'w', encoding='utf-8').write(t)
        n += 1
print('BEs import-fixed:', n)

# ClientListener import fix
p = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop\block\listener\ClientListener.java'
t = open(p, encoding='utf-8').read()
t = t.replace('import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;',
              'import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;')
open(p, 'w', encoding='utf-8').write(t)
print('ClientListener fixed')
