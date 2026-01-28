package de.brickforceaurora.gameserver.legacy.protocol;

import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.match.MatchData;

public final class MsgReference {

    public final Msg2Handle msg;
    public final ClientReference client;
    public final SendType sendType;
    public final ChannelReference channelRef;
    public final MatchData matchData;

    public MsgReference(final Msg2Handle msg, final ClientReference client, final SendType sendType, final ChannelReference channelRef,
        final MatchData matchData) {
        this.msg = msg;
        this.client = client;
        this.sendType = sendType != null ? sendType : SendType.UNICAST;
        this.channelRef = channelRef;
        this.matchData = matchData;
    }

    public MsgReference(final MessageId id, final MsgBody body, final ClientReference client, final SendType sendType,
        final ChannelReference channelRef, final MatchData matchData) {
        this(new Msg2Handle(id, body), client, sendType, channelRef, matchData);
    }

    public MsgReference(final MessageId id, final MsgBody body, final ClientReference client) {
        this(id, body, client, SendType.UNICAST, null, null);
    }

    public MsgReference(final MessageId id, final MsgBody body, final ClientReference client, final SendType sendType) {
        this(id, body, client, sendType, null, null);
    }
}
