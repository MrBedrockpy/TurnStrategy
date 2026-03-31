package ru.mrbedrockpy.util;

import ru.mrbedrockpy.util.math.Vec2i;

public interface Rectangle {

    float getScale();
    Vec2i getPos();
    Vec2i getSize();

    static boolean mouseInside(Rectangle rectangle, Vec2i mousePos) {
        return rectangle.getPos().getX() <= mousePos.getX() && rectangle.getPos().getY() <= mousePos.getY() &&
                rectangle.getPos().getX() + rectangle.getSize().getX() * rectangle.getScale() >= mousePos.getX() &&
                rectangle.getPos().getY() + rectangle.getSize().getY() * rectangle.getScale() >= mousePos.getY();
    }

}
