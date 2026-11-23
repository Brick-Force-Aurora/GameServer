package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.alias = buf.readString();
        this.enableOnAwake = buf.readBoolean();
        this.visibleOnAwake = buf.readBoolean();
        this.commands = buf.readString();
    }
}