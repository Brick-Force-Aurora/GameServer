package de.brickforceaurora.gameserver.legacy.math;

public final class Vector3 {

    public static final Vector3 ZERO = new Vector3(0f, 0f, 0f);

    public float x;
    public float y;
    public float z;

    public Vector3() {
        this(0f, 0f, 0f);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 o) {
        return new Vector3(x + o.x, y + o.y, z + o.z);
    }

    public Vector3 sub(Vector3 o) {
        return new Vector3(x - o.x, y - o.y, z - o.z);
    }

    public Vector3 mul(float s) {
        return new Vector3(x * s, y * s, z * s);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    @Override
    public String toString() {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }
}
