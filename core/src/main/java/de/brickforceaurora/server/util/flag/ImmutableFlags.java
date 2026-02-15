package de.brickforceaurora.server.util.flag;

public final class ImmutableFlags<F> implements IFlags<F> {

    private final FlagManager<F> manager;
    private final int value;

    ImmutableFlags(FlagManager<F> manager, int value) {
        this.manager = manager;
        this.value = value;
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public ImmutableFlags<F> value(int value) {
        return this;
    }

    @Override
    public boolean is(final F flag) {
        int mask = manager.mask(flag);
        return (this.value & mask) == mask;
    }

    @Override
    public ImmutableFlags<F> set(F flag, boolean state) {
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ImmutableFlags<F> setAll(boolean state, F... flags) {
        return this;
    }

    @Override
    public ImmutableFlags<F> duplicate() {
        return this;
    }

    @Override
    public ImmutableFlags<F> immutable() {
        return this;
    }

    @Override
    public Flags<F> mutable() {
        return new Flags<>(manager).value(value);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
