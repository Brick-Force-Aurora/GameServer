package de.brickforceaurora.server.match.command.argument;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import de.brickforceaurora.server.command.IArgumentExtension;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.command.IArgumentMap;
import me.lauriichan.laylib.command.Suggestions;
import me.lauriichan.laylib.command.util.LevenshteinDistance;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class UUIDArgument implements IArgumentExtension<UUID> {

    private final boolean collection;
    private final List<UUID> selection;

    public UUIDArgument(IArgumentMap map) {
        UUID[] uuids = map.get("collection", UUID[].class).get();
        if (uuids == null || uuids.length == 0) {
            this.selection = Collections.emptyList();
            this.collection = false;
            return;
        }
        ObjectArrayList<UUID> list = new ObjectArrayList<>();
        Collections.addAll(list, uuids);
        this.selection = Collections.unmodifiableList(list);
        this.collection = true;
    }

    @Override
    public UUID parse(Actor<?> actor, String input, IArgumentMap map) throws IllegalArgumentException {
        UUID uuid = uuidFromString(input);
        if (!collection) {
            return uuid;
        }
        if (!selection.contains(uuid)) {
            throw new IllegalArgumentException("Invalid input");
        }
        return uuid;
    }

    @Override
    public void suggest(Actor<?> actor, String input, Suggestions suggestions, IArgumentMap map) {
        if (!collection) {
            return;
        }
        List<Map.Entry<String, Integer>> list = LevenshteinDistance.rankByDistance(input, selection.stream().map(UUID::toString).toList());
        double max = list.stream().map(Map.Entry::getValue).collect(Collectors.summingInt(Integer::intValue));
        for (int index = 0; index < list.size(); index++) {
            Map.Entry<String, Integer> entry = list.get(index);
            suggestions.suggest(1 - (entry.getValue().doubleValue() / max), entry.getKey());
        }
    }

    public static UUID uuidFromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("null");
        }
        if (value.contains("-")) {
            if (value.length() != 36) {
                throw new IllegalArgumentException("UUIDs that have the '-' seperator have to be 36 characters long.");
            }
            return UUID.fromString(value);
        }
        if (value.length() != 32) {
            throw new IllegalArgumentException("UUIDs that don't have the '-' seperator have to be 32 characters long.");
        }
        return UUID.fromString(value.substring(0, 8) + "-" + value.substring(8, 12) + "-" + value.substring(12, 16) + "-"
            + value.substring(16, 20) + "-" + value.substring(20, 32));
    }

}
