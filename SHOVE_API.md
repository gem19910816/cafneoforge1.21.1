# Zombie shove integration

Right-click a zombie with an empty hand or a melee item. The server selects
visible targets inside a forward cone and rolls knockdown independently.
Failed rolls apply a stagger. The original living entity remains in place.

Both knockdown and stagger share one main-hand swing per shove action, not one
per target. The client predicts the normal held-item swing after checking the
same cone and visible eligible targets; the server broadcasts a swing only
after at least one target accepts the shove. Cooldown and no-target attempts
consume the zombie interaction without another swing or a damaging attack.
Prediction may still animate an attempt rejected later by a server event.

`ZombieShoveEvent` runs on `NeoForge.EVENT_BUS` on the server, once per eligible
target, before the roll. Cancel it to reject that target. Its setters clamp
chance to 0..1, strength to 0..1.5 and stagger duration to 10..60 ticks.

```java
@SubscribeEvent
public static void onShove(ZombieShoveEvent event) {
    double skill = getCharacterStrength(event.getPlayer());
    event.setKnockdownChance(event.getKnockdownChance() + skill * 0.02);
    event.setPushStrength(event.getPushStrength() + skill * 0.015);
}
```

`ZombieShoveResultEvent` runs once per applied target. `isKnockedDown()`
distinguishes knockdown from stagger. Award target-based XP here. For stamina
charged once per action, deduplicate by player UUID and server game tick;
multiple targets can report results in the same tick.

The SERVER config is `GE-SHOVE.toml` in the world's `serverconfig` directory.
Defaults: reach 2.25 blocks, cone half-angle 50 degrees, maximum 3 targets,
base knockdown chance 0.35, strength 0.45 and sound volume 0.75.
Knockback resistance reduces the default chance and strength before the event.

The server sends frozen body yaw, relative direction, original duration and
remaining duration to observers, including players who start tracking midway.
Four knockdown/recovery clips and four stagger clips blend at every angle.
The editable Blockbench reference and runtime share baked 60 fps keyframes.

With EMF installed, Gore explicitly evaluates the current resource-pack pose
before blending in the shove. The registered pause condition suppresses only
EMF's later draw-time evaluation, not the advancing base animation. Main-part
pivots, rotations, scales and visible headwear blend in and out; the renderer
restores saved main-part transforms after drawing.

Child-bone rotations use a separate Blockbench-to-model coordinate conversion.
Death and mutilation replacements carry the original texture identity through
the normal corpse, disarmed, headless, severed-leg, cut and crushed variants.

Regression checks can be run from the Gore project directory with:

```powershell
gradle verifyShovePose --init-script tools/verify_shove.gradle
```

The checks cover directional continuity, cone/probability rules, child-bone
quaternion conversion and shortest-arc pose blending. Visual pack compatibility
still requires an in-game test. The v10 baseline was user-tested; the newer
live-pose handoff change awaits visual verification.
