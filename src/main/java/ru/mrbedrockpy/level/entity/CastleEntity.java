package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import lombok.Getter;
import ru.mrbedrockpy.level.LevelTile;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.level.TileType;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Getter
public class CastleEntity extends Entity {

    private final List<Vec2i> territory;
    private int wood = 0;

    public CastleEntity(Level level, Vec2i pos, List<Vec2i> territory) {
        super(level, pos);
        this.territory = territory;
    }

    @Override
    public Texture getTexture() {
        return Textures.CASTLE_ENTITY;
    }

    @Override
    public void onStep() {

    }

    @Override
    public void onClick() {
        if (this.wood < 2) return;
        this.wood -= 2;
        this.level.getNeighbours(this.pos, t -> t.getEntity() == null && !t.getType().equals(TileType.WATER)).findFirst()
                .ifPresent(t -> t.setEntity(new RiotEntity(this.level, t.getPos())));
    }

    public void addWood(int wood) {
        this.wood += wood;
    }
    public void addTerritory(Vec2i pos) {
        this.territory.add(pos);
    }
}
