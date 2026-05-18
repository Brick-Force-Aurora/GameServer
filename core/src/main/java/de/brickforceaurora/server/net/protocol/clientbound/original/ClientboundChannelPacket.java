package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ChannelInfo;

public final class ClientboundChannelPacket implements IClientboundPacket {

	private ChannelInfo[] channels;

	public final ClientboundChannelPacket channels(ChannelInfo[] channels) {
		this.channels = channels;
		return this;
	}

	public final ChannelInfo[] channels() {
		return this.channels;
	}

	@Override
	public int packetId() {
		return 141;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.channels.length);
		for (ChannelInfo channel: channels){
			buf.writeInt(channel.id());
			buf.writeInt(channel.mode());
			buf.writeString(channel.name());
			buf.writeString(channel.ip());
			buf.writeInt(channel.port());
			buf.writeInt(channel.userCount());
			buf.writeInt(channel.maxUserCount());
			buf.writeInt(channel.country());
			buf.writeByte(channel.minLvRank());
			buf.writeByte(channel.maxLvRank());
			buf.writeShort(channel.xpBonus()); //Unsigned Short
			buf.writeShort(channel.fpBonus()); //Unsigned Short
			buf.writeInt(channel.limitStarRate());
		}
	}
}