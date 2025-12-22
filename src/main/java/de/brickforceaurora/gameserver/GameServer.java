package de.brickforceaurora.gameserver;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.util.tick.AbstractTickTimer;

public class GameServer extends AbstractTickTimer {

    public static final long TARGET_TPS = 60;

    private static final float SECOND_IN_NANOS = TimeUnit.SECONDS.toNanos(1);

    private final ISimpleLogger logger;
    private final GameServerLogic logic;

    public GameServer(ISimpleLogger logger) {
        this.logger = logger;
        this.logic = new GameServerLogic(logger);

        // Setup timer
        setDaemon(false);
        setName("GameServer");
        setLength((long) Math.floor(SECOND_IN_NANOS / TARGET_TPS), TimeUnit.NANOSECONDS);
    }

    @Deprecated
    public GameServerLogic logic() {
        return logic;
    }

    @Override
    protected void tick(long delta) {
        // Calculate floating point delta time
        float deltaTime = delta / SECOND_IN_NANOS;

        // Tick game server logic class
        logic.tick(deltaTime);
    }

}
