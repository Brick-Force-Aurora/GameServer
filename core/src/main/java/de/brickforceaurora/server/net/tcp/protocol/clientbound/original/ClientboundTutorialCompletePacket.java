package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundTutorialCompletePacket implements IClientboundPacket {

    private boolean val;
    private byte val2;

    public ClientboundTutorialCompletePacket val(final boolean val) {
        this.val = val;
        return this;
    }

    public boolean val() {
        return this.val;
    }

    public ClientboundTutorialCompletePacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 171;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeBoolean(this.val);
        buf.writeByte(this.val2);
    }
}