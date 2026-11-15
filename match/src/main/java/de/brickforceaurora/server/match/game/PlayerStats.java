package de.brickforceaurora.server.match.game;

public final class PlayerStats {

    private final int clientId;
    private final boolean red;
    private final String nickname;

    private int kill;
    private int death;
    private int assist;
    private int score;
    private int damage;
    private int playMinutes;
    private int streak;
    private boolean alive = true;
    private boolean wanted;

    PlayerStats(final int clientId, final boolean red, final String nickname) {
        this.clientId = clientId;
        this.red = red;
        this.nickname = nickname;
    }

    public int clientId() {
        return clientId;
    }

    public boolean red() {
        return red;
    }

    public String nickname() {
        return nickname;
    }

    public int kill() {
        return kill;
    }

    public int death() {
        return death;
    }

    public int assist() {
        return assist;
    }

    public int score() {
        return score;
    }

    public int damage() {
        return damage;
    }

    public int playMinutes() {
        return playMinutes;
    }

    public boolean alive() {
        return alive;
    }

    public PlayerStats alive(final boolean alive) {
        this.alive = alive;
        return this;
    }

    public int addKill() {
        return ++kill;
    }

    public int addDeath() {
        return ++death;
    }

    public int addAssist() {
        return ++assist;
    }

    public int addScore(final int amount) {
        return score += amount;
    }

    public PlayerStats addDamage(final int amount) {
        damage += amount;
        return this;
    }

    public PlayerStats addPlayMinute() {
        playMinutes++;
        return this;
    }

    public int streak() {
        return streak;
    }

    public int addStreak() {
        return ++streak;
    }

    public PlayerStats resetStreak() {
        streak = 0;
        return this;
    }

    public boolean wanted() {
        return wanted;
    }

    public PlayerStats wanted(final boolean wanted) {
        this.wanted = wanted;
        return this;
    }

}
