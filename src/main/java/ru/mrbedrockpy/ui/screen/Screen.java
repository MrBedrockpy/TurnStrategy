package ru.mrbedrockpy.ui.screen;

import ru.mrbedrockpy.ui.Widget;
import ru.mrbedrockpy.util.Rectangle;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Screen {

    private final List<Widget> widgets = new ArrayList<>();

    public final void add(Widget... widgets) {
        this.widgets.addAll(Arrays.asList(widgets));
    }

    public final void render() {
        widgets.forEach(Widget::render);
    }

    public final void onClick(Vec2i mousePos) {
        widgets.stream().filter(widget -> Rectangle.mouseInside(widget, mousePos))
                .findFirst().ifPresent(Widget::onClick);
    }
}
