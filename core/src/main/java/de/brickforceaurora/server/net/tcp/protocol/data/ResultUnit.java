package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public record ResultUnit(boolean red, int clientId, String nickname, int kill, int death, int assist, int score, int point, int xp,
    int mission, int prevXp, int nextXp, long buff) {

    public void toBuffer(final PacketBuf buf) {
        buf.writeBoolean(red);
        buf.writeInt(clientId);
        buf.writeString(nickname);
        buf.writeInt(kill);
        buf.writeInt(death);
        buf.writeInt(assist);
        buf.writeInt(score);
        buf.writeInt(point);
        buf.writeInt(xp);
        buf.writeInt(mission);
        buf.writeInt(prevXp);
        buf.writeInt(nextXp);
        buf.writeLong(buff);
    }

}
