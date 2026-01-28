package de.brickforceaurora.gameserver.legacy.data.flag;

public interface IFlag {

    int mask();

    @SuppressWarnings("unchecked")
    public static <F extends IFlag> int combine(F... flags) {
        int output = 0;
        for (F flag : flags) {
            output |= flag.mask();
        }
        return output;
    }

}
