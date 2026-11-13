package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChatPacket implements IClientboundPacket {

	private int clientId;
	private byte chatType;
	private String nickname;
	private String text;
	private boolean gameMaster;

	public final ClientboundChatPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundChatPacket chatType(byte chatType) {
		this.chatType = chatType;
		return this;
	}

	public final byte chatType() {
		return this.chatType;
	}

	public final ClientboundChatPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ClientboundChatPacket text(String text) {
		this.text = text;
		return this;
	}

	public final String text() {
		return this.text;
	}

	public final ClientboundChatPacket gameMaster(boolean gameMaster) {
		this.gameMaster = gameMaster;
		return this;
	}

	public final boolean gameMaster() {
		return this.gameMaster;
	}

	@Override
	public int packetId() {
		return 25;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeByte(this.chatType);
		buf.writeString(this.nickname);
		buf.writeString(this.text);
		buf.writeBoolean(this.gameMaster);
	}
}
