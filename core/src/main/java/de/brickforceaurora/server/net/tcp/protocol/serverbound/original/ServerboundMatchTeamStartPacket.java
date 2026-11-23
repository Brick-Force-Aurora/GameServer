package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundMatchTeamStartPacket implements IServerboundPacket {

    private int mode;
    private int maxPlayer;
    private int map;
    private int killCount;
    private int timeLimit;
    private int weaponOption;
    private String alias;
    private boolean wanted;

    public ServerboundMatchTeamStartPacket mode(final int mode) {
        this.mode = mode;
        return this;
    }

    public int mode() {
        return this.mode;
    }

    public ServerboundMatchTeamStartPacket maxPlayer(final int maxPlayer) {
        this.maxPlayer = maxPlayer;
        return this;
    }

    public int maxPlayer() {
        return this.maxPlayer;
    }

    public ServerboundMatchTeamStartPacket map(final int map) {
        this.map = map;
        return this;
    }

    public int map() {
        return this.map;
    }

    public ServerboundMatchTeamStartPacket killCount(final int killCount) {
        this.killCount = killCount;
        return this;
    }

    public int killCount() {
        return this.killCount;
    }

    public ServerboundMatchTeamStartPacket timeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public int timeLimit() {
        return this.timeLimit;
    }

    public ServerboundMatchTeamStartPacket weaponOption(final int weaponOption) {
        this.weaponOption = weaponOption;
        return this;
    }

    public int weaponOption() {
        return this.weaponOption;
    }

    public ServerboundMatchTeamStartPacket alias(final String alias) {
        this.alias = alias;
        return this;
    }

    public String alias() {
        return this.alias;
    }

    public ServerboundMatchTeamStartPacket wanted(final boolean wanted) {
        this.wanted = wanted;
        return this;
    }

    public boolean wanted() {
        return this.wanted;
    }

    @Override
    public int packetId() {
        return 251;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.mode = buf.readInt();
        this.maxPlayer = buf.readInt();
        this.map = buf.readInt();
        this.killCount = buf.readInt();
        this.timeLimit = buf.readInt();
        this.weaponOption = buf.readInt();
        this.alias = buf.readString();
        this.wanted = buf.readBoolean();
    }
}