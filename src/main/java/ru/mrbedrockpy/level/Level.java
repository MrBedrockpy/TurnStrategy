package ru.mrbedrockpy.level;

import lombok.Getter;
import lombok.Setter;
import ru.mrbedrockpy.level.entity.CastleEntity;
import ru.mrbedrockpy.level.entity.Entity;
import ru.mrbedrockpy.level.generator.ILevelGenerator;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Level {

    private final LevelTile[][] tiles;
    @Getter private final int size;
    @Getter @Setter private CastleEntity castle;
    @Getter @Setter private LevelTile selectedTile = null;

    public Level(int size, ILevelGenerator generator) {
        this.tiles = new LevelTile[size][size];
        this.size = size;
        generator.generateLevel(this);
    }

    public void step() {
        for (LevelTile[] tiles : this.tiles)
            for (LevelTile tile : tiles)
                if (tile.getEntity() != null) tile.getEntity().onStep();
    }

    public LevelTile getTile(Vec2i pos) {
        return this.getTile(pos.getX(), pos.getY());
    }

    public LevelTile getTile(int x, int y) {
        try { return tiles[x][y]; } catch (IndexOutOfBoundsException _) { return null; }
    }

    public void setTile(Vec2i pos, LevelTile tile) {
        this.setTile(pos.getX(), pos.getY(), tile);
    }

    public void setTile(int x, int y, LevelTile tile) {
        try { tiles[x][y] = tile; } catch (IndexOutOfBoundsException _) {}
    }

    public Stream<LevelTile> getNeighbours(Vec2i pos, Predicate<LevelTile> filter) {
        return Stream.of(
                new Vec2i(-1, -1), new Vec2i(0, -1), new Vec2i(1, -1),
                new Vec2i(-1, 0), new Vec2i(1, 0),
                new Vec2i(-1, 1), new Vec2i(0, 1), new Vec2i(1, 1)
        ).map(neighbour -> this.getTile(pos.add(neighbour))).filter(Objects::nonNull).filter(filter);
    }
}
