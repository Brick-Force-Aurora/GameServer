package de.brickforceaurora.server.util;

/**
 * Unity-style time utility.
 * deltaTime is updated once per server tick.
 */
public final class Time {

    /** Seconds since last tick */
    public static volatile float deltaTime = 0f;

    private Time() {
        // static utility
    }
}
