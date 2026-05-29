package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntPriorityQueue;

public final class RoomManager {

    private final Int2ObjectMap<Room> rooms = new Int2ObjectArrayMap<>();
    private final IntPriorityQueue freeIds = new IntArrayFIFOQueue();

    public Room createRoom(int type, String title, boolean locked, String password, int maxPlayers, int[] parameters) {
        if (parameters == null || parameters.length != 8) {
            throw new IllegalArgumentException("Room parameters must contain exactly 8 values.");
        }
        //TODO: Handle Parameters for each gamemode

        int id = newRoomId();

        Room room = new Room(id);
        room.type(RoomType.byId(type)).title(title).passwordLocked(locked).password(password).maxPlayers(maxPlayers).status(RoomStatus.WAITING);

        rooms.put(id, room);

        return room;
    }

    private final int newRoomId() {
        if (freeIds.isEmpty()) {
            return rooms.size();
        }
        return freeIds.dequeueInt();
    }
}
