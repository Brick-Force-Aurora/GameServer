package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAddBrickPacket implements IServerboundPacket {

    private int brick;
    private int x;
    private int y;
    private int z;
    private int rot;

    public ServerboundAddBrickPacket brick(final int brick) {
        if (brick > 255L || brick < 0L) {
            throw new IllegalArgumentException("Value " + brick + " is out of bounds of allowed number range of 0 - 255");
        }
        this.brick = brick;
        return this;
    }

    public int brick() {
        return this.brick;
    }

    public ServerboundAddBrickPacket x(final int x) {
        if (x > 255L || x < 0L) {
            throw new IllegalArgumentException("Value " + x + " is out of bounds of allowed number range of 0 - 255");
        }
        this.x = x;
        return this;
    }

    public int x() {
        return this.x;
    }

    public ServerboundAddBrickPacket y(final int y) {
        if (y > 255L || y < 0L) {
            throw new IllegalArgumentException("Value " + y + " is out of bounds of allowed number range of 0 - 255");
        }
        this.y = y;
        return this;
    }

    public int y() {
        return this.y;
    }

    public ServerboundAddBrickPacket z(final int z) {
        if (z > 255L || z < 0L) {
            throw new IllegalArgumentException("Value " + z + " is out of bounds of allowed number range of 0 - 255");
        }
        this.z = z;
        return this;
    }

    public int z() {
        return this.z;
    }

    public ServerboundAddBrickPacket rot(final int rot) {
        if (rot > 255L || rot < 0L) {
            throw new IllegalArgumentException("Value " + rot + " is out of bounds of allowed number range of 0 - 255");
        }
        this.rot = rot;
        return this;
    }

    public int rot() {
        return this.rot;
    }

    @Override
    public int packetId() {
        return 13;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.brick = buffer.readUnsignedByte();
        this.x = buffer.readUnsignedByte();
        this.y = buffer.readUnsignedByte();
        this.z = buffer.readUnsignedByte();
        this.rot = buffer.readUnsignedByte();
    }
}