package de.brickforceaurora.gameserver.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public final class Msg4Send {

    private final byte[] buffer;

    public byte[] buffer() {
        return buffer;
    }

    public Msg4Send(int id, long meta, long src, MsgBody body, byte sendKey) {

        int offset = body.offset();
        byte[] srcBody = body.buffer();

        // Copy body (do NOT mutate original MsgBody)
        byte[] bodyCopy = new byte[offset];
        System.arraycopy(srcBody, 0, bodyCopy, 0, offset);

        byte crc = 0;

        if ((sendKey & 0xFF) == 0xFF) {
            for (int i = 0; i < offset; i++) {
                crc ^= bodyCopy[i];
            }
        } else {
            for (int i = 0; i < offset; i++) {
                crc ^= bodyCopy[i];
                bodyCopy[i] ^= sendKey;
            }
        }

        MsgHdr hdr = new MsgHdr(offset, id, crc, meta, src);

        byte[] hdrBytes = hdr.toArray();

        // Final buffer
        buffer = new byte[MsgHdr.SIZE + offset];
        System.arraycopy(hdrBytes, 0, buffer, 0, MsgHdr.SIZE);
        System.arraycopy(bodyCopy, 0, buffer, MsgHdr.SIZE, offset);
    }


    public ByteBuf toByteBuf(ByteBufAllocator allocator) {
        ByteBuf buf = allocator.buffer(buffer.length);
        buf.writeBytes(buffer);
        return buf;
    }
}
