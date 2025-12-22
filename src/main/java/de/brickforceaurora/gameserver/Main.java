package de.brickforceaurora.gameserver;

import me.lauriichan.snowframe.SnowFrame;

public final class Main {
    
    private Main() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws Exception {
        SnowFrame<GameServerApp> snowFrame = GameServerApp.init(args);
        snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_STARTUP);
        Runtime.getRuntime()
            .addShutdownHook(new Thread(() -> snowFrame.lifecycle().execute(SnowFrame.LIFECYCLE_CHAIN_SHUTDOWN), "ShutdownHandler"));
    }

}
