package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IPacket;
import de.brickforceaurora.gameserver.net.protocol.PacketRegistry;

public final class NetHandler<P extends IPacket> {

    private final int packetId;
    private final INetHandler<P> handler;

    public NetHandler(Class<P> packetType, INetHandler<P> handler) {
        this.packetId = PacketRegistry.packetIdByType(packetType);
        this.handler = handler;
    }

    public final int packetId() {
        return packetId;
    }

    final void handle(NetContext<P> context) {
        try {
            handler.onPacket(context);
        } catch (Throwable e) {
            context.manager().logger().error("Failed to run packet listener for packet '{0}' with id {1})", e,
                context.packet().packetName(), packetId);
        }
    }

}
