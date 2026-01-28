package de.brickforceaurora.gameserver.channel;

import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.NetSignal;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ChannelSignalListenerTemplate implements ISignalHandler {
    
    @SignalHandler
    public void onClientJoinedChannel(SignalContext<NetSignal.ClientJoinedChannel> context) {
        NetSignal.ClientJoinedChannel signal = context.signal();
        Channel previous = signal.client().channel.getAndSet(signal.channel());
        if (previous != null) {
            previous.removeClient(signal.client());
        }
        signal.channel().addClient(signal.client());
    }

    @SignalHandler
    public void onClientDisconnected(SignalContext<NetSignal.ClientDisconnected> context) {
        BFClient client = context.signal().client();
        Channel channel = client.channel.get();
        if (channel != null) {
            channel.removeClient(client);
        }
    }

}
