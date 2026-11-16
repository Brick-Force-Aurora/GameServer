package de.brickforceaurora.server.match.game.mode.zombie;

import de.brickforceaurora.server.match.game.GameData;
import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import me.lauriichan.snowframe.SnowFrame;

// TODO: @Extension
public class ZombieMode extends GameMode<ZombieData> {

    public ZombieMode(final SnowFrame<?> snowFrame) {
        super(snowFrame, ZombieData.class, RoomType.ZOMBIE);
    }

    @Override
    public GameData createGameDataFor(final Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleRoomCreation(final Room room, final NetContext<ServerboundCreateRoomPacket> context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleRoomJoin(final NetManager<?> netManager, final Room room, final BFClient client) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleRoomUpdate(final Room room, final NetContext<ServerboundRoomConfigPacket> context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleRoomLeave(final Room room, final BFClient client) {
        // TODO Auto-generated method stub

    }

}
