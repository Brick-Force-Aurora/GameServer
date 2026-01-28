package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGadgetActionPacket implements IServerboundPacket {

    private int clientId;
    private int action;

    public ServerboundGadgetActionPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundGadgetActionPacket action(final int action) {
        this.action = action;
        return this;
    }

    public int action() {
        return this.action;
    }

    @Override
    public int packetId() {
        return 402;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        this.action = buffer.readIntLE();
    }
}