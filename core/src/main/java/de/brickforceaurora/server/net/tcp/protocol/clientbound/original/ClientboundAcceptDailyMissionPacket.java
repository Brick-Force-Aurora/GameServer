package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAcceptDailyMissionPacket implements IClientboundPacket {

    private int resultCode;

    /*
     * 0 = Success
     * -1 = Error Once per Day
     * -2 = Error Already Accepted
     * other numbers = Error Unkown
     */
    public ClientboundAcceptDailyMissionPacket resultCode(final int resultCode) {
        if (resultCode > 0 || resultCode < -2) {
            throw new IllegalArgumentException("ResultCode must be 0, -1 or -2");
        }
        this.resultCode = resultCode;
        return this;
    }

    public int resultCode() {
        return this.resultCode;
    }

    @Override
    public int packetId() {
        return 384;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.resultCode);
    }
}