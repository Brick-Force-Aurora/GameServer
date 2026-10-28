package de.brickforceaurora.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Optional;

import de.brickforceaurora.server.database.model.UserAccount;


public final class LoginRepository {

    public static final Duration DEFAULT_TRANSFER_TTL = Duration.ofSeconds(30);

    private static final class DummyHash {
        static final String VALUE = PasswordHasher.hash("dummy");
    }

    private final Database database;

    public LoginRepository(Database database) {
        this.database = database;
    }

    public Optional<UserAccount> loginDebug(String username) {
        return database.write(connection -> {
            Optional<UserAccount> existing = UserRepository.queryAccount(connection, "username = ?", username);
            UserAccount account;
            if (existing.isPresent()) {
                account = existing.get();
                if (hasCredentials(connection, account.id())) {
                    return Optional.<UserAccount>empty();
                }
            } else {
                account = UserRepository.insertAccount(connection, username, null, System.currentTimeMillis());
            }
            return Optional.of(touch(connection, account));
        });
    }

    public Optional<UserAccount> loginPassword(String username, String password) {
        return database.write(connection -> {
            Optional<UserAccount> account = UserRepository.queryAccount(connection, "username = ?", username);
            String stored = account.isPresent() ? passwordHash(connection, account.get().id()) : null;
            boolean valid = PasswordHasher.verify(password, stored == null ? DummyHash.VALUE : stored);
            if (!valid || stored == null || account.isEmpty()) {
                return Optional.<UserAccount>empty();
            }
            return Optional.of(touch(connection, account.get()));
        });
    }

    //TODO: Transfer Token
    //TODO: OAuth

    private static String passwordHash(Connection connection, int accountId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM account WHERE id = ?")) {
            statement.setInt(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? result.getString(1) : null;
            }
        }
    }

    private static boolean hasCredentials(Connection connection, int accountId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT (password_hash IS NOT NULL) OR EXISTS (SELECT 1 FROM oauth_identity WHERE account_id = account.id) FROM account WHERE id = ?")) {
            statement.setInt(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() && result.getInt(1) != 0;
            }
        }
    }

    private static UserAccount touch(Connection connection, UserAccount account) throws SQLException {
        long now = System.currentTimeMillis();
        UserRepository.execute(connection, "UPDATE account SET last_login_at = ? WHERE id = ?", now, account.id());
        return new UserAccount(account.id(), account.username(), account.createdAt(), now);
    }

}
