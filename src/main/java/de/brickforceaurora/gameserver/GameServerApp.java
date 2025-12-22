package de.brickforceaurora.gameserver;

import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;
import me.lauriichan.snowframe.util.logger.SysOutSimpleLogger;

public class GameServerApp implements ISnowFrameApp<GameServerApp> {

    private static SnowFrame<GameServerApp> snowFrame;

    static SnowFrame<GameServerApp> init(String[] args) {
        if (snowFrame != null) {
            return snowFrame;
        }
        return snowFrame = SnowFrame.builder(new GameServerApp()).logger(SysOutSimpleLogger.INSTANCE).build();
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
        lifecycle.startupChain().register("load", Stage.MAIN, frame -> {
            server = new GameServer(frame.logger());
        });
        lifecycle.startupChain().register("ready", Stage.MAIN, _ -> {
            server.start();
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
