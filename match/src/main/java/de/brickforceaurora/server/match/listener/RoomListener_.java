package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.gamemode.GameModeManager;
import de.brickforceaurora.server.match.gamemode.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraNotificationPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.RoomInfo;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;
import de.brickforceaurora.server.net.protocol.serverbound.original.*;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;

import java.util.Collection;

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
        room.addClient(context.client());
        room.masterId(context.client().id());
        mode.handleRoomCreation(room, context);
    }

    @PacketHandler
    public void onLeaveRoom(final NetContext<ServerboundLeavePacket> context) {
        BFClient client = context.client();
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            return;
        }

        room.removeClient(client);

        client.send(new ClientboundLeavePacket().clientId(client.id()));
        client.send(new ClientboundSetStatusPacket().clientId(client.id()).status(ClientStatus.WAITING.id()));

        if (room.players() <= 0) {
            roomManager.deleteRoom(room);
            client.send(new ClientboundDelRoomPacket().roomId(room.id()));
            return;
        }

        if (client.id() == room.masterId()) {
            BFClient newMaster = room.clients().getFirst();
            room.masterId(newMaster.id());

            client.send(new ClientboundMasterPacket().ownerClientId(newMaster.id()));
        }
    }

    @PacketHandler
    public void onDelegateMasterRequest(final NetContext<ServerboundDelegateMasterPacket> context) {
        BFClient client = context.client();
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null || client.id() != room.masterId()) {
            return;
        }

        int newMasterId = context.packet().newMaster();

        boolean newMasterExists = room.clients().stream()
                .anyMatch(c -> c.id() == newMasterId);

        if (!newMasterExists) {
            return;
        }
        room.masterId(newMasterId);
        client.send(new ClientboundMasterPacket().ownerClientId(newMasterId));
    }

    @PacketHandler
    public void onTeamChange(final NetContext<ServerboundTeamChangePacket> context) {
        BFClient client = context.client();
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            return;
        }

        if (!(room.mode() instanceof TeamGameMode teamMode)) {
            client.send(new ClientboundAuroraNotificationPacket()
                    .message("Team change is not supported in this game mode."));
            return;
        }

        teamMode.handleTeamChange(room, context);
    }
}
