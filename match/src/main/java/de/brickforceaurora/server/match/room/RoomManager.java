package de.brickforceaurora.server.match.room;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;
import it.unimi.dsi.fastutil.ints.IntPriorityQueue;

public final class RoomManager {

    private final Int2ObjectMap<Room> rooms = new Int2ObjectArrayMap<>();
    private final IntPriorityQueue freeIds = new IntArrayFIFOQueue();

    public Room newRoom() {
        Room room = new Room(newRoomId());
        rooms.put(room.id(), room);
        return room;
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
