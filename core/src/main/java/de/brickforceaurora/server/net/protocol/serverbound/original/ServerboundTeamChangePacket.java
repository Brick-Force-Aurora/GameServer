package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundTeamChangePacket implements IServerboundPacket {

    private boolean clickSlot;
    private int slotNum;

    public ServerboundTeamChangePacket clickSlot(final boolean clickSlot) {
        this.clickSlot = clickSlot;
        return this;
    }

    public boolean clickSlot() {
        return this.clickSlot;
    }

    public ServerboundTeamChangePacket slotNum(final int slotNum) {
        this.slotNum = slotNum;
        return this;
    }

    public int slotNum() {
        return this.slotNum;
    }

    @Override
    public int packetId() {
        return 80;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clickSlot = buf.readBoolean();
        this.slotNum = buf.readInt();
    }
}