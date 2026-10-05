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

# 1. capability regex across procedures
n = 0
for fn in os.listdir(os.path.join(BASE, 'procedures')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'procedures', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    t = re.sub(r'\.getCapability\(DyairdropModVariables\.PLAYER_VARIABLES_CAPABILITY,\s*null\)\s*\.orElse\(\s*new\s*DyairdropModVariables\.PlayerVariables\(\)\s*\)',
               '.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get())', t)
    t = re.sub(r'\.getCapability\(DyairdropModVariables\.PLAYER_VARIABLES_CAPABILITY,\s*null\)\s*\.ifPresent\(capability -> \{',
               '.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {', t)
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('procedures capability regex:', n)

# 2. SavedDataSyncPayload record component rename
p = os.path.join(BASE, 'network', 'SavedDataSyncPayload.java')
t = open(p, encoding='utf-8').read()
t = t.replace('public record SavedDataSyncPayload(int type, CompoundTag data)', 'public record SavedDataSyncPayload(int syncType, CompoundTag data)')
t = t.replace('buf.writeInt(msg.type());', 'buf.writeInt(msg.syncType());')
t = t.replace('if (message.type() == 0)', 'if (message.syncType() == 0)')
open(p, 'w', encoding='utf-8').write(t)
print('SavedDataSyncPayload renamed')

# 3. DyairdropModBlockEntities create() arg order
p = os.path.join(BASE, 'init', 'DyairdropModBlockEntities.java')
t = open(p, encoding='utf-8').read()
t = t.replace('DeferredRegister.create(DyairdropMod.MODID, net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE)',
              'DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, DyairdropMod.MODID)')
open(p, 'w', encoding='utf-8').write(t)
print('BE create fixed')

# 4. DyairdropModSounds: DyairdropMod import
p = os.path.join(BASE, 'init', 'DyairdropModSounds.java')
t = open(p, encoding='utf-8').read()
if 'import net.mcreator.dyairdrop.DyairdropMod;' not in t:
    t = add_imports(t, ['import net.mcreator.dyairdrop.DyairdropMod;'])
open(p, 'w', encoding='utf-8').write(t)

# 5. payloads: menu imports
n = 0
for fn in os.listdir(os.path.join(BASE, 'network')):
    if not fn.endswith('Payload.java'): continue
    p = os.path.join(BASE, 'network', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    for m in re.finditer(r'\b(\w+Menu)\.guistate\b', t):
        imp = 'import net.mcreator.dyairdrop.world.inventory.%s;' % m.group(1)
        if imp not in t:
            t = add_imports(t, [imp])
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('payload menu imports:', n)

# 6. AirdropEntity etc: Entity import for passenger method
n = 0
for fn in os.listdir(os.path.join(BASE, 'entity')):
    if not fn.endswith('.java'): continue
    p = os.path.join(BASE, 'entity', fn)
    t = open(p, encoding='utf-8').read()
    o = t
    if 'getPassengerAttachmentPoint' in t and 'import net.minecraft.world.entity.Entity;' not in t:
        t = add_imports(t, ['import net.minecraft.world.entity.Entity;'])
    if t != o:
        open(p, 'w', encoding='utf-8').write(t); n += 1
print('entity Entity imports:', n)

# 7. SafeopenBlock: Component + others
p = os.path.join(BASE, 'block', 'SafeopenBlock.java')
t = open(p, encoding='utf-8').read()
t = add_imports(t, [
    'import net.minecraft.network.chat.Component;',
    'import net.minecraft.world.entity.player.Player;',
])
open(p, 'w', encoding='utf-8').write(t)

# 8. FlaregunItem hurt -> hurtAndBreak
p = os.path.join(BASE, 'item', 'FlaregunItem.java')
t = open(p, encoding='utf-8').read()
t = t.replace("""            } else if (stack.isDamageableItem()) {
               if (stack.hurt(1, world.getRandom(), player)) {
                  stack.shrink(1);
                  stack.setDamageValue(0);
                  if (stack.isEmpty()) {
                     player.getInventory().removeItem(stack);
                  }
               }
            } else {""", """            } else if (stack.isDamageableItem()) {
               stack.hurtAndBreak(1, player.serverLevel(), player, item -> {
               });
               if (stack.isEmpty()) {
                  player.getInventory().removeItem(stack);
               }
            } else {""")
open(p, 'w', encoding='utf-8').write(t)
print('FlaregunItem hurt ok')
