package de.brickforceaurora.gameserver.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class MsgBody {

    private byte[] buffer;
    private int offset;

    public int offset() { return offset; }
    public byte[] buffer() { return buffer; }

    public MsgBody() {
        buffer = new byte[8192];
        offset = 0;
    }

    public MsgBody(byte[] src, int off, int len) {
        buffer = Arrays.copyOfRange(src, off, off + len);
        offset = 0;
    }

    private void ensure(int add) {
        if (offset + add > buffer.length) {
            buffer = Arrays.copyOf(buffer, buffer.length * 2);
        }
    }

    public void decrypt(byte key) {
        if ((key & 0xFF) != 0xFF) {
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] ^= key;
            }
        }
    }

    /* ===== WRITE ===== */

    public void write(int v) {
        ensure(4);
        ByteBuffer.wrap(buffer, offset, 4)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putInt(v);
        offset += 4;
    }

    public void write(short v) {
        ensure(2);
        ByteBuffer.wrap(buffer, offset, 2)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putShort(v);
        offset += 2;
    }

    public void write(byte v) {
        ensure(1);
        buffer[offset++] = v;
    }

    public void write(String s) {
        byte[] data = s.getBytes(StandardCharsets.UTF_16LE);
        write(data.length);
        ensure(data.length);
        System.arraycopy(data, 0, buffer, offset, data.length);
        offset += data.length;
    }

    /* ===== READ ===== */

    public int readInt() {
        int v = ByteBuffer.wrap(buffer, offset, 4)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt();
        offset += 4;
        return v;
    }

    public short readShort() {
        short v = ByteBuffer.wrap(buffer, offset, 2)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getShort();
        offset += 2;
        return v;
    }

    public byte readByte() {
        return buffer[offset++];
    }
}
