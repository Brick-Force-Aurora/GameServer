package de.brickforceaurora.server.util;

import me.lauriichan.laylib.logger.AbstractSimpleLogger;

public final class AnsiSysOutLogger extends AbstractSimpleLogger {

    public static final AnsiSysOutLogger INSTANCE = new AnsiSysOutLogger();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GRAY = "\u001B[38;5;15m";
    public static final String ANSI_YELLOW = "\u001B[38;5;226m";
    public static final String ANSI_RED = "\u001B[38;5;197m";
    public static final String ANSI_CYAN = "\u001B[38;5;87m";
    public static final String ANSI_GREEN = "\u001B[38;5;84m";

    public static final String INFO_FORMAT = ANSI_GRAY + "[INFO] %s" + ANSI_RESET + "\n";
    public static final String WARNING_FORMAT = ANSI_YELLOW + "[WARN] %s" + ANSI_RESET + "\n";
    public static final String ERROR_FORMAT = ANSI_RED + "[ERROR] %s" + ANSI_RESET + "\n";
    public static final String DEBUG_FORMAT = ANSI_CYAN + "[DEBUG] %s" + ANSI_RESET + "\n";
    public static final String TRACE_FORMAT = ANSI_GREEN + "[TRACE] %s" + ANSI_RESET + "\n";

    private AnsiSysOutLogger() {
        if (INSTANCE != null) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected void info(final String message) {
        System.out.printf(INFO_FORMAT, message);
    }

    @Override
    protected void warning(final String message) {
        System.out.printf(WARNING_FORMAT, message);
    }

    @Override
    protected void error(final String message) {
        System.out.printf(ERROR_FORMAT, message);
    }

    @Override
    protected void debug(final String message) {
        System.out.printf(DEBUG_FORMAT, message);
    }

    @Override
    protected void track(final String message) {
        System.out.printf(TRACE_FORMAT, message);
    }
}
