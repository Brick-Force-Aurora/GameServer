package de.brickforceaurora.server.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public final class Msg4Send {

    private final byte[] buffer;

    public byte[] buffer() {
        return buffer;
    }

    public Msg4Send(int id, long meta, long src, MsgBody body, byte sendKey) {

        int payloadSize = body.offset();
        byte crc = 0;

        if ((sendKey & 0xFF) == 0xFF) {
            for (int i = 0; i < payloadSize; i++) {
                crc ^= body.buffer()[i];
            }
        } else {
            for (int i = 0; i < payloadSize; i++) {
                crc ^= body.buffer()[i];
                body.buffer()[i] ^= sendKey;
            }
        }

        MsgHdr hdr = new MsgHdr(payloadSize, id, crc, meta, src);
        byte[] hdrBytes = hdr.toArray();

        buffer = new byte[MsgHdr.SIZE + payloadSize];
        System.arraycopy(hdrBytes, 0, buffer, 0, MsgHdr.SIZE);
        System.arraycopy(body.buffer(), 0, buffer, MsgHdr.SIZE, payloadSize);
    }

    public ByteBuf toByteBuf(ByteBufAllocator allocator) {
        ByteBuf buf = allocator.buffer(buffer.length);
        buf.writeBytes(buffer);
        return buf;
    }
}
