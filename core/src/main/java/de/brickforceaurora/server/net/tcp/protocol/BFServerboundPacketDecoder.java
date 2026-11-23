package de.brickforceaurora.server.net.tcp.protocol;

import java.util.List;

import de.brickforceaurora.server.net.protocol.PacketRegistry;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import de.brickforceaurora.server.util.CRC32;
import de.brickforceaurora.server.util.Encryption;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class BFServerboundPacketDecoder extends ByteToMessageDecoder {

    private static class ReceivingBuffer {

        private final IServerboundPacket packet;
        private final ByteBuf data;
        private final int chunkCount;
        private final long crc;

        private int expectedChunkId;

        public ReceivingBuffer(IServerboundPacket packet, int size, long crc) {
            this.packet = packet;
            this.data = Unpooled.buffer(size, size);
            this.chunkCount = Math.ceilDiv(size, ProtocolConstant.MAX_CHUNK_LENGTH);
            this.expectedChunkId = 0;
            this.crc = crc;
        }

    }

    private final ISimpleLogger logger;

    private final BFClient client;
    private final byte receiveKey;

    private final Int2ObjectArrayMap<ReceivingBuffer> buffers = new Int2ObjectArrayMap<>();

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
            if (id >= ProtocolExtension.DUPLEX_BUFFER_BEGIN && id <= ProtocolExtension.DUPLEX_BUFFER_END) {
                byte[] messageBuffer = new byte[(int) size];
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
                try (PacketBuf buf = new PacketBuf(messageBuffer)) {
                    switch (id) {
                    case ProtocolExtension.DUPLEX_BUFFER_BEGIN -> {
                        int bufId = buf.readUnsignedShort();
                        long bufSize = buf.readUnsignedInt();
                        long bufCrc = buf.readUnsignedInt();
                        if (bufSize > ProtocolConstant.MAX_BUFFER_LIMIT) {
                            logger.warning("Received too big chunked buffer from client {0}: ID: {1}, Size: {2}, Allowed Size: {3}", client,
                                bufId, bufSize, ProtocolConstant.MAX_BUFFER_LIMIT);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        ReceivingBuffer buffer = buffers.get(bufId);
                        if (buffer != null) {
                            logger.warning("Received another buffer begin for same message from client {0}: {1}", client, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        final IServerboundPacket packet = PacketRegistry.newServerPacket(bufId);
                        if (packet == null) {
                            logger.warning("Received buffer begin for unknown from client {0}: {1}", client, bufId);
                            return;
                        }
                        buffers.put(bufId, new ReceivingBuffer(packet, (int) bufSize, bufCrc));
                    }
                    case ProtocolExtension.DUPLEX_BUFFER_CHUNK -> {
                        int bufId = buf.readUnsignedShort();
                        int chunkId = buf.readUnsignedShort();
                        int byteLength = buf.readInt();
                        ReceivingBuffer buffer = buffers.get(bufId);
                        if (buffer == null) {
                            logger.warning("Received buffer chunk for unbuffered message from client {0}: {1}", client, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        if (chunkId != buffer.expectedChunkId) {
                            logger.warning("Expected chunk {1} but got chunk {2} for buffer of message {3} from client {0}", client,
                                buffer.expectedChunkId, chunkId, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        buf.buffer().readBytes(buffer.data, byteLength);
                        buffer.expectedChunkId = chunkId + 1;
                    }
                    case ProtocolExtension.DUPLEX_BUFFER_END -> {
                        int bufId = buf.readUnsignedShort();
                        ReceivingBuffer buffer = buffers.get(bufId);
                        if (buffer == null) {
                            logger.warning("Received buffer end for unbuffered message from client {0}: {1}", client, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        if (buffer.chunkCount != buffer.expectedChunkId) {
                            logger.warning("Not all chunks were delivered for buffer of message {1} from client {0}", client, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        buffer.data.resetReaderIndex();
                        long bufCrc = CRC32.computeUnsigned(buffer.data);
                        buffer.data.resetReaderIndex();
                        if (bufCrc != buffer.crc) {
                            logger.warning("CRC check failed, expected {1} but got {2} for buffer of message {3} from client {0}", client,
                                buffer.crc, bufCrc, bufId);
                            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received invalid chunked buffer"))
                                .addListener(ChannelFutureListener.CLOSE);
                            return;
                        }
                        buffers.remove(bufId);
                        if (buffer.packet.encrypted()) {
                            messageBuffer = new byte[buffer.data.readableBytes()];
                            buffer.data.readBytes(messageBuffer);
                            messageBuffer = Encryption.decrypt(messageBuffer, Encryption.PRIVATE);
                        }
                        try (PacketBuf packetBuf = new PacketBuf(messageBuffer)) {
                            buffer.packet.read(packetBuf);
                            out.add(buffer.packet);
                        }
                    }
                    }
                }
                return;
            }
            final IServerboundPacket packet = PacketRegistry.newServerPacket(id);
            if (packet == null) {
                logger.debug("Received unknown from client {0}: {1}", client, id);
                ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Received unknown packet"))
                    .addListener(ChannelFutureListener.CLOSE);
                return;
            }
            logger.debug("Received from client {0}: {1} ({2})", client, packet.packetName(), packet.packetId());
            // We simply hope this is never larger than Integer.MAX_VALUE
            byte[] messageBuffer = new byte[(int) size];
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
            if (packet.encrypted()) {
                messageBuffer = Encryption.decrypt(messageBuffer, Encryption.PRIVATE);
            }
            try (PacketBuf packetBuf = new PacketBuf(messageBuffer)) {
                packet.read(packetBuf);
                out.add(packet);
            }
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        logger.error("Error in inbound netty pipeline of player '{0}'", cause, client);
        ctx.close();
    }

}
