package de.brickforceaurora.server.net.udp.protocol.serverbound;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

public final class ServerboundP2PStatusPacket implements IServerboundPacket {

    private int clientId;
    private final IntSet relayedPeers = new IntOpenHashSet();

    @Override
    public int packetId() {
        return 52;
    }

    public int clientId() {
        return clientId;
    }

    public IntSet relayedPeers() {
        return relayedPeers;
    }

    @Override
    public void read(final PacketBuf buffer) {
        clientId = buffer.readInt();
        final int count = buffer.readInt();
        relayedPeers.clear();
        for (int i = 0; i < count; i++) {
            final int peer = buffer.readInt();
            if (buffer.readBoolean()) {
                relayedPeers.add(peer);
            }
        }
    }

    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
