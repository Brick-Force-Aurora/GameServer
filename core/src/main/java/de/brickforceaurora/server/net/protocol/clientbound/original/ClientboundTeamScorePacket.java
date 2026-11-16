package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTeamScorePacket implements IClientboundPacket {

    private int redScore;
    private int blueScore;

    public ClientboundTeamScorePacket redScore(final int redScore) {
        this.redScore = redScore;
        return this;
    }

    public int redScore() {
        return this.redScore;
    }

    public ClientboundTeamScorePacket blueScore(final int blueScore) {
        this.blueScore = blueScore;
        return this;
    }

    public int blueScore() {
        return this.blueScore;
    }

    @Override
    public int packetId() {
        return 67;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.redScore);
        buf.writeInt(this.blueScore);
    }
}
