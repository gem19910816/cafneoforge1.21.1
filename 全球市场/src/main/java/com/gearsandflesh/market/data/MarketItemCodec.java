package com.gearsandflesh.market.data;

import com.gearsandflesh.market.MarketConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

/**
 * Market-specific ItemStack serialization and validation (1.21.1 data
 * components edition). The quantity always travels separately from the
 * template stack so the protocol stays explicit.
 */
public final class MarketItemCodec {
    private static final int NETWORK_BUFFER_SLACK = 1_024;

    private MarketItemCodec() {
    }

    // ------------------------------------------------------------------
    // Network (RegistryFriendlyByteBuf)
    // ------------------------------------------------------------------

    public static void write(RegistryFriendlyByteBuf buffer, ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getCount() <= 0) {
            throw new IllegalArgumentException("Cannot encode an empty market item");
        }
        int quantity = stack.getCount();
        ItemStack template = stack.copy();
        template.setCount(1);
        ItemStack.STREAM_CODEC.encode(buffer, template);
        buffer.writeVarInt(quantity);
    }

    public static ItemStack read(RegistryFriendlyByteBuf buffer) {
        ItemStack stack = ItemStack.STREAM_CODEC.decode(buffer);
        int quantity = buffer.readVarInt();
        if (stack.isEmpty() || quantity <= 0) {
            throw new DecoderException("Invalid market item quantity: " + quantity);
        }
        stack.setCount(quantity);
        return stack;
    }

    // ------------------------------------------------------------------
    // JSON (website API)
    // ------------------------------------------------------------------

    /** Serializes a stack (with count) to the JSON shape the website stores. */
    public static JsonElement toJson(HolderLookup.Provider registries, ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getCount() <= 0) {
            throw new IllegalArgumentException("Cannot encode an empty market item");
        }
        DataResult<JsonElement> result = ItemStack.CODEC.encodeStart(
                registries.createSerializationContext(JsonOps.INSTANCE), stack);
        return result.getOrThrow(msg -> new IllegalStateException("物品数据无法序列化: " + msg));
    }

    /** Parses a stack previously produced by {@link #toJson}. */
    public static Optional<ItemStack> fromJson(HolderLookup.Provider registries, String json) {
        if (json == null || json.isBlank()) {
            return Optional.empty();
        }
        try {
            JsonElement element = JsonParser.parseString(json);
            DataResult<ItemStack> result = ItemStack.CODEC.parse(
                    registries.createSerializationContext(JsonOps.INSTANCE), element);
            return result.result().filter(stack -> !stack.isEmpty());
        } catch (RuntimeException failure) {
            return Optional.empty();
        }
    }

    // ------------------------------------------------------------------
    // Validation
    // ------------------------------------------------------------------

    /**
     * Returns null when the stack is safe to escrow, persist, and send. The
     * error text is intentionally suitable for a player-facing rejection.
     */
    public static String validationProblem(HolderLookup.Provider registries, ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getCount() <= 0) {
            return "物品数量无效";
        }
        int maxStackSize = stack.getMaxStackSize();
        if (maxStackSize <= 0 || stack.getCount() > maxStackSize) {
            return "物品数量超过当前堆叠上限";
        }

        Tag saved;
        try {
            saved = stack.save(registries);
        } catch (RuntimeException failure) {
            return "物品数据无法序列化";
        }

        if (!(saved instanceof CompoundTag compound) || !fitsUncompressedNbtLimit(compound)) {
            return "物品未压缩数据超过 128 KiB，不能上架";
        }

        Optional<ItemStack> persisted;
        try {
            persisted = ItemStack.parse(registries, compound);
        } catch (RuntimeException failure) {
            return "物品数据无法恢复";
        }
        if (persisted.isEmpty()
                || !sameItemAndCount(stack, persisted.get())
                || !ItemStack.isSameItemSameComponents(stack, persisted.get())) {
            return "物品数据无法安全保存";
        }

        return null;
    }

    public static int uncompressedPersistentBytes(HolderLookup.Provider registries, ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return -1;
        }
        try {
            Tag saved = stack.save(registries);
            if (!(saved instanceof CompoundTag compound)) {
                return -1;
            }
            CountingOutputStream output = new CountingOutputStream(Integer.MAX_VALUE);
            try (DataOutputStream dataOutput = new DataOutputStream(output)) {
                NbtIo.write(compound, dataOutput);
                return output.written();
            }
        } catch (IOException | RuntimeException failure) {
            return -1;
        }
    }

    private static boolean fitsUncompressedNbtLimit(CompoundTag tag) {
        try (DataOutputStream output = new DataOutputStream(
                new CountingOutputStream(MarketConstants.MAX_ITEM_NBT_BYTES))) {
            NbtIo.write(tag, output);
            return true;
        } catch (SizeLimitException tooLarge) {
            return false;
        } catch (IOException | RuntimeException failure) {
            return false;
        }
    }

    private static boolean sameItemAndCount(ItemStack expected, ItemStack actual) {
        return actual != null
                && !actual.isEmpty()
                && expected.getItem() == actual.getItem()
                && expected.getCount() == actual.getCount();
    }

    private static class CountingOutputStream extends OutputStream {
        private final int limit;
        private int written;

        private CountingOutputStream(int limit) {
            this.limit = limit;
        }

        int written() {
            return written;
        }

        @Override
        public void write(int value) throws IOException {
            ensureCapacity(1);
            written++;
        }

        @Override
        public void write(byte[] bytes, int offset, int length) throws IOException {
            if (offset < 0 || length < 0 || offset > bytes.length - length) {
                throw new IndexOutOfBoundsException();
            }
            ensureCapacity(length);
            written += length;
        }

        private void ensureCapacity(int additional) throws SizeLimitException {
            if (additional < 0 || additional > limit - written) {
                throw new SizeLimitException();
            }
        }
    }

    private static final class SizeLimitException extends IOException {
    }
}
