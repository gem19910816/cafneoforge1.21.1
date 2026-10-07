package me.xjqsh.lesraisinsarmor.handler;

import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.config.CommonConfig;
import me.xjqsh.lesraisinsarmor.init.ModEffects;
import me.xjqsh.lesraisinsarmor.item.LrArmorItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = LesRaisinsArmor.MOD_ID)
public class SuitHandler {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if(!CommonConfig.enableArmorSetEffect.get()) return;

        if(event.getEntity().level().isClientSide()) return;
        if(event.getEntity().tickCount % 10 != 0) return;

        Item item = event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem();
        if (item instanceof LrArmorItem armorItem) {
            if(armorItem.getSuitCount(event.getEntity())==4){
                if("medical".equals(armorItem.getSuitIdf())){
                    if(event.getEntity().getEffect(ModEffects.RESCUE_COOLDOWN)==null){
                        armorItem.applyEffect(event.getEntity());
                    }
                }else {
                    armorItem.applyEffect(event.getEntity());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDamage(LivingIncomingDamageEvent event){
        if(event.getEntity().level().isClientSide()) return;
        if(event.getEntity().hasEffect(ModEffects.HEAVY_ARMOR)){
            event.setAmount(event.getAmount()*0.85f);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerHurt(LivingIncomingDamageEvent event){
        if(event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) return;
        if(player.getHealth() - event.getAmount() < player.getMaxHealth()*0.3 && player.hasEffect(ModEffects.RESCUE)){
            player.removeEffect(ModEffects.RESCUE);
            player.addEffect(new MobEffectInstance(ModEffects.RESCUE_COOLDOWN, 600));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100,3));
        }
    }
}
