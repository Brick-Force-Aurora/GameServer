package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public record DamageEntry(int clientId, int damage) {

    public static DamageEntry fromBuffer(PacketBuf buf) {
        return new DamageEntry(buf.readInt(), buf.readInt());
    }

}
