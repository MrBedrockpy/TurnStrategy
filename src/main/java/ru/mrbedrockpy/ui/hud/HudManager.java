package ru.mrbedrockpy.ui.hud;

import ru.mrbedrockpy.Game;
import ru.mrbedrockpy.render.Renderer;
import ru.mrbedrockpy.util.Rectangle;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    private final List<Hud> huds = new ArrayList<>();

    public HudManager() {
        huds.add(new CastleHud());
    }

    public Hud getHudUnderMouse(Vec2i pos) {
        return huds.stream().filter(hud -> Rectangle.mouseInside(hud, pos))
                .findFirst().orElse(null);
    }

    public void render() {
        huds.forEach(Renderer::renderHud);
        if (Game.INSTANCE.isDebugMode()) huds.forEach(Renderer::renderBox);
    }
}
