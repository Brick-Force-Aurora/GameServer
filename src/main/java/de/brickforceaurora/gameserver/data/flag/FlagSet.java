package de.brickforceaurora.gameserver.data.flag;

public final class FlagSet<F extends IFlag> {
    
    private volatile int value;
    
    public FlagSet() {}
    
    public FlagSet(int value) {
        this.value = value;
    }

}
