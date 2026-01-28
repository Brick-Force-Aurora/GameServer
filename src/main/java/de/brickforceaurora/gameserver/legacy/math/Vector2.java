package de.brickforceaurora.gameserver.legacy.math;

public final class Vector2 {

    public static final Vector2 ZERO = new Vector2(0f, 0f);

    public float x;
    public float y;

    public Vector2() {
        this(0f, 0f);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 o) {
        return new Vector2(x + o.x, y + o.y);
    }

    public Vector2 sub(Vector2 o) {
        return new Vector2(x - o.x, y - o.y);
    }

    public Vector2 mul(float s) {
        return new Vector2(x * s, y * s);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    @Override
    public String toString() {
        return "Vector2(" + x + ", " + y + ")";
    }
}
