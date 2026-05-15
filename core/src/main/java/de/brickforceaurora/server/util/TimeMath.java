package de.brickforceaurora.server.util;

public final class TimeMath {
    
    private TimeMath() {
        throw new UnsupportedOperationException();
    }
    
    public static long calculateDifference(long time, long otherTime) {
        if (otherTime < time) {
            return Math.abs(time - otherTime);
        }
        return (Long.MAX_VALUE - otherTime) + Math.abs(Long.MIN_VALUE - time);
    }

}
