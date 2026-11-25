package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.ChatType;

public final class ClientboundChatPacket implements IClientboundPacket {

    private int clientId;
    private ChatType chatType;
    private String nickname;
    private String text;
    private boolean gameMaster;
    
    public ClientboundChatPacket() {}
    
    public ClientboundChatPacket(int clientId, ChatType chatType, String name, boolean gameMaster, String text) {
        this.clientId = clientId;
        this.chatType = chatType;
        this.nickname = name;
        this.text = text;
        this.gameMaster = gameMaster;
    }

    public ClientboundChatPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundChatPacket chatType(final ChatType chatType) {
        this.chatType = chatType;
        return this;
    }

    public ChatType chatType() {
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
        buf.writeByte(this.chatType.id());
        buf.writeString(this.nickname);
        buf.writeString(this.text);
        buf.writeBoolean(this.gameMaster);
    }
}
