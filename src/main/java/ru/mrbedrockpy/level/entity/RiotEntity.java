package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

public class RiotEntity extends Hero {

    public RiotEntity(Level level, Vec2i pos, CastleEntity castle) {
        super(level, pos, castle);
    }

    @Override
    public Texture getTexture() {
        return Textures.RIOT_ENTITY;
    }

}
