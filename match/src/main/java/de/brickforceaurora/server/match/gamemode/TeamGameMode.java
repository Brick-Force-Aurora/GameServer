package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.net.protocol.data.RoomType;
import me.lauriichan.snowframe.SnowFrame;

public abstract class TeamGameMode<D extends TeamGameData> extends GameMode<D> {

    public TeamGameMode(final SnowFrame<?> snowFrame, final Class<D> dataType, final RoomType roomType) {
        super(snowFrame, dataType, roomType);
    }

}
