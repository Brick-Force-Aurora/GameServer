package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public record WeaponRatio(int weapon, int ratio) {

    public static WeaponRatio fromBuffer(PacketBuf buf) {
        return new WeaponRatio(buf.readInt(), buf.readInt());
    }

}
