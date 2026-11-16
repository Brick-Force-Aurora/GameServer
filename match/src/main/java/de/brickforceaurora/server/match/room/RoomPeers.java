package de.brickforceaurora.server.match.room;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.rendezvous.PeerDirectory;

public final class RoomPeers implements PeerDirectory {

    @Override
    public List<BFClient> peersOf(final BFClient client) {
        final Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            return List.of();
        }
        final List<BFClient> peers = new ArrayList<>(room.clients());
        peers.remove(client);
        return peers;
    }

    @Override
    public int slotOf(final BFClient client) {
        final Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? -1 : slot;
    }

}
