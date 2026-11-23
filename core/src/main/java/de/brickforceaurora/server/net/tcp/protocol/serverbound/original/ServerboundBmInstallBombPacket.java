package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundBmInstallBombPacket implements IServerboundPacket {

    private int bomb;
    private float posX;
    private float posY;
    private float posZ;
    private float normalX;
    private float normalY;
    private float normalZ;

    public ServerboundBmInstallBombPacket bomb(final int bomb) {
        this.bomb = bomb;
        return this;
    }

    public int bomb() {
        return this.bomb;
    }

    public ServerboundBmInstallBombPacket posX(final float posX) {
        this.posX = posX;
        return this;
    }

    public float posX() {
        return this.posX;
    }

    public ServerboundBmInstallBombPacket posY(final float posY) {
        this.posY = posY;
        return this;
    }

    public float posY() {
        return this.posY;
    }

    public ServerboundBmInstallBombPacket posZ(final float posZ) {
        this.posZ = posZ;
        return this;
    }

    public float posZ() {
        return this.posZ;
    }

    public ServerboundBmInstallBombPacket normalX(final float normalX) {
        this.normalX = normalX;
        return this;
    }

    public float normalX() {
        return this.normalX;
    }

    public ServerboundBmInstallBombPacket normalY(final float normalY) {
        this.normalY = normalY;
        return this;
    }

    public float normalY() {
        return this.normalY;
    }

    public ServerboundBmInstallBombPacket normalZ(final float normalZ) {
        this.normalZ = normalZ;
        return this;
    }

    public float normalZ() {
        return this.normalZ;
    }

    @Override
    public int packetId() {
        return 279;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.bomb = buf.readInt();
        this.posX = buf.readFloat();
        this.posY = buf.readFloat();
        this.posZ = buf.readFloat();
        this.normalX = buf.readFloat();
        this.normalY = buf.readFloat();
        this.normalZ = buf.readFloat();
    }
}