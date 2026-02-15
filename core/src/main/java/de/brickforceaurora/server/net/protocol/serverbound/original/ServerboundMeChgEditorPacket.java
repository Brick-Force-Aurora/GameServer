package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.isEditor = buf.readBoolean();
	}
}