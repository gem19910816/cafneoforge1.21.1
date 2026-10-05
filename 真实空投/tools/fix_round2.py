# -*- coding: utf-8 -*-
import os, re

BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

def add_imports(path, imports):
    t = open(path, encoding='utf-8').read()
    lines = t.split('\n')
    idx = next(i for i, l in enumerate(lines) if l.startswith('package '))
    changed = False
    for imp in sorted(imports):
        if imp not in t:
            lines.insert(idx + 2, imp)
            changed = True
    if changed:
        open(path, 'w', encoding='utf-8').write('\n'.join(lines))
    return changed

# ---------- A. entities: drop MobType ----------
n = 0
ent_dir = os.path.join(BASE, 'entity')
for fn in os.listdir(ent_dir):
    p = os.path.join(ent_dir, fn)
    if not fn.endswith('.java'):
        continue
    t = open(p, encoding='utf-8').read()
    orig = t
    t = re.sub(r'\n   public MobType getMobType\(\) \{\n      return MobType\.UNDEFINED;\n   \}', '', t)
    t = t.replace('import net.minecraft.world.entity.MobType;\n', '')
    if t != orig:
        open(p, 'w', encoding='utf-8').write(t)
        n += 1
print('entities MobType removed:', n)

# ---------- B. entity/model: add entity + GeoModel imports ----------
n = 0
mdir = os.path.join(BASE, 'entity', 'model')
for fn in os.listdir(mdir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(mdir, fn)
    t = open(p, encoding='utf-8').read()
    adds = {'import software.bernie.geckolib.model.GeoModel;'}
    for m in re.finditer(r'\b(\w+Entity)\b', t):
        name = m.group(1)
        if os.path.exists(os.path.join(ent_dir, name + '.java')):
            adds.add('import net.mcreator.dyairdrop.entity.%s;' % name)
    if add_imports(p, adds):
        n += 1
print('entity models fixed:', n)

# ---------- C. entity/layer: add entity imports ----------
n = 0
ldir = os.path.join(BASE, 'entity', 'layer')
for fn in os.listdir(ldir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(ldir, fn)
    t = open(p, encoding='utf-8').read()
    adds = set()
    for m in re.finditer(r'\b(\w+Entity)\b', t):
        name = m.group(1)
        if os.path.exists(os.path.join(ent_dir, name + '.java')):
            adds.add('import net.mcreator.dyairdrop.entity.%s;' % name)
    if add_imports(p, adds):
        n += 1
print('entity layers fixed:', n)

# ---------- D. client renderers: Context type + EntityRenderer + Axis + color fix ----------
n = 0
rdir = os.path.join(BASE, 'client', 'renderer')
for fn in os.listdir(rdir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(rdir, fn)
    t = open(p, encoding='utf-8').read()
    orig = t
    t = re.sub(r'\((Context) (context|renderManager)\)', r'(EntityRendererProvider.Context \2)', t)
    # flare-style model render with float args -> int color
    t = re.sub(r'\.renderToBuffer\(poseStack, vb, packedLightIn, OverlayTexture\.NO_OVERLAY, 1\.0F, 1\.0F, 1\.0F, 0\.0625F\)',
               '.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 0x10FFFFFF)', t)
    if t != orig or 'EntityRenderer<' in t:
        adds = set()
        if 'EntityRendererProvider.Context' in t or 'EntityRenderer<' in t:
            adds.add('import net.minecraft.client.renderer.entity.EntityRendererProvider;')
        if re.search(r'\bEntityRenderer<', t) and 'extends EntityRenderer<' in t:
            adds.add('import net.minecraft.client.renderer.entity.EntityRenderer;')
        if re.search(r'\bAxis\.', t):
            adds.add('import com.mojang.math.Axis;')
        if 'OverlayTexture' in t:
            adds.add('import net.minecraft.client.renderer.texture.OverlayTexture;')
        add_imports(p, adds)
        open(p, 'w', encoding='utf-8').write(t)
        n += 1
print('client renderers fixed:', n)

# ---------- E. client model: drop bogus imports ----------
n = 0
cmdir = os.path.join(BASE, 'client', 'model')
for fn in os.listdir(cmdir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(cmdir, fn)
    t = open(p, encoding='utf-8').read()
    orig = t
    t = t.replace('import net.minecraft.client.model.MeshDefinition;\n', '')
    t = t.replace('import net.minecraft.client.model.PartDefinition;\n', '')
    if t != orig:
        open(p, 'w', encoding='utf-8').write(t)
        n += 1
print('client models cleaned:', n)

# ---------- F. blocks: add procedure/BE imports + codec lambda ----------
n = 0
bdir = os.path.join(BASE, 'block')
for fn in os.listdir(bdir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(bdir, fn)
    t = open(p, encoding='utf-8').read()
    orig = t
    adds = set()
    for m in re.finditer(r'\b(\w+Procedure)\b', t):
        adds.add('import net.mcreator.dyairdrop.procedures.%s;' % m.group(1))
    for m in re.finditer(r'\bnew (\w+(?:TileEntity|BlockEntity))\(', t):
        adds.add('import net.mcreator.dyairdrop.block.entity.%s;' % m.group(1))
    add_imports(p, adds)
    # codec simpleCodec with no-arg constructor -> lambda
    t = open(p, encoding='utf-8').read()
    cls = fn[:-5]
    t = t.replace('return simpleCodec(%s::new);' % cls, 'return simpleCodec(props -> new %s());' % cls)
    if t != orig:
        open(p, 'w', encoding='utf-8').write(t)
        n += 1
print('blocks fixed:', n)

# ---------- G. BEs: add procedure + block class imports ----------
n = 0
bedir = os.path.join(BASE, 'block', 'entity')
for fn in os.listdir(bedir):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(bedir, fn)
    t = open(p, encoding='utf-8').read()
    adds = set()
    for m in re.finditer(r'\b(\w+Procedure)\b', t):
        adds.add('import net.mcreator.dyairdrop.procedures.%s;' % m.group(1))
    if add_imports(p, adds):
        n += 1
print('BEs fixed:', n)

# ---------- H. DyairdropModBlockEntities Block import ----------
p = os.path.join(BASE, 'init', 'DyairdropModBlockEntities.java')
t = open(p, encoding='utf-8').read()
t = t.replace('DeferredHolder<Block, ? extends net.minecraft.world.level.block.Block> block',
              'DeferredHolder<net.minecraft.world.level.block.Block, ? extends net.minecraft.world.level.block.Block> block')
open(p, 'w', encoding='utf-8').write(t)
print('BE init fixed')

# ---------- I. PlayerVariablesSyncPayload: DistExecutor gone ----------
p = os.path.join(BASE, 'network', 'PlayerVariablesSyncPayload.java')
t = open(p, encoding='utf-8').read()
t = t.replace("""	public static void handle(PlayerVariablesSyncPayload message, IPayloadContext context) {
		context.enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
				if (Minecraft.getInstance().player != null) {
					Minecraft.getInstance().player.setData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT,
							PlayerVariables.fromNBT(message.data()));
				}
			});
		});
	}""",
"""	public static void handle(PlayerVariablesSyncPayload message, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (Minecraft.getInstance().player != null) {
				Minecraft.getInstance().player.setData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT,
						PlayerVariables.fromNBT(message.data()));
			}
		});
	}""")
t = t.replace('import net.neoforged.api.distmarker.Dist;\nimport net.neoforged.fml.DistExecutor;\n', '')
open(p, 'w', encoding='utf-8').write(t)
print('payload fixed')
