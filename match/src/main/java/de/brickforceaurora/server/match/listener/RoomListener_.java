package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.gamemode.GameModeManager;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraNotificationPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundLeavePacket;
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
            // TODO: NO ROOM?????
            return;
        }
        if (room.type() != context.packet().type()) {
            GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
            if (mode == null) {
                context.client().send(new ClientboundAuroraNotificationPacket()
                    .message("The game mode '%s' is not yet supported".formatted(context.packet().type().modeName())));
                return;
            }
            room.mode(mode);
        }
        room.mode().handleRoomUpdate(room, context);
    }

    @PacketHandler
    public void onRoomCreate(final NetContext<ServerboundCreateRoomPacket> context) {
        Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
        if (room != null) {
            context.client()
                .send(new ClientboundAuroraNotificationPacket().message("Please leave your room first before creating a new one."));
            return;
        }
        GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
        if (mode == null) {
            context.client().send(new ClientboundAuroraNotificationPacket()
                .message("The game mode '%s' is not yet supported".formatted(context.packet().type().modeName())));
            return;
        }
        room = roomManager.newRoom();
        room.mode(mode);
        room.status(RoomStatus.WAITING);
        room.title(context.packet().title());
        room.maxPlayers(context.packet().maxPlayers());
        context.client().attrSet(Room.ATTR_ROOM, room);
        mode.handleRoomCreation(room, context);
    }

    @PacketHandler
    public void onLeaveRoom(final NetContext<ServerboundLeavePacket> context) {
        Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
        if (room != null) {
            context.client()
                    .send(new ClientboundAuroraNotificationPacket().message("Please leave your room first before creating a new one."));
            return;
        }
        GameMode<?> mode = gameModeManager.modeByType(room.type());
        if (mode == null) {
            context.client().send(new ClientboundAuroraNotificationPacket()
                    .message("The game mode '%s' is not yet supported".formatted(room.type().modeName())));
            return;
        }
        mode.handleLeaveRoom(room, context);
    }
}
