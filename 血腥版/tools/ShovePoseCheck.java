import net.mcreator.gore.client.ZombieShoveAnimation;
import net.mcreator.gore.procedures.ZombieShoveRules;
import org.joml.Vector3f;
import org.joml.Quaternionf;

public final class ShovePoseCheck {
   private static final String[] BONES = {"root","body","head","leftArm","rightArm","leftLeg","rightLeg"};
   public static void main(String[] args) {
      int samples = 0;
      for (boolean downed : new boolean[] {true, false}) {
         float length = downed ? 4.2F : 1.0F;
         for (int direction = -180; direction <= 180; direction += 5) {
            for (String bone : BONES) {
               Vector3f previous = null;
               for (int step = 0; step <= Math.round(length * 100); step++) {
                  float time = step / 100.0F;
                  Vector3f value = ZombieShoveAnimation.directional(downed,direction,bone,"rotation",time);
                  if (!value.isFinite() || value.length() > 200) throw new AssertionError("Invalid pose");
                  if (previous != null && value.distance(previous) > 12) {
                     throw new AssertionError("Jump in " + bone + " at " + direction + "/" + time);
                  }
                  if (bone.equals("root") && (step == 0 || step == Math.round(length*100))
                     && value.length() > .01) throw new AssertionError("Endpoint tilt");
                  if (downed && bone.equals("root") && time >= 1 && time <= 2.5
                     && Math.abs(Math.hypot(value.x,value.z)-90) > .01) {
                     throw new AssertionError("Diagonal must lie flat: " + direction);
                  }
                  Vector3f adjacent = ZombieShoveAnimation.directional(downed,direction+.01F,bone,"rotation",time);
                  if (value.distance(adjacent) > .08F) throw new AssertionError("Angular seam");
                  previous = value;
                  samples++;
               }
            }
         }
      }
      if (!ZombieShoveRules.inCone(0,2,0,0,1,2.25,50)
         || !ZombieShoveRules.inCone(1,1,0,0,1,2.25,50)
         || ZombieShoveRules.inCone(0,-1,0,0,1,2.25,50)
         || ZombieShoveRules.inCone(2,0,0,0,1,2.25,50)
         || ZombieShoveRules.inCone(0,2.26,0,0,1,2.25,50)
         || ZombieShoveRules.inCone(0,1,1.26,0,1,2.25,50)) throw new AssertionError("Cone rules");
      if (!ZombieShoveRules.shouldKnockDown(.35,.34)
         || ZombieShoveRules.shouldKnockDown(.35,.35)
         || ZombieShoveRules.shouldKnockDown(0,0)) throw new AssertionError("Chance rules");
      Quaternionf conversion = new Quaternionf().rotationX((float) Math.PI);
      for (String direction : new String[] {"forward", "backward", "left", "right"}) {
         for (String bone : new String[] {"body", "head", "leftArm", "rightArm", "leftLeg", "rightLeg"}) {
            for (int step = 0; step <= 420; step++) {
               Vector3f authored = ZombieShoveAnimation.sample("animation.gore.shove_" + direction,
                  bone, "rotation", step / 100.0F).mul((float) (Math.PI / 180.0));
               Quaternionf reference = new Quaternionf().rotationZYX(authored.z, -authored.y, -authored.x);
               Quaternionf expected = new Quaternionf(conversion).mul(reference)
                  .mul(new Quaternionf(conversion).conjugate());
               Vector3f game = ZombieShoveAnimation.modelRotation(new Vector3f(authored)
                  .mul((float) (180.0 / Math.PI)));
               Quaternionf actual = new Quaternionf().rotationZYX(game.z, game.y, game.x);
               if (Math.abs(expected.dot(actual)) < .99999F) {
                  throw new AssertionError("Local coordinate mismatch " + direction + "/" + bone + "/" + step);
               }
            }
         }
      }
      System.out.println("PASS: "+samples+" directional joint samples, continuity, diagonal tilt, endpoints, cone and probability.");
      System.out.println("PASS: 10104 child-bone quaternion conversions across all four get-up directions.");
      float radians = (float) (Math.PI / 180.0);
      Vector3f base = new Vector3f(0, 0, 179 * radians);
      Vector3f target = new Vector3f(0, 0, -179 * radians);
      Vector3f middle = ZombieShoveAnimation.blendRotation(base, target, .5F);
      Quaternionf blended = new Quaternionf().rotationZYX(middle.z, middle.y, middle.x);
      if (Math.abs(blended.dot(new Quaternionf().rotationZ((float) Math.PI))) < .99999F) {
         throw new AssertionError("Blend crossed the long arc at +/-180 degrees");
      }
      if (!ZombieShoveAnimation.blendRotation(base, target, 0).equals(base)
         || !ZombieShoveAnimation.blendRotation(base, target, 1).equals(target)) {
         throw new AssertionError("Blend endpoints changed");
      }
      System.out.println("PASS: live-pose blend endpoints and shortest-arc rotation.");
   }
}
