package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUpdateScriptPacket implements IServerboundPacket {

    private int clientId;
    private String alias;
    private boolean enableOnAwake;
    private boolean visibleOnAwake;
    private String commands;

    public ServerboundUpdateScriptPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundUpdateScriptPacket alias(final String alias) {
        this.alias = alias;
        return this;
    }

    public String alias() {
        return this.alias;
    }

    public ServerboundUpdateScriptPacket enableOnAwake(final boolean enableOnAwake) {
        this.enableOnAwake = enableOnAwake;
        return this;
    }

    public boolean enableOnAwake() {
        return this.enableOnAwake;
    }

    public ServerboundUpdateScriptPacket visibleOnAwake(final boolean visibleOnAwake) {
        this.visibleOnAwake = visibleOnAwake;
        return this;
    }

    public boolean visibleOnAwake() {
        return this.visibleOnAwake;
    }

    public ServerboundUpdateScriptPacket commands(final String commands) {
        this.commands = commands;
        return this;
    }

    public String commands() {
        return this.commands;
    }

    @Override
    public int packetId() {
        return 167;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
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
        this.enableOnAwake = buffer.readBoolean();
        this.visibleOnAwake = buffer.readBoolean();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.commands = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.commands = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}