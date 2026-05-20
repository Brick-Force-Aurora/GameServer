package de.brickforceaurora.server.net.protocol.data.api;

import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;

public interface IRoomInfo {
    
    int id();
    
    RoomType type();
    
    String title();
    
    boolean passwordLocked();
    
    RoomStatus status();
    
    int players();
    
    int maxPlayers();
    
    int mapId();
    
    String mapAlias();
    
    int goal();
    
    int timeLimit();
    
    // TODO: Enum?
    int weaponOption();
    
    // WHY PING
    int ping();
    
    int blueScore();
    
    int redScore();
    
    CountryFilter countryFilter();
    
    boolean allowsLateJoining();
    
    boolean weaponDropEnabled();
    
    boolean wantedEnabled();
    
    // What is squad?
    int squad();
    
    // What is squad counter?
    int squadCounter();
    
    static void toBuffer(PacketBuf buf, IRoomInfo info) {
        buf.writeInt(info.id());
        buf.writeInt(info.type().id());
        buf.writeString(info.title());
        buf.writeBoolean(info.passwordLocked());
        buf.writeInt(info.status().id());
        buf.writeInt(info.players());
        buf.writeInt(info.maxPlayers());
        buf.writeInt(info.mapId());
        buf.writeString(info.mapAlias());
        buf.writeInt(info.goal());
        buf.writeInt(info.timeLimit());
        buf.writeInt(info.weaponOption());
        buf.writeInt(info.ping());
        buf.writeInt(info.blueScore());
        buf.writeInt(info.redScore());
        buf.writeInt(info.countryFilter().id());
        buf.writeBoolean(info.allowsLateJoining());
        buf.writeBoolean(info.weaponDropEnabled());
        buf.writeBoolean(info.wantedEnabled());
        buf.writeInt(info.squad());
        buf.writeInt(info.squadCounter());
    }

}
