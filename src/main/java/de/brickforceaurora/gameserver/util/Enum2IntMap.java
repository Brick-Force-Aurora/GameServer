package de.brickforceaurora.gameserver.util;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenCustomHashMap;

import java.io.Serial;

public class Enum2IntMap<K extends Enum<K>> extends Reference2IntOpenCustomHashMap<K> {
    @Serial
    private static final long serialVersionUID = -2511917123463504418L;
    private static final Hash.Strategy<Enum<?>> STRATEGY = new Hash.Strategy<Enum<?>>() {
        public int hashCode(Enum<?> o) {
            return o.ordinal();
        }

        public boolean equals(Enum<?> a, Enum<?> b) {
            return a == b;
        }
    };

    public Enum2IntMap(Class<K> enumType) {
        super(((Enum[])enumType.getEnumConstants()).length, 0.6F, STRATEGY);
    }
}
