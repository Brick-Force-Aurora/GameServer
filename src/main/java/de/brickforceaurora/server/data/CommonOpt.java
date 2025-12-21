package de.brickforceaurora.server.data;

public enum CommonOpt {
    DONOT_NEWBIE_CHANNEL_MSG(1),
    DONOT_BUNGEE_GUIDE(2),
    DONOT_MAPEDIT_GUIDE(4),
    DONOT_BND_GUIDE(8),
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

    private final int id;

    CommonOpt(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
