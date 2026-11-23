package de.brickforceaurora.server.login;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.command.Console;
import de.brickforceaurora.server.net.tcp.BrickForceServer;
import de.brickforceaurora.server.net.tcp.login.DevLoginHandler;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;
import me.lauriichan.snowframe.util.logger.FileLogger;

public class LoginServerApp extends ServerApp<LoginServerApp> {

    private static SnowFrame<LoginServerApp> snowFrame;

    static SnowFrame<LoginServerApp> init(final String[] args) {
        if (snowFrame != null) {
            return snowFrame;
        }

        // TODO: Do actual command line parsing
        final FileLogger logger = new FileLogger(new File("logs"), Console.INSTANCE);
        logger.setDebug(Arrays.stream(args).anyMatch(str -> "--debug".equalsIgnoreCase(str)));
        logger.setTracking(Arrays.stream(args).anyMatch(str -> "--trace".equalsIgnoreCase(str)));

        return snowFrame = SnowFrame.builder(new LoginServerApp()).logger(logger).build();
    }

    public static SnowFrame<LoginServerApp> get() {
        return snowFrame;
    }

    public static LoginServerApp app() {
        return snowFrame.app();
    }

    public static ISimpleLogger logger() {
        return snowFrame.logger();
    }

    private BrickForceServer<LoginServerApp> server;
    private ILoginHandler loginHandler;
    
    public LoginServerApp() {
        super(Main.version());
    }

    @Override
    public void registerLifecycle(Lifecycle<LoginServerApp> lifecycle) {
        super.registerLifecycle(lifecycle);
        lifecycle.startupChain().register("load", Stage.PRE, frame -> {
            frame.resourceManager().register("data", Paths.get("data"));
        });
        lifecycle.startupChain().register("load", Stage.MAIN, frame -> {
            server = new BrickForceServer<>(frame);
            loginHandler = new DevLoginHandler(server.netManager());
        });
        lifecycle.startupChain().register("ready", Stage.MAIN, frame -> {
            Console.INSTANCE.setup(frame);
            server.open();
        });
    }
    
    @Override
    public BrickForceServer<?> server() {
        return server;
    }

    @Override
    public SnowFrame<LoginServerApp> snowFrame() {
        return snowFrame;
    }
    
    @Override
    public ILoginHandler loginHandler() {
        return loginHandler;
    }
    
    @Override
    public int port() {
        return 18890;
    }

}
