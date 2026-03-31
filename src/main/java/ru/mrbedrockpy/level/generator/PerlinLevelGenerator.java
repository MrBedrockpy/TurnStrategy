package ru.mrbedrockpy.level.generator;

import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.level.LevelTile;
import ru.mrbedrockpy.level.TileType;
import ru.mrbedrockpy.level.entity.CastleEntity;
import ru.mrbedrockpy.level.entity.ForestEntity;
import ru.mrbedrockpy.util.math.PerlinNoise;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.List;
import java.util.Random;

public class PerlinLevelGenerator implements ILevelGenerator {

    private final PerlinNoise noise = new PerlinNoise();
    private final Random random = new Random();

    @Override
    public void generateLevel(Level level) {
        boolean hasCastle = false;
        double scale = 5;
        int castleX = 0, castleY = 0;
        for (int x = 0; x < level.getSize(); x++) {
            for (int y = 0; y < level.getSize(); y++) {
                double n = noise.noise(x * scale, y * scale);
                n = (n + 1) / 2.0;
                TileType type;
                if (n < 0.4) type = TileType.WATER;
                else if (n < 0.7) type = TileType.PLAINS;
                else type = TileType.ROCKS;
                LevelTile tile = new LevelTile(new Vec2i(x, y), type);
                if (type == TileType.PLAINS) {
                    if (!hasCastle && random.nextDouble() < 0.05) {
                        CastleEntity castle = new CastleEntity(level, tile.getPos(), List.of(
                                new Vec2i(x-1, y-1), new Vec2i(x, y-1), new Vec2i(x+1, y-1),
                                new Vec2i(x-1, y), new Vec2i(x, y), new Vec2i(x+1, y),
                                new Vec2i(x-1, y+1), new Vec2i(x, y+1), new Vec2i(x+1, y+1)
                        ));
                        tile.setEntity(castle);
                        level.setCastle(castle);
                        hasCastle = true;
                        castleX = x;
                        castleY = y;
                    } else if (random.nextDouble() < 0.3) tile.setEntity(new ForestEntity(
                            level, tile.getPos(), random.nextInt(1, 5)));
                }
                level.setTile(x, y, tile);
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                LevelTile tile = level.getTile(castleX + x, castleY + y);
                if (tile != null) tile.setInCastle(true);
            }
        }
    }
}