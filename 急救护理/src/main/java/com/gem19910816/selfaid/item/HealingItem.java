package com.gem19910816.selfaid.item;

import java.util.List;
import java.util.Random;

import com.gem19910816.selfaid.body.ActiveHeal;
import com.gem19910816.selfaid.body.BodyHealthAccess;
import com.gem19910816.selfaid.body.BodyHealth;
import com.gem19910816.selfaid.body.BodyPart;
import com.gem19910816.selfaid.registry.ModAttachments;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * 自制的急救物品基类：按住右键使用后，给玩家挂上一条或多条持续治疗计划。
 * 目标部位在服务端使用完成时确定。
 */
public class HealingItem extends Item {

    public enum HealMode {
        /** 随机一条未满的四肢。 */
        RANDOM_LIMB,
        /** 头部和躯干中比例较低的一处。 */
        HEAD_TORSO,
        /** 全身所有未满部位按缺口比例分摊。 */
        ALL_PARTS;
    }

    /** 物品行为配置（非原版 Properties，避免链式调用类型问题）。 */
    public static class Properties {
        private int useTicks = 32;
        private UseAnim animation = UseAnim.EAT;
        private HealMode mode = HealMode.ALL_PARTS;
        private float totalHeal = 4.0F;
        private int healDurationTicks = 100;
        private boolean clearNegativeEffects = false;
        private Rarity rarity = Rarity.COMMON;
        private int stackSize = 16;

        public Properties useTicks(int ticks) {
            this.useTicks = ticks;
            return this;
        }

        public Properties animation(UseAnim anim) {
            this.animation = anim;
            return this;
        }

        public Properties mode(HealMode mode) {
            this.mode = mode;
            return this;
        }

        public Properties totalHeal(float heal) {
            this.totalHeal = heal;
            return this;
        }

        public Properties healDurationTicks(int ticks) {
            this.healDurationTicks = ticks;
            return this;
        }

        public Properties clearNegativeEffects() {
            this.clearNegativeEffects = true;
            return this;
        }

        public Properties rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Properties stackSize(int size) {
            this.stackSize = size;
            return this;
        }
    }

    private static final Random RANDOM = new Random();

    private final Properties props;

    public HealingItem(Properties props) {
        super(new Item.Properties().stacksTo(props.stackSize).rarity(props.rarity));
        this.props = props;
    }

    public Properties healingProps() {
        return props;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (canTargetAnything(player)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    /** 客户端侧通过同步数据判断、服务端侧通过附加数据判断是否还有可治疗部位。 */
    private static boolean canTargetAnything(Player player) {
        float[] ratios = BodyHealthAccess.partRatios(player);
        for (float ratio : ratios) {
            if (ratio < 1.0F) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player && !level.isClientSide()) {
            BodyHealth health = player.getData(ModAttachments.BODY_HEALTH.get());
            int added = BodyHealthAccess.scheduleHeal(player, health, props.mode, props.totalHeal,
                    props.healDurationTicks);
            if (added > 0) {
                if (props.clearNegativeEffects) {
                    clearNegativeEffects(player);
                }
                level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.6F,
                        1.4F);
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }

    private static void clearNegativeEffects(Player player) {
        List<MobEffectInstance> snapshot = List.copyOf(player.getActiveEffects());
        for (MobEffectInstance effect : snapshot) {
            if (!effect.getEffect().value().isBeneficial()) {
                player.removeEffect(effect.getEffect());
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return props.useTicks;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return props.animation;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip,
            TooltipFlag flag) {
        tooltip.add(Component.translatable(switch (props.mode) {
            case RANDOM_LIMB -> "tooltip.selfaid.bandage";
            case HEAD_TORSO -> "tooltip.selfaid.plaster";
            case ALL_PARTS -> props.clearNegativeEffects
                    ? (props.totalHeal >= 10.0F ? "tooltip.selfaid.first_aid_kit" : "tooltip.selfaid.morphine")
                    : "tooltip.selfaid.generic";
        }).withStyle(ChatFormatting.GRAY));
        if (props.clearNegativeEffects) {
            tooltip.add(Component.translatable("tooltip.selfaid.clear_effects").withStyle(ChatFormatting.BLUE));
        }
    }
}
