# -*- coding: utf-8 -*-
import os, re

SRC = r'C:\Users\79662\AppData\Local\Temp\dyairdrop_moj\net\mcreator\dyairdrop'
DST = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop'

MENU_IMPORTS = """package net.mcreator.dyairdrop.world.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.mcreator.dyairdrop.DyairdropModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

"""

def transform_menu(fn, text):
    body = 'public class' + text.split('public class', 1)[1]
    body = body.replace('super((MenuType)DyairdropModMenus.', 'super(DyairdropModMenus.')
    body = body.replace('itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null)',
                        'itemstack.getCapability(Capabilities.ItemHandler.ITEM)')
    body = body.replace('this.boundEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null)',
                        'this.boundEntity.getCapability(Capabilities.ItemHandler.ENTITY)')
    body = body.replace('this.boundBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null)',
                        'this.boundBlockEntity.getCapability(Capabilities.ItemHandler.BLOCK)')
    # constructor buffer type: accept RegistryFriendlyByteBuf
    body = re.sub(r'public (\w+)\(int id, Inventory inv, FriendlyByteBuf extraData\)',
                  r'public \1(int id, Inventory inv, RegistryFriendlyByteBuf extraData)', body)
    # player tick event -> Post
    body = body.replace('@SubscribeEvent\n   public static void onPlayerTick(PlayerTickEvent event) {\n      Player entity = event.player;\n      if (event.phase == Phase.END && entity.containerMenu instanceof',
                        '@SubscribeEvent\n   public static void onPlayerTick(PlayerTickEvent.Post event) {\n      Player entity = event.getEntity();\n      if (entity.containerMenu instanceof')
    return MENU_IMPORTS + body

# ---- screens ----
GUI_IMPORTS = """package net.mcreator.dyairdrop.client.gui;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mcreator.dyairdrop.DyairdropMod;
import net.mcreator.dyairdrop.world.inventory.{menu};
import net.mcreator.dyairdrop.procedures.{procs};
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

"""

PROC_RE = re.compile(r'import net\.mcreator\.dyairdrop\.procedures\.([\w]+);')
PROC_EXCLUDE = re.compile(r'^(CheckProcedure|ChecknewliteProcedure)$')

def imgbtn_repl(m):
    indent, x, y, w, h, u, v, hoverv, tex, tw, th, payloadcls, btnid, xs, ys, zs = m.groups()
    cond = ''
    return (indent + 'Button.builder(Component.empty(), e -> {\n'
            + indent + '   PacketDistributor.sendToServer(new %s(%s, %s, %s, %s));\n'
            + indent + '   %s.handleButtonAction(this.entity, %s, %s, %s, %s);\n'
            + indent + '}).bounds(%s, %s, %s, %s).build(builder -> new Button(builder) {\n'
            + indent + '   @Override\n'
            + indent + '   public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {\n'
            + indent + '      guiGraphics.blit(new ResourceLocation("%s"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? %s.0F : 0.0F, %s, %s, %s, %s);\n'
            + indent + '   }\n'
            + indent + '});') % (payloadcls, btnid, xs, ys, zs, payloadcls, btnid, xs, ys, zs, x, y, w, h, tex, hoverv, w, h, tw, th)

def transform_screen(fn, text):
    menu_name = fn[:-6]  # PannelScreen -> PannelMenu
    if menu_name == 'AirdropGUIScreen': menu_name = 'AirdropGUIMenu'
    elif menu_name.endswith('Screen'):
        base = menu_name[:-6]
        menu_name = {'Pannel': 'PannelMenu', 'PannelRE': 'PannelREMenu', 'PannelRE2': 'PannelRE2Menu', 'TestGUI2': 'TestGUI2Menu'}.get(base, base + 'Menu')
    procs = sorted(set(PROC_RE.findall(text)))
    body = 'public class' + text.split('public class', 1)[1]

    # payload class rename
    body = body.replace('PannelRE2ButtonMessage', 'PannelRE2ButtonPayload')
    body = body.replace('PannelREButtonMessage', 'PannelREButtonPayload')
    body = body.replace('PannelButtonMessage', 'PannelButtonPayload')
    body = body.replace('TestGUI2ButtonMessage', 'TestGUI2ButtonPayload')
    body = body.replace('DyairdropMod.PACKET_HANDLER.sendToServer(', 'PacketDistributor.sendToServer(')
    # renderBackground with coords
    body = body.replace('this.renderBackground(guiGraphics);\n', 'this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);\n')
    # drop setShaderColor lines
    body = re.sub(r'\s*RenderSystem\.setShaderColor\(1\.0F, 1\.0F, 1\.0F, 1\.0F\);', '', body)
    # ImageButton -> custom texture button
    pat = re.compile(
        r'(\n   )this\.(\w+) = new ImageButton\(\n'
        r'\s*this\.leftPos \+ (-?[\d.]+), this\.topPos \+ (-?[\d.]+), (-?\d+), (-?\d+), (-?\d+), (-?\d+), (-?\d+), new ResourceLocation\("([^"]+)"\), (-?\d+), (-?\d+), e -> \{\n'
        r'\s*PacketDistributor\.sendToServer\(new (\w+)Payload\((\d+), this\.x, this\.y, this\.z\)\);\n'
        r'\s*\w+Payload\.handleButtonAction\(this\.entity, (\d+), this\.x, this\.y, this\.z\);\n'
        r'\s*\}\n'
        r'\s*\);')
    body = pat.sub(imgbtn_repl, body)
    return GUI_IMPORTS.format(menu=menu_name, procs=', '.join(p for p in procs if not PROC_EXCLUDE.match(p))) + body

# ---- run ----
n = 0
menudst = os.path.join(DST, 'world', 'inventory')
os.makedirs(menudst, exist_ok=True)
for fn in os.listdir(os.path.join(SRC, 'world', 'inventory')):
    if fn.endswith('.java'):
        t = open(os.path.join(SRC, 'world', 'inventory', fn), encoding='utf-8').read()
        open(os.path.join(menudst, fn), 'w', encoding='utf-8').write(transform_menu(fn, t))
        n += 1

guidst = os.path.join(DST, 'client', 'gui')
os.makedirs(guidst, exist_ok=True)
for fn in os.listdir(os.path.join(SRC, 'client', 'gui')):
    if fn.endswith('.java'):
        t = open(os.path.join(SRC, 'client', 'gui', fn), encoding='utf-8').read()
        open(os.path.join(guidst, fn), 'w', encoding='utf-8').write(transform_screen(fn, t))
        n += 1
print('menus+screens:', n)
