package de.brickforceaurora.server.match.game.base;

import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameTarget;
import de.brickforceaurora.server.match.game.IGameNetListener;
import de.brickforceaurora.server.match.game.MatchGameData;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundChgDoorStatusPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundEmptyCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundEmptyTrainPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundGadgetActionPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundGetCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundGetTrainPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundGotCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundCloseDoorPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundEmptyCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundEmptyTrainPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundGadgetActionPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundGetCannonPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundGetTrainPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundOpenDoorPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(value = GameMode.class, data = MatchGameData.class)
public class MapObjectListener_ implements IGameNetListener<MatchGameData> {
    @PacketHandler
    public void onOpenDoor(final Room room, final MatchGameData data, final NetContext<ServerboundOpenDoorPacket> context) {
        setDoor(room, data, context.client(), context.packet().door(), (byte) 1);
    }

    @PacketHandler
    public void onCloseDoor(final Room room, final MatchGameData data, final NetContext<ServerboundCloseDoorPacket> context) {
        setDoor(room, data, context.client(), context.packet().door(), (byte) 0);
    }

    @PacketHandler
    public void onGetCannon(final Room room, final MatchGameData data, final NetContext<ServerboundGetCannonPacket> context) {
        final BFClient client = context.client();
        if (!playing(room, data, client)) {
            return;
        }
        final int cannon = context.packet().cannon();
        final int shooter = data.occupyCannon(cannon, client.id());
        if (shooter == client.id()) {
            room.broadcast(new ClientboundGetCannonPacket().shooter(shooter).cannon(cannon));
        } else if (shooter != -1) {
            client.send(new ClientboundGotCannonPacket().shooter(shooter).cannon(cannon));
        }
    }

    @PacketHandler
    public void onEmptyCannon(final Room room, final MatchGameData data, final NetContext<ServerboundEmptyCannonPacket> context) {
        final BFClient client = context.client();
        final int cannon = context.packet().cannon();
        if (playing(room, data, client) && data.freeCannon(cannon, client.id())) {
            room.broadcast(new ClientboundEmptyCannonPacket().cannon(cannon));
        }
    }

    @PacketHandler
    public void onGetTrain(final Room room, final MatchGameData data, final NetContext<ServerboundGetTrainPacket> context) {
        final BFClient client = context.client();
        if (!playing(room, data, client)) {
            return;
        }
        final int train = context.packet().trainSeq();
        if (data.occupyTrain(train, client.id()) == client.id()) {
            room.broadcast(new ClientboundGetTrainPacket().shooter(client.id()).train(train));
        }
    }

    @PacketHandler
    public void onEmptyTrain(final Room room, final MatchGameData data, final NetContext<ServerboundEmptyTrainPacket> context) {
        final BFClient client = context.client();
        final int train = context.packet().trainSeq();
        if (playing(room, data, client) && data.freeTrain(train, client.id())) {
            room.broadcast(new ClientboundEmptyTrainPacket().train(train));
        }
    }

    @PacketHandler
    public void onGadgetAction(final Room room, final MatchGameData data, final NetContext<ServerboundGadgetActionPacket> context) {
        if (playing(room, data, context.client())) {
            room.broadcast(new ClientboundGadgetActionPacket().gadget(context.packet().gadget()).action(context.packet().action()));
        }
    }

    private static void setDoor(final Room room, final MatchGameData data, final BFClient client, final int door, final byte status) {
        if (!playing(room, data, client)) {
            return;
        }
        data.door(door, status == 1);
        room.broadcast(new ClientboundChgDoorStatusPacket().door(door).status(status));
    }

    private static boolean playing(final Room room, final MatchGameData data, final BFClient client) {
        return room.status() == RoomStatus.PLAYING && data.stats(client.id()) != null;
    }

}
