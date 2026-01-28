package de.brickforceaurora.gameserver.legacy.data.flag;

public final class FlagSet<F extends IFlag> {

    private volatile int value;

    public FlagSet() {}

    public FlagSet(int value) {
        this.value = value;
    }

    public FlagSet<F> set(F flag, boolean state) {
        if (state) {
            this.value |= flag.mask();
        } else {
            this.value &= ~flag.mask();
        }
        return this;
    }

    public boolean is(F flag) {
        return (this.value & flag.mask()) == flag.mask();
    }
    
    public FlagSet<F> value(int value) {
        this.value = value;
        return this;
    }

    public int value() {
        return value;
    }

}
