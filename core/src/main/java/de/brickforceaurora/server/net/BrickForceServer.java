package de.brickforceaurora.server.net;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.IBrickForceServer;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.tick.AbstractTickTimer;

public final class BrickForceServer<S extends ISnowFrameApp<S> & IBrickForceServer> extends AbstractTickTimer {

    public static final long TARGET_TPS = 60;

    private static final float SECOND_IN_NANOS = TimeUnit.SECONDS.toNanos(1);

    private final ISimpleLogger logger;
    private final NetManager<S> netManager;

    public BrickForceServer(final SnowFrame<S> frame) {
        this.logger = frame.logger();
        this.netManager = new NetManager<>(frame);
        frame.invoker().addExtra(this);

        // Setup timer
        setName("BrickForce Server");
        sync().length((long) Math.floor(SECOND_IN_NANOS / TARGET_TPS), TimeUnit.NANOSECONDS);
    }

    @Override
    protected Thread createThread(Runnable runnable) {
        return Thread.ofVirtual().unstarted(runnable);
    }

    @Override
    protected void tick(long delta) {
        netManager.tick(delta);
    }

    public NetManager<S> netManager() {
        return netManager;
    }

    public ISimpleLogger logger() {
        return logger;
    }

}
