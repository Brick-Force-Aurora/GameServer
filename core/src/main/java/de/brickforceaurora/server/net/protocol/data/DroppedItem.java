package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public record DroppedItem(int seq, String code, int ammo, int subAmmo, float x, float y, float z) {

    public void toBuffer(PacketBuf buf) {
        buf.writeInt(seq);
        buf.writeString(code);
        buf.writeInt(ammo);
        buf.writeInt(subAmmo);
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

}
