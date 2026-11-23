package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundWeaponChangePacket implements IClientboundPacket {

    private int result;
    private int cost;
    private long item;

    public ClientboundWeaponChangePacket result(final int result) {
        this.result = result;
        return this;
    }

    public int result() {
        return this.result;
    }

    public ClientboundWeaponChangePacket cost(final int cost) {
        this.cost = cost;
        return this;
    }

    public int cost() {
        return this.cost;
    }

    public ClientboundWeaponChangePacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    @Override
    public int packetId() {
        return 415;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.result);
        buf.writeInt(this.cost);
        buf.writeLong(this.item);
    }
}