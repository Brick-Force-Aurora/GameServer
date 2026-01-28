package de.brickforceaurora.gameserver.legacy.util;

import java.util.ArrayList;
import java.util.List;

public final class Utils {

    private Utils() {
        throw new UnsupportedOperationException();
    }

    public static <T> List<List<T>> SplitList(final List<T> list, final int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("chunkSize must be greater than 0.");
        }

        final List<List<T>> result = new ArrayList<>();

        for (int index = 0; index < list.size(); index += chunkSize) {
            final int end = Math.min(index + chunkSize, list.size());
            result.add(new ArrayList<>(list.subList(index, end)));
        }

        return result;
    }
}
