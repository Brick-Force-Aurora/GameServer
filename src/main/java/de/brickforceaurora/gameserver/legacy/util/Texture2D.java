package de.brickforceaurora.gameserver.legacy.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Texture2D {

    public int width;
    public int height;
    private byte[] rawData; // RGBA32
    public String name;

    public Texture2D(int width, int height) {
        this.width = width;
        this.height = height;
        this.rawData = new byte[width * height * 4]; // RGBA32
    }

    /* =========================
       Load PNG/JPG bytes
       ========================= */
    public void loadImage(byte[] imageBytes) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        if (img == null) {
            throw new IOException("Unsupported image format");
        }

        width = img.getWidth();
        height = img.getHeight();
        rawData = new byte[width * height * 4];

        int idx = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = img.getRGB(x, y);

                rawData[idx++] = (byte) ((argb >> 16) & 0xFF); // R
                rawData[idx++] = (byte) ((argb >> 8) & 0xFF);  // G
                rawData[idx++] = (byte) (argb & 0xFF);         // B
                rawData[idx++] = (byte) ((argb >> 24) & 0xFF); // A
            }
        }
    }

    /* =========================
       Encode to PNG
       ========================= */
    public byte[] encodeToPNG() throws IOException {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int idx = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = rawData[idx++] & 0xFF;
                int g = rawData[idx++] & 0xFF;
                int b = rawData[idx++] & 0xFF;
                int a = rawData[idx++] & 0xFF;

                int argb = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, argb);
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "PNG", out);
        return out.toByteArray();
    }

    /* =========================
       Raw buffer access
       ========================= */
    public byte[] getRawTextureData() {
        return rawData;
    }
}
