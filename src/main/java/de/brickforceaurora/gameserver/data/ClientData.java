package de.brickforceaurora.gameserver.data;

import de.brickforceaurora.gameserver.channel.BrickManStatus;
import de.brickforceaurora.gameserver.channel.ChannelReference;
import de.brickforceaurora.gameserver.channel.ClientStatus;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.match.SlotData;
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
    public int lastOpenedChestSeq = -1;
    
    public final BFClient client;
    
    public ClientData(BFClient client) {
        this.client = client;
    }

}
