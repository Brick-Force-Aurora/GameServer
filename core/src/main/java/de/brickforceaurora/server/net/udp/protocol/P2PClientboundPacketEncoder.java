package de.brickforceaurora.server.net.udp.protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class P2PClientboundPacketEncoder extends MessageToMessageEncoder<AddressedEnvelope<IClientboundPacket, InetSocketAddress>> {

    private final ISimpleLogger logger;

    public P2PClientboundPacketEncoder(final ISimpleLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean acceptOutboundMessage(final Object msg) throws Exception {
        // Datagrams are envelopes as well, they have a buffer as content
        return super.acceptOutboundMessage(msg) && ((AddressedEnvelope<?, ?>) msg).content() instanceof IClientboundPacket;
    }

    @Override
    protected void encode(final ChannelHandlerContext ctx, final AddressedEnvelope<IClientboundPacket, InetSocketAddress> msg,
        final List<Object> out) throws Exception {
        final IClientboundPacket packet = msg.content();
        try (PacketBuf packetBuf = new PacketBuf()) {
            try {
                packet.write(packetBuf);
            } catch (final IOException | RuntimeException exp) {
                logger.error("Failed to serialize {1} ({2}) to {0}", exp, msg.recipient(), packet.packetName(), packet.packetId());
                return;
            }
            final ByteBuf body = packetBuf.buffer();
            final int size = body.readableBytes();
            final ByteBuf buffer = ctx.alloc().buffer(P2PProtocolConstant.HEADER_SIZE + size);
            new P2PHeader(size, packet.packetId(), P2PHeader.checksum(body, body.readerIndex(), size), P2PProtocolConstant.NO_META,
                P2PProtocolConstant.BROADCAST, P2PProtocolConstant.BROADCAST).write(buffer);
            buffer.writeBytes(body, body.readerIndex(), size);
            out.add(new DatagramPacket(buffer, msg.recipient()));
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        logger.error("Error in outbound netty pipeline of the rendezvous server", cause);
    }

}
