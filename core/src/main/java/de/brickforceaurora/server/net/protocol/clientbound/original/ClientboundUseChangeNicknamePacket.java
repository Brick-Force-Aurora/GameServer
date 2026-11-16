package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundUseChangeNicknamePacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String Unnamed0;

    public ClientboundUseChangeNicknamePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUseChangeNicknamePacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundUseChangeNicknamePacket Unnamed0(final String Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public String Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 502;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeString(this.val2);
        buf.writeString(this.Unnamed0);
    }
}