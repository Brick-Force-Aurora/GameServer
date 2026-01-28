package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TBundle extends TItem {

    public TBundle(
            String itemCode,
            String itemName,
            Texture2D itemIcon,
            int ct,
            boolean isAmount,
            String itemComment,
            int season,
            int starRate
    ) {
        super(
                itemCode,
                ItemType.BUNDLE,
                itemName,
                itemIcon,
                ct,
                0,
                false,              // itemTakeoffable
                ItemSlot.NONE,
                itemComment,
                null,               // TBuff
                false,              // itemDiscomposable
                "",                 // bpBackCode
                -1,                 // upgradeType
                UpgradeCategory.NONE,
                false,              // basic
                starRate
        );

        this.isAmount = isAmount;
        this.season = season;
    }
}
