package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TUpgrade extends TItem {

    public int tier;
    public String target;
    public int playerLv;
    public int reqLv;
    public int maxLv;
    public int targetType;

    public TUpgrade(
            String itemCode,
            String itemName,
            Texture2D itemIcon,
            int ct,
            int tier,
            String target,
            int playerLv,
            int reqLv,
            int maxLv,
            String itemComment,
            int starRate
    ) {
        super(
                itemCode,
                ItemType.UPGRADE,
                itemName,
                itemIcon,
                ct,
                0,
                false,               // itemTakeoffable
                ItemSlot.NONE,
                itemComment,
                null,                // TBuff
                false,               // itemDiscomposable
                "",                  // bpBackCode
                -1,
                UpgradeCategory.NONE,
                false,
                starRate
        );

        this.tier = tier;
        this.target = target;
        this.playerLv = playerLv;
        this.reqLv = reqLv;
        this.maxLv = maxLv;
        this.targetType = TItem.string2UpgradeType(target);

        this.isAmount = true;
    }
}
