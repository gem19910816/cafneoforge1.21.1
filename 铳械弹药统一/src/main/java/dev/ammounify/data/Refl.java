package dev.ammounify.data;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 极小反射工具。
 *
 * <p>为什么需要它：TaCZ 的数据类（{@code GunData} / {@code BulletData} / {@code ExtraDamage}）
 * 只提供 getter，字段全是 private 且没有 setter，也没有拷贝构造器。
 * 想在"不修改 TaCZ 本体、不覆盖别人枪包文件"的前提下派生一份改过的数据，
 * 就只有反射这一条路。所有 {@link Field} 都会被缓存，热路径上只是一次数组遍历。</p>
 */
public final class Refl {

    private static final Map<Class<?>, Field[]> INSTANCE_FIELDS = new ConcurrentHashMap<>();
    private static final Map<String, Field> NAMED = new ConcurrentHashMap<>();

    private Refl() {
    }

    /** 取某类的全部实例字段（已 setAccessible），结果被缓存。 */
    public static Field[] instanceFields(Class<?> type) {
        return INSTANCE_FIELDS.computeIfAbsent(type, t -> {
            Field[] all = t.getDeclaredFields();
            Field[] out = new Field[all.length];
            int n = 0;
            for (Field f : all) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                f.setAccessible(true);
                out[n++] = f;
            }
            if (n == all.length) {
                return out;
            }
            Field[] trimmed = new Field[n];
            System.arraycopy(out, 0, trimmed, 0, n);
            return trimmed;
        });
    }

    /** 按名字取字段（已 setAccessible），找不到返回 null。 */
    @Nullable
    public static Field field(Class<?> type, String name) {
        String key = type.getName() + '#' + name;
        Field cached = NAMED.get(key);
        if (cached != null) {
            return cached;
        }
        try {
            Field f = type.getDeclaredField(name);
            f.setAccessible(true);
            NAMED.put(key, f);
            return f;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /** 读字段，失败返回 fallback。 */
    @Nullable
    public static Object get(Class<?> type, String name, Object target) {
        Field f = field(type, name);
        if (f == null) {
            return null;
        }
        try {
            return f.get(target);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /** 写字段，返回是否成功。 */
    public static boolean set(Class<?> type, String name, Object target, Object value) {
        Field f = field(type, name);
        if (f == null) {
            return false;
        }
        try {
            f.set(target, value);
            return true;
        } catch (IllegalAccessException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 浅拷贝：新建一个同类型实例，把所有实例字段原样搬过去。
     *
     * <p>刻意做浅拷贝——嵌套对象（配件表、后坐力曲线、脚本参数等）共享引用即可，
     * 我们只改顶层那几个标量字段，共享引用既省内存又不会破坏 TaCZ 的数据校验不变量。</p>
     *
     * @return 拷贝；任何一步失败返回 null
     */
    @Nullable
    public static <T> T shallowCopy(T source, Class<T> type) {
        if (source == null) {
            return null;
        }
        try {
            var ctor = type.getDeclaredConstructor();
            ctor.setAccessible(true);
            T copy = ctor.newInstance();
            for (Field f : instanceFields(type)) {
                f.set(copy, f.get(source));
            }
            return copy;
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}
