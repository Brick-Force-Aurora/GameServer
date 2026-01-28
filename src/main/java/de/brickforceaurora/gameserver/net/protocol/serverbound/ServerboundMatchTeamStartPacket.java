package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.mode = buffer.readIntLE();
        this.maxPlayer = buffer.readIntLE();
        this.map = buffer.readIntLE();
        this.killCount = buffer.readIntLE();
        this.timeLimit = buffer.readIntLE();
        this.weaponOption = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.alias = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.alias = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.wanted = buffer.readBoolean();
    }
}