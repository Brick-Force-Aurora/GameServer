package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMeChgEditorPacket implements IClientboundPacket {

    private int val;
    private boolean val2;

    public ClientboundMeChgEditorPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundMeChgEditorPacket val2(final boolean val2) {
        this.val2 = val2;
        return this;
    }

    public boolean val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 305;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeBoolean(this.val2);
    }
}