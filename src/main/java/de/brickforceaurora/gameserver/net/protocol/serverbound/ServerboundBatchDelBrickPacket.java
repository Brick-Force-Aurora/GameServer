package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBatchDelBrickPacket implements IServerboundPacket {

    private int[] bricks;

    public final ServerboundBatchDelBrickPacket bricks(int[] bricks) {
        this.bricks = bricks;
        return this;
    }

    public final int[] bricks() {
        return bricks;
    }

    @Override
    public int packetId() {
        return 479;
    }

    @Override
    public final void read(ByteBuf buffer) {
        {
            int length = buffer.readIntLE();
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = buffer.readIntLE();
            }
            this.bricks = values;
        }
    }
}