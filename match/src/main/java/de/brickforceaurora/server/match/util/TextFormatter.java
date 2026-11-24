package de.brickforceaurora.server.match.util;

import de.brickforceaurora.server.command.util.StringReader;

public final class TextFormatter {

    private TextFormatter() {
        throw new UnsupportedOperationException();
    }

    public static String clean(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        text = text.replaceAll("\\p{Cntrl}", "").replaceAll(" {2,}", " ").trim();
        if (text.isEmpty()) {
            return null;
        }
        StringReader reader = new StringReader(text);
        StringBuilder builder = new StringBuilder();
        while (reader.hasNext()) {
            builder.append(reader.readUntil('<'));
            if (reader.skipUntilIfFound('>')) {
                reader.skip();
                continue;
            }
        }
        return builder.toString();
    }

}
