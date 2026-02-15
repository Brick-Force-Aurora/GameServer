package de.brickforceaurora.server.util.fastutil;

@FunctionalInterface
public interface Obj2IntFunc<O> {

    int apply(O obj);

}
