package de.brickforceaurora.server.template;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class Templates {

    private Templates() {
        throw new UnsupportedOperationException();
    }

    public static List<String[]> load(final String name) {
        try (InputStream stream = Templates.class.getResourceAsStream("/templates/" + name + ".csv")) {
            if (stream == null) {
                throw new IllegalArgumentException("Unknown template '" + name + "'");
            }
            return parse(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException exception) {
            throw new IllegalStateException("Couldn't read template '" + name + "'", exception);
        }
    }

    static List<String[]> parse(final String text) {
        List<String[]> rows = new ArrayList<>();
        List<String> cells = new ArrayList<>();
        StringBuilder cell = new StringBuilder();
        boolean quoted = false;
        int length = text.length();
        for (int index = 0; index < length; index++) {
            char character = text.charAt(index);
            if (quoted) {
                if (character != '"') {
                    cell.append(character);
                } else if (index + 1 < length && text.charAt(index + 1) == '"') {
                    cell.append('"');
                    index++;
                } else {
                    quoted = false;
                }
                continue;
            }
            switch (character) {
            case '"' -> quoted = true;
            case ',' -> {
                cells.add(cell.toString());
                cell.setLength(0);
            }
            case '\r' -> {
                // The line break is handled by the following \n
            }
            case '\n' -> {
                cells.add(cell.toString());
                cell.setLength(0);
                rows.add(cells.toArray(String[]::new));
                cells.clear();
            }
            default -> cell.append(character);
            }
        }
        if (!cells.isEmpty() || cell.length() != 0) {
            cells.add(cell.toString());
            rows.add(cells.toArray(String[]::new));
        }
        return rows;
    }

}
