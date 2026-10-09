package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAcceptApplicantPacket implements IClientboundPacket {

	private int resultCode;
	private boolean accept;
	private String nickName;

	/*
	 * -11 = Too Many Clanmembers
	 * -3 = Already Member
	 * -7 = Failed to find applicant
	 * -1 = no auth
	 */
	public final ClientboundAcceptApplicantPacket resultCode(int resultCode) {
		if (resultCode != 0 && resultCode != -11 && resultCode != -3 && resultCode != -7 && resultCode != -1){
			throw new IllegalArgumentException("ResultCode must be 0, -11, -3, -7 or -1");
		}
		this.resultCode = resultCode;
		return this;
	}

	public final int resultCode() {
		return this.resultCode;
	}

	public final ClientboundAcceptApplicantPacket accept(boolean accept) {
		this.accept = accept;
		return this;
	}

	public final boolean accept() {
		return this.accept;
	}

	public final ClientboundAcceptApplicantPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 218;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.resultCode);
		buf.writeInt(0); // unused
		buf.writeBoolean(this.accept); //True if Aplicant accepted, False if rejected
		buf.writeString(this.nickName);
	}
}