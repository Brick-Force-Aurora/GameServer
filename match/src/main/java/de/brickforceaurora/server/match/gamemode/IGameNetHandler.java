package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.IPacket;

@FunctionalInterface
public interface IGameNetHandler<D extends GameData, P extends IPacket> {

    void onPacket(Room room, D gameData, NetContext<P> context) throws Throwable;

}
