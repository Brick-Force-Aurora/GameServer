package de.brickforceaurora.server.match.gamemode;

public abstract class TeamGameData extends MatchGameData {

    public static final int FIRST_BLUE_SLOT = 8;

    private int redTickets;
    private int blueTickets;

    @Override
    public abstract boolean autoBalance();

    public synchronized void resetTickets() {
        redTickets = 0;
        blueTickets = 0;
    }

    public synchronized int nextTicket(int slot) {
        return slot < FIRST_BLUE_SLOT ? redTickets++ : blueTickets++;
    }
}
