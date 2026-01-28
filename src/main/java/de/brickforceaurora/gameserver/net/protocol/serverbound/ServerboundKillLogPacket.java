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

    public ServerboundKillLogPacket killerType(final byte killerType) {
        this.killerType = killerType;
        return this;
    }

    public byte killerType() {
        return this.killerType;
    }

    public ServerboundKillLogPacket killer(final int killer) {
        this.killer = killer;
        return this;
    }

    public int killer() {
        return this.killer;
    }

    public ServerboundKillLogPacket victimType(final byte victimType) {
        this.victimType = victimType;
        return this;
    }

    public byte victimType() {
        return this.victimType;
    }

    public ServerboundKillLogPacket victim(final int victim) {
        this.victim = victim;
        return this;
    }

    public int victim() {
        return this.victim;
    }

    public ServerboundKillLogPacket weaponBy(final int weaponBy) {
        this.weaponBy = weaponBy;
        return this;
    }

    public int weaponBy() {
        return this.weaponBy;
    }

    public ServerboundKillLogPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundKillLogPacket category(final int category) {
        this.category = category;
        return this;
    }

    public int category() {
        return this.category;
    }

    public ServerboundKillLogPacket hitpart(final int hitpart) {
        this.hitpart = hitpart;
        return this;
    }

    public int hitpart() {
        return this.hitpart;
    }

    public Int2IntArrayMap damageLog() {
        return this.damageLog;
    }

    @Override
    public int packetId() {
        return 44;
    }

    @Override
    public void read(final ByteBuf buffer) {
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
            final int length = buffer.readIntLE();
            for (int i = 0; i < length; i++) {
                final int key = buffer.readIntLE();
                final int value = buffer.readIntLE();
                damageLog.put(key, value);
            }
        }
    }
}