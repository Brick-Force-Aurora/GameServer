package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.clickSlot = buffer.readBoolean();
		this.slotNum = buffer.readIntLE();
	}
}