package ru.mrbedrockpy.level.entity;

import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends Entity {

    protected final CastleEntity castle;
    private final List<MovementVariantEntity> movementVariants = new ArrayList<>();

    public Hero(Level level, Vec2i pos, CastleEntity castle) {
        super(level, pos);
        this.castle = castle;
        this.captureTerritory();
    }

    public void captureTerritory() {
        getLevel().getNeighbours(this.pos, tile -> !tile.isInCastle()).forEach(tile -> tile.setInCastle(true));
    }

    @Override
    public void onStep() {
        if (!this.movementVariants.isEmpty()) this.removeMovementVariants();
    }

    @Override
    public void onClick() {
        if (this.movementVariants.isEmpty()) {
            getLevel().getNeighbours(this.pos, tile -> tile.getEntity() == null).forEach(tile -> {
                MovementVariantEntity variant = new MovementVariantEntity(this.level, tile.getPos(), this);
                movementVariants.add(variant);
                tile.setEntity(variant);
            });
        } else this.removeMovementVariants();
    }

    @Override
    public void unselect() {
        if (!this.movementVariants.isEmpty()) this.removeMovementVariants();
    }

    public void movement(MovementVariantEntity movementVariantEntity) {
        this.removeMovementVariants();
        this.level.getTile(this.pos).setEntity(null);
        this.pos = movementVariantEntity.getPos();
        this.level.getTile(this.pos).setEntity(this);
        this.captureTerritory();
    }

    private void removeMovementVariants() {
        this.movementVariants.forEach(variant -> level.getTile(variant.getPos()).setEntity(null));
        this.movementVariants.clear();
    }
}
