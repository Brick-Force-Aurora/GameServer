package de.brickforceaurora.gameserver.legacy.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class MsgHdr {
    public static final int SIZE = 15;

    public long size;   // uint32
    public int id;      // uint16
    public byte crc;
    public long meta;   // uint32
    public long src;    // uint32

    public MsgHdr() {}

    public MsgHdr(final long size, final int id, final byte crc, final long meta, final long src) {
        this.size = size;
        this.id = id;
        this.crc = crc;
        this.meta = meta;
        this.src = src;
    }

    public byte[] toArray() {
        final ByteBuffer buf = ByteBuffer.allocate(SIZE).order(ByteOrder.LITTLE_ENDIAN);
        buf.putInt((int) size);
        buf.putShort((short) id);
        buf.put(crc);
        buf.putInt((int) meta);
        buf.putInt((int) src);
        return buf.array();
    }

    public void fromArray(final byte[] srcBuf) {
        final ByteBuffer buf = ByteBuffer.wrap(srcBuf).order(ByteOrder.LITTLE_ENDIAN);
        size = Integer.toUnsignedLong(buf.getInt());
        id = Short.toUnsignedInt(buf.getShort());
        crc = buf.get();
        meta = Integer.toUnsignedLong(buf.getInt());
        src = Integer.toUnsignedLong(buf.getInt());
    }
}
