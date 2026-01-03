package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.data.DummyData;
import de.brickforceaurora.gameserver.data.Inventory;
import de.brickforceaurora.gameserver.handler.RoomHandlers;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.match.SlotData;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;

public final class ClientReference {

    public Channel socket;
    public byte[] buffer = new byte[8192];
    public String ip;
    public int port;
    public float lastHeartBeatTime;
    public String name;
    public int seq;
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
    //public ChunkedBuffer chunkedBuffer;
    public int lastOpenedChestSeq = -1;

    public ClientReference(Channel socket, String name, int seq) {
        this.socket = socket;
        this.name = name;
        this.seq = seq;
        this.data = new DummyData();
        InetSocketAddress addr = (InetSocketAddress) socket.remoteAddress();
        this.ip = addr.getAddress().getHostAddress();
        this.clientStatus = ClientStatus.INVALID;
        this.status = BrickManStatus.PLAYER_WAITING;
        this.isLoaded = false;
        this.isHost = false;
        this.toleranceTime = 0f;
    }

    public ClientReference(Channel socket) {
        this(socket, "", -1);
    }

    public boolean Disconnect(boolean send) {
        GameServerLogic logic = GameServerApp.get().server().logic();
        if (send) {
            RoomHandlers.SendLeave(logic, this);
            RoomHandlers.sendSlotData(logic, matchData);
        }
        if(matchData != null){
            matchData.RemoveClient(this);
        }
        if(channel != null){
            channel.removeClient(this);
        }
        return logic.clientList.remove(this);
    }

    public boolean Disconnect() {
        return this.Disconnect(true);
    }

    public String GetIdentifier()
    {
        return name + "-" + seq + "-" + ip;
    }

    public void DetachSlot()
    {
        if (slot == null)
            return;

        slot.client = null;
        slot.isUsed = false;
        slot = null;
    }

    public boolean AssignSlot(SlotData _slot)
    {
        if (_slot.isUsed || _slot.isLocked)
            return false;

        DetachSlot();

        slot = _slot;
        slot.client = this;
        slot.isUsed = true;
        return true;
    }
}
