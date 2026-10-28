package de.brickforceaurora.server.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

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

    private final String url;

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

    public <T> T read(SqlFunction<T> function) {
        try (Connection connection = connect()) {
            return function.apply(connection);
        } catch (SQLException exception) {
            throw new DatabaseException("Database query failed", exception);
        }
    }

    public <T> T write(SqlFunction<T> function) {
        try (Connection connection = connect()) {
            connection.setAutoCommit(false);
            try {
                T result = function.apply(connection);
                connection.commit();
                return result;
            } catch (SQLException | RuntimeException exception) {
                connection.rollback();
                throw exception;
            }
        } catch (SQLException exception) {
            throw new DatabaseException("Database transaction failed", exception);
        }
    }

    public void run(SqlAction action) {
        write(connection -> {
            action.run(connection);
            return null;
        });
    }

}
