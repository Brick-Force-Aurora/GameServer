package de.brickforceaurora.server.match.game.mode.teamdeathmatch;

import de.brickforceaurora.server.match.game.PlayerStats;

//TODO: Config EXP4J maybe LUA
public final class TDMScoring {

    public static final int HEAD_HITPART = 4;

    public static final int KILL_SCORE = 10;
    public static final int HEADSHOT_SCORE = 5;
    public static final int ASSIST_SCORE = 5;
    public static final int MIN_ASSIST_DAMAGE = 1;
    public static final int MAX_SERIAL_BONUS = 100;
    public static final int WANTED_STREAK = 5;
    public static final int WANTED_BONUS = 20;
    public static final int WIN_BONUS = 20;
    public static final int DRAW_BONUS = 10;

    public static final int POINTS_PER_PLAY_MINUTE = 1;

    private TDMScoring() {
        throw new UnsupportedOperationException();
    }

    public static int xp(final PlayerStats stats, final int result) {
        return stats.score() + bonus(result);
    }

    public static int points(final PlayerStats stats, final int result) {
        return stats.score() + bonus(result) + stats.playMinutes() * POINTS_PER_PLAY_MINUTE;
    }

    private static int bonus(final int result) {
        return result > 0 ? WIN_BONUS : result == 0 ? DRAW_BONUS : 0;
    }

}
