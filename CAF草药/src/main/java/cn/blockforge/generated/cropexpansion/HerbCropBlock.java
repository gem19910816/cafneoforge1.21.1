package cn.blockforge.generated.cropexpansion;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * 药草作物：直接继承原版 CropBlock，生长、骨粉、被 Entity 踩碎、
 * 只能种在耕地上等行为与小麦完全一致，这里只需告诉它种子是哪个物品。
 *
 * <p>1.21 起 {@code BlockBehaviour.codec()} 是抽象的。CropBlock 已经实现了它，
 * 所以不覆盖也能编译；这里覆盖一次是为了让序列化构造出来的仍是 HerbCropBlock 自己。
 */
public class HerbCropBlock extends CropBlock {

    public static final MapCodec<HerbCropBlock> CODEC = simpleCodec(HerbCropBlock::new);

    public HerbCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    /** 破坏未成熟的作物 / 中键取块时对应的种子。 */
    @Override
    protected ItemLike getBaseSeedId() {
        return ModContent.HERB_SEEDS.get();
    }
}
