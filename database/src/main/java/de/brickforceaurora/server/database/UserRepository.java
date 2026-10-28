package de.brickforceaurora.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.database.model.UserStats;

public final class UserRepository {

    private static final String ACCOUNT_COLUMNS = "id, username, created_at, last_login_at";

    private final Database database;

    public UserRepository(Database database) {
        this.database = database;
    }

    public Database database() {
        return database;
    }

    public Optional<UserAccount> findAccount(int id) {
        return database.read(connection -> queryAccount(connection, "id = ?", id));
    }

    public Optional<UserAccount> findAccount(String username) {
        return database.read(connection -> queryAccount(connection, "username = ?", username));
    }

    public Optional<UserAccount> createAccount(String username, String password) {
        String hash = password == null ? null : PasswordHasher.hash(password);
        long now = System.currentTimeMillis();
        return database.write(connection -> {
            if (queryAccount(connection, "username = ?", username).isPresent()) {
                return Optional.<UserAccount>empty();
            }
            return Optional.of(insertAccount(connection, username, hash, now));
        });
    }

    static UserAccount insertAccount(Connection connection, String username, String passwordHash, long now) throws SQLException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO account (username, password_hash, created_at) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setLong(3, now);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                id = keys.getInt(1);
            }
        }
        UserProfile profile = UserProfile.defaults(id);
        execute(connection,
                "INSERT INTO profile (account_id, nickname, xp, rank, tutorials, country_filter, tos_accepted, extra_slots, first_login_fp, point, brick, cash, free_coin, star_dust) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                id, profile.nickname(), profile.xp(), profile.rank(), profile.tutorials(), profile.countryFilter(),
                profile.tosAccepted() ? 1 : 0, profile.extraSlots(), profile.firstLoginFp(), profile.point(), profile.brick(), profile.cash(),
                profile.freeCoin(), profile.starDust());
        execute(connection, "INSERT INTO stats (account_id) VALUES (?)", id);
        for (InventoryItem item : InventoryItem.starterItems(id)) {
            insertItem(connection, item);
        }
        return new UserAccount(id, username, now, 0L);
    }

    public boolean verifyPassword(int accountId, String password) {
        String stored = database.read(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM account WHERE id = ?")) {
                statement.setInt(1, accountId);
                try (ResultSet result = statement.executeQuery()) {
                    return result.next() ? result.getString(1) : null;
                }
            }
        });
        return PasswordHasher.verify(password, stored);
    }

    public void setPassword(int accountId, String password) {
        String hash = PasswordHasher.hash(password);
        database.run(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE account SET password_hash = ? WHERE id = ?")) {
                statement.setString(1, hash);
                statement.setInt(2, accountId);
                statement.executeUpdate();
            }
        });
    }

    public void touchLogin(int accountId) {
        long now = System.currentTimeMillis();
        database.run(connection -> execute(connection, "UPDATE account SET last_login_at = ? WHERE id = ?", now, accountId));
    }

    public boolean deleteAccount(int accountId) {
        return database.write(connection -> execute(connection, "DELETE FROM account WHERE id = ?", accountId)) > 0;
    }

    public Optional<UserProfile> loadProfile(int accountId) {
        return database.read(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT account_id, nickname, xp, rank, tutorials, country_filter, tos_accepted, extra_slots, first_login_fp, point, brick, cash, free_coin, star_dust FROM profile WHERE account_id = ?")) {
                statement.setInt(1, accountId);
                try (ResultSet r = statement.executeQuery()) {
                    if (!r.next()) {
                        return Optional.<UserProfile>empty();
                    }
                    return Optional.of(new UserProfile(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6),
                            r.getInt(7) != 0, r.getInt(8), r.getInt(9), r.getInt(10), r.getInt(11), r.getInt(12), r.getInt(13), r.getInt(14)));
                }
            }
        });
    }

    public boolean saveProfile(UserProfile profile) {
        return database.write(connection -> {
            if (profile.nickname() != null && isNicknameUsed(connection, profile.nickname(), profile.accountId())) {
                return false;
            }
            execute(connection,
                    "UPDATE profile SET nickname = ?, xp = ?, rank = ?, tutorials = ?, country_filter = ?, tos_accepted = ?, extra_slots = ?, first_login_fp = ?, point = ?, brick = ?, cash = ?, free_coin = ?, star_dust = ? WHERE account_id = ?",
                    profile.nickname(), profile.xp(), profile.rank(), profile.tutorials(), profile.countryFilter(), profile.tosAccepted() ? 1 : 0,
                    profile.extraSlots(), profile.firstLoginFp(), profile.point(), profile.brick(), profile.cash(), profile.freeCoin(),
                    profile.starDust(), profile.accountId());
            return true;
        });
    }

    public boolean acceptTos(int accountId) {
        return database.write(connection -> execute(connection, "UPDATE profile SET tos_accepted = 1 WHERE account_id = ?", accountId)) > 0;
    }

    public boolean isNicknameTaken(String nickname) {
        return database.read(connection -> isNicknameUsed(connection, nickname, -1));
    }

    private static boolean isNicknameUsed(Connection connection, String nickname, int exceptAccountId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM profile WHERE nickname = ? AND account_id <> ?")) {
            statement.setString(1, nickname);
            statement.setInt(2, exceptAccountId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public Optional<UserStats> loadStats(int accountId) {
        return database.read(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT account_id, kill, death, assist, mission, score, heavy, assault, sniper, sub_machine, hand_gun, melee, special FROM stats WHERE account_id = ?")) {
                statement.setInt(1, accountId);
                try (ResultSet r = statement.executeQuery()) {
                    if (!r.next()) {
                        return Optional.<UserStats>empty();
                    }
                    return Optional.of(new UserStats(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6),
                            r.getInt(7), r.getInt(8), r.getInt(9), r.getInt(10), r.getInt(11), r.getInt(12), r.getInt(13)));
                }
            }
        });
    }

    public void saveStats(UserStats stats) {
        database.run(connection -> execute(connection,
                "UPDATE stats SET kill = ?, death = ?, assist = ?, mission = ?, score = ?, heavy = ?, assault = ?, sniper = ?, sub_machine = ?, hand_gun = ?, melee = ?, special = ? WHERE account_id = ?",
                stats.kill(), stats.death(), stats.assist(), stats.mission(), stats.score(), stats.heavy(), stats.assault(), stats.sniper(),
                stats.subMachine(), stats.handGun(), stats.melee(), stats.special(), stats.accountId()));
    }

    public List<InventoryItem> loadInventory(int accountId) {
        return database.read(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, account_id, code, usage, remain, amount, premium, durability, tool_slot FROM inventory_item WHERE account_id = ? ORDER BY id")) {
                statement.setInt(1, accountId);
                try (ResultSet r = statement.executeQuery()) {
                    List<InventoryItem> items = new ArrayList<>();
                    while (r.next()) {
                        items.add(new InventoryItem(r.getLong(1), r.getInt(2), r.getString(3), r.getInt(4), r.getInt(5), r.getInt(6),
                                r.getInt(7), r.getInt(8), r.getInt(9)));
                    }
                    return items;
                }
            }
        });
    }

    public InventoryItem addItem(InventoryItem item) {
        return database.write(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO inventory_item (account_id, code, usage, remain, amount, premium, durability, tool_slot) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, item.accountId());
                statement.setString(2, item.code());
                statement.setInt(3, item.usage());
                statement.setInt(4, item.remain());
                statement.setInt(5, item.amount());
                statement.setInt(6, item.premium());
                statement.setInt(7, item.durability());
                statement.setInt(8, item.toolSlot());
                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    keys.next();
                    return new InventoryItem(keys.getLong(1), item.accountId(), item.code(), item.usage(), item.remain(), item.amount(),
                            item.premium(), item.durability(), item.toolSlot());
                }
            }
        });
    }

    static InventoryItem insertItem(Connection connection, InventoryItem item) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO inventory_item (account_id, code, usage, remain, amount, premium, durability, tool_slot) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, item.accountId());
            statement.setString(2, item.code());
            statement.setInt(3, item.usage());
            statement.setInt(4, item.remain());
            statement.setInt(5, item.amount());
            statement.setInt(6, item.premium());
            statement.setInt(7, item.durability());
            statement.setInt(8, item.toolSlot());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                return new InventoryItem(keys.getLong(1), item.accountId(), item.code(), item.usage(), item.remain(), item.amount(),
                        item.premium(), item.durability(), item.toolSlot());
            }
        }
    }

    public void updateItem(InventoryItem item) {
        database.run(connection -> execute(connection,
                "UPDATE inventory_item SET code = ?, usage = ?, remain = ?, amount = ?, premium = ?, durability = ?, tool_slot = ? WHERE id = ? AND account_id = ?",
                item.code(), item.usage(), item.remain(), item.amount(), item.premium(), item.durability(), item.toolSlot(), item.id(),
                item.accountId()));
    }

    public boolean removeItem(int accountId, long itemId) {
        return database.write(connection -> execute(connection, "DELETE FROM inventory_item WHERE id = ? AND account_id = ?", itemId,
                accountId)) > 0;
    }

    static Optional<UserAccount> queryAccount(Connection connection, String where, Object value) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT " + ACCOUNT_COLUMNS + " FROM account WHERE " + where)) {
            statement.setObject(1, value);
            try (ResultSet r = statement.executeQuery()) {
                if (!r.next()) {
                    return Optional.empty();
                }
                return Optional.of(new UserAccount(r.getInt(1), r.getString(2), r.getLong(3), r.getLong(4)));
            }
        }
    }

    static int execute(Connection connection, String sql, Object... parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int index = 0; index < parameters.length; index++) {
                statement.setObject(index + 1, parameters[index]);
            }
            return statement.executeUpdate();
        }
    }

}
