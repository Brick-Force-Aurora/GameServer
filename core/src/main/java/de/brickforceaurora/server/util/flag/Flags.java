package de.brickforceaurora.server.util.flag;

public final class Flags<F> implements IFlags<F> {

    private final FlagManager<F> manager;
    private volatile int value;

    Flags(FlagManager<F> manager) {
        this.manager = manager;
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public Flags<F> value(final int value) {
        this.value = (value & manager.maxMask());
        return this;
    }

    @Override
    public boolean is(final F flag) {
        int mask = manager.mask(flag);
        return (this.value & mask) == mask;
    }

    @Override
    public Flags<F> set(final F flag, final boolean state) {
        if (state) {
            this.value |= manager.mask(flag);
        } else {
            this.value &= ~manager.mask(flag);
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Flags<F> setAll(final boolean state, final F... flags) {
        if (state) {
            for (F flag : flags) {
                this.value |= manager.mask(flag);
            }
        } else {
            for (F flag : flags) {
                this.value &= ~manager.mask(flag);
            }
        }
        return this;
    }

    @Override
    public Flags<F> duplicate() {
        return new Flags<F>(manager).value(value);
    }

    @Override
    public ImmutableFlags<F> immutable() {
        return new ImmutableFlags<F>(manager, value);
    }

    @Override
    public Flags<F> mutable() {
        return this;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

}
