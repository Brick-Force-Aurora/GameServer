package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDelRoomPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLeavePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMasterPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundSetStatusPacket;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntPriorityQueue;

public final class RoomManager {

    private final Int2ObjectMap<Room> rooms = new Int2ObjectArrayMap<>();
    private final IntPriorityQueue freeIds = new IntArrayFIFOQueue();

    private NetManager<?> netManager;

    void setNetManager(NetManager<?> netManager) {
        this.netManager = netManager;
    }

    public Room newRoom() {
        Room room = new Room(newRoomId());
        rooms.put(room.id(), room);
        return room;
    }

    public Room roomById(int roomId) {
        return rooms.get(roomId);
    }

    public boolean enterRoom(BFClient client, Room room) {
        Room currentRoom = client.attrUnset(Room.ATTR_ROOM, Room.class);
        if (currentRoom != null) {
            leaveRoomInternal(client, currentRoom);
        }
        return enterRoomInternal(client, room);
    }

    private boolean enterRoomInternal(BFClient client, Room room) {
        GameMode<?> gameMode = room.mode();
        if (gameMode == null) {
            return false;
        }
        
        // Set current room first
        client.attrSet(Room.ATTR_ROOM, room);
        
        gameMode.handleRoomJoin(netManager, room, client);

        // Join last and send packets first
        room.clients().add(client);
        
        return true;
    }

    public boolean leaveRoom(BFClient client) {
        Room currentRoom = client.attrUnset(Room.ATTR_ROOM, Room.class);
        if (currentRoom == null) {
            // Was not in a room, we don't care
            return false;
        }
        leaveRoomInternal(client, currentRoom);
        return true;
    }

    private void leaveRoomInternal(BFClient client, Room room) {
        // Leave first then send packets
        room.clients().remove(client);

        if (room.players() == 0) {
            deleteRoom(room);
            return;
        }

        int clientId = client.id();
        room.broadcast(new IClientboundPacket[] {
            new ClientboundSetStatusPacket().clientId(clientId).status(ClientStatus.WAITING),
            new ClientboundLeavePacket().clientId(clientId),
        });

        if (clientId == room.masterId()) {
            int newMasterId = room.clients().getFirst().id();
            room.masterId(newMasterId);
            room.broadcast(new ClientboundMasterPacket().ownerClientId(newMasterId));
        }
    }

    public boolean deleteRoom(Room room) {
        if (room == null) {
            return false;
        }
        return deleteRoomById(room.id());
    }

    public boolean deleteRoomById(int id) {
        Room removed = rooms.remove(id);
        if (removed != null) {
            freeIds.enqueue(id);
            if (netManager != null) {
                netManager.broadcast(new ClientboundDelRoomPacket().roomId(id));
            }
            return true;
        }
        return false;
    }

    private final int newRoomId() {
        if (freeIds.isEmpty()) {
            return rooms.size();
        }
        return freeIds.dequeueInt();
    }

}
