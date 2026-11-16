package de.brickforceaurora.server.net.rendezvous;

import java.util.List;

import de.brickforceaurora.server.net.BFClient;

public interface PeerDirectory {
    List<BFClient> peersOf(BFClient client);

    int slotOf(BFClient client);
}
