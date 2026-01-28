package de.brickforceaurora.gameserver.util.logging;

public final class Ansi {

    public static final String RESET = "\u001B[0m";

    public static final String GRAY = "\u001B[38;5;15m";
    public static final String YELLOW = "\u001B[38;5;226m";
    public static final String RED = "\u001B[38;5;197m";
    public static final String CYAN = "\u001B[38;5;87m";
    public static final String GREEN = "\u001B[38;5;84m";

    private Ansi() {
        throw new UnsupportedOperationException();
    }
}
