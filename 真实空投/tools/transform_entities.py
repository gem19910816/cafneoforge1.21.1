# -*- coding: utf-8 -*-
import os, re

SRC = r'C:\Users\79662\AppData\Local\Temp\dyairdrop_moj\net\mcreator\dyairdrop\entity'
DST = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop\entity'
os.makedirs(DST, exist_ok=True)

count = 0
for root, dirs, files in os.walk(SRC):
    for fn in files:
        if not fn.endswith('.java'):
            continue
        srcfile = os.path.join(root, fn)
        rel = os.path.relpath(srcfile, SRC)
        sub = os.path.dirname(rel)
        pkg = 'net.mcreator.dyairdrop.entity' + ('.' + sub.replace(os.sep, '.') if sub and sub != '.' else '')
        text = open(srcfile, encoding='utf-8').read()
        body = 'public class' + text.split('public class', 1)[1]

        # drop SpawnEntity ctor + getAddEntityPacket override
        body = re.sub(r'\n   public \w+\(SpawnEntity packet, Level world\) \{\n      this\(\(EntityType<\w+>\)DyairdropModEntities\.\w+\.get\(\), world\);\n   \}', '', body)
        body = re.sub(r'\n   public Packet<ClientGamePacketListener> getAddEntityPacket\(\) \{\n      return NetworkHooks\.getEntitySpawningPacket\(this\);\n   \}', '', body)
        # defineSynchedData 1.21.1 signature
        body = body.replace(
            'protected void defineSynchedData() {\n      super.defineSynchedData();\n      this.entityData.define',
            'protected void defineSynchedData(SynchedEntityData.Builder builder) {\n      super.defineSynchedData(builder);\n      builder.define')
        # passenger offset -> attachment point
        def riderepl(m):
            return ('\n   @Override\n   public Vec3 getDefaultPassengerAttachmentPoint() {\n'
                    '      return new Vec3(0.0, this.getBbHeight() + %s, 0.0);\n   }' % m.group(1))
        body = re.sub(r'\n   public double getPassengersRidingOffset\(\) \{\n      return super\.getPassengersRidingOffset\(\) \+ ([0-9.]+);\n   \}', riderepl, body)
        body = re.sub(r'\n   @OnlyIn\(value = Dist\.CLIENT, _interface = ItemSupplier\.class\)', '', body)
        body = re.sub(r'\n   @OnlyIn\(Dist\.CLIENT\)', '', body)
        # flare entity 1.21.1 APIs
        body = body.replace('setSecondsOnFire(100)', 'igniteForSeconds(100)')
        body = re.sub(r'\(SoundEvent\)ForgeRegistries\.SOUND_EVENTS\.getValue\(new ResourceLocation\("entity\.firework_rocket\.launch"\)\)', 'SoundEvents.FIREWORK_ROCKET_LAUNCH', body)

        header = "package %s;\n\n" % pkg
        imports = set()
        for m in re.finditer(r'import (net\.mcreator\.dyairdrop\.procedures\.[\w]+);', text):
            imports.add('import %s;' % m.group(1))
        imports.add('import net.mcreator.dyairdrop.init.DyairdropModEntities;')
        if re.search(r'\b(SHOOT|ANIMATION|TEXTURE|entityData|defineSynchedData)', body):
            imports.update([
                'import net.minecraft.network.syncher.EntityDataAccessor;',
                'import net.minecraft.network.syncher.EntityDataSerializers;',
                'import net.minecraft.network.syncher.SynchedEntityData;',
            ])
        if re.search(r'\bGeoEntity\b', body):
            imports.add('import software.bernie.geckolib.animatable.GeoEntity;')
        if re.search(r'AnimatableInstanceCache', body):
            imports.add('import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;')
        if re.search(r'new AnimationController|ControllerRegistrar', body):
            imports.update([
                'import software.bernie.geckolib.animation.AnimationController;',
                'import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;',
            ])
        if re.search(r'AnimationState[ )]', body):
            imports.add('import software.bernie.geckolib.animation.AnimationState;')
        if re.search(r'RawAnimation', body):
            imports.add('import software.bernie.geckolib.animation.RawAnimation;')
        if re.search(r'PlayState', body):
            imports.add('import software.bernie.geckolib.animation.PlayState;')
        if re.search(r'AnimationController\.State', body):
            imports.add('import software.bernie.geckolib.animation.AnimationController.State;')
        if re.search(r'GeckoLibUtil', body):
            imports.add('import software.bernie.geckolib.util.GeckoLibUtil;')
        if re.search(r'GeoRenderLayer<', body):
            imports.add('import software.bernie.geckolib.renderer.layer.GeoRenderLayer;')
        if re.search(r'GeoRenderer<', body):
            imports.add('import software.bernie.geckolib.renderer.GeoRenderer;')
        if re.search(r'BakedGeoModel', body):
            imports.add('import software.bernie.geckolib.cache.object.BakedGeoModel;')
        if re.search(r'RenderType', body):
            imports.add('import net.minecraft.client.renderer.RenderType;')
        if re.search(r'MultiBufferSource', body):
            imports.add('import net.minecraft.client.renderer.MultiBufferSource;')
        if re.search(r'VertexConsumer', body):
            imports.add('import com.mojang.blaze3d.vertex.VertexConsumer;')
        if re.search(r'PoseStack', body):
            imports.add('import com.mojang.blaze3d.vertex.PoseStack;')
        if re.search(r'OverlayTexture', body):
            imports.add('import net.minecraft.client.renderer.texture.OverlayTexture;')

        imports.update([
            'import javax.annotation.Nullable;',
            'import net.minecraft.core.BlockPos;',
            'import net.minecraft.nbt.CompoundTag;',
            'import net.minecraft.network.protocol.Packet;',
            'import net.minecraft.network.protocol.game.ClientGamePacketListener;',
            'import net.minecraft.resources.ResourceLocation;',
            'import net.minecraft.server.level.ServerLevel;',
            'import net.minecraft.server.level.TicketType;',
            'import net.minecraft.sounds.SoundEvents;',
            'import net.minecraft.sounds.SoundSource;',
            'import net.minecraft.util.RandomSource;',
            'import net.minecraft.world.InteractionHand;',
            'import net.minecraft.world.damagesource.DamageSource;',
            'import net.minecraft.world.damagesource.DamageTypes;',
            'import net.minecraft.world.entity.AreaEffectCloud;',
            'import net.minecraft.world.entity.Entity.RemovalReason;',
            'import net.minecraft.world.entity.EntityDimensions;',
            'import net.minecraft.world.entity.EntityType;',
            'import net.minecraft.world.entity.EquipmentSlot;',
            'import net.minecraft.world.entity.LivingEntity;',
            'import net.minecraft.world.entity.Mob;',
            'import net.minecraft.world.entity.MobType;',
            'import net.minecraft.world.entity.PathfinderMob;',
            'import net.minecraft.world.entity.Pose;',
            'import net.minecraft.world.entity.ai.attributes.AttributeSupplier;',
            'import net.minecraft.world.entity.ai.attributes.Attributes;',
            'import net.minecraft.world.entity.ai.control.FlyingMoveControl;',
            'import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;',
            'import net.minecraft.world.entity.ai.navigation.PathNavigation;',
            'import net.minecraft.world.entity.item.ItemEntity;',
            'import net.minecraft.world.entity.player.Player;',
            'import net.minecraft.world.entity.projectile.AbstractArrow;',
            'import net.minecraft.world.entity.projectile.ItemSupplier;',
            'import net.minecraft.world.entity.projectile.ThrownPotion;',
            'import net.minecraft.world.item.ItemStack;',
            'import net.minecraft.world.item.ProjectileWeaponItem;',
            'import net.minecraft.world.level.ChunkPos;',
            'import net.minecraft.world.level.Level;',
            'import net.minecraft.world.phys.BlockHitResult;',
            'import net.minecraft.world.phys.EntityHitResult;',
            'import net.minecraft.world.phys.Vec3;',
        ])
        header += '\n'.join(sorted(imports)) + '\n\n'
        outp = os.path.join(DST, rel)
        os.makedirs(os.path.dirname(outp), exist_ok=True)
        open(outp, 'w', encoding='utf-8').write(header + body)
        count += 1
print('entities transformed:', count)
