package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.channel.Channel;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public final class ClientboundChannelPacket implements IClientboundPacket {

	private Channel[] channels;

	public final ClientboundChannelPacket channels(Channel[] channels) {
		this.channels = channels;
		return this;
	}

	public final Channel[] channels() {
		return this.channels;
	}

	@Override
	public int packetId() {
		return 141;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.channels.length);
		for (Channel channel : channels){
			buffer.writeIntLE(channel.id());
			buffer.writeIntLE(channel.mode().getId());
			if (channel.name().isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = channel.name().getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			if ("".isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = "".getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			buffer.writeIntLE(5000);
			buffer.writeIntLE(channel.userCount());
			buffer.writeIntLE(channel.data().maxUsers());
			buffer.writeIntLE(channel.data().country());
			buffer.writeByte(channel.data().minLevel());
			buffer.writeByte(channel.data().maxLevel());
			buffer.writeShortLE(channel.data().xpBonus());
			buffer.writeShortLE(channel.data().fpBonus());
			buffer.writeIntLE(channel.data().limitStarRate());
		}
	}
}