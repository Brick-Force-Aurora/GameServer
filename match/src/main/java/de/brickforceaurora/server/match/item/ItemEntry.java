package de.brickforceaurora.server.match.item;

public record ItemEntry(String code, boolean tool, String slot, boolean takeoffable) {

    public boolean weapon() {
        return !tool && (slot.equals("melee") || slot.equals("aux") || slot.equals("main") || slot.equals("bomb"));
    }

    public boolean wearable() {
        return !tool;
    }

}
