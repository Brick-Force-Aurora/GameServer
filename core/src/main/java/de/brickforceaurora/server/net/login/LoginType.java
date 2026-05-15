package de.brickforceaurora.server.net.login;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum LoginType implements IFlag {

    PASSWORD(0x1),
    TRANSFER_TOKEN(0x2),
    OAUTH(0x4, false);

    public static final FlagManager<LoginType> MANAGER = FlagManager.ofEnum(LoginType.class);

    private final int mask;
    private final boolean supportsLoginPacket;

    private LoginType(int mask) {
        this(mask, true);
    }

    private LoginType(int mask, boolean supportsLoginPacket) {
        this.mask = mask;
        this.supportsLoginPacket = supportsLoginPacket;
    }

    @Override
    public int mask() {
        return mask;
    }

    public boolean supportsLoginPacket() {
        return supportsLoginPacket;
    }

}
