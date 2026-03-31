package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

@Getter
@AllArgsConstructor
public enum CastleBorder {

    LEFT_BOTTOM(0, 1, Textures.LEFT_BOTTOM_CASTLE_BORDER),
    LEFT_TOP(-1, 0, Textures.LEFT_TOP_CASTLE_BORDER),
    RIGHT_BOTTOM(1, 0, Textures.RIGHT_BOTTOM_CASTLE_BORDER),
    RIGHT_TOP(0, -1, Textures.RIGHT_TOP_CASTLE_BORDER),
    ;

    private final int offsetX;
    private final int offsetY;
    private final Texture texture;

    public Vec2i getPos() {
        return new Vec2i(offsetX, offsetY);
    }

}
