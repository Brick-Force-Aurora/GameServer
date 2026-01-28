package de.brickforceaurora.gameserver.legacy.item.template;

public class TBuff {

    private final int index;

    private final boolean isPoint;
    private final boolean isXp;
    private final boolean isLuck;

    private final float factor;

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

    public TBuff(final int index, final boolean isPoint, final boolean isXp, final boolean isLuck, final float factor) {

        this.index = index;
        this.isPoint = isPoint;
        this.isXp = isXp;
        this.isLuck = isLuck;
        this.factor = factor;
    }
}
