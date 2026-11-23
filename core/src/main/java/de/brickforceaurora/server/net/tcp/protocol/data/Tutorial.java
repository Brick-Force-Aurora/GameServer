package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum Tutorial implements IFlag {

    BATTLE(0x1),
    BUILD(0x2);

    public static final FlagManager<Tutorial> MANAGER = FlagManager.ofEnum(Tutorial.class);

    private final int mask;

    Tutorial(final int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
