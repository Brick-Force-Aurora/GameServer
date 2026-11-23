package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameModeManager;
import de.brickforceaurora.server.match.game.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.INetListener;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraNotificationPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundCreateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundJoinPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundRoomListPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundTeamChangeFailPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundDelegateMasterPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundJoinPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundLeavePacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundRoomConfigPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundRoomListPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundTeamChangePacket;
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
        final Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            // TODO: NO ROOM?????
            return;
        }
        if (room.type() != context.packet().type()) {
            final GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
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
        final Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
        if (room != null) {
            context.client()
                .send(new ClientboundAuroraNotificationPacket().message("Please leave your room first before creating a new one."));
            return;
        }
        final GameMode<?> mode = gameModeManager.modeByType(context.packet().type());
        if (mode == null) {
            context.client().send(new ClientboundCreateRoomPacket()
                .failure("The game mode '%s' is not yet supported".formatted(context.packet().type().modeName())));
            return;
        }
        roomManager.createRoom(context.client(), newRoom -> {
            newRoom.mode(mode);
            newRoom.status(RoomStatus.WAITING);
            newRoom.title(context.packet().title());
            newRoom.maxPlayers(context.packet().maxPlayers());
            newRoom.masterId(context.client().id());
            final String password = context.packet().password();
            newRoom.password(password == null || password.isEmpty() ? null : password);
            mode.handleRoomCreation(newRoom, context);
        });
    }

    @PacketHandler
    public void onJoinRoom(final NetContext<ServerboundJoinPacket> context) {
        final Room room = roomManager.roomById(context.packet().roomId());
        if (room == null) {
            context.client().send(new ClientboundJoinPacket().result(ClientboundJoinPacket.JoinResult.CANT_JOIN));
            return;
        }
        final RoomStatus status = room.status();
        if (status != RoomStatus.WAITING && !(status == RoomStatus.PLAYING && room.allowsLateJoining())) {
            context.client().send(new ClientboundJoinPacket()
                .result(status == RoomStatus.PENDING ? ClientboundJoinPacket.JoinResult.LOADING : ClientboundJoinPacket.JoinResult.CANT_JOIN));
            return;
        }
        if (room.passwordLocked()) {
            final String str = context.packet().password();
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
        final BFClient client = context.client();
        final Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null || client.id() != room.masterId()) {
            return;
        }

        final int newMasterId = context.packet().newMaster();

        roomManager.changeMaster(room, newMasterId);
    }

    @PacketHandler
    public void onTeamChange(final NetContext<ServerboundTeamChangePacket> context) {
        final BFClient client = context.client();
        final Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if ((room == null) || !(room.mode() instanceof final TeamGameMode<?> teamMode)) {
            client.send(new ClientboundTeamChangeFailPacket().reason(0));
            return;
        }

        teamMode.handleTeamChange(room, context);
    }
}
