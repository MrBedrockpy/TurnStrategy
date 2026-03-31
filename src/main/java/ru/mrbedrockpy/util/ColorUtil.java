package ru.mrbedrockpy.util;

import com.raylib.Color;

public class ColorUtil {

    public static final Color WHITE = of(255, 255, 255);

    public static Color of(int r, int g, int b) {
        return of(r, g, b, 255);
    }

    public static Color of(int r, int g, int b, int a) {
        return new Color((byte) r, (byte) g, (byte) b, (byte) a);
    }

}
