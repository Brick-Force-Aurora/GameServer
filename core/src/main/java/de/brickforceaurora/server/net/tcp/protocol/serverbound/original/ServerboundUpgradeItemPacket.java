package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundUpgradeItemPacket implements IServerboundPacket {

    private long item;
    private String code;
    private long upgrader;
    private String upgradercode;
    private int prop;

    public ServerboundUpgradeItemPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundUpgradeItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundUpgradeItemPacket upgrader(final long upgrader) {
        this.upgrader = upgrader;
        return this;
    }

    public long upgrader() {
        return this.upgrader;
    }

    public ServerboundUpgradeItemPacket upgradercode(final String upgradercode) {
        this.upgradercode = upgradercode;
        return this;
    }

    public String upgradercode() {
        return this.upgradercode;
    }

    public ServerboundUpgradeItemPacket prop(final int prop) {
        this.prop = prop;
        return this;
    }

    public int prop() {
        return this.prop;
    }

    @Override
    public int packetId() {
        return 353;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.code = buf.readString();
        this.upgrader = buf.readLong();
        this.upgradercode = buf.readString();
        this.prop = buf.readInt();
    }
}