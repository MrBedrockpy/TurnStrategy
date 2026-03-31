package ru.mrbedrockpy.level;

import com.raylib.Texture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.Resource;
import ru.mrbedrockpy.util.TextureUtil;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum TileType {

    PLAINS(Textures.PLAINS_TILE),
    WATER(Textures.WATER_TILE),
    ROCKS(Textures.ROCKS_TILE),
    ;

    private final Texture texture;

    public static TileType getRandomTile(Random random) {
        return values()[random.nextInt(values().length)];
    }
}
