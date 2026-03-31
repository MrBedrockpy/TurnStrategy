package ru.mrbedrockpy.level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.mrbedrockpy.level.entity.Entity;
import ru.mrbedrockpy.util.math.Vec2i;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LevelTile {

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 32;

    private final Vec2i pos;
    private final TileType type;
    private boolean inCastle = false;
    @Nullable private Entity entity;

}
