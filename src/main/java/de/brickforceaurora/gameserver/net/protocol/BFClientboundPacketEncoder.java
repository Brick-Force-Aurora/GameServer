package de.brickforceaurora.gameserver.net.protocol;

import de.brickforceaurora.gameserver.net.BFClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class BFClientboundPacketEncoder extends MessageToByteEncoder<IClientboundPacket> {

    private final BFClient client;
    private final byte sendKey;

    public BFClientboundPacketEncoder(final BFClient client) {
        this(client, ProtocolConstant.DEFAULT_SEND_KEY);
    }

    public BFClientboundPacketEncoder(final BFClient client, final byte sendKey) {
        this.client = client;
        this.sendKey = sendKey;
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final IClientboundPacket msg, final ByteBuf out) throws Exception {
        final ByteBuf bodyBuffer = Unpooled.buffer();
        byte[] body;
        try {
            if (msg.requiresClientId()) {
                bodyBuffer.writeIntLE(client.id());
            }
            msg.write(bodyBuffer);
            body = bodyBuffer.array();
        } finally {
            bodyBuffer.release();
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
        out.writeBytes(body);
    }

}
