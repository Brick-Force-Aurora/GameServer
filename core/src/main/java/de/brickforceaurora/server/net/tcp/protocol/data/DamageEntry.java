package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public record DamageEntry(int clientId, int damage) {

    public static DamageEntry fromBuffer(final PacketBuf buf) {
        return new DamageEntry(buf.readInt(), buf.readInt());
    }

}
