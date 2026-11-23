package de.brickforceaurora.server.match.game;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.protocol.PacketRegistry;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.protocol.IPacket;

public final class GameNetHandler<D extends GameData, P extends IPacket> {

    private final int packetId;
    private final IGameNetHandler<D, P> handler;

    public GameNetHandler(final Class<P> packetType, final IGameNetHandler<D, P> handler) {
        this.packetId = PacketRegistry.packetIdByType(packetType);
        this.handler = handler;
    }

    public int packetId() {
        return packetId;
    }

    void handle(final Room room, final D gameData, final NetContext<P> context) {
        try {
            handler.onPacket(room, gameData, context);
        } catch (final Throwable e) {
            context.manager().logger().error("Failed to run packet listener for packet '{0}' with id {1})", e,
                context.packet().packetName(), packetId);
        }
    }

}
