# -*- coding: utf-8 -*-
import os, re

SRC = r'C:\Users\79662\AppData\Local\Temp\dyairdrop_moj\net\mcreator\dyairdrop'
DST = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

def transform(text):
    # ---------- imports ----------
    text = re.sub(r'import net\.minecraftforge\.eventbus\.api\.SubscribeEvent;', 'import net.neoforged.bus.api.SubscribeEvent;', text)
    text = re.sub(r'import net\.minecraftforge\.eventbus\.api\.Event;', 'import net.neoforged.bus.api.Event;', text)
    text = re.sub(r'import net\.minecraftforge\.fml\.common\.Mod\.EventBusSubscriber\.Bus;', 'import net.neoforged.fml.common.EventBusSubscriber.Bus;', text)
    text = re.sub(r'import net\.minecraftforge\.fml\.common\.Mod\.EventBusSubscriber;', 'import net.neoforged.fml.common.EventBusSubscriber;', text)
    text = re.sub(r'import net\.minecraftforge\.fml\.ModList;', 'import net.neoforged.fml.ModList;', text)
    text = re.sub(r'import net\.minecraftforge\.event\.RegisterCommandsEvent;', 'import net.neoforged.neoforge.event.RegisterCommandsEvent;', text)
    text = re.sub(r'import net\.minecraftforge\.event\.entity\.EntityJoinLevelEvent;', 'import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;', text)
    text = re.sub(r'import net\.minecraftforge\.event\.TickEvent\.LevelTickEvent;', 'import net.neoforged.neoforge.event.tick.LevelTickEvent;', text)
    text = re.sub(r'import net\.minecraftforge\.event\.TickEvent\.Phase;\n?', '', text)
    text = re.sub(r'import net\.minecraftforge\.common\.util\.FakePlayerFactory;', 'import net.neoforged.neoforge.common.util.FakePlayerFactory;', text)
    text = re.sub(r'import net\.minecraftforge\.api\.distmarker\.OnlyIn;', 'import net.neoforged.api.distmarker.OnlyIn;', text)
    text = re.sub(r'import net\.minecraftforge\.api\.distmarker\.Dist;', 'import net.neoforged.api.distmarker.Dist;', text)
    text = re.sub(r'import net\.minecraftforge\.[\w.]+;\n', '', text)  # drop remaining forge imports

    # ---------- annotations ----------
    text = text.replace('@EventBusSubscriber(modid = "dyairdrop", bus = Bus.MOD)', '@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)')
    text = re.sub(r'\n\s*@OnlyIn\([^\n]*\)', '', text)

    # ---------- registry lookups ----------
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("entity\.firework_rocket\.launch"\)\)', 'SoundEvents.FIREWORK_ROCKET_LAUNCH', text)
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("block\.chest\.open"\)\)', 'SoundEvents.CHEST_OPEN', text)
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("dyairdrop:planesound"\)\)', 'DyairdropModSounds.PLANESOUND.get()', text)
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("dyairdrop:pwcorrect"\)\)', 'DyairdropModSounds.PWCORRECT.get()', text)
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("dyairdrop:pwwrong"\)\)', 'DyairdropModSounds.PWWRONG.get()', text)
    text = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("dyairdrop:check"\)\)', 'DyairdropModSounds.CHECK.get()', text)
    text = text.replace('ForgeRegistries.ITEMS.getKey(', 'BuiltInRegistries.ITEM.getKey(')
    text = text.replace('ForgeRegistries.BLOCKS.getKey(', 'BuiltInRegistries.BLOCK.getKey(')
    if 'BuiltInRegistries.' in text:
        text = text.replace('import net.minecraft.core.registries.Registries;', 'import net.minecraft.core.registries.BuiltInRegistries;\nimport net.minecraft.core.registries.Registries;')
        if 'import net.minecraft.core.registries.BuiltInRegistries;' not in text:
            text = text.replace('import net.minecraft.resources.ResourceLocation;', 'import net.minecraft.core.registries.BuiltInRegistries;\nimport net.minecraft.resources.ResourceLocation;')
    if re.search(r'DyairdropModSounds\.', text):
        if 'import net.mcreator.dyairdrop.init.DyairdropModSounds;' not in text:
            text = text.replace('import net.minecraft.resources.ResourceLocation;', 'import net.mcreator.dyairdrop.init.DyairdropModSounds;\nimport net.minecraft.resources.ResourceLocation;')
    if re.search(r'SoundEvents\.', text):
        if 'import net.minecraft.sounds.SoundEvents;' not in text:
            text = text.replace('import net.minecraft.resources.ResourceLocation;', 'import net.minecraft.resources.ResourceLocation;\nimport net.minecraft.sounds.SoundEvents;')

    # ---------- capability -> attachment ----------
    text = text.replace('.getCapability(DyairdropModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new DyairdropModVariables.PlayerVariables())',
                        '.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get())')
    text = text.replace('.getCapability(DyairdropModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {',
                        '.getData(DyairdropModVariables.PLAYER_VARIABLES_ATTACHMENT.get()).ifPresentData(capability -> {')
    if re.search(r'ifPresentData|getData\(DyairdropModVariables', text):
        if 'import net.mcreator.dyairdrop.network.DyairdropModVariables;' not in text:
            text = text.replace('import net.minecraft.resources.ResourceLocation;', 'import net.mcreator.dyairdrop.network.DyairdropModVariables;\nimport net.minecraft.resources.ResourceLocation;')

    # ---------- screen opening ----------
    text = text.replace('NetworkHooks.openScreen(_ent, new MenuProvider() {', '_ent.openMenu(new MenuProvider() {')
    text = re.sub(r'\}, _bpos\);', '}, _buf -> _buf.writeBlockPos(_bpos));', text)

    # ---------- 1.21 renames ----------
    text = text.replace('.dayTime()', '.getDayTime()')

    return text

# add ifPresentData helper into DyairdropModVariables.PlayerVariables
vars_path = os.path.join(DST, 'network', 'DyairdropModVariables.java')
vt = open(vars_path, encoding='utf-8').read()
if 'ifPresentData' not in vt:
    vt = vt.replace("""		public void syncPlayerVariables(Entity entity) {""",
                    """		/** Compatibility helper: keeps MCreator-style ifPresent writes working with attachments. */
		public void ifPresentData(java.util.function.Consumer<PlayerVariables> action) {
			action.accept(this);
		}

		public void syncPlayerVariables(Entity entity) {""")
    open(vars_path, 'w', encoding='utf-8').write(vt)
    print('variables helper added')

count = 0
for sub in ('procedures', 'command'):
    sdir = os.path.join(SRC, sub)
    ddir = os.path.join(DST, sub)
    os.makedirs(ddir, exist_ok=True)
    for fn in os.listdir(sdir):
        if not fn.endswith('.java'):
            continue
        text = open(os.path.join(sdir, fn), encoding='utf-8').read()
        text = transform(text)
        open(os.path.join(ddir, fn), 'w', encoding='utf-8').write(text)
        count += 1
print('procedures+commands transformed:', count)
