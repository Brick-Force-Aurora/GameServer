package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IPacket;
import de.brickforceaurora.server.net.protocol.PacketRegistry;

public final class NetHandler<P extends IPacket> {

    private final int packetId;
    private final INetHandler<P> handler;

    public NetHandler(final Class<P> packetType, final INetHandler<P> handler) {
        this.packetId = PacketRegistry.packetIdByType(packetType);
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
