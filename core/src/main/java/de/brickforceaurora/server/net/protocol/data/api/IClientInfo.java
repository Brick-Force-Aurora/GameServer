package de.brickforceaurora.server.net.protocol.data.api;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public interface IClientInfo {
    
    int id();
    
    String name();
    
    int xp();
    
    int rank();
    
    static void toBuffer(PacketBuf buf, IClientInfo info) {
        buf.writeInt(info.id());
        buf.writeString(info.name());
        buf.writeInt(info.xp());
        buf.writeInt(info.rank());
    }

}
