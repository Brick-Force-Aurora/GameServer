package de.brickforceaurora.gameserver.legacy.item;

public enum Pimp {
    PROP_NONE(-1),
    PROP_ATK_POW(0),
    PROP_ACCURACY(1),
    PROP_RECOIL(2),
    PROP_RPM(3),
    PROP_AMMO_MAX(4),
    PROP_ATTACK_SPEED(5),
    PROP_THROW_FORCE(6),
    PROP_EFFECT_RADIUS(7),
    PROP_EFFECT_TIME(8),
    PROP_BONUS_XP(9),
    PROP_BONUS_POINT(10),
    PROP_ARMOR(11),
    PROP_LUCK(12),
    PROP_MAX(13);

    public final int id;

    Pimp(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
