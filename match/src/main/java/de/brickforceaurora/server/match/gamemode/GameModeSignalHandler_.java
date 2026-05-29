package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.NetSignal;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public final class GameModeSignalHandler_ implements ISignalHandler {

    private final GameModeManager gameModeManager;

    public GameModeSignalHandler_(SnowFrame<MatchServerApp> frame) {
        gameModeManager = frame.app().gameModeManager();
    }

    @SignalHandler
    public void onServerStarted(final SignalContext<NetSignal.ServerStarted> context) {
        context.signal().netManager().registerListener(gameModeManager.netListener());
    }

    @SignalHandler
    public void onServerStopped(final SignalContext<NetSignal.ServerStopped> context) {
        context.signal().netManager().unregisterListener(gameModeManager.netListener().newContainer());
    }

}
