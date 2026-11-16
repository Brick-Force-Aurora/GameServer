package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.WeaponRatio;

public final class ServerboundWeaponHeldRatioPacket implements IServerboundPacket {

    private WeaponRatio[] heldRatio = {};

    public ServerboundWeaponHeldRatioPacket heldRatio(final WeaponRatio[] heldRatio) {
        this.heldRatio = heldRatio;
        return this;
    }

    public WeaponRatio[] heldRatio() {
        return this.heldRatio;
    }

    @Override
    public int packetId() {
        return 368;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.heldRatio = buf.readArray(WeaponRatio[]::new, WeaponRatio::fromBuffer);
    }
}
