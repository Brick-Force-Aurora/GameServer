package de.brickforceaurora.server.match.gamemode.common;

import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.gamemode.GameTarget;
import de.brickforceaurora.server.match.gamemode.IGameNetListener;
import de.brickforceaurora.server.match.gamemode.MatchGameData;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundChgDoorStatusPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundEmptyCannonPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundEmptyTrainPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGadgetActionPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGetCannonPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGetTrainPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGotCannonPacket;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCloseDoorPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundEmptyCannonPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundEmptyTrainPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundGadgetActionPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundGetCannonPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundGetTrainPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundOpenDoorPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(value = GameMode.class, data = MatchGameData.class)
public class MapObjectListener_ implements IGameNetListener<MatchGameData> {
    @PacketHandler
    public void onOpenDoor(Room room, MatchGameData data, NetContext<ServerboundOpenDoorPacket> context) {
        setDoor(room, data, context.client(), context.packet().door(), (byte)1);
    }

    @PacketHandler
    public void onCloseDoor(Room room, MatchGameData data, NetContext<ServerboundCloseDoorPacket> context) {
        setDoor(room, data, context.client(), context.packet().door(), (byte)0);
    }

    @PacketHandler
    public void onGetCannon(Room room, MatchGameData data, NetContext<ServerboundGetCannonPacket> context) {
        BFClient client = context.client();
        if (!playing(room, data, client)) {
            return;
        }
        int cannon = context.packet().cannon();
        int shooter = data.occupyCannon(cannon, client.id());
        if (shooter == client.id()) {
            room.broadcast(new ClientboundGetCannonPacket().shooter(shooter).cannon(cannon));
        } else if (shooter != -1) {
            client.send(new ClientboundGotCannonPacket().shooter(shooter).cannon(cannon));
        }
    }

    @PacketHandler
    public void onEmptyCannon(Room room, MatchGameData data, NetContext<ServerboundEmptyCannonPacket> context) {
        BFClient client = context.client();
        int cannon = context.packet().cannon();
        if (playing(room, data, client) && data.freeCannon(cannon, client.id())) {
            room.broadcast(new ClientboundEmptyCannonPacket().cannon(cannon));
        }
    }

    @PacketHandler
    public void onGetTrain(Room room, MatchGameData data, NetContext<ServerboundGetTrainPacket> context) {
        BFClient client = context.client();
        if (!playing(room, data, client)) {
            return;
        }
        int train = context.packet().trainSeq();
        if (data.occupyTrain(train, client.id()) == client.id()) {
            room.broadcast(new ClientboundGetTrainPacket().shooter(client.id()).train(train));
        }
    }

    @PacketHandler
    public void onEmptyTrain(Room room, MatchGameData data, NetContext<ServerboundEmptyTrainPacket> context) {
        BFClient client = context.client();
        int train = context.packet().trainSeq();
        if (playing(room, data, client) && data.freeTrain(train, client.id())) {
            room.broadcast(new ClientboundEmptyTrainPacket().train(train));
        }
    }

    @PacketHandler
    public void onGadgetAction(Room room, MatchGameData data, NetContext<ServerboundGadgetActionPacket> context) {
        if (playing(room, data, context.client())) {
            room.broadcast(new ClientboundGadgetActionPacket().gadget(context.packet().gadget()).action(context.packet().action()));
        }
    }

    private static void setDoor(Room room, MatchGameData data, BFClient client, int door, byte status) {
        if (!playing(room, data, client)) {
            return;
        }
        data.door(door, status == 1);
        room.broadcast(new ClientboundChgDoorStatusPacket().door(door).status(status));
    }

    private static boolean playing(Room room, MatchGameData data, BFClient client) {
        return room.status() == RoomStatus.PLAYING && data.stats(client.id()) != null;
    }

}
