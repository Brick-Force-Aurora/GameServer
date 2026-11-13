package de.brickforceaurora.server.net.rendezvous;

import de.brickforceaurora.server.net.BFClient;

import java.util.List;

public interface PeerDirectory {
    List<BFClient> peersOf(BFClient client);
    int slotOf(BFClient client);
}
