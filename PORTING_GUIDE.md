# Forge 1.20.1 → NeoForge 1.21.1 移植规范

本文件是所有移植工作的**强制规范**。目标：代码编译通过（`compileJava` 无错误），语义与原 1.20.1 版本一致。

## 工程环境

- 工作目录（每个代理不同，见各自任务书）：`E:\port\neoforge\<工程名>`
- 编译命令（Windows Git Bash）：
  ```bash
  cd /e/port/neoforge/<工程名> && JAVA_HOME=/e/port/tools/jdk21 /e/port/tools/gradle/bin/gradle compileJava --console=plain
  ```
- 提取错误清单：
  ```bash
  ... 2>&1 | grep -F ".java:" | sed 's/.*src.main.java.//' > errors.txt
  ```
- MC 1.21.1 mojmap jar（查类/方法签名）：`/e/port/tools/mc-1.21.1-mojmap.jar`
- NeoForge 21.1.252：`/e/PCL/.minecraft/libraries/net/neoforged/neoforge/21.1.252/neoforge-21.1.252-universal.jar`（+ 同目录 `-client.jar`）
- 查签名示例：
  ```bash
  unzip -p /e/port/tools/mc-1.21.1-mojmap.jar net/minecraft/world/item/ItemStack.class > /tmp/c.class && /e/port/tools/jdk21/bin/javap -p /tmp/c.class | grep -i getCount
  unzip -p /e/PCL/.minecraft/libraries/net/neoforged/neoforge/21.1.252/neoforge-21.1.252-universal.jar net/neoforged/neoforge/event/entity/living/LivingDamageEvent.class > /tmp/c.class && /e/port/tools/jdk21/bin/javap -p /tmp/c.class
  ```
- 原始 1.20.1 源码参考：`/e/port/sona/src` 和 `/e/port/zombiekit/src`
- **不确定 NeoForge 是否有某个类时，先 `unzip -l` 查 jar，禁止臆造 API。**

## 包名映射（机械替换）

| Forge 1.20.1 | NeoForge 1.21.1 |
|---|---|
| `net.minecraftforge.eventbus.api.*` | `net.neoforged.bus.api.*` |
| `net.minecraftforge.fml.common.Mod` | `net.neoforged.fml.common.Mod` |
| `net.minecraftforge.fml.common.Mod.EventBusSubscriber` | `net.neoforged.fml.common.EventBusSubscriber`（独立注解，bus 参数为 `EventBusSubscriber.Bus.MOD`） |
| `net.minecraftforge.api.distmarker.*` | `net.neoforged.api.distmarker.*` |
| `net.minecraftforge.fml.*`（ModList/FMLPaths/loading.*） | `net.neoforged.fml.*` |
| `net.minecraftforge.common.MinecraftForge.EVENT_BUS` | `net.neoforged.neoforge.common.NeoForge.EVENT_BUS` |
| `net.minecraftforge.event.*` | `net.neoforged.neoforge.event.*` |
| `net.minecraftforge.client.event.*` | `net.neoforged.neoforge.client.event.*` |
| `net.minecraftforge.registries.ForgeRegistries` | 原版注册表用 `net.minecraft.core.registries.BuiltInRegistries`；Forge 扩展注册表用 `net.neoforged.neoforge.registries.NeoForgeRegistries` |
| `net.minecraftforge.registries.DeferredRegister` / `RegistryObject` | `net.neoforged.neoforge.registries.DeferredRegister` / `DeferredHolder`（`.get()` 不变；需要 Holder 时直接传 DeferredHolder） |
| `net.minecraftforge.items.*` | `net.neoforged.neoforge.items.*` |
| `net.minecraftforge.common.util.FakePlayerFactory` | `net.neoforged.neoforge.common.util.FakePlayerFactory` |
| `net.minecraftforge.common.ForgeMod` / `ForgeHooks` | `net.neoforged.neoforge.common.NeoForgeMod` / `NeoForgeHooks`（注意属性是 `Holder<Attribute>`） |
| `net.minecraftforge.fml.DistExecutor` | 已移除。用 `FMLEnvironment.dist == Dist.CLIENT` 判断，或事件驱动 |
| `net.minecraftforge.fml.ModLoadingContext` 注册配置 | `@Mod` 构造函数注入 `ModContainer`，调 `container.registerConfig(ModConfig.Type.COMMON, SPEC)` |
| `net.minecraftforge.network.*` | 见「网络」节 |
| `software.bernie.geckolib.core.*`（GeckoLib 3） | GeckoLib 4：删掉 `.core`（如 `software.bernie.geckolib.animatable.GeoItem`）；`AnimatableManager`、渲染器接口有变动，以 `/e/port/libs/geckolib-neoforge-1.21.1-4.9.3.jar` 实际内容为准 |

## 高频 API 变动

### ResourceLocation（构造器已私有）
- `new ResourceLocation("modid", "path")` → `ResourceLocation.fromNamespaceAndPath("modid", "path")`
- `new ResourceLocation("modid:path")` / `new ResourceLocation("path")` → `ResourceLocation.parse(...)`

### MobEffect / Holder 化
- 一切 `MobEffect` 参数基本变为 `Holder<MobEffect>`：`hasEffect(holder)`、`new MobEffectInstance(holder, ...)`。
- 本 mod 的效果统一由 `SonaEffects`（DeferredRegister）注册，register 返回 `DeferredHolder<MobEffect, T>`——**需要 Holder 的地方直接传 DeferredHolder，需要 MobEffect 的地方用 `.get()`**。
- `MobEffectInstance#getEffect()` 现在返回 `Holder<MobEffect>`：比较用 `.is(holder)`，取实例用 `.value()`。
- 效果构造器里加属性修饰：`addAttributeModifier(Holder<Attribute>, ResourceLocation, double, AttributeModifier.Operation)`（1.21 MobEffect 自带此方法，在构造器中调用；ResourceLocation 用 `ResourceLocation.fromNamespaceAndPath(MODID, "effect_xxx")`）。
- `removeAttributeModifiers(...)` 已删除——1.21 效果结束时自动移除其属性修饰，删除对应调用即可。

### AttributeModifier（id 取代 UUID）
- `new AttributeModifier(UUID, String, double, Operation)` → `new AttributeModifier(ResourceLocation, double, Operation)`。
- 同一修饰符用固定 ResourceLocation 代替固定 UUID；移除用 `attribute.removeModifier(ResourceLocation)`。

### EntitySpawnReason
- `MobSpawnType.X` → `net.minecraft.world.entity.EntitySpawnReason.X`（枚举常量名相同，如 `NATURAL`、`SPAWNER`）。`finalizeSpawn` 签名第三个参数换成 `EntitySpawnReason`。

### NBT / DataComponents
- `ItemStack#getTag/setTag/getOrCreateTag` 已删除。自定义 NBT 用 `DataComponents.CUSTOM_DATA`（类型 `CustomData`）：
  - 读：`tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag()`
  - 写：`CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> { ... })` 或 `CustomData.set(DataComponents.CUSTOM_DATA, stack, tag)`
- `ItemStack#save(CompoundTag)` → `stack.save(HolderLookup.Provider, CompoundTag)`；`ItemStack.of(tag)` → `ItemStack.parse(provider, tag)`。`provider` 用 `level.registryAccess()`。
- `BlockEntity#saveWithoutMetadata()` → `saveWithoutMetadata(HolderLookup.Provider)`，构造参数/加载方法同理多加 `HolderLookup.Provider`（用 `level.registryAccess()`）。

### 网络（SimpleChannel 已删除）
1.21 用 `CustomPacketPayload` + `RegisterPayloadHandlersEvent`：
```java
public record MyPacket(int x) implements CustomPacketPayload {
    public static final Type<MyPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MODID, "my_packet"));
    public static final StreamCodec<FriendlyByteBuf, MyPacket> CODEC = StreamCodec.of(
        (buf, p) -> buf.writeInt(p.x),
        buf -> new MyPacket(buf.readInt()));
    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
// 注册（mod 事件总线）：
@SubscribeEvent static void register(RegisterPayloadHandlersEvent e) {
    PayloadRegistrar r = e.registrar("1");
    r.playToClient(MyPacket.TYPE, MyPacket.CODEC, MyPacket::handle); // 或 playToServer / playBidirectional
}
// 处理签名：static void handle(MyPacket payload, IPayloadContext ctx)
// ctx.enqueueWork(() -> {...}); ctx.player(); 不再需要 setPacketHandled
```
发送：
- `PacketDistributor.SERVER.noArg().send(p)` → `PacketDistributor.sendToServer(p)`
- 发给玩家：`PacketDistributor.sendToPlayer((ServerPlayer) player, p)`
- 追踪实体：`PacketDistributor.sendToPlayersTrackingEntity(entity, p)`；含自己：`sendToPlayersTrackingEntityAndSelf`
- 维度/全服：`PacketDistributor.sendToPlayersInDimension(...)` / `sendToAllPlayers(...)`
（`net.neoforged.neoforge.network.PacketDistributor`，静态方法，以 jar 实际签名为准）

### 事件细节
- `LivingDamageEvent` → 拆分为 `LivingDamageEvent.Pre` / `.Post`（`Pre` 可取消、`setNewAmount`；`Post` 只读结果）。原来的 `getAmount/setAmount` 在 `Pre` 上是 `getOriginalAmount/setNewAmount`，以 jar 为准。
- `LivingHealEvent` 同理可取消，`getAmount/setAmount` 保留。
- 渲染 GUI：`RenderGuiEvent` → `RenderGuiLayerEvent`（Pre/Post）或 `RegisterGuiLayersEvent`；取 GuiGraphics 用 `event.getGuiGraphics()`。
- 事件取消：`event.setCanceled(true)` 仍然有效（ICancellableEvent）。

### 渲染（1.21 大改）
- `Tesselator.getInstance().getBuilder()` → `Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.XXX)`（返回 `MeshData`，需 `bufferBuilder` 由 begin 的返回值… 正确用法：`BufferBuilder bb = Tesselator.getInstance().begin(mode, format)`，画完后 `bb.build()` 得到 MeshData，再 `BufferUploader.drawWithShader(meshData)`）。以 jar 中 Tesselator 实际方法为准。
- GUI 事件里 PoseStack 改从 `GuiGraphics` 拿：`guiGraphics.pose()`。
- `RenderSystem.setShaderTexture` 等大多不变；`RenderType` 内部类访问受限的问题用 `@Mixin(targets="...")` 字符串 + Accessor 解决。
- `Matrix4fStack`（RenderSystem.getModelViewStack）与 PoseStack 不通用，注意区分。

### 能力/数据
- Forge 的 `Entity#getPersistentData()` 在 NeoForge 已删除 → 用 Attachment：
```java
// init 类中：
public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
    DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);
public static final DeferredHolder<AttachmentType<?>, AttachmentType<CompoundTag>> XXX =
    ATTACHMENT_TYPES.register("xxx", () -> AttachmentType.serializable(e -> new CompoundTag()).build());
// 注册：ATTACHMENT_TYPES.register(modEventBus);
// 使用：CompoundTag t = entity.getData(XXX); entity.setData(XXX, t);
```
（`AttachmentType.serializable` 的 INBTSerializable 以 jar 为准；CompoundTag 实现了它。）

### 其它
- `@OnlyIn(Dist.CLIENT)` → 包名换 `net.neoforged.api.distmarker`。
- `ModList.get().isLoaded("xxx")` 不变（包名变）。
- 配置：`ModConfigSpec` → `net.neoforged.neoforge.common.ModConfigSpec`（API 几乎不变）。
- `MinecraftForge.EVENT_BUS.register(this)` → `NeoForge.EVENT_BUS.register(this)`。
- Mod 事件总线：`FMLJavaModLoadingContext.get().getModEventBus()` 仍可用，或在 `@Mod` 构造器参数 `IEventBus modEventBus`。
- MixinExtras 包不变（`com.llamalad7.mixinextras...` 已在 classpath）。
- Mixin 报「protected 访问控制」的目标内部类：改用 `@Mixin(targets = "全限定名$内部类")` 字符串形式 + Accessor 接口。
- Mixin 注入签名错误（「找不到符号」指向 @Inject 方法）：先 javap 目标类确认 1.21.1 的方法名/描述符，再改 `method = ` 或 handler 参数。回调里用的 `CallbackInfo` 不变，`CallbackInfoReturnable` 不变。

## 禁止事项
- 禁止改动分配给你的文件之外的文件。
- 禁止为「消错」而删除功能；遇到确实无法对应的 API，保留逻辑用最小改动适配，并在文件顶部加一行注释说明。
- 不要改包名、类名、资源路径（资源另有人处理）。
