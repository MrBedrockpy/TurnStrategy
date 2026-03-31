package ru.mrbedrockpy.ui.hud;

import com.raylib.Texture;
import ru.mrbedrockpy.render.Textures;
import ru.mrbedrockpy.util.math.Vec2i;

import static com.raylib.Raylib.getRenderHeight;

public class CastleHud extends Hud {

    private static final Texture TEXTURE = Textures.CASTLE_HUD;
    private static final float SCALE = 4f;

    public CastleHud() {
        add(new ResourceWidget(getPos().add(15, 15), new Vec2i(), 1));
    }

    @Override
    public float getScale() {
        return SCALE;
    }

    @Override
    public Vec2i getPos() {
        return new Vec2i(10, getRenderHeight() - Math.round(Textures.CASTLE_HUD.getHeight() * SCALE) - 10);
    }

    @Override
    public Vec2i getSize() {
        return new Vec2i(TEXTURE.getWidth(), TEXTURE.getHeight());
    }

    @Override
    public Texture getTexture() {
        return TEXTURE;
    }

    @Override
    public void onClick(Vec2i mouse) {

    }
}
