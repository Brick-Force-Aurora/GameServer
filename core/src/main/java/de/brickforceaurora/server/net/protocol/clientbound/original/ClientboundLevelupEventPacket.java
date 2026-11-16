package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundLevelupEventPacket implements IClientboundPacket {

    private byte eventType;
    private int clientId;
    private int xp;

    public ClientboundLevelupEventPacket eventType(final byte eventType) {
        this.eventType = eventType;
        return this;
    }

    public byte eventType() {
        return this.eventType;
    }

    public ClientboundLevelupEventPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundLevelupEventPacket xp(final int xp) {
        this.xp = xp;
        return this;
    }

    public int xp() {
        return this.xp;
    }

    @Override
    public int packetId() {
        return 152;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeByte(this.eventType);
        buf.writeInt(this.clientId);
        if (this.eventType == (byte) 0) { //write xp for friend or clan level up
            buf.writeInt(this.xp);
        }
    }
}
