package ru.mrbedrockpy.level;

import com.raylib.Camera2D;
import com.raylib.Raylib;
import com.raylib.Vector2;
import ru.mrbedrockpy.Game;
import ru.mrbedrockpy.level.entity.Entity;
import ru.mrbedrockpy.render.Window;
import ru.mrbedrockpy.util.math.Vec2i;

import java.util.Objects;

public class PlayerController {

    private boolean lastMouseDown = false;
    private Vec2i lastMousePos = null;

    public void handleInput() {
        Camera2D camera = Window.getCamera();
        Vec2i offset = Vec2i.rl(camera.getOffset());
        Vec2i mouse = Window.mousePos();
        boolean currentMouseDown = Window.mouseDown(Raylib.MouseButton.MOUSE_BUTTON_LEFT);
        if (currentMouseDown) {
            if (lastMouseDown) {
                Vec2i delta = mouse.sub(lastMousePos);
                offset.setX(offset.getX() + delta.getX());
                offset.setY(offset.getY() + delta.getY());
            }
            lastMousePos = mouse;
        }
        if (!currentMouseDown && lastMouseDown) {
            Vector2 world = Raylib.getScreenToWorld2D(mouse.rl(), camera);
            Vec2i tilePos = getTileUnderMouse(world);
            if (tilePos != null) {
                Level level = Game.INSTANCE.getLevel();
                LevelTile tile = level.getTile(tilePos.getX(), tilePos.getY());
                if (tile != null) {
                    Entity e = tile.getEntity();
                    if (e != null) e.onClick();
                }
                if (level.getSelectedTile() != null) {
                    if (!level.getSelectedTile().equals(tile) && level.getSelectedTile().getEntity() != null)
                        level.getSelectedTile().getEntity().unselect();
                }
                level.setSelectedTile(tile);
            }
        }
        lastMouseDown = currentMouseDown;
        camera.setOffset(offset.rl());
        lastMouseDown = currentMouseDown;
        float wheel = Window.mouseScroll();
        if (wheel != 0) {
            Vector2 mouseVec = mouse.rl();
            Vector2 before = Raylib.getScreenToWorld2D(mouseVec, camera);
            float zoom = camera.getZoom();
            zoom += wheel / 2f;
            zoom = Math.max(Math.min(zoom, 5f), 2f);
            camera.setZoom(zoom);
            Vector2 after = Raylib.getScreenToWorld2D(mouseVec, camera);
            Vector2 target = camera.getTarget();
            target.setX(target.getX() - (after.getX() - before.getX()));
            target.setY(target.getY() - (after.getY() - before.getY()));
            camera.setTarget(target);
        }
    }

    private Vec2i getTileUnderMouse(Vector2 worldPos) {
        float halfW = LevelTile.TILE_WIDTH / 2f;
        float halfH = LevelTile.TILE_HEIGHT / 2f;
        float x = worldPos.getX();
        float y = worldPos.getY();
        int baseI = (int) Math.floor((x / halfW + y / halfH) / 2f);
        int baseJ = (int) Math.floor((y / halfH - x / halfW) / 2f);
        Vec2i best = null;
        float bestDist = Float.MAX_VALUE;
        for (int di = 0; di <= 1; di++) {
            for (int dj = 0; dj <= 1; dj++) {
                int i = baseI + di;
                int j = baseJ + dj;
                Vector2 center = isoToWorld(i, j);
                float dx = Math.abs(x - center.getX()) / halfW;
                float dy = Math.abs(y - center.getY()) / halfH;
                if (dx + dy <= 1.0f) {
                    float dist = dx + dy;
                    if (dist < bestDist) {
                        bestDist = dist;
                        best = new Vec2i(i - 1, j);
                    }
                }
            }
        }
        return best;
    }

    private Vector2 isoToWorld(int i, int j) {
        return new Vector2(LevelTile.TILE_WIDTH / 2f * (i - j), LevelTile.TILE_HEIGHT / 2f * (i + j));
    }
}