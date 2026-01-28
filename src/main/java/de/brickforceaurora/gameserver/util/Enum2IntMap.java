package de.brickforceaurora.gameserver.util;

import java.io.Serial;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenCustomHashMap;

public class Enum2IntMap<K extends Enum<K>> extends Reference2IntOpenCustomHashMap<K> {
    @Serial
    private static final long serialVersionUID = -2511917123463504418L;
    private static final Hash.Strategy<Enum<?>> STRATEGY = new Hash.Strategy<>() {
        @Override
        public int hashCode(final Enum<?> o) {
            return o.ordinal();
        }

        @Override
        public boolean equals(final Enum<?> a, final Enum<?> b) {
            return a == b;
        }
    };

    public Enum2IntMap(final Class<K> enumType) {
        super(((Enum[]) enumType.getEnumConstants()).length, 0.6F, STRATEGY);
    }
}
