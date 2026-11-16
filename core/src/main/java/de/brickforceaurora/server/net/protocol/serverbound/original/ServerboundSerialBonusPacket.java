package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSerialBonusPacket implements IServerboundPacket {

    private int bonus;

    public ServerboundSerialBonusPacket bonus(final int bonus) {
        this.bonus = bonus;
        return this;
    }

    public int bonus() {
        return this.bonus;
    }

    @Override
    public int packetId() {
        return 82;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.bonus = buf.readInt();
    }
}