package de.brickforceaurora.gameserver.legacy.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public final class Msg4Send {

    private final byte[] buffer;

    public byte[] buffer() {
        return buffer;
    }

    public Msg4Send(final int id, final long meta, final long src, final MsgBody body, final byte sendKey) {

        final int offset = body.offset();
        final byte[] srcBody = body.buffer();

        // Copy body (do NOT mutate original MsgBody)
        final byte[] bodyCopy = new byte[offset];
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

        final MsgHdr hdr = new MsgHdr(offset, id, crc, meta, src);

        final byte[] hdrBytes = hdr.toArray();

        // Final buffer
        buffer = new byte[MsgHdr.SIZE + offset];
        System.arraycopy(hdrBytes, 0, buffer, 0, MsgHdr.SIZE);
        System.arraycopy(bodyCopy, 0, buffer, MsgHdr.SIZE, offset);
    }

    public ByteBuf toByteBuf(final ByteBufAllocator allocator) {
        final ByteBuf buf = allocator.buffer(buffer.length);
        buf.writeBytes(buffer);
        return buf;
    }
}
