package de.brickforceaurora.server.match;

import me.lauriichan.snowframe.SnowFrame;

public final class Main {

    private Main() {
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) throws Exception {
        final SnowFrame<MatchServerApp> snowFrame = MatchServerApp.init(args);
        snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_STARTUP);
        Runtime.getRuntime()
            .addShutdownHook(new Thread(() -> snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_SHUTDOWN), "ShutdownHandler"));
    }

}
