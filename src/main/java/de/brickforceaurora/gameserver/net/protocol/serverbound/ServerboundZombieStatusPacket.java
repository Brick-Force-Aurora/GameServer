package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.status = buffer.readIntLE();
        this.time = buffer.readIntLE();
        this.cntDn = buffer.readIntLE();
    }
}