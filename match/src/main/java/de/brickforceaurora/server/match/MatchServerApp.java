package de.brickforceaurora.server.match;

import java.nio.file.Paths;
import java.util.Arrays;

import de.brickforceaurora.server.IBrickForceServer;
import de.brickforceaurora.server.ILoginHandler;
import de.brickforceaurora.server.net.BrickForceServer;
import de.brickforceaurora.server.util.AnsiSysOutLogger;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
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

    private BrickForceServer<MatchServerApp> server;

    @Override
    public void registerLifecycle(Lifecycle<MatchServerApp> lifecycle) {
        lifecycle.startupChain().register("load", Stage.PRE, frame -> {
            frame.resourceManager().register("data", Paths.get("data"));
        });
        lifecycle.startupChain().register("load", Stage.MAIN, frame -> {
            server = new BrickForceServer<>(frame);
        });
        lifecycle.startupChain().register("ready", Stage.MAIN, _ -> {
            server.start();
            server.netManager().open();
        });
    }

    @Override
    public SnowFrame<MatchServerApp> snowFrame() {
        return snowFrame;
    }

    @Override
    public ILoginHandler loginHandler() {
        return null;
    }

}
