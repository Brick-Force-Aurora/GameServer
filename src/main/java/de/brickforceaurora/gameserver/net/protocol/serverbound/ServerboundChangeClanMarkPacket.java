package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.clan = buffer.readIntLE();
		this.mark = buffer.readIntLE();
		this.ticket = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.ticketCode = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.ticketCode = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}