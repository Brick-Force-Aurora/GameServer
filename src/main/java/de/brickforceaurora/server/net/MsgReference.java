package de.brickforceaurora.server.net;

import de.brickforceaurora.server.protocol.Msg2Handle;
import de.brickforceaurora.server.protocol.MsgBody;
import de.brickforceaurora.server.match.MatchData;

public final class MsgReference {

    public final Msg2Handle msg;
    public final ClientReference client;
    public final SendType sendType;
    public final ChannelReference channelRef;
    public final MatchData matchData;

    public MsgReference(Msg2Handle msg,
                        ClientReference client,
                        SendType sendType,
                        ChannelReference channelRef,
                        MatchData matchData) {
        this.msg = msg;
        this.client = client;
        this.sendType = (sendType != null) ? sendType : SendType.UNICAST;
        this.channelRef = channelRef;
        this.matchData = matchData;
    }

    public MsgReference(int id,
                        MsgBody body,
                        ClientReference client,
                        SendType sendType,
                        ChannelReference channelRef,
                        MatchData matchData) {
        this(new Msg2Handle(id, body), client, sendType, channelRef, matchData);
    }

    public MsgReference(int id, MsgBody body, ClientReference client) {
        this(id, body, client, SendType.UNICAST, null, null);
    }
}
