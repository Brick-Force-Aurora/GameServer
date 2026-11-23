package de.brickforceaurora.server.net.udp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// Remembers who waits for the ack of a relayed reliable message, not thread safe.
// The client sends one reliable message at a time and repeats it until it is acked,
// so per origin only the current meta is pending.
final class ReliableRoutes {

    record Resolution(int origin, boolean ambiguous) {}

    private static final class Pending {

        private int meta = -1;
        private long forwardedAt;
        private final Set<Integer> receivers = new HashSet<>();

    }

    private static final long MAX_AGE = TimeUnit.SECONDS.toNanos(5);

    private final Map<Integer, Pending> byOrigin = new HashMap<>();

    void forwarded(final int origin, final int receiver, final int meta, final long now) {
        final Pending pending = byOrigin.computeIfAbsent(origin, _ -> new Pending());
        if (pending.meta != meta) {
            // The origin moved on, so the older message was acked
            pending.receivers.clear();
            pending.meta = meta;
        }
        pending.forwardedAt = now;
        pending.receivers.add(receiver);
    }

    // TEMP: The ack only has the meta, not the origin. If two origins wait for the same meta of the
    // same receiver, the one forwarded last gets the ack and the other one repeats its message.
    // The clean fix is the client setting the slot of the sender as ack destination.
    Optional<Resolution> acknowledged(final int receiver, final int meta, final long now) {
        Integer origin = null;
        Pending found = null;
        boolean ambiguous = false;
        for (final Map.Entry<Integer, Pending> entry : byOrigin.entrySet()) {
            final Pending pending = entry.getValue();
            if (pending.meta != meta || now - pending.forwardedAt > MAX_AGE || !pending.receivers.contains(receiver)) {
                continue;
            }
            if (found != null) {
                ambiguous = true;
            }
            if (found == null || pending.forwardedAt > found.forwardedAt) {
                origin = entry.getKey();
                found = pending;
            }
        }
        if (found == null) {
            return Optional.empty();
        }
        found.receivers.remove(receiver);
        return Optional.of(new Resolution(origin, ambiguous));
    }

    // Removes old entries and entries of origins that are gone
    void purge(final long now, final Set<Integer> aliveOrigins) {
        byOrigin.entrySet().removeIf(entry -> !aliveOrigins.contains(entry.getKey()) || now - entry.getValue().forwardedAt > MAX_AGE);
    }

}
