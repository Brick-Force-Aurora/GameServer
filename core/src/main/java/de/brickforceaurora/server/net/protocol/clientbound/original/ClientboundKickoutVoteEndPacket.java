package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKickoutVoteEndPacket implements IClientboundPacket {

    private int result;
    private int target;
    private String targetNickname;

    public ClientboundKickoutVoteEndPacket result(final int result) {
        this.result = result;
        return this;
    }

    public int result() {
        return this.result;
    }

    public ClientboundKickoutVoteEndPacket target(final int target) {
        this.target = target;
        return this;
    }

    public int target() {
        return this.target;
    }

    public ClientboundKickoutVoteEndPacket targetNickname(final String targetNickname) {
        this.targetNickname = targetNickname;
        return this;
    }

    public String targetNickname() {
        return this.targetNickname;
    }

    @Override
    public int packetId() {
        return 499;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.result);
        buf.writeInt(this.target);
        buf.writeString(this.targetNickname);
    }
}
