package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.NetSignal;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class RoomSignalHandler_ implements ISignalHandler {

    private final RoomManager roomManager;

    public RoomSignalHandler_(SnowFrame<MatchServerApp> frame) {
        roomManager = frame.app().roomManager();
    }

    @SignalHandler
    public void onClientDisconnect(SignalContext<NetSignal.ClientDisconnected> context) {
        roomManager.leaveRoom(context.signal().client());
    }

    @SignalHandler
    public void onServerStarted(SignalContext<NetSignal.ServerStarted> context) {
        roomManager.setNetManager(context.signal().netManager());
    }

    @SignalHandler
    public void onServerStopped(SignalContext<NetSignal.ServerStopped> context) {
        roomManager.setNetManager(null);
    }
}
