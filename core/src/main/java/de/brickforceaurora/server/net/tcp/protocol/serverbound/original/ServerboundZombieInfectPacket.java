package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundZombieInfectPacket implements IServerboundPacket {

    private int brickMan;
    private int zombie;

    public ServerboundZombieInfectPacket brickMan(final int brickMan) {
        this.brickMan = brickMan;
        return this;
    }

    public int brickMan() {
        return this.brickMan;
    }

    public ServerboundZombieInfectPacket zombie(final int zombie) {
        this.zombie = zombie;
        return this;
    }

    public int zombie() {
        return this.zombie;
    }

    @Override
    public int packetId() {
        return 540;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.brickMan = buf.readInt();
        this.zombie = buf.readInt();
    }
}