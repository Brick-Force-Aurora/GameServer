package de.brickforceaurora.server.match.game;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.protocol.IPacket;

@FunctionalInterface
public interface IGameNetHandler<D extends GameData, P extends IPacket> {

    void onPacket(Room room, D gameData, NetContext<P> context) throws Throwable;

}
