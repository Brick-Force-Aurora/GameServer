package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAccusePlayerPacket implements IServerboundPacket {

	private int reason;
	private String nickName;
	private String contents;

	public final ServerboundAccusePlayerPacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	public final ServerboundAccusePlayerPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	public final ServerboundAccusePlayerPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 510;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.reason = buf.readInt();
		this.nickName = buf.readString();
		this.contents = buf.readString();
	}
}