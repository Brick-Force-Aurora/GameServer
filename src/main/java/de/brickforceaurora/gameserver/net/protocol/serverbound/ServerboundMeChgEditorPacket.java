package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMeChgEditorPacket implements IServerboundPacket {

    private int clientId;
    private boolean isEditor;

    public ServerboundMeChgEditorPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundMeChgEditorPacket isEditor(final boolean isEditor) {
        this.isEditor = isEditor;
        return this;
    }

    public boolean isEditor() {
        return this.isEditor;
    }

    @Override
    public int packetId() {
        return 304;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        this.isEditor = buffer.readBoolean();
    }
}