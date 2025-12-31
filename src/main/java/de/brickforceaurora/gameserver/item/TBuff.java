package de.brickforceaurora.gameserver.item;

public class TBuff {

    private int index;

    private boolean isPoint;
    private boolean isXp;
    private boolean isLuck;

    private float factor;

    /* =====================
       Getters (properties)
       ===================== */

    public int getIndex() {
        return index;
    }

    public boolean isPoint() {
        return isPoint;
    }

    public boolean isXp() {
        return isXp;
    }

    public boolean isLuck() {
        return isLuck;
    }

    public float getFactor() {
        return factor;
    }

    public int getPointRatio() {
        if (!isPoint) {
            return 0;
        }
        return (int) Math.floor(factor * 100f);
    }

    public int getXpRatio() {
        if (!isXp) {
            return 0;
        }
        return (int) Math.floor(factor * 100f);
    }

    public int getLuck() {
        if (!isLuck) {
            return 0;
        }
        return (int) Math.floor(factor * 100f);
    }

    /* =====================
       Constructor
       ===================== */

    public TBuff(int index,
                 boolean isPoint,
                 boolean isXp,
                 boolean isLuck,
                 float factor) {

        this.index = index;
        this.isPoint = isPoint;
        this.isXp = isXp;
        this.isLuck = isLuck;
        this.factor = factor;
    }
}
