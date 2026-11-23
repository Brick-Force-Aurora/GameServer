package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundWeaponDurabilityPacket implements IClientboundPacket {

    private long val;
    private int val2;

    public ClientboundWeaponDurabilityPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundWeaponDurabilityPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 350;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeLong(this.val);
        buf.writeInt(this.val2);
    }
}