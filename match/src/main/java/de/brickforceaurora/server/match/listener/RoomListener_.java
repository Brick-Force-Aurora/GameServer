package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.gamemode.GameModeManager;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomListPacket;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class RoomListener_ implements INetListener {

    private final RoomManager roomManager;
    private final GameModeManager gameModeManager;

    public RoomListener_(final SnowFrame<MatchServerApp> snowFrame) {
        roomManager = snowFrame.app().roomManager();
        gameModeManager = snowFrame.app().gameModeManager();
    }

    @PacketHandler
    public void onRoomList(final NetContext<ServerboundRoomListPacket> context) {
        context.client().send(new ClientboundRoomListPacket().rooms(new RoomInfo[] {}));
    }

    @PacketHandler
    public void onRoomConfig(final NetContext<ServerboundRoomConfigPacket> context) {
        Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            // NO ROOM?????
            return;
        }
        
        if (room.type() != context.packet().type()) {
            GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
            if (mode == null) {
                // TODO: SMTH???
                return;
            }
            room.mode(mode);
        }
        room.mode().handleRoomUpdate(room, context);
    }

    @PacketHandler
    public void onRoomCreate(final NetContext<ServerboundCreateRoomPacket> context) {
        GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
        if (mode == null) {
            // TODO: SMTH???
            return;
        }
        Room room = roomManager.newRoom();
        room.mode(mode);
        context.client().attrSet(Room.ATTR_ROOM, room);
        mode.handleRoomCreation(room, context);
    }
}
