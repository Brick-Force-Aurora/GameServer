package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKillLogPacket implements IClientboundPacket {

    private byte killerType;
    private int killer;
    private byte victimType;
    private int victim;
    private int weaponBy;
    private int hitpart;

    public ClientboundKillLogPacket killerType(final byte killerType) {
        this.killerType = killerType;
        return this;
    }

    public byte killerType() {
        return this.killerType;
    }

    public ClientboundKillLogPacket killer(final int killer) {
        this.killer = killer;
        return this;
    }

    public int killer() {
        return this.killer;
    }

    public ClientboundKillLogPacket victimType(final byte victimType) {
        this.victimType = victimType;
        return this;
    }

    public byte victimType() {
        return this.victimType;
    }

    public ClientboundKillLogPacket victim(final int victim) {
        this.victim = victim;
        return this;
    }

    public int victim() {
        return this.victim;
    }

    public ClientboundKillLogPacket weaponBy(final int weaponBy) {
        this.weaponBy = weaponBy;
        return this;
    }

    public int weaponBy() {
        return this.weaponBy;
    }

    public ClientboundKillLogPacket hitpart(final int hitpart) {
        this.hitpart = hitpart;
        return this;
    }

    public int hitpart() {
        return this.hitpart;
    }

    @Override
    public int packetId() {
        return 45;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeByte(this.killerType);
        buf.writeInt(this.killer);
        buf.writeByte(this.victimType);
        buf.writeInt(this.victim);
        buf.writeInt(this.weaponBy);
        buf.writeInt(this.hitpart);
    }
}
