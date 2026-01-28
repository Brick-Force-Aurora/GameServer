package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeBoolean(this.val2);
    }
}