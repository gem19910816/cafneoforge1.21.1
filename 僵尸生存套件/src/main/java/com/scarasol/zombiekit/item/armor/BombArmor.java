package com.scarasol.zombiekit.item.armor;

import com.google.common.collect.Maps;
import com.scarasol.zombiekit.ZombieKitMod;
import com.scarasol.zombiekit.client.model.BombSuitModel;
import com.scarasol.zombiekit.init.ZombieKitModels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.Map;
import java.util.function.Consumer;

public class BombArmor extends ArmorItem {

    private static final ResourceLocation ATTACK_SPEED_ID = ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "bomb_armor_attack_speed");
    private static final ResourceLocation SLOWNESS_ID = ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "bomb_armor_slowness");
    private static final ResourceLocation HEALTH_BOOST_ID = ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "bomb_armor_health_boost");
    private static final AttributeModifier ATTACK_SPEED = new AttributeModifier(ATTACK_SPEED_ID, -0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final AttributeModifier SLOWNESS = new AttributeModifier(SLOWNESS_ID, -0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    private static final AttributeModifier HEALTH_BOOST = new AttributeModifier(HEALTH_BOOST_ID, 20, AttributeModifier.Operation.ADD_VALUE);
    public final static Map<EquipmentSlot, HumanoidModel<LivingEntity>> ARMOR_MODEL = Maps.newHashMap();

    public BombArmor(Holder<ArmorMaterial> armorMaterial, Type equipmentSlot, Properties properties) {
        super(armorMaterial, equipmentSlot, properties.durability(equipmentSlot.getDurability(ModArmorMaterial.getDurabilityMultiplier(armorMaterial))));
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(ZombieKitMod.MODID, "textures/entities/bomb_suit.png");
    }

    @OnlyIn(Dist.CLIENT)
    public HumanoidModel getArmorModel(){
        if (ARMOR_MODEL.isEmpty()) {
            for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
                if (equipmentSlot.isArmor()) {
                    ARMOR_MODEL.put(equipmentSlot, ZombieKitModels.getDefaultArmorModel(equipmentSlot, new BombSuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(BombSuitModel.LAYER_LOCATION))));
                }
            }
        }
        return ARMOR_MODEL.get(getEquipmentSlot());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel<LivingEntity> armorModel = BombArmor.this.getArmorModel();
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }

    public static void updateModifier(LivingEntity livingEntity){
        int count = numberOfSuit(livingEntity);
        if (count == 0){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST_ID);
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SLOWNESS_ID);
            if (livingEntity instanceof Player)
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_ID);
        }else if (count == 4){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST_ID);
            livingEntity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(HEALTH_BOOST);
        }else if (count == 1){
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SLOWNESS_ID);
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(SLOWNESS);
            if (livingEntity instanceof Player){
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_ID);
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(ATTACK_SPEED);
            }
        }else if (count == 2){
            livingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SLOWNESS_ID);
            if (livingEntity instanceof Player)
                livingEntity.getAttribute(Attributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_ID);
        }else if (count == 3){
            livingEntity.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BOOST_ID);
        }
    }

    public static int numberOfSuit(LivingEntity livingEntity){
        int count = 0;
        for (ItemStack itemStack : livingEntity.getArmorSlots()){
            if (itemStack.getItem() instanceof BombArmor)
                count++;
        }
        return count;
    }
}
