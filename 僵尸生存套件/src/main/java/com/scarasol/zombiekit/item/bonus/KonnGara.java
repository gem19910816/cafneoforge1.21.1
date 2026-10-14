package com.scarasol.zombiekit.item.bonus;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

// 1.21 已无 RecordItem（唱片改为数据驱动 JukeboxSong），保留构造器签名，唱片播放功能依赖数据包 jukebox_song 资源（另由资源代理处理）
public class KonnGara extends Item {

    public KonnGara(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder, int i1) {
        super(builder);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(Component.translatable("item.zombiekit.konn_gara.description"));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }
}
