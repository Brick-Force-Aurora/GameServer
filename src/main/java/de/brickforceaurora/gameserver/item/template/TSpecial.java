package de.brickforceaurora.gameserver.item.template;

import de.brickforceaurora.gameserver.item.ItemSlot;
import de.brickforceaurora.gameserver.item.ItemType;
import de.brickforceaurora.gameserver.item.UpgradeCategory;
import de.brickforceaurora.gameserver.util.Texture2D;

public class TSpecial extends TItem {

    public int functionMask;
    public String param;

    /* =====================
       Derived properties
       ===================== */

    public boolean isConsumableBuff() {
        return functionMask == 82
                || functionMask == 81
                || functionMask == 84
                || functionMask == 83
                || functionMask == 86
                || functionMask == 85;
    }

    /* =====================
       Constructor
       ===================== */

    public TSpecial(String itemCode,
                    String itemName,
                    Texture2D itemIcon,
                    int ct,
                    boolean isAmount,
                    int functionMask,
                    String itemComment,
                    boolean itemDiscomposable,
                    String itemBpBackCode,
                    boolean basic,
                    int season,
                    String param,
                    int starRate) {

        super(
                itemCode,
                ItemType.SPECIAL,
                itemName,
                itemIcon,
                ct,
                0,                 // catKind
                false,             // itemTakeoffable
                ItemSlot.NONE,
                itemComment,
                null,              // TBuff
                itemDiscomposable,
                itemBpBackCode,
                -1,                // upgradeType
                UpgradeCategory.NONE,
                basic,
                starRate
        );

        this.functionMask = functionMask;
        this.isAmount = isAmount;
        this.season = season;
        this.param = param;
    }
}
