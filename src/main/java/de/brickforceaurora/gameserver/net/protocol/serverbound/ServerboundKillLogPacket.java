package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;

public final class ServerboundKillLogPacket implements IServerboundPacket {

	private byte killerType;
	private int killer;
	private byte victimType;
	private int victim;
	private int weaponBy;
	private int slot;
	private int category;
	private int hitpart;
	private final Int2IntArrayMap damageLog = new Int2IntArrayMap();

	public final ServerboundKillLogPacket killerType(byte killerType) {
		this.killerType = killerType;
		return this;
	}

	public final byte killerType() {
		return this.killerType;
	}

	public final ServerboundKillLogPacket killer(int killer) {
		this.killer = killer;
		return this;
	}

	public final int killer() {
		return this.killer;
	}

	public final ServerboundKillLogPacket victimType(byte victimType) {
		this.victimType = victimType;
		return this;
	}

	public final byte victimType() {
		return this.victimType;
	}

	public final ServerboundKillLogPacket victim(int victim) {
		this.victim = victim;
		return this;
	}

	public final int victim() {
		return this.victim;
	}

	public final ServerboundKillLogPacket weaponBy(int weaponBy) {
		this.weaponBy = weaponBy;
		return this;
	}

	public final int weaponBy() {
		return this.weaponBy;
	}

	public final ServerboundKillLogPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundKillLogPacket category(int category) {
		this.category = category;
		return this;
	}

	public final int category() {
		return this.category;
	}

	public final ServerboundKillLogPacket hitpart(int hitpart) {
		this.hitpart = hitpart;
		return this;
	}

	public final int hitpart() {
		return this.hitpart;
	}

    public final Int2IntArrayMap damageLog() {
        return this.damageLog;
    }

	@Override
	public int packetId() {
		return 44;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.killerType = buffer.readByte();
		this.killer = buffer.readIntLE();
		this.victimType = buffer.readByte();
		this.victim = buffer.readIntLE();
		this.weaponBy = buffer.readIntLE();
		this.slot = buffer.readIntLE();
		this.category = buffer.readIntLE();
		this.hitpart = buffer.readIntLE();
		{
		    damageLog.clear();
	        int length = buffer.readIntLE();
	        for (int i = 0; i < length; i++) {
	            int key = buffer.readIntLE();
	            int value = buffer.readIntLE();
	            damageLog.put(key, value);
	        }
		}
	}
}