package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.GameTarget;
import de.brickforceaurora.server.match.gamemode.IGameNetListener;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundAcceptApplicantPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(TeamDeathMatchMode.class)
public class RoomListener_ implements IGameNetListener<TDMData> {
    
    private final TeamDeathMatchMode mode;
    
    public RoomListener_(TeamDeathMatchMode mode) {
        this.mode = mode;
    }
    
    @PacketHandler
    public void handleMyExamplePacket(Room room, TDMData data, NetContext<ServerboundAcceptApplicantPacket> context) {
        
    }

}
