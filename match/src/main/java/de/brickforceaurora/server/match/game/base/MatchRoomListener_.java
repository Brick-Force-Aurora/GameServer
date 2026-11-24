package de.brickforceaurora.server.match.game.base;

import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameTarget;
import de.brickforceaurora.server.match.game.IGameNetListener;
import de.brickforceaurora.server.match.game.MatchGameData;
import de.brickforceaurora.server.match.game.TeamGameData;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.match.util.TextFormatter;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundChatPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundKickPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundSetStatusPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundChatPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundKickPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundMakeRoomNullPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundSetStatusPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundTeamChatPacket;
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

    @PacketHandler
    public void onChat(final Room room, final MatchGameData data, final NetContext<ServerboundChatPacket> context) {
        final String text = TextFormatter.clean(context.packet().text());
        if (text == null) {
            return;
        }
        room.broadcast(chatPacket(context.client(), (byte) 0, text));
    }

    @PacketHandler
    public void onTeamChat(final Room room, final MatchGameData data, final NetContext<ServerboundTeamChatPacket> context) {
        final BFClient client = context.client();
        final String text = TextFormatter.clean(context.packet().text());
        if (text == null) {
            return;
        }
        sendToTeam(room, client, chatPacket(client, (byte) 4, text));
    }

    /*
     * HELPER
     */

    private static ClientboundChatPacket chatPacket(final BFClient client, final byte type, final String text) {
        return new ClientboundChatPacket().clientId(client.id()).chatType(type).nickname(client.name()).text(text).gameMaster(false);
    }

    private static void sendToTeam(final Room room, final BFClient client, final IClientboundPacket packet) {
        final boolean teams = room.gameData() instanceof TeamGameData;
        final boolean red = TeamGameData.isRed(slotOf(client));
        for (final BFClient member : room.clients()) {
            if (!teams || TeamGameData.isRed(slotOf(member)) == red) {
                member.send(packet);
            }
        }
    }

    private static int slotOf(final BFClient client) {
        final Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? 0 : slot;
    }

}
