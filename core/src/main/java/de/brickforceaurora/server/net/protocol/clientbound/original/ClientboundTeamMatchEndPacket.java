package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ResultUnit;

public final class ClientboundTeamMatchEndPacket implements IClientboundPacket {

	private byte endCode;
	private int redKill;
	private int redDeath;
	private int blueKill;
	private int blueDeath;
	private ResultUnit[] results = new ResultUnit[0];

	public final ClientboundTeamMatchEndPacket endCode(byte endCode) {
		this.endCode = endCode;
		return this;
	}

	public final byte endCode() {
		return this.endCode;
	}

	public final ClientboundTeamMatchEndPacket redKill(int redKill) {
		this.redKill = redKill;
		return this;
	}

	public final int redKill() {
		return this.redKill;
	}

	public final ClientboundTeamMatchEndPacket redDeath(int redDeath) {
		this.redDeath = redDeath;
		return this;
	}

	public final int redDeath() {
		return this.redDeath;
	}

	public final ClientboundTeamMatchEndPacket blueKill(int blueKill) {
		this.blueKill = blueKill;
		return this;
	}

	public final int blueKill() {
		return this.blueKill;
	}

	public final ClientboundTeamMatchEndPacket blueDeath(int blueDeath) {
		this.blueDeath = blueDeath;
		return this;
	}

	public final int blueDeath() {
		return this.blueDeath;
	}

	public final ClientboundTeamMatchEndPacket results(ResultUnit[] results) {
		this.results = results;
		return this;
	}

	public final ResultUnit[] results() {
		return this.results;
	}

	@Override
	public int packetId() {
		return 70;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.endCode);
		buf.writeInt(this.redKill);
		buf.writeInt(this.redDeath);
		buf.writeInt(this.blueKill);
		buf.writeInt(this.blueDeath);
		buf.writeInt(this.results.length);
		for (ResultUnit unit : this.results) {
			unit.toBuffer(buf);
		}
	}
}
