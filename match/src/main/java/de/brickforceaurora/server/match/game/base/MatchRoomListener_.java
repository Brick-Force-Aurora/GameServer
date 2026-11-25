package de.brickforceaurora.server.match.game.base;

import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameTarget;
import de.brickforceaurora.server.match.game.IGameNetListener;
import de.brickforceaurora.server.match.game.MatchGameData;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundKickPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundSetStatusPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundKickPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundMakeRoomNullPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundSetStatusPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(value = GameMode.class, data = MatchGameData.class)
public class MatchRoomListener_ implements IGameNetListener<MatchGameData> {

    private final RoomManager roomManager;

    public MatchRoomListener_(final RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    @PacketHandler
    public void onMakeRoomNull(final Room room, final MatchGameData data, final NetContext<ServerboundMakeRoomNullPacket> context) {
        roomManager.leaveRoom(context.client());
    }

    @PacketHandler
    public void onSetStatus(final Room room, final MatchGameData data, final NetContext<ServerboundSetStatusPacket> context) {
        final BFClient client = context.client();
        final ClientStatus status = context.packet().status();
        if (room.status() != RoomStatus.WAITING || status == ClientStatus.P2PING || status == ClientStatus.LOADING) {
            return;
        }
        client.attrSet(Room.ATTR_STATUS, status);
        room.broadcast(new ClientboundSetStatusPacket().clientId(client.id()).status(status));
    }

    @PacketHandler
    public void onKick(final Room room, final MatchGameData data, final NetContext<ServerboundKickPacket> context) {
        final BFClient client = context.client();
        if (client.id() != room.masterId()) {
            return;
        }
        final BFClient target = room.clientById(context.packet().clientId());
        if (target == null || target == client) {
            return;
        }
        room.broadcast(new ClientboundKickPacket().clientId(target.id()));
        roomManager.leaveRoom(target);
    }

}
