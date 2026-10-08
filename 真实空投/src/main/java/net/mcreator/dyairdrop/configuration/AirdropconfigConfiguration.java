package net.mcreator.dyairdrop.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class AirdropconfigConfiguration {
   public static final Builder BUILDER = new Builder();
   public static final ModConfigSpec SPEC;

   public static final ConfigValue<Boolean> ENABLEAIRDROPEVENTS;
   public static final ConfigValue<Double> GAP;
   public static final ConfigValue<Double> MAXLEVEL;
   public static final ConfigValue<Boolean> ENABLELOCK;
   public static final ConfigValue<String> DRIFT;
   public static final ConfigValue<String> AVAILABLEWORLD;
   public static final ConfigValue<Double> ATTEMPTPUNISHMENT;
   public static final ConfigValue<Boolean> ENABLEENEMIES;
   public static final ConfigValue<Double> ENEMYARRIVETIME;
   public static final ConfigValue<Double> AIRDROPSTOLENTIME;
   public static final ConfigValue<Double> DISTANCE;
   public static final ConfigValue<String> ENEMYLIST;
   public static final ConfigValue<Boolean> FORCELOAD;
   public static final ConfigValue<Double> STARTPOSITION;
   public static final ConfigValue<Double> HEIGHT;
   public static final ConfigValue<Boolean> INCOMPLETE_BLOCK_DESTRUCTION;
   public static final ConfigValue<Double> WEAPONAIRDROPWEIGHT;
   public static final ConfigValue<Double> MEDICALAIRDROPWEIGHT;
   public static final ConfigValue<Double> SMALLAIRDROPWEIGHT;
   public static final ConfigValue<Boolean> DEBUGMODE;
   public static final ConfigValue<Boolean> ENABLEGLOBALCOORDINATES;

   public AirdropconfigConfiguration() {
   }

   static {
      BUILDER.push("worldevents");
      ENABLEAIRDROPEVENTS = BUILDER.comment("是否启用全局空投事件").define("enable", true);
      GAP = BUILDER.comment("每次空投的时间间隔，以天为单位").define("gap", 10.0);
      MAXLEVEL = BUILDER.comment("空投的最大等级（需要和对应空投文件的数量一致）").define("maxlevel", 5.0);
      ENABLELOCK = BUILDER.comment("全局空投是否需要密码解锁？").define("enablelock", false);
      DRIFT = BUILDER.comment("空投预计位置距离玩家的距离范围").define("drift", "50,100");
      AVAILABLEWORLD = BUILDER.comment("可以触发空投的世界").define("availableworld", "minecraft:overworld");
      ATTEMPTPUNISHMENT = BUILDER.comment("当输入密码错误时，受到的魔法伤害").define("attemptpunishment", 2.0);
      BUILDER.pop();
      BUILDER.push("chestevents");
      ENABLEENEMIES = BUILDER.comment("空投是否会吸引敌人？").define("enableenemies", true);
      ENEMYARRIVETIME = BUILDER.comment("敌人将会多快（分钟）找到空投？").define("enemyarrivetime", 1.0);
      AIRDROPSTOLENTIME = BUILDER.comment("空投将多快（分钟）被窃取？").define("airdropstolentime", 5.0);
      DISTANCE = BUILDER.comment("玩家至少距离空投多远时，空投会被窃取").define("distance", 50.0);
      ENEMYLIST = BUILDER.define("enemylist", "minecraft:pillager,minecraft:zombie,minecraft:husk");
      BUILDER.pop();
      BUILDER.push("Performance");
      FORCELOAD = BUILDER.comment("是否强制加载空投路径区域？此法可以允许玩家在未加载的区块召唤空投，但是可能会破坏已有的常加载区域，服务器慎用。").define("forceload", true);
      STARTPOSITION = BUILDER.comment("飞机起始飞行距离空投预计位置的距离，如果未开启强制加载，不允许超过（16*加载距离）。该参数范围[0，270}")
         .define("startposition", 262.0);
      HEIGHT = BUILDER.comment("飞机飞行的高度。范围[100,320]").define("height", 200.0);
      INCOMPLETE_BLOCK_DESTRUCTION = BUILDER.comment("下落的空投是否会破坏不完整方块").define("incomplete_block_destruction", false);
      BUILDER.pop();
      BUILDER.push("FlaregunEvents");
      WEAPONAIRDROPWEIGHT = BUILDER.comment("信号枪召唤武器空投的权重").define("WeaponairdropWeight", 4.0);
      MEDICALAIRDROPWEIGHT = BUILDER.comment("信号枪召唤医疗空投的权重").define("MedicalairdropWeight", 4.0);
      SMALLAIRDROPWEIGHT = BUILDER.comment("信号枪召唤小型空投的权重").define("SmallairdropWeight", 2.0);
      BUILDER.pop();
      BUILDER.push("debug");
      DEBUGMODE = BUILDER.define("debugmode", false);
      BUILDER.pop();
      BUILDER.push("mapcompat");
      ENABLEGLOBALCOORDINATES = BUILDER.comment("全局空投是否允许放置坐标").define("enableglobalcoordinates", true);
      BUILDER.pop();
      SPEC = BUILDER.build();
   }
}
