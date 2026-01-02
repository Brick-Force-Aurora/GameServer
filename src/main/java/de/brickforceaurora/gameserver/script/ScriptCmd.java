package de.brickforceaurora.gameserver.script;

public abstract class ScriptCmd {

    public static final String[] CMD_DELIMITERS = {
            ")(*&",
            "\0"
    };

    public static final String[] ARG_DELIMITERS = {
            "!@#$",
            "\0"
    };

    public abstract String getDescription();

    public abstract int getIconIndex();

    public abstract String getName();
}