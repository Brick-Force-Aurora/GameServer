package de.brickforceaurora.server.net.rendezvous.protocol;

import io.netty.buffer.ByteBuf;

public record P2PHeader(int size, int id, int crc, int meta, int source, int destination) {

    public boolean reliable() {
        return meta != P2PProtocolConstant.NO_META;
    }

    public boolean broadcast() {
        return destination == P2PProtocolConstant.BROADCAST;
    }

    public static P2PHeader parse(final ByteBuf data) {
        final int start = data.readerIndex();
        final int readable = data.readableBytes();
        if (readable < P2PProtocolConstant.HEADER_SIZE || readable > P2PProtocolConstant.MAX_DATAGRAM_SIZE) {
            return null;
        }
        final int size = data.getUnsignedShortLE(start);
        if (readable != P2PProtocolConstant.HEADER_SIZE + size) {
            return null;
        }
        final int crc = data.getUnsignedByte(start + 3);
        if (crc != checksum(data, start + P2PProtocolConstant.HEADER_SIZE, size)) {
            return null;
        }
        return new P2PHeader(size, data.getUnsignedByte(start + 2), crc, data.getUnsignedShortLE(start + 4),
            data.getUnsignedByte(start + 6), data.getUnsignedByte(start + 7));
    }

    public void write(final ByteBuf buffer) {
        buffer.writeShortLE(size).writeByte(id).writeByte(crc).writeShortLE(meta).writeByte(source).writeByte(destination);
    }

    static int checksum(final ByteBuf data, final int offset, final int length) {
        int crc = 0;
        for (int i = 0; i < length; i++) {
            crc ^= data.getByte(offset + i);
        }
        return crc & 0xFF;
    }

}
