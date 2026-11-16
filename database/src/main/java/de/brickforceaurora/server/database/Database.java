package de.brickforceaurora.server.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class Database implements AutoCloseable {

    @FunctionalInterface
    public interface SqlFunction<T> {

        T apply(Connection connection) throws SQLException;

    }

    @FunctionalInterface
    public interface SqlAction {

        void run(Connection connection) throws SQLException;

    }

    public static final String DEFAULT_FILE = "data/users.db";

    public static Path defaultFile() {
        return Path.of(DEFAULT_FILE);
    }

    // unused connections are kept for the next access
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private final String url;
    private final BlockingQueue<Connection> idle = new ArrayBlockingQueue<>(MAX_IDLE_CONNECTIONS);
    private volatile boolean closed;

    public Database(Path file) {
        Path absolute = file.toAbsolutePath();
        try {
            Class.forName("org.sqlite.JDBC");
            if (absolute.getParent() != null) {
                Files.createDirectories(absolute.getParent());
            }
        } catch (ClassNotFoundException | IOException exception) {
            throw new DatabaseException("Failed to prepare database at " + absolute, exception);
        }
        // Write lock
        this.url = "jdbc:sqlite:" + absolute + "?transaction_mode=IMMEDIATE";
        try (Connection connection = connect()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA journal_mode = WAL");
            }
            Migrations.apply(connection);
        } catch (SQLException exception) {
            throw new DatabaseException("Failed to initialize database at " + absolute, exception);
        }
    }

    public Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
            statement.execute("PRAGMA busy_timeout = 5000");
        } catch (SQLException exception) {
            connection.close();
            throw exception;
        }
        return connection;
    }

    // If no connection is free a new one is opened
    private Connection acquire() throws SQLException {
        Connection connection;
        while ((connection = idle.poll()) != null) {
            if (!connection.isClosed()) {
                return connection;
            }
        }
        return connect();
    }

    // Connections of failed accesses are closed
    private void release(Connection connection, boolean healthy) {
        try {
            if (healthy && !closed && idle.offer(connection)) {
                if (closed) {
                    closeIdle();
                }
                return;
            }
            connection.close();
        } catch (SQLException _) {
            // RIP
        }
    }

    private void closeIdle() {
        Connection connection;
        while ((connection = idle.poll()) != null) {
            try {
                connection.close();
            } catch (SQLException _) {
                // RIP
            }
        }
    }

    @Override
    public void close() {
        closed = true;
        closeIdle();
    }

    public <T> T read(SqlFunction<T> function) {
        Connection connection = null;
        boolean healthy = false;
        try {
            connection = acquire();
            T result = function.apply(connection);
            healthy = true;
            return result;
        } catch (SQLException exception) {
            throw new DatabaseException("Database query failed", exception);
        } finally {
            if (connection != null) {
                release(connection, healthy);
            }
        }
    }

    public <T> T write(SqlFunction<T> function) {
        Connection connection = null;
        boolean healthy = false;
        try {
            connection = acquire();
            connection.setAutoCommit(false);
            try {
                T result = function.apply(connection);
                connection.commit();
                // Back to auto commit so that no transaction stays open while idle
                connection.setAutoCommit(true);
                healthy = true;
                return result;
            } catch (SQLException | RuntimeException exception) {
                connection.rollback();
                throw exception;
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Database transaction failed", exception);
        } finally {
            if (connection != null) {
                release(connection, healthy);
            }
        }
    }

    public void run(SqlAction action) {
        write(connection -> {
            action.run(connection);
            return null;
        });
    }

}
