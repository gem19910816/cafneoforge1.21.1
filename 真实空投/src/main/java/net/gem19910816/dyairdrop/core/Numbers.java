package net.gem19910816.dyairdrop.core;

/**
 * 数值解析工具：取代 MCreator 生成的 {@code convert} 匿名类
 * （{@code try { return Double.parseDouble(s.trim()); } catch (Exception e) { return 0.0; }}）。
 */
public final class Numbers {

    private Numbers() {
    }

    /** 解析失败返回 {@code 0.0}（与原实现一致）。 */
    public static double parseDouble(String text) {
        if (text == null) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
