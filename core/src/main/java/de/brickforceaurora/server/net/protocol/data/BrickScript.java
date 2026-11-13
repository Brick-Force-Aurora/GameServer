package de.brickforceaurora.server.net.protocol.data;

public record BrickScript(String alias, boolean enableOnAwake, boolean visibleOnAwake, String commands) {
}
