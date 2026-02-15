package de.brickforceaurora.server.net.protocol;

import de.brickforceaurora.server.net.BFClient;
import io.netty.buffer.ByteBuf;
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
    protected void encode(final ChannelHandlerContext ctx, final IClientboundPacket msg, final ByteBuf out) throws Exception {
        byte[] body;
        int bodyOffset, bodyLength;
        try (PacketBuf packetBuf = new PacketBuf()) {
            if (msg.requiresClientId()) {
                packetBuf.writeInt(client.id());
            }
            msg.write(packetBuf);
            ByteBuf nettyBuf = packetBuf.buffer();
            body = nettyBuf.array();
            bodyOffset = nettyBuf.arrayOffset();
            bodyLength = nettyBuf.readableBytes();
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

        out.writeIntLE(body.length);
        out.writeShortLE(msg.packetId());
        out.writeByte(crc);
        out.writeIntLE(0xFFFFFFFF);
        out.writeIntLE(0xFFFFFFFF);
        out.writeBytes(body, bodyOffset, bodyLength);
        logger.debug("Sent to client {0}: {1} ({2})", client, msg.packetName(), msg.packetId());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Error in outbound netty pipeline of player '{0}'", cause, client);
        ctx.close();
    }

}
