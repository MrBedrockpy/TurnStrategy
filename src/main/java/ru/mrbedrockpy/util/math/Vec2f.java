package ru.mrbedrockpy.util.math;

import com.raylib.Vector2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vec2f {

    private float x = 0, y = 0;

    public Vec2f add(Vec2f v) {
        return new Vec2f(x + v.x, y + v.y);
    }
    public Vec2f sub(Vec2f v) {
        return new Vec2f(x - v.x, y - v.y);
    }
    public Vec2f mul(Vec2f v) {
        return new Vec2f(x * v.x, y * v.y);
    }

    public Vector2 rl() {
        return new Vector2(x, y);
    }
    public static Vec2f rl(Vector2 v) {
        return new Vec2f(v.x(), v.y());
    }

    public Vec2i toInt() {
        return new Vec2i(Math.round(x), Math.round(y));
    }
}
