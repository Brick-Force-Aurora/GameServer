package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundChangeUserPrivateMapAliasPacket implements IServerboundPacket {

    private int slot;
    private String alias;

    public ServerboundChangeUserPrivateMapAliasPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundChangeUserPrivateMapAliasPacket alias(final String alias) {
        this.alias = alias;
        return this;
    }

    public String alias() {
        return this.alias;
    }

    @Override
    public int packetId() {
        return 54;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.slot = buf.readInt();
        this.alias = buf.readString();
    }
}