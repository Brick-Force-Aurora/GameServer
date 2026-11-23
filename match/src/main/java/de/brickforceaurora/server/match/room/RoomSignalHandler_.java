package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.tcp.NetSignal;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class RoomSignalHandler_ implements ISignalHandler {

    private RoomManager roomManager;

    @SignalHandler
    public void onServerStarted(final SignalContext<NetSignal.ServerStarted> context) {
        roomManager = ((MatchServerApp) context.signal().netManager().snowFrame().app()).roomManager();
        roomManager.setNetManager(context.signal().netManager());
    }

    @SignalHandler
    public void onClientDisconnect(final SignalContext<NetSignal.ClientDisconnected> context) {
        roomManager.leaveRoom(context.signal().client());
    }

    @SignalHandler
    public void onServerStopped(final SignalContext<NetSignal.ServerStopped> context) {
        roomManager.setNetManager(null);
    }
}
