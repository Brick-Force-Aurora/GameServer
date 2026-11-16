package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChatPacket implements IClientboundPacket {

    private int clientId;
    private byte chatType;
    private String nickname;
    private String text;
    private boolean gameMaster;

    public ClientboundChatPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundChatPacket chatType(final byte chatType) {
        this.chatType = chatType;
        return this;
    }

    public byte chatType() {
        return this.chatType;
    }

    public ClientboundChatPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ClientboundChatPacket text(final String text) {
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    public ClientboundChatPacket gameMaster(final boolean gameMaster) {
        this.gameMaster = gameMaster;
        return this;
    }

    public boolean gameMaster() {
        return this.gameMaster;
    }

    @Override
    public int packetId() {
        return 25;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeByte(this.chatType);
        buf.writeString(this.nickname);
        buf.writeString(this.text);
        buf.writeBoolean(this.gameMaster);
    }
}
