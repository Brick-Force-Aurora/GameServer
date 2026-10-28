package de.brickforceaurora.server.database.model;

public record UserAccount(int id, String username, long createdAt, long lastLoginAt) {

}
