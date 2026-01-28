package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCtfFlagReturnPacket implements IServerboundPacket {

    private float x;
    private float y;
    private float z;

    public ServerboundCtfFlagReturnPacket x(final float x) {
        this.x = x;
        return this;
    }

    public float x() {
        return this.x;
    }

    public ServerboundCtfFlagReturnPacket y(final float y) {
        this.y = y;
        return this;
    }

    public float y() {
        return this.y;
    }

    public ServerboundCtfFlagReturnPacket z(final float z) {
        this.z = z;
        return this;
    }

    public float z() {
        return this.z;
    }

    @Override
    public int packetId() {
        return 366;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.x = buffer.readFloatLE();
        this.y = buffer.readFloatLE();
        this.z = buffer.readFloatLE();
    }
}