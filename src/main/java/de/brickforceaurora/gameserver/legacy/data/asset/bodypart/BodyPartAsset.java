package de.brickforceaurora.gameserver.legacy.data.asset.bodypart;

import de.brickforceaurora.gameserver.legacy.data.asset.Asset;

public final class BodyPartAsset extends Asset {
    
    private final float damageFactor;

    public BodyPartAsset(String name, int id, float damageFactor) {
        super(name, id);
        this.damageFactor = damageFactor;
    }
    
    public float damageFactor() {
        return damageFactor;
    }

}
