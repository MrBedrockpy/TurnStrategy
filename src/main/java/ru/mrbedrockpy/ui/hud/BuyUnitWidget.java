package ru.mrbedrockpy.ui.hud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mrbedrockpy.level.entity.CastleEntity;
import ru.mrbedrockpy.level.entity.Entity;
import ru.mrbedrockpy.ui.Widget;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class BuyUnitWidget implements Widget {

    private final CastleEntity castle;
    private final Vec2i pos;
    private final Vec2i size;
    private final float scale;
    private final Supplier<Entity> supplier;

    @Override
    public void render() {

    }

    @Override
    public void onClick() {

    }
}
