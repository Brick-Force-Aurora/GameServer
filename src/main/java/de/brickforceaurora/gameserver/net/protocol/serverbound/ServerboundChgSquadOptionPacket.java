package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.wannaPlayMap = buffer.readIntLE();
        this.wannaPlayMode = buffer.readIntLE();
        this.maxPlayers = buffer.readIntLE();
    }
}