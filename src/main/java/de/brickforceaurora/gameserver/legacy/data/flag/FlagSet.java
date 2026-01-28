package de.brickforceaurora.gameserver.legacy.data.flag;

public final class FlagSet<F extends IFlag> {

    private volatile int value;

    public FlagSet() {}

    public FlagSet(final int value) {
        this.value = value;
    }

    public FlagSet<F> set(final F flag, final boolean state) {
        if (state) {
            this.value |= flag.mask();
        } else {
            this.value &= ~flag.mask();
        }
        return this;
    }

    public boolean is(final F flag) {
        return (this.value & flag.mask()) == flag.mask();
    }

    public FlagSet<F> value(final int value) {
        this.value = value;
        return this;
    }

    public int value() {
        return value;
    }

}
