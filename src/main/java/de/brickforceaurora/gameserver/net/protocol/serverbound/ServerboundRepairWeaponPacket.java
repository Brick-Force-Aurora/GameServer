package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.item = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.code = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.code = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.buyHow = buffer.readIntLE();
        this.repairFee = buffer.readIntLE();
    }
}