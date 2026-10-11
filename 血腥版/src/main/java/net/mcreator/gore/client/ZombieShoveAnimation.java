package net.mcreator.gore.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import net.mcreator.gore.procedures.ZombiePushProcedure;
import org.joml.Vector3f;
import org.joml.Quaternionf;

/** Reads the same authored keyframes exported to the Blockbench project. */
public final class ZombieShoveAnimation {
   public static final String DOWNED = "animation.gore.shove_forward";
   public static final String STAGGER = "animation.gore.stagger_forward";
   private static final String[] DIRECTIONS = {"forward", "left", "backward", "right"};
   private static final Map<String, Map<String, Track>> CLIPS = load();

   private record Track(float[] times, float[][] values) {
      Vector3f sample(float seconds) {
         if (seconds <= times[0]) return new Vector3f(values[0]);
         for (int i = 0; i < times.length - 1; i++) {
            if (seconds <= times[i + 1]) {
               float h = times[i + 1] - times[i];
               float u = (seconds - times[i]) / h;
               Vector3f value = new Vector3f();
               for (int axis = 0; axis < 3; axis++) {
                  float interpolated = values[i][axis] + u * (values[i + 1][axis] - values[i][axis]);
                  value.setComponent(axis, interpolated);
               }
               return value;
            }
         }
         return new Vector3f(values[values.length - 1]);
      }

   }

   private ZombieShoveAnimation() {
   }

   public static Vector3f sample(String clip, String bone, String channel, float seconds) {
      Track track = CLIPS.getOrDefault(clip, Map.of()).get(bone + "/" + channel);
      return track == null ? new Vector3f() : track.sample(seconds);
   }

   /** Local 0 is forward, +90 is left. Adjacent poses blend through every diagonal. */
   public static Vector3f directional(boolean downed, float direction, String bone, String channel, float seconds) {
      float sector = ((direction % 360.0F + 360.0F) % 360.0F) / 90.0F;
      int first = (int) Math.floor(sector);
      float blend = sector - first;
      String prefix = downed ? "animation.gore.shove_" : "animation.gore.stagger_";
      Vector3f a = sample(prefix + DIRECTIONS[first], bone, channel, seconds);
      Vector3f b = sample(prefix + DIRECTIONS[(first + 1) % 4], bone, channel, seconds);
      Vector3f result = new Vector3f(a).lerp(b, blend);
      if (bone.equals("root") && channel.equals("rotation")) {
         // A diagonal must still lie flat, rather than stop at a 64 degree tilt.
         float angle = (float) Math.hypot(a.x, a.z) * (1.0F - blend)
            + (float) Math.hypot(b.x, b.z) * blend;
         float actual = (float) Math.hypot(result.x, result.z);
         if (actual > 0.0001F) {
            result.x *= angle / actual;
            result.z *= angle / actual;
         }
      }
      return result;
   }

   public static float angle(float elapsed) {
      return -sample(DOWNED, "root", "rotation", elapsed / 20.0F).x;
   }

   public static Vector3f modelRotation(Vector3f frame) {
      float radians = (float) (Math.PI / 180.0);
      return new Vector3f(-frame.x, frame.y, -frame.z).mul(radians);
   }

   public static Vector3f blendRotation(Vector3f base, Vector3f authored, float weight) {
      if (weight <= 0.0F) return new Vector3f(base);
      if (weight >= 1.0F) return new Vector3f(authored);
      return new Quaternionf().rotationZYX(base.z, base.y, base.x)
         .slerp(new Quaternionf().rotationZYX(authored.z, authored.y, authored.x), weight)
         .getEulerAnglesZYX(new Vector3f());
   }

   public static float ease(float value) {
      float t = Math.max(0.0F, Math.min(1.0F, value));
      return t * t * t * (t * (6.0F * t - 15.0F) + 10.0F);
   }

   public static float weight(float elapsed, float duration) {
      return ease(elapsed / 3.0F) * (1.0F - ease((elapsed - duration + 6.0F) / 6.0F));
   }

   public static float staggerAngle(float elapsed, float duration) {
      return -sample(STAGGER, "root", "rotation", elapsed / duration).x;
   }

   public static float rise(float elapsed) {
      return Math.max(0.0F, Math.min(1.0F,
         (elapsed - (ZombiePushProcedure.DOWNED_TOTAL_TICKS - ZombiePushProcedure.GET_UP_TICKS))
            / ZombiePushProcedure.GET_UP_TICKS));
   }

   public static float pulse(float phase) {
      if (phase <= 0.0F || phase >= 1.0F) return 0.0F;
      float value = (float)Math.sin(phase * Math.PI);
      return value * value;
   }

   private static Map<String, Map<String, Track>> load() {
      String path = "/assets/gore_edition/animations/zombie_shove.animation.json";
      try (InputStream stream = ZombieShoveAnimation.class.getResourceAsStream(path)) {
         if (stream == null) throw new IllegalStateException("Missing shove animation");
         JsonObject animations = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
            .getAsJsonObject().getAsJsonObject("animations");
         Map<String, Map<String, Track>> result = new HashMap<>();
         for (Map.Entry<String, JsonElement> clip : animations.entrySet()) {
            Map<String, Track> tracks = new HashMap<>();
            for (Map.Entry<String, JsonElement> bone : clip.getValue().getAsJsonObject().getAsJsonObject("bones").entrySet()) {
               for (Map.Entry<String, JsonElement> channel : bone.getValue().getAsJsonObject().entrySet()) {
                  TreeMap<Float, float[]> frames = new TreeMap<>();
                  for (Map.Entry<String, JsonElement> frame : channel.getValue().getAsJsonObject().entrySet()) {
                     float[] vector = new float[3];
                     for (int i = 0; i < 3; i++) vector[i] = frame.getValue().getAsJsonArray().get(i).getAsFloat();
                     frames.put(Float.parseFloat(frame.getKey()), vector);
                  }
                  float[] times = new float[frames.size()];
                  float[][] values = new float[frames.size()][];
                  int index = 0;
                  for (Map.Entry<Float, float[]> frame : frames.entrySet()) {
                     times[index] = frame.getKey();
                     values[index++] = frame.getValue();
                  }
                  tracks.put(bone.getKey() + "/" + channel.getKey(), new Track(times, values));
               }
            }
            result.put(clip.getKey(), tracks);
         }
         float length = animations.getAsJsonObject(DOWNED).get("animation_length").getAsFloat() * 20.0F;
         if (Math.abs(length - ZombiePushProcedure.DOWNED_TOTAL_TICKS) > 0.01F) {
            throw new IllegalStateException("Shove timeline and server duration differ");
         }
         return result;
      } catch (Exception exception) {
         throw new IllegalStateException("Cannot load authored shove keyframes", exception);
      }
   }
}
