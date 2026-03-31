package ru.mrbedrockpy;

import com.raylib.Raylib;
import lombok.Getter;
import lombok.Setter;
import ru.mrbedrockpy.ui.hud.Hud;
import ru.mrbedrockpy.ui.hud.HudManager;
import ru.mrbedrockpy.ui.screen.Screen;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.level.PlayerController;
import ru.mrbedrockpy.level.generator.PerlinLevelGenerator;
import ru.mrbedrockpy.render.Renderer;
import ru.mrbedrockpy.render.Window;
import ru.mrbedrockpy.util.LoggerStream;
import ru.mrbedrockpy.util.TextureUtil;
import ru.mrbedrockpy.util.math.Vec2i;

import java.io.PrintStream;
import java.util.List;

@Getter
public class Game {

    public static final Game INSTANCE = new Game();

    private static final LoggerStream LOGGER_STREAM = new LoggerStream(10);

    private PlayerController playerController;
    private HudManager hudManager;
    @Setter private Screen screen;
    private Level level;

    @Setter private boolean debugMode = false;

    private void run() {
        this.init();
        while (Window.isRunning()) {
            this.logic();
            this.render();
        }
        this.terminate();
    }

    private void init() {
        Window.init();
        this.level = new Level(40, new PerlinLevelGenerator());
        this.playerController = new PlayerController();
        this.hudManager = new HudManager();
    }

    private void logic() {
        if (Window.keyPressed(Raylib.KeyboardKey.KEY_F3)) debugMode = !debugMode;
        if (this.screen == null) {
            Vec2i mouse = Window.mousePos();
            Hud hud = this.hudManager.getHudUnderMouse(mouse);
            if (hud != null) hud.onClick(mouse);
            else this.playerController.handleInput();
        }
        else {
            if (Window.mouseDown(Raylib.MouseButton.MOUSE_BUTTON_LEFT)) this.screen.onClick(Window.mousePos());
            if (Window.keyDown(Raylib.KeyboardKey.KEY_ESCAPE)) this.screen = null;
        }
    }

    private void render() {
        Renderer.beginRender();
        Renderer.renderBackground();
        Renderer.begin2D();
        Renderer.renderLevel(level);
        Renderer.end2D();
        this.hudManager.render();
        if (this.screen != null) this.screen.render();
        if (this.debugMode) {
            Renderer.renderText("Camera X: " + Window.getCamera().offset().x(), 0, 0);
            Renderer.renderText("Camera Y: " + Window.getCamera().offset().y(), 0, 20);
            Renderer.renderText("Camera Zoom: " + Window.getCamera().zoom(), 0, 40);
            Renderer.renderText("FPS: " + Window.getFPS(), 0, 60);
            for (int i = 0; i < 5; i++) {
                List<String> log = LOGGER_STREAM.getLastLines();
                try {
                    Renderer.renderText(log.get(log.size() - 1 - i), 0, 100 + i * 20);
                } catch (IndexOutOfBoundsException _) {}
            }
        }
        Renderer.endRender();
    }

    private void terminate() {
        TextureUtil.unload();
        Window.close();
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(LOGGER_STREAM, true));
        INSTANCE.run();
    }
}