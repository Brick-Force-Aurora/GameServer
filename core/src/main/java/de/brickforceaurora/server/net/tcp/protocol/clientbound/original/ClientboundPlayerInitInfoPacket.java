package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.tcp.protocol.data.Tutorial;
import de.brickforceaurora.server.util.flag.IFlags;

public final class ClientboundPlayerInitInfoPacket implements IClientboundPacket {

    private int xp;
    private IFlags<Tutorial> tutorials;
    private CountryFilter countryFilter;
    private boolean tosAccepted;
    private int extraSlots;
    private int rank;
    private int firstLoginFp;

    public ClientboundPlayerInitInfoPacket xp(final int xp) {
        this.xp = xp;
        return this;
    }

    public int xp() {
        return this.xp;
    }

    public ClientboundPlayerInitInfoPacket tutorials(final IFlags<Tutorial> tutorials) {
        this.tutorials = tutorials;
        return this;
    }

    public IFlags<Tutorial> tutorials() {
        return this.tutorials;
    }

    public ClientboundPlayerInitInfoPacket countryFilter(final CountryFilter countryFilter) {
        this.countryFilter = countryFilter;
        return this;
    }

    public CountryFilter countryFilter() {
        return this.countryFilter;
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

    public ClientboundPlayerInitInfoPacket firstLoginFp(final int firstLoginFp) {
        this.firstLoginFp = firstLoginFp;
        return this;
    }

    public int firstLoginFp() {
        return this.firstLoginFp;
    }

    @Override
    public int packetId() {
        return 148;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.xp);
        buf.writeByte(this.tutorials.value());
        buf.writeInt(this.countryFilter.id());
        buf.writeByte(this.tosAccepted ? 1 : 0);
        buf.writeInt(this.extraSlots);
        buf.writeInt(this.rank);
        buf.writeInt(this.firstLoginFp);
    }
}