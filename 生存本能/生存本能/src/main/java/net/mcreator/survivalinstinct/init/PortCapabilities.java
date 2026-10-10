package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.item.PortableBagItem;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.minecraft.world.WorldlyContainer;

public final class PortCapabilities {
    public static void register(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.ItemHandler.ITEM, (stack, context) -> PortableBagItem.inventory(stack),
                SurvivalInstinctModItems.EMPTY_BAG.get(), SurvivalInstinctModItems.GARBAGE_BAG.get());
        event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, pos, state, blockEntity, side) ->
                blockEntity instanceof WorldlyContainer sided && side != null ? new SidedInvWrapper(sided, side)
                        : blockEntity instanceof net.minecraft.world.Container container ? new InvWrapper(container) : null,
                SurvivalInstinctModBlocks.REFRIGERATOR.get(), SurvivalInstinctModBlocks.CASH_REGISTER.get(), SurvivalInstinctModBlocks.TRASH_CAN.get());
    }
}
