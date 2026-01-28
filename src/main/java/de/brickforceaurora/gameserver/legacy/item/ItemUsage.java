package de.brickforceaurora.gameserver.legacy.item;

public enum ItemUsage {
    NOT_USING(-1),
    UNEQUIP(0),
    EQUIP(1),
    DELETED(2);

    public final int id;

    ItemUsage(int id) {
        this.id = id;
    }

    public static ItemUsage fromId(int id) {
        for (ItemUsage m : values()) {
            if (m.id == id) return m;
        }
        return null;
    }
}
