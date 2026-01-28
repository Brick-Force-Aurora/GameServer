package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundPlayerInitInfoPacket implements IClientboundPacket {

    private int xp;
    private byte tutorialFlag;
    private int country;
    private boolean tosAccepted;
    private int extraSlots;
    private int rank;
    private int firstLoginForcePoints;

    public ClientboundPlayerInitInfoPacket xp(final int xp) {
        this.xp = xp;
        return this;
    }

    public int xp() {
        return this.xp;
    }

    public ClientboundPlayerInitInfoPacket tutorialFlag(final byte tutorialFlag) {
        this.tutorialFlag = tutorialFlag;
        return this;
    }

    public byte tutorialFlag() {
        return this.tutorialFlag;
    }

    public ClientboundPlayerInitInfoPacket country(final int country) {
        this.country = country;
        return this;
    }

    public int country() {
        return this.country;
    }

    public ClientboundPlayerInitInfoPacket tosAccepted(final boolean tosAccepted) {
        this.tosAccepted = tosAccepted;
        return this;
    }

    public boolean tosAccepted() {
        return this.tosAccepted;
    }

    public ClientboundPlayerInitInfoPacket extraSlots(final int extraSlots) {
        this.extraSlots = extraSlots;
        return this;
    }

    public int extraSlots() {
        return this.extraSlots;
    }

    public ClientboundPlayerInitInfoPacket rank(final int rank) {
        this.rank = rank;
        return this;
    }

    public int rank() {
        return this.rank;
    }

    public ClientboundPlayerInitInfoPacket firstLoginForcePoints(final int firstLoginForcePoints) {
        this.firstLoginForcePoints = firstLoginForcePoints;
        return this;
    }

    public int firstLoginForcePoints() {
        return this.firstLoginForcePoints;
    }

    @Override
    public int packetId() {
        return 148;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.xp);
        buffer.writeByte(this.tutorialFlag);
        buffer.writeIntLE(this.country);
        buffer.writeBoolean(this.tosAccepted);
        buffer.writeIntLE(this.extraSlots);
        buffer.writeIntLE(this.rank);
        buffer.writeIntLE(this.firstLoginForcePoints);
    }
}