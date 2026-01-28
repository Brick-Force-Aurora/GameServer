package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.legacy.room.Room;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundUserMapPacket implements IClientboundPacket {

	private int page;
	private int count;
	private int offset;
	private RegMap[] regMaps;

	public final ClientboundUserMapPacket page(int page) {
		this.page = page;
		return this;
	}

	public final int page() {
		return this.page;
	}

	public final ClientboundUserMapPacket count(int count) {
		this.count = count;
		return this;
	}

	public final int count() {
		return this.count;
	}

	public final ClientboundUserMapPacket offset(int offset) {
		this.offset = offset;
		return this;
	}

	public final int offset() {
		return this.offset;
	}

	public final ClientboundUserMapPacket regMaps(RegMap[] regMaps) {
		this.regMaps = regMaps;
		return this;
	}

	public final RegMap[] regMaps() {
		return this.regMaps;
	}

	@Override
	public int packetId() {
		return 430;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.page);
		buffer.writeIntLE(this.count);
		for (int i = this.offset; i < this.offset + this.count; i++) {
			RegMap regMap = regMaps[i];
			buffer.writeIntLE(i); //slot
			if (regMap.getAlias().isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = regMap.getAlias().getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			buffer.writeIntLE(10000); //brickCount
			buffer.writeIntLE(regMap.getRegisteredDate().getYear());
			buffer.writeByte(regMap.getRegisteredDate().getMonth().getValue());
			buffer.writeByte(regMap.getRegisteredDate().getDayOfMonth());
			buffer.writeByte(regMap.getRegisteredDate().getHour());
			buffer.writeByte(regMap.getRegisteredDate().getMinute());
			buffer.writeByte(regMap.getRegisteredDate().getSecond());
			buffer.writeByte(0);
		}
	}
}