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
public class Vec2i {

    private int x = 0, y = 0;

    public Vec2i add(Vec2i v) {
        return new Vec2i(x + v.x, y + v.y);
    }
    public Vec2i add(int x, int y) {
        return new Vec2i(this.x + x, this.y + y);
    }
    public Vec2i addX(int x) {
        return new Vec2i(this.x + x, this.y);
    }
    public Vec2i addY(int y) {
        return new Vec2i(this.x, this.y + y);
    }

    public Vec2i sub(Vec2i v) {
        return new Vec2i(x - v.x, y - v.y);
    }

    public Vec2i mul(Vec2i v) {
        return new Vec2i(x * v.x, y * v.y);
    }
    public Vec2i mul(int v) {
        return new Vec2i(x * v, y * v);
    }
    public Vec2i mul(float v) {
        return new Vec2i(Math.round(x * v), Math.round(y * v));
    }

    public Vector2 rl() {
        return new Vector2(x, y);
    }
    public static Vec2i rl(Vector2 v) {
        return new Vec2i(Math.round(v.x()), Math.round(v.y()));
    }

    public Vec2f toFloat() {
        return new Vec2f(x, y);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
