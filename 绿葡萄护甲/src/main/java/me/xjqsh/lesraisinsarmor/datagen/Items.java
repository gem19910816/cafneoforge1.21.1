package me.xjqsh.lesraisinsarmor.datagen;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = LesRaisinsArmor.MOD_ID)
public class Items {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new ItemModels(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
    }

    public static class ItemModels extends ItemModelProvider {
        public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
            super(output, LesRaisinsArmor.MOD_ID, existingFileHelper);
        }

        @Override
        protected void registerModels() {
            for(Item item : BuiltInRegistries.ITEM){
                if(item instanceof LrArmorItem){
                    getBuilder(item.toString())
                            .parent(getExistingFile(mcLoc("item/generated")))
                            .texture("layer0", "item/"+ item);
                }
            }

        }
    }
}
