package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUseChangeNicknamePacket implements IServerboundPacket {

	private String newNickname;
	private long target;
	private String ticketCode;

	public final ServerboundUseChangeNicknamePacket newNickname(String newNickname) {
		this.newNickname = newNickname;
		return this;
	}

	public final String newNickname() {
		return this.newNickname;
	}

	public final ServerboundUseChangeNicknamePacket target(long target) {
		this.target = target;
		return this;
	}

	public final long target() {
		return this.target;
	}

	public final ServerboundUseChangeNicknamePacket ticketCode(String ticketCode) {
		this.ticketCode = ticketCode;
		return this;
	}

	public final String ticketCode() {
		return this.ticketCode;
	}

	@Override
	public int packetId() {
		return 501;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.newNickname = buf.readString();
		this.target = buf.readLong();
		this.ticketCode = buf.readString();
	}
}