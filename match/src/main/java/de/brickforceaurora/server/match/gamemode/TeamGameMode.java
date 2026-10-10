package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundResumeRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import me.lauriichan.snowframe.SnowFrame;

public abstract class TeamGameMode<D extends TeamGameData> extends GameMode<D> {

    public TeamGameMode(final SnowFrame<?> snowFrame, final Class<D> dataType, final RoomType roomType) {
        super(snowFrame, dataType, roomType);
    }

    public abstract void handleTeamChange(Room room, final NetContext<ServerboundResumeRoomPacket> context);

}
