package me.xjqsh.lesraisinsarmor.init;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(net.minecraft.core.registries.BuiltInRegistries.ITEM, LesRaisinsArmor.MOD_ID);

    static {
        registerInBatch("armored_chemical");
        registerInBatch("attacker", ModEffects.TOUGH);
        registerInBatch("chemical_protective");
        registerInBatch("defender", ModEffects.HEAVY_ARMOR);
        registerInBatch("medical", ModEffects.RESCUE);
        registerInBatch("scout", ModEffects.LIGHT_LEG);
        registerInBatch("sniper");
        registerInBatch("dea_armed");
        registerInBatch("dea");
        registerInBatch("atf");
        registerInBatch("atf_vest");
        registerInBatch("irs");
        registerInBatch("fbi");
        registerInBatch("fbi_armed");
        registerInBatch("joker");
        registerSingle(ArmorItem.Type.HELMET, "joker_armed");
        registerSingle(ArmorItem.Type.CHESTPLATE, "joker_armed");
    }

    public static void registerSingle(ArmorItem.Type slotType, String name){
        String slotName = slotType.getName();
        REGISTER.register(name + "_" + slotName, ()->new LrArmorItem(name, slotType,
                new Item.Properties().stacksTo(1), null));
    }

    public static void registerInBatch(String name){
        for (ArmorItem.Type slotType : ArmorItem.Type.values()){
            if (slotType == ArmorItem.Type.BODY) continue;
            String slotName = slotType.getName();
            REGISTER.register(name + "_" + slotName,
                    ()->new LrArmorItem(name, slotType, new Item.Properties().stacksTo(1), null));
        }
    }

    public static void registerInBatch(String name, DeferredHolder<MobEffect, ? extends MobEffect> effect){
        for (ArmorItem.Type slotType : ArmorItem.Type.values()){
            if (slotType == ArmorItem.Type.BODY) continue;
            String slotName = slotType.getName();
            Supplier<Holder<MobEffect>> supplier = () -> effect;
            REGISTER.register(name + "_" + slotName,
                    ()->new LrArmorItem(name, slotType, new Item.Properties().stacksTo(1), supplier));
        }
    }

}
