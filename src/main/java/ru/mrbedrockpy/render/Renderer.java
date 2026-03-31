package ru.mrbedrockpy.render;

import ru.mrbedrockpy.Game;
import ru.mrbedrockpy.level.Level;
import ru.mrbedrockpy.level.LevelTile;
import ru.mrbedrockpy.level.entity.CastleBorder;
import ru.mrbedrockpy.level.entity.Entity;
import ru.mrbedrockpy.ui.Widget;
import ru.mrbedrockpy.ui.hud.Hud;
import ru.mrbedrockpy.util.ColorUtil;
import ru.mrbedrockpy.util.Rectangle;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.ArrayList;
import java.util.List;

import static com.raylib.Raylib.*;

public class Renderer {

    public static void beginRender() {
        beginDrawing();
    }
    public static void begin2D() {
        beginMode2D(Window.getCamera());
    }
    public static void endRender() {
        endDrawing();
    }
    public static void end2D() {
        endMode2D();
    }

    public static void renderBackground() {
        clearBackground(ColorUtil.of(20, 20, 20));
    }

    public static void renderLevel(Level level) {
        renderLayer(level, (tile, _, graphicPos) -> renderTile(tile, graphicPos));
        renderLayer(level, (tile, indexPos, graphicPos) -> {
            List<CastleBorder> tileBorders = new ArrayList<>();
            if (tile.isInCastle()) {
                for (CastleBorder border : CastleBorder.values()) {
                    LevelTile neighbour = level.getTile(indexPos.add(border.getPos()));
                    if (neighbour == null) tileBorders.add(border);
                    else if (!neighbour.isInCastle()) tileBorders.add(border);
                }
            }
            renderCastleBorders(graphicPos, tileBorders);
        });
        renderLayer(level, (tile, indexPos, graphicPos) -> {
            if (tile.getEntity() != null) renderEntity(graphicPos, tile.getEntity());
            if (Game.INSTANCE.isDebugMode()) renderText(indexPos.toString(), graphicPos.getX() + 16, graphicPos.getY());
        });
    }

    public static void renderHud(Hud hud) {
        drawTextureEx(hud.getTexture(), hud.getPos().rl(), 0f, hud.getScale(), ColorUtil.WHITE);
        hud.getWidgets().forEach(Widget::render);
    }

    public static void renderText(String text, int x, int y) {
        drawText(text, x, y, 16, ColorUtil.WHITE);
    }

    public static void renderBox(Rectangle rectangle) {
        renderLine(rectangle.getPos(), rectangle.getPos().addX(Math.round(rectangle.getSize().getX() * rectangle.getScale())));
        renderLine(rectangle.getPos(), rectangle.getPos().addY(Math.round(rectangle.getSize().getY() * rectangle.getScale())));
        renderLine(rectangle.getPos().addX(Math.round(rectangle.getSize().getX() * rectangle.getScale())), rectangle.getPos().add(rectangle.getSize().mul(rectangle.getScale())));
        renderLine(rectangle.getPos().addY(Math.round(rectangle.getSize().getY() * rectangle.getScale())), rectangle.getPos().add(rectangle.getSize().mul(rectangle.getScale())));
    }

    private static void renderLine(Vec2i start, Vec2i end) {
        drawLine(start.getX(), start.getY(), end.getX(), end.getY(), ColorUtil.WHITE);
    }

    private static void renderLayer(Level level, RenderConsumer draw) {
        for (int i = 0; i < level.getSize(); i++) {
            for (int j = 0; j < level.getSize(); j++) {
                LevelTile tile = level.getTile(i, j);
                if (tile == null) continue;
                Vec2i pos = new Vec2i(
                        LevelTile.TILE_WIDTH / 2 * i - LevelTile.TILE_WIDTH / 2 * j,
                        LevelTile.TILE_HEIGHT / 2 * i + LevelTile.TILE_HEIGHT / 2 * j
                );
                draw.draw(tile, new Vec2i(i, j), pos);
            }
        }
    }

    private static void renderTile(LevelTile tile, Vec2i pos) {
        drawTexture(tile.getType().getTexture(), pos.getX(), pos.getY(), ColorUtil.WHITE);
    }

    private static void renderCastleBorders(Vec2i pos, List<CastleBorder> castleBorders) {
        castleBorders.forEach(b -> drawTexture(b.getTexture(), pos.getX(), pos.getY(), ColorUtil.WHITE));
    }

    private static void renderEntity(Vec2i pos, Entity entity) {
        drawTexture(entity.getTexture(), pos.getX() + 16, pos.getY() - 10, ColorUtil.WHITE);
    }

    @FunctionalInterface
    private interface RenderConsumer {
        void draw(LevelTile tile, Vec2i indexPos, Vec2i graphicPos);
    }
}
