package de.brickforceaurora.gameserver;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.gameserver.net.NetManager;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.tick.AbstractTickTimer;

public class GameServer extends AbstractTickTimer {

    public static final long TARGET_TPS = 60;

    private static final float SECOND_IN_NANOS = TimeUnit.SECONDS.toNanos(1);

    private final ISimpleLogger logger;

    private final NetManager netManager;

    public GameServer(final SnowFrame<GameServerApp> frame) {
        this.logger = frame.logger();
        this.netManager = new NetManager(frame);

        // Setup timer
        setName("GameServer");
        sync().length((long) Math.floor(SECOND_IN_NANOS / TARGET_TPS), TimeUnit.NANOSECONDS);
    }

    public NetManager netManager() {
        return netManager;
    }

    public ISimpleLogger logger() {
        return logger;
    }

    @Override
    protected void tick(final long delta) {
        // Calculate floating point delta time
        final float deltaTime = delta / SECOND_IN_NANOS;

        // Tick net manager
        netManager.tick(delta);
    }

    @Override
    protected Thread createThread(final Runnable runnable) {
        return Thread.ofVirtual().unstarted(runnable);
    }

}
