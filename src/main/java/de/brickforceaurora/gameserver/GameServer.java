package de.brickforceaurora.gameserver;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.net.NetManager;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.tick.AbstractTickTimer;

public class GameServer extends AbstractTickTimer {

    public static final long TARGET_TPS = 60;

    private static final float SECOND_IN_NANOS = TimeUnit.SECONDS.toNanos(1);

    private final ISimpleLogger logger;
    private final GameServerLogic logic;

    private final NetManager netManager;

    public GameServer(SnowFrame<GameServerApp> frame) {
        this.logger = frame.logger();
        this.netManager = new NetManager(frame);
        this.logic = new GameServerLogic(frame);

        // Setup timer
        setDaemon(false);
        setName("GameServer");
        sync().length((long) Math.floor(SECOND_IN_NANOS / TARGET_TPS), TimeUnit.NANOSECONDS);
    }

    public GameServerLogic logic() {
        return logic;
    }

    public ISimpleLogger logger() {
        return logger;
    }

    @Override
    protected void tick(long delta) {
        // Calculate floating point delta time
        float deltaTime = delta / SECOND_IN_NANOS;

        // Tick game server logic class
        logic.tick(deltaTime);

        // Tick net manager
        netManager.tick(delta);
    }

    @Override
    protected Thread createThread(Runnable runnable) {
        return Thread.ofVirtual().unstarted(runnable);
    }

}
