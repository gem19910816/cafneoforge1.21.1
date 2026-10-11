package com.scarasol.rummage.porttest;

import com.scarasol.rummage.api.mixin.ILootrInventoryDelegate;
import com.scarasol.rummage.api.mixin.IRummageable;
import com.scarasol.rummage.compat.petiteinventory.PetiteInventoryCompat;
import de.maxhenkel.corpse.corelib.death.Death;
import de.maxhenkel.corpse.entities.CorpseEntity;
import de.maxhenkel.corpse.gui.CorpseInventoryContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;
import noobanidus.mods.lootr.common.data.LootrInventory;
import net.neoforged.neoforge.common.util.FakePlayer;

import java.lang.reflect.Proxy;

final class CompatSmokeTests {
    static void run(ServerLevel level, FakePlayer player) {
        player.getInventory().setItem(0, new ItemStack(Items.DIAMOND));
        CorpseEntity corpse = CorpseEntity.createFromDeath(player, Death.fromPlayer(player));
        IRummageable corpseRummage = (IRummageable) corpse;
        corpseRummage.setNeedRummage(true);
        AbstractContainerMenu corpseMenu = new CorpseInventoryContainer(2, player.getInventory(), corpse, true, false);
        player.containerMenu = corpseMenu;
        require(corpseMenu.slots.stream().anyMatch(slot -> slot.container instanceof IRummageable), "Corpse inventories bound");
        require(PetiteInventoryCompat.getWidth(new ItemStack(Items.DIAMOND)) > 0, "Petite API area");

        BlockPos pos = new BlockPos(4, 150, 0);
        var block = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("lootr", "lootr_chest"));
        level.setBlockAndUpdate(pos, block.defaultBlockState());
        var source = level.getBlockEntity(pos);
        require(source instanceof IRummageable, "Lootr chest is rummageable");
        IRummageable sourceRummage = (IRummageable) source;
        sourceRummage.setNeedRummage(true);
        var info = (ILootrSavedData) Proxy.newProxyInstance(ILootrSavedData.class.getClassLoader(),
                new Class<?>[]{ILootrSavedData.class}, (proxy, method, args) -> switch (method.getName()) {
                    case "getInfoContainer" -> source;
                    case "getInfoContainerSize" -> 27;
                    case "getInfoUUID" -> sourceRummage.getRummageableUUID();
                    case "markChanged" -> null;
                    default -> null;
                });
        var contents = NonNullList.withSize(27, ItemStack.EMPTY);
        contents.set(0, new ItemStack(Items.DIAMOND));
        LootrInventory inventory = new LootrInventory(info, contents);
        inventory.createMenu(3, player.getInventory(), player);
        require(((ILootrInventoryDelegate) inventory).rummage$getBlockEntity() == sourceRummage, "Lootr delegate bound");
        require(((IRummageable) inventory).isNeedRummage(), "Lootr inventory locked");
        level.removeBlock(pos, false);
    }

    private static void require(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }
}
