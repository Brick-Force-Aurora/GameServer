package de.brickforceaurora.server.util;

import me.lauriichan.snowframe.util.logger.IDelegateLogger;

public final class AnsiSysOutLogger implements IDelegateLogger {

    public static final AnsiSysOutLogger INSTANCE = new AnsiSysOutLogger();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GRAY = "\u001B[38;5;15m";
    public static final String ANSI_YELLOW = "\u001B[38;5;226m";
    public static final String ANSI_RED = "\u001B[38;5;197m";
    public static final String ANSI_CYAN = "\u001B[38;5;87m";
    public static final String ANSI_GREEN = "\u001B[38;5;84m";

    public static final String INFO_FORMAT = ANSI_GRAY + "%s" + ANSI_RESET + "\n";
    public static final String WARNING_FORMAT = ANSI_YELLOW + "%s" + ANSI_RESET + "\n";
    public static final String ERROR_FORMAT = ANSI_RED + "%s" + ANSI_RESET + "\n";
    public static final String DEBUG_FORMAT = ANSI_CYAN + "%s" + ANSI_RESET + "\n";
    public static final String TRACE_FORMAT = ANSI_GREEN + "%s" + ANSI_RESET + "\n";

    private AnsiSysOutLogger() {
        if (INSTANCE != null) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void info(final String message) {
        System.out.printf(INFO_FORMAT, message);
    }

    @Override
    public void warning(final String message) {
        System.out.printf(WARNING_FORMAT, message);
    }

    @Override
    public void error(final String message) {
        System.out.printf(ERROR_FORMAT, message);
    }

    @Override
    public void debug(final String message) {
        System.out.printf(DEBUG_FORMAT, message);
    }

    @Override
    public void track(final String message) {
        System.out.printf(TRACE_FORMAT, message);
    }
}
