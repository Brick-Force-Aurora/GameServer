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
        StringBuilder builder = new StringBuilder();
        appendClean0(builder, text);
        return builder.toString();
    }

    public static void appendClean(StringBuilder builder, String text) {
        if (text == null || text.isBlank()) {
            return;
        }
        appendClean0(builder, text);
    }

    private static void appendClean0(StringBuilder builder, String text) {
        StringReader reader = new StringReader(text.replaceAll("\\p{Cntrl}", " ").replaceAll(" {2,}", " ").trim());
        while (reader.hasNext()) {
            builder.append(reader.readUntil('<'));
            if (reader.skipUntilIfFound('>')) {
                reader.skip();
                continue;
            }
        }
    }

}
