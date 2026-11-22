package de.brickforceaurora.server.net.rendezvous;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// Token bucket per key with a limit on the number of keys, not thread safe
final class RateLimiter<K> {

    private static final class Bucket {

        private double tokens;
        private long updated;

        private Bucket(final double tokens, final long updated) {
            this.tokens = tokens;
            this.updated = updated;
        }

    }

    private final double tokensPerNano;
    private final double burst;
    private final int maxKeys;

    private final Map<K, Bucket> buckets = new HashMap<>();

    RateLimiter(final int perSecond, final int burst, final int maxKeys) {
        this.tokensPerNano = perSecond / (double) TimeUnit.SECONDS.toNanos(1);
        this.burst = burst;
        this.maxKeys = maxKeys;
    }

    boolean allow(final K key, final long now) {
        Bucket bucket = buckets.get(key);
        if (bucket == null) {
            if (buckets.size() >= maxKeys) {
                purge(now);
                if (buckets.size() >= maxKeys) {
                    // Better to refuse than to let the table grow, sources can be spoofed
                    return false;
                }
            }
            bucket = new Bucket(burst, now);
            buckets.put(key, bucket);
        } else {
            bucket.tokens = refilled(bucket, now);
            bucket.updated = now;
        }
        if (bucket.tokens < 1) {
            return false;
        }
        bucket.tokens--;
        return true;
    }

    // Buckets that are full again behave like new ones
    void purge(final long now) {
        buckets.values().removeIf(bucket -> refilled(bucket, now) >= burst);
    }

    private double refilled(final Bucket bucket, final long now) {
        return Math.min(burst, bucket.tokens + (now - bucket.updated) * tokensPerNano);
    }

}
