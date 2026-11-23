package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundDropItemPacket implements IServerboundPacket {

    private String itemCode;
    private int count;
    private int subCount;
    private float x;
    private float y;
    private float z;

    public ServerboundDropItemPacket itemCode(final String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String itemCode() {
        return this.itemCode;
    }

    public ServerboundDropItemPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    public ServerboundDropItemPacket subCount(final int subCount) {
        this.subCount = subCount;
        return this;
    }

    public int subCount() {
        return this.subCount;
    }

    public ServerboundDropItemPacket x(final float x) {
        this.x = x;
        return this;
    }

    public float x() {
        return this.x;
    }

    public ServerboundDropItemPacket y(final float y) {
        this.y = y;
        return this;
    }

    public float y() {
        return this.y;
    }

    public ServerboundDropItemPacket z(final float z) {
        this.z = z;
        return this;
    }

    public float z() {
        return this.z;
    }

    @Override
    public int packetId() {
        return 526;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.itemCode = buf.readString();
        this.count = buf.readInt();
        this.subCount = buf.readInt();
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
    }
}