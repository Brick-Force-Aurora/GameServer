package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.legacy.room.Room;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundMyDownloadMapPacket implements IClientboundPacket {

	private int page;
	private int count;
	private int offset;
	private RegMap[] regMaps;

	public final ClientboundMyDownloadMapPacket page(int page) {
		this.page = page;
		return this;
	}

	public final int page() {
		return this.page;
	}

	public final ClientboundMyDownloadMapPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	public final ClientboundMyDownloadMapPacket offset(int offset) {
		this.offset = offset;
		return this;
	}

	public final int offset() {
		return this.offset;
	}

	public final ClientboundMyDownloadMapPacket regMaps(RegMap[] regMaps) {
		this.regMaps = regMaps;
		return this;
	}

	public final RegMap[] regMaps() {
		return this.regMaps;
	}

	@Override
	public int packetId() {
		return 426;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.page);
		buffer.writeIntLE(this.count);
		for (int i = this.offset; i < this.offset + this.count; i++) {
			RegMap regMap = regMaps[i];
			buffer.writeIntLE(regMap.getMap());
			if (regMap.getDeveloper().isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = regMap.getDeveloper().getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			if (regMap.getAlias().isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = regMap.getAlias().getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			if (regMap.getModeMask() > 32767L || regMap.getModeMask() < 0L) {
				throw new IllegalArgumentException(
						"Value " + regMap.getModeMask() + " is out of bounds of allowed number range of 0 - 32767");
			}
			buffer.writeShortLE(regMap.getModeMask() & 0xFFFF);
			byte roomType = (byte)(Room.clanMatch | Room.official);
			if (roomType > 255L || roomType < 0L) {
				throw new IllegalArgumentException(
						"Value " + roomType + " is out of bounds of allowed number range of 0 - 255");
			}
			buffer.writeByte( roomType & 0xFF);
			if (regMap.tagMask > 255L || regMap.tagMask < 0L) {
				throw new IllegalArgumentException(
						"Value " + regMap.tagMask + " is out of bounds of allowed number range of 0 - 255");
			}
			buffer.writeByte(regMap.tagMask & 0xFF);
			buffer.writeIntLE(regMap.getRegisteredDate().getYear());
			buffer.writeByte(regMap.getRegisteredDate().getMonth().getValue());
			buffer.writeByte(regMap.getRegisteredDate().getDayOfMonth());
			buffer.writeByte(regMap.getRegisteredDate().getHour());
			buffer.writeByte(regMap.getRegisteredDate().getMinute());
			buffer.writeByte(regMap.getRegisteredDate().getSecond());
			buffer.writeIntLE(regMap.getDownloadFee());
			buffer.writeIntLE(regMap.getRelease());
			buffer.writeIntLE(regMap.getLatestRelease());
			buffer.writeIntLE(regMap.getLikes());
			buffer.writeIntLE(regMap.getDisLikes());
			buffer.writeIntLE(regMap.getDownloadCount());
		}
	}
}