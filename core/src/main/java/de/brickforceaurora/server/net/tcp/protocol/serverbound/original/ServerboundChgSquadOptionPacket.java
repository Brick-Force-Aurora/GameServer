package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundChgSquadOptionPacket implements IServerboundPacket {

    private int wannaPlayMap;
    private int wannaPlayMode;
    private int maxPlayers;

    public ServerboundChgSquadOptionPacket wannaPlayMap(final int wannaPlayMap) {
        this.wannaPlayMap = wannaPlayMap;
        return this;
    }

    public int wannaPlayMap() {
        return this.wannaPlayMap;
    }

    public ServerboundChgSquadOptionPacket wannaPlayMode(final int wannaPlayMode) {
        this.wannaPlayMode = wannaPlayMode;
        return this;
    }

    public int wannaPlayMode() {
        return this.wannaPlayMode;
    }

    public ServerboundChgSquadOptionPacket maxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public int maxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public int packetId() {
        return 259;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.wannaPlayMap = buf.readInt();
        this.wannaPlayMode = buf.readInt();
        this.maxPlayers = buf.readInt();
    }
}