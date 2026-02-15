package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum MapTag implements IFlag {

    GOLD_RIBBON(0x2),
    MEDAL(0x4),
    GLORY(0x8),
    ABUSE(0x10);
    
    public static final FlagManager<MapTag> MANAGER = FlagManager.ofEnum(MapTag.class);

    private final int mask;

    private MapTag(int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
