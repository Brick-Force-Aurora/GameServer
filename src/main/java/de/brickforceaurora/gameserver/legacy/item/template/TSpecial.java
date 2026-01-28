package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TSpecial extends TItem {

    public int functionMask;
    public String param;

    /* =====================
       Derived properties
       ===================== */

    public boolean isConsumableBuff() {
        return functionMask == 82 || functionMask == 81 || functionMask == 84 || functionMask == 83 || functionMask == 86
            || functionMask == 85;
    }

    /* =====================
       Constructor
       ===================== */

    public TSpecial(final String itemCode, final String itemName, final Texture2D itemIcon, final int ct, final boolean isAmount,
        final int functionMask, final String itemComment, final boolean itemDiscomposable, final String itemBpBackCode, final boolean basic,
        final int season, final String param, final int starRate) {

        super(itemCode, ItemType.SPECIAL, itemName, itemIcon, ct, 0,                 // catKind
            false,             // itemTakeoffable
            ItemSlot.NONE, itemComment, null,              // TBuff
            itemDiscomposable, itemBpBackCode, -1,                // upgradeType
            UpgradeCategory.NONE, basic, starRate);

        this.functionMask = functionMask;
        this.isAmount = isAmount;
        this.season = season;
        this.param = param;
    }
}
