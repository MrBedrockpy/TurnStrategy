package ru.mrbedrockpy.render;

import com.raylib.Camera2D;
import com.raylib.Raylib;
import com.raylib.Vector2;
import lombok.Getter;
import ru.mrbedrockpy.util.math.Vec2i;

import static com.raylib.Raylib.*;
import static com.raylib.Raylib.getScreenWidth;

public class Window {

    @Getter private static Camera2D camera;

    public static void init() {
        int monitor = getCurrentMonitor();
        setConfigFlags(ConfigFlags.FLAG_FULLSCREEN_MODE);
        initWindow(getMonitorWidth(monitor), getMonitorHeight(monitor), "RTS test");
        setExitKey(-1);
        Window.camera = new Camera2D(new Vector2(getScreenWidth() / 2f, getScreenHeight() / 2f), new Vec2i(0, 0).rl(), 0, 3f);
    }

    public static void close() {
        closeWindow();
    }

    public static boolean keyPressed(int key) {
        return isKeyPressed(key);
    }
    public static boolean keyDown(int key) {
        return isKeyDown(key);
    }
    public static boolean mouseDown(int button) {
        return isMouseButtonDown(button);
    }
    public static Vec2i mousePos() {
        return new Vec2i(getMouseX(), getMouseY());
    }
    public static float mouseScroll() {
        return Raylib.getMouseWheelMove();
    }

    public static boolean isRunning() {
        return !windowShouldClose();
    }

    public static int getFPS() {
        return Raylib.getFPS();
    }
}
