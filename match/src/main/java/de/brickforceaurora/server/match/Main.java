package de.brickforceaurora.server.match;

import java.io.BufferedReader;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jline.reader.UserInterruptException;

import de.brickforceaurora.server.command.Console;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.resource.source.IDataSource;
import me.lauriichan.snowframe.util.Version;
import me.lauriichan.snowframe.util.logger.FileLogger;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException();
    }

    public static final String JAR_FILE_NAME;

    static {
        String jarName = null;
        try {
            jarName = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
        } catch (final URISyntaxException _) {
        }
        JAR_FILE_NAME = jarName;
    }

    private static final AtomicBoolean SHUT_DOWN = new AtomicBoolean(false);
    private static SnowFrame<MatchServerApp> snowFrame;
    private static Version version = new Version(0, 0, 0);
    
    private static Thread consoleThread;

    public static void main(final String[] args) throws Exception {
        snowFrame = MatchServerApp.init(args);

        // DO UPDATES BEFORE STARTUP CHAIN
        final IDataSource source = snowFrame.resource("META-INF/maven/de.brickforceaurora.server/match/pom.properties");
        if (source.exists()) {
            final Properties properties = new Properties();
            try (BufferedReader reader = source.openReader()) {
                properties.load(reader);
            }
            // If we don't know our version we choose 0.0.0 as its not a release
            if (properties.containsKey("version")) {
                version = Version.parse(properties.getProperty("version"));
            }
        }

        snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_STARTUP);

        Runtime.getRuntime().addShutdownHook(Thread.ofPlatform().name("Shutdown").unstarted(Main::shutdown));
        consoleThread = Thread.ofPlatform().daemon(true).name("Console").start(Main::listenToConsole);
    }

    private static void listenToConsole() {
        Console console = Console.INSTANCE;
        while (!isShutdown()) {
            try {
                String line = console.readLine();
                if (line == null || line.isBlank()) {
                    continue;
                }
                console.execute(line);
            } catch(UserInterruptException e) {
                if (isShutdown()) {
                    return;
                }
                snowFrame.logger().info("Shutting down because of user interrupt");
                shutdown();
            }
        }
    }

    public static Version version() {
        return version;
    }

    public static boolean isShutdown() {
        return SHUT_DOWN.get();
    }

    public static void shutdown() {
        if (!SHUT_DOWN.compareAndSet(false, true)) {
            return;
        }
        try {
            snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_SHUTDOWN);
        } finally {
            if (Thread.currentThread() != consoleThread) {
                consoleThread.interrupt();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException _) {}
            }
            if (snowFrame.logger() instanceof final FileLogger fileLogger) {
                fileLogger.close();
            }
        }
    }

}
