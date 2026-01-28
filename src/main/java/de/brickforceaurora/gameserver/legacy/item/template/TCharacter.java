package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TCharacter extends TItem {

    public String prefix;
    public String gender;
    public String mainMat;

    public TCharacter(final String itemCode, final String itemName, final Texture2D itemIcon, final int ct, final boolean itemTakeoffable,
        final String gender, final String prefix, final String itemComment, final TBuff tb, final boolean itemDiscomposable,
        final String itemBpBackCode, final int season, final String itemMainMat, final String grp1, final String grp2, final String grp3,
        final int starRate) {
        super(itemCode, ItemType.CHARACTER, itemName, itemIcon, ct, 0, itemTakeoffable, ItemSlot.CHARACTER, itemComment, tb,
            itemDiscomposable, itemBpBackCode, -1, UpgradeCategory.NONE, false, starRate);

        this.gender = gender;
        this.prefix = prefix;
        this.season = season;
        this.mainMat = itemMainMat;

        this.grp1 = grp1;
        this.grp2 = grp2;
        this.grp3 = grp3;
    }
}
