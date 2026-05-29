package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.IPacket;
import de.brickforceaurora.server.net.protocol.PacketRegistry;

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

    void handle(final Room room, D gameData, final NetContext<P> context) {
        try {
            handler.onPacket(room, gameData, context);
        } catch (final Throwable e) {
            context.manager().logger().error("Failed to run packet listener for packet '{0}' with id {1})", e,
                context.packet().packetName(), packetId);
        }
    }

}
