package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.DamageEntry;

public final class ServerboundInflictedDamagePacket implements IServerboundPacket {

    private DamageEntry[] damage = {};

    public ServerboundInflictedDamagePacket damage(final DamageEntry[] damage) {
        this.damage = damage;
        return this;
    }

    public DamageEntry[] damage() {
        return this.damage;
    }

    @Override
    public int packetId() {
        return 399;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.damage = buf.readArray(DamageEntry[]::new, DamageEntry::fromBuffer);
    }
}
