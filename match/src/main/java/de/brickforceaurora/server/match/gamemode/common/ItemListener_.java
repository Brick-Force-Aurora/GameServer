package de.brickforceaurora.server.match.gamemode.common;

import java.util.Arrays;
import java.util.Optional;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.protocol.data.DroppedItem;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.match.gamemode.GameTarget;
import de.brickforceaurora.server.match.gamemode.IGameNetListener;
import de.brickforceaurora.server.match.gamemode.MatchGameData;
import de.brickforceaurora.server.match.listener.ClientListener_;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDelDropItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDropItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPickupDroppedItemFailPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPickupDroppedItemPacket;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundDropItemPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundPickupDroppedItemPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundUseShooterConsumablePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundWeaponHeldRatioPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(value = GameMode.class, data = MatchGameData.class)
public class ItemListener_ implements IGameNetListener<MatchGameData> {

    @PacketHandler
    public void onDropItem(Room room, MatchGameData data, NetContext<ServerboundDropItemPacket> context) {
        ServerboundDropItemPacket packet = context.packet();
        String code = packet.itemCode();
        if (room.status() != RoomStatus.PLAYING || !data.drop() || data.stats(context.client().id()) == null || code == null
            || code.isEmpty() || code.length() > 16 || !Float.isFinite(packet.x()) || !Float.isFinite(packet.y())
            || !Float.isFinite(packet.z())) {
            return;
        }
        DroppedItem item = data.addDrop(code, packet.count(), packet.subCount(), packet.x(), packet.y(), packet.z());
        if (item == null) {
            return;
        }
        room.broadcast(dropPacket(item));
    }

    @PacketHandler
    public void onPickupDroppedItem(Room room, MatchGameData data, NetContext<ServerboundPickupDroppedItemPacket> context) {
        BFClient client = context.client();
        int itemSeq = context.packet().itemSeq();
        if (room.status() != RoomStatus.PLAYING || !data.drop() || data.stats(client.id()) == null) {
            return;
        }
        if (data.takeDrop(itemSeq) == null) {
            client.send(new ClientboundPickupDroppedItemFailPacket());
            return;
        }
        room.broadcast(new ClientboundPickupDroppedItemPacket().clientId(client.id()).itemSeq(itemSeq),
            new ClientboundDelDropItemPacket().itemSeq(itemSeq));
    }

    @PacketHandler
    public void onWeaponHeldRatio(Room room, MatchGameData data, NetContext<ServerboundWeaponHeldRatioPacket> context) {
        context.manager().logger().debug("{0} held its weapons: {1}", context.client(), Arrays.toString(context.packet().heldRatio()));
    }

    @PacketHandler
    public void onUseShooterConsumable(Room room, MatchGameData data, NetContext<ServerboundUseShooterConsumablePacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener_.ATTR_ACCOUNT_ID, Integer.class);
        if (room.status() != RoomStatus.PLAYING || accountId == null) {
            return;
        }
        ServerboundUseShooterConsumablePacket packet = context.packet();
        try {
            UserRepository users = new UserRepository(MatchServerApp.database());
            Optional<InventoryItem> found = users.loadInventory(accountId).stream()
                .filter(item -> item.id() == packet.item() && item.code().equals(packet.code())).findFirst();
            if (found.isEmpty()) {
                return;
            }
            InventoryItem item = found.get();
            if (item.amount() > 1) {
                users.updateItem(new InventoryItem(item.id(), item.accountId(), item.code(), item.usage(), item.remain(),
                    item.amount() - 1, item.premium(), item.durability(), item.toolSlot()));
            } else {
                users.removeItem(accountId, item.id());
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't use the consumable of {0}", exception, client);
        }
    }

    public static ClientboundDropItemPacket dropPacket(DroppedItem item) {
        return new ClientboundDropItemPacket().item(item);
    }

}
