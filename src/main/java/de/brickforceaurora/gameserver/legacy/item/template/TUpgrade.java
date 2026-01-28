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

    public TUpgrade(final String itemCode, final String itemName, final Texture2D itemIcon, final int ct, final int tier,
        final String target, final int playerLv, final int reqLv, final int maxLv, final String itemComment, final int starRate) {
        super(itemCode, ItemType.UPGRADE, itemName, itemIcon, ct, 0, false,               // itemTakeoffable
            ItemSlot.NONE, itemComment, null,                // TBuff
            false,               // itemDiscomposable
            "",                  // bpBackCode
            -1, UpgradeCategory.NONE, false, starRate);

        this.tier = tier;
        this.target = target;
        this.playerLv = playerLv;
        this.reqLv = reqLv;
        this.maxLv = maxLv;
        this.targetType = TItem.string2UpgradeType(target);

        this.isAmount = true;
    }
}
