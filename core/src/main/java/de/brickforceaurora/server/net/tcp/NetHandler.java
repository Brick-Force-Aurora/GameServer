package de.brickforceaurora.server.net.tcp;

import de.brickforceaurora.server.net.tcp.protocol.PacketRegistry;
import de.brickforceaurora.server.net.tcp.protocol.IPacket;

public final class NetHandler<P extends IPacket> {

    private final int packetId;
    private final INetHandler<P> handler;

    public NetHandler(final Class<P> packetType, final INetHandler<P> handler) {
        this(PacketRegistry.packetIdByType(packetType), handler);
    }

    public NetHandler(final int packetId, final INetHandler<P> handler) {
        this.packetId = packetId;
        this.handler = handler;
    }

    public int packetId() {
        return packetId;
    }

    void handle(final NetContext<P> context) {
        try {
            handler.onPacket(context);
        } catch (final Throwable e) {
            context.manager().logger().error("Failed to run packet listener for packet '{0}' with id {1})", e,
                context.packet().packetName(), packetId);
        }
    }

}
