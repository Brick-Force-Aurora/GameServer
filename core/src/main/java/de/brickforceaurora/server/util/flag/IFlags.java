package de.brickforceaurora.server.util.flag;

public interface IFlags<F> {

    public static class Builder<B, F> {

        private final B parent;
        private final Flags<F> flags;

        Builder(final B parent, final Flags<F> flags) {
            this.parent = parent;
            this.flags = flags;
        }

        public int value() {
            return flags.value();
        }

        public Builder<B, F> value(final int value) {
            flags.value(value);
            return this;
        }

        public boolean is(final F flag) {
            return flags.is(flag);
        }

        public Builder<B, F> set(final F flag, final boolean state) {
            flags.set(flag, state);
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<B, F> setAll(final boolean state, final F... flags) {
            this.flags.setAll(state, flags);
            return this;
        }

        public Flags<F> delegate() {
            return flags;
        }
        
        public ImmutableFlags<F> immutable() {
            return flags.immutable();
        }

        public final B done() {
            return parent;
        }

    }
    
    int value();
    
    IFlags<F> value(int value);
    
    boolean is(F flag);
    
    IFlags<F> set(F flag, boolean state);
    
    @SuppressWarnings("unchecked")
    IFlags<F> setAll(boolean state, F... flags);
    
    IFlags<F> duplicate();
    
    IFlags<F> immutable();
    
    IFlags<F> mutable();
    
    boolean isMutable();

}
