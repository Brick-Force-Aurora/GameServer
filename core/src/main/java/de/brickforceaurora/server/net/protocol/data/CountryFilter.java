package de.brickforceaurora.server.net.protocol.data;

public enum CountryFilter {

    NONE(-1),
    EU(0),
    GERMANY(1),
    FRANCE(2),
    POLAND(3),
    SPAIN(4),
    UNITED_KINGDOM(5),
    TURKEY(6),
    CANADA_F(7),
    CANADA_E(8),
    USA_EAST(9),
    USA_WEST(10),
    MEXICO(11),
    HONGKONG(12),
    TAIWAN(13),
    MALAYSIA(14),
    INDONESIA(15),
    USA(16),
    CANADA(17),
    K_NEWBIE(18),
    K_BATTLE(19),
    K_BUILD(20),
    K_CLAN(21),
    I_BEGINNER(22),
    I_BUILD(23),
    I_CLAN(24),
    I_MEDIUM(25),
    I_VETERAN(26),
    I_FUN(27);

    public static final CountryFilter[] VALUES = CountryFilter.values();

    public static CountryFilter byId(int id) {
        if (id < -1 || id > 27) {
            return null;
        }
        return VALUES[id + 1];
    }

    private final int id;

    private CountryFilter(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
