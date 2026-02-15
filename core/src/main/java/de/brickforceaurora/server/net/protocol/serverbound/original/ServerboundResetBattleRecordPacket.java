package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundResetBattleRecordPacket implements IServerboundPacket {

	private int target;
	private long ticket;
	private String ticketCode;

	public final ServerboundResetBattleRecordPacket target(int target) {
		this.target = target;
		return this;
	}

	public final int target() {
		return this.target;
	}

	public final ServerboundResetBattleRecordPacket ticket(long ticket) {
		this.ticket = ticket;
		return this;
	}

	public final long ticket() {
		return this.ticket;
	}

	public final ServerboundResetBattleRecordPacket ticketCode(String ticketCode) {
		this.ticketCode = ticketCode;
		return this;
	}

	public final String ticketCode() {
		return this.ticketCode;
	}

	@Override
	public int packetId() {
		return 514;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.target = buf.readInt();
		this.ticket = buf.readLong();
		this.ticketCode = buf.readString();
	}
}