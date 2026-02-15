package de.brickforceaurora.server.util.flag;

public interface IFlag {

    int mask();

    @SuppressWarnings("unchecked")
    static <F extends IFlag> int combine(final F... flags) {
        int output = 0;
        for (final F flag : flags) {
            output |= flag.mask();
        }
        return output;
    }

}
