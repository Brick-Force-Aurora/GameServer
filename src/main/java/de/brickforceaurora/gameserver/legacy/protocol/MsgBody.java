package de.brickforceaurora.gameserver.legacy.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class MsgBody {

    private byte[] buffer;
    private int offset;

    public int offset() {
        return offset;
    }

    public byte[] buffer() {
        return buffer;
    }

    public MsgBody() {
        buffer = new byte[8192];
        offset = 0;
    }

    public MsgBody(final byte[] src, final int off, final int len) {
        buffer = Arrays.copyOfRange(src, off, off + len);
        offset = 0;
    }

    private void ensure(final int add) {
        if (offset + add > buffer.length) {
            buffer = Arrays.copyOf(buffer, buffer.length * 2);
        }
    }

    public void decrypt(final byte key) {
        if ((key & 0xFF) != 0xFF) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] ^= key;
            }
        }
    }

    /* ===== WRITE ===== */

    public void write(final int v) {
        ensure(4);
        ByteBuffer.wrap(buffer, offset, 4).order(ByteOrder.LITTLE_ENDIAN).putInt(v);
        offset += 4;
    }

    public void write(final long v) {
        ensure(8);
        ByteBuffer.wrap(buffer, offset, 8).order(ByteOrder.LITTLE_ENDIAN).putLong(v);
        offset += 8;
    }

    public void write(final short v) {
        ensure(2);
        ByteBuffer.wrap(buffer, offset, 2).order(ByteOrder.LITTLE_ENDIAN).putShort(v);
        offset += 2;
    }

    public void write(final boolean v) {
        ensure(1);
        buffer[offset++] = (byte) (v ? 1 : 0);
    }

    public void writeUShort(final int v) {
        ensure(2);
        buffer[offset++] = (byte) (v & 0xFF);
        buffer[offset++] = (byte) (v >>> 8 & 0xFF);
    }

    public void write(final byte v) {
        ensure(1);
        buffer[offset++] = v;
    }

    public void write(final String s) {
        final byte[] data = s.getBytes(StandardCharsets.UTF_16LE);
        write(data.length);
        ensure(data.length);
        System.arraycopy(data, 0, buffer, offset, data.length);
        offset += data.length;
    }

    /* ===== READ ===== */

    public int readInt() {
        final int v = ByteBuffer.wrap(buffer, offset, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        offset += 4;
        return v;
    }

    public boolean readBool() {
        return buffer[offset++] != 0;
    }

    public short readShort() {
        final short v = ByteBuffer.wrap(buffer, offset, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        offset += 2;
        return v;
    }

    public int readUShort() {
        final int v = ByteBuffer.wrap(buffer, offset, 2).order(ByteOrder.LITTLE_ENDIAN).getShort() & 0xFFFF;
        offset += 2;
        return v;
    }

    public String readString() {
        // length in BYTES (not chars)
        final int len = readInt();

        if (len <= 0) {
            return "";
        }

        final String val = new String(buffer, offset, len, StandardCharsets.UTF_16LE);
        offset += len;
        return val;
    }

    public float readFloat() {
        final float v = ByteBuffer.wrap(buffer, offset, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
        offset += 4;
        return v;
    }

    public long readLong() {
        final long v = ByteBuffer.wrap(buffer, offset, 8).order(ByteOrder.LITTLE_ENDIAN).getLong();
        offset += 8;
        return v;
    }

    public byte readByte() {
        return buffer[offset++];
    }
}
