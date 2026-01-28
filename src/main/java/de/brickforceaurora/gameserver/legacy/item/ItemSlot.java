package de.brickforceaurora.gameserver.legacy.item;

public enum ItemSlot {
    UPPER(0),
    LOWER(1),
    MELEE(2),
    AUX(3),
    MAIN(4),
    BOMB(5),
    HEAD(6),
    FACE(7),
    BACK(8),
    LEG(9),
    SASH1(10),
    SASH2(11),
    SASH3(12),
    LAUNCHER(13),
    MAGAZINE_L(14),
    MAGAZINE_R(15),
    KIT(16),
    CHARACTER(17),
    NUM(18),
    NONE(-1);

    public final int value;

    ItemSlot(int v) {
        value = v;
    }
}
