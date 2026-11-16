package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.isEditor = buf.readBoolean();
    }
}