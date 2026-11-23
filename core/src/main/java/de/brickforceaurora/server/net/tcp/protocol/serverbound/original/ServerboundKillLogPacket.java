package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.DamageEntry;

public final class ServerboundKillLogPacket implements IServerboundPacket {

    private byte killerType;
    private int killer;
    private byte victimType;
    private int victim;
    private int weaponBy;
    private int slot;
    private int category;
    private int hitpart;
    private DamageEntry[] damageLog = {};

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

    public ServerboundKillLogPacket damageLog(final DamageEntry[] damageLog) {
        this.damageLog = damageLog;
        return this;
    }

    public DamageEntry[] damageLog() {
        return this.damageLog;
    }

    @Override
    public int packetId() {
        return 44;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.killerType = buf.readByte();
        this.killer = buf.readInt();
        this.victimType = buf.readByte();
        this.victim = buf.readInt();
        this.weaponBy = buf.readInt();
        this.slot = buf.readInt();
        this.category = buf.readInt();
        this.hitpart = buf.readInt();
        this.damageLog = buf.readArray(DamageEntry[]::new, DamageEntry::fromBuffer);
    }
}
