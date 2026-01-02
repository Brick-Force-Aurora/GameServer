package de.brickforceaurora.gameserver.maps;

public final class Landscape {

    public final byte[] bricks;
    public final float[] ratios;
    public final float[] distribution;
    public final byte size;
    public final byte height;

    public Landscape(byte[] bricks, float[] ratios, byte size, byte height) {
        this.bricks = bricks;
        this.ratios = ratios;
        this.size = size;
        this.height = height;

        this.distribution = new float[ratios.length + 1];
        this.distribution[0] = 0f;

        for (int i = 0; i < ratios.length; i++) {
            this.distribution[i + 1] = ratios[i] + this.distribution[i];
        }
    }
}
