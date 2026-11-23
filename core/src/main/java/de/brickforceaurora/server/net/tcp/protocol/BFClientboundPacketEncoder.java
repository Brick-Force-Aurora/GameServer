package de.brickforceaurora.server.net.tcp.protocol;

import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import de.brickforceaurora.server.util.CRC32;
import de.brickforceaurora.server.util.Encryption;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class BFClientboundPacketEncoder extends MessageToByteEncoder<IClientboundPacket> {

    private final ISimpleLogger logger;

    private final BFClient client;
    private final byte sendKey;

    public BFClientboundPacketEncoder(final ISimpleLogger logger, final BFClient client) {
        this(logger, client, ProtocolConstant.DEFAULT_SEND_KEY);
    }

    public BFClientboundPacketEncoder(final ISimpleLogger logger, final BFClient client, final byte sendKey) {
        this.logger = logger;
        this.client = client;
        this.sendKey = sendKey;
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final IClientboundPacket packet, final ByteBuf out) throws Exception {
        byte[] body;
        int bodyOffset, bodyLength;
        try (PacketBuf packetBuf = new PacketBuf()) {
            if (packet.requiresClientId()) {
                packetBuf.writeInt(client.id());
            }
            try {
                packet.write(packetBuf);
            } catch (final RuntimeException exp) {
                logger.error("Failed to serialize {1} ({2}) to client {0}", exp, client, packet.packetName(), packet.packetId());
                ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Failed to serialize packet"))
                    .addListener(ChannelFutureListener.CLOSE);
                return;
            }
            final ByteBuf nettyBuf = packetBuf.buffer();
            if (packet.encrypted()) {
                if (client.encryptionKey() == null) {
                    logger.error("Failed to send encrypted {1} ({2}) to client {0}", client, packet.packetName(), packet.packetId());
                    ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Failed to encrypt packet"))
                        .addListener(ChannelFutureListener.CLOSE);
                    return;
                }
                final byte[] bytes = new byte[nettyBuf.readableBytes()];
                System.arraycopy(nettyBuf.array(), nettyBuf.arrayOffset(), bytes, 0, bytes.length);
                body = Encryption.encrypt(bytes, client.encryptionKey());
                bodyOffset = 0;
                bodyLength = body.length;
            } else {
                body = nettyBuf.array();
                bodyOffset = nettyBuf.arrayOffset();
                bodyLength = nettyBuf.readableBytes();
            }
        }

        if (bodyLength > ProtocolConstant.MAX_CHUNK_LENGTH && !(packet instanceof UnchunkedClientboundPacket)) {
            if (bodyLength > ProtocolConstant.MAX_BUFFER_LIMIT) {
                logger.error("Failed to send message {1} ({2}) to client {0}: It is simply too big", client, packet.packetName(),
                    packet.packetId());
                ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Tried to send too big message"))
                    .addListener(ChannelFutureListener.CLOSE);
                return;
            }
            int chunkCount = Math.ceilDiv(bodyLength, ProtocolConstant.MAX_CHUNK_LENGTH);
            long crc = CRC32.computeUnsigned(body, bodyOffset, bodyLength);
            try (PacketBuf packetBuf = new PacketBuf()) {
                packetBuf.writeShort(packet.packetId());
                packetBuf.writeInt(bodyLength);
                packetBuf.writeInt(crc);
                writeBufferMessage(packetBuf, ProtocolExtension.DUPLEX_BUFFER_BEGIN, out);
                ctx.flush();

                int chunkLength, remainingLength = bodyLength;
                for (int chunkId = 0; chunkId < chunkCount; chunkId++) {
                    packetBuf.clear();
                    packetBuf.writeShort(packet.packetId());
                    packetBuf.writeShort(chunkId);
                    chunkLength = Math.min(ProtocolConstant.MAX_CHUNK_LENGTH, remainingLength);
                    packetBuf.buffer().writeBytes(body, chunkId * ProtocolConstant.MAX_CHUNK_LENGTH, chunkLength);
                    remainingLength -= chunkLength;
                    writeBufferMessage(packetBuf, ProtocolExtension.DUPLEX_BUFFER_CHUNK, out);
                    ctx.flush();
                }

                packetBuf.clear();
                packetBuf.writeShort(packet.packetId());
                writeBufferMessage(packetBuf, ProtocolExtension.DUPLEX_BUFFER_END, out);
                ctx.flush();
            }
            return;
        }

        byte crc = 0;
        if (sendKey == ProtocolConstant.KEY_MAX_VALUE) {
            for (int i = 0; i < body.length; i++) {
                crc ^= body[i];
            }
        } else {
            for (int i = 0; i < body.length; i++) {
                crc ^= body[i];
                body[i] ^= sendKey;
            }
        }

        out.writeIntLE(bodyLength);
        out.writeShortLE(packet.packetId());
        out.writeByte(crc);
        out.writeIntLE(0xFFFFFFFF);
        out.writeIntLE(0xFFFFFFFF);
        out.writeBytes(body, bodyOffset, bodyLength);
        ctx.flush();
        if (client != null) {
            logger.debug("Sent to client {0}: {1} ({2})", client, packet.packetName(), packet.packetId());
        }
    }

    private void writeBufferMessage(PacketBuf buf, int packetId, ByteBuf out) {
        ByteBuf buffer = buf.buffer();
        buffer.resetReaderIndex();
        int size = buffer.readableBytes();
        byte crc = 0;
        if (sendKey == ProtocolConstant.KEY_MAX_VALUE) {
            for (int i = 0; i < size; i++) {
                crc ^= buffer.readByte();
            }
        } else {
            for (int i = 0; i < size; i++) {
                crc ^= buffer.getByte(i);
                buffer.setByte(i, buffer.getByte(i) ^ sendKey);
            }
        }
        buffer.resetReaderIndex();
        out.writeIntLE(size);
        out.writeShortLE(packetId);
        out.writeByte(crc);
        out.writeIntLE(0xFFFFFFFF);
        out.writeIntLE(0xFFFFFFFF);
        out.writeBytes(buffer);
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        if (client != null) {
            logger.error("Error in outbound netty pipeline of player '{0}'", cause, client);
        }
        ctx.close();
    }

}
