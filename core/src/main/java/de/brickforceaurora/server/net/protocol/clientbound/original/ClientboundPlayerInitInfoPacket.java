package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.protocol.data.Tutorial;
import de.brickforceaurora.server.util.flag.IFlags;

public final class ClientboundPlayerInitInfoPacket implements IClientboundPacket {

	private int xp;
	private IFlags<Tutorial> tutorials;
	private CountryFilter countryFilter;
	private boolean tosAccepted;
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

	public final ClientboundPlayerInitInfoPacket tutorials(IFlags<Tutorial> tutorials) {
		this.tutorials = tutorials;
		return this;
	}

	public final IFlags<Tutorial> tutorials() {
		return this.tutorials;
	}

	public final ClientboundPlayerInitInfoPacket countryFilter(CountryFilter countryFilter) {
		this.countryFilter = countryFilter;
		return this;
	}

	public final CountryFilter countryFilter() {
		return this.countryFilter;
	}

	public final ClientboundPlayerInitInfoPacket tosAccepted(boolean tosAccepted) {
		this.tosAccepted = tosAccepted;
		return this;
	}

	public final boolean tosAccepted() {
		return this.tosAccepted;
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
		buf.writeByte(this.tutorials.value());
		buf.writeInt(this.countryFilter.id());
		buf.writeByte(this.tosAccepted ? 1 : 0);
		buf.writeInt(this.extraSlots);
		buf.writeInt(this.rank);
		buf.writeInt(this.firstLoginFp);
	}
}