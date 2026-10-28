package de.brickforceaurora.server.database;

public final class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 6290178464651325917L;

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
