package de.brickforceaurora.gameserver.util;

import java.util.Optional;

import me.lauriichan.snowframe.util.attribute.IAttributable;

public final class Attribute<T> {

    public static final <T> Attribute<T> of(String key, Class<T> type) {
        return new Attribute<>(key, type);
    }

    private final String key;
    private final Class<T> type;

    private Attribute(String key, Class<T> type) {
        this.key = key;
        this.type = type;
    }

    public <E extends IAttributable> E set(E attributes, T value) {
        if (value == null) {
            return unset(attributes);
        }
        attributes.attrSet(key, value);
        return attributes;
    }

    public <E extends IAttributable> E unset(E attributes) {
        attributes.attrUnset(key);
        return attributes;
    }

    public Optional<T> get(IAttributable attributes) {
        return Optional.ofNullable(attributes.attr(key, type));
    }

    public T getOrElse(IAttributable attributes, T fallback) {
        return attributes.attrOrDefault(key, type, fallback);
    }

}
