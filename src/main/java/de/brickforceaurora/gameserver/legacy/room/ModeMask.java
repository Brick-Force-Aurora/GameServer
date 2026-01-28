package de.brickforceaurora.gameserver.legacy.room;

public enum ModeMask {
    TEAM_MATCH_MASK(1),
    INDIVIDUAL_MATCH_MASK(2),
    CAPTURE_THE_FALG_MATCH(4),
    EXPLOSION_MATCH(8),
    MISSION_MASK(0x10),
    BND_MASK(0x20),
    BUNGEE_MASK(0x40),
    ESCAPE_MASK(0x80),
    ZOMBIE_MASK(0x100),
    ALL_MASK(0x7FFF);

    private final int id;

    ModeMask(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
