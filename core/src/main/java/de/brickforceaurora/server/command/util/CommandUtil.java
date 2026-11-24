package de.brickforceaurora.server.command.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public final class CommandUtil {

    private CommandUtil() {
        throw new UnsupportedOperationException();
    }

    private static final String[] EMPTY = new String[0];

    public static record CommandArguments(String commandName, String[] arguments) {}

    public static CommandArguments toArguments(String line) {
        ObjectArrayList<String> arguments = new ObjectArrayList<>();
        StringReader reader = new StringReader(line).skipWhitespace();
        if (reader.hasNext() && reader.peek() == '/') {
            reader.skip();
        }
        int prevIdx = 0, idx = 0;
        StringBuilder buffer = new StringBuilder();
        while (reader.hasNext()) {
            prevIdx = idx;
            if (Character.isWhitespace(reader.peek())) {
                reader.skipWhitespace();
                if (!buffer.isEmpty()) {
                    arguments.add(buffer.toString());
                    buffer = new StringBuilder();
                } else {
                    arguments.add("");
                }
                if (!reader.hasNext()) {
                    break;
                }
            }
            String str = reader.read();
            idx = reader.getCursor();
            if (prevIdx == idx) {
                char ch = reader.peek();
                reader.skip();
                idx++;
                if (!Character.isWhitespace(ch)) {
                    buffer.append(ch);
                }
            }
            if (str.isBlank()) {
                continue;
            }
            buffer.append(str);
        }
        if (!buffer.isEmpty()) {
            arguments.add(buffer.toString());
        }
        if (arguments.isEmpty()) {
            return new CommandArguments("", EMPTY);
        }
        String commandName = arguments.remove(0);
        return new CommandArguments(commandName, arguments.isEmpty() ? EMPTY : arguments.toArray(String[]::new));
    }

    public static int countSpace(final String path) {
        int count = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    public static String recombine(String label, String[] args) {
        final int space = countSpace(label);
        final StringBuilder string = new StringBuilder();
        for (int index = space; index < args.length; index++) {
            appendSafe(string, args[index]);
            if (index + 1 != args.length) {
                string.append(' ');
            }
        }
        return string.toString();
    }

    public static void appendSafe(StringBuilder builder, String str) {
        if (!str.isBlank() && str.chars().allMatch(ch -> StringReader.isUnquotedCharacter((char) ch))) {
            builder.append(str);
        } else {
            builder.append('"');
            builder.append(str.replace("\"", "\\\""));
            builder.append('"');
        }
    }

}
