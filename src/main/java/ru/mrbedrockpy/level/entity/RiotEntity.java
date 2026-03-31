package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

public class RiotEntity extends Entity {

    public RiotEntity(Level level, Vec2i pos) {
        super(level, pos);
    }

    @Override
    public Texture getTexture() {
        return Textures.RIOT_ENTITY;
    }

    @Override
    public void onStep() {

    }

    @Override
    public void onClick() {

    }
}
