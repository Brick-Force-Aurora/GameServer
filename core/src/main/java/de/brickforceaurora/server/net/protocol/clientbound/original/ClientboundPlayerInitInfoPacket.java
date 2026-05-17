package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPlayerInitInfoPacket implements IClientboundPacket {
    
    public static final byte TOS_ACCEPTED = 0b1;
    public static final byte TUTORIALS_DONE = 0b11;

	private int xp;
	private byte tutorialed;
	private int countryFilter;
	private byte tos;
	private int extraSlots;
	private int rank;
	private int firstLoginFp;

	public final ClientboundPlayerInitInfoPacket xp(int xp) {
		this.xp = xp;
		return this;
	}

	public final int xp() {
		return this.xp;
	}

	public final ClientboundPlayerInitInfoPacket tutorialed(byte tutorialed) {
		this.tutorialed = tutorialed;
		return this;
	}

	public final byte tutorialed() {
		return this.tutorialed;
	}

	public final ClientboundPlayerInitInfoPacket countryFilter(int countryFilter) {
		this.countryFilter = countryFilter;
		return this;
	}

	public final int countryFilter() {
		return this.countryFilter;
	}

	public final ClientboundPlayerInitInfoPacket tos(byte tos) {
		this.tos = tos;
		return this;
	}

	public final byte tos() {
		return this.tos;
	}

	public final ClientboundPlayerInitInfoPacket extraSlots(int extraSlots) {
		this.extraSlots = extraSlots;
		return this;
	}

	public final int extraSlots() {
		return this.extraSlots;
	}

	public final ClientboundPlayerInitInfoPacket rank(int rank) {
		this.rank = rank;
		return this;
	}

	public final int rank() {
		return this.rank;
	}

	public final ClientboundPlayerInitInfoPacket firstLoginFp(int firstLoginFp) {
		this.firstLoginFp = firstLoginFp;
		return this;
	}

	public final int firstLoginFp() {
		return this.firstLoginFp;
	}

	@Override
	public int packetId() {
		return 148;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.xp);
		buf.writeByte(this.tutorialed);
		buf.writeInt(this.countryFilter);
		buf.writeByte(this.tos);
		buf.writeInt(this.extraSlots);
		buf.writeInt(this.rank);
		buf.writeInt(this.firstLoginFp);
	}
}