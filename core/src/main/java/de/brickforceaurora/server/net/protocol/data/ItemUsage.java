package de.brickforceaurora.server.net.protocol.data;

public enum ItemUsage {

    NOT_USING(-1),
    UNEQUIP(0),
    EQUIP(1),
    DELETED(2);

    public static final ItemUsage[] VALUES = ItemUsage.values();

    public static ItemUsage byId(final int id) {
        for (final ItemUsage usage : VALUES) {
            if (usage.id == id) {
                return usage;
            }
        }
        return null;
    }

    private final int id;

    ItemUsage(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}