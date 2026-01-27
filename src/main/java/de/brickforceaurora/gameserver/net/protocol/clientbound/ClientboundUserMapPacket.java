package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundUserMapPacket implements IClientboundPacket {

	private int page;
	private int count;
	private int slot;
	private String alias;
	private int brickCount;
	private int year;
	private byte month;
	private byte day;
	private byte hour;
	private byte minute;
	private byte second;
	private byte premium;

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

	public final ClientboundUserMapPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ClientboundUserMapPacket alias(String alias) {
		this.alias = alias;
		return this;
	}

	public final String alias() {
		return this.alias;
	}

	public final ClientboundUserMapPacket brickCount(int brickCount) {
		this.brickCount = brickCount;
		return this;
	}

	public final int brickCount() {
		return this.brickCount;
	}

	public final ClientboundUserMapPacket year(int year) {
		this.year = year;
		return this;
	}

	public final int year() {
		return this.year;
	}

	public final ClientboundUserMapPacket month(byte month) {
		this.month = month;
		return this;
	}

	public final byte month() {
		return this.month;
	}

	public final ClientboundUserMapPacket day(byte day) {
		this.day = day;
		return this;
	}

	public final byte day() {
		return this.day;
	}

	public final ClientboundUserMapPacket hour(byte hour) {
		this.hour = hour;
		return this;
	}

	public final byte hour() {
		return this.hour;
	}

	public final ClientboundUserMapPacket minute(byte minute) {
		this.minute = minute;
		return this;
	}

	public final byte minute() {
		return this.minute;
	}

	public final ClientboundUserMapPacket second(byte second) {
		this.second = second;
		return this;
	}

	public final byte second() {
		return this.second;
	}

	public final ClientboundUserMapPacket premium(byte premium) {
		this.premium = premium;
		return this;
	}

	public final byte premium() {
		return this.premium;
	}

	@Override
	public int packetId() {
		return 430;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.page);
		buffer.writeIntLE(this.count);
		buffer.writeIntLE(this.slot);
		if (this.alias.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.alias.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.brickCount);
		buffer.writeIntLE(this.year);
		buffer.writeByte(this.month);
		buffer.writeByte(this.day);
		buffer.writeByte(this.hour);
		buffer.writeByte(this.minute);
		buffer.writeByte(this.second);
		buffer.writeByte(this.premium);
	}
}