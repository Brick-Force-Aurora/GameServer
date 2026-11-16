package de.brickforceaurora.server.match.room;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDelRoomPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLeavePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMasterPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundSetStatusPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUpdateRoomPacket;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntPriorityQueue;

public final class RoomManager {

    private static final IRoomInfo[] EMPTY_ROOM_INFOS = new IRoomInfo[0];

    private final Int2ObjectMap<Room> rooms = new Int2ObjectArrayMap<>();
    private final IntPriorityQueue freeIds = new IntArrayFIFOQueue();

    private final AtomicReference<IRoomInfo[]> roomInfos = new AtomicReference<>(EMPTY_ROOM_INFOS);

    private NetManager<?> netManager;

    void setNetManager(NetManager<?> netManager) {
        this.netManager = netManager;
    }

    public IRoomInfo[] roomInfos() {
        return roomInfos.get();
    }

    public synchronized Room createRoom(BFClient creator, Consumer<Room> setup) {
        Room room = new Room(newRoomId());
        try {
            setup.accept(room);
        } catch (RuntimeException exception) {
            freeIds.enqueue(room.id());
            throw exception;
        }
        rooms.put(room.id(), room);
        roomInfos.set(rooms.values().toArray(IRoomInfo[]::new));
        if (!enterRoom(creator, room)) {
            deleteRoom(room);
            return null;
        }
        return room;
    }

    public synchronized Room roomById(int roomId) {
        return rooms.get(roomId);
    }

    public void updateRoom(Room room) {
        if (netManager != null) {
            netManager.broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        }
    }

    public synchronized boolean enterRoom(BFClient client, Room room) {
        Room currentRoom = client.attrUnset(Room.ATTR_ROOM, Room.class);
        if (currentRoom != null) {
            leaveRoomInternal(client, currentRoom);
        }
        return enterRoomInternal(client, room);
    }

    private boolean enterRoomInternal(BFClient client, Room room) {
        GameMode<?> gameMode = room.mode();
        if (gameMode == null || rooms.get(room.id()) != room) {
            return false;
        }

        // The slot has to be taken and the client has to join before anyone else can look for a free slot
        synchronized (room) {
            // The slot is how the clients address each other
            int slot = room.freeSlot();
            if (slot < 0) {
                // Room is full
                return false;
            }
            client.attrSet(Room.ATTR_SLOT, slot);

            // Set current room first
            client.attrSet(Room.ATTR_ROOM, room);

            try {
                gameMode.handleRoomJoin(netManager, room, client);
            } catch (RuntimeException exception) {
                client.attrUnset(Room.ATTR_ROOM);
                client.attrUnset(Room.ATTR_SLOT);
                throw exception;
            }

            // Join last and send packets first
            room.clients().add(client);
        }

        // The room list of the other players shows the player count
        updateRoom(room);
        return true;
    }

    public synchronized boolean leaveRoom(BFClient client) {
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
        client.attrUnset(Room.ATTR_SLOT);

        if (room.players() == 0) {
            deleteRoom(room);
            return;
        }

        room.mode().handleRoomLeave(room, client);

        int clientId = client.id();
        room.broadcast(new IClientboundPacket[] {
            new ClientboundSetStatusPacket().clientId(clientId).status(ClientStatus.WAITING),
            new ClientboundLeavePacket().clientId(clientId),
        });

        if (clientId == room.masterId()) {
            changeMaster(room, room.clients().getFirst().id());
        }
        updateRoom(room);
    }

    public synchronized boolean changeMaster(Room room, int newMasterId) {
        if (room.clientById(newMasterId) == null) {
            return false;
        }
        room.masterId(newMasterId);
        room.broadcast(new ClientboundMasterPacket().ownerClientId(newMasterId));
        // Everything that depends on the master has to be sent again, like the room password
        room.mode().handleMasterChange(room);
        return true;
    }

    public boolean deleteRoom(Room room) {
        if (room == null) {
            return false;
        }
        return deleteRoomById(room.id());
    }

    public synchronized boolean deleteRoomById(int id) {
        Room removed = rooms.remove(id);
        if (removed != null) {
            roomInfos.set(rooms.values().toArray(IRoomInfo[]::new));
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
            int id = rooms.size();
            while (rooms.containsKey(id)) {
                id++;
            }
            return id;
        }
        return freeIds.dequeueInt();
    }

}
