package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TBundle extends TItem {

    public TBundle(final String itemCode, final String itemName, final Texture2D itemIcon, final int ct, final boolean isAmount,
        final String itemComment, final int season, final int starRate) {
        super(itemCode, ItemType.BUNDLE, itemName, itemIcon, ct, 0, false,              // itemTakeoffable
            ItemSlot.NONE, itemComment, null,               // TBuff
            false,              // itemDiscomposable
            "",                 // bpBackCode
            -1,                 // upgradeType
            UpgradeCategory.NONE, false,              // basic
            starRate);

        this.isAmount = isAmount;
        this.season = season;
    }
}
