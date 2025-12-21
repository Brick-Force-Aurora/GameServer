package de.brickforceaurora.server;

import de.brickforceaurora.server.core.GameServer;
import de.brickforceaurora.server.util.Time;

public final class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Brick-Force Aurora Dedicated Server...");

        GameServer server = GameServer.getInstance();
        server.start();

        System.out.println("Server ready.");
        System.out.println("Press Ctrl+C to exit.");

        final int tickRate = 60;
        final long tickMs = 1000 / tickRate;

        long lastMs = System.currentTimeMillis();

        while (true) {
            long now = System.currentTimeMillis();
            long elapsed = now - lastMs;

            if (elapsed >= tickMs) {
                Time.deltaTime = elapsed / 1000f;
                lastMs = now;

                server.tick();
            }

            Thread.sleep(1);
        }
    }
}
