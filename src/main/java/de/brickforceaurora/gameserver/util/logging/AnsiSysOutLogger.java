package de.brickforceaurora.gameserver.util.logging;

import me.lauriichan.laylib.logger.AbstractSimpleLogger;

public final class AnsiSysOutLogger extends AbstractSimpleLogger {

    public static final AnsiSysOutLogger INSTANCE = new AnsiSysOutLogger();

    private static final String INFO = Ansi.GRAY + "[INFO] %s" + Ansi.RESET + "\n";
    private static final String WARNING = Ansi.YELLOW + "[WARN] %s" + Ansi.RESET + "\n";
    private static final String ERROR = Ansi.RED + "[ERROR] %s" + Ansi.RESET + "\n";
    private static final String DEBUG = Ansi.CYAN + "[DEBUG] %s" + Ansi.RESET + "\n";
    private static final String TRACE = Ansi.GREEN + "[TRACE] %s" + Ansi.RESET + "\n";

    private AnsiSysOutLogger() {
        if (INSTANCE != null) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void info(final String message) {
        System.out.printf(INFO, message);
    }

    @Override
    protected void warning(final String message) {
        System.out.printf(WARNING, message);
    }

    @Override
    protected void error(final String message) {
        System.out.printf(ERROR, message);
    }

    @Override
    protected void debug(final String message) {
        System.out.printf(DEBUG, message);
    }

    @Override
    protected void track(final String message) {
        System.out.printf(TRACE, message);
    }
}
