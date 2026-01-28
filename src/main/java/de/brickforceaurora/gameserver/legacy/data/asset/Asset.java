package de.brickforceaurora.gameserver.legacy.data.asset;

public abstract class Asset {

    private final String name;
    private final int id;

    public Asset(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public final String name() {
        return name;
    }

    public final int id() {
        return id;
    }

}
