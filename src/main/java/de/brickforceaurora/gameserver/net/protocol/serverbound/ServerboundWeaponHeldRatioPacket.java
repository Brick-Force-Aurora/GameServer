package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.Long2FloatArrayMap;

public final class ServerboundWeaponHeldRatioPacket implements IServerboundPacket {

    private final Long2FloatArrayMap weaponHeldRatio = new Long2FloatArrayMap();

    public Long2FloatArrayMap weaponHeldRatio() {
        return weaponHeldRatio;
    }

    @Override
    public int packetId() {
        return 368;
    }

    @Override
    public void read(final ByteBuf buffer) {
        {
            weaponHeldRatio.clear();
            final int length = buffer.readIntLE();
            for (int i = 0; i < length; i++) {
                final long key = buffer.readLongLE();
                final float value = buffer.readFloatLE();
                weaponHeldRatio.put(key, value);
            }
        }
    }
}