package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUpdateScriptPacket implements IServerboundPacket {

	private int clientId;
	private String alias;
	private boolean enableOnAwake;
	private boolean visibleOnAwake;
	private String commands;

	public final ServerboundUpdateScriptPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundUpdateScriptPacket alias(String alias) {
		this.alias = alias;
		return this;
	}

	public final String alias() {
		return this.alias;
	}

	public final ServerboundUpdateScriptPacket enableOnAwake(boolean enableOnAwake) {
		this.enableOnAwake = enableOnAwake;
		return this;
	}

	public final boolean enableOnAwake() {
		return this.enableOnAwake;
	}

	public final ServerboundUpdateScriptPacket visibleOnAwake(boolean visibleOnAwake) {
		this.visibleOnAwake = visibleOnAwake;
		return this;
	}

	public final boolean visibleOnAwake() {
		return this.visibleOnAwake;
	}

	public final ServerboundUpdateScriptPacket commands(String commands) {
		this.commands = commands;
		return this;
	}

	public final String commands() {
		return this.commands;
	}

	@Override
	public int packetId() {
		return 167;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.alias = buf.readString();
		this.enableOnAwake = buf.readBoolean();
		this.visibleOnAwake = buf.readBoolean();
		this.commands = buf.readString();
	}
}