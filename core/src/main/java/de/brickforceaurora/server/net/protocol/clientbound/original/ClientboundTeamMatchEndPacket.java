package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ResultUnit;

public final class ClientboundTeamMatchEndPacket implements IClientboundPacket {

    private byte endCode;
    private int redKill;
    private int redDeath;
    private int blueKill;
    private int blueDeath;
    private ResultUnit[] results = {};

    public ClientboundTeamMatchEndPacket endCode(final byte endCode) {
        this.endCode = endCode;
        return this;
    }

    public byte endCode() {
        return this.endCode;
    }

    public ClientboundTeamMatchEndPacket redKill(final int redKill) {
        this.redKill = redKill;
        return this;
    }

    public int redKill() {
        return this.redKill;
    }

    public ClientboundTeamMatchEndPacket redDeath(final int redDeath) {
        this.redDeath = redDeath;
        return this;
    }

    public int redDeath() {
        return this.redDeath;
    }

    public ClientboundTeamMatchEndPacket blueKill(final int blueKill) {
        this.blueKill = blueKill;
        return this;
    }

    public int blueKill() {
        return this.blueKill;
    }

    public ClientboundTeamMatchEndPacket blueDeath(final int blueDeath) {
        this.blueDeath = blueDeath;
        return this;
    }

    public int blueDeath() {
        return this.blueDeath;
    }

    public ClientboundTeamMatchEndPacket results(final ResultUnit[] results) {
        this.results = results;
        return this;
    }

    public ResultUnit[] results() {
        return this.results;
    }

    @Override
    public int packetId() {
        return 70;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeByte(this.endCode);
        buf.writeInt(this.redKill);
        buf.writeInt(this.redDeath);
        buf.writeInt(this.blueKill);
        buf.writeInt(this.blueDeath);
        buf.writeInt(this.results.length);
        for (final ResultUnit unit : this.results) {
            unit.toBuffer(buf);
        }
    }
}
