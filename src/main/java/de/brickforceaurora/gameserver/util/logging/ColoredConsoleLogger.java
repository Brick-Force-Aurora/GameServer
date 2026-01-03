package de.brickforceaurora.gameserver.util.logging;

import me.lauriichan.laylib.logger.AbstractSimpleLogger;

public final class ColoredConsoleLogger extends AbstractSimpleLogger {

    public static final ColoredConsoleLogger INSTANCE = new ColoredConsoleLogger();

    private ColoredConsoleLogger() {}


    @Override
    protected void info(String message) {
        System.out.println(Ansi.GREEN + "[INFO] " + message + Ansi.RESET);
    }

    @Override
    protected void warning(String message) {
        System.out.println(Ansi.YELLOW + "[WARN] " + message + Ansi.RESET);
    }

    @Override
    protected void debug(String message) {
        System.out.println(Ansi.CYAN + "[DEBUG] " + message + Ansi.RESET);
    }

    @Override
    protected void error(String message) {
        System.err.println(Ansi.RED + "[ERROR] " + message + Ansi.RESET);
    }

    @Override
    protected void track(String message) {
        System.err.println(Ansi.GRAY + "[TRACE] " + message + Ansi.RESET);
    }
}
