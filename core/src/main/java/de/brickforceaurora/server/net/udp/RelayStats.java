package de.brickforceaurora.server.net.udp;

import java.util.StringJoiner;

import me.lauriichan.laylib.logger.ISimpleLogger;

// Counts what the relay drops, only used by the event loop of the rendezvous channel
final class RelayStats {

    enum Event {

        RATE_LIMITED("rate limited datagrams"),
        UNKNOWN_SENDER("datagrams from unknown senders"),
        REJECTED_PING("rejected pings"),
        NO_PEERS("datagrams from clients without peers"),
        NOT_RELAYED("broadcasts without a peer that wants them"),
        NO_DESTINATION("datagrams to an empty slot"),
        NO_ENDPOINT("forwards to peers without an endpoint"),
        UNMATCHED_ACK("acks without a pending message"),
        AMBIGUOUS_ACK("acks with more than one possible origin");

        private final String description;

        Event(final String description) {
            this.description = description;
        }

    }

    private final long[] counts = new long[Event.values().length];

    void count(final Event event) {
        counts[event.ordinal()]++;
    }

    // Logs the counts since the last report and resets them
    void report(final ISimpleLogger logger) {
        final StringJoiner joiner = new StringJoiner(", ");
        for (final Event event : Event.values()) {
            final long count = counts[event.ordinal()];
            if (count == 0) {
                continue;
            }
            counts[event.ordinal()] = 0;
            joiner.add(count + " " + event.description);
        }
        if (joiner.length() != 0) {
            logger.warning("Rendezvous relay: {0}", joiner.toString());
        }
    }

}
