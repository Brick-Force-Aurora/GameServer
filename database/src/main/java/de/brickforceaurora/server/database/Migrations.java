package de.brickforceaurora.server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

final class Migrations {

    private Migrations() {
        throw new UnsupportedOperationException();
    }

    private static final String[][] MIGRATIONS = {
            {"""
            CREATE TABLE account (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE COLLATE NOCASE,
                password_hash TEXT,
                created_at INTEGER NOT NULL,
                last_login_at INTEGER
            )
            """, """
            CREATE TABLE profile (
                account_id INTEGER PRIMARY KEY REFERENCES account(id) ON DELETE CASCADE,
                nickname TEXT UNIQUE COLLATE NOCASE,
                xp INTEGER NOT NULL DEFAULT 0,
                rank INTEGER NOT NULL DEFAULT 0,
                tutorials INTEGER NOT NULL DEFAULT 0,
                country_filter INTEGER NOT NULL DEFAULT -1,
                tos_accepted INTEGER NOT NULL DEFAULT 0,
                extra_slots INTEGER NOT NULL DEFAULT 0,
                first_login_fp INTEGER NOT NULL DEFAULT 0,
                point INTEGER NOT NULL DEFAULT 0,
                brick INTEGER NOT NULL DEFAULT 0,
                cash INTEGER NOT NULL DEFAULT 0,
                free_coin INTEGER NOT NULL DEFAULT 0,
                star_dust INTEGER NOT NULL DEFAULT 0
            )
            """, """
            CREATE TABLE stats (
                account_id INTEGER PRIMARY KEY REFERENCES account(id) ON DELETE CASCADE,
                kill INTEGER NOT NULL DEFAULT 0,
                death INTEGER NOT NULL DEFAULT 0,
                assist INTEGER NOT NULL DEFAULT 0,
                mission INTEGER NOT NULL DEFAULT 0,
                score INTEGER NOT NULL DEFAULT 0,
                heavy INTEGER NOT NULL DEFAULT 0,
                assault INTEGER NOT NULL DEFAULT 0,
                sniper INTEGER NOT NULL DEFAULT 0,
                sub_machine INTEGER NOT NULL DEFAULT 0,
                hand_gun INTEGER NOT NULL DEFAULT 0,
                melee INTEGER NOT NULL DEFAULT 0,
                special INTEGER NOT NULL DEFAULT 0
            )
            """, """
            CREATE TABLE inventory_item (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                account_id INTEGER NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                code TEXT NOT NULL,
                usage INTEGER NOT NULL DEFAULT 0,
                remain INTEGER NOT NULL DEFAULT -1,
                amount INTEGER NOT NULL DEFAULT 1,
                premium INTEGER NOT NULL DEFAULT 0,
                durability INTEGER NOT NULL DEFAULT 0,
                tool_slot INTEGER NOT NULL DEFAULT -1
            )
            """, "CREATE INDEX idx_inventory_item_account ON inventory_item(account_id)"
            },
            {"""
            CREATE TABLE transfer_token (
                token_hash TEXT PRIMARY KEY,
                account_id INTEGER NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                origin_channel INTEGER NOT NULL,
                expires_at INTEGER NOT NULL
            )
            """, "CREATE INDEX idx_transfer_token_expires ON transfer_token(expires_at)", """
            CREATE TABLE oauth_identity (
                provider TEXT NOT NULL,
                subject TEXT NOT NULL,
                account_id INTEGER NOT NULL REFERENCES account(id) ON DELETE CASCADE,
                linked_at INTEGER NOT NULL,
                PRIMARY KEY (provider, subject)
            )
            """, "CREATE INDEX idx_oauth_identity_account ON oauth_identity(account_id)"
            }
    };

    static void apply(Connection connection) throws SQLException {
        int current = currentVersion(connection);
        if (current > MIGRATIONS.length) {
            throw new IllegalStateException("Database schema version " + current + " is newer than supported version " + MIGRATIONS.length);
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (Statement statement = connection.createStatement()) {
            for (int version = current; version < MIGRATIONS.length; version++) {
                for (String sql : MIGRATIONS[version]) {
                    statement.execute(sql);
                }
                statement.execute("PRAGMA user_version = " + (version + 1));
            }
            connection.commit();
        } catch (SQLException | RuntimeException exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private static int currentVersion(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery("PRAGMA user_version")) {
            return result.next() ? result.getInt(1) : 0;
        }
    }

}
