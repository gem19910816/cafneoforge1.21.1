package cn.blockforge.generated.cropexpansion;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 注册药草相关的全部方块与物品：
 * herb_crop 方块（8 个生长阶段）、herb_seeds 种子、herbs 草药产物。
 *
 * <p>这里刻意<b>不写</b> {@code bus = EventBusSubscriber.Bus.MOD}：
 * NeoForge 21.1 起 {@code EventBusSubscriber.Bus} / {@code bus()} 已标记为待删除
 * （编译会报 [removal] 弃用警告）。现在由 AutomaticEventSubscriber 按事件类型自动分流 ——
 * {@code BuildCreativeModeTabContentsEvent} 实现了 {@code IModBusEvent}，
 * 所以下面这个监听器会被自动挂到 mod 事件总线，行为与 1.20.1 写死 Bus.MOD 一致。
 */
@EventBusSubscriber(modid = CropExpansion.MOD_ID)
public final class ModContent {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CropExpansion.MOD_ID);
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(CropExpansion.MOD_ID);

    /**
     * 1.21.1 里 {@code CreativeModeTabs.NATURAL_BLOCKS} / {@code INGREDIENTS} 已经变成
     * private 字段，而且没有公开的取值方法，所以只能按原版 id 自己造 ResourceKey。
     */
    private static final ResourceKey<CreativeModeTab> TAB_NATURAL_BLOCKS =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    ResourceLocation.withDefaultNamespace("natural_blocks"));
    private static final ResourceKey<CreativeModeTab> TAB_INGREDIENTS =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    ResourceLocation.withDefaultNamespace("ingredients"));

    /** 药草作物方块，行为对齐原版小麦。 */
    public static final DeferredBlock<HerbCropBlock> HERB_CROP = BLOCKS.register("herb_crop",
            () -> new HerbCropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));

    /** 药草种子：种到耕地上长出药草作物。
     *  使用 ItemNameBlockItem（而非 BlockItem），让种子显示自己的
     *  item.crop_expansion.herb_seeds 翻译键，而不是方块名 block.crop_expansion.herb_crop。 */
    public static final DeferredItem<ItemNameBlockItem> HERB_SEEDS = ITEMS.registerItem("herb_seeds",
            properties -> new ItemNameBlockItem(HERB_CROP.get(), properties));

    /** 草药：成熟收割后的产物。 */
    public static final DeferredItem<Item> HERBS = ITEMS.registerSimpleItem("herbs");

    private ModContent() {
    }

    /** 把种子放进「自然方块」页、草药放进「材料」页，和原版小麦的摆放位置一致。 */
    @SubscribeEvent
    public static void onBuildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(TAB_NATURAL_BLOCKS)) {
            event.accept(new ItemStack(HERB_SEEDS.get()));
        } else if (event.getTabKey().equals(TAB_INGREDIENTS)) {
            event.accept(new ItemStack(HERBS.get()));
        }
    }
}
