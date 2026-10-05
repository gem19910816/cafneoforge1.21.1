# -*- coding: utf-8 -*-
import os, re
BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

def add_import_lines(t, imports):
    lines = t.split('\n')
    idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
    changed = False
    for imp in imports:
        if imp not in t:
            lines.insert(idx + 2, imp)
            changed = True
    return '\n'.join(lines), changed

# 1. entities
n = 0
for fn in os.listdir(os.path.join(BASE, 'entity')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'entity', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = t.replace('this.entityData.define(', 'builder.define(')
    t = re.sub(r'\n   public EntityDimensions getDimensions\(Pose p_\d+_\) \{\n      return super\.getDimensions\(p_\d+_\)\.scale\(1\.0F\);\n   \}', '', t)
    t = t.replace('this.dropExperience();', 'this.dropExperience(null);')
    t = t.replace('entityarrow.setKnockback(knockback);', '')
    t = t.replace('entityarrow.setKnockback(0);', '')
    t = t.replace('protected ItemStack getPickupItem() {', 'protected ItemStack getDefaultPickupItem() {')
    if re.search(r'\bState\.STOPPED\b', t) and 'import software.bernie.geckolib.animation.AnimationController.State;' not in t:
        t, ch = add_import_lines(t, ['import software.bernie.geckolib.animation.AnimationController.State;'])
    if fn == 'FlareEntity.java':
        if 'import net.mcreator.dyairdrop.init.DyairdropModItems;' not in t:
            t, ch = add_import_lines(t, ['import net.mcreator.dyairdrop.init.DyairdropModItems;'])
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('entities fixed:', n)

# 2. FlareRenderer OverlayTexture
p = os.path.join(BASE, 'client', 'renderer', 'FlareRenderer.java')
t = open(p, encoding='utf-8').read()
if 'import net.minecraft.client.renderer.texture.OverlayTexture;' not in t:
    t, ch = add_import_lines(t, ['import net.minecraft.client.renderer.texture.OverlayTexture;'])
    open(p, 'w', encoding='utf-8').write(t)
print('FlareRenderer OverlayTexture ok')

# 3. SafeopenBlock: use->useWithoutItem + Inventory import
p = os.path.join(BASE, 'block', 'SafeopenBlock.java')
t = open(p, encoding='utf-8').read()
o = t
t = t.replace('public InteractionResult use(BlockState blockstate, Level world, final BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {',
              'public InteractionResult useWithoutItem(BlockState blockstate, Level world, final BlockPos pos, Player entity, BlockHitResult hit) {')
t = t.replace('super.use(blockstate, world, pos, entity, hand, hit);', 'super.useWithoutItem(blockstate, world, pos, entity, hit);')
if 'import net.minecraft.world.entity.player.Inventory;' not in t:
    t, ch = add_import_lines(t, ['import net.minecraft.world.entity.player.Inventory;'])
if t != o:
    open(p, 'w', encoding='utf-8').write(t)
print('SafeopenBlock ok')
