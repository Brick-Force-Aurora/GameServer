package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.util.flag.FlagManager;
import de.brickforceaurora.server.util.flag.IFlag;

public enum CommonOpt implements IFlag {

    DONOT_NEWBIE_CHANNEL_MSG(0x1),
    DONOT_BUNGEE_GUIDE(0x2),
    DONOT_MAPEDIT_GUIDE(0x4),
    DONOT_BND_GUIDE(0x8),
    DONOT_EXPLOSION_ATTACK_GUIDE(0x10),
    DONOT_EXPLOSION_DEFENCE_GUIDE(0x20),
    INVITE_MASK1_FRIEND(0x40),
    INVITE_MASK2_ALL_NO(0x80),
    WHISPER_MASK1_FRIEND(0x100),
    WHISPER_MASK2_ALL_NO(0x200),
    DONOT_BATTLE_GUIDE(0x400),
    DONOT_ZOMBIE_GUIDE(0x800),
    DONOT_FLAG_GUIDE(0x1000),
    DONOT_DEFENSE_GUIDE(0x2000),
    DONOT_ESCAPE_GUIDE(0x4000);

    public static final FlagManager<CommonOpt> MANAGER = FlagManager.ofEnum(CommonOpt.class);

    private final int mask;

    private CommonOpt(int mask) {
        this.mask = mask;
    }

    @Override
    public int mask() {
        return mask;
    }

}
