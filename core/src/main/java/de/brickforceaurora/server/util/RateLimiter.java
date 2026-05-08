package de.brickforceaurora.server.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;

public final class RateLimiter<K> {

    private static record Limiter(AtomicLong cooldown, ObjectList<AtomicLong> tickets) {}

    private final Object2ObjectMap<K, Limiter> limits = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
    private final AtomicLong windowLength = new AtomicLong(TimeUnit.MINUTES.toNanos(10));
    private final AtomicInteger rateLimit = new AtomicInteger(7);

    public long windowLength() {
        return windowLength.get();
    }

    public RateLimiter<K> windowLength(long length, TimeUnit unit) {
        long nanoLength = unit.toNanos(length);
        if (nanoLength <= 0) {
            throw new IllegalArgumentException("Non-positive nano second value");
        }
        windowLength.set(nanoLength);
        return this;
    }

    public int rateLimit() {
        return rateLimit.get();
    }

    public RateLimiter<K> rateLimit(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Non-positive rate limit value");
        }
        rateLimit.set(limit);
        return this;
    }

    public void tick(long delta) {
        long negativeDelta = -delta;
        ObjectIterator<Limiter> limiters = limits.values().iterator();
        while (limiters.hasNext()) {
            Limiter limiter = limiters.next();
            if (limiter.tickets().isEmpty()) {
                if (limiter.cooldown().addAndGet(negativeDelta) <= 0) {
                    limiters.remove();
                }
                continue;
            }
            ObjectIterator<AtomicLong> tickets = limiter.tickets().iterator();
            while (tickets.hasNext()) {
                if (tickets.next().addAndGet(negativeDelta) <= 0) {
                    tickets.remove();
                }
            }
        }
    }

    private Limiter createLimiter(K _key) {
        return new Limiter(new AtomicLong(windowLength.get()), ObjectLists.synchronize(new ObjectArrayList<>()));
    }

    public boolean acquire(K key) {
        Limiter limiter = limits.computeIfAbsent(key, this::createLimiter);
        if (limiter.tickets.size() >= rateLimit.get()) {
            return false;
        }
        long length = windowLength.get();
        limiter.tickets.add(new AtomicLong(length));
        limiter.cooldown().set(length);
        return true;
    }

    public int tickets(K key) {
        Limiter limiter = limits.get(key);
        if (limiter == null) {
            return 0;
        }
        return limiter.tickets().size();
    }

    public boolean canAcquire(K key) {
        return tickets(key) < rateLimit.get();
    }

}
