package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundInstallGadgetPacket implements IServerboundPacket {

	private int gadget;
	private float px;
	private float py;
	private float pz;
	private float nx;
	private float ny;
	private float nz;

	public final ServerboundInstallGadgetPacket gadget(int gadget) {
		this.gadget = gadget;
		return this;
	}

	public final int gadget() {
		return this.gadget;
	}

	public final ServerboundInstallGadgetPacket px(float px) {
		this.px = px;
		return this;
	}

	public final float px() {
		return this.px;
	}

	public final ServerboundInstallGadgetPacket py(float py) {
		this.py = py;
		return this;
	}

	public final float py() {
		return this.py;
	}

	public final ServerboundInstallGadgetPacket pz(float pz) {
		this.pz = pz;
		return this;
	}

	public final float pz() {
		return this.pz;
	}

	public final ServerboundInstallGadgetPacket nx(float nx) {
		this.nx = nx;
		return this;
	}

	public final float nx() {
		return this.nx;
	}

	public final ServerboundInstallGadgetPacket ny(float ny) {
		this.ny = ny;
		return this;
	}

	public final float ny() {
		return this.ny;
	}

	public final ServerboundInstallGadgetPacket nz(float nz) {
		this.nz = nz;
		return this;
	}

	public final float nz() {
		return this.nz;
	}

	@Override
	public int packetId() {
		return 400;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.gadget = buf.readInt();
		this.px = buf.readFloat();
		this.py = buf.readFloat();
		this.pz = buf.readFloat();
		this.nx = buf.readFloat();
		this.ny = buf.readFloat();
		this.nz = buf.readFloat();
	}
}