package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundResetBattleRecordPacket implements IServerboundPacket {

	private int target;
	private long ticket;
	private String ticketCode;

	public final ServerboundResetBattleRecordPacket target(int target) {
		this.target = target;
		return this;
	}

	public final int target() {
		return this.target;
	}

	public final ServerboundResetBattleRecordPacket ticket(long ticket) {
		this.ticket = ticket;
		return this;
	}

	public final long ticket() {
		return this.ticket;
	}

	public final ServerboundResetBattleRecordPacket ticketCode(String ticketCode) {
		this.ticketCode = ticketCode;
		return this;
	}

	public final String ticketCode() {
		return this.ticketCode;
	}

	@Override
	public int packetId() {
		return 514;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.target = buffer.readIntLE();
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