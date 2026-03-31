package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.util.math.Vec2i;

@Getter
@Setter
@AllArgsConstructor
public abstract class Entity {

    protected Level level;
    protected Vec2i pos;

    public abstract Texture getTexture();
    public abstract void onStep();
    public abstract void onClick();

}
