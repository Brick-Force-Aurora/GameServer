package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameModeManager;
import de.brickforceaurora.server.match.game.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraNotificationPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.*;
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
        context.client().send(new ClientboundRoomListPacket().rooms(roomManager.roomInfos()));
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
            context.client().send(new ClientboundCreateRoomPacket().failure("The game mode '%s' is not yet supported".formatted(context.packet().type().modeName())));
            return;
        }
        roomManager.createRoom(context.client(), newRoom -> {
            newRoom.mode(mode);
            newRoom.status(RoomStatus.WAITING);
            newRoom.title(context.packet().title());
            newRoom.maxPlayers(context.packet().maxPlayers());
            newRoom.masterId(context.client().id());
            String password = context.packet().password();
            newRoom.password(password == null || password.isEmpty() ? null : password);
            mode.handleRoomCreation(newRoom, context);
        });
    }

    @PacketHandler
    public void onJoinRoom(final NetContext<ServerboundJoinPacket> context) {
        Room room = roomManager.roomById(context.packet().roomId());
        if (room == null) {
            context.client().send(new ClientboundJoinPacket().result(ClientboundJoinPacket.JoinResult.CANT_JOIN));
            return;
        }
        if (room.passwordLocked()) {
            String str = context.packet().password();
            if (!str.equals(room.password())) {
                context.client().send(new ClientboundJoinPacket().result(ClientboundJoinPacket.JoinResult.WRONG_PASSWORD));
                return;
            }
        }
        if (!roomManager.enterRoom(context.client(), room)) {
            context.client().send(new ClientboundJoinPacket().result(ClientboundJoinPacket.JoinResult.TOO_MANY_PLAYERS));
        }
    }

    @PacketHandler
    public void onLeaveRoom(final NetContext<ServerboundLeavePacket> context) {
        roomManager.leaveRoom(context.client());
    }

    @PacketHandler
    public void onDelegateMasterRequest(final NetContext<ServerboundDelegateMasterPacket> context) {
        BFClient client = context.client();
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null || client.id() != room.masterId()) {
            return;
        }

        int newMasterId = context.packet().newMaster();

        roomManager.changeMaster(room, newMasterId);
    }

    @PacketHandler
    public void onTeamChange(final NetContext<ServerboundTeamChangePacket> context) {
        BFClient client = context.client();
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            // The client waits for an answer
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }

        if (!(room.mode() instanceof TeamGameMode<?> teamMode)) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }

        teamMode.handleTeamChange(room, context);
    }
}
