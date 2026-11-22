package de.brickforceaurora.server.net.protocol;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;

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

    public PacketBuf(final byte[] bytes) {
        this.buffer = Unpooled.wrappedBuffer(bytes);
    }

    public ByteBuf buffer() {
        return buffer;
    }
    
    public void clear() {
        buffer.clear();
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
        final byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        try (FastByteArrayInputStream input = new FastByteArrayInputStream(bytes)) {
            return ImageIO.read(input);
        }
    }

    public Version readVersion() {
        final int major = buffer.readIntLE();
        final int minor = buffer.readIntLE();
        final int patch = buffer.readIntLE();
        final int revision = buffer.readIntLE();
        return new Version(major, minor, patch, revision);
    }

    public LocalDateTime readDateTime() {
        final int year = buffer.readIntLE();
        final byte month = buffer.readByte();
        final byte day = buffer.readByte();
        final byte hour = buffer.readByte();
        final byte minute = buffer.readByte();
        final byte second = buffer.readByte();
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public byte[] readByteArray() {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return new byte[0];
        }
        final byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return bytes;
    }

    public byte[] readReversedByteArray() {
        return reverse(readByteArray());
    }

    public int[] readIntArray() {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return new int[0];
        }
        final int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = buffer.readIntLE();
        }
        return values;
    }

    public <T> T[] readArray(final IntFunction<T[]> arrayCreator, final Function<PacketBuf, T> valueReader) {
        final int length = buffer.readIntLE();
        if (length <= 0) {
            return arrayCreator.apply(0);
        }
        final T[] values = arrayCreator.apply(length);
        for (int i = 0; i < length; i++) {
            values[i] = valueReader.apply(this);
        }
        return values;
    }

    /*
     * Writing
     */

    public void writeByte(final byte value) {
        buffer.writeByte(value);
    }

    public void writeByte(final int value) {
        buffer.writeByte(value & 0xFF);
    }

    public void writeShort(final short value) {
        buffer.writeShortLE(value);
    }

    public void writeShort(final int value) {
        buffer.writeShortLE(value & 0xFFFF);
    }

    public void writeInt(final int value) {
        buffer.writeIntLE(value);
    }

    public void writeInt(final long value) {
        buffer.writeIntLE((int) (value & 0xFFFFFFFF));
    }

    public void writeLong(final long value) {
        buffer.writeLongLE(value);
    }

    public void writeFloat(final float value) {
        buffer.writeFloatLE(value);
    }

    public void writeDouble(final double value) {
        buffer.writeDoubleLE(value);
    }

    public void writeBoolean(final boolean value) {
        buffer.writeBoolean(value);
    }

    public void writeString(final String value) {
        if (value == null || value.isEmpty()) {
            buffer.writeIntLE(0);
            return;
        }
        final byte[] bytes = value.getBytes(StandardCharsets.UTF_16LE);
        buffer.writeIntLE(bytes.length);
        buffer.writeBytes(bytes);
    }

    public void writeImage(final BufferedImage image) throws IOException {
        if (image == null) {
            buffer.writeIntLE(0);
            return;
        }
        final FastByteArrayOutputStream output = new FastByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        buffer.writeIntLE(output.length);
        buffer.writeBytes(output.array, 0, output.length);
    }

    public void writeDateTime(final LocalDateTime time) {
        buffer.writeIntLE(time.getYear());
        buffer.writeByte(time.getMonthValue());
        buffer.writeByte(time.getDayOfMonth());
        buffer.writeByte(time.getHour());
        buffer.writeByte(time.getMinute());
        buffer.writeByte(time.getSecond());
    }

    public void writeByteArray(final byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            buffer.writeIntLE(0);
            return;
        }
        buffer.writeIntLE(bytes.length);
        buffer.writeBytes(bytes);
    }

    public void writeReversedByteArray(final byte[] bytes) {
        writeByteArray(reverse(bytes));
    }

    public void writeIntArray(final int[] values) {
        if (values == null || values.length == 0) {
            buffer.writeIntLE(0);
            return;
        }
        buffer.writeIntLE(values.length);
        for (final int value : values) {
            buffer.writeIntLE(value);
        }
    }

    public <T> void writeArray(final T[] values, final BiConsumer<PacketBuf, T> valueWriter) {
        if (values == null || values.length == 0) {
            buffer.writeIntLE(0);
            return;
        }
        buffer.writeIntLE(values.length);
        for (final T value : values) {
            valueWriter.accept(this, value);
        }
    }

    /*
     * Helper
     */

    private byte[] reverse(final byte[] input) {
        if (input == null || input.length < 2) {
            return input;
        }
        final byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[output.length - 1 - i] = input[i];
        }
        return output;
    }

    /*
     * Closing
     */

    @Override
    public void close() {
        buffer.release();
    }

}
