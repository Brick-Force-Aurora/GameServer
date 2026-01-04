package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.alias = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.alias = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.enableOnAwake = buffer.readBoolean();
		this.visibleOnAwake = buffer.readBoolean();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.commands = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.commands = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}