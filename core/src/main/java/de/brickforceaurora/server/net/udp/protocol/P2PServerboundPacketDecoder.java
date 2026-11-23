package de.brickforceaurora.server.net.udp.protocol;

import java.io.IOException;
import java.util.List;

import de.brickforceaurora.server.net.tcp.protocol.IPacket;
import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.udp.P2PPacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import me.lauriichan.laylib.logger.ISimpleLogger;

public final class P2PServerboundPacketDecoder extends MessageToMessageDecoder<DatagramPacket> {

    private final ISimpleLogger logger;

    public P2PServerboundPacketDecoder(final ISimpleLogger logger) {
        this.logger = logger;
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final DatagramPacket datagram, final List<Object> out) throws Exception {
        final ByteBuf data = datagram.content();
        final P2PHeader header = P2PHeader.parse(data);
        if (header == null) {
            return;
        }
        // null if the server doesn't understand the packet, those are only relayed
        final IPacket packet = P2PPacketRegistry.create(header.id());
        if (packet instanceof final IServerboundPacket serverbound) {
            final byte[] messageBuffer = new byte[header.size()];
            data.getBytes(data.readerIndex() + P2PProtocolConstant.HEADER_SIZE, messageBuffer);
            try (PacketBuf packetBuf = new PacketBuf(messageBuffer)) {
                serverbound.read(packetBuf);
            } catch (final IOException | IndexOutOfBoundsException exception) {
                logger.debug("Received malformed P2P packet {0} ({1}) from {2}", packet.packetName(), packet.packetId(), datagram.sender());
                return;
            }
        }
        // The datagram is released after this method, the message releases the retained content
        out.add(new P2PMessage(data.retain(), datagram.sender(), header, packet));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        logger.error("Error in inbound netty pipeline of the rendezvous server", cause);
    }

}
