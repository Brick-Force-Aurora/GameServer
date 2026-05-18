package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum ChannelMode implements IFlag {

    NEWBIE(1),
    BATTLE(2),
    MAPEDIT(3),
    CLAN(4);

    public static final FlagManager<ChannelMode> MANAGER = FlagManager.ofEnum(ChannelMode.class);

    private final int mask;

    private ChannelMode(int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }
}
