package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.NetSignal;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ClientListener_ implements ISignalHandler {

    @SignalHandler
    public void onLogin(SignalContext<NetSignal.ClientLoggedIn> context) {

    }

}
