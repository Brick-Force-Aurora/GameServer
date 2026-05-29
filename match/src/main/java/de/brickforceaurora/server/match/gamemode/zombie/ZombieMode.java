package de.brickforceaurora.server.match.gamemode.zombie;

import de.brickforceaurora.server.match.gamemode.GameData;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ZombieMode extends GameMode<ZombieData> {

    public ZombieMode(final SnowFrame<?> snowFrame) {
        super(snowFrame, ZombieData.class, RoomType.ZOMBIE);
    }

    @Override
    public GameData createGameDataFor(Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleRoomCreation(Room room, NetContext<ServerboundCreateRoomPacket> context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleRoomUpdate(Room room, NetContext<ServerboundRoomConfigPacket> context) {
        // TODO Auto-generated method stub
        
    }

}
