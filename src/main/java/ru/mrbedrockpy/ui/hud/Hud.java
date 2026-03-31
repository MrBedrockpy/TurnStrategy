package ru.mrbedrockpy.ui.hud;

import com.raylib.Texture;
import lombok.Getter;
import ru.mrbedrockpy.ui.Widget;
import ru.mrbedrockpy.util.Rectangle;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Hud implements Rectangle {

    @Getter
    private final List<Widget> widgets = new ArrayList<>();

    public final void add(Widget widget) {
        widgets.add(widget);
    }

    public abstract Texture getTexture();

    public abstract void onClick(Vec2i mouse);
}
