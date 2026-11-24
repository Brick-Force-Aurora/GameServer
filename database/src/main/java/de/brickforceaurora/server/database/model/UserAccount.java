package de.brickforceaurora.server.database.model;

import java.util.Random;
import java.util.UUID;

public record UserAccount(int id, UUID uuid, String username, long createdAt, long lastLoginAt) {
    
    public UserAccount(int id, String username, long createdAt, long lastLoginAt) {
        Random random = new Random(id);
        long mostSigBits = (random.nextLong() & 0xFFFFFFFFFFFF0FFFL) | 0x0000000000004000L;
        long leastSigBits = (random.nextLong() & 0x3FFFFFFFFFFFFFFFL) | 0x8000000000000000L;
        this(id, new UUID(mostSigBits, leastSigBits), username, createdAt, lastLoginAt);
    }

}
