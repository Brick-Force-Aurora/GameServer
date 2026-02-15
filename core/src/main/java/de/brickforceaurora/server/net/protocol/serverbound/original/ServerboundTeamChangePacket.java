package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundTeamChangePacket implements IServerboundPacket {

	private boolean clickSlot;
	private int slotNum;

	public final ServerboundTeamChangePacket clickSlot(boolean clickSlot) {
		this.clickSlot = clickSlot;
		return this;
	}

	public final boolean clickSlot() {
		return this.clickSlot;
	}

	public final ServerboundTeamChangePacket slotNum(int slotNum) {
		this.slotNum = slotNum;
		return this;
	}

	public final int slotNum() {
		return this.slotNum;
	}

	@Override
	public int packetId() {
		return 80;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clickSlot = buf.readBoolean();
		this.slotNum = buf.readInt();
	}
}