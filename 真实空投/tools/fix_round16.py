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

# 1. entities: passenger attachment override name + step height attribute
n = 0
for fn in os.listdir(os.path.join(BASE, 'entity')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'entity', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = t.replace('public Vec3 getDefaultPassengerAttachmentPoint() {',
                  'protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {')
    t = t.replace('   @Override\n   protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {',
                  '   @Override\n   protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {')
    # PlaneEntity step height
    t = t.replace('      this.setMaxUpStep(0.6F);\n', '')
    t = t.replace('return builder.add(Attributes.FLYING_SPEED, 0.3);',
                  'builder = builder.add(Attributes.STEP_HEIGHT, 0.6);\n      return builder.add(Attributes.FLYING_SPEED, 0.3);')
    if 'Attributes.STEP_HEIGHT' in t and 'import net.minecraft.world.entity.ai.attributes.AttributeSupplier;' in t:
        pass
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('entities round16:', n)

# 2. FlareEntity ctor rewrites
p = os.path.join(BASE, 'entity', 'FlareEntity.java')
t = open(p, encoding='utf-8').read()
t = t.replace("""   public FlareEntity(EntityType<? extends FlareEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world);
   }""", """   public FlareEntity(EntityType<? extends FlareEntity> type, double x, double y, double z, Level world) {
      super(type, world);
      this.setPos(x, y, z);
   }""")
t = t.replace("""   public FlareEntity(EntityType<? extends FlareEntity> type, LivingEntity entity, Level world) {
      super(type, entity, world);
   }""", """   public FlareEntity(EntityType<? extends FlareEntity> type, LivingEntity entity, Level world) {
      super(type, world);
      this.setOwner(entity);
   }""")
open(p, 'w', encoding='utf-8').write(t)
print('FlareEntity ctors ok')

# 3. layers: reRender colors
n = 0
for fn in os.listdir(os.path.join(BASE, 'entity', 'layer')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'entity', 'layer', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = re.sub(r'(\n\s*packedLight,\n\s*packedOverlay,\n\s*)1\.0F,\n\s*1\.0F,\n\s*1\.0F,\n\s*1\.0F\n', r'\1-1\n', t)
    t = re.sub(r'(partialTick,\n\s*packedLight,\n\s*OverlayTexture\.NO_OVERLAY,\n\s*)1\.0F,\n\s*1\.0F,\n\s*1\.0F,\n\s*1\.0F\n', r'\1-1\n', t)
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('layers colors:', n)

# 4. renderers: layer imports
n = 0
for fn in os.listdir(os.path.join(BASE, 'client', 'renderer')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'client', 'renderer', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    for m in re.finditer(r'\bnew (\w+Layer)\(', t):
        imp = 'import net.mcreator.dyairdrop.entity.layer.%s;' % m.group(1)
        if imp not in t:
            t = add_imports(t, [imp])
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('renderer layer imports:', n)

# 5. init particle types: DyairdropMod import
p = os.path.join(BASE, 'init', 'DyairdropModParticleTypes.java')
t = open(p, encoding='utf-8').read()
if 'import net.mcreator.dyairdrop.DyairdropMod;' not in t:
    t = add_imports(t, ['import net.mcreator.dyairdrop.DyairdropMod;'])
open(p, 'w', encoding='utf-8').write(t)
print('particle types import ok')

# 6. menus: procedure imports
n = 0
for fn in os.listdir(os.path.join(BASE, 'world', 'inventory')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'world', 'inventory', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    adds = set()
    for m in re.finditer(r'\b(\w+Procedure)\b', t):
        adds.add('import net.mcreator.dyairdrop.procedures.%s;' % m.group(1))
    if adds:
        t = add_imports(t, sorted(adds))
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('menu procedure imports:', n)

# 7. SafeopenBlock missing imports
p = os.path.join(BASE, 'block', 'SafeopenBlock.java')
t = open(p, encoding='utf-8').read()
t = add_imports(t, [
    'import net.minecraft.server.level.ServerPlayer;',
    'import net.minecraft.world.inventory.AbstractContainerMenu;',
    'import io.netty.buffer.Unpooled;',
    'import net.minecraft.network.FriendlyByteBuf;',
    'import net.minecraft.world.MenuProvider;',
    'import net.mcreator.dyairdrop.world.inventory.AirdropGUIMenu;',
])
open(p, 'w', encoding='utf-8').write(t)
print('SafeopenBlock imports ok')
