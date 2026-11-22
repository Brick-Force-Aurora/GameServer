package de.brickforceaurora.server.net.rendezvous.protocol;

import java.net.InetSocketAddress;

import de.brickforceaurora.server.net.protocol.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;

public final class P2PMessage extends DefaultByteBufHolder {

    private final InetSocketAddress sender;
    private final P2PHeader header;
    private final IPacket packet;

    public P2PMessage(final ByteBuf data, final InetSocketAddress sender, final P2PHeader header, final IPacket packet) {
        super(data);
        this.sender = sender;
        this.header = header;
        this.packet = packet;
    }

    public InetSocketAddress sender() {
        return sender;
    }

    public P2PHeader header() {
        return header;
    }

    public IPacket packet() {
        return packet;
    }

    @Override
    public P2PMessage replace(final ByteBuf content) {
        return new P2PMessage(content, sender, header, packet);
    }

}
