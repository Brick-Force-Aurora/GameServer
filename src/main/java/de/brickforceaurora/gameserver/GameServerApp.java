package de.brickforceaurora.gameserver;

import java.nio.file.Paths;
import java.util.Arrays;

import de.brickforceaurora.gameserver.util.logging.AnsiSysOutLogger;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;

public class GameServerApp implements ISnowFrameApp<GameServerApp> {

    private static SnowFrame<GameServerApp> snowFrame;

    static SnowFrame<GameServerApp> init(String[] args) {
        if (snowFrame != null) {
            return snowFrame;
        }
        
        // TODO: Do actual command line parsing
        ISimpleLogger logger = AnsiSysOutLogger.INSTANCE;
        logger.setDebug(Arrays.stream(args).anyMatch(str -> str.equalsIgnoreCase("--debug")));
        
        return snowFrame = SnowFrame.builder(new GameServerApp()).logger(logger).build();
    }

    public static GameServerApp get() {
        return snowFrame.app();
    }
    
    public static ISimpleLogger logger() {
        return snowFrame.logger();
    }

    private GameServer server;

    @Override
    public void registerLifecycle(Lifecycle<GameServerApp> lifecycle) {
        lifecycle.startupChain().register("load", Stage.PRE, frame -> {
            frame.resourceManager().register("data", Paths.get("data")); 
        });
        lifecycle.startupChain().register("load", Stage.MAIN, frame -> {
            server = new GameServer(frame);
        });
        lifecycle.startupChain().register("ready", Stage.MAIN, _ -> {
            server.start();
            server.netManager().open();
            snowFrame.logger().info("Server ready.");
            snowFrame.logger().info("Press Ctrl+C to exit.");
        });
    }

    @Override
    public SnowFrame<GameServerApp> snowFrame() {
        return snowFrame;
    }

    public GameServer server() {
        return server;
    }

}
