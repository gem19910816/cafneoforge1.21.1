# -*- coding: utf-8 -*-
import os, re

SRC = r'C:\Users\79662\AppData\Local\Temp\dyairdrop_moj\net\mcreator\dyairdrop\item'
DST = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop\item'
os.makedirs(DST, exist_ok=True)

HEADER = """package net.mcreator.dyairdrop.item;

import java.util.List;

import net.mcreator.dyairdrop.entity.FlareEntity;
import net.mcreator.dyairdrop.procedures.FlaregunlootsetProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

"""

count = 0
for fn in os.listdir(SRC):
    if not fn.endswith('.java'):
        continue
    text = open(os.path.join(SRC, fn), encoding='utf-8').read()
    body = 'public class' + text.split('public class', 1)[1]
    # 1.21.1 item API
    body = body.replace(
        'public int getUseDuration(ItemStack itemstack) {',
        'public int getUseDuration(ItemStack itemstack, LivingEntity entity) {')
    body = body.replace(
        'public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {\n      super.appendHoverText(itemstack, world, list, flag);\n   }',
        '')
    body = body.replace(
        'itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));',
        'itemstack.hurtAndBreak(1, entity, LivingEntity.getSlotForHand(entity.getUsedItemHand()));')
    # flaregun0 ammo lookup + damage: keep hurt(int, RandomSource, ServerPlayer) which still exists in 1.21.1
    open(os.path.join(DST, fn), 'w', encoding='utf-8').write(HEADER + body)
    count += 1
print('items transformed:', count)
