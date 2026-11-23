package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public record WeaponRatio(int weapon, int ratio) {

    public static WeaponRatio fromBuffer(final PacketBuf buf) {
        return new WeaponRatio(buf.readInt(), buf.readInt());
    }

}
