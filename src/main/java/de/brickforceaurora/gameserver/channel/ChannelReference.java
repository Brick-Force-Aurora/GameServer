package de.brickforceaurora.gameserver.channel;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.gameserver.match.MatchData;

public final class ChannelReference {

    public Channel channel;
    public List<MatchData> matches;
    public List<ClientReference> clientList;

    private static final int MAX_ROOM_NUMBER = 100;

    public ChannelReference(Channel channel) {
        this.channel = channel;
        this.matches = new ArrayList<>();
        this.clientList = new ArrayList<>();
    }

    /* ===== Matches ===== */

    public MatchData getMatchByRoomNumber(int roomNumber) {
        for (MatchData m : matches) {
            if (m.room.no == roomNumber) {
                return m;
            }
        }
        return null;
    }

    public int getNextRoomNumber() {
        for (int i = 0; i < MAX_ROOM_NUMBER; i++) {
            boolean used = false;
            for (MatchData m : matches) {
                if (m.room.no == i) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                return i;
            }
        }
        return -1;
    }

    /* ===== Clients ===== */

    public void addClient(ClientReference client)
    {
        if (client.channel != null)
            client.channel.removeClient(client);
        client.channel = this;
        clientList.add(client);
        channel.userCount = clientList.size();
    }

    public void removeClient(ClientReference client) {
        client.socket = null;
        clientList.remove(client);
        channel.userCount = clientList.size();
    }

    /* ===== Match management ===== */

    public MatchData addNewMatch() {
        MatchData matchData = new MatchData();
        matchData.channel = this;
        matchData.roomCreated = true;
        matchData.room.no = getNextRoomNumber();
        matches.add(matchData);
        return matchData;
    }

    public void addMatch(MatchData match) {
        match.channel = this;
        matches.add(match);
    }

    public void removeMatch(MatchData match) {
        match.channel = null;
        matches.remove(match);
    }

    /* ===== Shutdown ===== */

    public void shutdown() {
        for (MatchData match : matches) {
            match.Shutdown();
            match.channel = null;
        }
        // intentionally NOT clearing lists (matches C# behavior)
    }
}
