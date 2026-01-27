package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundInvitePacket implements IServerboundPacket {

	private int inviteeSeq;
	private String nickName;

	public final ServerboundInvitePacket inviteeSeq(int inviteeSeq) {
		this.inviteeSeq = inviteeSeq;
		return this;
	}

	public final int inviteeSeq() {
		return this.inviteeSeq;
	}

	public final ServerboundInvitePacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 481;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.inviteeSeq = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.nickName = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.nickName = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}