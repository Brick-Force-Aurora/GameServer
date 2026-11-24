package de.brickforceaurora.server.match;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.file.Paths;
import java.util.Arrays;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.command.Console;
import de.brickforceaurora.server.database.Database;
import de.brickforceaurora.server.database.LoginRepository;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.match.config.HostConfig;
import de.brickforceaurora.server.match.game.GameModeManager;
import de.brickforceaurora.server.match.room.RoomManager;
import de.brickforceaurora.server.match.room.RoomPeers;
import de.brickforceaurora.server.match.user.ClaimPasswordLoginHandler;
import de.brickforceaurora.server.net.tcp.BrickForceServer;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecycleBuilder;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;
import me.lauriichan.snowframe.util.logger.FileLogger;

public final class MatchServerApp extends ServerApp<MatchServerApp> {

    private static SnowFrame<MatchServerApp> snowFrame;

    static SnowFrame<MatchServerApp> init(final String[] args) {
        if (snowFrame != null) {
            return snowFrame;
        }

        // TODO: Do actual command line parsing
        final FileLogger logger = new FileLogger(new File("logs"), Console.INSTANCE);
        logger.setDebug(Arrays.stream(args).anyMatch("--debug"::equalsIgnoreCase));
        logger.setTracking(Arrays.stream(args).anyMatch("--trace"::equalsIgnoreCase));

        return snowFrame = SnowFrame.builder(new MatchServerApp()).logger(logger).build();
    }

    public static SnowFrame<MatchServerApp> get() {
        return snowFrame;
    }

    private HostConfig config;

    private RoomManager roomManager;
    private GameModeManager gameModeManager;

    private Database database;
    private UserRepository userRepository;

    private BrickForceServer<MatchServerApp> server;
    private ILoginHandler loginHandler;
    
    public MatchServerApp() {
        super(Main.version());
    }

    @Override
    public void setupLifecycle(final LifecycleBuilder<MatchServerApp> builder) {
        super.setupLifecycle(builder);
        builder.startupChain().newPhase("server-setup", true).newPhase("server-start", true);
    }

    @Override
    public void registerLifecycle(final Lifecycle<MatchServerApp> lifecycle) {
        super.registerLifecycle(lifecycle);
        lifecycle.startupChain().register("load", Stage.PRE, frame -> {
            frame.resourceManager().register("config", Paths.get("config"));
            frame.resourceManager().register("data", Paths.get("data"));
        }).register("ready", Stage.MAIN, frame -> {
            config = frame.module(ConfigModule.class).manager().config(HostConfig.class);
            frame.logger().info("Server host from config: {0}:{1}", config.host(), config.port());
            checkHost(frame, config.host());
            roomManager = new RoomManager();
            frame.invoker().addExtra(roomManager);
            gameModeManager = new GameModeManager(frame);
        }).register("ready", Stage.POST, frame -> {
            Console.INSTANCE.setup(frame);
        });
        lifecycle.startupChain().register("server-setup", Stage.MAIN, _ -> {

        });
        lifecycle.startupChain().register("server-start", Stage.PRE, frame -> {
            database = new Database(Paths.get(frame.resource("data://users.db").getPath()));
            userRepository = new UserRepository(database);
            server = new BrickForceServer<>(frame);
            server.rendezvousManager().directory(new RoomPeers());
            loginHandler = new ClaimPasswordLoginHandler(frame, new LoginRepository(database));
        }).register("server-start", Stage.MAIN, _ -> {
            server.open();
        });
        lifecycle.shutdownChain().register("shutdown", Stage.PRE, frame -> {
            if (server != null) {
                server.close();
                frame.logger().info("Server closed");
            }
            // Save dirty configs
            frame.module(ConfigModule.class).manager().save();
        }).register("shutdown", Stage.POST, frame -> {
            if (database != null) {
                database.close();
                frame.logger().info("Database closed");
            }
        });
    }

    // The clients connect to this address and need it as an IPv4 address for the UDP relay
    private static void checkHost(final SnowFrame<MatchServerApp> frame, final String host) {
        InetAddress address = null;
        if (host != null) {
            try {
                address = InetAddress.ofLiteral(host);
            } catch (final IllegalArgumentException exception) {
            }
        }
        if (!(address instanceof Inet4Address)) {
            throw new IllegalStateException("The host in config/host.json must be an IPv4 address, not '%s'".formatted(host));
        }
        if (address.isLoopbackAddress() || address.isAnyLocalAddress()) {
            frame.logger().warning("The host '{0}' can only be reached from this machine, set the public address in config/host.json", host);
        }
    }

    @Override
    public String host() {
        return config.host();
    }

    @Override
    public int port() {
        return config.port();
    }

    @Override
    public SnowFrame<MatchServerApp> snowFrame() {
        return snowFrame;
    }

    @Override
    public ILoginHandler loginHandler() {
        return loginHandler;
    }

    @Override
    public BrickForceServer<MatchServerApp> server() {
        return server;
    }

    public Database database() {
        return database;
    }

    public UserRepository userRepository() {
        return userRepository;
    }

    public RoomManager roomManager() {
        return roomManager;
    }

    public GameModeManager gameModeManager() {
        return gameModeManager;
    }
}
