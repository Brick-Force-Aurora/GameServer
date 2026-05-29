package de.brickforceaurora.server.match;

import java.nio.file.Paths;
import java.util.Arrays;

import de.brickforceaurora.server.IBrickForceServer;
import de.brickforceaurora.server.match.gamemode.GameModeManager;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.net.BrickForceServer;
import de.brickforceaurora.server.net.login.DevLoginHandler;
import de.brickforceaurora.server.net.login.ILoginHandler;
import de.brickforceaurora.server.util.AnsiSysOutLogger;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecycleBuilder;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;

public class MatchServerApp implements ISnowFrameApp<MatchServerApp>, IBrickForceServer {

    private static SnowFrame<MatchServerApp> snowFrame;

    static SnowFrame<MatchServerApp> init(final String[] args) {
        if (snowFrame != null) {
            return snowFrame;
        }

        // TODO: Do actual command line parsing
        final ISimpleLogger logger = AnsiSysOutLogger.INSTANCE;
        logger.setDebug(Arrays.stream(args).anyMatch(str -> "--debug".equalsIgnoreCase(str)));
        logger.setTracking(Arrays.stream(args).anyMatch(str -> "--trace".equalsIgnoreCase(str)));

        return snowFrame = SnowFrame.builder(new MatchServerApp()).logger(logger).build();
    }

    public static SnowFrame<MatchServerApp> get() {
        return snowFrame;
    }

    public static MatchServerApp app() {
        return snowFrame.app();
    }

    public static ISimpleLogger logger() {
        return snowFrame.logger();
    }

    private RoomManager roomManager;
    private GameModeManager gameModeManager;

    private BrickForceServer<MatchServerApp> server;
    private ILoginHandler loginHandler;

    @Override
    public void setupLifecycle(LifecycleBuilder<MatchServerApp> builder) {
        builder.startupChain().newPhase("server-setup", true).newPhase("server-start", true);
    }

    @Override
    public void registerLifecycle(Lifecycle<MatchServerApp> lifecycle) {
        lifecycle.startupChain().register("load", Stage.PRE, frame -> {
            frame.resourceManager().register("data", Paths.get("data"));
        }).register("ready", Stage.MAIN, frame -> {
            gameModeManager = new GameModeManager(frame);
        });
        lifecycle.startupChain().register("server-setup", Stage.MAIN, _ -> {
            roomManager = new RoomManager();
        });
        lifecycle.startupChain().register("server-start", Stage.PRE, frame -> {
            server = new BrickForceServer<>(frame, this);
            loginHandler = new DevLoginHandler(server.netManager());
        }).register("server-start", Stage.MAIN, _ -> {
            server.open();
        });
    }

    @Override
    public SnowFrame<MatchServerApp> snowFrame() {
        return snowFrame;
    }

    @Override
    public ILoginHandler loginHandler() {
        return loginHandler;
    }

    public RoomManager roomManager() {
        return roomManager;
    }

    public GameModeManager gameModeManager() {
        return gameModeManager;
    }
}
