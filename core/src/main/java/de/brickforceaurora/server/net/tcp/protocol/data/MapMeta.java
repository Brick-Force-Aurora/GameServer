package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum MapMeta implements IFlag {

    OFFICIAL(0x1),
    ALLOWS_CLAN_MATCHES(0x2),
    EXCLUDED_FROM_RANKING(0x4),
    BLOCKED(0x8);

    public static final FlagManager<MapMeta> MANAGER = FlagManager.ofEnum(MapMeta.class);

    private final int mask;

    MapMeta(final int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
