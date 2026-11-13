package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public record BrickInfo(int seq, int template, int x, int y, int z, int meshCode, int rotation, BrickScript script) {

    public void toBuffer(PacketBuf buf) {
        buf.writeInt(seq);
        buf.writeByte(template);
        buf.writeByte(x);
        buf.writeByte(y);
        buf.writeByte(z);
        buf.writeShort(meshCode);
        buf.writeByte(rotation);
        if (script == null) {
            buf.writeByte(0);
        } else {
            buf.writeByte(1);
            buf.writeString(script.alias());
            buf.writeBoolean(script.enableOnAwake());
            buf.writeBoolean(script.visibleOnAwake());
            buf.writeString(script.commands());
        }
    }

}
