package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRepairWeaponPacket implements IServerboundPacket {

    private long item;
    private String code;
    private int buyHow;
    private int repairFee;

    public ServerboundRepairWeaponPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundRepairWeaponPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundRepairWeaponPacket buyHow(final int buyHow) {
        this.buyHow = buyHow;
        return this;
    }

    public int buyHow() {
        return this.buyHow;
    }

    public ServerboundRepairWeaponPacket repairFee(final int repairFee) {
        this.repairFee = repairFee;
        return this;
    }

    public int repairFee() {
        return this.repairFee;
    }

    @Override
    public int packetId() {
        return 351;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.code = buf.readString();
        this.buyHow = buf.readInt();
        this.repairFee = buf.readInt();
    }
}