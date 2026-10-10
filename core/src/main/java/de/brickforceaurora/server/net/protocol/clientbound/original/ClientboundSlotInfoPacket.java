package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSlotInfoPacket implements IClientboundPacket {

	private int id;
	private byte slot;
	private int status;
	private int kill;
	private int death;
	private int assist;
	private int score;
	private int mission;

	public int id() {
		return this.id;
	}

	public ClientboundSlotInfoPacket id(int id) {
		this.id = id;
		return this;
	}

	public byte slot() {
		return this.slot;
	}

	public ClientboundSlotInfoPacket slot(byte slot) {
		this.slot = slot;
		return this;
	}

	public int status() {
		return this.status;
	}

	public ClientboundSlotInfoPacket status(int status) {
		this.status = status;
		return this;
	}

	public int kill() {
		return this.kill;
	}

	public ClientboundSlotInfoPacket kill(int kill) {
		this.kill = kill;
		return this;
	}

	public int death() {
		return this.death;
	}

	public ClientboundSlotInfoPacket death(int death) {
		this.death = death;
		return this;
	}

	public int assist() {
		return this.assist;
	}

	public ClientboundSlotInfoPacket assist(int assist) {
		this.assist = assist;
		return this;
	}

	public int score() {
		return this.score;
	}

	public ClientboundSlotInfoPacket score(int score) {
		this.score = score;
		return this;
	}

	public int mission() {
		return this.mission;
	}

	public ClientboundSlotInfoPacket mission(int mission) {
		this.mission = mission;
		return this;
	}

	@Override
	public int packetId() {
		return 46;
	}

	@Override
	public void write(PacketBuf buf) {
		buf.writeInt(this.id);
		buf.writeByte(this.slot);
		buf.writeInt(this.status);
		buf.writeInt(this.kill);
		buf.writeInt(this.death);
		buf.writeInt(this.assist);
		buf.writeInt(this.score);
		buf.writeInt(this.mission);
	}
}