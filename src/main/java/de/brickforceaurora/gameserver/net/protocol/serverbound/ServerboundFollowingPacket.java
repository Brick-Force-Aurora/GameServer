package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundFollowingPacket implements IServerboundPacket {

	private int followeeSeq;
	private String nickName;

	public final ServerboundFollowingPacket followeeSeq(int followeeSeq) {
		this.followeeSeq = followeeSeq;
		return this;
	}

	public final int followeeSeq() {
		return this.followeeSeq;
	}

	public final ServerboundFollowingPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 484;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.followeeSeq = buffer.readIntLE();
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