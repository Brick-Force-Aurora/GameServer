package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum GameMode implements IFlag {

    TEAM_DEATH_MATCH(0x1),
    DEATH_MATCH(0x2),
    CAPTURE_THE_FLAG(0x4),
    DEMOLITION(0x8),
    DEFENSE(0x10),
    BUILD_AND_DESTROY(0x20),
    FREEFALL(0x40),
    ESCAPE(0x80),
    ZOMBIE(0x100);

    public static final FlagManager<GameMode> MANAGER = FlagManager.ofEnum(GameMode.class);

    private final int mask;

    private GameMode(int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
