package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAccusePlayerPacket implements IClientboundPacket {

	private int resultCode;

	/*
	 * 0 = success, report sent to GM
	 * -1 = Nickname does not exist, dialog pops up again
	 * -2 = Already reported, dialog pops up again
	 * -3 = Max amount of reports
	 * -4 = You cant report yourself or GM, dialog pops up again
	 * -5 = Error in the report system, try aqain, dialog pops up again
	 */
	public final ClientboundAccusePlayerPacket resultCode(int resultCode) {
		if (resultCode > 0 || resultCode < -5) {
			throw new IllegalArgumentException("ResultCode must be between 0 and -5");
		}
		this.resultCode = resultCode;
		return this;
	}

	public final int resultCode() {
		return this.resultCode;
	}

	@Override
	public int packetId() {
		return 511;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.resultCode);
	}
}