# -*- coding: utf-8 -*-
"""Audit: for each class, determine declared bus and check each @SubscribeEvent param is valid."""
import os, re

BASE = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java'

# NeoForge 1.21.1: mod-bus-only event base types
MOD_BUS_MARKERS = [
    'RegisterPayloadHandlersEvent', 'EntityAttributeCreationEvent', 'FMLCommonSetupEvent',
    'FMLClientSetupEvent', 'FMLDedicatedServerSetupEvent', 'FMLConstructModEvent',
    'EntityRenderersEvent', 'RegisterMenuScreensEvent', 'RegisterParticleProvidersEvent',
    'RegisterCapabilitiesEvent', 'RegisterLayerDefinitions', 'InterModEnqueueEvent',
    'InterModComms', 'ModelEvent', 'RegisterClientReloadListenersEvent', 'AddPackFindersEvent',
    'RegisterKeyMappingsEvent', 'CreativeModeTabEvent', 'RegisterClientCommandsEvent',
    'RegisterTextureAtlasSpriteLoadersEvent', 'BuildCreativeModeTabContentsEvent',
]
GAME_BUS_MARKERS = [
    'PlayerEvent', 'EntityTickEvent', 'LevelTickEvent', 'ServerTickEvent', 'ClientTickEvent',
    'PlayerTickEvent', 'RegisterCommandsEvent', 'EntityJoinLevelEvent', 'LivingEvent',
    'PlayerInteractEvent', 'BlockEvent', 'ExplosionEvent', 'ServerLifecycleEvent',
    'TickEvent', 'RecipesUpdatedEvent', 'ServerStartedEvent', 'EntityTravelToDimensionEvent',
]

problems = []
for root, dirs, files in os.walk(BASE):
    for fn in files:
        if not fn.endswith('.java'):
            continue
        p = os.path.join(root, fn)
        t = open(p, encoding='utf-8').read()
        # declared bus
        m = re.search(r'@EventBusSubscriber(?:\(([^)]*)\))?', t)
        if not m:
            bus = None  # not auto-registered
        else:
            args = m.group(1) or ''
            bus = 'MOD' if 'Bus.MOD' in args else 'GAME'
        # collect @SubscribeEvent methods and their first param type
        for sm in re.finditer(r'@SubscribeEvent\s+(?:public|private|protected)?\s*static\s+\w+\s+\w+\s*\(\s*([\w.$]+)', t):
            param = sm.group(1)
            simple = param.split('.')[-1]
            is_mod_event = any(k in param for k in MOD_BUS_MARKERS)
            is_game_event = any(k in param for k in GAME_BUS_MARKERS)
            if bus == 'MOD' and is_game_event and not is_mod_event:
                problems.append((p, fn, bus, param, 'GAME event on MOD bus -> will crash'))
            elif bus == 'GAME' and is_mod_event and not is_game_event:
                problems.append((p, fn, bus, param, 'MOD event on GAME bus -> will crash'))
            elif bus is None:
                problems.append((p, fn, 'NONE', param, 'has @SubscribeEvent but class has no @EventBusSubscriber'))
            elif not is_mod_event and not is_game_event:
                problems.append((p, fn, bus, param, 'UNCLASSIFIED param -> review'))

# also: manual registrations in main class vs annotated
main = os.path.join(BASE, 'net', 'mcreator', 'dyairdrop', 'DyairdropMod.java')
mt = open(main, encoding='utf-8').read()
man_reg = re.findall(r'(modEventBus|NeoForge\.EVENT_BUS)\.register\(([\w.]+)\.class\)', mt)
print('manual registrations:', man_reg)

print('\n--- problems ---')
if not problems:
    print('none')
for pr in problems:
    print('%s [%s] %s : %s' % (pr[1], pr[2], pr[3], pr[4]))
