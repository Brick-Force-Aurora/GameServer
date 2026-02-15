package de.brickforceaurora.server.net.protocol;

import java.util.List;

import de.brickforceaurora.server.net.BFClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class BFServerboundPacketDecoder extends ByteToMessageDecoder {

    private final ISimpleLogger logger;

    private final BFClient client;
    private final byte receiveKey;

    public BFServerboundPacketDecoder(final ISimpleLogger logger, final BFClient client) {
        this(logger, client, ProtocolConstant.DEFAULT_RECEIVE_KEY);
    }

    public BFServerboundPacketDecoder(final ISimpleLogger logger, final BFClient client, final byte receiveKey) {
        this.logger = logger;
        this.client = client;
        this.receiveKey = receiveKey;
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf accumulator, final List<Object> out) throws Exception {
        while (accumulator.isReadable()) {
            if (accumulator.readableBytes() < ProtocolConstant.HEADER_SIZE) {
                return;
            }
            accumulator.markReaderIndex();
            final long size = accumulator.readUnsignedIntLE();
            final int id = accumulator.readUnsignedShortLE();

            final byte crc = accumulator.readByte();
            // We don't really care about the meta and src value anywhere therefore we just skip it.
            // If we ever need it the code can simply be uncommented
            accumulator.skipBytes(8);
            //      long meta = accumulator.readUnsignedIntLE();
            //      long src = accumulator.readUnsignedIntLE();
            if (accumulator.readableBytes() < size) {
                accumulator.resetReaderIndex();
                return;
            }
            final IServerboundPacket packet = PacketRegistry.newServerPacket(id);
            if (packet == null) {
                logger.debug("Received unknown from client {0}: {1}", id);
                ctx.close();
                return;
            }
            logger.debug("Received from client {0}: {1} ({2})", client, packet.packetName(), packet.packetId());
            // We simply hope this is never larger than Integer.MAX_VALUE
            final byte[] messageBuffer = new byte[(int) size];
            accumulator.readBytes(messageBuffer);
            accumulator.discardReadBytes();
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
            try (PacketBuf packetBuf = new PacketBuf(messageBuffer)) {
                packet.read(packetBuf);
                out.add(packet);
            }
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Error in inbound netty pipeline of player '{0}'", cause, client);
        ctx.close();
    }

}
