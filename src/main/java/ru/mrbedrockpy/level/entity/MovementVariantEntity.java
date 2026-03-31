package ru.mrbedrockpy.level.entity;

import com.raylib.Texture;
import lombok.Getter;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

@Getter
public class MovementVariantEntity extends Entity {

    private final Hero hero;

    public MovementVariantEntity(Level level, Vec2i pos, Hero hero) {
        super(level, pos);
        this.hero = hero;
    }

    @Override
    public Texture getTexture() {
        return Textures.MOVEMENT_VARIANT_ENTITY;
    }

    @Override
    public void onStep() {

    }

    @Override
    public void unselect() {

    }

    @Override
    public void onClick() {
        hero.movement(this);
    }
}
