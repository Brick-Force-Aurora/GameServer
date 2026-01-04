package de.brickforceaurora.gameserver.net.protocol;

import java.util.List;

import de.brickforceaurora.gameserver.net.BFClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public final class BFChannelEncoder extends MessageToMessageEncoder<IClientboundPacket> {

    private final BFClient client;
    private final byte sendKey;

    public BFChannelEncoder(final BFClient client) {
        this(client, ProtocolConstant.DEFAULT_SEND_KEY);
    }

    public BFChannelEncoder(final BFClient client, final byte sendKey) {
        this.client = client;
        this.sendKey = sendKey;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IClientboundPacket msg, List<Object> out) throws Exception {
        ByteBuf bodyBuffer = Unpooled.directBuffer();
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

        ByteBuf outputBuffer = Unpooled.buffer(ProtocolConstant.HEADER_SIZE + body.length);
        outputBuffer.writeIntLE(body.length);
        outputBuffer.writeShortLE(msg.packetId());
        outputBuffer.writeByte(crc);
        outputBuffer.writeIntLE(0xFFFFFFFF);
        outputBuffer.writeIntLE(0xFFFFFFFF);
        outputBuffer.writeBytes(body);
        out.add(outputBuffer);
    }

}
