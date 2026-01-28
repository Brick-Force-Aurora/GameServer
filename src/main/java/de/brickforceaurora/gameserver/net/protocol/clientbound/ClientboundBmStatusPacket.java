package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBmStatusPacket implements IClientboundPacket {

    private boolean rounding;
    private int bombInstaller;
    private int blastTarget;
    private float posX;
    private float posY;
    private float posZ;
    private float normalX;
    private float normalY;
    private float normalZ;

    public ClientboundBmStatusPacket rounding(final boolean rounding) {
        this.rounding = rounding;
        return this;
    }

    public boolean rounding() {
        return this.rounding;
    }

    public ClientboundBmStatusPacket bombInstaller(final int bombInstaller) {
        this.bombInstaller = bombInstaller;
        return this;
    }

    public int bombInstaller() {
        return this.bombInstaller;
    }

    public ClientboundBmStatusPacket blastTarget(final int blastTarget) {
        this.blastTarget = blastTarget;
        return this;
    }

    public int blastTarget() {
        return this.blastTarget;
    }

    public ClientboundBmStatusPacket posX(final float posX) {
        this.posX = posX;
        return this;
    }

    public float posX() {
        return this.posX;
    }

    public ClientboundBmStatusPacket posY(final float posY) {
        this.posY = posY;
        return this;
    }

    public float posY() {
        return this.posY;
    }

    public ClientboundBmStatusPacket posZ(final float posZ) {
        this.posZ = posZ;
        return this;
    }

    public float posZ() {
        return this.posZ;
    }

    public ClientboundBmStatusPacket normalX(final float normalX) {
        this.normalX = normalX;
        return this;
    }

    public float normalX() {
        return this.normalX;
    }

    public ClientboundBmStatusPacket normalY(final float normalY) {
        this.normalY = normalY;
        return this;
    }

    public float normalY() {
        return this.normalY;
    }

    public ClientboundBmStatusPacket normalZ(final float normalZ) {
        this.normalZ = normalZ;
        return this;
    }

    public float normalZ() {
        return this.normalZ;
    }

    @Override
    public int packetId() {
        return 301;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeBoolean(this.rounding);
        buffer.writeIntLE(this.bombInstaller);
        buffer.writeIntLE(this.blastTarget);
        buffer.writeFloatLE(this.posX);
        buffer.writeFloatLE(this.posY);
        buffer.writeFloatLE(this.posZ);
        buffer.writeFloatLE(this.normalX);
        buffer.writeFloatLE(this.normalY);
        buffer.writeFloatLE(this.normalZ);
    }
}