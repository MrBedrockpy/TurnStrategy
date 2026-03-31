package ru.mrbedrockpy.ui;

import ru.mrbedrockpy.util.Rectangle;

public interface Widget extends Rectangle {

    void render();
    void onClick();

}
