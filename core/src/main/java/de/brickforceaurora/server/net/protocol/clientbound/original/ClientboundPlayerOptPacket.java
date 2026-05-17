package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPlayerOptPacket implements IClientboundPacket {

	private int qjModeMask;
	private int qjOfficialMask;
	private int qjCommonMask;

	public final ClientboundPlayerOptPacket qjModeMask(int qjModeMask) {
		this.qjModeMask = qjModeMask;
		return this;
	}

	public final int qjModeMask() {
		return this.qjModeMask;
	}

	public final ClientboundPlayerOptPacket qjOfficialMask(int qjOfficialMask) {
		this.qjOfficialMask = qjOfficialMask;
		return this;
	}

	public final int qjOfficialMask() {
		return this.qjOfficialMask;
	}

	public final ClientboundPlayerOptPacket qjCommonMask(int qjCommonMask) {
		this.qjCommonMask = qjCommonMask;
		return this;
	}

	public final int qjCommonMask() {
		return this.qjCommonMask;
	}

	@Override
	public int packetId() {
		return 417;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.qjModeMask);
		buf.writeInt(this.qjOfficialMask);
		buf.writeInt(this.qjCommonMask);
	}
}