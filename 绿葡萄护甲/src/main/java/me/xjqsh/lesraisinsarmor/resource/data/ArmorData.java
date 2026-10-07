package me.xjqsh.lesraisinsarmor.resource.data;

import com.google.gson.*;
import me.xjqsh.lesraisinsarmor.LesRaisinsArmor;
import me.xjqsh.lesraisinsarmor.resource.ArmorDataManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class ArmorData {
    private final EnumMap<ArmorItem.Type, ArmorPartData> partData = new EnumMap<>(ArmorItem.Type.class);
    @Nullable
    public ArmorPartData getByType(@NotNull ArmorItem.Type type){
        return partData.get(type);
    }

    public <T> T getByType(@NotNull ArmorItem.Type type, T defaultValue, Function<ArmorPartData, T> getter){
        var data = this.getByType(type);
        if (data != null) {
            return getter.apply(data);
        }
        return defaultValue;
    }

    public static <T> T getByType(ArmorData data, @NotNull ArmorItem.Type type, T defaultValue, Function<ArmorPartData, T> getter){
        if (data==null) {
            return defaultValue;
        }
        return data.getByType(type, defaultValue, getter);
    }

    public static class Deserializer implements JsonDeserializer<ArmorData> {
        @Override
        public ArmorData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
            ArmorData data = new ArmorData();
            JsonObject object = jsonElement.getAsJsonObject();
            Map<String, JsonElement> map = object.asMap();
            for (ArmorItem.Type armorType : ArmorItem.Type.values()){
                if (armorType == ArmorItem.Type.BODY) continue;
                if (map.containsKey(armorType.getName())) {
                    var struct = ArmorDataManager.GSON.fromJson(map.get(armorType.getName()), ArmorPartData.Struct.class);
                    ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath(LesRaisinsArmor.MOD_ID, "armor_" + armorType.getName());
                    EquipmentSlot slot = armorType.getSlot();
                    data.partData.put(armorType, ArmorPartData.fromJson(modifierId, struct, slot));
                }
            }
            return data;
        }
    }
}
