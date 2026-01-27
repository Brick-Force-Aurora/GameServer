package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMeChgEditorPacket implements IServerboundPacket {

	private int clientId;
	private boolean isEditor;

	public final ServerboundMeChgEditorPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundMeChgEditorPacket isEditor(boolean isEditor) {
		this.isEditor = isEditor;
		return this;
	}

	public final boolean isEditor() {
		return this.isEditor;
	}

	@Override
	public int packetId() {
		return 304;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		this.isEditor = buffer.readBoolean();
	}
}