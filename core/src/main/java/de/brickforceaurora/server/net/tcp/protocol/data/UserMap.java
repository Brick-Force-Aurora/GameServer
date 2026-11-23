package de.brickforceaurora.server.net.tcp.protocol.data;

import java.time.LocalDateTime;

public record UserMap(int slot, String name, int brickCount, LocalDateTime lastModified, byte premium) {}
