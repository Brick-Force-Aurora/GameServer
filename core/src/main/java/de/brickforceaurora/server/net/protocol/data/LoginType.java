package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum LoginType implements IFlag {

    PASSWORD(0x1),
    TRANSFER_TOKEN(0x2),
    OAUTH(0x4);

    public static final FlagManager<LoginType> MANAGER = FlagManager.ofEnum(LoginType.class);

    private final int mask;

    private LoginType(int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
