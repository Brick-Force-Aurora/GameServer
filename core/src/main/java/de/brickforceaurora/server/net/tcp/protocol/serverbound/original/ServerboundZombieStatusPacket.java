package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundZombieStatusPacket implements IServerboundPacket {

    private int status;
    private int time;
    private int cntDn;

    public ServerboundZombieStatusPacket status(final int status) {
        this.status = status;
        return this;
    }

    public int status() {
        return this.status;
    }

    public ServerboundZombieStatusPacket time(final int time) {
        this.time = time;
        return this;
    }

    public int time() {
        return this.time;
    }

    public ServerboundZombieStatusPacket cntDn(final int cntDn) {
        this.cntDn = cntDn;
        return this;
    }

    public int cntDn() {
        return this.cntDn;
    }

    @Override
    public int packetId() {
        return 547;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.status = buf.readInt();
        this.time = buf.readInt();
        this.cntDn = buf.readInt();
    }
}