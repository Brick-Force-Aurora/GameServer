package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundJoinPacket implements IClientboundPacket {

    public enum JoinResult {
        TOO_MANY_PLAYERS(-2),
        WRONG_PASSWORD(-3),
        LOADING(-5),
        CANT_JOIN(-6),
        KICKED_OFF_CURRENT_ROOM(-8),
        CLAN_MATCH_SEARCHING(-10);

        private final int id;

        JoinResult(final int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }
    }

    private int roomIdOrResultId;

    public ClientboundJoinPacket roomId(final int roomId) {
        this.roomIdOrResultId = roomId;
        return this;
    }

    public ClientboundJoinPacket result(final JoinResult result) {
        this.roomIdOrResultId = result.id();
        return this;
    }

    @Override
    public int packetId() {
        return 29;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.roomIdOrResultId);
    }
}