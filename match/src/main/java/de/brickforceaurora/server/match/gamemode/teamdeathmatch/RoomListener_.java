package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.GameTarget;
import de.brickforceaurora.server.match.gamemode.IGameNetListener;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLoadCompletePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLoadStartPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMatchCountdownPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPToPCompletePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRespawnTicketPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundStartPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundStartingPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUpdateRoomPacket;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundAcceptApplicantPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundLoadCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMatchCountdownPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundPToPCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRespawnTicketPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundResumeRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundStartPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(TeamDeathMatchMode.class)
public class RoomListener_ implements IGameNetListener<TDMData> {

    private static final int START_SUCCESS = 0, START_ONLY_MASTER = -2;

    private final TeamDeathMatchMode mode;

    public RoomListener_(TeamDeathMatchMode mode) {
        this.mode = mode;
    }

    @PacketHandler
    public void handleMyExamplePacket(Room room, TDMData data, NetContext<ServerboundAcceptApplicantPacket> context) {

    }

    @PacketHandler
    public void onStart(Room room, TDMData data, NetContext<ServerboundStartPacket> context) {
        BFClient client = context.client();
        if (client.id() != room.masterId()) {
            client.send(new ClientboundStartPacket().result(START_ONLY_MASTER));
            return;
        }
        if (room.status() != RoomStatus.WAITING) {
            // Already started
            return;
        }
        int remain = context.packet().remain();
        if (remain != 0) {
            // Countdown tick or cancel (negative)
            room.broadcast(new ClientboundStartingPacket().remain(remain < 0 ? -1 : remain));
            return;
        }
        room.status(RoomStatus.PLAYING);
        room.broadcast(new ClientboundStartingPacket().remain(0), new ClientboundStartPacket().result(START_SUCCESS));
        // Everyone waits for each other
        data.resetTickets();
        for (BFClient member : room.clients().toArray(new BFClient[0])) {
            room.broadcast(new ClientboundLoadStartPacket().clientId(member.id()));
            member.send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(member))));
        }
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        context.manager().logger().info("Room {0} ({1}) of {2} started with {3} player(s)", room.id(), room.title(), client,
            room.players());
    }

    @PacketHandler
    public void onRespawnTicket(Room room, TDMData data, NetContext<ServerboundRespawnTicketPacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        context.client().send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(context.client()))));
    }

    private static int slotOf(BFClient client) {
        Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? 0 : slot;
    }

    @PacketHandler
    public void onLoadComplete(Room room, TDMData data, NetContext<ServerboundLoadCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundLoadCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onPeerToPeerComplete(Room room, TDMData data, NetContext<ServerboundPToPCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundPToPCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onMatchCountdown(Room room, TDMData data, NetContext<ServerboundMatchCountdownPacket> context) {
        if (room.status() != RoomStatus.PLAYING || context.client().id() != room.masterId()) {
            return;
        }
        room.broadcast(new ClientboundMatchCountdownPacket().count(context.packet().count()));
    }

    @PacketHandler
    public void onResumeRoom(Room room, TDMData data, NetContext<ServerboundResumeRoomPacket> context) {
        if (context.client().id() != room.masterId()) {
            return;
        }
        RoomStatus status = RoomStatus.byId(context.packet().nextStatus());
        if (status == null || status == room.status()) {
            return;
        }
        room.status(status);
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

}
