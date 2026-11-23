package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundAddFriendPacket implements IServerboundPacket {

    private int friendWannabe;

    public ServerboundAddFriendPacket friendWannabe(final int friendWannabe) {
        this.friendWannabe = friendWannabe;
        return this;
    }

    public int friendWannabe() {
        return this.friendWannabe;
    }

    @Override
    public int packetId() {
        return 103;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.friendWannabe = buf.readInt();
    }
}