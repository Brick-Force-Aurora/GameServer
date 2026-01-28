package de.brickforceaurora.gameserver.legacy.data;

import de.brickforceaurora.gameserver.legacy.channel.BrickManStatus;
import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientStatus;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.match.SlotData;
import de.brickforceaurora.gameserver.net.BFClient;

public class ClientData {
    
    public boolean isLoaded;
    public boolean isHost;
    public int kills = 0;
    public int deaths = 0;
    public int assists = 0;
    public int score = 0;
    public boolean isZombie = false;
    public boolean isBreakingInto;
    public float toleranceTime;
    public ClientStatus clientStatus;
    public BrickManStatus status;
    public SlotData slot;
    public Inventory inventory;
    public DummyData data;
    public MatchData matchData;
    public ChannelReference channel;
    public int xp = 7_000_000;
    public int rank = 65;
    public int extraSlots = 6;
    public int lastOpenedChestSeq = -1;
    
    public final BFClient client;
    
    public ClientData(BFClient client) {
        this.client = client;
    }

}
