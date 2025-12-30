package de.brickforceaurora.gameserver.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public final class Msg4Send {

    private final byte[] buffer;

    public byte[] buffer() {
        return buffer;
    }

    public Msg4Send(int id, long meta, long src, MsgBody body, byte sendKey) {

        int payloadSize = body.offset();
        byte[] bodyBuf = body.buffer();

        byte crc = 0;

        // CRC + optional encryption (ONLY payloadSize bytes)
        if ((sendKey & 0xFF) == 0xFF) {
            for (int i = 0; i < payloadSize; i++) {
                crc ^= bodyBuf[i];
            }
        } else {
            for (int i = 0; i < payloadSize; i++) {
                crc ^= bodyBuf[i];
                bodyBuf[i] ^= sendKey;
            }
        }

        // Build EXACT 15-byte header
        byte[] hdr = new byte[15];

        // id (ushort, big-endian)
        hdr[0] = (byte) ((id >>> 8) & 0xFF);
        hdr[1] = (byte) (id & 0xFF);

        // meta (uint, big-endian)
        hdr[2] = (byte) ((meta >>> 24) & 0xFF);
        hdr[3] = (byte) ((meta >>> 16) & 0xFF);
        hdr[4] = (byte) ((meta >>> 8) & 0xFF);
        hdr[5] = (byte) (meta & 0xFF);

        // size (uint, big-endian)
        hdr[6] = (byte) ((payloadSize >>> 24) & 0xFF);
        hdr[7] = (byte) ((payloadSize >>> 16) & 0xFF);
        hdr[8] = (byte) ((payloadSize >>> 8) & 0xFF);
        hdr[9] = (byte) (payloadSize & 0xFF);

        // crc
        hdr[10] = crc;

        // padding (required, C# assumes body starts at 15)
        hdr[11] = 0;
        hdr[12] = 0;
        hdr[13] = 0;
        hdr[14] = 0;

        // Final packet
        buffer = new byte[15 + payloadSize];
        System.arraycopy(hdr, 0, buffer, 0, 15);
        System.arraycopy(bodyBuf, 0, buffer, 15, payloadSize);
    }

    public ByteBuf toByteBuf(ByteBufAllocator allocator) {
        ByteBuf buf = allocator.buffer(buffer.length);
        buf.writeBytes(buffer);
        return buf;
    }
}
