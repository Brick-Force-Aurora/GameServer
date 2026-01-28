package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.brickMan = buffer.readIntLE();
        this.zombie = buffer.readIntLE();
    }
}