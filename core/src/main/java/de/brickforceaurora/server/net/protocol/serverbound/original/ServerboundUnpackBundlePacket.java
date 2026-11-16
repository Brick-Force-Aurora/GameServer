package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUnpackBundlePacket implements IServerboundPacket {

    private long bundle;
    private String bundleCode;

    public ServerboundUnpackBundlePacket bundle(final long bundle) {
        this.bundle = bundle;
        return this;
    }

    public long bundle() {
        return this.bundle;
    }

    public ServerboundUnpackBundlePacket bundleCode(final String bundleCode) {
        this.bundleCode = bundleCode;
        return this;
    }

    public String bundleCode() {
        return this.bundleCode;
    }

    @Override
    public int packetId() {
        return 360;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.bundle = buf.readLong();
        this.bundleCode = buf.readString();
    }
}