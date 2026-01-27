package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundUseChangeNicknamePacket implements IServerboundPacket {

	private String newNickname;
	private long target;
	private String ticketCode;

	public final ServerboundUseChangeNicknamePacket newNickname(String newNickname) {
		this.newNickname = newNickname;
		return this;
	}

	public final String newNickname() {
		return this.newNickname;
	}

	public final ServerboundUseChangeNicknamePacket target(long target) {
		this.target = target;
		return this;
	}

	public final long target() {
		return this.target;
	}

	public final ServerboundUseChangeNicknamePacket ticketCode(String ticketCode) {
		this.ticketCode = ticketCode;
		return this;
	}

	public final String ticketCode() {
		return this.ticketCode;
	}

	@Override
	public int packetId() {
		return 501;
	}

	@Override
	public final void read(ByteBuf buffer) {
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.newNickname = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.newNickname = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.target = buffer.readLongLE();
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