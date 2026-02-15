package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeClanMarkPacket implements IServerboundPacket {

	private int clan;
	private int mark;
	private long ticket;
	private String ticketCode;

	public final ServerboundChangeClanMarkPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundChangeClanMarkPacket mark(int mark) {
		this.mark = mark;
		return this;
	}

	public final int mark() {
		return this.mark;
	}

	public final ServerboundChangeClanMarkPacket ticket(long ticket) {
		this.ticket = ticket;
		return this;
	}

	public final long ticket() {
		return this.ticket;
	}

	public final ServerboundChangeClanMarkPacket ticketCode(String ticketCode) {
		this.ticketCode = ticketCode;
		return this;
	}

	public final String ticketCode() {
		return this.ticketCode;
	}

	@Override
	public int packetId() {
		return 234;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.mark = buf.readInt();
		this.ticket = buf.readLong();
		this.ticketCode = buf.readString();
	}
}