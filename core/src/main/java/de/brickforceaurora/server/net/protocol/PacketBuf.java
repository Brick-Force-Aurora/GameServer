package de.brickforceaurora.server.net.protocol;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import me.lauriichan.snowframe.util.Version;

public final class PacketBuf implements AutoCloseable {

    private final ByteBuf buffer;

    public PacketBuf() {
        this.buffer = Unpooled.buffer();
    }

    public PacketBuf(byte[] bytes) {
        this.buffer = Unpooled.wrappedBuffer(bytes);
    }

    public ByteBuf buffer() {
        return buffer;
    }

    /*
     * Reading
     */

    public byte readByte() {
        return buffer.readByte();
    }

    public short readUnsignedByte() {
        return buffer.readUnsignedByte();
    }

    public short readShort() {
        return buffer.readShortLE();
    }

    public int readUnsignedShort() {
        return buffer.readUnsignedShortLE();
    }

    public int readInt() {
        return buffer.readIntLE();
    }

    public long readUnsignedInt() {
        return buffer.readUnsignedIntLE();
    }

    public long readLong() {
        return buffer.readLongLE();
    }

    public float readFloat() {
        return buffer.readFloatLE();
    }

    public double readDouble() {
        return buffer.readDoubleLE();
    }

    public boolean readBoolean() {
        return buffer.readBoolean();
    }

    public String readString() {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return "";
        }
        final byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_16LE);
    }

    public BufferedImage readImage() throws IOException {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        try (FastByteArrayInputStream input = new FastByteArrayInputStream(bytes)) {
            return ImageIO.read(input);
        }
    }
    
    public Version readVersion() {
        int major = buffer.readIntLE();
        int minor = buffer.readIntLE();
        int patch = buffer.readIntLE();
        int revision = buffer.readIntLE();
        return new Version(major, minor, patch, revision);
    }

    public LocalDateTime readDateTime() {
        int year = buffer.readIntLE();
        byte month = buffer.readByte();
        byte day = buffer.readByte();
        byte hour = buffer.readByte();
        byte minute = buffer.readByte();
        byte second = buffer.readByte();
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
    
    public byte[] readByteArray() {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return new byte[0];
        }
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return bytes;
    }

    public int[] readIntArray() {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return new int[0];
        }
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = buffer.readIntLE();
        }
        return values;
    }

    /*
     * Writing
     */

    public void writeByte(byte value) {
        buffer.writeByte(value);
    }

    public void writeByte(int value) {
        buffer.writeByte(value & 0xFF);
    }

    public void writeShort(short value) {
        buffer.writeShortLE(value);
    }

    public void writeShort(int value) {
        buffer.writeShortLE(value & 0xFFFF);
    }

    public void writeInt(int value) {
        buffer.writeIntLE(value);
    }

    public void writeInt(long value) {
        buffer.writeIntLE((int) (value & 0xFFFFFFFF));
    }

    public void writeLong(long value) {
        buffer.writeLongLE(value);
    }

    public void writeFloat(float value) {
        buffer.writeFloatLE(value);
    }

    public void writeDouble(double value) {
        buffer.writeDoubleLE(value);
    }

    public void writeBoolean(boolean value) {
        buffer.writeBoolean(value);
    }

    public void writeString(String value) {
        if (value == null || value.isEmpty()) {
            buffer.writeIntLE(0);
            return;
        }
        final byte[] bytes = value.getBytes(StandardCharsets.UTF_16LE);
        buffer.writeIntLE(bytes.length);
        buffer.writeBytes(bytes);
    }

    public void writeImage(BufferedImage image) throws IOException {
        if (image == null) {
            buffer.writeIntLE(0);
            return;
        }
        final FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        buffer.writeIntLE(output.length);
        buffer.writeBytes(output.array, 0, output.length);
    }

    public void writeDateTime(LocalDateTime time) {
        buffer.writeIntLE(time.getYear());
        buffer.writeByte(time.getMonthValue());
        buffer.writeByte(time.getDayOfMonth());
        buffer.writeByte(time.getHour());
        buffer.writeByte(time.getMinute());
        buffer.writeByte(time.getSecond());
    }

    public void writeByteArray(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            buffer.writeIntLE(0);
            return;
        }
        buffer.writeIntLE(bytes.length);
        buffer.writeBytes(bytes);
    }
    
    public void writeIntArray(int[] values) {
        if (values == null || values.length == 0) {
            buffer.writeIntLE(0);
            return;
        }
        buffer.writeIntLE(values.length);
        for (int value : values) {
            buffer.writeIntLE(value);
        }
    }

    /*
     * Closing
     */

    @Override
    public void close() {
        buffer.release();
    }

}
