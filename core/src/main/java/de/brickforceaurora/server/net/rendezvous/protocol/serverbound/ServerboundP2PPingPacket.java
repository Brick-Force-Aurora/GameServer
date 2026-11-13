package de.brickforceaurora.server.net.rendezvous.protocol.serverbound;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundP2PPingPacket implements IServerboundPacket {

    private int clientId;
    private float time;
    private boolean unpointed;
    private int slot;

    @Override
    public int packetId() {
        return 24;
    }

    public int clientId() {
        return clientId;
    }

    public float time() {
        return time;
    }

    public boolean unpointed() {
        return unpointed;
    }

    public int slot() {
        return slot;
    }

    @Override
    public void read(final PacketBuf buffer) {
        clientId = buffer.readInt();
        time = buffer.readFloat();
        unpointed = buffer.readBoolean();
        slot = buffer.readUnsignedByte();
    }

    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
