package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.level.LevelTile;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

public class ForestEntity extends Entity {

    private final int amount;
    private int heath = 3;

    public ForestEntity(Level level, Vec2i pos, int amount) {
        super(level, pos);
        this.amount = amount;
    }

    @Override
    public Texture getTexture() {
        return Textures.FOREST_ENTITY;
    }

    @Override
    public void onStep() {}

    @Override
    public void onClick() {
        LevelTile tile = this.level.getTile(this.pos);
        if (!tile.isInCastle()) return;
        this.heath--;
        if (this.heath > 0) return;
        this.level.getCastle().addWood(this.amount);
        tile.setEntity(null);
    }
}
