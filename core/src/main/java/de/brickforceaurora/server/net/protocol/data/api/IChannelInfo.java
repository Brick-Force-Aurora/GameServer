package de.brickforceaurora.server.net.protocol.data.api;

import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ChannelMode;

public interface IChannelInfo {
    
    int id();
    
    ChannelMode mode();
    
    String name();
    
    String ip();
    
    int port();
    
    int users();
    
    int maxUsers();
    
    int country();
    
    int minRank();
    
    int maxRank();
    
    int xpBonus();
    
    int fpBonus();
    
    int starRatingLimit();
    
    static void toBuffer(PacketBuf buf, IChannelInfo info) {
        buf.writeInt(info.id());
        buf.writeInt(info.mode().id());
        buf.writeString(info.name());
        buf.writeString(info.ip());
        buf.writeInt(info.port());
        buf.writeInt(info.users());
        buf.writeInt(info.maxUsers());
        buf.writeInt(info.country());
        buf.writeByte(info.minRank());
        buf.writeByte(info.maxRank());
        buf.writeShort(info.xpBonus());
        buf.writeShort(info.fpBonus());
        buf.writeInt(info.starRatingLimit());
    }

}
