package de.brickforceaurora.server.net.udp;

import java.util.List;

import de.brickforceaurora.server.net.tcp.BFClient;

public interface PeerDirectory {
    List<BFClient> peersOf(BFClient client);

    int slotOf(BFClient client);
}
