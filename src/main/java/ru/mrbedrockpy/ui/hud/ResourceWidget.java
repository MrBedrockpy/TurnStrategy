package ru.mrbedrockpy.ui.hud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.mrbedrockpy.Game;
import ru.mrbedrockpy.render.Renderer;
import ru.mrbedrockpy.ui.Widget;
import ru.mrbedrockpy.util.math.Vec2i;

@Getter
@RequiredArgsConstructor
public class ResourceWidget implements Widget {

    private final Vec2i pos;
    private final Vec2i size;
    private final float scale;

    private int t = 0;

    @Override
    public void render() {
        Renderer.renderText("Wood: " + Game.INSTANCE.getLevel().getCastle().getWood(), pos.getX(), pos.getY());
    }

    @Override
    public void onClick() {

    }
}
