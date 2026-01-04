package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;

public final class ServerboundInflictedDamagePacket implements IServerboundPacket {
	
    private final Int2IntArrayMap inflictedDamage = new Int2IntArrayMap();
    
    public final Int2IntArrayMap inflictedDamage() {
        return inflictedDamage;
    }

	@Override
	public int packetId() {
		return 399;
	}

	@Override
	public final void read(ByteBuf buffer) {
	    {
	        inflictedDamage.clear();
            int length = buffer.readIntLE();
            for (int i = 0; i < length; i++) {
                int key = buffer.readIntLE();
                int value = buffer.readIntLE();
                inflictedDamage.put(key, value);
            }
	    }
	}
}