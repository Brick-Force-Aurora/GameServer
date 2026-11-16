package de.brickforceaurora.server.match.item;

public record ItemEntry(String code, boolean tool, String slot, boolean takeoffable, int category) {

    public boolean weapon() {
        return !tool && ("melee".equals(slot) || "aux".equals(slot) || "main".equals(slot) || "bomb".equals(slot));
    }

    public boolean wearable() {
        return !tool;
    }

}
