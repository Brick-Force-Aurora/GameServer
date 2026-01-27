package de.brickforceaurora.gameserver.net.protocol;

import java.util.List;

import de.brickforceaurora.gameserver.GameServerApp;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class BFServerboundPacketDecoder extends ByteToMessageDecoder {

    private final ISimpleLogger logger = GameServerApp.logger();
    private final ByteBuf accumulator = Unpooled.buffer(8092);

    private final byte receiveKey;

    public BFServerboundPacketDecoder() {
        this(ProtocolConstant.DEFAULT_RECEIVE_KEY);
    }

    public BFServerboundPacketDecoder(final byte receiveKey) {
        this.receiveKey = receiveKey;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        msg.readBytes(accumulator);
        logger.debug("Hello");
        if (accumulator.readableBytes() < ProtocolConstant.HEADER_SIZE) {
            return;
        }
        accumulator.markReaderIndex();
        long size = accumulator.readUnsignedIntLE();
        int id = accumulator.readUnsignedShortLE();

        byte crc = accumulator.readByte();
        // We don't really care about the meta and src value anywhere therefore we just skip it.
        // If we ever need it the code can simply be uncommented
        accumulator.skipBytes(8);
        //      long meta = accumulator.readUnsignedIntLE();
        //      long src = accumulator.readUnsignedIntLE();
        logger.debug("Hello2");
        if (accumulator.readableBytes() < size) {
            accumulator.resetReaderIndex();
            return;
        }
        IServerboundPacket packet = PacketRegistry.newServerPacket(id);
        if (packet == null) {
            logger.debug("Unknown packet: {0}", id);
            ctx.close();
            return;
        }
        logger.debug("Packet: {0} ({1})", packet.packetName(), packet.packetId());
        // We simply hope this is never larger than Integer.MAX_VALUE
        byte[] messageBuffer = new byte[(int) size];
        accumulator.readBytes(messageBuffer);
        byte calculatedCrc = 0;
        if (receiveKey == ProtocolConstant.KEY_MAX_VALUE) {
            for (int i = 0; i < messageBuffer.length; i++) {
                calculatedCrc ^= messageBuffer[i];
            }
        } else {
            // Decrypt buffer and calculate crc
            for (int i = 0; i < messageBuffer.length; i++) {
                messageBuffer[i] ^= receiveKey;
                calculatedCrc ^= messageBuffer[i];
            }
        }
        if (crc != calculatedCrc) {
            ctx.close();
            return;
        }
        ByteBuf packetBuf = Unpooled.wrappedBuffer(messageBuffer);
        try {
            packet.read(packetBuf);
        } finally {
            packetBuf.release();
        }
    }

}
