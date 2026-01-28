package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRoaminPacket implements IServerboundPacket {

    private int clientId;
    private int userType;
    private boolean isWebPlayer;
    private int language;
    private String hash;

    public ServerboundRoaminPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundRoaminPacket userType(final int userType) {
        this.userType = userType;
        return this;
    }

    public int userType() {
        return this.userType;
    }

    public ServerboundRoaminPacket isWebPlayer(final boolean isWebPlayer) {
        this.isWebPlayer = isWebPlayer;
        return this;
    }

    public boolean isWebPlayer() {
        return this.isWebPlayer;
    }

    public ServerboundRoaminPacket language(final int language) {
        this.language = language;
        return this;
    }

    public int language() {
        return this.language;
    }

    public ServerboundRoaminPacket hash(final String hash) {
        this.hash = hash;
        return this;
    }

    public String hash() {
        return this.hash;
    }

    @Override
    public int packetId() {
        return 145;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        this.userType = buffer.readIntLE();
        this.isWebPlayer = buffer.readBoolean();
        this.language = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.hash = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.hash = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}