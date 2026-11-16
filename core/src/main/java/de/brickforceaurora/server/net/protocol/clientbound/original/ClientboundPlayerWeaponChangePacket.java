package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPlayerWeaponChangePacket implements IClientboundPacket {

    private int clientId;
    private String prev;
    private String next;

    public ClientboundPlayerWeaponChangePacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundPlayerWeaponChangePacket prev(final String prev) {
        this.prev = prev;
        return this;
    }

    public String prev() {
        return this.prev;
    }

    public ClientboundPlayerWeaponChangePacket next(final String next) {
        this.next = next;
        return this;
    }

    public String next() {
        return this.next;
    }

    @Override
    public int packetId() {
        return 416;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeString(this.prev);
        buf.writeString(this.next);
    }
}