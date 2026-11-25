package de.brickforceaurora.server.match.game;

public abstract class TeamGameData extends MatchGameData {

    public static final int FIRST_BLUE_SLOT = 8;

    public static boolean isRed(final int slot) {
        return slot < FIRST_BLUE_SLOT;
    }

    private int redTickets;
    private int blueTickets;

    private int redScore;
    private int blueScore;

    @Override
    public abstract boolean autoBalance();

    @Override
    public synchronized void resetMatch() {
        super.resetMatch();
        resetTickets();
        redScore = 0;
        blueScore = 0;
    }

    public synchronized void resetTickets() {
        redTickets = 0;
        blueTickets = 0;
    }

    public synchronized int nextTicket(final int slot) {
        return isRed(slot) ? redTickets++ : blueTickets++;
    }

    @Override
    public synchronized int redScore() {
        return redScore;
    }

    @Override
    public synchronized int blueScore() {
        return blueScore;
    }

    public synchronized void addScore(final boolean red) {
        if (red) {
            redScore++;
        } else {
            blueScore++;
        }
    }

}
